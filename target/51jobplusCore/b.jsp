<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>session</title>
</head>
<body>
<%String s = session.getId();  %>
 <%=s %>
 <br/>
 <br/>
<%=(String)session.getAttribute("userid")%>

</body>
</html>