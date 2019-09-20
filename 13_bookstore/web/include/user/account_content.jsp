<%--
  Created by IntelliJ IDEA.
  User: A6924
  Date: 2018/6/5
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="divpagecontent">
    <table width="100%" border="0" cellspacing="0">
        <tr>
            <td width="25%">
                <table width="100%" border="0" cellspacing="0"
                       style="margin-top:30px">
                    <tr>
                        <td class="listtitle">我的帐户</td>
                    </tr>
                    <tr>
                        <td class="listtd">
                            <img src="/images/miniicon.gif" width="9" height="6"/>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/user/getinfo?username=${user.username}">用户信息修改</a>
                        </td>
                    </tr>

                    <tr>
                        <td class="listtd">
                            <img src="/images/miniicon.gif" width="9" height="6"/>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/order/list">订单查询</a>
                        </td>
                    </tr>
                    <tr>
                        <td class="listtd">
                            <img src="/images/miniicon.gif" width="9" height="6"/>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/user/logout">用戶退出</a>
                        </td>
                    </tr>
                </table>
            </td>
            <td>
                <div style="text-align:right; margin:5px 10px 5px 0px">
                    <a href="/index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
                    <a href="/user/account.jsp">&nbsp;我的帐户</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;欢迎
                </div>
                <table cellspacing="0" class="infocontent">
                    <tr>
                        <td style="padding:20px"><p>
                            <img src="/images/ad/myad.jpg" width="631" height="436"/>
                        </p>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
