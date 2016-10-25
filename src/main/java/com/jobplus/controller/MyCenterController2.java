package com.jobplus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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
import com.jobplus.pojo.Article;
import com.jobplus.pojo.BookShare;
import com.jobplus.pojo.Books;
import com.jobplus.pojo.Courses;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.ReportInfoConfig;
import com.jobplus.pojo.Sites;
import com.jobplus.pojo.Sms;
import com.jobplus.pojo.TypeConfig;
import com.jobplus.pojo.User;
import com.jobplus.pojo.VisitHistory;
import com.jobplus.pojo.WwwInfo;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.IArticleService;
import com.jobplus.service.IAttentionService;
import com.jobplus.service.IBookShareService;
import com.jobplus.service.IBooksService;
import com.jobplus.service.ICoursesService;
import com.jobplus.service.IOperationSumService;
import com.jobplus.service.IReportInfoService;
import com.jobplus.service.ISitesService;
import com.jobplus.service.ISmsService;
import com.jobplus.service.ITypeConfigService;
import com.jobplus.service.IUpdTableColumnService;
import com.jobplus.service.IUserService;
import com.jobplus.service.IVisitHistoryService;
import com.jobplus.utils.ConstantManager;
import com.jobplus.utils.DateUtils;

/**
 * 
 * 个人中心2
 * @author Jerry
 * 2016年7月12日上午9:13:31
 *
 */
