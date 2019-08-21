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
        * {
            margin: 0;
            padding: 0;
        }

        .box {
            width: 50%;
            background: lightgrey;
            float: left;
        }

        .edit {
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
    <button type="button" value="update" onclick="updateFunction()">修改</button>
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
            chkboxType: {"Y": "", "N": ""}
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

    //获取edit编辑区父节点
    var $edit = $(".edit");

    /**
     * 递归显示子节点
     * 但是只要显示没有children属性的节点
     * 将他们与父node拼接起来
     * @param node
     */
    function buildSubTreeOnRight(node) {
        var str = "";
        //递归显示节点
        for (var i in node.children) {
            var subNode = node.children[i];
            if (!subNode.hasOwnProperty("children")) {
                str += "<div class='keyValue'>" +
                    "&nbsp;&nbsp;&nbsp;&nbsp;<span>" + subNode.name + "&nbsp;:&nbsp;</span>" +
                    "<input name='" + subNode.name + "' value='" + subNode.value + "'/>" +
                    "</div>";
            }
        }
        $edit.append("<div>" +
            "<span class='node' count='" + node.getIndex() + "' depth='" + node.level + "'>" + node.name + "</span>" + str + "</div>");
    }

    function zTreeOnClick(event, treeId, treeNode) {
        var node;
        var treeObj = $.fn.zTree.getZTreeObj("tree");
        var sNodes = treeObj.getSelectedNodes();
        if (sNodes.length > 0) {
            //如果点击的节点时同级节点中的最后一个节点，则获取它的父级节点
            if (!sNodes[0].isParent) {
                node = sNodes[0].getParentNode();
            } else {
                //tId是节点的唯一标识
                node = treeObj.getNodeByTId(sNodes[0].tId);
            }
            $.fn.zTree.init($("#tree1"), setting, node);
        }
        buildSubTreeOnRight(node);
    }

    function updateFunction() {
        var list = [];
        var $divs = $(".edit > div");
        console.log($divs);
        for (var index in $divs) {
            if (index === "length") {
                break;
            }
            var map = {};
            var spans = $divs[index].getElementsByTagName("span");
            var inputs = $divs[index].getElementsByTagName("input");
            map["key"] = spans[0].innerText;
            map["count"] = spans[0].getAttribute("count");
            map["depth"] = spans[0].getAttribute("depth");
            var propertyKey, propertyValue;
            for (var j = 0; j < inputs.length; j++) {
                propertyKey = inputs[j].name;
                propertyValue = inputs[j].value;
                map[propertyKey] = propertyValue;
            }
            console.log(map);
            list.push(map);
        }
        console.log(list[0]);
        $.ajax({
            url: "/ztree/update",
            contentType: 'application/json;charset=utf-8',
            type: "post",
            data: JSON.stringify(list),
            dataType: "json",
            success: function (result) {
                alert(result["msg"]);
                window.location.href = 'http://localhost:8080/ztree/toZTree';
            }
        })
    }
</script>
</body>
</html>
