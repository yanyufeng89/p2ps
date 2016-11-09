package com.jobplus.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.SmsFilter;
import com.jobplus.pojo.User;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ISmsFilterService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUserService;
import com.jobplus.utils.ConstantManager;

/**
 * 个人中心    我的账户 
 * @author Jerry
 * 2016年7月28日上午11:02:47
 *
 */
@Controller
@RequestMapping("/myCenter")
public class MyCenterAccountController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(MyCenterAccountController.class);
	@Resource
	private ISmsService smsService;
	@Resource
	private ISmsFilterService smsFilterService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IUserService userService;
	
	
	/**
	 * 分页加载所有消息
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getAllSms")
	@ResponseBody
	public ModelAndView getAllSms(@RequestHeader("Accept") String encoding,HttpServletRequest request,Sms record) throws Exception {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		record.setReceivedid(Integer.parseInt(userid));
		logger.info("getAllSms  分页加载所有消息     record:" + JSON.toJSONString(record));
		Page<Sms> allSmsPage = smsService.getAllSms(record);
		logger.info("getAllSms  分页加载所有消息    allSmsPage:" + JSON.toJSONString(allSmsPage));
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			@SuppressWarnings("rawtypes")
			Map map = new HashMap();
			map.put("allSmsPage", allSmsPage);
			//如果获取未读消息(传进来的islook为0)   返回0  跳到未读消息页,否则返回1 跳到全部消息页
			map.put("pageFlag", record.getIslook());
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			mv.setViewName("mydocs/mycount/allnews");
			mv.addObject("allSmsPage", allSmsPage);
			//如果获取未读消息(传进来的islook为0)   返回0  跳到未读消息页,否则返回1 跳到全部消息页
			mv.addObject("pageFlag", record.getIslook());			
			return mv;
		}
	}
	/**
	 *	批量标记消息为已读
	 *	condition
	 */
	@RequestMapping(value = "/makeSmsRead")
	@ResponseBody
	public String makeSmsRead(HttpServletRequest request, HttpServletResponse response, Sms record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setReceivedid(Integer.parseInt(userid));
				ret = smsService.makeReadSms(record);
				if (ret > 0) {
					// 更新用消息统计数 放入session
					userService.getSmsOper(request);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("更新失败");
				}
			}
			logger.info("**makeSmsRead*批量标记消息为已读****record==" + JSON.toJSONString(record));
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**getNewSms*弹窗显示未读消息列表*      失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 *	批量删除消息
	 *	condition
	 */
	@RequestMapping(value = "/delSms")
	@ResponseBody
	public String delSms(HttpServletRequest request, HttpServletResponse response, Sms record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setReceivedid(Integer.parseInt(userid));
				ret = smsService.delSms(record);
				if (ret > 0) {
					// 更新用消息统计数 放入session
					userService.getSmsOper(request);
					
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("更新失败");
				}
			}
			logger.info("**makeSmsRead*批量删除消息****record==" + JSON.toJSONString(record));
			// baseResponse.setObj(list);
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**getNewSms*批量删除消息*      失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
	
	
	/**
	 * 消息过滤设置  参数：消息类型 filteritem;过滤等级  filterlevel
	 */
	@RequestMapping(value = "/setSmsFilter")
	@ResponseBody
	public String setSmsFilter(HttpServletRequest request, SmsFilter record) throws Exception {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				record.setUserid(user.getUserid());
				int ret = smsFilterService.insertOrUpd(record);
				if (ret > 0) {
					logger.info("*setSmsFilter**--消息过滤设置**record==" + JSON.toJSONString(record));
					// baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*setSmsFilter** --消息过滤设置    失败  999   删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*setSmsFilter**--消息过滤设置  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*setSmsFilter** --消息过滤设置    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
	/**
	 * 跳到消息设置页
	 */
	@RequestMapping(value = "/getSmsFilterParm")
	@ResponseBody
	public ModelAndView getSmsFilterParm(HttpServletRequest request, SmsFilter record) throws Exception {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		record.setUserid(Integer.parseInt(userid));
		logger.info("getSmsFilterParm  跳到消息设置页    record:" + JSON.toJSONString(record));
		Map<Object, Object> smsFilterMap = smsFilterService.getSmsFilterParm(record);
		logger.info("getSmsFilterParm  跳到消息设置页    smsFilterMap:" + JSON.toJSONString(smsFilterMap));
		mv.setViewName("mydocs/mycount/infosettings");
		mv.addObject("smsFilterMap", smsFilterMap);
		return mv;
	}
	
	
	
}
