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
import com.jobplus.pojo.Sites;
import com.jobplus.pojo.SitesLiked;
import com.jobplus.pojo.User;
import com.jobplus.pojo.SiteShare;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.OperationSum;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.ISitesLikedService;
import com.jobplus.service.ISitesService;
import com.jobplus.service.IUserService;
import com.jobplus.service.ISiteShareService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.utils.ConstantManager;

/**
 * 
 * @author Jerry
 * 2016年7月19日下午3:44:46
 *
 */
@Controller
@RequestMapping("/sites")
public class SitesController {
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(SitesController.class);
	@Resource
	private ISitesService sitesService;
	@Resource
	private  ISiteShareService sitesShareService;
	@Resource
	private  ISitesLikedService sitesLikedService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IUserService userService;
	
	
	
	/**
	 * 批量删除我分享的站点列表
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/delSharedSites", method = RequestMethod.POST)
	@ResponseBody
	public String delSharedSites(HttpServletRequest request, HttpServletResponse response, Sites record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				if (!StringUtils.isBlank(record.getCondition())) {// 批量删除  个人中心：我分享的站点列表
					record.setUserid(Integer.parseInt(userid));
					String conditions[] = record.getCondition().split(",");
					ret = sitesService.delSharedSites(conditions);
				}
				if (ret > 0) {
					//个人操作数之类的信息放入session
					userService.getMyHeadTopAndOper(request);
					//获取统计数  
					OperationSum operationSum = userService.getOperationSum(request);
					baseResponse.setOperationSum(operationSum);
					logger.info("***delSharedSites**个人中心--批量删除我分享的站点列表******");
					logger.info("*delSharedSites**个人中心--批量删除我分享的站点列表**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*delSharedSites** 个人中心--批量删除我分享的站点列表    失败  999   删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*delSharedSites**个人中心--批量删除我分享的站点列表  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*delSharedSites** 个人中心--批量删除我分享的站点列表    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
	/**
	 * 获取站点详情
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getSiteDetail")
	@ResponseBody
	public ModelAndView getSiteDetail(HttpServletRequest request, HttpServletResponse response, Sites record,@RequestParam(required=false) String isAdmin) {
		ModelAndView mv = new ModelAndView();
		if (record.getId() != null) {
			record = sitesService.getSiteDetail(record);
			if(null == record){
				mv.setViewName("404");
				return mv;
			}
//			logger.info("**getSiteDetail*获取站点详情****record==" + JSON.toJSONString(record));
			mv.addObject("record", record);
			if("7".equals(isAdmin)){
				//后台管理员查看
				mv.setViewName("manage/siteDetail");
			}else{
				mv.setViewName("mydocs/docs/siteDetail");
			}
		} else {
			logger.info("**getSiteDetail*获取站点详情  失败   record.getId() == null  999****");
		}
		return mv;
	}
	/**
	 * 加载更多站点评论 参数：站点ID、pageNo
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/loadComments", method = RequestMethod.POST)
	@ResponseBody
	public String loadSiteComments(HttpServletRequest request, HttpServletResponse response, SiteShare record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			Page<SiteShare> page = sitesShareService.getList(record);
			logger.info("**loadSiteComments*加载更多站点评论****page==" + JSON.toJSONString(page));
			baseResponse.setObj(page);
			baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
			baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			return JSON.toJSONString(baseResponse);

		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*loadSiteComments** --加载更多站点评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 新增站点评论          
	 * 消息参数： siteTitle  siteUrl
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	public String addComment(HttpServletRequest request, HttpServletResponse response, SiteShare record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				record.setUserid(user.getUserid());
				record = sitesShareService.insertAndReturnRecord(record, request.getContextPath(), user);
				if (record != null) {
					logger.info("*addComment**--新增站点评论**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*addComment** --新增站点评论    失败  999   删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*addComment**--新增站点评论  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*addComment** --新增站点评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 删除站点评论    同时需要传递所属站点的id
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/delComment", method = RequestMethod.POST)
	@ResponseBody
	public String delComment(HttpServletRequest request, HttpServletResponse response, SiteShare record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setUserid(Integer.parseInt(userid));
				ret = sitesShareService.delComment(record);
				if (ret > 0) {
					logger.info("*delComment**--删除站点评论**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*delComment** --删除站点评论    失败  999   删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*delComment**--删除站点评论  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*delComment** --删除站点评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 站点点赞取消点赞     需要传递站点id
	 * @param request
	 * @param response
	 * @param sites
	 * @return
	 */
	@RequestMapping(value = "/clickLikeOnSite", method = RequestMethod.POST)
	@ResponseBody
	public String clickLikeOnSite(HttpServletRequest request, HttpServletResponse response, Sites sites) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				int ret = 0;

				SitesLiked record = new SitesLiked();
				record.setUserid(user.getUserid());
				record.setCommid(sites.getId());
				record.setRelationidPg(sites.getRelationidPg());
				record.setObjCreatepersonPg(sites.getObjCreatepersonPg());
				record.setObjectNamePg(sites.getObjectNamePg());
				if ("1".equals(request.getParameter("likeOperate"))) {// 已点赞
					// 开始取消点赞
					ret = sitesLikedService.deleteByPrimaryKey(record);
					logger.info("**clickLikeOnSite* 站点点赞取消点赞 *****取消操作取消操作取消操作  record==" + JSON.toJSONString(record));
				} else {// 没有点赞
					// 开始点赞
					ret = sitesLikedService.insert(record, request.getContextPath(), user);
					logger.info("**clickLikeOnSite* 站点点赞取消点赞 *****点赞操作点赞操作点赞操作 record==" + JSON.toJSONString(record));
				}
				if (ret > 0) {
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("**clickLikeOnSite* 站点点赞取消点赞  失败 999  ret=0 **");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("**clickLikeOnSite* 站点点赞取消点赞  失败 999  登录失败**");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**clickLikeOnSite* 站点点赞取消点赞  失败 999**" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
	/**
	 * 站点收藏、取消收藏
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/collectSites", method = RequestMethod.POST)
	@ResponseBody
	public String collectSites(HttpServletRequest request, HttpServletResponse response, MyCollect record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setUserid(Integer.parseInt(userid));
				//COLLECTTYPES = { "tbl_docs", "tbl_topics", "tbl_books", "tbl_courses", "tbl_article", "tbl_sites" };
				record.setCollecttype(record.getCOLLECTTYPES()[5]);
				record = sitesService.collectSite(record);
				if(record != null) {
					//更新用户操作数统计    存入session
					userService.getMyHeadTopAndOper(request);
					logger.info(
							"*collectSites** 站点收藏、取消收藏       对象ID objectId(即SiteId)  judgeTodo 1关注，0取消关注**MyCollect record=="
									+ JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				}else{
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("更新数据库失败");
				}
				
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*collectSites** 站点收藏、取消收藏    失败  999 登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*collectSites** 站点收藏、取消收藏    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
}
