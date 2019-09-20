<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: icql
  Date: 2018/6/7
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="divpagecontent">
    <table width="100%" border="0" cellspacing="0">
        <tr>

            <td>
                <div style="text-align:right; margin:5px 10px 5px 0px">
                    <a href="/index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;订单详细信息
                </div>


                <table cellspacing="0" class="infocontent">
                    <tr>
                        <td>
                            <table width="100%" border="0" cellspacing="0">
                                <tr>
                                    <td>
                                        <p>订单编号:001</p></td>
                                </tr>
                                <tr>
                                    <td>
                                        <table cellspacing="1" class="carttable">
                                            <tr>
                                                <td width="10%">序号</td>
                                                <td width="40%">商品名称</td>
                                                <td width="10%">价格</td>
                                                <td width="10%">数量</td>
                                                <td width="10%">小计</td>

                                            </tr>
                                        </table>
                                        <c:set var="totalPrice" value="0"/>
                                        <c:forEach items="${order.lsOrderItem}" var="o" varStatus="vs">
                                            <table width="100%" border="0" cellspacing="0">
                                                <tr>
                                                    <td width="10%">${vs.count }</td>
                                                    <td width="40%">${o.product.name }</td>
                                                    <td width="10%">${o.product.price }</td>
                                                    <td width="10%">${o.buynum }</td>
                                                    <td width="10%">${o.buynum * o.product.price }</td>
                                                    <c:set var="totalPrice"
                                                           value="${totalPrice+o.buynum * o.product.price}"/>
                                                </tr>
                                            </table>
                                        </c:forEach>


                                        <table cellspacing="1" class="carttable">
                                            <tr>
                                                <td style="text-align:right; padding-right:40px;"><font
                                                        style="color:#FF0000">合计：&nbsp;&nbsp;${totalPrice}</font></td>
                                            </tr>
                                        </table>

                                        <p>
                                            收货地址：${order.receiverAddress }&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                                            收货人：&nbsp;&nbsp;&nbsp;&nbsp;${order.receiverName }&nbsp;&nbsp;&nbsp;&nbsp;<br/>
                                            联系方式：${order.receiverPhone }&nbsp;&nbsp;&nbsp;&nbsp;

                                        </p>
                                        <hr>
                                        <c:if test="${order.paystate == 0}">
                                            <p style="text-align:right">

                                                <a href="/order/repay?orderid=${order.id}&money=${order.money}">
                                                    <img src="/images/gif53_029.gif" width="204"
                                                         height="51" border="0"/> </a>
                                            </p>
                                        </c:if>
                                    </td>
                                </tr>
                            </table>
                        </td>


                    </tr>


                </table>
            </td>
        </tr>
    </table>
</div>
