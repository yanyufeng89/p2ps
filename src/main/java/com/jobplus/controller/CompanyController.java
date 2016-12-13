package com.jobplus.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.BusiAreaLib;
import com.jobplus.pojo.CompanyIntro;
import com.jobplus.pojo.CompanyNews;
import com.jobplus.pojo.CpnewsComment;
import com.jobplus.pojo.CpnewsIslikedKey;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;
import com.jobplus.pojo.WwwInfo;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.ICompanyIntroService;
import com.jobplus.service.ICompanyNewsService;
import com.jobplus.service.ICompanyService;
import com.jobplus.service.ICpnewsCommentService;
import com.jobplus.service.IMyHomePageService;
import com.jobplus.service.ISensitiveWordsService;
import com.jobplus.service.IUserService;
import com.jobplus.utils.ConstantManager;
import com.jobplus.utils.HTTPCommonUtil;
import com.jobplus.utils.SolrJUtils;

/**
 * 公司(企业)
 * @author Jerry
 * 2016年11月30日上午10:46:20
 *
 */
@Controller
@RequestMapping("/comp")
public class CompanyController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(CompanyController.class);
	@Resource
	private ICompanyService companyService;
	@Resource
	private ICpnewsCommentService cpnewsCommentService;
	@Resource
	private ICompanyIntroService companyIntroService;
	@Resource
	private ICompanyNewsService companyNewsService;
	@Resource
	private IUserService userService;
	@Resource
	private SolrJUtils solrJUtils;
	@Resource
	private ISensitiveWordsService sensitiveWordsService;
	@Resource
	private IMyHomePageService myHomePageService;
	
	/**
	 * 进入企业主页
	 * @param request
	 * @param userid
	 * @param isReview 0是编辑界面   其他都是预览
	 */
	@RequestMapping(value = "/getHomePage/{userid}")
	@ResponseBody
	public ModelAndView getHomePage(HttpServletRequest request,@PathVariable String userid,@RequestParam(required=false) String isReview) throws Exception  {

		ModelAndView mv = new ModelAndView();		
		if(StringUtils.isBlank(userid)){
			mv.setViewName("404");
			return mv;
		}		
		//当前登录人id
		String cutUserid = (String) request.getSession().getAttribute("userid");
		
		User user = userService.get(Integer.parseInt(userid));
		if (user.getUsertype() != null && user.getUsertype() == 1) {
			// 个人用户
			mv = myHomePageService.getHomePage(request, mv, userid, cutUserid, isReview);
		} else {
			// 企业用户
			mv = companyService.getHomePage(request, mv, userid, cutUserid, isReview);

			// 编辑界面
			if ("0".equals(isReview) && cutUserid.equals(userid))
				mv.setViewName("mydocs/mycenter/companyCenter");
			// 预览界面
			else
				mv.setViewName("mydocs/mycenter/aboutCompany");
		}
		
		return mv;
	}
	
	/**
	 * 加载企业快讯 
	 * param:compid
	 */
	@RequestMapping(value = "/loadNews", method = RequestMethod.POST)
	@ResponseBody
	public String loadNews(HttpServletRequest request, HttpServletResponse response, CompanyNews cpNews) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			// 公司快讯
			Page<CompanyNews> page = companyNewsService.getNewsList(cpNews);
			logger.info("**loadNews*加载企业快讯 ****page==" + JSON.toJSONString(page));
			baseResponse.setObj(page);
			baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
			baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);

			return JSON.toJSONString(baseResponse);

		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*loadNews** --加载企业快讯     失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 加载企业快讯评论    
	 * param:newsid
	 */
	@RequestMapping(value = "/loadNewsComments", method = RequestMethod.POST)
	@ResponseBody
	public String loadNewsComments(HttpServletRequest request, HttpServletResponse response, CpnewsComment record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			Page<CpnewsComment> page = cpnewsCommentService.getComtList(record);
			logger.info("**loadNewsComments*加载快讯评论****page==" + JSON.toJSONString(page));
			baseResponse.setObj(page);
			baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
			baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			return JSON.toJSONString(baseResponse);
			
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*loadNewsComments** --加载快讯评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 公司主页   修改主页信息
	 */
	@RequestMapping(value = "/updUserInfo")
	@ResponseBody  
	public String updUserInfo(HttpServletRequest request, HttpServletResponse response, CompanyIntro companyIntro) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				if(companyIntro!=null){
					//更新的是企业表信息CompanyIntro tbl_company_intro
					companyIntro.setId(Integer.parseInt(userid));
					//上传图片文件
					if(companyIntro.getImgFiles()!=null && companyIntro.getImgFiles().getSize()>0){
						MultipartFile[] files = new MultipartFile[1];
						files[0] = companyIntro.getImgFiles();
						BaseResponse resp = this.updImg(files, request);
						if("000".equals(resp.getReturnStatus())){
							companyIntro.setImgurl(resp.getObj().toString().split(";")[0]);
							//此处只是更改图片
							int rt = companyIntroService.updImgurl(companyIntro);
							if(rt > 0){
								baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
								baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
							}else{
								baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
								baseResponse.setReturnMsg("图片更新失败");
							}								
							return JSON.toJSONString(baseResponse);
						}else{
							baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
							baseResponse.setReturnMsg("图片上传失败");
							return JSON.toJSONString(baseResponse);
						}
						
					}
					//判断是有修改创建时间(避免如果establishtime为null,不增量更新)
					Integer isEstTime = request.getParameter("isEstTime")!=null?1:0;
					ret = companyIntroService.updateByPrimaryKeySelective(companyIntro,isEstTime);					
				}
				if (ret > 0) {
					//获取最新用户信息  放入session
					User ltUser = userService.get(Integer.parseInt(userid));
					request.getSession().setAttribute("user", ltUser);
					
					baseResponse.setObj(companyIntro);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("更新失败");
				}
			}
			logger.info("**updUserInfo*个人主页 修改个人信息**ret=" + ret + "**record==" + JSON.toJSONString(companyIntro));
			// baseResponse.setObj(list);
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**updUserInfo*个人主页 修改个人信息*      失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}

