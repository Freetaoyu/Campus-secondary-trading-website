<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>base</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link  href="bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="bootstrap-3.3.7/js/bootstrap.min.js"></script>
  </head>
<body>
base.jsp !!!!ddddddddddd basePath=<%=basePath %><br/>
base.jsp !!!!ddddddddddd path=<%=path %>


</body>
</html>