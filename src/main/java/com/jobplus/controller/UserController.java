package com.jobplus.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jobplus.pojo.User;
import com.jobplus.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@SuppressWarnings("unused")
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);
	@Resource
	private IUserService userService;

	@SuppressWarnings("rawtypes")
	@Resource
	private RedisTemplate redisTemplate;

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response, User user) {
		user = userService.post(user);
		// 注册成功将用户信息存入session
		ModelAndView mv = new ModelAndView();
		// 返回前端数据设置
		// 返回视图名设置
		mv.setViewName("redirect:/");
		
		// 个人操作数之类的信息放入session
		userService.getMyHeadTopAndOper(request);
		// 更新用消息统计数 放入session
		userService.getSmsOper(request);
		
		return mv;
	}

	/**
	 * 根据用户id获取爱用户信息
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */

	@RequestMapping("/{id}")
	public ModelAndView queryUser(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
		ModelAndView mv = new ModelAndView();
		User user = userService.get(id);
		mv.setViewName("showUser");
		mv.addObject("user", user);
		return mv;
	}

	/**
	 * 验证 手机号/邮箱验证是否已存在
	 * 
	 * @param checkName
	 * @param checkValue
	 * @return
	 */
	@RequestMapping("/check/{id}/{type}")
	@ResponseBody
	public String checkAccount(@PathVariable("type") String checkName, @PathVariable("id") String checkValue) {
		return String.valueOf(userService.checkAccount(checkName, checkValue));
	}

	/**
	 * 更新用户信息
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public ModelAndView updateUser(HttpServletRequest request, HttpServletResponse response, User user) {
		ModelAndView mv = new ModelAndView();
		// 更新用户信息
		user = userService.put(user, request);
		mv.addObject("user", user);
		mv.setViewName("showUser");
		return mv;
	}

	/*
	 * @RequestMapping(value = "/login") public ModelAndView
	 * login(HttpServletRequest request, HttpServletResponse response, User
	 * user) {; // 注册成功将用户信息存入session ModelAndView mv = new ModelAndView(); //
	 * 返回前端数据设置 mv.addObject("user", user); // 返回视图名设置 mv.setViewName("index");
	 * return mv; }
	 */

	@RequestMapping(value = "/allorder")
	public ModelAndView allorder(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("mydocs/myorders/allorders");
		return mv;
	}

	@RequestMapping(value = "/cash")
	public ModelAndView cash(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("mydocs/myorders/cash");
		return mv;
	}

	@RequestMapping(value = "/nocash")
	public ModelAndView nocash(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("mydocs/myorders/nocash");
		return mv;
	}
	@RequestMapping(value = "/mydoc")
	public ModelAndView mydoc(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("mydocs/mydoc");
		return mv;
	}

	@RequestMapping(value = "/myshare/{id}")
	public ModelAndView myshare(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("mydocs/docs/myshare");
		return mv;
	}
	
	@RequestMapping(value = "/aboutme/{id}")
	public ModelAndView aboutme(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("mydocs/mycenter/aboutme");
		return mv;
	}

	/**
	 * 更新密码
	 *
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/changePassword")
	@ResponseBody
	public String changePassword(HttpServletRequest request, User user) {
		int state = userService.changePassword(user);
		//如果已经登录则退出
		if (request.getSession().getAttribute("userid") != null) {
			SecurityUtils.getSubject().logout();
			String sessionId = request.getSession().getId();
			redisTemplate.delete(sessionId);
		}
		return String.valueOf(state);
	}

	/**
	 * 更新
	 *
	 * @param user
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public String updateUser(HttpServletRequest request, User user) {
		user.setUserid(Integer.parseInt((String) request.getSession().getAttribute("userid")));
		int state = userService.updateByPrimaryKeySelective(user);
		//如果成功则更新session
		if (state == 1)
			SecurityUtils.getSubject().getSession().setAttribute("user", userService.get(Integer.parseInt((String) request.getSession().getAttribute("userid"))));
		return String.valueOf(state);
	}
}
