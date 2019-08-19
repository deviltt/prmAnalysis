<%--
  Created by IntelliJ IDEA.
  User: tt416
  Date: 2019/8/16
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>所有的演示例子</h1>
<h3><a href="${pageContext.request.contextPath}/daodb">处理dao中数据库异常</a></h3>
<h3><a href="${pageContext.request.contextPath}/daomy">处理dao中自定义异常</a></h3>
<h3><a href="${pageContext.request.contextPath}/daono">处理dao中未知异常</a></h3>
<h3><a href="${pageContext.request.contextPath}/servicedb">处理service中数据库异常</a></h3>
<h3><a href="${pageContext.request.contextPath}/servicemy">处理service中自定义异常</a></h3>
<h3><a href="${pageContext.request.contextPath}/serviceno">处理service中未知异常</a></h3>
<h3><a href="${pageContext.request.contextPath}/db">处理controller数据库异常</a></h3>
<h3><a href="${pageContext.request.contextPath}/my">处理controller中自定义异常</a></h3>
<h3><a href="${pageContext.request.contextPath}/no">处理controller中未知异常</a></h3>
</body>
</html>
