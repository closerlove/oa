<%--
  Created by IntelliJ IDEA.
  User: cx
  Date: 2018/11/1
  Time: 17:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
    <!-- Reset Stylesheet -->
    <link rel="stylesheet" href="resources/css/reset.css" type="text/css"
          media="screen" />
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="resources/css/style.css" type="text/css"
          media="screen" />
    <link rel="stylesheet" href="resources/css/invalid.css" type="text/css"
          media="screen" />
    <!

    <!-- Javascripts -->
    <!-- jQuery -->
    <script type="text/javascript"
            src="resources/scripts/jquery-1.8.3.min.js"></script>
    <!-- jQuery Configuration -->
    <script type="text/javascript"
            src="resources/scripts/simpla.jquery.configuration.js"></script>

    <!-- dialog弹出框的导入 -->
    <link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css"/>
    <script type="text/javascript" src="resources/widget/dialog/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="resources/js/plugin.js"></script>

    <!-- ztree树形结构 -->
    <link rel="stylesheet" href="resources/widget/zTree/zTreeStyle/zTreeStyle.css"/>
    <script type="text/javascript" src="resources/widget/zTree/jquery.ztree.all.min.js"></script>

    <style>
        .time-input {
            padding: 6px;
            font-size: 13px;
            border: 1px solid #d5d5d5;
            color: #333;
        }
    </style>

    <script>
        /**
         * 显示父部门的树形结构
         */
        function showRoleTree(){
            $.get("resc/listajax", function(data){

                //生成Ztree
                createZtree("ztree_div", data, {
                    name:"rname",
                    pid:"pid",
                    icon:false,
                    expand:true,
                    onclick:function(event, treeId, treeNode){
                        //将选中的父部门名称设置给button按钮
                        $("#btn_id").html(treeNode.rname);
                        $("#pid_id").val(treeNode.id);
                        if(treeNode.restate==1){
                            $("#span_id").html("二级权限");
                            $("#restate_id").val(2);
                        }else if(treeNode.restate==2){
                            $("#span_id").html("三级权限");
                            $("#restate_id").val(3);
                        }else if(treeNode.restate==3){
                            alert("三级权限下不能够再有子权限");
                            return;
                        }

                        //关闭弹出框
                        closeDialog("tree_dialog");
                    }
                });

                //弹出dialog
                openDialog("tree_dialog", "选择父权限", 300, 200)
            }, "json");
        }

    </script>


</head>
<body>
<div id="main-content">
    <div class="content-box">
        <!-- End .content-box-header -->
        <div class="content-box-content">
            <div class="tab-content default-tab" id="tab1">
                <table>
                    <thead>
                    <tr>
                        <th><input class="check-all" type="checkbox" /></th>
                        <th>编号</th>
                        <th>权限名称</th>
                        <th>父权限名称</th>
                        <th>权限路径</th>
                        <th>权限描述</th>
                        <th>权限类型</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${list}" var="resc">
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>${resc.id}</td>
                            <td>${resc.rname}</td>
                            <td>${resc.pname}</td>
                            <td>${resc.repath}</td>
                            <td>${resc.redesc}</td>
                            <td>
                                <c:if test="${resc.restate ==1}">
                                    一级权限
                                </c:if>
                                <c:if test="${resc.restate ==2}">
                                    二级权限
                                </c:if>
                                <c:if test="${resc.restate ==3}">
                                    三级权限
                                </c:if>
                            </td>
                            <td>
                                <!-- Icons -->
                                <shiro:hasPermission name="/resc/addOrUpdate">
                                <a href="javascript:updateResc(${resc.id},'${resc.rname}',${resc.pid},'${resc.repath}',
                                '${resc.redesc}', '${resc.restate}');" title="Edit"><img
                                        src="resources/images/icons/pencil.png" alt="Edit" /></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="/resc/delete">
                                <a
                                        href="resc/delete?id=${resc.id}" title="Delete"><img
                                        src="resources/images/icons/cross.png" alt="Delete" /></a>
                                </shiro:hasPermission>

                                <a
                                        href="#" title="Edit Meta"><img
                                        src="resources/images/icons/hammer_screwdriver.png"
                                        alt="Edit Meta" /></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                    <tfoot>
                    <tr>
                        <td colspan="6">
                            <div class="bulk-actions align-left">
                                <shiro:hasPermission name="/resc/addOrUpdate">
                                <a class="mybutton" href="javascript:addResc();">权限添加</a>
                                </shiro:hasPermission>
                            </div>

                            <!-- 分页导航 -->
                            <%@ include file="page.jsp"%>
                            <div class="clear"></div>
                        </td>
                    </tr>
                    </tfoot>
                </table>
            </div>
        </div>
        <!-- End .content-box-content -->
    </div>
</div>
<!-- End #main-content -->

<script>

    /**
     * 添加职工
     */
    function addResc(){
        //清空表单
        $("#rid_id").val("");
        $("#rname_id").val("");
        $("#redesc_id").val("");
        $("#restate_id").val("1");
        $("#repath_id").val("");
        //弹出框
        openDialog('div_dialog', '添加权限');
    }

    /**
     * 修改用户
     * @param eid
     */

    function updateResc(rid, rname, pid, repath, redesc, restate){
        //获得需要修改的所有职工信息
        //填充到dialog的表单上

        $("#rid").val(rid);
        $("#rname_id").val(rname);
        $("#pid_id").val(pid);
        $("#repath_id").val(repath);
        $("#redesc_id").val(redesc);


        //弹出dialog
        openDialog('div_dialog', '修改权限');
    }

</script>

<!-- 弹出框 -->
<div id="div_dialog" style="display: none;">
    <form action="${pageContext.request.contextPath}/resc/addOrUpdate" method="post">
        <input id="rid" name="id" value="" type="hidden">
        <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
                <label>权限名称</label>
                <input class="text-input input" type="text" id="rname_id"
                       name="rname" />
            </p>
            <p>
                <label>父权限</label>
                <button id="btn_id" class="mybutton" type="button" onclick="showRoleTree()">无</button>
                <input id="pid_id" name="pid" type="hidden" value="-1"/>
            </p>
            <p>
                <label>权限路径</label>
                <input class="text-input input" type="text" id="repath_id"
                       name="repath" />
            </p>
            <p>
                <label>权限描述</label>
                <input class="text-input input" type="text" id="redesc_id"
                       name="redesc" />
            </p>
            <p>
                <label>权限类型</label>
                <span id="span_id">一级权限</span>
                <input type="hidden" value="1" name="restate" id="restate_id">
            </p>



            <p>
                <input class="mybutton" type="submit" value="提交" />
            </p>
        </fieldset>
        <div class="clear"></div>
        <!-- End .clear -->
    </form>
</div>

<!-- 树形弹出框 -->
<div id="tree_dialog" style="display: none;">
    <div id="ztree_div" class="ztree"></div>
</div>


</body>
</html>