/*	// timestamp 数据类型绑定
	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(Timestamp.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				try {
					setValue(new DateFormatter(value,));
				} catch (Exception e) {
					setValue(null);
					logger.info("数据绑定******    时间为 null");
					// e.printStackTrace();
				}
			}
		});
	}*/
/*	@InitBinder("user")
    public void initUser(WebDataBinder binder){
        binder.setFieldDefaultPrefix("user_");
    }
    @InitBinder("companyIntro")
    public void initAdmin(WebDataBinder binder){
        binder.setFieldDefaultPrefix("companyIntro_");
    }*/
    /**
	 * 新增快讯评论
	 */
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	public String addComment(HttpServletRequest request, HttpServletResponse response, CpnewsComment record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if (user!=null) {
					record.setUserid(user.getUserid());
					record = cpnewsCommentService.insertAndReturnRecord(record,request.getContextPath(),user);
				if (record != null) {
					logger.info("*addComment**--新增快讯评论**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("请刷新页面重试");
					logger.info("*addComment** --新增快讯评论   失败  999  新增失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*addComment**--新增快讯评论 999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*addComment** --新增快讯评论   失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
	/**
	 * 删除评论
	 */
	@RequestMapping(value = "/delComment", method = RequestMethod.POST)
	@ResponseBody
	public String delComment(HttpServletRequest request, HttpServletResponse response, CpnewsComment record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setUserid(Integer.parseInt(userid));
				ret = cpnewsCommentService.delComment(record);
				if (ret > 0) {
					logger.info("*delComment**--删除快讯评论**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*delComment** --删除快讯评论    失败  999   删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*delComment**--删除快讯评论  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*delComment** --删除快讯评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 快讯点赞
	 */
	@RequestMapping(value = "/likeNews", method = RequestMethod.POST)
	@ResponseBody
	public String likeNews(HttpServletRequest request, HttpServletResponse response, CompanyNews CompanyNews) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				int ret = 0;

				CpnewsIslikedKey record = new CpnewsIslikedKey();
				record.setUserid(user.getUserid());
				record.setNewsid(CompanyNews.getId());
				record.setRelationidPg(CompanyNews.getId());
				record.setObjCreatepersonPg(CompanyNews.getObjCreatepersonPg());
				record.setObjectNamePg(CompanyNews.getObjectNamePg());
				if ("1".equals(request.getParameter("likeOperate"))) {// 已点赞
					// 开始取消点赞
					ret = cpnewsCommentService.cancelLikeNews(record);
					logger.info(
							"**clickLikeOnCourse* 快讯点赞取消点赞 *****取消操作取消操作取消操作  record==" + JSON.toJSONString(record));
				} else {// 没有点赞
					// 开始点赞
					ret = cpnewsCommentService.likeNews(record, request.getContextPath(), user);
					logger.info("**clickLikeOnCourse* 快讯点赞取消点赞 *****点赞操作点赞操作点赞操作 record==" + JSON.toJSONString(record));
				}
				if (ret > 0) {
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("**clickLikeOnCourse* 快讯点赞取消点赞  失败 999  ret=0 **");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("**clickLikeOnCourse* 快讯点赞取消点赞  失败 999  登录失败**");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**clickLikeOnCourse* 快讯点赞取消点赞  失败 999**" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 上传图片
	 * @param files
	 * @param request
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/updImg", method = RequestMethod.POST)
	@ResponseBody
	public BaseResponse updImg(@RequestParam(value="imgField",required=false) MultipartFile[] files,HttpServletRequest request) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");
			String imgUrl = companyService.uploadImg(files, user);
			if (!StringUtils.isBlank(imgUrl)) {
				logger.info("*updImg**--上传图片 success**" );
				baseResponse.setObj(imgUrl);
				baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*updImg** --上传图片    失败  999   删除失败*********");
			}
			return baseResponse;
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*updImg** --上传图片    失败  999 *********" + e.getMessage());
			return baseResponse;
		}
	}
	/**
	 * 新增企业快讯 发布企业快讯
	 */
	@RequestMapping(value = "/addCpNews", method = RequestMethod.POST,produces="application/json;charset=UTF-8")
	@ResponseBody
	public String addCpNews(HttpServletRequest request, HttpServletResponse response, CompanyNews record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if (user!=null) {
					record.setCompid(user.getUserid());
					//上传图片文件 
					if(record.getNewsImgFiles()!=null && record.getNewsImgFiles().getSize()>0){
						MultipartFile[] files = new MultipartFile[1];
						files[0] = record.getNewsImgFiles();
						BaseResponse resp = this.updImg(files, request);
						if("000".equals(resp.getReturnStatus())){
							record.setImgurl(resp.getObj().toString().split(";")[0]);
						}else{
							baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
							baseResponse.setReturnMsg("图片上传失败");
							return JSON.toJSONString(baseResponse);
						}
					}
					//页面端不需要 设置为null 
					record.setNewsImgFiles(null);
					record = companyNewsService.insertCpNews(record);
				if (record != null) {
					logger.info("*addCpNews**--新增企业快讯**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("请刷新页面重试");
					logger.info("*addCpNews** --新增企业快讯   失败  999  新增失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*addCpNews**--新增企业快讯 999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*addCpNews** --新增企业快讯   失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 删除企业快讯
	 */
	@RequestMapping(value = "/delCpNews", method = RequestMethod.POST)
	@ResponseBody
	public String delCpNews(HttpServletRequest request, HttpServletResponse response, CompanyNews record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				int ret = 0;
				record.setCompid(user.getUserid());
				ret = companyNewsService.delCpNews(record);
				if (ret > 0) {
					logger.info("*addCpNews**--删除企业快讯**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("请刷新页面重试");
					logger.info("*addCpNews** --删除企业快讯   失败  999  删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*addCpNews**--删除企业快讯 999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*addCpNews** --删除企业快讯   失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 置顶企业快讯
	 */
	@RequestMapping(value = "/topCpNews", method = RequestMethod.POST)
	@ResponseBody
	public String topCpNews(HttpServletRequest request, HttpServletResponse response, CompanyNews record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				int ret = 0;
				record.setCompid(user.getUserid());
				ret = companyNewsService.topCpNews(record);
				if (ret > 0) {
					logger.info("*addCpNews**--置顶企业快讯**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("请刷新页面重试");
					logger.info("*addCpNews** --置顶企业快讯   失败  999  置顶失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*addCpNews**--置顶企业快讯 999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*addCpNews** --置顶企业快讯   失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 获取一条企业快讯
	 */
	@RequestMapping(value = "/getOneNews",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getOneNews(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer id) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				CompanyNews record = companyNewsService.getOneNews(id);
				baseResponse.setObj(record);
				baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*getOneNews**--获取一条企业快讯 999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*getOneNews** --获取一条企业快讯   失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 搜索业务领域
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value = "/findBusLib/{condition}", method = RequestMethod.POST)
	public String findSkill (HttpServletRequest request,@PathVariable String condition) throws Exception {
		return solrJUtils.findBusiArea(condition);
	}
	/**
	 * 插入业务领域
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value = "/insertBusLib", method = RequestMethod.POST)
	public String insertTags(HttpServletRequest request, BusiAreaLib record) throws Exception {
		BaseResponse response = new BaseResponse();
		try {
			int ret = 0;
			// 校验输入的是否合法
			if (!sensitiveWordsService.isSensitive(record.getBusiareaname())) {
				String userid = (String)request.getSession().getAttribute("userid");
				record.setCreator(Integer.parseInt(userid));
				// 合法
				ret = companyService.insertBusLib(record);
				if (ret > 0) {
					//更新标签搜索
					solrJUtils.addBusiAreaIndexToSolr(record);
					response.setObj(record);
					response.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					response.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					response.setReturnStatus(ConstantManager.ERROR_STATUS);
					response.setReturnStatus("业务领域入库失败");
				}
			} else {
				// 不合法
				response.setReturnMsg(ConstantManager.INVALID_STATUS);// -999
				response.setReturnStatus("您输入的业务领域不合法,请重新输入");
			}
			return JSON.toJSONString(response);
		} catch (Exception e) {
			response.setReturnMsg(e.getMessage());
			response.setReturnStatus(ConstantManager.ERROR_STATUS);
			return JSON.toJSONString(response);
		}
	}
	
	
	
	/**
	 * 获取网站信息
	 * @param url
	 * @return
	 */
	@RequestMapping(value = "/get3WInfo")
	@ResponseBody
	public String get3WInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true)String url) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			
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

			Document doc = HTTPCommonUtil.getHtmlByUrl(url);
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
			/*wInfo.setTitle(title);*/
			//截取前70个字
			wInfo.setTitle(title.length()>70?title.substring(0, 70)+"...":title);
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
	 * 获取一条企业记录  
	 */
	@RequestMapping(value = "/getCompIntro",produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getCompIntro(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = true) Integer id) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User) request.getSession().getAttribute("user");
			if (user != null) {
				CompanyIntro record = companyIntroService.selectByPrimaryKey(id);
				baseResponse.setObj(record);
				baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*getOneNews**--获取一条企业快讯 999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*getOneNews** --获取一条企业快讯   失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
}
