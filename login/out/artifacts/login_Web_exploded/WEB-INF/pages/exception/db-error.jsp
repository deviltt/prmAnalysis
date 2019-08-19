<%--
  Created by IntelliJ IDEA.
  User: tt416
  Date: 2019/8/16
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Exception exception= (Exception) request.getAttribute("ex");
%>
<html>
<head>
    <title>数据库错误</title>
</head>
<body>
<h1>异常类型11&nbsp;<%=exception.getClass().getSimpleName()%></h1>
<h2>错误内容&nbsp;<%=exception.getMessage()%></h2>
</body>
</html>
