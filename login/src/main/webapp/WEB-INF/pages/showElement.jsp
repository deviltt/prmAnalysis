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
        //form标签的第一个和最后一个没有property的key和value，所以只要遍历中间的索引
        var child = form.childNodes;
        //childNodes里面的input标签，索引为1
        var map = {};
        for (var j = 0; j < child.length; j++) {
            if (j == 0) {
                map["key"] = child[j].innerText;    //key
                map["count"] = child[j].childNodes[j].attributes[1].nodeValue;    //count
                map["depth"] = child[j].childNodes[j].attributes[2].nodeValue;    //depth
            } else if (j != child.length - 1) {
                var propertyKey = child[j].childNodes[1].attributes[0].nodeValue;
                var propertyValue = child[j].childNodes[1].attributes[1].nodeValue;
                map[propertyKey] = propertyValue;
            }
        }
        console.log(map);
        $.ajax({
            url: "/tree/update",
            contentType : 'application/json;charset=utf-8',
            type: "post",
            data: JSON.stringify(map),
            dataType: "json",
            success: function (result) {
                alert("修改成功");
            }
        })
    }
</script>
<script type="text/javascript" src="../../js/jquery-1.4.2.js"></script>
</body>
</html>
