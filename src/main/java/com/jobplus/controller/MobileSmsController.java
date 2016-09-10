package com.jobplus.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jobplus.service.impl.EmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jobplus.service.impl.MobileSmsService;

/**
 * 
 * 
 * Title: MobileSmsController <br>
 * Description: 手机短信发送<br>
 * Copyright:suzhoupingjia Copyright (C) 2016 <br>
 * 
 * @author <a href="mailto:anan.wang@jobplus.com.cn">WangFei(Anan.wang)</a><br>
 * @e-mail: anan.wang@jobplus.com.cn <br>
 * @version 1.0 <br>
 * @creatdate Aug 9, 2016 2:08:37 PM <br>
 *
 */

@Controller
@RequestMapping("/mobilesms")
public class MobileSmsController {
	
	@Resource
	private MobileSmsService mobileSmsService;

	@Resource
	private EmailService emailService;

	/**
	 * 短信发送
	 * @param request
	 * @param response
	 * @return 根据returnStatus状态发送短信是否成功 000为成功/其它为失败
	 * {\"returnStatus\":\"001\",\"returnData\":\"系统异常\"} 
	 * {\"returnStatus\":\"000\",\"returnData\":\"smsId\"}
	 */
	@ResponseBody
	@RequestMapping(value = "/sendSms", method = RequestMethod.POST)
	public String sendSmsCode(HttpServletRequest request, HttpServletResponse response,String mobileNo) {
        if (mobileNo.indexOf("@") > -1)
            return emailService.sendCode(request, mobileNo);
        return mobileSmsService.sendSmsCode(request, mobileNo);
    }
	
	
	/**
	 * 短信校验
	 * @param request
	 * @param response
	 * @return 1:通过 /其它值为不通过
	 */
	@ResponseBody
	@RequestMapping(value = "/checkSms", method = RequestMethod.POST)
	public String checkSmsCode(HttpServletRequest request, HttpServletResponse response,String smsId,String validateCode) {
		return mobileSmsService.checkSmsCode(smsId, validateCode);
	}
}
