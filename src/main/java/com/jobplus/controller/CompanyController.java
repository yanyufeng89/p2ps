package com.jobplus.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.CompanyIntro;
import com.jobplus.pojo.CompanyNews;
import com.jobplus.pojo.CpnewsComment;
import com.jobplus.pojo.CpnewsIslikedKey;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.ICompanyIntroService;
import com.jobplus.service.ICompanyNewsService;
import com.jobplus.service.ICompanyService;
import com.jobplus.service.ICpnewsCommentService;
import com.jobplus.service.IUserService;
import com.jobplus.utils.ConstantManager;

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
//		if(StringUtils.isBlank(userid)){
//			mv.setViewName("404");
//			return mv;
//		}
		
		//当前登录人id
		String cutUserid = (String) request.getSession().getAttribute("userid");
		
		mv = companyService.getHomePage(request, mv, userid, cutUserid, isReview);
		
		//编辑界面
		if("0".equals(isReview))
			mv.setViewName("mydocs/mycenter/companyCenter");		
		//预览界面
		else
			mv.setViewName("mydocs/mycenter/aboutCompany");
		
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
//			String userid = (String) request.getSession().getAttribute("userid");
//			if (!StringUtils.isBlank(userid)) {
				//公司快讯
				Page<CompanyNews> page = companyNewsService.getNewsList(cpNews);
				logger.info("**loadNews*加载企业快讯 ****page==" + JSON.toJSONString(page));
				baseResponse.setObj(page);
				baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
				baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
//			}else{
//				baseResponse.setReturnMsg("登录失败");
//				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
//			}
			
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
	 * 个人主页 修改个人信息
	 */
	@RequestMapping(value = "/updUserInfo")
	@ResponseBody  
	public String updUserInfo(HttpServletRequest request, HttpServletResponse response, User user,CompanyIntro companyIntro) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				if(companyIntro!=null){
					//更新的是企业表信息CompanyIntro tbl_company_intro
					companyIntro.setId(Integer.parseInt(userid));
					ret = companyIntroService.updateByPrimaryKey(companyIntro);					
				}
				if(user!=null){
					//更新用户表信息 tbl_user
					user.setUserid(Integer.parseInt(userid));
					if(user.getHeadIconFile()!=null){
						ret = userService.updUserInfoIncHeadIcon(user.getHeadIconFile(), user, request, response);
					}else{
						ret = userService.updateByPrimaryKeySelective(user);
					}					
				}				
				
				if (ret > 0) {
					//获取最新用户信息  放入session
					User ltUser = userService.get(Integer.parseInt(userid));
					request.getSession().setAttribute("user", ltUser);
					
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					baseResponse.setReturnMsg("更新失败");
				}
			}
			logger.info("**updUserInfo*个人主页 修改个人信息**ret=" + ret + "**record==" + JSON.toJSONString(user));
			// baseResponse.setObj(list);
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**updUserInfo*个人主页 修改个人信息*      失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}	
	@InitBinder("user")
    public void initUser(WebDataBinder binder){
        binder.setFieldDefaultPrefix("user_");
    }
    @InitBinder("companyIntro")
    public void initAdmin(WebDataBinder binder){
        binder.setFieldDefaultPrefix("companyIntro_");
    }
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
				record.setRelationidPg(CompanyNews.getRelationidPg());
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
	public String updImg(@RequestParam(value="imgField",required=false) MultipartFile[] files,HttpServletRequest request,CompanyNews record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");
			String imgUrl = companyService.uploadImg(files, user);
			if (!StringUtils.isBlank(imgUrl)) {
				logger.info("*updImg**--上传图片**record==" + JSON.toJSONString(record));
				baseResponse.setObj(imgUrl);
				baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*updImg** --上传图片    失败  999   删除失败*********");
			}
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*updImg** --上传图片    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 新增企业快讯
	 */
	@RequestMapping(value = "/addCpNews", method = RequestMethod.POST)
	@ResponseBody
	public String addCpNews(HttpServletRequest request, HttpServletResponse response, CompanyNews record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if (user!=null) {
					record.setCompid(user.getUserid());
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
				ret = companyNewsService.delCpNews(record);
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
	
}
