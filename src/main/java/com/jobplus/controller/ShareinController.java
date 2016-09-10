package com.jobplus.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jobplus.pojo.User;
import com.jobplus.service.IUserService;

@Controller
@RequestMapping("/sharein")
public class ShareinController {

	@Resource
	private IUserService userService;
	
	@RequestMapping(value = "/searchuploadFile")
	public ModelAndView searchuploadFile(HttpServletRequest request, @RequestParam(required = false,defaultValue = "0") int type) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.addObject("type", type);
		mv.setViewName("sharein/searchuploadFile");
		return mv;
	}
	
	@RequestMapping(value = "/shareDocument")
	public ModelAndView shareDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/share/shareDocument");
		return mv;
	}
	
	@RequestMapping(value = "/shareTopic")
	public ModelAndView shareTopic(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/share/shareTopic");
		return mv;
	}
	
	@RequestMapping(value = "/shareBook")
	public ModelAndView shareBook(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/share/shareBook");
		return mv;
	}
	
	@RequestMapping(value = "/shareCourse")
	public ModelAndView shareCourse(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/share/shareCourse");
		return mv;
	}
	
	@RequestMapping(value = "/shareArticle")
	public ModelAndView shareArticle(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/share/shareArticle");
		return mv;
	}
	
	@RequestMapping(value = "/shareSite")
	public ModelAndView shareSite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/share/shareSite");
		return mv;
	}
	
	@RequestMapping(value = "/uploadArticle")
	public ModelAndView uploadArticle(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/upload/uploadArticle");
		return mv;
	}
	
	@RequestMapping(value = "/uploadBook")
	public ModelAndView uploadBook(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/upload/uploadBook");
		return mv;
	}
	@RequestMapping(value = "/uploadCourse")
	public ModelAndView uploadCourse(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/upload/uploadCourse");
		return mv;
	}
	@RequestMapping(value = "/uploadDocument")
	public ModelAndView uploadDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/upload/uploadDocument");
		return mv;
	}
	@RequestMapping(value = "/uploadSite")
	public ModelAndView uploadSite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/upload/uploadSite");
		return mv;
	}
	
	@RequestMapping(value = "/uploadTopic")
	public ModelAndView uploadTopic(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/upload/uploadTopic");
		return mv;
	}

/*	@RequestMapping(value = "/successUploadArticle")
	public ModelAndView successUploadArticle(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/success/successUploadArticle");
		return mv;
	}
	
	@RequestMapping(value = "/successUploadBook")
	public ModelAndView successUploadBook(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/success/successUploadBook");
		return mv;
	}
	
	@RequestMapping(value = "/successUploadCourse")
	public ModelAndView successUploadCourse(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/success/successUploadCourse");
		return mv;
	}*/
	
	/*@RequestMapping(value = "/successUploadDocument")
	public ModelAndView successUploadDocument(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/success/successUploadDocument");
		return mv;
	}
	
	@RequestMapping(value = "/successUploadSite")
	public ModelAndView successUploadSite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/success/successUploadSite");
		return mv;
	}
	
	@RequestMapping(value = "/successUploadTopic")
	public ModelAndView successUploadTopic(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		int userid = Integer.parseInt((String)request.getSession().getAttribute("userid"));
		User user = userService.get(userid);
		mv.addObject("user", user);
		mv.setViewName("sharein/success/successUploadTopic");
		return mv;
	}*/
	
	
}
