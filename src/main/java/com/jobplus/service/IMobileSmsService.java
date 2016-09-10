package com.jobplus.service;

import javax.servlet.http.HttpServletRequest;

public interface IMobileSmsService {
	
	public String sendSmsCode(HttpServletRequest request,String mobileNo);
	
	
	public String checkSmsCode(String  mobileNo,String validateCode);

}
