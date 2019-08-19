<%@ page import="com.tt.oa.exception.MyException" %><%--
  Created by IntelliJ IDEA.
  User: tt416
  Date: 2019/8/16
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Exception myException= (Exception) request.getAttribute("ex");
%>
<html>
<head>
    <title>自定义错误</title>
</head>
<body>
<h1>自定义异常错误</h1>
<h2>错误内容</h2>
<%=myException.getMessage()%>
</body>
</html>
