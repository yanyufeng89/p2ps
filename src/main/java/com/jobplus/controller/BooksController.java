package com.jobplus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.BookLiked;
import com.jobplus.pojo.BookShare;
import com.jobplus.pojo.Books;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.OperationSum;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.IBookLikedService;
import com.jobplus.service.IBookShareService;
import com.jobplus.service.IBooksService;
import com.jobplus.service.IHomePageService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.ITypeConfigService;
import com.jobplus.service.IUserService;
import com.jobplus.utils.ConstantManager;
import com.jobplus.utils.SolrJUtils;

/**
 * 书籍控制器 包括书籍分享评论
 * 
 * @author Jerry 2016年7月12日上午11:08:06
 *
 */
@Controller
@RequestMapping("/books")
public class BooksController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(BooksController.class);
	@Resource
	private IBooksService booksService;
	@Resource
	private IBookShareService bookShareService;
	@Resource
	private IBookLikedService bookLikedService;
	@Resource
	private SolrJUtils solrJUtils;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IUserService userService;
	@Resource
	private IHomePageService homePageService;
	@Resource
	private ITypeConfigService typeConfigService;

	/**
	 * 获取书籍详情
	 * 
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/getBookDetail/{id}")
	@ResponseBody
	public ModelAndView getBookDetail(HttpServletRequest request, HttpServletResponse response, Books record,@RequestParam(required=false) String isAdmin,@PathVariable String id) {
		ModelAndView mv = new ModelAndView();
		if (record.getId() != null) {
			record = booksService.getBookDetail(record);
			if(null == record){
				mv.setViewName("404");
				return mv;
			}
//			logger.info("**getBookDetail*获取书籍详情****record==" + JSON.toJSONString(record));
			mv.addObject("record",record);
			if("7".equals(isAdmin)){
				//后台管理员查看
				mv.setViewName("manage/bookDetail");
			}else{
				mv.setViewName("/mydocs/docs/bookDetail");
			}
		} else {
			logger.info("**getBookDetail*获取书籍详情  失败   record.getId() == null  999****");
		}
		return mv;
	}
	/**
	 * 加载更多书籍评论    参数：书籍ID、pageNo
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/loadComments")
	@ResponseBody
	public String loadBookComments(HttpServletRequest request, HttpServletResponse response, BookShare record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			if (record.getBookid() != null) {
				Page<BookShare> page = bookShareService.getList(record);
				logger.info("**loadBookComments*书籍评论加载更多****page==" + JSON.toJSONString(page));
				baseResponse.setObj(page);
				baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
				baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("**loadComments*书籍评论加载更多    失败   record.getId() == null  999****");
			}
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**loadComments*书籍评论加载更多  失败 抛出异常   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}

	/**
	 * 根据书名模糊查找书籍
	 * 
	 * @param request
	 * @param response
	 * @param bookName
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value = "/searchBooksByName")
	@ResponseBody
	public String searchBooksByName(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) String bookName, @RequestParam(required = false)String pages, @RequestParam(required = false)String rows) {
		logger.info("**searchBooksByName*根据书名模糊查找书籍****bookName==" + bookName + "   pages=="+pages);
//		BaseResponse baseResponse = new BaseResponse();
		// 这里设置 默认每页显示条数
		rows = !StringUtils.isBlank(rows)?rows:"10";

		// 搜索结果 List
		List<?> bookList = solrJUtils.searchBook(bookName, "", "", pages, rows, "");
		
		// 搜索结果总条数
//		Long reCount = bookList.size() > 0 ? (Long) bookList.get(0) : 0L;
//		// 搜索结果集
//		String result = bookList.size() > 0 ?"{\"list\":" + JSON.toJSONString(bookList.get(1))
//		+ ",\"returnMsg\":\"SUCCESS\",\"returnStatus\":\"000\"}": "";
//		String result = bookList.size() > 0 ? JSON.toJSONString(bookList.get(1)): "";
//		result = "{\"list\":" + JSON.toJSONString(result)
//		+ ",\"returnMsg\":\"SUCCESS\",\"returnStatus\":\"000\"}"
//		+",\"reCount\":"+reCount;
//		logger.info(result);
		
//		baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
//		baseResponse.setObj(result);
//		baseResponse.setObj2(reCount);
		
		
		return JSON.toJSONString(bookList);
	}

	/**
	 * 获取书籍简略信息
	 * 
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/getBookSimpleInfo")
	@ResponseBody
	public String getBookSimpleInfo(HttpServletRequest request, HttpServletResponse response, Books record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			if (record.getId() != null) {
				record = booksService.selectByPrimaryKey(record.getId());
				logger.info("**getBookSimpleInfo*获取书籍简略信息****record==" + JSON.toJSONString(record));
				baseResponse.setObj(record);
				baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
				baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("**getBookSimpleInfo*获取书籍简略信息    失败   record.getId() == null  999****");
			}
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**getBookSimpleInfo*获取书籍简略信息  失败 抛出异常   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}

	/**
	 * 分享书籍
	 * 
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/shareBook", method = RequestMethod.POST)
	public ModelAndView shareBook(HttpServletRequest request, HttpServletResponse response, BookShare record) {
		int userid = Integer.parseInt((String) request.getSession().getAttribute("userid"));
		// 设置推荐人
		record.setUserid(userid);
		// 1.插入话题
		@SuppressWarnings("unused")
		int ret = bookShareService.insert(record);

		ModelAndView mv = new ModelAndView();
		// 返回前端数据设置
		// mv.addObject("record", record);
		// 返回视图名设置
		mv.setViewName("redirect:success");
		logger.info("**shareBook*  分享书籍  ***record==" + JSON.toJSONString(record));
		//个人操作数之类的信息放入session
		userService.getMyHeadTopAndOper(request);
		return mv;
	}

	/**
	 * 书籍评论点赞取消点赞
	 * 
	 * @param request
	 * @param response
	 * @param bShare
	 * @return
	 */
	@RequestMapping(value = "/clickOnLike", method = RequestMethod.POST)
	@ResponseBody
	public String clickOnLike(HttpServletRequest request, HttpServletResponse response, BookShare bShare) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if (user!=null) {
				int ret = 0;

				BookLiked record = new BookLiked();
				record.setCommid(bShare.getId());
				record.setUserid(user.getUserid());
				record.setRelationidPg(bShare.getRelationidPg());
				record.setObjCreatepersonPg(bShare.getObjCreatepersonPg());
				record.setObjectNamePg(bShare.getObjectNamePg());
				record.setTopObjId(bShare.getTopObjId());
				if ("0".equals(request.getParameter("likeOperate"))) {// 没有点赞
					//开始点赞
					ret = bookLikedService.insert(record,request.getContextPath(),user);
					logger.info("**clickOnLike* 书籍评论点赞取消点赞 *****点赞操作点赞操作点赞操作 record==" + JSON.toJSONString(record));
				} else {// 已点赞
					//开始取消点赞
					ret = bookLikedService.deleteByPrimaryKey(record);
					logger.info("**clickOnLike* 书籍评论点赞取消点赞 *****取消操作取消操作取消操作  record==" + JSON.toJSONString(record));
				}
				if (ret > 0) {
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("**clickOnLike* 书籍评论点赞取消点赞  失败 999  ret=0 **");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("**clickOnLike* 书籍评论点赞取消点赞  失败 999  登录失败**");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**clickOnLike* 书籍评论点赞取消点赞  失败 999**" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}

	/**
	 * 书籍收藏、取消收藏    对象ID objectId(即BookId) actionType 1关注，0取消关注
	 * 
	 * @param request
	 * @param response
	 * @param objectId
	 * @param actionType
	 * @return
	 */
	@RequestMapping(value = "/collectBook", method = RequestMethod.POST)
	@ResponseBody
	public String collectBook(HttpServletRequest request, HttpServletResponse response, MyCollect record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setUserid(Integer.parseInt(userid));
				record = booksService.collectBook(record);
				if(record != null) {
					//更新用户操作数统计    存入session
					userService.getMyHeadTopAndOper(request);
					logger.info(
							"*collectBook** 书籍收藏、取消收藏       对象ID objectId(即BookId)  actionType 1关注，0取消关注**MyCollect record=="
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
				logger.info("*collectBook** 书籍收藏、取消收藏    失败  999 登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*collectBook** 书籍收藏、取消收藏    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 新增评论 or 新增推荐(对应书籍分享数增加1)  书籍下方的评论数增加1
	 * 
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/insertComment", method = RequestMethod.POST)
	@ResponseBody
	public String insertComment(HttpServletRequest request, HttpServletResponse response, BookShare record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if (user!=null) {
				record.setUserid(user.getUserid());
				record = bookShareService.insertAndReturnId(record,request.getContextPath(),user);
				if(record != null){
					logger.info("*insertComment**新增评论 or 新增推荐**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				}else{
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*insertComment** 新增评论 or 新增推荐    失败  999   登录失败*********");
				}				
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*insertComment** 新增评论 or 新增推荐    失败  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*insertComment** 新增评论 or 新增推荐    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 删除评论 or 删除推荐  
	 * @param request
	 * @param response
	 * @param record   BookShare.id
	 * @return
	 */
	@RequestMapping(value = "/delComment", method = RequestMethod.POST)
	@ResponseBody
	public String delComment(HttpServletRequest request, HttpServletResponse response, BookShare record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				if(!StringUtils.isBlank(record.getCondition())){//批量删除	  个人中心：我分享的书籍列表   				
					String conditions[] = record.getCondition().split(",");
					// 书籍ID 暂时定义长度
					String bookIds[] = new String[conditions.length];
					for (int i = 0; i < conditions.length; i++) {
						bookIds[i] = conditions[i].split(":")[1];
						conditions[i] = conditions[i].split(":")[0];
					}

					ret = bookShareService.deleteByConditions(conditions, bookIds);
				}else{//书籍详情页    单个删除下方的评论或推荐语
					ret = bookShareService.deleteByRecord(record);
				}				
				if(ret > 0){
					
					//个人操作数之类的信息放入session
					userService.getMyHeadTopAndOper(request);
					//获取统计数 
					OperationSum operationSum = userService.getOperationSum(request);		
					baseResponse.setOperationSum(operationSum);
					logger.info("***deleteTopicsComments**个人中心--批量删除我的回复******");
					logger.info("*delComment**删除评论 or 删除推荐**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				}else{
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*delComment** 删除评论 or 删除推荐    失败  999   删除失败*********");
				}				
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*delComment** 删除评论 or 删除推荐    失败  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*insertComment** 删除评论 or 删除推荐    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}

	/**
	 * 防止重复提交 转发到静态页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public ModelAndView success(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sharein/success/successUploadBook");
		return mv;
	}
	
	/**
	 * 书籍专区
	 * 
	 * @param Condition
	 *            查询关键字
	 * @param sharedType
	 *            行业分类
	 * @param tags
	 *            标签
	 * @param pages
	 *            第几页，默认第0页(页标从0开始)
	 * @param rows
	 *            每页几条数据，默认10条
	 * @param sortType
	 *         书籍专区   话题分类参数     1：热门书籍    2：最新书籍        默认为1
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "static-access", "unchecked" })
	@RequestMapping(value = "/fore/area/{sharedType}")
	@ResponseBody
	public ModelAndView search(@RequestHeader("Accept") String encoding, HttpServletRequest request,@PathVariable String sharedType,
			@RequestParam(required = false) String Condition, 
			@RequestParam(required = false) String tags, @RequestParam(required = false)String pages, @RequestParam(required = false)String sortType)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		//如果sharedType==0  表示查询所有分类
		sharedType = "0".equals(sharedType)?"":sharedType;
		sortType = "2".equals(sortType)?"2":"1";
		
		//这里设置  默认每页显示条数
		String rows = "10";
		//搜索结果 List
		List<?> bookList = solrJUtils.searchBook(Condition, sharedType, tags, pages, rows, sortType);
		
		
		//搜索结果总条数
		Long reCount = bookList.size()>0?(Long)bookList.get(0):0L;
		//搜索结果集
		String result =  bookList.size()>0?bookList.get(1).toString():"";
		
		logger.info("********Condition=="+Condition+"********sharedType=="+sharedType+"********页数    pages=="+pages+"********分页大小 默认10 rows=="+rows+"********总条数reCount=="+reCount);
		
		if (encoding.indexOf("application/json") != -1) {
			// post请求
			@SuppressWarnings("rawtypes")
			Map map = new HashMap<String, String>();
			map.put("result", result);
			map.put("reCount", reCount);
			map.put("rows", rows);
			map.put("sortType", sortType);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {

			mv.setViewName("bookDivision");
			mv.addObject("result", result);
			mv.addObject("reCount", reCount);
			mv.addObject("rows", rows);
			mv.addObject("sortType", sortType);
			mv.addObject("typeConfigs", typeConfigService.getAllParentTypeConfigs());	
			
			mv.addObject("preSharedType", sharedType);
			mv.addObject("preCondition", Condition);
			mv.addObject("prePages", pages);
			
			return mv;
		}
	}
	
	
	public static void main(String[] args) {
		String ss = "1:11,2:22,3:33";
		String conditions[] = ss.split(",");
//		String bookIds[] = new String[conditions.length];
		
		for (int i = 0; i < conditions.length; i++) {
//			bookIds[i] = conditions[i].split(":")[0];
			conditions[i] = conditions[i].split(":")[0];
//			System.out.println(bookIds[i]);
			System.out.println(conditions[i]);
		}
	}

}
