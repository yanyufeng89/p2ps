package com.jobplus.controller;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.jobplus.pojo.DocComment;
import com.jobplus.pojo.Docs;
import com.jobplus.pojo.DocsLiked;
import com.jobplus.pojo.MyCollect;
import com.jobplus.pojo.OperationSum;
import com.jobplus.pojo.Page;
import com.jobplus.pojo.User;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.pojo.response.DocsResponse;
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
		String rest = "redirect:success";
		String num = "?num=";
		// 用于编辑功能
		String docId = request.getParameter("docId");
		if(!StringUtils.isBlank(docId)){//编辑
			logger.info("****文档编辑****"+docId);
			String userid = (String) request.getSession().getAttribute("userid");
			record.setId(Integer.parseInt(docId));
			record.setUserid(Integer.parseInt(userid));
			//文档原状态   用于财富值扣减
			String preIsPublic = request.getParameter("preIsPublic");
			docsService.updDocAndUpdMoney(record,preIsPublic);			
			// 更新用户操作统计数 存入session
			userService.getMyHeadTopAndOper(request);
			num += "0";
			return "redirect:modSuccess";
		}else{// 多文件上传
			//如果返回""  则num=0
			String tmp = docsService.upload(files, request, response);
			num += "".equals(tmp)?"0":tmp;
		}
		//防止重复提交   转发到静态页面   定义财富值是2
