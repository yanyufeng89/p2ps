package com.jobplus.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.DocComment;
import com.jobplus.pojo.Docs;
import com.jobplus.pojo.DocsLiked;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.IDocCommentService;
import com.jobplus.service.IDocsLikedService;
import com.jobplus.service.IDocsService;
import com.jobplus.service.ITagsService;
import com.jobplus.service.ITypeConfigService;
import com.jobplus.service.IUserService;
import com.jobplus.utils.ConstantManager;

/**
 * 文档控制器
 * @author Jerry
 * 2016年6月20日上午9:42:53
 *
 */
@Controller
@RequestMapping("/docs")
public class DocsController {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(DocsController.class);
	@Resource
	private IDocsService docsService;	
	@Resource
	private IDocCommentService docCommentService;	
	@Resource
	private ITypeConfigService typeConfigService;	
	@Resource
	private ITagsService tagsService ;
	@Resource
	private IDocsLikedService docsLikedService ;
	@Resource
	private IUserService userService;
	
	
	
	/**
	 * 多文件上传         && 文档详情页的编辑
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/upload")  
	  public String upload(@RequestParam(value="fileField",required=false) MultipartFile[] files,HttpServletRequest request,HttpServletResponse response,Docs record) throws IllegalStateException, IOException {
		
		// 用于编辑功能
		String docId = request.getParameter("docId");
		if(!StringUtils.isBlank(docId)){//编辑
			record.setId(Integer.parseInt(docId));
			docsService.updateByPrimaryKeySelective(record);
		}else{// 多文件上传
			 docsService.upload(files, request, response);
		}
		//防止重复提交   转发到静态页面
		
	     return "redirect:success?num="+(files.length-1);  
	  }  
	

	/**
	 * 文档详情
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/getDocsDetail")
	public ModelAndView getDocsDetail(HttpServletRequest request, HttpServletResponse response,Docs record) {
		ModelAndView mv = new ModelAndView();
		record = docsService.getDocsDetail(record);
		if(record == null || 1!=record.getIsconverter() ){
			//转换不成功 或者没转换
			mv.setViewName("404");
			return mv;
		}
		logger.info("*****getDocsDetail 文档详情*******record=="+JSON.toJSONString(record));
		mv.addObject("record", record);
		mv.setViewName("mydocs/docs/documentDetail");
		return mv;
	}
	/**
	 * 加载更多文档评论 参数：文档ID、pageNo
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/loadComments", method = RequestMethod.POST)
	@ResponseBody
	public String loadDocComments(HttpServletRequest request, HttpServletResponse response, DocComment record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			Page<DocComment> page = docCommentService.getList(record);
			logger.info("**loadDocComments*加载更多文档评论****page==" + JSON.toJSONString(page));
			baseResponse.setObj(page);
			baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
			baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			return JSON.toJSONString(baseResponse);

		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*loadDocComments** --加载更多文档评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
	
	/**
	 * 新增文档评论          
	 * 消息参数： siteTitle  siteUrl
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	public String addComment(HttpServletRequest request, HttpServletResponse response, DocComment record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if (user!=null) {
					record.setUserid(user.getUserid());
					record = docCommentService.insertAndReturnRecord(record,request.getContextPath(),user);
				if (record != null) {
					logger.info("*addComment**--新增文档评论**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*addComment** --新增文档评论    失败  999   删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*addComment**--新增文档评论  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*addComment** --新增文档评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 删除文档评论    同时需要传递所属文档的id
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/delComment", method = RequestMethod.POST)
	@ResponseBody
	public String delComment(HttpServletRequest request, HttpServletResponse response, DocComment record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = 0;
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setUserid(Integer.parseInt(userid));
				ret = docCommentService.delComment(record);
				if (ret > 0) {
					logger.info("*delComment**--删除文档评论**record==" + JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*delComment** --删除文档评论    失败  999   删除失败*********");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*delComment**--删除文档评论  999   登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*delComment** --删除文档评论    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 文档点赞取消点赞     需要传递文档id
	 * @param request
	 * @param response
	 * @param docs
	 * @return
	 */
	@RequestMapping(value = "/clickLikeOnDoc", method = RequestMethod.POST)
	@ResponseBody
	public String clickLikeOnDoc(HttpServletRequest request, HttpServletResponse response, Docs docs) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");//new User();user.setUserid(30);//
			if (user!=null) {
				int ret = 0;

				DocsLiked record = new DocsLiked();
				record.setUserid(user.getUserid());
				record.setDocid(docs.getId());
				record.setRelationidPg(docs.getRelationidPg());
				record.setObjCreatepersonPg(docs.getObjCreatepersonPg());
				record.setObjectNamePg(docs.getObjectNamePg());
				if ("1".equals(request.getParameter("likeOperate"))){// 已点赞
					//开始取消点赞
					ret = docsLikedService.deleteByPrimaryKey(record);
					logger.info("**clickLikeOnDoc* 文档点赞取消点赞 *****取消操作取消操作取消操作  record==" + JSON.toJSONString(record));
				} else {// 没有点赞
					//开始点赞
					ret = docsLikedService.insert(record,request.getContextPath(),user);
					logger.info("**clickLikeOnDoc* 文档点赞取消点赞 *****点赞操作点赞操作点赞操作 record==" + JSON.toJSONString(record));}
				if (ret > 0) {
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("**clickLikeOnDoc* 文档点赞取消点赞  失败 999  ret=0 **");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("**clickLikeOnDoc* 文档点赞取消点赞  失败 999  登录失败**");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**clickLikeOnDoc* 文档点赞取消点赞  失败 999**" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 文档收藏、取消收藏
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/collectDocs", method = RequestMethod.POST)
	@ResponseBody
	public String collectDocs(HttpServletRequest request, HttpServletResponse response, MyCollect record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				record.setUserid(Integer.parseInt(userid));
				//COLLECTTYPES = { "tbl_docs", "tbl_topics", "tbl_books", "tbl_courses", "tbl_article", "tbl_sites" };
				record.setCollecttype(record.getCOLLECTTYPES()[0]);
				record = docsService.collectDoc(record);
				if(record!=null){
					// 更新用户操作统计数 存入session
					userService.getMyHeadTopAndOper(request);
					logger.info(
							"*collectBook** 文档收藏、取消收藏       对象ID objectId(即SiteId)  judgeTodo 1关注，0取消关注**MyCollect record=="
									+ JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				}else{
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				}				
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*collectBook** 文档收藏、取消收藏    失败  999 登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*collectBook** 文档收藏、取消收藏    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 文档下载  需要文档id,文档财富值;文档拥有者：userid
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/downloadDocs")
	@ResponseBody
	public String downloadDocs(HttpServletRequest request, HttpServletResponse response,Docs doc) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");//doc.setDownvalue(3);"30";//
			if (!StringUtils.isBlank(userid)&&doc.getId()!=null&&doc.getDownvalue()!=null) {
				MyCollect record= new MyCollect();
				//当前登录人
				record.setUserid(Integer.parseInt(userid));
				//COLLECTTYPES = { "tbl_docs", "tbl_topics", "tbl_books", "tbl_courses", "tbl_article", "tbl_sites" };
				record.setCollecttype(record.getCOLLECTTYPES()[0]);
				record.setObjectid(doc.getId());
				record = docsService.downloadDoc(record,doc);
				if(record!=null){
					// 更新用户操作统计数 存入session
					userService.getMyHeadTopAndOper(request);
					logger.info(
							"*downloadDocs** 文档下载         judgeTodo 1关注，0取消关注**MyCollect record=="
									+ JSON.toJSONString(record));
					baseResponse.setObj(record);
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				}else{
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*downloadDocs** 文档下载    失败  999 登录失败*********");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*downloadDocs** 文档下载    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
	

	
	/**
	 * 防止重复提交   转发到静态页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public ModelAndView success(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sharein/success/successUploadDocument");
		mv.addObject("num",request.getParameter("num"));
		return mv;
	}
	
	
	/**
	 * 暂时不用
	 * 多文件上传保存   
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/upload2" )  
	  public String upload2(@RequestParam("textfield") MultipartFile[] files,HttpServletRequest request,HttpServletResponse response, Model model) throws IllegalStateException, IOException {  
	      //创建一个通用的多部分解析器  		
	      CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
	      //判断 request 是否有文件上传,即多部分请求  
	      if(multipartResolver.isMultipart(request)){  
	          //转换成多部分request    
	          MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request; 
	          
	          //取得request中的所有文件名  
	          Iterator<String> iter = multiRequest.getFileNames();
	          
	         System.out.println( files);
	          
	         
	          //文档list
	          List<Docs> docsList = new ArrayList<>();
	          //财富值    
	          String downValue = request.getParameter("downvalue");
	          //单个文档
	          Docs doc = null;
	          while(iter.hasNext()){  
	              //记录上传过程起始时的时间，用来计算上传时间  
	              int pre = (int) System.currentTimeMillis();  
	              //取得上传文件  
	              MultipartFile file = multiRequest.getFile(iter.next());  
	              if(file != null){  
	                  //取得当前上传文件的文件名称  
	                  String myFileName = file.getOriginalFilename();  
	                  
	                  //如果名称不为“”,说明该文件存在，否则说明该文件不存在  
	                  if(myFileName.trim() !=""){  
	                      System.out.println(myFileName);  
	                      //重命名上传后的文件名  
	                      String fileName = file.getOriginalFilename();//"demoUpload" + file.getOriginalFilename();
	                      doc = new Docs();
	                      doc.setTitle(myFileName);//
	                      //定义上传路径  
	                      String path = "F:/" + fileName;  
	                      doc.setFilepath(path);//
	                      doc.setDownvalue(Integer.parseInt(downValue));
	                      File localFile = new File(path);  
	                      file.transferTo(localFile);  
	                  }  
	              }  
	              docsList.add(doc);
	              //记录上传该文件后的时间  
	              int finaltime = (int) System.currentTimeMillis();  
	              System.out.println(finaltime - pre);  
	          }  
	           
	          docsService.insertDocs(docsList);
	          
	      }  
	      return "sharein/upload/uploadTopic";  
	  }  
	
}
