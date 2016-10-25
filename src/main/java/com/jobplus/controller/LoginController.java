package com.jobplus.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jobplus.utils.Common;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jobplus.pojo.User;
import com.jobplus.service.ISysLoginLogService;
import com.jobplus.service.IUserService;
import com.jobplus.service.impl.RedisServiceImpl;
import com.jobplus.utils.SHAUtil;

import java.io.UnsupportedEncodingException;

@Controller
public class LoginController {
	/**
	 * 实际的登录代码 如果登录成功，跳转至首页；登录失败，则将失败信息反馈对用户
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@Resource
	private IUserService userService;

	@Resource
	private RedisServiceImpl redisService;

	@Resource
	private ISysLoginLogService sysLoginLogService;

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false, defaultValue = "") String backurl) {
		ModelAndView mv = new ModelAndView();
        mv.addObject("backurl", backurl.indexOf("/fore/retakepwd") > -1 ? "/index" : backurl);
        // 返回视图名设置
		mv.setViewName("login");
		return mv;
	}


	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response, User user, @RequestParam(required = false, defaultValue = "") String backurl) throws UnsupportedEncodingException {
		// 注册成功将用户信息存入session
		//SavedRequest savedRequest = WebUtils.getSavedRequest(request);
		//savedRequest.getRequestUrl();
		ModelAndView mv = new ModelAndView();
		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			mv.setViewName(StringUtils.isNotBlank(backurl) ? "redirect:"+backurl : "redirect:/");
			return mv;
		}
		try {
			String username = user.getUsername();
			String password = SHAUtil.shaEncode(user.getPasswd());
			user = userService.findUserByName(username);
			if (null != user && password.equals(user.getPasswd())&&user.getIsvalid()!=0) {
				username = String.valueOf(user.getUserid());
			} else {
				// 返回前端数据设置
				mv.addObject("backurl", backurl);
				// 返回视图名设置
				mv.setViewName("login");
				return mv;
			}
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			token.setRememberMe(true);
			// 获取当前的Subject
			// 使用权限工具进行用户登录，登录成功后跳到shiro配置的successUrl中，与下面的return没什么关系！
			currentUser.login(token);
			if (currentUser.isAuthenticated()) {
				currentUser.getSession().setAttribute("userid", username);
				currentUser.getSession().setAttribute("user", user);
				//记录登录日志
				sysLoginLogService.insert(request, user);
				logger.info("用户[" + username + "]登录认证通过");
				//个人操作数之类的信息放入session
				userService.getMyHeadTopAndOper(request);
				//更新用消息统计数 放入session
				userService.getSmsOper(request);
			} else {
				token.clear();
			}
			if (StringUtils.isNotBlank(backurl)) {
				mv.setViewName("redirect:" + Common.encoderUrl(backurl));
			} else {
				// 返回前端数据设置
				mv.addObject("user", user);
				// 返回视图名设置
				mv.setViewName("redirect:/");
			}
			return mv;
		} catch (AuthenticationException e) {
			// 返回前端数据设置
			mv.addObject("backurl", backurl);
			// 返回视图名设置
			mv.setViewName("login");
			return mv;
		}
	}

	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false, defaultValue = "") String backurl) {
		// 使用权限管理工具进行用户的退出，跳出登录，给出提示信息
		SecurityUtils.getSubject().logout();
		String seesionId = request.getSession().getId();
		redisService.del(seesionId);
		ModelAndView mv = new ModelAndView();
		// 返回视图名设置
		mv.setViewName(StringUtils.isNotBlank(backurl) ? "redirect:"+backurl : "redirect:/");
		logger.info("用户[" + SecurityUtils.getSubject().getPrincipal() + "]退出登录!");
		return mv;
	}
	/**
	 * 刷新个人操作数
	 * @param request
	 *//*
	@RequestMapping(value = "/getMyHeadTopAndOper")
	@ResponseBody
	public void getMyHeadTopAndOper(HttpServletRequest request){
		String userid = (String) request.getSession().getAttribute("userid");
//		//我关注的人总数
//		int attenManSum = attentionService.getAttenManSum(Integer.parseInt(userid));
//		//我的粉丝总数
//		int fansSum = attentionService.getFansSum(Integer.parseInt(userid));
		//未读消息总数(除私信)
//		int newSmsSum = smsService.getNewSmsSum(Integer.parseInt(userid));
//		//未读私信总数
//		int privateSmsSum = smsService.getPrivateSmsSum(Integer.parseInt(userid));
//		//我的消息总数 (未读、已读  消息&&私信)
//		int smsSum = smsService.geSmsSum(Integer.parseInt(userid));
		//我关注的人 ids 和我的粉丝  ids
		String attenMan = attentionService.getAttenMan(Integer.parseInt(userid));
		String fans = attentionService.getFans(Integer.parseInt(userid));

		// 获取统计数
		OperationSum operationSum = getOperationSum(request);
		@SuppressWarnings("unchecked")
		Map<Object, Object> myHeadTop = (Map<Object, Object>)request.getSession().getAttribute("myHeadTop");
		myHeadTop.put("attenManSum", operationSum.getAttentionsum());//		myHeadTop.put("attenManSum", attenManSum);
		myHeadTop.put("fansSum", operationSum.getFanssum());//		myHeadTop.put("fansSum", fansSum);
//		myHeadTop.put("newSmsSum", newSmsSum);
//		myHeadTop.put("privateSmsSum", privateSmsSum);
//		myHeadTop.put("smsSum", smsSum);
		myHeadTop.put("attenMan", attenMan);
		myHeadTop.put("fans", fans);
		//存入session  
		request.getSession().setAttribute("operationSum", operationSum);
		request.getSession().setAttribute("myHeadTop", myHeadTop);
		
		Account account = accountService.getAccountByUserId(Integer.parseInt(userid));
		request.getSession().setAttribute("account", account);
//		logger.info("**个人账户信息放入session account=="+JSON.toJSONString(account));
	}
	public void getSmsOper(HttpServletRequest request){
		String userid = (String) request.getSession().getAttribute("userid");
		//未读消息总数(除私信)
		int newSmsSum = smsService.getNewSmsSum(Integer.parseInt(userid));
		//未读私信总数
		int privateSmsSum = smsService.getPrivateSmsSum(Integer.parseInt(userid));
		//我的消息总数 (未读、已读  消息&&私信)
		int smsSum = smsService.geSmsSum(Integer.parseInt(userid));
		
		@SuppressWarnings("unchecked")
		Map<Object, Object> myHeadTop = (Map<Object, Object>)request.getSession().getAttribute("myHeadTop");
		myHeadTop.put("newSmsSum", newSmsSum);
		myHeadTop.put("privateSmsSum", privateSmsSum);
		myHeadTop.put("smsSum", smsSum);
		request.getSession().setAttribute("myHeadTop", myHeadTop);
	}
	
	*//**
	 * 内部方法 获取用户操作统计
	 *//*
	public OperationSum getOperationSum(HttpServletRequest request) {
		String userid = (String) request.getSession().getAttribute("userid");
		if (!StringUtils.isBlank(userid)) {
			OperationSum operationSum = new OperationSum();
			operationSum = operationSumService.selectByPrimaryKey(Integer.parseInt(userid));
//			logger.info("**getOperationSum**获取用户操作统计*****operationSum=="+JSON.toJSONString(operationSum));
			return operationSum;
		}
		return null;
	}
*/
	/**
	 * 找回密码
	 * @return
	 */
	@RequestMapping(value = "/index/fore/retakepwd")
	public ModelAndView retakepwd() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("passwordRetake");
		return mv;
	}
}
