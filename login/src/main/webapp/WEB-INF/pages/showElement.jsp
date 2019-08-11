<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%
    String content = (String) request.getSession().getAttribute("content");
%>
<html>
<head>
    <title>Document</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }
    </style>
</head>
<body>
<div id="main">
    <%=content%>
</div>
<script>
    function clickFunction(i) {
        var form = document.getElementById("form" + i);
        var inputs = form.getElementsByTagName("input");
        var spans = form.getElementsByTagName("span");
        //form标签的第一个和最后一个没有property的key和value，所以只要遍历中间的索引
        //childNodes里面的input标签，索引为1
        var map = {};
        map["key"] = spans[0].innerText;
        map["count"]=spans[0].getAttribute("count");
        map["depth"]=spans[0].getAttribute("depth");
        var propertyKey, propertyValue;
        for (var j = 0; j < inputs.length; j++) {
            propertyKey=inputs[j].name;
            propertyValue=inputs[j].value;
            map[propertyKey]=propertyValue;
        }
        console.log(map);
        $.ajax({
            url: "/tree/update",
            contentType: 'application/json;charset=utf-8',
            type: "post",
            data: JSON.stringify(map),
            dataType: "json",
            success: function (result) {
                alert("修改成功");
                window.location.href = 'http://localhost:8080/tree/toUpload';
            }
        })
    }
</script>
<script type="text/javascript" src="../../js/jquery-1.4.2.js"></script>
</body>
</html>
