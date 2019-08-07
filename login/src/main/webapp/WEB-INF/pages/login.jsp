<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String errMsg= (String) request.getAttribute("errMsg");
%>
<html>
<head>
    <meta charset="utf-8">
    <title>欢迎使用OA管理系统</title>
    <meta name="keywords" content="HTML5 Bootstrap 3 Admin Template UI Theme" />
    <meta name="description" content="AbsoluteAdmin - A Responsive HTML5 Admin UI Framework">
    <meta name="author" content="AbsoluteAdmin">

    <!-- 包含头部信息用于适应不同设备 -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <style>
        #login{
            text-align: center;
        }
    </style>
</head>

<body class="external-page external-alt sb-l-c sb-r-c">
<section id="content_wrapper">
    <div class="admin-form theme-info mw500" id="login">
        <div class="content-header">
            <h1>登录系统</h1>
            <p class="lead">欢迎使用自动化OA管理系统</p>
        </div>
        <div class="panel mt30 mb25">
            <form method="post" action="/login/doLogin" id="contact">
                <div class="panel-body bg-light p25 pb15">
                    <div class="section">
                        <label for="username" class="field-label text-muted fs18 mb10">用户名:</label>
                        <label for="username" class="field prepend-icon">
                            <input type="text" name="username" id="username" class="gui-input" placeholder="请输入工号...">
                            <label for="username" class="field-icon">
                                <i class="fa fa-user"></i>
                            </label>
                        </label>
                    </div>
                    <div class="section">
                        <label for="password" class="field-label text-muted fs18 mb10">&nbsp;&nbsp;&nbsp;密码:</label>
                        <label for="password" class="field prepend-icon">
                            <input type="password" name="password" id="password" class="gui-input" placeholder="请输入密码...">
                            <label for="password" class="field-icon">
                                <i class="fa fa-lock"></i>
                            </label>
                        </label>
                    </div>
                    <div>
                        <button type="submit">登陆</button>
                    </div>
                    <div>
                        <span id="span"></span>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

<script>
    var msg="<%=errMsg%>";
    var span=document.getElementById("span");
    if (msg!="null")
        span.innerHTML=msg;
</script>

</body>
</html>
