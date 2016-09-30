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
import com.jobplus.pojo.Article;
import com.jobplus.pojo.ArticleLiked;
import com.jobplus.pojo.ArticleShare;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.OperationSum;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.IArticleLikedService;
import com.jobplus.service.IArticleService;
import com.jobplus.service.IArticleShareService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.IUserService;
import com.jobplus.utils.ConstantManager;


/**
 * @author Jerry
 * 2016年7月26日上午10:36:38
 *
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
	
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ArticleController.class);
	@Resource
	private IArticleService articleService;
	@Resource
	private  IArticleShareService articleShareService;
	@Resource
	private  IArticleLikedService articleLikedService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IUserService userService;
	
	
	
	/**
	 * 批量删除我分享的文章列表
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/delSharedArticle", method = RequestMethod.POST)
	@ResponseBody
	public String delSharedArticle(HttpServletRequest request, HttpServletResponse response, Article record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				if (!StringUtils.isBlank(record.getCondition())) {// 批量删除  个人中心：我分享的文章列表
					record.setUserid(Integer.parseInt(userid));
					String conditions[] = record.getCondition().split(",");
					ret = articleService.delSharedArticle(conditions);
				}
				if (ret > 0) {
					// 获取统计数
					OperationSum operationSum = userService.getOperationSum(request);
					baseResponse.setOperationSum(operationSum);
					
					//个人操作数之类的信息放入session
					userService.getMyHeadTopAndOper(request);
					
					logger.info("***delSharedArticle**个人中心--批量删除我分享的文章列表******");
					logger.info("*delSharedArticle**个人中心--批量删除我分享的文章列表**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*delSharedArticle** 个人中心--批量删除我分享的文章列表    失败  999   删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*delSharedArticle**个人中心--批量删除我分享的文章列表  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*delSharedArticle** 个人中心--批量删除我分享的文章列表    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
	/**
	 * 获取文章详情
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getArticleDetail")
	@ResponseBody
	public ModelAndView getArticleDetail(HttpServletRequest request, HttpServletResponse response,Article record,@RequestParam(required=false) String isAdmin) {
		ModelAndView mv = new ModelAndView();
		if (record.getId() != null) {
			record = articleService.getArticleDetail(record);
			if(null == record){
				mv.setViewName("404");
				return mv;
			}
//			logger.info("**getArticleDetail*获取文章详情****record==" + JSON.toJSONString(record));
			mv.addObject("record",record);
			if("7".equals(isAdmin)){
				//后台管理员查看
				mv.setViewName("manage/articleDetail");
			}else{
				mv.setViewName("mydocs/docs/articleDetail");
			}
		} else {
			logger.info("**getArticleDetail*获取文章详情  失败   record.getId() == null  999****");
		}
		return mv;
	}
	/**
	 * 加载更多文章评论 参数：文章ID、pageNo
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/loadComments", method = RequestMethod.POST)
	@ResponseBody
	public String loadArticleComments(HttpServletRequest request, HttpServletResponse response, ArticleShare record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
				Page<ArticleShare> page = articleShareService.getList(record);
				logger.info("**loadArticleComments*加载更多文章评论****page==" + JSON.toJSONString(page));
				baseResponse.setObj(page);
				baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
				baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			return JSON.toJSONString(baseResponse);
		
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*loadArticleComments** --加载更多文章评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 新增文章评论
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	public String addComment(HttpServletRequest request, HttpServletResponse response, ArticleShare record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if (user!=null) {
					record.setUserid(user.getUserid());
					record = articleShareService.insertAndReturnRecord(record,request.getContextPath(),user);
				if (record != null) {
					logger.info("*addComment**--新增文章评论**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*addComment** --新增文章评论    失败  999   删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*addComment**--新增文章评论  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*addComment** --新增文章评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 删除文章评论    同时需要传递所属文章的id
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/delComment", method = RequestMethod.POST)
	@ResponseBody
	public String delComment(HttpServletRequest request, HttpServletResponse response, ArticleShare record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setUserid(Integer.parseInt(userid));
				ret = articleShareService.delComment(record);
				if (ret > 0) {
					logger.info("*delComment**--删除文章评论**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*delComment** --删除文章评论    失败  999   删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*delComment**--删除文章评论  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*delComment** --删除文章评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 文章点赞取消点赞     需要传递文章id
	 * @param request
	 * @param response
	 * @param article
	 * @return
	 */
	@RequestMapping(value = "/clickLikeOnArticle", method = RequestMethod.POST)
	@ResponseBody
	public String clickLikeOnArticle(HttpServletRequest request, HttpServletResponse response, Article article) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if (user!=null) {
				int ret = 0;

				ArticleLiked record = new ArticleLiked();
				record.setUserid(user.getUserid());
				record.setArticleid(article.getId());
				record.setRelationidPg(article.getRelationidPg());
				record.setObjCreatepersonPg(article.getObjCreatepersonPg());
				record.setObjectNamePg(article.getObjectNamePg());
				if ("1".equals(request.getParameter("likeOperate"))){// 已点赞
					//开始取消点赞
					ret = articleLikedService.deleteByPrimaryKey(record);
					logger.info("**clickLikeOnArticle* 文章点赞取消点赞 *****取消操作取消操作取消操作  record==" + JSON.toJSONString(record));
				} else {// 没有点赞
					//开始点赞
					ret = articleLikedService.insert(record,request.getContextPath(),user);
					logger.info("**clickLikeOnArticle* 文章点赞取消点赞 *****点赞操作点赞操作点赞操作 record==" + JSON.toJSONString(record));}
				if (ret > 0) {
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("**clickLikeOnArticle* 文章点赞取消点赞  失败 999  ret=0 **");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("**clickLikeOnArticle* 文章点赞取消点赞  失败 999  登录失败**");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**clickLikeOnArticle* 文章点赞取消点赞  失败 999**" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
	/**
	 * 文章收藏、取消收藏
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/collectArticle", method = RequestMethod.POST)
	@ResponseBody
	public String collectArticle(HttpServletRequest request, HttpServletResponse response, MyCollect record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setUserid(Integer.parseInt(userid));
				//COLLECTTYPES = { "tbl_docs", "tbl_topics", "tbl_books", "tbl_courses", "tbl_article", "tbl_sites" };
				record.setCollecttype(record.getCOLLECTTYPES()[4]);
				record = articleService.collectArticle(record);
				if(record != null){
					//更新用户操作数统计    存入session
					userService.getMyHeadTopAndOper(request);
					logger.info(
							"*collectBook** 文章收藏、取消收藏       对象ID objectId(即BookId)  judgeTodo 1关注，0取消关注**MyCollect record=="
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
				logger.info("*collectBook** 文章收藏、取消收藏    失败  999 登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*collectBook** 文章收藏、取消收藏    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
}
