package com.jobplus.service;

import javax.servlet.http.HttpServletRequest;

public interface IEmailService {
	
	public String sendCode(HttpServletRequest request, String email);

}
