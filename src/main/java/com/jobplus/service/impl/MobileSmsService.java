package com.jobplus.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jobplus.dao.MobileSmsMapper;
import com.jobplus.pojo.MobileSms;
import com.jobplus.service.IMobileSmsService;
import com.jobplus.service.ISequenceService;
import com.jobplus.utils.Common;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * 
 * 
 * Title: jobplus <br>
 * Description: 短信服务类<br>
 * Copyright: suzhoupingjia Copyright (C) 2016 <br>
 * 
 * @author <a href="mailto:anan.wang@jobplus.com.cn">WangFei(Anan.wang)</a><br>
 * @e-mail: anan.wang@jobplus.com.cn <br>
 * @version 1.0 <br>
 * @creatdate Aug 9, 2016 2:22:51 PM <br>
 *
 */

@Service("mobileSmsService")
public class MobileSmsService implements IMobileSmsService {

	private static org.slf4j.Logger LOG = LoggerFactory.getLogger(MobileSmsService.class);
	
	@Resource
	private MobileSmsMapper mobileSmsDao;
	
	@Resource
	private ISequenceService seqService;
	
	@Value("${sms.appkey}")
	public String appkey;//TOP分配给应用的AppKey
	
	@Value("${sms.url}")
	public String url;//阿里大于调用地址
	
	@Value("${sms.secret}")
	public String secret;//密匙
	
	@Value("${sms.extend}")
	public String extend;//公共回传参数
	
	@Value("${sms.smsType}")
	public String smsType;//短信类型，传入值请填写normal
	
	@Value("${sms.templateId}")
	public String templateId;//短信模板ID
	
	@Value("${sms.signName}")
	public String signName;//短信签名
	
	@Value("${sms.param}")
	public String param;//短信模板变量{"code":"1234","product":"alidayu"}
	
	
	
	
	/**
	 * 每个手机号一分钟内只允许发送一次短信
	 * 同一个ip地址1小时内只允许调用30次
	 * 同一个手机号一小时内只允许接收20次短信
	 */
	@Override
	public String sendSmsCode(HttpServletRequest request, String mobileNo) {
		String ipAddr = Common.getIpAddress(request);
		int flag = this.checkSend(ipAddr, mobileNo);
		String str="";
		if(0 == flag){
			//发送短信并入库
			String validateCode = Common.random();
			String reaultStr = sendSms(mobileNo,validateCode);
			MobileSms sms = new MobileSms();
			int id = seqService.nextVal("tbl_mobilesms");
			if("error".equals(reaultStr)){
				sms.setSendstatus(0);
				sms.setSendresult("发送短信异常");
				str = "{\"returnStatus\":\"001\",\"returnData\":\"系统异常\"}";
			}else{
				JSONObject ob = (JSONObject)JSON.parse(reaultStr);
				sms.setSendresult(reaultStr);
				if(null!=ob.get("alibaba_aliqin_fc_sms_num_send_response")){
					sms.setSendstatus(1);
					str = "{\"returnStatus\":\"000\",\"returnData\":\""+String.valueOf(id)+"\"}";
				}else{
					sms.setSendstatus(0);
					str = "{\"returnStatus\":\"001\",\"returnData\":\"发送短信异常\"}";
				}
			}
			sms.setId(id);
			sms.setMobile(mobileNo);
			sms.setValidatecode(validateCode);
			sms.setIp(ipAddr);
			
			mobileSmsDao.insert(sms);
			//发送短信
			return str;
		}else{
			str = "{\"returnStatus\":\"001\",\"returnData\":\""+checkFlag(flag)+"\"}";
			return str;
			
		}
		
	}
	
	public static String checkFlag(int flag){
		
		if(flag>1){
			return "你涉嫌恶意发送短信";
		}
		
		return "你的短信校验码还在有效期";
	}

	@Override
	public String checkSmsCode(String id, String validateCode) {
		
		MobileSms sms = new MobileSms();
		sms.setId(Integer.valueOf(id));
		sms.setValidatecode(validateCode);
		return String.valueOf(mobileSmsDao.updateByValidateCode(sms));
	}
	
	
	/**
	 * 每个手机号一分钟内只允许发送一次短信
	 * 同一个ip地址1小时内只允许调用30次
	 * 同一个手机号一小时内只允许接收20次短信
	 */
	public int checkSend(String mobileNo, String ip) {
		int check = 0;
		check = mobileSmsDao.isSendValidateCode(mobileNo, ip);
		if (check > 0) {
			check = 1;
		}
		return check;
	}
	
	
	public String sendSms(String mobileNo,String paramStr){
		
		TaobaoClient client = new DefaultTaobaoClient(this.url, this.appkey, this.secret);  
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();  
		req.setExtend(this.extend);
		req.setSmsType(this.smsType);
		req.setSmsFreeSignName(this.signName);
		req.setSmsParamString(this.param.replace("?", paramStr));
		req.setRecNum(mobileNo);
		req.setSmsTemplateCode(this.templateId); 
		try {  
			AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);  
			return rsp.getBody();  
		} catch (Exception e) {  
		// TODO: handle exception  
			e.printStackTrace();
		} 
		return "error";
	
	}
}
