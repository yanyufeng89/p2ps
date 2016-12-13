package com.jobplus.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.SkillLibrary;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.ISensitiveWordsService;
import com.jobplus.service.ISkillLibraryService;
import com.jobplus.utils.ConstantManager;
import com.jobplus.utils.SolrJUtils;

@Controller
@RequestMapping("/skills")
public class SkillsController {
	@Resource
	private SolrJUtils solrJUtils;
	@Resource
	private ISensitiveWordsService sensitiveWordsService;
	@Resource
	private ISkillLibraryService skillLibraryService;
	
	
	/**
	 * 根据条件模糊查询人
	 * @param request
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value = "/findUser/{condition}", method = RequestMethod.POST)
	public String findUser(HttpServletRequest request,@PathVariable String condition) throws Exception {
		String userid = (String) request.getSession().getAttribute("userid");
		return solrJUtils.findUser(condition,userid);		
	}
	/**
	 * 根据条件模糊查询技能  
	 * 对于rest风格的请求，如果参数是以".js"类型后辍结尾，会被当做静态资源处理 
	 * 如果逻辑确实需要进入requestMapping的方法，则不能在web.xml中处理静态资源，需要使用<mvc:default-servlet-handler />处理静态资源，
	 * 并在requestMapping的condition上编写通配符（sping会对 .js做截取处理） @RequestMapping(value = "/findSkill/{condition:.*}", method = RequestMethod.POST)
	 * @param request
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value = "/findSkill/{condition}", method = RequestMethod.POST)
	public String findSkill (HttpServletRequest request,@PathVariable String condition) throws Exception {
		return solrJUtils.findSkill(condition);
		
	}
	/**
	 * 插入技能类
	 */
	@SuppressWarnings("static-access")
	@ResponseBody
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertTags(HttpServletRequest request, SkillLibrary record) throws Exception {
		BaseResponse response = new BaseResponse();
		try {
			int ret = 0;
			// 校验输入的是否合法
			if (!sensitiveWordsService.isSensitive(record.getSkillname())) {
				String userid = (String)request.getSession().getAttribute("userid");
				record.setCreator(Integer.parseInt(userid));
				// 合法
				ret = skillLibraryService.insert(record);
				if (ret > 0) {
					//更新标签搜索
					solrJUtils.addSkillIndexToSolr(record);
					response.setObj(record);
					response.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
					response.setReturnStatus(ConstantManager.SUCCESS_STATUS);
				} else {
					response.setReturnStatus(ConstantManager.ERROR_STATUS);
					response.setReturnStatus("技能入库失败");
				}
			} else {
				// 不合法
				response.setReturnMsg(ConstantManager.INVALID_STATUS);// -999
				response.setReturnStatus("您输入的技能不合法,请重新输入");
			}
			return JSON.toJSONString(response);
		} catch (Exception e) {
			response.setReturnMsg(e.getMessage());
			response.setReturnStatus(ConstantManager.ERROR_STATUS);
			return JSON.toJSONString(response);
		}
	}
}
