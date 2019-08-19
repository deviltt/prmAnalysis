<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: tt416
  Date: 2019/7/9
  Time: 12:25
  To change this template use UpFile | Settings | UpFile Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    Map<String, List<String>> next= (Map<String, List<String>>) request.getAttribute("rootMap");
%>
<html>
<head>
    <title>Title</title>
    <style>
        input{
            vertical-align: text-bottom;
        }
    </style>
</head>
<body>
<%--    <c:forEach items="${roots}" var="root">--%>
<%--        <input type="button" value="+"><a href="http://localhost/io/toIO1?next=${root}">${root}</a><br>--%>
<%--    </c:forEach>--%>
    <%--    ${roots}--%>
    <%--    <%=next%>--%>
<%--    <script>--%>

<%--    </script>--%>
<%--${string}--%>
<script>
    console.log("${string}");
    var temp="${string}";
    console.log(typeof temp);
</script>
</body>
</html>
