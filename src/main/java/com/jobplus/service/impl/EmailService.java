package com.jobplus.service.impl;

import com.jobplus.dao.MobileSmsMapper;
import com.jobplus.pojo.MobileSms;
import com.jobplus.service.IEmailService;
import com.jobplus.service.ISequenceService;
import com.jobplus.utils.Common;
import com.jobplus.utils.DateUtils;
import com.jobplus.utils.EmailUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


/**
 * Title: jobplus <br>
 * Description: 邮箱服务类<br>
 * Copyright: suzhoupingjia Copyright (C) 2016 <br>
 *
 * @author <a href="mailto:anan.wang@jobplus.com.cn">WangFei(Anan.wang)</a><br>
 * @version 1.0 <br>
 * @e-mail: anan.wang@jobplus.com.cn <br>
 * @creatdate Aug 9, 2016 2:22:51 PM <br>
 */

@Service("emailService")
public class EmailService implements IEmailService {

    @Resource
    private MobileSmsMapper mobileSmsDao;

    @Resource
    private ISequenceService seqService;

    @Value("${mail.host}")
    public String host;//服务器地址

    @Value("${mail.username}")
    public String username;//发送人

    @Value("${mail.sender}")
    public String sender;//发送人邮箱

    @Value("${mail.password}")
    public String password;//密码

    /**
     * 每个手机号一分钟内只允许发送一次短信
     * 同一个ip地址1小时内只允许调用30次
     * 同一个手机号一小时内只允许接收20次短信
     */
    @Override
    public String sendCode(HttpServletRequest request, String email) {
        String ipAddr = Common.getIpAddress(request);
        int flag = this.checkSend(ipAddr, email);
        String str = "";
        if (0 == flag) {
            //发送验证码并入库
            String validateCode = Common.random();
            MobileSms sms = new MobileSms();
            int id = seqService.nextVal("tbl_mobilesms");
            if (!sendEmail(email, validateCode)) {
                sms.setSendstatus(0);
                sms.setSendresult("发送短信异常");
                str = "{\"returnStatus\":\"001\",\"returnData\":\"系统异常\"}";
            } else {
                sms.setSendstatus(1);
                str = "{\"returnStatus\":\"000\",\"returnData\":\"" + String.valueOf(id) + "\"}";
            }
            sms.setId(id);
            sms.setMobile(email);
            sms.setValidatecode(validateCode);
            sms.setIp(ipAddr);
            mobileSmsDao.insertEmail(sms);
            return str;
        } else {
            str = "{\"returnStatus\":\"001\",\"returnData\":\"" + checkFlag(flag) + "\"}";
            return str;
        }
    }

    public static String checkFlag(int flag) {
        if (flag > 1) {
            return "你涉嫌恶意发送邮件";
        }
        return "你的邮箱校验码还在有效期";
    }

    public int checkSend(String mobileNo, String ip) {
        int check = 0;
        check = mobileSmsDao.isSendValidateCode(mobileNo, ip);
        if (check > 0) {
            check = 1;
        }
        return check;
    }

    public boolean sendEmail(String email, String paramStr) {
        String subject = "聘+帐号--邮箱身份验证";
        String content = "<table cellpadding=\"0\" cellspacing=\"0\" width=\"96%\" style=\"background:#ffffff;border:1px solid rgb(204,204,204);margin:2%;\"> <tbody><tr><td width=\"30px;\">&nbsp;</td><td align=\"\"><div style=\"line-height:40px;height:40px;\">&nbsp;</div><p style=\"margin:0px;padding:0px;\"><strong style=\"font-size:14px;line-height:30px;color:#333333;font-family:arial,sans-serif;\">亲爱的用户：</strong></p><div style=\"line-height:20px;height:20px;\">&nbsp;</div><p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\">您好！感谢您使用聘+服务，您正在进行邮箱验证，本次请求的验证码为：</p><p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\"><b style=\"font-size:18px;color:#ff9900\">" + paramStr + "</b><span style=\"margin:0px;padding:0px;margin-left:10px;line-height:30px;font-size:14px;color:#979797;font-family:'宋体',arial,sans-serif;\">(为了保障您帐号的安全性，请在1小时内完成验证。)</span></p><div style=\"line-height:80px;height:80px;\">&nbsp;</div><p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\">聘+帐号团队</p><p style=\"margin:0px;padding:0px;line-height:30px;font-size:14px;color:#333333;font-family:'宋体',arial,sans-serif;\">" + DateUtils.getDate("yyyy年MM月dd日") + "</p><div style=\"line-height:20px;height:20px;\">&nbsp;</div></td></tr></tbody></table>";
        Map<String, String> recipients = new HashMap<String, String>();
        recipients.put(email, email);
        try {
            EmailUtil emailTool = new EmailUtil(host, username, password,
                    sender, recipients, subject,
                    content, null, EmailUtil.CONTENT_TYPE_HTML,
                    null, null);
            /*发送*/
            return emailTool.send();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return false;
    }
}
