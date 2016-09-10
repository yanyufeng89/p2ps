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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.EducationBgrd;
import com.jobplus.pojo.MyHomePage;
import com.jobplus.pojo.OperationSum;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.SmsFilter;
import com.jobplus.pojo.User;
import com.jobplus.pojo.VisitHistory;
import com.jobplus.pojo.WorkExper;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.IAttentionService;
import com.jobplus.service.IEducationBgrdService;
import com.jobplus.service.IMyHomePageService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ISmsFilterService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.IUserService;
import com.jobplus.service.IVisitHistoryService;
import com.jobplus.service.IWorkExperService;
import com.jobplus.utils.ConstantManager;

/**
 * 我的主页相关
 * @author Jerry
 * 2016年8月4日下午2:14:19
 *
 */
@Controller
@RequestMapping("/myHome")
public class MyHomeController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(MyHomeController.class);
	@Resource
	private ISmsService smsService;
	@Resource
	private ISmsFilterService smsFilterService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IVisitHistoryService visitHistoryService;
	@Resource
	private IWorkExperService workExperService;
	@Resource
	private IEducationBgrdService educationBgrdService;
	@Resource
	private IUserService userService;
	@Resource
	private IMyHomePageService myHomePageService;
	@Resource
	private IAttentionService attentionService;
	
	
	
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
		Page<Sms> allSmsPage = smsService.getAllSms(record);
		logger.info("getAllSms  分页加载所有消息    allSmsPage:" + JSON.toJSONString(allSmsPage));
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			@SuppressWarnings("rawtypes")
			Map map = new HashMap();
			map.put("allSmsPage", allSmsPage);
			//如果获取未读消息(传进来的islook为0)   返回0  跳到未读消息页,否则返回1 跳到全部消息页
			map.put("pageFlag", record.getIslook()== null?1:0);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			mv.setViewName("mydocs/mycount/allnews");
			mv.addObject("allSmsPage", allSmsPage);
			//如果获取未读消息(传进来的islook为0)   返回0  跳到未读消息页,否则返回1 跳到全部消息页
			mv.addObject("pageFlag", record.getIslook()== null?1:0);			
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
					//更新用消息统计数 放入session
					userService.getSmsOper(request);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("更新失败");
				}
			}
			logger.info("**makeSmsRead*批量标记消息为已读****record==" + JSON.toJSONString(record));
			// baseResponse.setObj(list);
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**getNewSms*弹窗显示未读消息列表*      失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 *	批量标删除消息
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
					//更新用消息统计数 放入session
					userService.getSmsOper(request);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("批量标删除消息失败");
				}
			}
			logger.info("**delSms*批量标删除消息****record==" + JSON.toJSONString(record));
			// baseResponse.setObj(list);
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**delSms*批量标删除消息*      失败   999****" + e.getMessage());
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
				if (ret>0) {
					logger.info("*setSmsFilter**--消息过滤设置**record==" + JSON.toJSONString(record));
//					baseResponse.setObj(record);
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
		mv.setViewName("mydocs/docs/myfans");
		mv.addObject("smsFilterMap", smsFilterMap);
		return mv;
	}
	/**
	 * 个人主页  && 浏览他人主页
	 * 传入的userid为被访问人的id
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getHomePage")
	@ResponseBody
	public ModelAndView getHomePage(HttpServletRequest request,@RequestParam(required=true) String userid,@RequestParam(required=false) String isReview) throws Exception  {
		
		ModelAndView mv = new ModelAndView();
		//当前登录人id
		String cutUserid = (String) request.getSession().getAttribute("userid");
		
		mv = myHomePageService.getHomePage(request,mv,userid, cutUserid, isReview);
		
		return mv;
	}	
	/**
	 * 分页加载更多最近分享      userid必传
	 * @param encoding
	 * @param request
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/moreRecentShare")
	@ResponseBody
	public ModelAndView moreRecentShare(@RequestHeader("Accept") String encoding,HttpServletRequest request,MyHomePage record) throws Exception {
		ModelAndView mv = new ModelAndView();
		
		Page<MyHomePage> recentShare = myHomePageService.getRecentShare(request,record);	
		logger.info("moreRecentVistors 分页加载更多最近分享    recentShare:" + JSON.toJSONString(recentShare));
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			Map<String, Page<MyHomePage>> map = new HashMap<String, Page<MyHomePage>>();
			map.put("recentShare", recentShare);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			mv.setViewName("mydocs/mycount/RecentShare");
			mv.addObject("recentShare", recentShare);
			return mv;
		}
	}	
	/**
	 * 加载更多最近访问的人   同时返回 用户信息     参数:userid
	 * @param encoding
	 * @param request
	 * @param vh
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/moreRecentVistors")
	@ResponseBody
	public ModelAndView moreRecentVistors(@RequestHeader("Accept") String encoding,HttpServletRequest request ,VisitHistory vh) throws Exception {
		ModelAndView mv = new ModelAndView();		
		if(vh.getUserid() == null){
			String userid = (String) request.getSession().getAttribute("userid");
			vh.setUserid(Integer.parseInt(userid));
		}
		//访问我的主页类型
		vh.setVisittype(vh.getVISITTYPES()[0]);
		//最近访问的人
		Page<VisitHistory> visitors = visitHistoryService.getRecentVistors(vh);
		logger.info("moreRecentVistors 加载更多最近访问的人    visitors:" + JSON.toJSONString(visitors));
		//被访问的人的个人信息
		User cutUser = userService.get(vh.getUserid());	
		mv.addObject("cutUser",cutUser);
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			Map<String, Page<VisitHistory>> map = new HashMap<String, Page<VisitHistory>>();
			map.put("visitors", visitors);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			mv.setViewName("mydocs/docs/mycurrentvisit");
			mv.addObject("visitors", visitors);
			return mv;
		}
		
	}	
	/**
	 *  获取我分享的某一类 tableName;tableColumn;userid;   
	 * @param encoding
	 * @param request
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getOneShares" , method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView getOneShares(@RequestHeader("Accept") String encoding,HttpServletRequest request,MyHomePage record) throws Exception {
		ModelAndView mv = new ModelAndView();
		if(null == record.getUserid()){
			//userId为null********userId为null******userId为null
			if (encoding.indexOf("application/json") != -1) {
				logger.info("getOneShares  获取我分享的某一类    recentShare: userId为null********userId为null******userId为null" );
				return new ModelAndView(new MappingJackson2JsonView(), null);
			} else {
				mv.setViewName("mydocs/mycount/RecentShare");
				mv.addObject("shares", null);
				return mv;
			} 			
		}
		Page<MyHomePage> shares = myHomePageService.getOneShares(request,record);	
		logger.info("getOneShares  获取我分享的某一类    recentShare:" + JSON.toJSONString(shares));
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			Map<String, Page<MyHomePage>> map = new HashMap<String, Page<MyHomePage>>();
			map.put("shares", shares);
			logger.info("getOneShares  获取我分享的某一类    recentShare:" + JSON.toJSONString(map));
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			mv.setViewName("mydocs/mycount/RecentShare");
			mv.addObject("shares", shares);
			return mv;
		}
	}	
	
	/**
	 * 个人主页 修改个人信息
	 */
	@RequestMapping(value = "/updUserInfo")
	@ResponseBody  
	public String updUserInfo(HttpServletRequest request, HttpServletResponse response, User user) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				user.setUserid(Integer.parseInt(userid));
				
				if(user.getHeadIconFile()!=null){
					ret = userService.updUserInfoIncHeadIcon(user.getHeadIconFile(), user, request, response);
				}else{
					ret = userService.updateByPrimaryKeySelective(user);
				}	
				if (ret > 0) {
					//获取最新用户信息  放入session
					User ltUser = userService.get(Integer.parseInt(userid));
					request.getSession().setAttribute("user", ltUser);
					
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("更新失败");
				}
			}
			logger.info("**updUserInfo*个人主页 修改个人信息**ret=" + ret + "**record==" + JSON.toJSONString(user));
			// baseResponse.setObj(list);
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**updUserInfo*个人主页 修改个人信息*      失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
/*	*//**
	 * 修改用户头像
	 * @param headIconFile
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 *//*
	@RequestMapping(value = "/updUserHeadIcon")
	@ResponseBody	
	public ModelAndView updUserHeadIcon(@RequestParam(value="headIconFile",required=false)MultipartFile headIconFile,HttpServletRequest request, 
			HttpServletResponse response, User user) {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		if (!StringUtils.isBlank(userid)) {
			user.setUserid(Integer.parseInt(userid));
			if(headIconFile!=null){
				 userService.updUserInfoIncHeadIcon(headIconFile, user, request, response);
			}else{
				
			}	
		}
		logger.info("**updUserHeadIcon*修改用户头像***record==" + JSON.toJSONString(user));
//		mv.setViewName("mydocs/mycenter/mycenter");
//		mv.addObject("smsFilterMap", smsFilterMap);
		try {
			mv = this.getHomePage(request, userid);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mv;
		
	}*/
	/**
	 * 个人主页 修改教育背景
	 */
	@RequestMapping(value = "/updEduInfo")
	@ResponseBody
	public String updEduInfo(HttpServletRequest request, HttpServletResponse response, EducationBgrd record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setUserid(Integer.parseInt(userid));
				ret = educationBgrdService.insertOrUpdate(record);
				if (ret > 0) {
					//传递的id值
					baseResponse.setObj(ret);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("更新失败");
				}
			}
			logger.info("**updEduInfo*个人主页 修改教育背景**ret="+ret+"**record==" + JSON.toJSONString(record));
			// baseResponse.setObj(list);
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**updEduInfo*个人主页 修改教育背景*      失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 个人主页 修改工作经历
	 */
	@RequestMapping(value = "/updWorkExpInfo")
	@ResponseBody
	public String updWorkExpInfo(HttpServletRequest request, HttpServletResponse response, WorkExper record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setUserid(Integer.parseInt(userid));
				ret = workExperService.insertOrUpdate(record);
				if (ret > 0) {
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("更新失败");
				}
			}
			logger.info("**updWorkExpInfo*个人主页 修改工作经历**ret="+ret+"**record==" + JSON.toJSONString(record));
			// baseResponse.setObj(list);
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**updWorkExpInfo*个人主页 修改工作经历*      失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}	
	
	
	
	
	/**
	 * 内部方法 获取用户操作统计
	 */
	public OperationSum getOperationSum(HttpServletRequest request) {
		String userid = (String) request.getSession().getAttribute("userid");
		if (!StringUtils.isBlank(userid)) {
			OperationSum operationSum = new OperationSum();
			operationSum = operationSumService.selectByPrimaryKey(Integer.parseInt(userid));
			logger.info("**getOperationSum**获取用户操作统计*****operationSum=="+JSON.toJSONString(operationSum));
			return operationSum;
		}
		return null;
	}
}
