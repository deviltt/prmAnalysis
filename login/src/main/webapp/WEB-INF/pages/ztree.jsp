<%--
  Created by IntelliJ IDEA.
  User: tt416
  Date: 2019/8/12
  Time: 9:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ztree</title>
    <script type="text/javascript" src="../../js/jquery-3.4.1.js"></script>
    <script type="text/javascript" src="../../js/ztree/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="../../js/ztree/jquery.ztree.all.js"></script>
    <link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css">
    <style>
        *{
            margin:0;
            padding:0;
        }
        .box{
            width: 50%;
            background: lightgrey;
            float: left;
        }
        .edit{
            width: 50%;
            background: beige;
            float: right;
        }
    </style>
</head>
<body>
<div class="box">
    <form id="form" enctype="multipart/form-data">
        <div>
            <input type="file" class="upload" id="uploadFile" name="uploadFile">
            <input type="button" value="上传" onclick="test()"/>
        </div>
    </form>
    <ul class="ztree" id="tree"></ul>
</div>
<div class="edit">
    <ul class="ztree" id="tree1"></ul>
</div>

<script type="text/javascript">
    var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
        check: {
            enable: false,
            chkStyle: "checkbox",
            chkboxType:{"Y":"","N":""}
        },
        callback: {
            onClick: zTreeOnClick
        }
    };

    function test() {
        var formData = new FormData(document.getElementById("form"));
        // var formData = new FormData($("#form")[0]);
        formData.append("uploadFile", $(".upload")[0].files[0]);
        $.ajax({
            url: "/ztree/build",
            type: "post",
            contentType: false,  //禁止设置请求类型
            processData: false,  //禁止jQuery对Data数据的处理，默认会处理
            data: formData,
            success: function (data) {
                // var result = (new Function("return " + data))();
                var result = eval("(" + data + ")");
                $("#form").hide();
                $.fn.zTree.init($("#tree"), setting, result);
            }
        })
    }

    // function zTreeOnCheck(event, treeId, treeNode) {
    //     alert(treeNode.name);
    // }


    function zTreeOnClick(event, treeId, treeNode) {
        // alert(treeNode.value);
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var sNodes = treeObj.getSelectedNodes();
        if (sNodes.length > 0) {
            // alert("sNodes.tId : " + sNodes[0].tId);
            var node = treeObj.getNodeByTId(sNodes[0].tId);
            console.log(node);
            console.log(node.getIndex());
            $.fn.zTree.init($("#tree1"), setting, node);
        }
        // alert(treeNode.getIndex());  //获取选中节点在当前列表中的位置
    }
</script>
</body>
</html>