@Controller
@RequestMapping("/myCenter")   
public class MyCenterController2 {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(MyCenterController2.class);
	@Resource
	private ISmsService smsService;
	@Resource
	private IBooksService booksService;
	@Resource
	private IBookShareService bookShareService;
	@Resource
	private ICoursesService coursesService;
	@Resource
	private IUserService userService;
	@Resource
	private ISitesService sitesService;
	@Resource
	private IAttentionService attentionService;
	@Resource
	private IArticleService articleService;
	@Resource
	private ITypeConfigService typeConfigService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IReportInfoService reportService;
	@Resource
	private IVisitHistoryService visitHistoryService;
	@Resource
	private IUpdTableColumnService updTableColumnService;
	
	
	/**
	 * 发送私信
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/sendSmsPrivate")
	@ResponseBody
	public String sendSmsPrivate(HttpServletRequest request, HttpServletResponse response, Sms record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				int ret = 0;
				// 设置发送人为当前登录人
				record.setSenderid(Integer.parseInt(userid));
				// 插入私信
				ret = smsService.insertPrvivatSms(record);
				if (ret > 0) {
					logger.info("**sendSmsPrivate*发送私信****record==" + JSON.toJSONString(record));
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("**sendSmsPrivate*发送私信  失败   999****");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnMsg("**登录失败****");
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("**sendSmsPrivate*发送私信  失败   999****");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**sendSmsPrivate*发送私信  失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
		
	/**
	 * 我分享的书籍列表
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSharedBookList")
	@ResponseBody
	public ModelAndView getSharedBookList(@RequestHeader("Accept") String encoding,HttpServletRequest request,Books record) throws Exception {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		BookShare bookShare = new BookShare();
		bookShare.setUserid(Integer.parseInt(userid));
		record.setBookShare(bookShare);
		Page<Books> sharedBookPage = booksService.getSharedBookList(record);
		logger.info("getSharedBookList   我分享的书籍列表    sharedBookList ==" + JSON.toJSONString(sharedBookPage));
		
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			Map<String, Page<Books>> map = new HashMap<String, Page<Books>>();
			map.put("sharedBookPage", sharedBookPage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			// 首次加载页面  get请求
//			//获取统计数  
//			OperationSum operationSum = getOperationSum(request);
//			logger.info("getSharedBookList 个人中心-我分享的书籍列表    获取统计数:" + JSON.toJSONString(operationSum));
//			mv.addObject("operationSum", operationSum);

			mv.addObject("sharedBookPage", sharedBookPage);
			mv.setViewName("mydocs/docs/mybook");
			return mv;
		}

	}
	/**
	 * 我收藏的书籍列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCollectedBookList")
	public ModelAndView getCollectedBookList(@RequestHeader("Accept") String encoding, HttpServletRequest request,
			Books record) throws Exception {
		ModelAndView mv = new ModelAndView();
		// List<Books> collectedBookList =
		// booksService.getCollectedBookList(record);
		Page<Books> collectedBookPage = booksService.getCollectedBookList(record);
		logger.info("getCollectedBookList   我收藏的书籍列表     page.getList() ==" + JSON.toJSONString(collectedBookPage));
		for (Books book : collectedBookPage.getList()) {
			book.setUserShareTime(DateUtils.formatDate(book.getMyCollect().getColltime(), "yyyy-MM-dd"));
		}

		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			Map<String, Page<Books>> map = new HashMap<String, Page<Books>>();
			map.put("collectedBookPage", collectedBookPage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			// 首次加载页面  get请求
//			//获取统计数  
//			OperationSum operationSum = getOperationSum(request);
//			logger.info("getSharedBookList 个人中心-我分享的书籍列表    获取统计数:" + JSON.toJSONString(operationSum));
//			mv.addObject("operationSum", operationSum);
			mv.addObject("collectedBookPage", collectedBookPage);
			mv.setViewName("mydocs/docs/mybook");
			return mv;
		}

	}

	/**
	 * 获取书籍分享详情用以编辑
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getBookShareDetailForEdit")
	public ModelAndView getBookShareDetailForEdit(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=true) String id) {
		ModelAndView mv = new ModelAndView();
		BookShare record = bookShareService.getBookShareDetailForEdit(Integer.parseInt(id));

		// 标签的所有父节点
		List<TypeConfig> parentTagsList = typeConfigService.getAllParentTypeConfigs();
		String parentTypeId;
		try {
			// 300:初中教育,302:物理化学 获取300
			parentTypeId = record.getSharetype().split(",")[0].split(":")[0];
		} catch (Exception e) {
			// 出错获取默认值 ：第一个父节点
			parentTypeId = parentTagsList.get(0).getTypeid().toString();
		}
		// 对应标签的子节点
		List<TypeConfig> childTagsList = typeConfigService.getChildrenTypeConfigs(parentTypeId);
		logger.info("*getBookShareDetailForEdit*获取书籍分享详情用以编辑***record*" + JSON.toJSONString(record));
		logger.info("**获取书籍分享详情用以编辑***parentTagsList*" + JSON.toJSONString(parentTagsList));
		logger.info("*getBookShareDetailForEdit*获取书籍分享详情用以编辑***childTagsList*" + JSON.toJSONString(childTagsList));

		mv.addObject("record", record);
		mv.addObject("parentTagsList", parentTagsList);
		mv.addObject("childTagsList", childTagsList);
		mv.setViewName("sharein/editshare/editBook");
		return mv;
	}
	/**
	 * 获取举报列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getReportInfoConfigList")
	@ResponseBody
	public String getReportInfoConfigList(HttpServletRequest request, HttpServletResponse response) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			List<ReportInfoConfig> list = reportService.getReportInfoConfigList();
			logger.info("**getReportInfoConfigList*获取举报列表****list==" + JSON.toJSONString(list));
			baseResponse.setBaseResponseList(list);
			baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
			baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**getReportInfoConfigList*获取举报列表       失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 获取url 简介
	 * @param request
	 * @param response
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/get3WInfo")
	@ResponseBody
	public String get3WInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true)String url, @RequestParam(required = true)String tableName) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if(!StringUtils.isBlank(userid)){
				int count = updTableColumnService.hasSharedUrl(tableName, Integer.parseInt(userid), url);
				if(count > 0){
					baseResponse.setReturnStatus(ConstantManager.INVALID_STATUS);
					baseResponse.setReturnMsg("这个链接您已经分享过了！");
					return JSON.toJSONString(baseResponse);
				}
					
			}else{
				baseResponse.setReturnStatus(ConstantManager.INVALID_STATUS);
				baseResponse.setReturnMsg("用户未登录");
				return JSON.toJSONString(baseResponse);
			}			
			
			String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" ;
			Pattern patt = Pattern.compile(regex);
			Matcher matcher = patt.matcher(url);
			boolean isMatch = matcher.matches();
			if (!isMatch) {				
				//URL地址不符合以“http://”打头       校验是否是合法网址  
				logger.info("***获取url 简介         URL地址不符合以“http://”打头         ****url=="+url);
				String regex1 = "(^(https?|ftp|file)://)?[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" ;
				Pattern patt1 = Pattern.compile(regex1);
				Matcher matcher1 = patt1.matcher(url);
				boolean isMatch1 = matcher1.matches();
				if(isMatch1){
					//合法   开始拼接网址
					url = "http://" + url;
					logger.info("***拼接url    ****url=="+url);
				}else{
					//不合法  抛出异常 
					throw new Exception();					
				}
			}
			
			Document doc = Jsoup.connect(url)
					.header("User-Agent", "Mozilla/31.0 (compatible; MSIE 10.0; Windows NT; DigExt)").timeout(3000)
					.get();
			String title = doc.title();
			String descStr = "";
			Elements Edesc = doc.select("[name=description]");
			Elements Ekey = doc.select("[name=keywords]");
			if (null != Edesc && Edesc.size() > 0) {
				descStr = Edesc.get(0).attr("content");
			} else {
				if (null != Ekey && Edesc.size() > 0) {
					descStr = Ekey.get(0).attr("content");
				}
			}
			if (descStr.indexOf("\u00A0") > 0) {//包含空格 转义
				descStr = descStr.replaceAll("\u00A0", "");
			}
			
			
			Element image = doc.select("img").first();
			String igmUrl = image.absUrl("src");
			
			WwwInfo wInfo = new WwwInfo();
			wInfo.setTitle(title);
			wInfo.setIntro(descStr);
			wInfo.setImgUrl(igmUrl);
			wInfo.setUrl(url);
			
			logger.info("**get3WInfo*获取url 简介****wInfo==" + JSON.toJSONString(wInfo));
			baseResponse.setObj(wInfo);
			baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			WwwInfo wInfo = new WwwInfo();
			wInfo.setTitle(url);
			wInfo.setIntro("");
			wInfo.setImgUrl("");
			wInfo.setUrl(url);
			baseResponse.setObj(wInfo);
			baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			logger.info("**get3WInfo*获取url 简介      失败   ****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
	/**
	 * 分享课程  
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/shareCourse", method = RequestMethod.POST)
	public ModelAndView shareCourse(HttpServletRequest request, HttpServletResponse response, Courses record) {
		int userid = Integer.parseInt((String) request.getSession().getAttribute("userid"));
		// 设置推荐人
		record.setUserid(userid);
		// 1.新增课程
		int ret = coursesService.insertCouseAndCourseShare(record);
		if(ret > 0){
			//个人操作数之类的信息放入session
			userService.getMyHeadTopAndOper(request);
		}
		
		ModelAndView mv = new ModelAndView();
		// 返回前端数据设置
		// mv.addObject("record", record);
		// 返回视图名设置
		mv.setViewName("redirect:successUploadCourse");
		logger.info("**shareCourse*  分享课程    ***record==" + JSON.toJSONString(record));
		return mv;
	}
	
	/**
	 * 我分享的课程列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSharedCourseList")
	@ResponseBody
	public ModelAndView getSharedCourseList(@RequestHeader("Accept") String encoding, HttpServletRequest request,Courses record) throws Exception {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		record.setUserid(Integer.parseInt(userid));
		Page<Courses> shaCoursePage = coursesService.getSharedCourseList(record);
		logger.info("getSharedCourseList   我分享的课程列表   shaCoursePage ==" + JSON.toJSONString(shaCoursePage));
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			Map<String, Page<Courses>> map = new HashMap<String, Page<Courses>>();
			map.put("shaCoursePage", shaCoursePage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			// 首次加载页面  get请求
//			//获取统计数  
//			OperationSum operationSum = getOperationSum(request);
//			logger.info("getSharedCourseList 个人中心-我分享的课程列表    获取统计数:" + JSON.toJSONString(operationSum));
//			mv.addObject("operationSum", operationSum);

			mv.addObject("shaCoursePage", shaCoursePage);
			mv.setViewName("mydocs/docs/mycourse");
			return mv;
		}
		
		
	}
	/**
	 * 我收藏的课程列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCollectedCourseList")
	@ResponseBody
	public ModelAndView getCollectedCourseList(@RequestHeader("Accept") String encoding, HttpServletRequest request,Courses record) throws Exception {
		ModelAndView mv = new ModelAndView();
		Page<Courses> colCoursePage = coursesService.getCollectedCourseList(record);
		logger.info("getCollectedCourseList   我收藏的课程列表   colCoursePage ==" + JSON.toJSONString(colCoursePage));
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			Map<String, Page<Courses>> map = new HashMap<String, Page<Courses>>();
			map.put("colCoursePage", colCoursePage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			// 首次加载页面  get请求
			//个人操作数之类的信息放入session
			userService.getMyHeadTopAndOper(request);
//			//获取统计数  
//			OperationSum operationSum = getOperationSum(request);
//			logger.info("getCollectedCourseList 个人中心-我收藏的课程列表    获取统计数:" + JSON.toJSONString(operationSum));
//			mv.addObject("operationSum", operationSum);
			mv.addObject("colCoursePage", colCoursePage);
			mv.setViewName("mydocs/docs/mycourse");
			return mv;
		}
		
	}
	
	/**
	 * 分享文章  
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/shareArticle", method = RequestMethod.POST)
	public ModelAndView shareArticle(HttpServletRequest request, HttpServletResponse response, Article record) {
		int userid = Integer.parseInt((String) request.getSession().getAttribute("userid"));
		// 设置推荐人
		record.setUserid(userid);
		// 1.新增文章
//		int ret = articleService.insertArticleAndArticlehare(record);
		int ret = articleService.insert(record);
		if(ret > 0){
			//个人操作数之类的信息放入session
			userService.getMyHeadTopAndOper(request);
		}
		ModelAndView mv = new ModelAndView();
		// 返回前端数据设置
		// mv.addObject("record", record);
		// 返回视图名设置
		mv.setViewName("redirect:successUploadArticle");
		logger.info("**shareArticle*  分享文章    ***record==" + JSON.toJSONString(record));
		return mv;
	}
	
	/**
	 * 我分享的文章列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSharedArticleList")
	@ResponseBody
	public ModelAndView getSharedArticleList(@RequestHeader("Accept") String encoding, HttpServletRequest request,Article record) throws Exception {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		record.setUserid(Integer.parseInt(userid));
		Page<Article> shaArticlePage = articleService.getSharedArticleList(record);
		logger.info("getSharedArticleList   我分享的文章列表   shaArticlePage ==" + JSON.toJSONString(shaArticlePage));
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			Map<String, Page<Article>> map = new HashMap<String, Page<Article>>();
			map.put("shaArticlePage", shaArticlePage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			// 首次加载页面  get请求
//			//获取统计数 
//			OperationSum operationSum = getOperationSum(request);
//			logger.info("getSharedArticleList 个人中心-我分享的文章列表    获取统计数:" + JSON.toJSONString(operationSum));
//			mv.addObject("operationSum", operationSum);

			mv.addObject("shaArticlePage", shaArticlePage);
			mv.setViewName("mydocs/docs/myarticle");
			return mv;
		}
	}
	/**
	 * 我收藏的文章列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCollectedArticleList")
	@ResponseBody
	public ModelAndView getCollectedArticleList(@RequestHeader("Accept") String encoding, HttpServletRequest request,Article record) throws Exception {
		ModelAndView mv = new ModelAndView();
		Page<Article> colArticlePage = articleService.getCollectedArticleList(record);
		logger.info("getSharedArticleList   我收藏的文章列表   colArticlePage ==" + JSON.toJSONString(colArticlePage));
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			Map<String, Page<Article>> map = new HashMap<String, Page<Article>>();
			map.put("colArticlePage", colArticlePage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			// 首次加载页面  get请求
//			//获取统计数  
//			OperationSum operationSum = getOperationSum(request);
//			logger.info("getCollectedArticleList 个人中心-我收藏的文章列表    获取统计数:" + JSON.toJSONString(operationSum));
//			mv.addObject("operationSum", operationSum);
			mv.addObject("colArticlePage", colArticlePage);
			mv.setViewName("mydocs/docs/myarticle");
			return mv;
		}
		
	}

	/**
	 * 分享站点  
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/shareSite", method = RequestMethod.POST)
	public ModelAndView shareSite(HttpServletRequest request, HttpServletResponse response, Sites record) {
		int userid = Integer.parseInt((String) request.getSession().getAttribute("userid"));
		// 设置推荐人
		record.setUserid(userid);
		// 1.新增站点
		int ret = sitesService.insertSiteAndSiteShare(record);
		if(ret > 0){
			//个人操作数之类的信息放入session
			userService.getMyHeadTopAndOper(request);
		}
		ModelAndView mv = new ModelAndView();
		// 返回前端数据设置
		// mv.addObject("record", record);
		// 返回视图名设置
		mv.setViewName("redirect:successUploadSite");
		logger.info("**shareSite*  分享站点    ***record==" + JSON.toJSONString(record));
		return mv;
	}
	
	/**
	 * 我分享的站点列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getSharedSiteList")
	@ResponseBody
	public ModelAndView getSharedSiteList(@RequestHeader("Accept") String encoding, HttpServletRequest request,Sites record) throws Exception {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		record.setUserid(Integer.parseInt(userid));
		Page<Sites> shaSitePage = sitesService.getSharedSiteList(record);
		logger.info("getSharedSiteList   我分享的站点列表   shaSitePage ==" + JSON.toJSONString(shaSitePage));
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			Map<String, Page<Sites>> map = new HashMap<String, Page<Sites>>();
			map.put("shaSitePage", shaSitePage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			// 首次加载页面  get请求
//			//获取统计数  
//			OperationSum operationSum = getOperationSum(request);
//			logger.info("getSharedSiteList 个人中心-我分享的站点列表    获取统计数:" + JSON.toJSONString(operationSum));
//			mv.addObject("operationSum", operationSum);

			mv.addObject("shaSitePage", shaSitePage);
			mv.setViewName("mydocs/docs/mysite");
			return mv;
		}
		
		
	}
	/**
	 * 我收藏的站点列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getCollectedSiteList")
	@ResponseBody
	public ModelAndView getCollectedSiteList(@RequestHeader("Accept") String encoding, HttpServletRequest request,Sites record) throws Exception {
		ModelAndView mv = new ModelAndView();
		Page<Sites> colSitePage = sitesService.getCollectedSiteList(record);
		logger.info("getCollectedSiteList   我收藏的站点列表   colSitePage ==" + JSON.toJSONString(colSitePage));
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			Map<String, Page<Sites>> map = new HashMap<String, Page<Sites>>();
			map.put("colSitePage", colSitePage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			// 首次加载页面  get请求
//			//获取统计数  
//			OperationSum operationSum = getOperationSum(request);
//			logger.info("getCollectedSiteList 个人中心-我收藏的站点列表    获取统计数:" + JSON.toJSONString(operationSum));
//			mv.addObject("operationSum", operationSum);
			mv.addObject("colSitePage", colSitePage);
			mv.setViewName("mydocs/docs/mysite");
			return mv;
		}		
	}	
	/**
	 * 获取统计数 （关注数、粉丝数、新消息数）
	 */
	@RequestMapping(value = "/getMyHeadTop")
	@ResponseBody
	public ModelAndView getMyHeadTop(HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("mydocs/mydoc");
		return mv;
	}
	/**
	 * 个人中心：我关注的人列表    需要传递useid
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMyAttenMan")
	@ResponseBody
	public ModelAndView getMyAttenMan(@RequestHeader("Accept") String encoding, HttpServletRequest request, User record)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		if (record.getUserid() == null) {
			String userid = (String) request.getSession().getAttribute("userid");
			record.setUserid(Integer.parseInt(userid));
		}
		Page<User> attenManPage = userService.getAttenManList(record);
		logger.info("getMyAttenMan  个人中心：我关注的人列表 record:" + JSON.toJSONString(record));
		logger.info("getMyAttenMan  个人中心：我关注的人列表attenManPage:" + JSON.toJSONString(attenManPage));
		if (encoding.indexOf("application/json") != -1) {
			// 分页 post请求
			Map<String, Page<User>> map = new HashMap<String, Page<User>>();
			map.put("attenManPage", attenManPage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			VisitHistory vh = new VisitHistory();
			vh.setUserid(record.getUserid());
			// 访问我的主页类型
			vh.setVisittype(vh.getVISITTYPES()[0]);
			// 最近访问的人
			Page<VisitHistory> visitors = visitHistoryService.getRecentVistors(vh);
			mv.addObject("visitors", visitors);
			// 被访问的人的个人信息
			User cutUser = userService.get(record.getUserid());

			mv.addObject("cutUser", cutUser);
			mv.addObject("attenManPage", attenManPage);
			mv.setViewName("mydocs/docs/myconcern");
			return mv;
		}
	}
	/**
	 * 个人中心：我的粉丝列表      需要传递useid
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMyFans")
	@ResponseBody
	public ModelAndView getMyFans(@RequestHeader("Accept") String encoding, HttpServletRequest request, User record)
			throws Exception {
		ModelAndView mv = new ModelAndView();
		if (record.getUserid() == null) {
			String userid = (String) request.getSession().getAttribute("userid");
			record.setUserid(Integer.parseInt(userid));
		}
		Page<User> myFansPage = userService.getMyFansList(record);
		logger.info("getMyFans  个人中心：我的粉丝列表 record:" + JSON.toJSONString(record));
		logger.info("getMyFans  个人中心：我的粉丝列表attenManPage:" + JSON.toJSONString(myFansPage));
		if (encoding.indexOf("application/json") != -1) {
			// 分页 post请求
			Map<String, Page<User>> map = new HashMap<String, Page<User>>();
			map.put("myFansPage", myFansPage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			VisitHistory vh = new VisitHistory();
			vh.setUserid(record.getUserid());
			// 访问我的主页类型
			vh.setVisittype(vh.getVISITTYPES()[0]);
			// 最近访问的人
			Page<VisitHistory> visitors = visitHistoryService.getRecentVistors(vh);
			mv.addObject("visitors", visitors);
			// 被访问的人的个人信息
			User cutUser = userService.get(record.getUserid());

			mv.addObject("cutUser", cutUser);
			mv.setViewName("mydocs/docs/myfans");
			mv.addObject("myFansPage", myFansPage);
			return mv;
		}
	}
	/**
	 * 阅读私信      俩参数：id;islook
	 */
	@RequestMapping(value = "/getSmsDetail")
	@ResponseBody
	public String getSmsDetail(HttpServletRequest request, Sms record) throws Exception {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setReceivedid(Integer.parseInt(userid));
				if (record.getId() == null) {// 私信ID为空
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("**阅读私信 私信ID为空****");
					logger.info("**getSmsDetail*阅读私信     私信ID为空   更改是否阅读  失败   999****");
					return JSON.toJSONString(baseResponse);
				}
				record = smsService.getSmsDetail(record);
				if (record != null) {
					//更新用消息统计数 放入session
					userService.getSmsOper(request);
					logger.info("**getSmsDetail*阅读私信     更改是否阅读****record==" + JSON.toJSONString(record));
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("**getSmsDetail*阅读私信     更改是否阅读  失败   999   ret=0****");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnMsg("**登录失败****");
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("**getSmsDetail*阅读私信  登录失败    更改是否阅读  失败   999****");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**sendSmsPrivate*阅读私信   抛出异常  更改是否阅读  失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}

	}
	/**
	 * 弹窗显示未读消息列表
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getNewSms")
	@ResponseBody
	public String getNewSms(HttpServletRequest request, HttpServletResponse response, Sms record,@RequestParam(required = false)String smsType) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			int newsSum = 0;
			if("1".equals(smsType)){
				//私信				
//				//未读私信总数
				newsSum = smsService.getPrivateSmsSum(Integer.parseInt(userid));
			}else{
				//系统通知等消息
				//未读消息总数(除私信)
				newsSum = smsService.getNewSmsSum(Integer.parseInt(userid));
			}			
			// 未读消息列表
			List<Sms> list = smsService.getNewSms(record);
			Sms sms = new Sms();
			// 只获取私信
			sms.setSmstype(sms.getSMSTYPES()[1]);
			// 私信列表
			List<Sms> privateList = smsService.getNewSms(sms);

			logger.info("**getNewSms*弹窗显示未读消息列表****list==" + JSON.toJSONString(list));
			
			//个人操作数之类的信息放入session
			userService.getMyHeadTopAndOper(request);
			//更新用消息统计数 放入session
			userService.getSmsOper(request);
			
			baseResponse.setObj(list);
			//点击获取未读消息数
			baseResponse.setObj2(newsSum);
			baseResponse.setBaseResponseList(privateList);
			baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
			baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**getNewSms*弹窗显示未读消息列表*      失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}	
	
	/**
	 * 防止重复提交 转发到静态页面
	 * 课程上传成功
	 */
	@RequestMapping(value = "/successUploadCourse", method = RequestMethod.GET)
	public ModelAndView success(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sharein/success/successUploadCourse");
		return mv;
	}
	/**
	 * 文章上传成功
	 */
	@RequestMapping(value = "/successUploadArticle", method = RequestMethod.GET)
	public ModelAndView successUploadArticle(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sharein/success/successUploadArticle");
		return mv;
	}
	/**
	 * 站点上传成功
	 */
	@RequestMapping(value = "/successUploadSite", method = RequestMethod.GET)
	public ModelAndView successUploadSite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sharein/success/successUploadSite");
		return mv;
	}
	public static void main(String[] args) throws Exception {
		String url = "www.zhihu.com/question/30026173#answer-40758495";
		
		String regex = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" ;
		Pattern patt = Pattern.compile(regex);
		Matcher matcher = patt.matcher(url);
		boolean isMatch = matcher.matches();
		if (!isMatch) {				
			//URL地址不符合以“http://”打头       校验是否是合法网址  
			String regex1 = "(^(https?|ftp|file)://)?[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]" ;
			Pattern patt1 = Pattern.compile(regex1);
			Matcher matcher1 = patt1.matcher(url);
			boolean isMatch1 = matcher1.matches();
			if(isMatch1){
				//合法   开始拼接网址
				url = "http://" + url;
				System.out.println("拼接http  "+ url);
			}else{
				System.out.println("抛出异常  ");
				//不合法  抛出异常 
				throw new Exception();					
			}
		}else{
			System.out.println("合法");
		}
	}
}
