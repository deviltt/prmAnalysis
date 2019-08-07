<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%
    String content= (String) request.getSession().getAttribute("content");
%>
<html>
<head>
    <title>Document</title>
    <style>
        *{
            margin:0;
            padding:0;
        }
    </style>
</head>
<body>
<div id="main">
    <%=content%>
</div>
</body>
</html>
