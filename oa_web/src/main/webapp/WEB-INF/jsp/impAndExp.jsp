<%--
  Created by IntelliJ IDEA.
  User: cx
  Date: 2018/11/9
  Time: 15:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <base href="${pageContext.request.contextPath}/"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Insert title here</title>
    <!-- Invalid Stylesheet. This makes stuff look pretty. Remove it if you want the CSS completely valid -->
    <!-- Reset Stylesheet -->
    <link rel="stylesheet" href="resources/css/reset.css" type="text/css"
          media="screen"/>
    <!-- Main Stylesheet -->
    <link rel="stylesheet" href="resources/css/style.css" type="text/css"
          media="screen"/>
    <link rel="stylesheet" href="resources/css/invalid.css" type="text/css"
          media="screen"/>
    <!

    <!--                       Javascripts                       -->
    <!-- jQuery -->
    <script type="text/javascript"
            src="resources/scripts/jquery-1.8.3.min.js"></script>
    <!-- jQuery Configuration -->
    <script type="text/javascript"
            src="resources/scripts/simpla.jquery.configuration.js"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
    <script type="text/javascript">
        //ajax 方式上传文件操作
        $(document).ready(function(){
            $('#btn').click(function(){
                if(checkData()){
                    $('#form1').ajaxSubmit({
                        url:'doc/upload',
                        dataType: 'text',
                        success: resutlMsg,
                        error: errorMsg
                    });
                    function resutlMsg(msg){
                        alert(msg);
                        $("#upfile").val("");
                    }
                    function errorMsg(){
                        alert("导入excel出错！");
                    }
                }
            });
        });

        //JS校验form表单信息
        function checkData(){
            var fileDir = $("#upfile").val();
            var suffix = fileDir.substr(fileDir.lastIndexOf("."));
            if("" == fileDir){
                alert("选择需要导入的Excel文件！");
                return false;
            }
            if(".xls" != suffix && ".xlsx" != suffix ){
                alert("选择Excel格式的文件导入！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>


<script type="text/javascript">
        function expemp(){
            window.location.href="/doc/export";
        }

        function expdep(){
            window.location.href="/doc/exportdep";
        }
</script>


<h3 style="color: #7EC4CC">请选择您要导出的文件</h3>
    <div id="main-content">
        <div class="content-box">
            <div class="content-box-content">
                <div class="tab-content default-tab" id="tab1">
                    <table>
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>文件描述</th>
                            <th>操作</th>
                        </tr>
                        </thead>

                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>部门信息表</td>
                            <td> <input type="button"id="b1" class="btn btn-blue" value="导出" onclick="expdep()"/></td>

                        </tr>
                        <tr>
                            <td>2</td>
                            <td>员工信息表</td>
                            <td>
                            <input type="button"id="b2" class="btn btn-blue" value="导出" onclick="expemp()"/></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>



<form method="POST"  enctype="multipart/form-data" id="form1" action="doc/upload">
<h3 style="color: #7EC4CC">请选择您要导入的文件</h3>
<div id="main-content">
    <div class="content-box">
        <div class="content-box-content">
            <div class="tab-content default-tab" id="tab">
                <table>
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>文件描述</th>
                        <th>文件模板</th>
                        <th>选择文件</th>
                        <th>操作</th>
                    </tr>
                    </thead>

                    <tbody>
                    <tr>
                        <td>1</td>
                        <td>部门信息表</td>
                        <td>
                            <a href="resources/file/depInfoModel.xls">部门信息模板</a>
                        </td>
                        <td>部门信息表</td>
                        <td> <input type="button"id="b3" class="btn btn-blue" value="导出" onclick="expdep()"/></td>

                    </tr>
                    <tr>
                        <td>2</td>
                        <td>员工信息表</td>
                        <td>
                            <a href="resources/file/empInfoModel.xls">员工信息模板</a>
                        </td>
                        <td><input id="upfile" type="file" name="upfile"></td>
                        <td>
                            <input type="submit" value="提交" onclick="checkData()">
                            <input type="button" value="ajax方式提交" id="btn" name="btn" >
                        </td>
                    </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</form>
</body>
</html>
