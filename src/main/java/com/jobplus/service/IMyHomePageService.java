package com.jobplus.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.jobplus.pojo.MyHomePage;
import com.jobplus.pojo.Page;

public interface IMyHomePageService {

	/**
	 * 获取最近分享的6类
	 * @param record
	 * @return
	 */
	public Page<MyHomePage> getRecentShare(HttpServletRequest request,MyHomePage record);
	
	/**
	 * 获取我分享的某一类 tableName;tableColumn;userid;
	 * @param request
	 * @param record
	 * @return
	 */
	public Page<MyHomePage> getOneShares(HttpServletRequest request,MyHomePage record);
	
	
	/**
	 *  个人主页  && 浏览他人主页
	 * @param mv
	 * @param cutUserid
	 * @return
	 */
	public ModelAndView getHomePage(HttpServletRequest request,ModelAndView mv, String userid,String cutUserid,String isReview);
}
