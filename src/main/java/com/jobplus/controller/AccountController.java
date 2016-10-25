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
import com.jobplus.pojo.Account;
import com.jobplus.pojo.AccountDetail;
import com.jobplus.pojo.Page;
import com.jobplus.service.IAccountDetailService;
import com.jobplus.service.IAccountService;
import com.jobplus.service.IUserService;

@Controller
@RequestMapping("/account")
public class AccountController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(AccountController.class);
	@Resource
	private IAccountService accountService;
	@Resource
	private IAccountDetailService accountDetailService;
	@Resource
	private IUserService userService;
	
	
	/**
	 * 获取账户财富收支详情    参数：收入还是支出 changetype   + -
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getDetailListByRecord")
	@ResponseBody
	public ModelAndView getDetailListByRecord(@RequestHeader("Accept") String encoding, HttpServletRequest request,
			HttpServletResponse response, AccountDetail record) {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		if(StringUtils.isBlank(userid)){
			mv.setViewName("redirect:/login");
			return mv;
		}
		Account act = new Account();
		act.setUserid(Integer.parseInt(userid));
		act = accountService.getAccountByUserId(Integer.parseInt(userid));
		//个人操作数之类的信息放入session
		userService.getMyHeadTopAndOper(request);
		if (act.getId() != null) {
			// accountId 不为空
			record.setAccountid(act.getId());
			Page<AccountDetail> actDPage = accountDetailService.getDetailListByRecord(record);
			logger.info(
					"**getDetailListByRecord*获取账户财富收支详情***Page<AccountDetail>actDPage==" + JSON.toJSONString(actDPage));
			if (encoding.indexOf("application/json") != -1) {
				// 分页 post请求
				Map<String, Page<AccountDetail>> map = new HashMap<String, Page<AccountDetail>>();
				map.put("actDPage", actDPage);
				return new ModelAndView(new MappingJackson2JsonView(), map);
			} else {
				// 首次加载页面 get请求
				mv.addObject("actDPage", actDPage);
				if(record.getChangetype().equals(record.getCHANGETYPES()[1])){
					//支出页面
					mv.setViewName("mydocs/myfortune/fortuneexpend");					
				}else{
					//收入页面
					mv.setViewName("mydocs/myfortune/fortuneincome");
				}
				return mv;
			}
		} else {
			// accountId 为空
			logger.info("**getDetailListByRecord*获取账户财富收支详情  失败   act.getId() == null  999****");
			mv.setViewName("mydocs/myfortune/fortuneexpend");
			return mv;
		}
	}
	/**
	 * 加载更多课程评论 参数：课程ID、pageNo
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
//	@RequestMapping(value = "/loadCourseComments", method = RequestMethod.POST)
//	@ResponseBody
//	public String loadCourseComments(HttpServletRequest request, HttpServletResponse response, CoursesShare record) {
//		BaseResponse baseResponse = new BaseResponse();
//		try {
//			String userid = (String) request.getSession().getAttribute("userid");
//			if (record.getCoursesid() != null && !StringUtils.isBlank(userid)) {
//				record.setCurrentUserId(Integer.parseInt(userid));
//				Page<CoursesShare> page = coursesShareService.getList(record);
//				logger.info("**loadCourseComments*加载更多课程评论****page==" + JSON.toJSONString(page));
//				baseResponse.setObj(page);
//				baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
//				baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
//			} else {
//				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
//				logger.info("**loadCourseComments*加载更多课程评论    失败   record.getCoursesid() == null  999****");
//			}
//			return JSON.toJSONString(baseResponse);
//		
//		} catch (Exception e) {
//			baseResponse.setReturnMsg(e.getMessage());
//			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
//			logger.info("*loadCourseComments** --加载更多课程评论    失败  999 *********" + e.getMessage());
//			return JSON.toJSONString(baseResponse);
//		}
//	}
}
