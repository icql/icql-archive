<%--
  Created by IntelliJ IDEA.
  User: A6924
  Date: 2018/6/7
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="divpagecontent">
    <table width="100%" border="0" cellspacing="0">
        <tr>

            <td><div style="text-align:right; margin:5px 10px 5px 0px">
                <a href="/index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a
                    href="/cart/list.jsp">&nbsp;购物车</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;订单
            </div>

                <form id="orderForm" action="${pageContext.request.contextPath }/order/create" method="post">
                    <table cellspacing="0" class="infocontent">
                        <tr>
                            <td><table width="100%" border="0" cellspacing="0">
                                <tr>
                                    <td><img src="/images/buy2.gif" width="635" height="38" />
                                        <p>您好：${user.username}！欢迎您来到商城结算中心</p></td>
                                </tr>
                                <tr>
                                    <td><table cellspacing="1" class="carttable">
                                        <tr>
                                            <td width="10%">序号</td>
                                            <td width="40%">商品名称</td>
                                            <td width="10%">价格</td>
                                            <td width="10%">类别</td>
                                            <td width="10%">数量</td>
                                            <td width="10%">小计</td>

                                        </tr>
                                    </table>
                                        <c:set var="count" value="0"></c:set>
                                        <c:forEach items="${mapCart}" var="p" varStatus="vs">
                                            <table width="100%" border="0" cellspacing="0">
                                                <tr>
                                                    <td width="10%">${vs.count }</td>
                                                    <td width="40%">${p.value.name }</td>
                                                    <td width="10%">${p.value.price }</td>
                                                    <td width="10%">${p.value.category }</td>
                                                    <td width="10%"><input name="text" type="text" value="${p.key.product_num }"
                                                                           style="width:20px" readonly="readonly" /></td>
                                                    <td width="10%">${p.value.price*p.key.product_num }</td>
                                                    <c:set var="count" value="${count+p.value.price * p.key.product_num}"></c:set>
                                                </tr>
                                            </table>
                                        </c:forEach>

                                        <table cellspacing="1" class="carttable">
                                            <tr>
                                                <td style="text-align:right; padding-right:40px;"><font
                                                        style="color:#FF0000">合计：&nbsp;&nbsp;${count }元</font></td>
                                            </tr>
                                            <input type="hidden" name="money" value="${count }"/>
                                        </table>

                                        <p>
                                            收货地址：<input name="receiverAddress" type="text" value="xxx"
                                                        style="width:350px" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"></a>
                                            <br /> 收货人：&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input name="receiverName" type="text" value="xxx"
                                                style="width:150px" />&nbsp;&nbsp;&nbsp;&nbsp;
                                            <a href="#"></a>
                                            <br /> 联系方式：<input type="text" name="receiverPhone"
                                                               value="xxx" style="width:150px" />&nbsp;&nbsp;&nbsp;&nbsp;

                                        </p>
                                        <hr />
                                        <p style="text-align:right">
                                            <img src="/images/gif53_029.gif" onclick="_submitOrder()" width="204" height="51"
                                                 border="0" />
                                        </p></td>
                                </tr>
                            </table></td>

                        </tr>
                    </table>
                </form></td>
            <script type="text/javascript">
                function _submitOrder(){
                    document.getElementById("orderForm").submit();
                }
            </script>
        </tr>
    </table>
</div>