package com.jobplus.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

/**
 * 重构FreeMarkerViewResolver支持wap视图解析 读取缓存的问题
 * 
 * Title: jobplus <br>
 * Description: <br>
 * Copyright: suzhoupingjia Copyright (C) 2016 <br>
 * 
 * @author <a href="mailto:anan.wang@jobplus.com.cn">WangFei(Anan.wang)</a><br>
 * @e-mail: anan.wang@jobplus.com.cn <br>
 * @version 1.0 <br>
 * @creatdate Nov 17, 2016 10:01:16 AM <br>
 *
 */
public class FreeMarkerViewResolverUtil extends FreeMarkerViewResolver {
	@Autowired
	private HttpServletRequest request;

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		if (Common.isWapReq(request, viewName)) {
			viewName = "wap/" + viewName;
		}
		return super.resolveViewName(viewName, locale);
	}
}
