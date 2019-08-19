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
    <script type="text/javascript" src="../../js/jquery-1.4.2.js"></script>
    <script type="text/javascript" src="../../js/ztree/jquery.ztree.core.js"></script>
    <script type="text/javascript" src="../../js/ztree/jquery.ztree.all.js"></script>
    <link rel="stylesheet" href="../../css/zTreeStyle/zTreeStyle.css">
</head>
<body>
<div class="box">
    <ul class="ztree" id="tree"></ul>
</div>

<script type="text/javascript">
    $(function () {
        var setting = {
            data: {
                simpleData: {
                    enable: true
                }
            },
            // check: {
            //     enable: true,
            //     chkStyle: "checkbox"
            // },
            callback: {
                // onCheck: zTreeOnCheck,
                onClick: zTreeOnClick
            }
        };

        // var data = [
        //     {
        //         name: "MIB-2", "open": "false", "children": [{
        //             "name": "SYSTEM",
        //             "open": "false",
        //             "children": [{"name": "sysContact", "value": "support@ndsatcom.cn"}, {
        //                 "name": "sysName",
        //                 "value": "SkyWAN IDU 2X00/5000 - V5.60"
        //             },
        //                 {"name": "sysLocation", "value": "master"},{
        //                 name:"tt", open:'true',children:[]
        //                 }
        //             ]
        //         }, {
        //             "name": "INTERFACES", "open": "false", "children": [
        //                 {
        //                     "name": "IFTABLE", "open": "false", "children": [
        //                         {
        //                             "name": "IFENTRY", "open": "false", "children": [
        //                                 {"name": "IFIndex", "value": "1"},
        //                                 {"name": "IFAdminStatus", "value": "up"}
        //                             ]
        //                         }
        //                     ]
        //                 }
        //             ]
        //         }
        //         ]
        //     }
            // {
            //     "name": "SNMP", "open": true, children: [
            //         {"name": "snmpEnableAuthenTraps","value":"disabled"}
            //     ]
            // },
            // {
            //     "name": "ip", "open": true, children: [
            //         {"name": "ipForwarding","value":"forwarding"},
            //         {"name": "ipDefaultTTL","value":"65"},
            //         {"name": "IPFORWARD", "open": true, children: []}
            //     ]
            // }
        // ];

        var data=[{name:'SYSTEM',open:'true',children:[{name:'sysContact',value:'support@ndsatcom.cn'},{name:'sysName',value:'SkyWAN IDU 2X00/5000 - V5.60'},{name:'sysLocation',value:'master'},{name:'RTRIPADDRESSENTRY',open:'true',children:[]},{name:'RTRIPADDRESSENTRY',open:'true',children:[{name:'sysContact',value:'support@ndsatcom.cn'},{name:'sysName',value:'SkyWAN IDU 2X00/5000 - V5.60'},{name:'sysLocation',value:'master'}]}]}
        ];

        $.fn.zTree.init($("#tree"), setting, data);

        // function zTreeOnCheck(event, treeId, treeNode) {
        //     alert(treeNode.name);
        // }

        function zTreeOnClick(event, treeId, treeNode) {
            alert(treeNode.value);
        }
    });
</script>
</body>
</html>
