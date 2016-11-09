package com.jobplus.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

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
import com.jobplus.utils.ThreadFTPUtils.FTPType;

public class FTPUtils {
	@Value("${ftp.ip}")
	public String ftpIp;
	@Value("${ftp.port}")
	public String ftpPort;
	@Value("${ftp.username}")
	public String ftpUserName;
	@Value("${ftp.passwd}")
	public String ftpPsd;
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

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(FTPUtils.class);

	private FTPClient ftpClient = new FTPClient();

	/**
	 * 对象构造 设置将过程中使用到的命令输出到控制台
	 */
	public FTPUtils() {
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
	public boolean connect() throws IOException {
		
		String hostname = this.ftpIp;
		int port = Integer.parseInt(ftpPort);
		String username = this.ftpUserName;
		String password = this.ftpPsd;
		
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

				if (ftpClient.storeFile(remote, is)) {

					return FTPStatus.Upload_From_Break_Success;

				}

			}

			// 如果断点续传没有成功，则删除服务器上文件，重新上传

			if (!ftpClient.deleteFile(remoteFileName)) {

				return FTPStatus.Delete_Remote_Faild;

			}

			is = new FileInputStream(f);

			if (ftpClient.storeFile(remote, is)) {

				result = FTPStatus.Upload_New_File_Success;

			} else {

				result = FTPStatus.Upload_New_File_Failed;

			}

			is.close();

		} else {

			InputStream is = new FileInputStream(local);

			if (ftpClient.storeFile(remoteFileName, is)) {

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

	public static void main1(String[] args) {
		FTPUtils myFtp = new FTPUtils();
		try {

			myFtp.connect("192.168.0.39", 21, "jobftp", "3er4#ER$");

			logger.info(myFtp.upload("C:\\Users\\pj01\\Desktop\\DocsController.java", "/docsDir/DocsController.java")
					.toString());

			myFtp.disconnect();

		} catch (IOException e) {

			logger.info("FTP上传文件异常：" + e.getMessage());

		}

	}

	public static void main2(String[] args) {
		try {
			String fileID = UUIDGenerator.getUUID();
			String fileName = fileID + ".txt";
			ThreadFTPUtils myFtp3 = new ThreadFTPUtils("192.168.0.39", 21, "jobftp", "3er4#ER$", "/docsDir/" + fileName,
					new FileInputStream(new File("C:\\Users\\pj01\\Desktop\\新建文本文档22.html.bak")), FTPType.UPLOAD);
			Thread thread3 = new Thread(myFtp3);
			thread3.start();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		// try {
		// String fileID = UUIDGenerator.getUUID();
		// String fileName = fileID+".txt";
		// ThreadFTPUtils myFtp3 = new ThreadFTPUtils(ftpIp, 21, "jobftp",
		// "3er4#ER$","/docsDir/"+fileName,new FileInputStream(new
		// File("C:\\Users\\pj01\\Desktop\\新建文本文档22.html.bak")),FTPType.UPLOAD);
		// Thread thread3=new Thread(myFtp3);
		// thread3.start();
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		FTPUtils ff = new FTPUtils();
		System.out.println(ff.ftpIp);

	}
}
