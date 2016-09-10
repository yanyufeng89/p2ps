package com.jobplus.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.SocketException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jobplus.pojo.FTPStatus;

@Service("threadFTPUtils")
public class ThreadFTPUtils implements Runnable {
	
	@Value("${ftp.ip}")
	public String ftpIp;
	@Value("${ftp.port}")
	public String ftpPort;
	@Value("${ftp.username}")
	public String ftpUserName;
	@Value("${ftp.passwd}")
	public String ftpPasswd;
	@Value("${ftp.file.dir}")
	public String ftpFileDir;
	@Value("${ftp.img.dir}")
	public String ftpImgDir;
	@Value("${dir.server}")
	public String dirServer;
	@Value("${img.server}")
	public String imgServer;
	
	
	@Value("${ftp.headIcon.dir}")
	public String ftpHeadIconDir;
	@Value("${headIcon.server}")
	public String headIconServer;
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ThreadFTPUtils.class);

	private UserInfo userInfo;

	private FTPClient ftpClient = new FTPClient();

	private FTPType ftpType;

	public FTPType getFtpType() {
		return ftpType;
	}

	public void setFtpType(FTPType ftpType) {
		this.ftpType = ftpType;
	}

	public static enum FTPType {

		UPLOAD(0), DOWNLOAD(1), RENAME(2), DELETE(3);

		private int type;

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		FTPType(int type) {
			this.type = type;
		}
	}

	/**
	 * 对象构造 设置将过程中使用到的命令输出到控制台
	 */
	public ThreadFTPUtils(String ip, int port, String username, String password, String local, String remote,
			FTPType ftpType) {
		userInfo = new UserInfo(ip, port, username, password, local, remote, null, null);
		this.ftpType = ftpType;
		this.ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
	}
	
	/**
	 * 对象构造 设置将过程中使用到的命令输出到控制台
	 */
	public ThreadFTPUtils(String ip, int port, String username, String password, String remote, File file,
			FTPType ftpType) {
		userInfo = new UserInfo(ip, port, username, password, null, remote, file, null);
		this.ftpType = ftpType;
		this.ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
	}

	public ThreadFTPUtils(){
		this.ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
	}
	
	/**
	 * 对象构造 设置将过程中使用到的命令输出到控制台
	 */
	public ThreadFTPUtils(String ip, int port, String username, String password, String remote, InputStream is,
			FTPType ftpType) {
		userInfo = new UserInfo(ip, port, username, password, null, remote, null, is);
		this.ftpType = ftpType;
		this.ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
	}
	public ThreadFTPUtils(String remote, InputStream is, FTPType ftpType) {
		String ip = this.ftpIp; 
		int port = Integer.parseInt(this.ftpPort);
		String username = this.ftpUserName;
		String password = this.ftpPasswd;
		userInfo = new UserInfo(ip, port, username, password, null, remote, null, is);
		this.ftpType = ftpType;
		this.ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
	}
	
	public void uploadInit(String remote, InputStream is, FTPType ftpType) {
		String ip = this.ftpIp; 
		int port = Integer.parseInt(this.ftpPort);
		String username = this.ftpUserName;
		String password = this.ftpPasswd;
		userInfo = new UserInfo(ip, port, username, password, null, remote, null, is);
		this.ftpType = ftpType;
		this.ftpClient.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
	}

	/**
	 * 
	 * java编程中用于连接到FTP服务器
	 * 
	 * @param hostname
	 *            主机名
	 * 
	 * @param port
	 *            端口
	 * 
	 * @param username
	 *            用户名
	 * 
	 * @param password
	 *            密码
	 * 
	 * @return 是否连接成功
	 * 
	 * @throws IOException
	 */

	public boolean connect(String hostname, int port, String username, String password) throws IOException {

		ftpClient.connect(hostname, port);

		if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {

			if (ftpClient.login(username, password)) {
				return true;
			}
		}
		disconnect();
		return false;
	}

	/**
	 * 删除远程FTP文件
	 * 
	 * @param remote
	 *            远程文件路径
	 * @return
	 * @throws IOException
	 */
	public FTPStatus delete(String remote) throws IOException {
		ftpClient.enterLocalPassiveMode();

		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

		FTPStatus result = null;

		FTPFile[] files = ftpClient.listFiles(remote);
		if (files.length == 1) {
			boolean status = ftpClient.deleteFile(remote);
			result = status ? FTPStatus.Delete_Remote_Success : FTPStatus.Delete_Remote_Faild;
		} else {
			result = FTPStatus.Not_Exist_File;
		}
		logger.info("FTP服务器文件删除标识：" + result);
		return result;
	}

	/**
	 * 重命名远程FTP文件
	 * 
	 * @param name
	 *            新远程文件名称(路径-必须保证在同一路径下)
	 * 
	 * @param remote
	 *            远程文件路径
	 * 
	 * @return 是否成功
	 * 
	 * @throws IOException
	 */
	public FTPStatus rename(String name, String remote) throws IOException {
		ftpClient.enterLocalPassiveMode();

		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

		FTPStatus result = null;

		FTPFile[] files = ftpClient.listFiles(remote);
		if (files.length == 1) {
			boolean status = ftpClient.rename(remote, name);
			result = status ? FTPStatus.Remote_Rename_Success : FTPStatus.Remote_Rename_Faild;
		} else {
			result = FTPStatus.Not_Exist_File;
		}
		logger.info("FTP服务器文件名更新标识：" + result);
		return result;
	}

	/**
	 * 
	 * 从FTP服务器上下载文件
	 * 
	 * @param fileName
	 *            下载文件的名字(包括后缀名)
	 * 
	 * @param remote
	 *            远程文件路径
	 * 
	 * @param local
	 *            本地文件路径
	 * 
	 * @return 是否成功
	 * 
	 * @throws IOException
	 */

	public FTPStatus download(String fileName, String remote, HttpServletResponse response) throws IOException {
		// 开启输出流弹出文件保存路径选择窗口
		response.setContentType("application/octet-stream");

		response.setContentType("application/OCTET-STREAM;charset=UTF-8");

		response.setHeader("Content-Disposition", "attachment;filename=" + fileName);

		ftpClient.enterLocalPassiveMode();

		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

		FTPStatus result;

		OutputStream out = response.getOutputStream();

		boolean status = ftpClient.retrieveFile(remote, out);

		result = status ? FTPStatus.Download_From_Break_Success : FTPStatus.Download_From_Break_Faild;

		logger.info("FTP服务器文件下载标识：" + result);

		out.close();

		return result;
	}

	/**
	 * 
	 * 从FTP服务器上下载文件
	 * 
	 * @param remote
	 *            远程文件路径
	 * 
	 * @param local
	 *            本地文件路径
	 * 
	 * @return 是否成功
	 * 
	 * @throws IOException
	 */

	@SuppressWarnings("resource")
	public FTPStatus download(String remote, String local) throws IOException {

		ftpClient.enterLocalPassiveMode();

		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

		FTPStatus result;

		File f = new File(local);

		FTPFile[] files = ftpClient.listFiles(remote);

		if (files.length != 1) {
			logger.info("远程文件不唯一");
			return FTPStatus.File_Not_Unique;
		}

		long lRemoteSize = files[0].getSize();

		if (f.exists()) {
			OutputStream out = new FileOutputStream(f, true);
			logger.info("本地文件大小为:" + f.length());

			if (f.length() >= lRemoteSize) {

				logger.info("本地文件大小大于远程文件大小，下载中止");
				return FTPStatus.Remote_smaller_local;

			}

			ftpClient.setRestartOffset(f.length());

			boolean status = ftpClient.retrieveFile(remote, out);
			result = status ? FTPStatus.Download_From_Break_Success : FTPStatus.Download_From_Break_Faild;
			out.close();

		} else {
			OutputStream out = new FileOutputStream(f);
			boolean status = ftpClient.retrieveFile(remote, out);
			result = status ? FTPStatus.Download_From_Break_Success : FTPStatus.Download_From_Break_Faild;
			out.close();
		}

		return result;

	}

	/**
	 * 
	 * 上传文件到FTP服务器，支持断点续传
	 * 
	 * @param local
	 *            本地文件名称，绝对路径
	 * 
	 * @param remote
	 *            远程文件路径，使用/home/directory1/subdirectory/file.ext
	 *            按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构
	 * 
	 * @return 上传结果
	 * 
	 * @throws IOException
	 */

	@SuppressWarnings("resource")
	public FTPStatus upload(String local, String remote) throws IOException {
		// 设置PassiveMode传输
		ftpClient.enterLocalPassiveMode();

		// 设置以二进制流的方式传输
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

		FTPStatus result;

		// 对远程目录的处理
		String remoteFileName = remote;

		if (remote.contains("/")) {

			remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);

			String directory = remote.substring(0, remote.lastIndexOf("/") + 1);

			if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(directory)) {

				// 如果远程目录不存在，则递归创建远程服务器目录

				int start = 0;

				int end = 0;

				if (directory.startsWith("/")) {

					start = 1;

				} else {

					start = 0;

				}

				end = directory.indexOf("/", start);

				while (true) {

					String subDirectory = remote.substring(start, end);

					if (!ftpClient.changeWorkingDirectory(subDirectory)) {

						if (ftpClient.makeDirectory(subDirectory)) {

							ftpClient.changeWorkingDirectory(subDirectory);

						} else {

							logger.info("创建目录失败");

							return FTPStatus.Create_Directory_Fail;

						}

					}

					start = end + 1;

					end = directory.indexOf("/", start);

					// 检查所有目录是否创建完毕

					if (end <= start) {

						break;

					}

				}

			}

		}

		// 检查远程是否存在文件

		FTPFile[] files = ftpClient.listFiles(remoteFileName);

		if (files.length == 1) {

			long remoteSize = files[0].getSize();

			File f = new File(local);

			long localSize = f.length();

			if (remoteSize == localSize) {

				return FTPStatus.File_Exits;

			} else if (remoteSize > localSize) {

				return FTPStatus.Remote_Bigger_Local;

			}

			// 尝试移动文件内读取指针,实现断点续传

			InputStream is = new FileInputStream(f);

			if (is.skip(remoteSize) == remoteSize) {

				ftpClient.setRestartOffset(remoteSize);

				if (ftpClient.storeFile(new String(remote.getBytes("UTF-8"),"iso-8859-1"), is)) {

					return FTPStatus.Upload_From_Break_Success;

				}

			}

			// 如果断点续传没有成功，则删除服务器上文件，重新上传

			if (!ftpClient.deleteFile(remoteFileName)) {

				return FTPStatus.Delete_Remote_Faild;

			}

			is = new FileInputStream(f);

			if (ftpClient.storeFile( new String(remote.getBytes("UTF-8"),"iso-8859-1"), is)) {

				result = FTPStatus.Upload_New_File_Success;

			} else {

				result = FTPStatus.Upload_New_File_Failed;

			}

			is.close();

		} else {

			InputStream is = new FileInputStream(local);

			if (ftpClient.storeFile( new String(remoteFileName.getBytes("UTF-8"),"iso-8859-1"), is)) {

				result = FTPStatus.Upload_New_File_Success;

			} else {

				result = FTPStatus.Upload_New_File_Failed;

			}

			is.close();
		}

		return result;

	}

	/**
	 * 
	 * 上传文件到FTP服务器，支持断点续传
	 * 
	 * @param local
	 *            本地文件名称，绝对路径
	 * 
	 * @param remote
	 *            远程文件路径，使用/home/directory1/subdirectory/file.ext
	 *            按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构
	 * 
	 * @return 上传结果
	 * 
	 * @throws IOException
	 */

	@SuppressWarnings("resource")
	public FTPStatus upload(File file, String remote) throws IOException {
		// 设置PassiveMode传输
		ftpClient.enterLocalPassiveMode();

		// 设置以二进制流的方式传输
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

		FTPStatus result;

		// 对远程目录的处理
		String remoteFileName = remote;

		if (remote.contains("/")) {

			remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);

			String directory = remote.substring(0, remote.lastIndexOf("/") + 1);

			if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(directory)) {

				// 如果远程目录不存在，则递归创建远程服务器目录

				int start = 0;

				int end = 0;

				if (directory.startsWith("/")) {

					start = 1;

				} else {

					start = 0;

				}

				end = directory.indexOf("/", start);

				while (true) {

					String subDirectory = remote.substring(start, end);

					if (!ftpClient.changeWorkingDirectory(subDirectory)) {

						if (ftpClient.makeDirectory(subDirectory)) {

							ftpClient.changeWorkingDirectory(subDirectory);

						} else {

							logger.info("创建目录失败");

							return FTPStatus.Create_Directory_Fail;

						}

					}

					start = end + 1;

					end = directory.indexOf("/", start);

					// 检查所有目录是否创建完毕

					if (end <= start) {

						break;

					}

				}

			}

		}

		// 检查远程是否存在文件

		FTPFile[] files = ftpClient.listFiles(remoteFileName);

		if (files.length == 1) {

			long remoteSize = files[0].getSize();

			File f = file;

			long localSize = f.length();

			if (remoteSize == localSize) {

				return FTPStatus.File_Exits;

			} else if (remoteSize > localSize) {

				return FTPStatus.Remote_Bigger_Local;

			}

			// 尝试移动文件内读取指针,实现断点续传

			InputStream is = new FileInputStream(f);
			if (is.skip(remoteSize) == remoteSize) {

				ftpClient.setRestartOffset(remoteSize);

				if (ftpClient.storeFile(new String(remote.getBytes("UTF-8"), "iso-8859-1"), is)) {

					return FTPStatus.Upload_From_Break_Success;

				}

			}

			// 如果断点续传没有成功，则删除服务器上文件，重新上传

			if (!ftpClient.deleteFile(remoteFileName)) {

				return FTPStatus.Delete_Remote_Faild;

			}

			is = new FileInputStream(f);

			if (ftpClient.storeFile(new String(remote.getBytes("UTF-8"), "iso-8859-1"), is)) {

				result = FTPStatus.Upload_New_File_Success;

			} else {

				result = FTPStatus.Upload_New_File_Failed;

			}

			is.close();

		} else {

			InputStream is = new FileInputStream(file);
			if (ftpClient.storeFile(new String(remoteFileName.getBytes("UTF-8"), "iso-8859-1"), is)) {

				result = FTPStatus.Upload_New_File_Success;

			} else {

				result = FTPStatus.Upload_New_File_Failed;

			}

			is.close();
		}

		return result;

	}

	/**
	 * 
	 * 上传文件到FTP服务器，支持断点续传
	 * 
	 * @param local
	 *            本地文件名称，绝对路径
	 * 
	 * @param remote
	 *            远程文件路径，使用/home/directory1/subdirectory/file.ext
	 *            按照Linux上的路径指定方式，支持多级目录嵌套，支持递归创建不存在的目录结构
	 * 
	 * @return 上传结果
	 * 
	 * @throws IOException
	 */

	@SuppressWarnings("resource")
	public FTPStatus upload(InputStream is, String remote) throws IOException {
		// 设置PassiveMode传输
		ftpClient.enterLocalPassiveMode();

		// 设置以二进制流的方式传输
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

		FTPStatus result;

		// 对远程目录的处理
		String remoteFileName = remote;

		if (remote.contains("/")) {

			remoteFileName = remote.substring(remote.lastIndexOf("/") + 1);

			String directory = remote.substring(0, remote.lastIndexOf("/") + 1);

			if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(directory)) {

				// 如果远程目录不存在，则递归创建远程服务器目录

				int start = 0;

				int end = 0;

				if (directory.startsWith("/")) {

					start = 1;

				} else {

					start = 0;

				}

				end = directory.indexOf("/", start);

				while (true) {

					String subDirectory = remote.substring(start, end);

					if (!ftpClient.changeWorkingDirectory(subDirectory)) {

						if (ftpClient.makeDirectory(subDirectory)) {

							ftpClient.changeWorkingDirectory(subDirectory);

						} else {

							logger.info("创建目录失败");

							return FTPStatus.Create_Directory_Fail;

						}

					}

					start = end + 1;

					end = directory.indexOf("/", start);

					// 检查所有目录是否创建完毕

					if (end <= start) {

						break;

					}

				}

			}

		}

		// 检查远程是否存在文件

		FTPFile[] files = ftpClient.listFiles(remoteFileName);

		if (files.length == 1) {

			long remoteSize = files[0].getSize();

			long localSize = is.available();

			if (remoteSize == localSize) {

				return FTPStatus.File_Exits;

			} else if (remoteSize > localSize) {

				return FTPStatus.Remote_Bigger_Local;

			}

			// 尝试移动文件内读取指针,实现断点续传

			if (is.skip(remoteSize) == remoteSize) {

				ftpClient.setRestartOffset(remoteSize);

				if (ftpClient.storeFile(new String(remote.getBytes("UTF-8"), "iso-8859-1"), is)) {

					return FTPStatus.Upload_From_Break_Success;

				}

			}

			// 如果断点续传没有成功，则删除服务器上文件，重新上传

			if (!ftpClient.deleteFile(remoteFileName)) {

				return FTPStatus.Delete_Remote_Faild;

			}

			if (ftpClient.storeFile(new String(remote.getBytes("UTF-8"), "iso-8859-1"), is)) {

				result = FTPStatus.Upload_New_File_Success;

			} else {

				result = FTPStatus.Upload_New_File_Failed;

			}

			is.close();

		} else {

			if (ftpClient.storeFile(new String(remoteFileName.getBytes("UTF-8"), "iso-8859-1"), is)) {

				result = FTPStatus.Upload_New_File_Success;

			} else {

				result = FTPStatus.Upload_New_File_Failed;

			}

			is.close();
		}

		return result;

	}

	/**
	 * 
	 * 断开与远程服务器的连接
	 * 
	 * @throws IOException
	 */

	public void disconnect() throws IOException {

		if (ftpClient.isConnected()) {
			ftpClient.disconnect();
		}

	}

	@Override
	public void run() {
		boolean status = false;
		// 建立FTP连接
		try {
			ftpClient.connect(userInfo.getIp(), userInfo.getPort());

			if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {

				if (ftpClient.login(userInfo.getUsername(), userInfo.getPassword())) {
					status = true;
				}
			} else {
				try {
					disconnect();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		} catch (SocketException e1) {

			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// FTP连接成功后执行相应的操作
		if (status) {
			FTPStatus result = null;
			if (this.ftpType == FTPType.UPLOAD) {
				try {
					if (null != userInfo.getFile()) {
						result = this.upload(userInfo.getFile(), userInfo.getRemote());// 上传文件
					}else
					if (null != userInfo.getLocal()) {
						result = this.upload(new File(userInfo.getLocal()), userInfo.getRemote());// 上传文件
					}else
					if (null != userInfo.getIs()) {
						result = this.upload(userInfo.getIs(), userInfo.getRemote());// 上传文件
					}else{

					throw new IOException("没有需要上传的资源");
					}
				} catch (IOException e) {
					logger.info("FTP上传文件异常：" + e.getMessage());
				}
			} else if (this.ftpType == FTPType.DOWNLOAD) {
				try {
					result = this.download(userInfo.getRemote(), userInfo.getLocal());// 下载文件
				} catch (IOException e) {
					logger.info("FTP下载文件异常：" + e.getMessage());
				}
			} else if (this.ftpType == FTPType.RENAME) {
				try {
					result = this.rename(userInfo.getLocal(), userInfo.getRemote());// 修改名称
				} catch (IOException e) {
					logger.info("FTP修改文件名称异常：" + e.getMessage());
				}
			} else if (this.ftpType == FTPType.DELETE) {
				try {
					result = this.delete(userInfo.getRemote()); // 删除文件
				} catch (IOException e) {
					logger.info("FTP删除文件异常：" + e.getMessage());
				}
			}
			try {
				disconnect();
			} catch (IOException e) {

				logger.info("FTP连接释放异常：" + e.getMessage());
			}
			logger.info("FTP操作状态码:" + result);
		}

	}

}

class UserInfo {

	private String ip; // FTP服务器的IP地址

	private int port; // FTP服务器端口

	private String username;// 登录用户名

	private String password;// 登录密码

	private String local; // 本地文件或文件名

	private String remote; // 远程文件或路径

	private File file; // 远程文件或路径

	/**
	 * this method get the is of value
	 * 
	 * @return Returns the is.
	 */
	public InputStream getIs() {
		return is;
	}

	/**
	 * this method set the is of value
	 * 
	 * @param enName
	 *            The is to set.
	 */
	public void setIs(InputStream is) {
		this.is = is;
	}

	InputStream is; // 远程文件或路径

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getRemote() {
		return remote;
	}

	public void setRemote(String remote) {
		this.remote = remote;
	}

	public UserInfo() {

	}

	public UserInfo(String ip, int port, String username, String password, String local, String remote, File file,
			InputStream is) {
		this.ip = ip;
		this.port = port;
		this.username = username;
		this.password = password;
		this.local = local;
		this.remote = remote;
		this.file = file;
		this.is = is;
	}

}