<%@ page language="java" contentType="text/html; charset=utf-8"
             pageEncoding="utf-8"%>
<%@ page import="com.jobplus.utils.Uploader" %>
<%@ page import="org.springframework.web.context.support.*"%>    
<%@ page import="org.springframework.context.*" %>  

<%
    request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());    
    Uploader up = (Uploader)ctx.getBean("uploader");
    up.setRequest(request);
    up.setSavePath("upload");
    String[] fileType = {".gif" , ".png" , ".jpg" , ".jpeg" , ".bmp"};
    up.setAllowFiles(fileType);
    up.setMaxSize(10000); //单位KB
    up.upload();

    String callback = request.getParameter("callback");

    String result = "{\"name\":\""+ up.getFileName() +"\", \"originalName\": \""+ up.getOriginalName() +"\", \"size\": "+ up.getSize() +", \"state\": \""+ up.getState() +"\", \"type\": \""+ up.getType() +"\", \"url\": \""+ up.getUrl() +"\"}";

    result = result.replaceAll( "\\\\", "\\\\" );

    if( callback == null ){
        response.getWriter().print( result );
    }else{
        response.getWriter().print("<script>"+ callback +"(" + result + ")</script>");
    }
%>
