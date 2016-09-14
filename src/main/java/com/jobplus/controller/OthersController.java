package com.jobplus.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.jobplus.pojo.Suggestion;
import com.jobplus.pojo.response.BaseResponse;
import com.jobplus.service.ISuggestionService;
import com.jobplus.utils.ConstantManager;

@Controller
public class OthersController {

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(OthersController.class);
	@Resource
	private ISuggestionService suggestionService;

	@RequestMapping(value = "/suggestion/add", method = RequestMethod.POST)
	@ResponseBody
	public String addSug(HttpServletRequest request, HttpServletResponse response, Suggestion record) {
		BaseResponse baseResponse = new BaseResponse();
		try {
			int ret = suggestionService.insert(record);
			if (ret > 0) {
				baseResponse.setReturnMsg(ConstantManager.SUCCESS_MESSAGE);
				baseResponse.setReturnStatus(ConstantManager.SUCCESS_STATUS);
			} else {
				baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
				baseResponse.setReturnMsg("入库失败");
			}
			return JSON.toJSONString(baseResponse);
		} catch (Exception e) {
			baseResponse.setReturnMsg(e.getMessage());
			baseResponse.setReturnStatus(ConstantManager.ERROR_STATUS);
			logger.info("**建议反馈添加*      失败   999****" + e.getMessage());
			return JSON.toJSONString(baseResponse);
		}
	}

}
