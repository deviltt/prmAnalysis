<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getLocalPort() + request.getContextPath();
%>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            margin: 0;
            padding: 0;
        }

        /*把datalist后面的箭头给去掉*/
        input::-webkit-calendar-picker-indicator {
            display: none;
        }

        .search {
            position: sticky;
            top: 0;
        }
    </style>
</head>
<body>
<div class="search">
    <form action="search" method="post">
        <input placeholder="查找" list="inputList" id="key" name="key" oninput="inputChange()"/>
        <datalist id="inputList">

        </datalist>
        <input type="submit" value="查询"/>
    </form>
</div>
<!-- 从session中获取内容输出到页面中 -->
<%=request.getSession().getAttribute("result")%>
<script>
    function inputChange() {
        var datalist = document.getElementById("inputList");
        //获取每次输入的input标签里面的值
        var key = document.getElementById("key");
        /**
         * f、t、r三个字母开头的查找内容要输入6个字符才能进行查找
         * 其余字母开头的查找内容输入3个字符就进行查找
         */
        var t_length, f_length, r_length, other_length, keyInputLength = key.value.length;
        if (key.value.charAt(0) == 't') {
            t_length = keyInputLength;
        } else if (key.value.charAt(0) == 'f') {
            f_length = keyInputLength;
        } else if (key.value.charAt(0) == 'r') {
            r_length = keyInputLength;
        } else {
            other_length = keyInputLength;
        }
        if (other_length >= 3 || t_length >= 6 || r_length >= 12 || f_length >= 6) {
            $.ajax({
                url: "/tree/searchTrie",
                type: "post",
                data: {
                    "name": key.value
                },
                dataType: "json",
                success: function (result) {
                    for (var i in result) {
                        datalist.innerHTML += "<option value=\"" + i + "\">" + result[i] + "</option>";
                    }
                }
            });
        }
        datalist.innerHTML = "";
    }
</script>
<script type="text/javascript" src="../../js/jquery-1.4.2.js"></script>
</body>
</html>
