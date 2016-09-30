package com.jobplus.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.Courses;
import com.jobplus.pojo.CoursesLiked;
import com.jobplus.pojo.CoursesShare;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.OperationSum;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.ICoursesLikedService;
import com.jobplus.service.ICoursesService;
import com.jobplus.service.ICoursesShareService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.IUserService;
import com.jobplus.utils.ConstantManager;

/**
 * 
 * @author Jerry
 * 2016年7月19日下午3:44:46
 *
 */
@Controller
@RequestMapping("/courses")
public class CoursesController {
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(CoursesController.class);
	@Resource
	private ICoursesService coursesService;
	@Resource
	private  ICoursesShareService coursesShareService;
	@Resource
	private  ICoursesLikedService coursesLikedService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IUserService userService;
	
	
	
	/**
	 * 批量删除我分享的课程列表
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/delSharedCourses", method = RequestMethod.POST)
	@ResponseBody
	public String delSharedCourses(HttpServletRequest request, HttpServletResponse response, Courses record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				if (!StringUtils.isBlank(record.getCondition())) {// 批量删除  个人中心：我分享的课程列表
					record.setUserid(Integer.parseInt(userid));
					String conditions[] = record.getCondition().split(",");
					ret = coursesService.delSharedCourses(conditions);
				}
				if (ret > 0) {
					//个人操作数之类的信息放入session
					userService.getMyHeadTopAndOper(request);
					//获取统计数 
					OperationSum operationSum = userService.getOperationSum(request);
					baseResponse.setOperationSum(operationSum);
					logger.info("***delSharedCourses**个人中心--批量删除我分享的课程列表******");
					logger.info("*delSharedCourses**个人中心--批量删除我分享的课程列表**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*delSharedCourses** 个人中心--批量删除我分享的课程列表    失败  999   删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*delSharedCourses**个人中心--批量删除我分享的课程列表  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*delSharedCourses** 个人中心--批量删除我分享的课程列表    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
	/**
	 * 获取课程详情
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getCourseDetail")
	@ResponseBody
	public ModelAndView getCourseDetail(HttpServletRequest request, HttpServletResponse response, Courses record,@RequestParam(required=false) String isAdmin) {
		ModelAndView mv = new ModelAndView();
		if (record.getId() != null) {
			record = coursesService.getCourseDetail(record);
			if(null == record){
				mv.setViewName("404");
				return mv;
			}
//			logger.info("**getCourseDetail*获取课程详情****record==" + JSON.toJSONString(record));
			mv.addObject("record", record);
			if("7".equals(isAdmin)){
				//后台管理员查看
				mv.setViewName("manage/courseDetail");
			}else{
				mv.setViewName("mydocs/docs/courseDetail");
			}
		} else {
			logger.info("**getCourseDetail*获取课程详情  失败   record.getId() == null  999****");
		}
		return mv;
	}
	/**
	 * 加载更多课程评论 参数：课程ID、pageNo
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/loadComments", method = RequestMethod.POST)
	@ResponseBody
	public String loadCourseComments(HttpServletRequest request, HttpServletResponse response, CoursesShare record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			Page<CoursesShare> page = coursesShareService.getList(record);
			logger.info("**loadCourseComments*加载更多课程评论****page==" + JSON.toJSONString(page));
			baseResponse.setObj(page);
			baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
			baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			return JSON.toJSONString(baseResponse);

		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*loadCourseComments** --加载更多课程评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 新增课程评论
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	public String addComment(HttpServletRequest request, HttpServletResponse response, CoursesShare record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if (user!=null) {
					record.setUserid(user.getUserid());
					record = coursesShareService.insertAndReturnRecord(record,request.getContextPath(),user);
				if (record != null) {
					logger.info("*addComment**--新增课程评论**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*addComment** --新增课程评论    失败  999   删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*addComment**--新增课程评论  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*addComment** --新增课程评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 删除课程评论    同时需要传递所属课程的id
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/delComment", method = RequestMethod.POST)
	@ResponseBody
	public String delComment(HttpServletRequest request, HttpServletResponse response, CoursesShare record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setUserid(Integer.parseInt(userid));
				ret = coursesShareService.delComment(record);
				if (ret > 0) {
					logger.info("*delComment**--删除课程评论**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*delComment** --删除课程评论    失败  999   删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*delComment**--删除课程评论  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*delComment** --删除课程评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 课程点赞取消点赞     需要传递课程id
	 * @param request
	 * @param response
	 * @param courses
	 * @return
	 */
	@RequestMapping(value = "/clickLikeOnCourse", method = RequestMethod.POST)
	@ResponseBody
	public String clickLikeOnCourse(HttpServletRequest request, HttpServletResponse response, Courses courses) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				int ret = 0;

				CoursesLiked record = new CoursesLiked();
				record.setUserid(user.getUserid());
				record.setCoursesid(courses.getId());
				record.setRelationidPg(courses.getRelationidPg());
				record.setObjCreatepersonPg(courses.getObjCreatepersonPg());
				record.setObjectNamePg(courses.getObjectNamePg());
				if ("1".equals(request.getParameter("likeOperate"))) {// 已点赞
					// 开始取消点赞
					ret = coursesLikedService.deleteByPrimaryKey(record);
					logger.info(
							"**clickLikeOnCourse* 课程点赞取消点赞 *****取消操作取消操作取消操作  record==" + JSON.toJSONString(record));
				} else {// 没有点赞
					// 开始点赞
					ret = coursesLikedService.insert(record, request.getContextPath(), user);
					logger.info("**clickLikeOnCourse* 课程点赞取消点赞 *****点赞操作点赞操作点赞操作 record==" + JSON.toJSONString(record));
				}
				if (ret > 0) {
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("**clickLikeOnCourse* 课程点赞取消点赞  失败 999  ret=0 **");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("**clickLikeOnCourse* 课程点赞取消点赞  失败 999  登录失败**");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**clickLikeOnCourse* 课程点赞取消点赞  失败 999**" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
	/**
	 * 课程收藏、取消收藏
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/collectCourses", method = RequestMethod.POST)
	@ResponseBody
	public String collectCourses(HttpServletRequest request, HttpServletResponse response, MyCollect record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setUserid(Integer.parseInt(userid));
				record.setCollecttype(record.getCOLLECTTYPES()[3]);
				record = coursesService.collectCourse(record);
				if(record != null){
					//更新用户操作数统计    存入session
					userService.getMyHeadTopAndOper(request);
					logger.info(
							"*collectBook** 课程收藏、取消收藏       对象ID objectId(即BookId)  judgeTodo 1关注，0取消关注**MyCollect record=="
									+ JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);

				}else{
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("数据库更新失败");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*collectBook** 课程收藏、取消收藏    失败  999 登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*collectBook** 课程收藏、取消收藏    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
}
