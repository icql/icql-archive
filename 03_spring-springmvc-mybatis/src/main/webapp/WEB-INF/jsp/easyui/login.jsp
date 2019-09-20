<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户登录</title>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/themes/default/easyui.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/taotao.css"/>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
</head>

<body style="background-color: #F3F3F3">
<div class="easyui-dialog" title="用户登录" data-options="closable:false,draggable:false"
     style="width:300px;height:160px;padding:10px;">
    <table cellpadding=3>
        <tr>
            <td>用户名：</td>
            <td><input name="username" type="text" class="easyui-textbox"
                       required="true" autocomplete="off"/></td>
        </tr>
        <tr>
            <td>密码：</td>
            <td><input name="password" type="Password" class="easyui-textbox"
                       required="true" autocomplete="off"/></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)"
                   onclick="login()">登录</a>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    function login() {
        var username = $("[name=username]").val();
        var password = $("[name=password]").val();
        if(!(username && password)){
            $.messager.alert('错误', "用户名或密码不能为空！");
            return;
        }

        if (username != "admin" && password != "123") {
            $.messager.alert('错误', "用户名密码不正确！");
            return;
        }
        window.location.href = "${pageContext.request.contextPath}/easyui/";
    }

    $(function(){
        $(document).keyup(function(event){
            if(event.keyCode ==13){
                login();
            }
        });
    });
</script>
</body>
</html>