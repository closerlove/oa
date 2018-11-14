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

    <!-- 时间日期插件 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/widget/My97DatePicker/WdatePicker.js"></script>

    <!-- dialog弹出框的导入 -->
    <link rel="stylesheet" href="//apps.bdimg.com/libs/jqueryui/1.10.4/css/jquery-ui.min.css"/>
    <script type="text/javascript" src="resources/widget/dialog/jquery-ui-1.9.2.custom.min.js"></script>
    <script type="text/javascript" src="resources/js/plugin.js"></script>

    <!-- ztree树形结构 -->
    <link rel="stylesheet" href="resources/widget/zTree/zTreeStyle/zTreeStyle.css"/>
    <script type="text/javascript" src="resources/widget/zTree/jquery.ztree.all.min.js"></script>

    <!-- 图片上传插件 -->
    <link rel="stylesheet" href="resources/widget/webuploader/webuploader.css"/>
    <script type="text/javascript" src="resources/widget/webuploader/webuploader.min.js"></script>

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
         * 显示部门的树形结构
         */
        function showDepsTree(){
            $.get("dep/listajax", function(data){

                //生成Ztree
                createZtree("ztree_div", data, {
                    name:"dname",
                    pid:"pid",
                    icon:false,
                    expand:true,
                    onclick:function(event, treeId, treeNode){
                        //将选中的父部门名称设置给button按钮
                        $("#btn_id").html(treeNode.dname);
                        $("#pid_id").val(treeNode.id);
                        //关闭弹出框
                        closeDialog("tree_dialog");
                    }
                },$("#pid_id").val());

                //弹出dialog
                openDialog("tree_dialog", "选择部门", 300, 200);
            }, "json");
        }


        /**
         * 第一种做法
         * @param eid

        function show_role(eid){
            $("#eid_id").val(eid);
            $.get("role/ajxjlist",function(data){
                var html="";
                for (var i=0;i<data.length;i++){
                    // html+="<input type='checkbox' name='rid' value='data[i].id' />"+data[i].rolename+"<br/>";
                    html += "<input type=\"checkbox\" name=\"rid\" value=\"" + data[i].id + "\"/>" + data[i].rolename + "<br/>";
                }
                $("#role_div").html(html);

                //通过ajxj查询当前职工所拥有的角色
               $.get("/role/getRoleByEid",{eid:eid},function (data) {

                   for(var i = 0; i < data.length; i++){

                       $("input[type='checkbox'][name='rid'][value='" + data[i].id + "']")
                           .attr("checked", "checked");
                   }
               },"json");

            },"json");
            openDialog('role_dialog', '修改权限',300,200);
        }
         */

        //第二种做法
        function show_role(eid){
            $("#eid_id").val(eid);
            $.get("role/ajxjlist2",{eid:eid},function(data){
                var html="";
                for (var i=0;i<data.length;i++){
                    if(data[i].checked){
                        html += "<input type=\"checkbox\" name=\"rid\" value=\"" + data[i].id + "\"checked/>" + data[i].rolename + "<br/>";
                    }else {
                        html += "<input type=\"checkbox\" name=\"rid\" value=\"" + data[i].id + "\"/>" + data[i].rolename + "<br/>";
                    }
                }
                $("#role_div").html(html);



            },"json");
            openDialog('role_dialog', '修改权限',300,200);
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
                        <th>姓名</th>
                        <th>头像</th>
                        <th>邮箱</th>
                        <th>性别</th>
                        <th>所属部门</th>
                        <th>出生日期</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <c:forEach items="${emp}" var="emp">
                        <tr>
                            <td><input type="checkbox" /></td>
                            <td>${emp.id}</td>
                            <td>${emp.name}</td>
                            <c:if test="${emp.image == null}">
                                <td><img width="100" height="110" src="resources/images/icons/header.jpg"/></td>
                            </c:if>
                            <c:if test="${emp.image != null}">
                                <td><img width="100" height="110" src="/img/getImg?path=${emp.image}"/></td>
                            </c:if>
                            <td>${emp.email}</td>
                            <td>${emp.sex ==1?"男":"女"}</td>
                            <td>${emp.dname}</td>
                            <td><fmt:formatDate value="${emp.birthday}" pattern="yyyy-MM-dd"/></td>
                            <td></td>
                            <td>
                                <!-- Icons -->
                                <shiro:hasPermission name="/emp/addOrUpdate">
                                <a href="javascript:updateEmp(${emp.id},'${emp.name}',${emp.sex},'${emp.email}',
                                '${emp.password}', '${emp.birthday}', '${emp.image}', '${emp.did}', '${emp.dname}');" title="Edit"><img
                                        src="resources/images/icons/pencil.png" alt="Edit" /></a>
                                </shiro:hasPermission>
                                <shiro:hasPermission name="/emp/delete">
                                <a
                                        href="emp/delete?id=${emp.id}" title="Delete"><img
                                        src="resources/images/icons/cross.png" alt="Delete" /></a>
                                </shiro:hasPermission>

                                <a
                                        href="javascript:show_role(${emp.id})" title="Edit Meta"><img
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
                                <a class="mybutton" href="javascript:addEmp();">员工添加</a>
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
    function addEmp(){
        //清空表单
        $("#eid").val("");
        $("#header_id").attr("src", "resources/images/icons/header.jpg");
        $("#image_id").val("");
        $("#name_id").val("");
        $("#email_id").val("");
        $("#password_id").val("");
        $("input[type='radio'][value='1']").attr("checked", "checked");

        //Thu Nov 12 00:00:00 CST 2015
        $("#birthday_id").val("");
        //填充部门
        $("#btn_id").html("无");
        $("#pid_id").val("-1");

        //弹出框
        openDialog('div_dialog', '添加职工');
    }

    /**
     * 修改用户
     * @param eid
     */
    function updateEmp(eid, name, sex, email, password, birthday, image, did, dname){
        //获得需要修改的所有职工信息
        //填充到dialog的表单上
        $("#eid").val(eid);
        if(image != null && image != "") {
            $("#header_id").attr("src", "/img/getImg?path=" + image);
            $("#image_id").val(image);
        }
        $("#name_id").val(name);
        $("#email_id").val(email);
        $("#password_id").val(password);
        $("input[type='radio'][value='" + sex + "']").attr("checked", "checked");

        //Thu Nov 12 00:00:00 CST 2015
        var date = new Date(birthday);
        var dateStr = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
        $("#birthday_id").val(dateStr);
        //填充部门
        $("#btn_id").html(dname);
        $("#pid_id").val(did);


        //弹出dialog
        openDialog('div_dialog', '修改职工');
    }

