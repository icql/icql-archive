<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Easyui</title>
    <link rel="stylesheet" type="text/css" id="easyuiCss"
          href="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/themes/bootstrap/easyui.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/themes/icon.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/easyui-common.css"/>

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/json3-3.3.2/json3.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/js/jquery-form-4.4.2/jquery.form.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.icql.js"></script>

    <script type="text/javascript">

    </script>
</head>

<body class="easyui-layout">
<div data-options="region:'north',border:false"
     style="height:50px;padding:0px;background:url('${pageContext.request.contextPath}/img/header_bg.png') no-repeat right;">

    <div style="padding-top: 10px;padding-left: 5px">
        <img src="${pageContext.request.contextPath}/img/logo.png" style="">
        Easyui
    </div>

    <div id="sessionInfoDiv" style="position: absolute;right: 5px;top:2px;">
        [<strong>超级管理员</strong>]，欢迎你！
    </div>

    <div style="position: absolute; right: 5px; bottom: 2px; ">
        <span id="online" class="easyui-linkbutton"
              style="background: url(${pageContext.request.contextPath }/img/online.png) no-repeat left;padding-left:18px;margin-left:3px;border: 0px">在线人数:1</span>
        <a href="javascript:void(0);" class="easyui-menubutton"
           data-options="menu:'#northSettingMenu',iconCls:'icon-help'">控制面板</a>
    </div>

    <div id="northSettingMenu" style="width: 100px; display: none;">
        <div>
            <span>更换皮肤</span>
            <div style="width:150px;">
                <div onclick="changeTheme('easyuiCss','default');">default</div>
                <div onclick="changeTheme('easyuiCss','gray');">gray</div>
                <div onclick="changeTheme('easyuiCss','black');">black</div>
                <div onclick="changeTheme('easyuiCss','bootstrap');">bootstrap</div>
                <div onclick="changeTheme('easyuiCss','metro');">metro</div>
            </div>
        </div>
        <div onclick="editPwd();">修改密码</div>
        <div onclick="showAbout();">联系管理员</div>
        <div class="menu-sep"></div>
        <div onclick="logout();">退出系统</div>
    </div>
</div>

<div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    <ul id="menu" style="margin-top: 10px;margin-left: 5px;"></ul>
</div>

<div data-options="region:'center',title:''">
    <div id="tabs" class="easyui-tabs content">
        <div title="消息中心" style="padding:20px;">
            test
        </div>
    </div>
</div>

<script type="text/javascript">
    function changeTheme(linkCssEleId, themeName) {
        var url = $('#' + linkCssEleId).attr('href');
        var href = url.substring(0, url.indexOf('themes')) + 'themes/'
            + themeName + '/easyui.css';
        $('#easyuiCss').attr('href', href);
        var $iframe = $('iframe');
        if ($iframe.length > 0) {
            for (var i = 0; i < $iframe.length; i++) {
                var ifr = $iframe[i];
                $(ifr).contents().find('#easyuiTheme').attr('href', href);
            }
        }
    }

    function showAbout() {
        $.messager.alert("ICQL-Easyui v1.0", "管理员邮箱: 37612949@qq.com");
    }

    function editPwd() {
        $.easyui.winOpen({
            title: "修改密码",
            width: 350,
            height: 200,
            href: 'form-pwd'
        });
    }

    function logout() {
        $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function (r) {
            if (r) {
                location.href = '${pageContext.request.contextPath }/easyui/login';
            }
        });
    }

    $(function () {
        $.easyuiTreeInit("menu", "${pageContext.request.contextPath}/easyui/api/tree");

        //欢迎消息
        window.setTimeout(function () {
            $.messager.show({
                title: '消息提示',
                msg: '欢迎登录，超级管理员！ <a href=\'javascript:void(0)\' onclick=\'showAbout()\'>联系管理员</a>',
                timeout: 3000,
                style: {
                    top: '60'
                }
            });
        }, 1000);
    });
</script>
</body>
</html>