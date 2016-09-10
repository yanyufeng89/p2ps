package com.jobplus.utils;

import java.security.MessageDigest;

import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;


/**
 * SHA加密
 */
public class SHAUtil {
	/***
	 * SHA加密 生成40位SHA码
	 * 
	 * @param 待加密字符串
	 * @return 返回40位SHA码
	 */
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(SHAUtil.class);
	public static String shaEncode(String inStr) {
		MessageDigest sha = null;
		StringBuffer hexValue = new StringBuffer();
		try {
			sha = MessageDigest.getInstance("SHA");

			byte[] byteArray = inStr.getBytes("UTF-8");
			byte[] md5Bytes = sha.digest(byteArray);
			for (int i = 0; i < md5Bytes.length; i++) {
				int val = ((int) md5Bytes[i]) & 0xff;
				if (val < 16) {
					hexValue.append("0");
				}
				hexValue.append(Integer.toHexString(val));
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(JSON.toJSONString( "加密密码异常： " +inStr));
			return inStr;
		}

		return hexValue.toString();
	}

	/**
	 * 测试主函数
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception {
		String str = new String("amigoxiexiexingxing");
		String str1 = new String("amigoxiexiexingxing1");
		System.out.println("原始：" + str);
		System.out.println("SHA后：" + shaEncode(str));
		System.out.println("原始：" + str1);
		System.out.println("SHA后：" + shaEncode(str1));
	}
}