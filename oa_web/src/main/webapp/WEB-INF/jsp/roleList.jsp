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

    <!--                       Javascripts                       -->
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



    <script>
        var rid1;
        /**
         * 显示父部门的树形结构
         */
        function show_resc(rid){
            rid1=rid;

            $.get("resc/listajax2", {rid:rid},function(data){

                //生成Ztree
                createZtree("ztree_div", data, {
                    name:"rname",
                    pid:"pid",
                    icon:false,
                    expand:true,
                    check:true,
                    checkType:{"Y":"ps","N":"s"}

            });

            //弹出dialog
            openDialog("tree_dialog", "设置角色权限", 300, 400);
            }, "json");
        }
        
        
        function updateRescAjxj() {
            //创建一个数组
            var reids=[];
            //获取treeObject对象
            var treeObj=$.fn.zTree.getZTreeObj("ztree_div");
            var nodes = treeObj.getCheckedNodes(true);
            for (var i=0;i<nodes.length;i++){
                //把权限id加入到数组
                reids.push(nodes[i].id);
            }
            //提交请求
            $.ajax({
                type:"POST",
                url:"/role/updateResc",
                traditional:true,
                data:{rid:rid1,reids:reids},
                success:function(data){
                    if(data==1){
                        alert("权限修改成功！");
                        closeDialog("tree_dialog");
                    }else{
                        alert("修改失败");
                    }

                }
            })
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
                        <th>角色名称</th>
                        <th>角色描述</th>
                        <th>角色状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${list}" var="role">
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>${role.id}</td>
                            <td>${role.rolename}</td>
                            <td>${role.roledesc}</td>
                            <td>${role.rolestate}</td>
                            <td>
                                <!-- Icons -->
                                <shiro:hasPermission name="	/resc/addOrUpdate">
                                <a href="javascript:updateRole(${role.id},'${role.rolename}','${role.roledesc}',${role.rolestate})" title="Edit"><img
                                        src="resources/images/icons/pencil.png" alt="Edit" /></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="	/resc/deleteRole">
                                <a
                                        href="role/deleteRole?id=${role.id}" title="Delete"><img
                                        src="resources/images/icons/cross.png" alt="Delete" /></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="	/role/updateRoles">
                                <a
                                        href="javascript:show_resc(${role.id})" title="Edit Meta"><img
                                        src="resources/images/icons/hammer_screwdriver.png"
                                        alt="Edit Meta" /></a>
                                </shiro:hasPermission>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                    <tfoot>
                    <tr>
                        <td colspan="6">
                            <div class="bulk-actions align-left">
                                <shiro:hasPermission name="	/role/addOrUpdate">
                                <a class="mybutton" href="javascript:addRole();">添加角色</a>
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
     * 添加角色
     */
    function addRole(){
        //清空表单
        $("#roledesc_id").val("");
        $("#rolename_id").val("");
        $("input[type='radio'][value='1']").attr("checked", "checked");


        //弹出框
        openDialog('div_dialog', '添加角色');
    }

    /**
     * 修改角色
     * @param eid
     */
    function updateRole(id, name, desc, state){
        //获得需要修改的所有职工信息
        //填充到dialog的表单上
        $("#role_id").val(id);
        $("#rolename_id").val(name);
        $("#roledesc_id").val(desc);
        $("input[type='radio'][value='" + state + "']").attr("checked", "checked");



        //弹出dialog
        openDialog('div_dialog', '修改角色');
    }

</script>

<!-- 弹出框 -->
<div id="div_dialog" style="display: none;">
    <form action="${pageContext.request.contextPath}/role/addOrUpdate" method="post">

        <input type="hidden" name="id" id="role_id"/>
        <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
                <label>角色名称</label>
                <input class="text-input input" type="text" id="rolename_id"
                       name="rolename" />
            </p>
            <p>
                <label>角色描述</label>
                <input class="text-input input" type="text" id="roledesc_id"
                          name="roledesc"></input>
            </p>
            <p>
                <label>角色状态</label>
                <input type="radio" name="rolestate" value="1">在职
                <input type="radio" name="rolestate" value="0">离职
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
    <button type="submit" class="mybutton" onclick="updateRescAjxj()">提交</button>
</div>

<div id="resc_dialog" style="display: none;">
    <div id="resc" ></div>
</div>

</body>
</html>
