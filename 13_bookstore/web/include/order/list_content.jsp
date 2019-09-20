<%--
  Created by IntelliJ IDEA.
  User: icql
  Date: 2018/6/7
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="divpagecontent">
    <table width="100%" border="0" cellspacing="0">
        <tr>
            <td width="25%"><table width="100%" border="0" cellspacing="0"
                                   style="margin-top:30px">
                <tr>
                    <td class="listtitle">我的帐户</td>
                </tr>
                <tr>
                    <td class="listtd"><img src="/images/miniicon.gif" width="9"
                                            height="6" />&nbsp;&nbsp;&nbsp;&nbsp; <a
                            href="/user/getinfo?username=${user.username}">用户信息修改</a>
                    </td>
                </tr>

                <tr>
                    <td class="listtd"><img src="/images/miniicon.gif" width="9"
                                            height="6" />&nbsp;&nbsp;&nbsp;&nbsp; <a href="/order/list">订单查询</a>
                    </td>
                </tr>
                <tr>
                    <td class="listtd"><img src="/images/miniicon.gif" width="9"
                                            height="6" />&nbsp;&nbsp;&nbsp;&nbsp; <a href="/user/logout">用戶退出</a></td>
                </tr>
















            </table>
            </td>
            <td><div style="text-align:right; margin:5px 10px 5px 0px">
                <a href="/index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a
                    href="/user/account">&nbsp;我的帐户</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;订单查询
            </div>


                <table cellspacing="0" class="infocontent">
                    <tr>
                        <td style="padding:20px"><p>欢迎${user.username}光临商城！</p>
                            <p>
                                您有<font style="color:#FF0000">${count}</font>个订单
                            </p>
                            <table width="100%" border="0" cellspacing="0" class="tableopen">
                                <tr>
                                    <td bgcolor="#A3E6DF" class="tableopentd01">订单号</td>
                                    <td bgcolor="#A3D7E6" class="tableopentd01">收件人</td>
                                    <td bgcolor="#A3CCE6" class="tableopentd01">订单时间</td>
                                    <td bgcolor="#A3B6E6" class="tableopentd01">状态</td>
                                    <td bgcolor="#A3E2E6" class="tableopentd01">操作</td>
                                </tr>

                                <c:forEach items="${lsOrder}" var="order" varStatus="vs">

                                    <tr>
                                        <td class="tableopentd02">${vs.count }</td>

                                        <td class="tableopentd02">${order.receiverName }</td>
                                        <td class="tableopentd02">${order.ordertime }</td>
                                        <td class="tableopentd02">${order.paystate==0? "未支付":"已支付"}</td>
                                        <td class="tableopentd03">
                                            <a href="${pageContext.request.contextPath }/order/info?orderid=${order.id}">查看</a>&nbsp;&nbsp;
                                            <a href="/order/delete?orderid=${order.id}">刪除</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </td>
                    </tr>
                </table></td>
        </tr>
    </table>
</div>
