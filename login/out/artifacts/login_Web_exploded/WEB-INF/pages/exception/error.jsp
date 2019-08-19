<%--
  Created by IntelliJ IDEA.
  User: tt416
  Date: 2019/8/16
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
Exception exception= (Exception) request.getAttribute("ex");
%>
<html>
<head>
    <title>未知异常</title>
</head>
<body>
<h1>异常类型&nbsp;<%=exception.getClass().getSimpleName()%></h1>
<h2>异常原因&nbsp;<%=exception.getMessage()%></h2>
</body>
</html>
