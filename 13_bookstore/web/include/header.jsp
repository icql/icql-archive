<%--
  Created by IntelliJ IDEA.
  User: A6924
  Date: 2018/6/4
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="/css/main.css" type="text/css"/>
    <%--<script type="text/javascript" src="/js/my.js"></script>--%>
</head>
<body>
<div id="divhead">
    <table cellspacing="0" class="headtable">
        <tr>
            <td>
                <a href="/index.jsp"><img src="/images/logo.png" width="95" height="30" border="0"/> </a>
            </td>
            <td style="text-align:right">
                <img src="/images/cart.gif" width="26" height="23" style="margin-bottom:-4px"/>
                &nbsp;
                <a href="/cart/list">购物车</a>
                |
                <a href="#">帮助中心</a>
                |
                <a href="/user/account">我的帐户</a>
                |
                <a href="/user/register.jsp">新用户注册</a>
            </td>
        </tr>
    </table>
</div>


<div id="divmenu">
    <a href="${pageContext.request.contextPath}/product/list?category=文学">文学</a>
    <a href="${pageContext.request.contextPath}/product/list?category=生活">生活</a>
    <a href="${pageContext.request.contextPath}/product/list?category=计算机">计算机</a>
    <a href="${pageContext.request.contextPath}/product/list?category=外语">外语</a>
    <a href="${pageContext.request.contextPath}/product/list?category=经营">经管</a>
    <a href="${pageContext.request.contextPath}/product/list?category=励志">励志</a>
    <a href="${pageContext.request.contextPath}/product/list?category=社科">社科</a>
    <a href="${pageContext.request.contextPath}/product/list?category=学术">学术</a>
    <a href="${pageContext.request.contextPath}/product/list?category=少儿">少儿</a>
    <a href="${pageContext.request.contextPath}/product/list?category=艺术">艺术</a>
    <a href="${pageContext.request.contextPath}/product/list?category=原版">原版</a>
    <a href="${pageContext.request.contextPath}/product/list?category=科技">科技</a>
    <a href="${pageContext.request.contextPath}/product/list?category=考试">考试</a>
    <a href="${pageContext.request.contextPath}/product/list?category=生活百科">生活百科</a>
    <a href="${pageContext.request.contextPath}/product/list?" style="color:#FFFF00">全部商品目录</a>
</div>
<div id="divsearch">
    <form action="${pageContext.request.contextPath}/product/list"
          method="post">
        <table width="100%" border="0" cellspacing="0">
            <tr>
                <td style="text-align:right; padding-right:220px">
                    Search
                    <input type="text" name="name" class="inputtable" onkeyup="searchName();" id="name" value="${mapWhere['name']}"/>
                    <input type="image" src="/images/serchbutton.gif" border="0" style="margin-bottom:-4px">
                </td>
            </tr>
        </table>

    </form>
</div>

<div id="content"
     style="background-color:white;width:128px; text-align:left;margin-left:945px;color:black;float:left;margin-top: -20px;display: none">
</div>
