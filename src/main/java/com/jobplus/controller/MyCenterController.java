package com.jobplus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jobplus.pojo.*;
import com.jobplus.service.*;
import org.apache.commons.lang3.StringUtils;
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
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.pojo.response.DocsResponse;
import com.jobplus.pojo.response.MyCollectResponse;
import com.jobplus.utils.ConstantManager;

/**
 * 个人中心
 * 
 * @author Jerry 2016年6月23日下午4:42:59
 *
 */
@Controller
@RequestMapping("/myCenter")
public class MyCenterController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(MyCenterController.class);
	@Resource
	private IDocsService docsService;
	@Resource
	private ITopicsService topicsService;
	@Resource
	private IUserService userService;
	@Resource
	private IMyCollectService myCollectService;	
	@Resource
	private ITypeConfigService typeConfigService;
	@Resource
	private IAttentionService attentionService;
	@Resource
	private ITopicsCommentService topicsCommentService;
	@Resource
	private ITopicsLikedService topicsLikedService;
	@Resource
	private IOperationSumService operationSumService;
	@Resource
	private IReportInfoService reportInfoService;
	@Resource
	private IOauthLoginInfoService oauthLoginInfoService;
	
	/**
	 * 个人中心-我上传的文档   列表
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMyDocsUploaded")
	@ResponseBody
	public ModelAndView getMyDocsUploaded(@RequestHeader("Accept") String encoding,HttpServletRequest request,Docs record) throws Exception {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		record.setUserid(Integer.parseInt(userid));
		//如果前端不传值过来 默认传到页面为1
		record.setIsvalid(record.getIsvalid()==null?1:record.getIsvalid());
		Page<Docs> docsPage =  new Page<>();
		if(1!=record.getIsvalid()){
			//回收站文档列表
			docsPage = docsService.getGbgDocs(record);
			logger.info("getGbgDocs 回收站文档列表   列表 :" + JSON.toJSONString(docsPage));
		}else{
			//公开、私有文档列表
			docsPage = docsService.getMyDocsUploaded(record);
			logger.info("getMyDocsUploaded 个人中心-我上传的文档   列表 :" + JSON.toJSONString(docsPage));
		}
		if (encoding.indexOf("application/json") != -1) {
			// 分页 post请求
			Map<String, Page<Docs>> map = new HashMap<String, Page<Docs>>();
			map.put("docsPage", docsPage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			mv.addObject("docsPage", docsPage);
			mv.addObject("ispublic", record.getIspublic() == null ? 1 : record.getIspublic());
			
			mv.addObject("record", record);
			mv.setViewName("mydocs/docs/mydocument");
			return mv;
		}
	}
	
	/**
	 * 个人中心-我收藏的&&我下载的文档  列表 
	 * @param request　
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMyDocsCollected")
	public ModelAndView getMyDocsCollected(@RequestHeader("Accept") String encoding,HttpServletRequest request,MyCollect record) throws Exception {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		// 设置userId
		record.setUserid(Integer.parseInt(userid));
		// 设置 收藏还是下载
		record.setActionType(record.getACTIONTYPES()[1]);
		// 设置表名
		record.setCollecttype(record.getCOLLECTTYPES()[0]);
		if(record.getActionType()!=null && !StringUtils.isBlank(record.getCollecttype())){
			Page<MyCollect> myCollectPage = myCollectService.getMyDocsList(record);	
			logger.info("getMyDocsCollected 个人中心-我收藏的&&我下载的文档  列表:   PageNo=="+record.getPageNo()+"***myCollectPage===" + JSON.toJSONString(myCollectPage));
			if (encoding.indexOf("application/json") != -1) {				
				//分页   post请求
				Map<String, Page<MyCollect>> map = new HashMap<String, Page<MyCollect>>();
				map.put("myCollectPage", myCollectPage);
				return new ModelAndView(new MappingJackson2JsonView(), map);
			} else {
//				// 获取统计数
//				OperationSum operationSum = getOperationSum(request);
//				logger.info("getMyDocsCollected 个人中心-我收藏的&&我下载的文档   operationSum:" + JSON.toJSONString(operationSum));
//				mv.addObject("operationSum", operationSum);
				mv.addObject("myCollectPage", myCollectPage);
				mv.setViewName("mydocs/docs/mydocument");
				return mv;
			}
		}else{
			//请求出错
			mv.setViewName("mydocs/docs/mydocument");
			return mv;
		}
	}
	/**
	 * 个人中心-我收藏的&&我下载的文档   列表
	 * @param request
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMyDocsDowned")
	public ModelAndView getMyDocsDowned(@RequestHeader("Accept") String encoding,HttpServletRequest request,MyCollect record) throws Exception {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		// 设置userId
		record.setUserid(Integer.parseInt(userid));
		// 设置 收藏还是下载
		record.setActionType(record.getACTIONTYPES()[0]);
		// 设置表名
		record.setCollecttype(record.getCOLLECTTYPES()[0]);		
		Page<MyCollect> myDownloadPage = myCollectService.getMyDocsList(record);
		
		logger.info("getMyDocsDowned 个人中心-我收藏的&&我下载的文档   列表 myDownloadList:   PageNo=="+record.getPageNo()+"***myDownloadPage===" +  JSON.toJSONString(myDownloadPage));
		if (encoding.indexOf("application/json") != -1) {				
			//分页   post请求
			Map<String, Page<MyCollect>> map = new HashMap<String, Page<MyCollect>>();
			map.put("myDownloadPage", myDownloadPage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			mv.addObject("myDownloadPage", myDownloadPage);
			mv.setViewName("mydocs/docs/mydocument");
			return mv;
		}
	}
	
	/**
	 * 收藏和下载记录 删除
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/deleteMyCollects", method = RequestMethod.POST)
	@ResponseBody
	public String deleteMyCollects(HttpServletRequest request, HttpServletResponse response, MyCollect record) {
		MyCollectResponse myCollectResponse = new MyCollectResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid) && record.getCondition() != null) {
				record.setUserid(Integer.parseInt(userid));
				int ret = 0;
				// 拼装成数组 "1:11,2:22,3:33" ——> [1,2,3] [11,22,33]
				// 或者是 "1,2,3" ——> [1,2,3]
				if (record.getCondition().length() > 0) {
					String conditions[] = record.getCondition().split(",");
					// // 书籍ID 暂时定义长度
					// String bookIds[] = new String[conditions.length];
					// for (int i = 0; i < conditions.length; i++) {
					// bookIds[i] = conditions[i].split(":")[1];
					// conditions[i] = conditions[i].split(":")[0];
					// }
					ret = myCollectService.deleteMycollects(conditions, record);
					logger.info("**************deleteMyCollects收藏和下载记录 删除**************************");
				}
				if (ret > 0) {
					// 个人操作数之类的信息放入session
					userService.getMyHeadTopAndOper(request);
					//获取统计数 
					OperationSum operationSum = userService.getOperationSum(request);
					myCollectResponse.setOperationSum(operationSum);

					myCollectResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					myCollectResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					myCollectResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("******deleteMyCollects 收藏和下载记录 删除失败   999 ************");
				}
				return JSON.toJSONString(myCollectResponse);
			} else {
				myCollectResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("******deleteMyCollects 收藏和下载记录 删除失败   999 ************");
				return JSON.toJSONString(myCollectResponse);
			}
		} catch (Exception e) {
			myCollectResponse.setReturnMsg(e.getMessage());
			myCollectResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("******deleteMyCollects 收藏和下载记录 删除失败   999 抛出异常************" + e.getMessage());
			return JSON.toJSONString(myCollectResponse);
		}
	}
	
	/**
	 * 获取用户简单信息     
	 * @param request
	 * @param response
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getUserSimpleInformation")
	@ResponseBody
	public String getUserSimpleInformation(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=true) String userId) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			if (StringUtils.isNotBlank(userId)) {
				User user = userService.getUserSimpleInformation(Integer.parseInt(userId));
				String currentUserid = (String) request.getSession().getAttribute("userid");
				logger.info("**getUserSimpleInformation获取用户简单信息 **user==" + JSON.toJSONString(user)
						+ "   currentUserid==" + currentUserid);
				baseResponse.setUser(user);
				baseResponse.setCurrentUserid(currentUserid);
				baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
				baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("******getUserSimpleInformation 失败 999*******");
			}
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("******getUserSimpleInformation 失败 999*******" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	
		
	/**
	 * 获取文档详情用以编辑
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/getDocsDetailForEdit")
	public ModelAndView getDocsDetailForEdit(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=false) String docId) {
		ModelAndView mv = new ModelAndView();
		Docs record = docsService.selectByPrimaryKey(Integer.parseInt(docId));

		// 标签的所有父节点
		List<TypeConfig> parentTagsList = typeConfigService.getAllParentTypeConfigs();
		String parentTypeId;
		try {
			// 300:初中教育,302:物理化学 获取300
			parentTypeId = record.getDoctype().split(",")[0].split(":")[0];
		} catch (Exception e) {
			// 出错获取默认值 ：第一个父节点
			parentTypeId = parentTagsList.get(0).getTypeid().toString();
		}
		// 对应标签的子节点
		List<TypeConfig> childTagsList = typeConfigService.getChildrenTypeConfigs( parentTypeId);
		logger.info("*getDocsDetailForEdit*获取文档详情用以编辑***record*"+JSON.toJSONString(record));
		logger.info("**获取文档详情用以编辑***parentTagsList*"+JSON.toJSONString(parentTagsList));
		logger.info("*getDocsDetailForEdit*获取文档详情用以编辑***childTagsList*"+JSON.toJSONString(childTagsList));		
		
		mv.addObject("record", record);
		mv.addObject("parentTagsList", parentTagsList);
		mv.addObject("childTagsList", childTagsList);
		mv.setViewName("sharein/editshare/editDocument");
		return mv;
	}
	/**
	 * 文档编辑提交
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/editDocs")
	@ResponseBody
	public String editDocs(HttpServletRequest request, HttpServletResponse response, Docs record) {
		DocsResponse DocsResponse = new DocsResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				int ret = docsService.updateByPrimaryKeySelective(record);
				if (ret > 0) {
					logger.info("*******editDocs*文档编辑提交************" + JSON.toJSONString(record));
					DocsResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					DocsResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					DocsResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*******editDocs*文档编辑提交失败 999   ************");
				}
				return JSON.toJSONString(DocsResponse);
			} else {
				DocsResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				return JSON.toJSONString(DocsResponse);
			}
		} catch (Exception e) {
			DocsResponse.setReturnMsg(e.getMessage());
			DocsResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*****editDocs***文档编辑提交失败 999   ************" + e.getMessage());
			return JSON.toJSONString(DocsResponse);
		}
	}
	
	/**
	 * 文档删除 逻辑删除
	 * @param request
	 * @param response
	 * @param condition
	 * @param ispublic
     * @param delStatus 设定为2    删除状态 ：0 彻底删除  2 放入回收站   若为空 放入回收站
	 * @return
	 */
	@RequestMapping(value = "/deleteDocs", method = RequestMethod.POST)
	@ResponseBody
	public String deleteDocs(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=false) String condition,
			@RequestParam(required=false) String ispublic,@RequestParam(required=false) String delStatus,@RequestParam(required=false) String isvalid) {
		DocsResponse DocsResponse = new DocsResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				int ret = 0;
				// 拼装成数组 "1,2,3" ——> [1,2,3]
				if (condition.length() > 0) {
					String conditions[] = condition.split(",");
					//回收站文档删除
					if("2".equals(isvalid)){
						ret = docsService.gbgDelDocs(conditions,"0");
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
					}
					
					delStatus = "2";
					ret = docsService.deleteDocs(conditions, userid,ispublic,delStatus);
				}
				if (ret > 0) {
					// 个人操作数之类的信息放入session
					userService.getMyHeadTopAndOper(request);
					//获取统计数 
					OperationSum operationSum = userService.getOperationSum(request);
					DocsResponse.setOperationSum(operationSum);
					logger.info("**deleteDocs**文档删除 逻辑删除***condition==" + JSON.toJSONString(condition) + "   userid=="
							+ userid + "  operationSum==" + JSON.toJSONString(operationSum) + "*****");
					DocsResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					DocsResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					DocsResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*******deleteDocs 文档删除 逻辑删除 失败 999*****");
				}
				return JSON.toJSONString(DocsResponse);
			} else {
				DocsResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*******deleteDocs 登录失败 文档删除 逻辑删除 失败 999*****");
				return JSON.toJSONString(DocsResponse);
			}
		} catch (Exception e) {
			DocsResponse.setReturnMsg(e.getMessage());
			DocsResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*******deleteDocs 文档删除 逻辑删除 失败 999*****" + e.getMessage());
			return JSON.toJSONString(DocsResponse);
		}
	}
	
	/**
	 * 话题删除    逻辑删除
	 * @param request
	 * @param response
	 * @param condition
	 * @return
	 */
	@RequestMapping(value = "/deleteTopics", method = RequestMethod.POST)
	@ResponseBody
	public String deleteTopics(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=false) String condition) {
		BaseResponse basesResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				int ret = 0;
				// 拼装成数组 "1,2,3" ——> [1,2,3]
				if (condition.length() > 0) {
					String conditions[] = condition.split(",");
					ret = topicsService.deleteTopics(conditions, userid);
				}
				if (ret > 0) {
					// 个人操作数之类的信息放入session
					userService.getMyHeadTopAndOper(request);
					//获取统计数 
					OperationSum operationSum = userService.getOperationSum(request);
					basesResponse.setOperationSum(operationSum);
					logger.info("**deleteTopics**话题删除    逻辑删除***condition==" + JSON.toJSONString(condition)
							+ "   userid==" + userid + "  operationSum==" + JSON.toJSONString(operationSum) + "*****");
					basesResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					basesResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					basesResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*******deleteTopics 话题删除    逻辑删除 失败 999   ret=0*****");
				}
				return JSON.toJSONString(basesResponse);
			} else {
				basesResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*******deleteTopics 话题删除    逻辑删除 失败 999 登录失败*****");
				return JSON.toJSONString(basesResponse);
			}
		} catch (Exception e) {
			basesResponse.setReturnMsg(e.getMessage());
			basesResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*******deleteTopics 话题删除    逻辑删除 失败 999*****" + e.getMessage());
			return JSON.toJSONString(basesResponse);
		}	
	}	
	
	/**
	 * 个人中心-我发布的话题
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMyTopicsUploaded")
	public ModelAndView getMyTopicsUploaded(@RequestHeader("Accept") String encoding,HttpServletRequest request,Topics record) throws Exception {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		record.setCreateperson(Integer.parseInt(userid));

		Page<Topics> topicsPage = topicsService.getMyTopicsUploaded(record);

		if (encoding.indexOf("application/json") != -1) {
			// 分页 post请求
			Map<String, Page<Topics>> map = new HashMap<String, Page<Topics>>();
			map.put("topicsPage", topicsPage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		} else {
			mv.addObject("topicsPage", topicsPage);
			mv.setViewName("mydocs/docs/mytopic");
			return mv;
		}		
	}
	/**
	 * 个人中心--我关注的话题
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMyTopicsAttention")
	public ModelAndView getMyTopicsAttention(@RequestHeader("Accept") String encoding,HttpServletRequest request,Attention attention) throws Exception {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		attention.setUserid(Integer.parseInt(userid));
		// 枚举 用表名
		attention.setColltype(attention.getCOLLTYPES()[1]);

		Page<Attention> attentionPage = attentionService.getMyAttentionList(attention);
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			Map<String, Page<Attention>> map = new HashMap<String, Page<Attention>>();
			map.put("attentionPage", attentionPage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		}else{
//			//获取统计数  
//			OperationSum operationSum = getOperationSum(request);
//			mv.addObject("operationSum", operationSum);

//			logger.info("*getMyTopicsAttention**个人中心--我关注的话题****attentionPage=="+JSON.toJSONString(attentionPage)+"   operationSum=="+JSON.toJSONString(operationSum));
			mv.addObject("attentionPage", attentionPage);
			mv.setViewName("mydocs/docs/mytopic");
			return mv;
		}
	}
	
	/**
	 * 个人中心--批量取消关注     （删除话题关注）
	 * @param request
	 * @param response
	 * @param condition
	 * @return
	 */
	@RequestMapping(value = "/deleteAttentions", method = RequestMethod.POST)
	@ResponseBody
	public String deleteAttentions(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=true) String condition) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if(!StringUtils.isBlank(userid)){
				int ret = 0;				
				//拼装成数组     "1,2,3" ——>  [1,2,3]
				if(condition.length()>0){
					String conditions[] = condition.split(",");
					ret = attentionService.deleteAttentions(conditions,userid);
				}				
				if(ret > 0){
					//个人操作数之类的信息放入session
					userService.getMyHeadTopAndOper(request);
					//获取统计数 
					OperationSum operationSum = userService.getOperationSum(request);	
					baseResponse.setOperationSum(operationSum);
					logger.info("***deleteAttentions**个人中心--批量取消关注     （删除话题关注）******condition=="+JSON.toJSONString(condition)+"  operationSum== "+JSON.toJSONString(operationSum));
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				}else{
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("***deleteAttentions**个人中心--批量取消关注     （删除话题关注） 失败  999  ret=0*****");
				}				
				return JSON.toJSONString(baseResponse);
			}else{
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("***deleteAttentions**个人中心--批量取消关注     （删除话题关注）  失败  登录失败  999*****");
				return JSON.toJSONString(baseResponse);
			}			
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("***deleteAttentions**个人中心--批量取消关注     （删除话题关注） 失败  999*****"+e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
		
	}
	
	/**
	 * 个人中心--我回复过的话题
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getMyTopicsComment")
	public ModelAndView getMyTopicsComment(@RequestHeader("Accept") String encoding,HttpServletRequest request,TopicsComment record ) throws Exception {
		ModelAndView mv = new ModelAndView();
		String userid = (String) request.getSession().getAttribute("userid");
		record.setUserid(Integer.parseInt(userid));
		Page<TopicsComment> topicsCommentPage = topicsCommentService.getMyTopicsComments(record);
		if (encoding.indexOf("application/json") != -1) {
			//分页   post请求
			Map<String, Page<TopicsComment>> map = new HashMap<String, Page<TopicsComment>>();
			map.put("topicsCommentPage", topicsCommentPage);
			return new ModelAndView(new MappingJackson2JsonView(), map);
		}else{
			//获取统计数  
//			OperationSum operationSum = getOperationSum(request);
//			logger.info("**getMyTopicsComment*个人中心--我回复过的话题**topicsCommentPage=="+JSON.toJSONString(topicsCommentPage)+"  operationSum=="+JSON.toJSONString(operationSum));
//			mv.addObject("operationSum", operationSum);
			mv.addObject("topicsCommentPage", topicsCommentPage);
			mv.setViewName("mydocs/docs/mytopic");
			return mv;
		}
		

	}
	/**
	 * 个人中心--批量删除我的回复
	 * @param request
	 * @param response
	 * @param condition
	 * @return
	 */
	@RequestMapping(value = "/deleteTopicsComments", method = RequestMethod.POST)
	@ResponseBody
	public String deleteTopicsComments(HttpServletRequest request, HttpServletResponse response,@RequestParam(required=false) String condition) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if(!StringUtils.isBlank(userid)){
				int ret = 0;				
				//拼装成数组     "1,2,3" ——>  [1,2,3]
				if(condition.length()>0){
					String conditions[] = condition.split(",");
					ret = topicsCommentService.deleteTopicsComments(conditions,userid);
				}				
				if(ret > 0){
					//个人操作数之类的信息放入session
					userService.getMyHeadTopAndOper(request);
					//获取统计数 
					OperationSum operationSum = userService.getOperationSum(request);// getOperationSum(request);		
					baseResponse.setOperationSum(operationSum);
					logger.info("***deleteTopicsComments**个人中心--批量删除我的回复******condition=="+JSON.toJSONString(condition));
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				}else{
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("****deleteTopicsComments*个人中心--批量删除我的回复 失败  999  ret=0**********");
				}				
				return JSON.toJSONString(baseResponse);
			}else{
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("****deleteTopicsComments*个人中心--批量删除我的回复 失败  999 登录失败**********");
				return JSON.toJSONString(baseResponse);
			}			
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("****deleteTopicsComments*个人中心--批量删除我的回复 失败  999**********"+e.getMessage());
			return JSON.toJSONString(baseResponse);
		}		
	}
	
	
	/**
	 * 话题编辑
	 * @param request
	 * @param response
	 * @param topic
	 * @return
	 */
	@RequestMapping(value = "/updateTopics", method = RequestMethod.POST)
	@ResponseBody
	public String updateTopics(HttpServletRequest request, HttpServletResponse response, Topics topic) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if (!StringUtils.isBlank(userid)) {
				int ret = 0;
				ret = topicsService.updateByPrimaryKeySelective(topic);
				logger.info("**updateTopics* 话题编辑 **topic==" + JSON.toJSONString(topic));
				if (ret > 0) {
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("**updateTopics* 话题编辑  失败 999  ret=0**");
				}
				return JSON.toJSONString(baseResponse);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("**updateTopics* 话题编辑  失败 999 登录失败**");
				return JSON.toJSONString(baseResponse);
			}
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**updateTopics* 话题编辑  失败 999**" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}
	/**
	 * 点赞取消点赞         topicsComment     isLiked为0后者null执行点赞操作，为1执行取消点赞操作
	 * @param request
	 * @param response
	 * @param topicsComment
	 * @return
	 */
	@RequestMapping(value = "/clickOnLike", method = RequestMethod.POST)
	@ResponseBody
	public String clickOnLike(HttpServletRequest request, HttpServletResponse response,TopicsComment topicsComment) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if (user!=null) {
				int ret = 0;				
				TopicsLiked record = new TopicsLiked();
				record.setCommid(topicsComment.getId());
				record.setUserid(user.getUserid());
				record.setObjCreatepersonPg(topicsComment.getObjCreatepersonPg());
				record.setObjectNamePg(topicsComment.getObjectNamePg());
				record.setRelationidPg(topicsComment.getRelationidPg());
				record.setTopObjId(topicsComment.getTopObjId());
				if("0".equals(request.getParameter("likeOperate"))){//没有点赞
					//开始点赞
					ret = topicsLikedService.insert(record,request.getContextPath(),user);
					logger.info("**clickOnLike* 点赞取消点赞 *****点赞操作点赞操作点赞操作 record=="+JSON.toJSONString(record));
				}else{//已点赞
					//取消点赞
					ret = topicsLikedService.deleteByPrimaryKey(record);
					logger.info("**clickOnLike* 点赞取消点赞 *****取消操作取消操作取消操作  record=="+JSON.toJSONString(record));
				}				
				if(ret > 0){					
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				}else{
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("**clickOnLike* 点赞取消点赞  失败 999  ret=0 **");
				}				
				return JSON.toJSONString(baseResponse);
			}else{
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("**clickOnLike* 点赞取消点赞  失败 999  登录失败**");
				return JSON.toJSONString(baseResponse);
			}			
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**clickOnLike* 点赞取消点赞  失败 999**"+e.getMessage());
			return JSON.toJSONString(baseResponse);
		}		
	}	
	
	/**
	 * 关注取消关注        对象类型 objectType 0:用户  1：话题;对象ID objectid,关注对象的id     actionType 1关注，0取消关注
	 * 关注  需要大改 FIXME 关注  需要大改     关注对象id  由objectId  改为 objectid
	 * @param request
	 * @param response
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/addFollows", method = RequestMethod.POST)
	@ResponseBody
	public String addFollows(HttpServletRequest request, HttpServletResponse response,Attention record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User)request.getSession().getAttribute("user");
			if (user!=null) {
				int ret = 0;	
				ret = attentionService.addFollows(record,request.getContextPath(),user);							
				if(ret > 0){	
					//个人操作数之类的信息放入session
					userService.getMyHeadTopAndOper(request);
					logger.info("*addFollows** 关注取消关注        对象类型 objectType 0:用户  1：话题,对象ID objectId,关注fcl作  actionType 1关注，0取消关注**");
					logger.info("*addFollows** objectType=="+record.getObjectType());
					logger.info("*addFollows** objectid=="+record.getObjectid());
					logger.info("*addFollows** actionType=="+record.getActionType());
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				}else{
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("*addFollows** 关注取消关注    失败  999 ret=0*********");
				}				
				return JSON.toJSONString(baseResponse);
			}else{
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("*addFollows** 关注取消关注    失败  999 登录失败*********");
				return JSON.toJSONString(baseResponse);
			}			
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*addFollows** 关注取消关注    失败  999 *********"+e.getMessage());
			return JSON.toJSONString(baseResponse);
		}		
	}	
	
	
	/**
	 * 举报      "tbl_user","tbl_article","tbl_books","tbl_courses","tbl_docs","tbl_topics","tbl_topics_comment" REPORTTYPE_INDEX对应枚举数据的下标
	 * @param request
	 * @param response
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/reportReportInfo")
	@ResponseBody
	public String reportReportInfo(HttpServletRequest request, HttpServletResponse response,ReportInfo record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			String userid = (String) request.getSession().getAttribute("userid");
			if(!StringUtils.isBlank(userid)){
				int ret = 0;	
				ret = reportInfoService.insert(record);
				if(ret > 0){					
					logger.info("**reportReportInfo*举报****record=="+JSON.toJSONString(record));
					baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				}else{
					baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
					logger.info("**reportReportInfo*举报  失败   999  ret=0****");
				}				
				return JSON.toJSONString(baseResponse);
			}else{
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				logger.info("**reportReportInfo*举报  失败   999登录失败****");
				return JSON.toJSONString(baseResponse);
			}			
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**reportReportInfo*举报  失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}		
	}
	
	
	/**
	 * 获取当前登录用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getCurrentUser", method = RequestMethod.POST)
	@ResponseBody
	public String getCurrentUser(HttpServletRequest request, HttpServletResponse response) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			User user = (User) request.getSession().getAttribute("user");
//			logger.info("*getCurrentUser**获取当前登录用户信息****user=="+JSON.toJSONString(user));
			baseResponse.setUser(user);
			baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
			baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("*getCurrentUser**获取当前登录用户信息  失败  999****"+e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}


	/**
	 * 帐号安全
	 * @return
	 */
	@RequestMapping(value = "/account/security")
	public ModelAndView accountsecurity(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String userId = (String) request.getSession().getAttribute("userid");
		List<OauthLoginInfo> list = oauthLoginInfoService.findByUserId(Integer.parseInt(userId));
		mv.addObject("thirdInfos", list);
		mv.setViewName("/mydocs/mycount/accountsecurity");
		return mv;
	}
}
