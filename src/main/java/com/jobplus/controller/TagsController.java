package com.jobplus.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.Tags;
import com.jobplus.pojo.TypeConfig;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.pojo.response.TypeConfigResponse;
import com.jobplus.service.ITagsService;
import com.jobplus.service.ITypeConfigService;
import com.jobplus.utils.ConstantManager;
import com.jobplus.utils.SolrJUtils;


/**
 * 标签控制器
 * @author Jerry
 * 2016年6月20日下午4:29:31
 *
 */
@Controller
@RequestMapping("/tags")
public class TagsController {
	
	@Resource
	private ITypeConfigService typeConfigService;	
	@Resource
	private ITagsService tagsService ;
	@Resource
	private SolrJUtils solrJUtils;
	
	/**
	 * 获取所有父节点
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getAllParentTypeConfigs", method = RequestMethod.POST)
	@ResponseBody
	public String getAllParentTypeConfigs(HttpServletRequest request) throws Exception {
		
		TypeConfigResponse typeConfigResponse = new TypeConfigResponse();
		try {
			List<TypeConfig> list = typeConfigService.getAllParentTypeConfigs();
			typeConfigResponse.setTypeConfigList(list);
			typeConfigResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
			typeConfigResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			return JSON.toJSONString(typeConfigResponse);
		} catch (Exception e) {
			typeConfigResponse.setReturnMsg(e.getMessage());
			typeConfigResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			return JSON.toJSONString(typeConfigResponse);
		}		
		
	}
	/**
	 * 根据父节点ID获取子节点
	 * @param request
	 * @param parentTagId
	 * @return
	 */
	@RequestMapping(value = "/getChildTypeConfigsByParent", method = RequestMethod.POST)
	@ResponseBody
	public String getChildrenTypeConfigs(HttpServletRequest request,@RequestParam(required=false) String parentTypeId) throws Exception{		
		TypeConfigResponse typeConfigResponse = new TypeConfigResponse();
		try {
			
			List<TypeConfig> list = typeConfigService.getChildrenTypeConfigs(parentTypeId);
			typeConfigResponse.setTypeConfigList(list);
			typeConfigResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
			typeConfigResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			return JSON.toJSONString(typeConfigResponse);
		} catch (Exception e) {
			typeConfigResponse.setReturnMsg(e.getMessage());
			typeConfigResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			return JSON.toJSONString(typeConfigResponse);
		}
		
	}
	
	/**
	 * 根据条件模糊查询标签 
	 * @param request
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value = "/findClass/{condition}", method = RequestMethod.POST)
	public String findAllTopClass(HttpServletRequest request,@PathVariable String condition) throws Exception {
		return solrJUtils.findTags(condition);
		
	}
	
	/**
	 * 插入自己输入的标签 
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value = "/insertTags", method = RequestMethod.POST)
	public String insertTags(HttpServletRequest request, Tags record) throws Exception {
		BaseResponse response = new BaseResponse();
		try {
			int ret = 0;
			// 校验输入的是否合法
			ret = tagsService.isValid(request,record);
			if (ret > 0) {
				String userid = (String)request.getSession().getAttribute("userid");
				record.setCreator(Integer.parseInt(userid));
				// 合法
				ret = tagsService.insert(record);
				if (ret > 0) {
					//更新标签搜索
					solrJUtils.addTagsIndexToSolr(record);
					response.setObj(record);
					response.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					response.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					response.setReturnStatus(ConstantManager.ERROR_STATUS);
					response.setReturnStatus("入库失败");
				}
			} else {
				// 不合法
				response.setReturnMsg(ConstantManager.INVALID_STATUS);// -999
				response.setReturnStatus("您输入的标签不合法,请重新输入");
			}
			return JSON.toJSONString(response);
		} catch (Exception e) {
			response.setReturnMsg(e.getMessage());
			response.setReturnStatus(ConstantManager.ERROR_STATUS);
			return JSON.toJSONString(response);
		}
	}
	
}
