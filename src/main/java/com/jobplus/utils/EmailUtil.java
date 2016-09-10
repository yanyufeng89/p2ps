package com.jobplus.utils;

import org.apache.log4j.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * Created with IntelliJ IDEA.
 * User: Eric
 * Date: 16-8-29
 * Time: 下午3:47
 */
public class EmailUtil {
    private String username;
    private String password;
    private String smtpHost;
    private String subject;
    private String senders;
    private Map<String, String> recipients;
    private String content;
    private String contentEncoding;
    private int contentType;
    private File[] attaches;
    private String fileNameEncoding;
    private Session session;
    private static final String PROTOCOL = "smtp";
    private static final String TRANSFER_ENCODING = "ISO-8859-1";
    private static Logger log = Logger.getLogger(EmailUtil.class.getName());
    public static final int CONTENT_TYPE_TEXT = 0;
    public static final int CONTENT_TYPE_HTML = 1;

    /**
     * @param smtpHost         邮箱smtp
     * @param username         发送者名字
     * @param password         发送方帐号密码
     * @param senders          发送方邮箱
     * @param recipients       接收方
     * @param subject          主题
     * @param content          内容
     * @param contentEncoding  编码
     * @param contentType      内容类型
     * @param files            附件
     * @param fileNameEncoding 附件编码
     */
    public EmailUtil(String smtpHost, String username, String password, String senders, Map<String, String> recipients, String subject, String content, String contentEncoding, int contentType, File[] files, String fileNameEncoding) {
        this.smtpHost = smtpHost;
        this.username = username;
        this.password = password;
        this.senders = senders;
        this.recipients = recipients;
        this.subject = subject;
        this.content = content;
        this.contentEncoding = contentEncoding;
        this.contentType = contentType;
        this.attaches = files;
        this.fileNameEncoding = fileNameEncoding;
    }

    /**
     * 继承Authenticator子类用于用户认证  （这里指邮件服务器对用户的认证）
     * 也可外部创建一个单独的邮件实体类（包涵用户名/密码即可），继承Authenticator类来重写PasswordAuthentication方法
     */
    static class MyAuthenricator extends Authenticator {
        private String user = null;
        private String pass = "";

        public MyAuthenricator(String user, String pass) {
            this.user = user;
            this.pass = pass;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(user, pass);
        }
    }

    /**
     * 初始化  session 实例方法
     *
     * @param username     发送邮件的用户名(地址)
     * @param password     密码
     * @param smtpHostName SMTP邮件服务器地址
     */
    private void initSession(String username, String password, String smtpHostName) {
        // 初始化props文件
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", PROTOCOL);//发送邮件协议
        props.put("mail.smtp.auth", "true");        //需要验证
        props.put("mail.smtp.host", smtpHostName);    //服务器地址
        // 根据property文件创建session,并传入Authenticator进行验证
        session = Session.getInstance(props, new MyAuthenricator(username, password));
        // 是否控制台打印消息列表 （可选）
        session.setDebug(false);
    }

    public boolean send() throws MessagingException, UnsupportedEncodingException {
        Transport trans = null;
        try {
            if (this.smtpHost.isEmpty() || this.username.isEmpty() || this.password.isEmpty() || this.senders.isEmpty() || recipients == null || recipients.size() == 0) {
                log.error("参数异常！！！");
                return false;
            }

            initSession(this.senders, this.password, this.smtpHost);
            trans = session.getTransport();
            // 打开链接
            trans.connect(username, password);
            //log.debug("连接邮件服务器成功");
            MimeMultipart multiPart = new MimeMultipart();
            MimeBodyPart mainBodyPart = new MimeBodyPart();

            if (this.contentEncoding == null) {
                this.content = new String(this.content.getBytes(), TRANSFER_ENCODING);
            } else {
                this.content = new String(this.content.getBytes(this.contentEncoding), TRANSFER_ENCODING);
            }

            if (this.contentType == 1) {
                mainBodyPart.setContent(this.content, "text/html");
            } else {
                mainBodyPart.setContent(this.content, "text/plain");
            }
            multiPart.addBodyPart(mainBodyPart);

            if ((this.attaches != null) && (this.attaches.length > 0)) {
                for (int i = 0; i < this.attaches.length; i++) {
                    MimeBodyPart tmpBody = new MimeBodyPart();
                    FileDataSource fds = new FileDataSource(this.attaches[i]);
                    tmpBody.setDataHandler(new DataHandler(fds));
                    String fileName = this.attaches[i].getName();

                    if (this.fileNameEncoding == null) {
                        fileName = new String(fileName.getBytes(), TRANSFER_ENCODING);
                    } else {
                        fileName = new String(fileName.getBytes(this.fileNameEncoding), TRANSFER_ENCODING);
                    }
                    tmpBody.setFileName(fileName);
                    multiPart.addBodyPart(tmpBody);
                }
            }

            MimeMessage msg = new MimeMessage(this.session);
            msg.setContent(multiPart);
            msg.setSubject(this.subject);
            InternetAddress[] senderAddress = InternetAddress.parse(this.senders, true);
            if (!this.username.isEmpty()) {
                senderAddress[0].setPersonal(this.username);
            }
            msg.addFrom(senderAddress);
            InternetAddress[] acceptAddress = new InternetAddress[this.recipients.size()];
            Set<String> sendersKey = this.recipients.keySet();
            int i = 0;
            for (Iterator it = sendersKey.iterator(); it.hasNext(); ) {
                String s = (String) it.next();
                acceptAddress[i] = new InternetAddress(s, this.recipients.get(s));
                i++;
            }
            msg.setRecipients(Message.RecipientType.TO, acceptAddress);
            trans.sendMessage(msg, acceptAddress);
            log.debug("邮件发送完毕");
            return true;
        } catch (Exception e) {
            log.error(e);
            return false;
        } finally {
            if (trans != null) {
                trans.close();
            }
        }
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtpHost() {
        return this.smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSenders() {
        return this.senders;
    }

    public void setSenders(String senders) {
        this.senders = senders;
    }

    public Map<String, String> getRecipients() {
        return recipients;
    }

    public void setRecipients(Map<String, String> recipients) {
        this.recipients = recipients;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getContentType() {
        return this.contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

    public String getContentEncoding() {
        return this.contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public File[] getAttaches() {
        return this.attaches;
    }

    public void setAttaches(File[] attaches) {
        this.attaches = attaches;
    }

    public String getFileNameEncoding() {
        return this.fileNameEncoding;
    }

    public void setFileNameEncoding(String fileNameEncoding) {
        this.fileNameEncoding = fileNameEncoding;
    }
}