//		int num = files.length == 0 ?files.length*new AccountDetail().getCHANGEVALUES()[1]:(files.length - 1)*new AccountDetail().getCHANGEVALUES()[1];
		String tmp = rest+num;
	     return tmp;  
	  }  
	/**
	 * 判断文档是否转换成功
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/isConverted", method = RequestMethod.POST)
	@ResponseBody
	public String isConverted(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) Integer id) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			Docs record = docsService.selectByPrimaryKey(id);
			if (record.getIsconverter() != 1) {// 文档转换不成功 或者转换失败
				baseResponse.setReturnMsg("当前文档正在转换中,请稍后重试！");
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*isConverted** --文档转换不成功  或者转换失败    失败 *********");
				return JSON.toJSONString(baseResponse);
			}
			baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
			baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			return JSON.toJSONString(baseResponse);

		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*isConverted** --文档转换不成功  或者转换失败    失败  999 *********" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}

	/**
	 * 文档详情
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/getDocsDetail/{id}")
	public ModelAndView getDocsDetail(HttpServletRequest request, HttpServletResponse response,Docs record,@RequestParam(required=false) String isAdmin,@PathVariable String id) {
		ModelAndView mv = new ModelAndView();
//		Docs record = new Docs();
//		record.setId(id);
		record = docsService.getDocsDetail(record);
		if(record == null || 1!=record.getIsconverter() ){
			//转换不成功 或者没转换
			mv.setViewName("404");
			return mv;
		}
		mv.addObject("record", record);
		if("7".equals(isAdmin)){
			//后台管理员查看
			mv.setViewName("manage/documentDetail");
		}else{
			
			mv.setViewName("mydocs/docs/documentDetail");
		}
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
	public String downloadDocs(HttpServletRequest request, HttpServletResponse response,Docs doc,@RequestParam(required = true)String filePath) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			//判断文件是否可以下载
			int state = -1;
			if (filePath == null || filePath.length() <= 1) {
				baseResponse.setReturnMsg("文件url不正确");
				baseResponse.setReturnStatus(ConstantManager.INVALID_STATUS);
				return JSON.toJSONString(baseResponse);
			}
			try {
				URL url = new URL(filePath);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				state = con.getResponseCode();
				if (state != 200) {
					//URL不可用！
					baseResponse.setReturnMsg("您访问的文件不存在");
					baseResponse.setReturnStatus(ConstantManager.INVALID_STATUS);
					return JSON.toJSONString(baseResponse);
				}
			} catch (Exception ex) {
				baseResponse.setReturnMsg("您访问的文件不存在");
				baseResponse.setReturnStatus(ConstantManager.INVALID_STATUS);
				return JSON.toJSONString(baseResponse);
			}
			
			String userid = (String) request.getSession().getAttribute("userid");//doc.setDownvalue(3);"30";//
			if (!StringUtils.isBlank(userid)&&doc.getId()!=null&&doc.getDownvalue()!=null) {
				MyCollect record= new MyCollect();
				//当前登录人
				record.setUserid(Integer.parseInt(userid));
				//COLLECTTYPES = { "tbl_docs", "tbl_topics", "tbl_books", "tbl_courses", "tbl_article", "tbl_sites" };
				record.setCollecttype(record.getCOLLECTTYPES()[0]);
				record.setObjectid(doc.getId());
				
				record = docsService.downloadDoc(record,doc,request);
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
					baseResponse.setReturnMsg("财富值不足，扣减失败");
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
	@RequestMapping(value = "/modSuccess", method = RequestMethod.GET)
	public ModelAndView modSuccess(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("sharein/success/successModifyDocument");
		mv.addObject("num",request.getParameter("num"));
		return mv;
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
	 * 回收站文档删除
	 * @param request
	 * @param response
	 * @param condition
	 * @return
	 */
	@RequestMapping(value = "/gbgDelDocs", method = RequestMethod.POST)
	@ResponseBody
	public String gbgDelDocs(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=true) String condition) {
		DocsResponse DocsResponse = new DocsResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				int ret = 0;
				// 拼装成数组 "1,2,3" ——> [1,2,3]
				if (condition.length() > 0) {
					String conditions[] = condition.split(",");
					//彻底删除
					ret = docsService.gbgDelDocs(conditions,"0");
				}
				if (ret > 0) {
					logger.info("**gbgDelDocs **回收站文档    删除 ***condition==" + JSON.toJSONString(condition) + "   userid=="
							+ userid );
					//获取统计数 
					OperationSum operationSum = userService.getOperationSum(request);
					DocsResponse.setOperationSum(operationSum);
					DocsResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					DocsResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					DocsResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*******gbgDelDocs ****回收站文档    删除 * *999*****");
				}
				return JSON.toJSONString(DocsResponse);
			} else {
				DocsResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*******gbgDelDocs ***回收站文档    删除 ** 失败 999*****");
				return JSON.toJSONString(DocsResponse);
			}
		} catch (Exception e) {
			DocsResponse.setReturnMsg(e.getMessage());
			DocsResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*******gbgDelDocs***回收站文档    删除 **失败 999*****" + e.getMessage());
			return JSON.toJSONString(DocsResponse);
		}
	}
	/**
	 * 回收站文档还原
	 * @param request
	 * @param response
	 * @param condition
	 * @param ispublic
	 * @return
	 */
	@RequestMapping(value = "/gbgReBackDocs", method = RequestMethod.POST)
	@ResponseBody
	public String gbgReBackDocs(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=true) String condition,
			@RequestParam(required=true) String ispublic) {
		DocsResponse DocsResponse = new DocsResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				int ret = 0;
				// 拼装成数组 "1,2,3" ——> [1,2,3]
				if (condition.length() > 0 && ispublic.length()>0) {
					
					String conditions[] = condition.split(",");
					//文档还原
					ret = docsService.gbgReBackDocs(conditions, userid, ispublic, "1");
				}
				if (ret > 0) {
					logger.info("**gbgReBackDocs **回收站文档还原***condition==" + JSON.toJSONString(condition) + "   userid=="
							+ userid );
					// 个人操作数之类的信息放入session
					userService.getMyHeadTopAndOper(request);
					//获取统计数 
					OperationSum operationSum = userService.getOperationSum(request);
					DocsResponse.setOperationSum(operationSum);
					DocsResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					DocsResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					DocsResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*******gbgReBackDocs **回收站文档还原*999*****");
				}
				return JSON.toJSONString(DocsResponse);
			} else {
				DocsResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*******gbgReBackDocs **回收站文档还原* 失败 999*****");
				return JSON.toJSONString(DocsResponse);
			}
		} catch (Exception e) {
			DocsResponse.setReturnMsg(e.getMessage());
			DocsResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*******gbgReBackDocs**回收站文档还原*失败 999*****" + e.getMessage());
			return JSON.toJSONString(DocsResponse);
		}
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
	 *//*
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
	  }  */
	
}