</script>

<!-- 弹出框 -->
<div id="div_dialog" style="display: none;">
    <form action="${pageContext.request.contextPath}/emp/addOrUpdate" method="post">
        <input id="eid" name="id" value="" type="hidden">
        <fieldset>
            <!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
            <p>
                <label>员工照片</label>
                <img id="header_id" width="100" height="110" src="resources/images/icons/header.jpg"/>
                <input type="hidden" id="image_id" name="image" value=""/>
                <div id="filePicker"><span>选择头像</span></div>
            </p>
            <p>
                <label>员工姓名</label>
                <input class="text-input input" type="text" id="name_id"
                       name="name" />
            </p>
            <p>
                <label>密码</label>
                <input class="text-input input" type="password" id="password_id"
                       name="password" />
            </p>
            <p>
                <label>邮箱</label>
                <input class="text-input input" type="text" id="email_id"
                       name="email" />
            </p>
            <p>
                <label>员工性别</label>
                <input type="radio" value="1" name="sex" checked/>男
                <input type="radio" value="0" name="sex" />女
            </p>
            <p>
                <label>部门选择</label>
                <button id="btn_id" class="mybutton" type="button" onclick="showDepsTree();">无</button>
                <input id="pid_id" name="did" type="hidden" value="-1"/>
            </p>
            <p>
                <label>出生日期</label>
                <input
                        class="Wdate time-input " type="text" id="birthday_id"
                        name="birthday" onclick="WdatePicker()"/>
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

<%--角色弹出框--%>
<div id="role_dialog" style="display: none">
    <form method="post" action="role/updateRoles">
        <input type="hidden" name="eid" id="eid_id" value=""/>
        <!-- 所有的角色 -->
        <div id="role_div"></div>

        <button type="submit" class="mybutton">提交</button>
    </form>


</div>

<script>
    // 初始化Web Uploader
    var uploader = WebUploader.create({
        // 选完文件后，是否自动上传。
        auto: true,
        // swf文件路径
        swf: ${pageContext.request.contextPath} + '/resources/widget/webuploader/Uploader.swf',
        // 文件接收服务端。
        server: 'img/uploadImg',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#filePicker'
    });

    //设置一个队列监听事件，当有一个图片添加进队列中时，触发该方法
    uploader.on("fileQueued", function(file){
        //找到头像的img标签
        var $img = $("#header_id");

        //创建缩略图
        uploader.makeThumb( file, function( error, src ) {
            if ( error ) {
                $img.replaceWith('<span>不能预览</span>');
                return;
            }

            $img.attr( 'src', src );
        }, 100, 110 );
    });


    uploader.on("uploadSuccess", function(file, response){
        $("#image_id").val(response.uploadpath);
    });

</script>

</body>
</html>
