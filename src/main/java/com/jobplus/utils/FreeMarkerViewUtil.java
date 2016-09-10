package com.jobplus.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 视图解析器(FreeMarkerViewUtil.java),
 * 继承自org.springframework.web.servlet.view.freemarker.FreeMarkerView在这里对原类进行扩展
 * Title: jobplus <br>
 * Description: <br>
 * Copyright: suzhoupingjia Copyright (C) 2016 <br>
 * 
 * @author <a href="mailto:anan.wang@jobplus.com.cn">WangFei(Anan.wang)</a><br>
 * @e-mail: anan.wang@jobplus.com.cn <br>
 * @version 1.0 <br>
 * @creatdate Jul 30, 2016 4:53:15 PM <br>
 *
 */
public class FreeMarkerViewUtil extends FreeMarkerView {
    @Override
    protected void doRender(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // Expose model to JSP tags (as request attributes).
        exposeModelAsRequestAttributes(model, request);
        // Expose all standard FreeMarker hash models.
        SimpleHash fmModel = buildTemplateModel(model, request, response);

        if (logger.isDebugEnabled()) {
            logger.debug("Rendering FreeMarker 模版 [" + getUrl() + "] in FreeMarkerView '" + getBeanName() + "'");
        }
        // Grab the locale-specific version of the template.
        Locale locale = RequestContextUtils.getLocale(request);

        /*
         * 默认不生成静态文件,除非在编写ModelAndView时指定CREATE_HTML = true, 这样对静态文件生成的粒度控制更细一点
         * 例如：ModelAndView mav = new ModelAndView("search");
         * mav.addObject("CREATE_HTML", true);
         */
        if (Boolean.TRUE.equals(model.get("CREATE_HTML"))) {
            createHTML(getTemplate(locale), fmModel, request, response);
        } else {
        	processTemplate(getTemplate(locale), fmModel, response);
        }
    }

    public void createHTML(Template template, SimpleHash model, HttpServletRequest request,
            HttpServletResponse response) throws IOException, TemplateException, ServletException {
        // 站点根目录的绝对路径
        String basePath = request.getSession().getServletContext().getRealPath("/");
        String requestHTML = this.getRequestHTML(request);
        // 静态页面绝对路径
        String htmlPath = basePath + requestHTML;
        htmlPath = htmlPath.replaceAll("\\\\", "/");
        System.out.println("静态页面绝对路径===========>>:"+htmlPath);
        File htmlFile = new File(htmlPath);
        if (!htmlFile.getParentFile().exists()) {
            htmlFile.getParentFile().mkdirs();
        }
        if (!htmlFile.exists()) {
            htmlFile.createNewFile();
        }
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), "UTF-8"));
        // 处理模版
        template.process(model, out);
        out.flush();
        out.close();
        /* 将请求转发到生成的htm文件 */
        request.getRequestDispatcher(requestHTML).forward(request, response);
    }

    /**
     * 计算要生成的静态文件相对路径 因为大家在调试的时候一般在Tomcat的webapps下面新建站点目录的，
     * 但在实际应用时直接布署到ROOT目录里面,这里要保证路径的一致性。
     * 
     * @param request
     *            HttpServletRequest
     * @return /目录/*.html
     */
    private String getRequestHTML(HttpServletRequest request) {
    	String servletPath =   	request.getServletPath() ;
        String requestURI = servletPath+".html"; 
        return requestURI;
    }
}
