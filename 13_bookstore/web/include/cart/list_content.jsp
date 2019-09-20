<%--
  Created by IntelliJ IDEA.
  User: A6924
  Date: 2018/6/7
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript">
    function changeNum(id,num,totalCount){
        num = parseInt(num);
        totalCount =parseInt(totalCount);

        if(num<1){
            if(confirm("是否确认要删除此商品？")){
                num = 0;
            }else{
                num=1;
            }
        }

        if(num>totalCount){
            alert("购买数量不能大于库存数量！");
            num=totalCount;
        }
        location.href="${pageContext.request.contextPath}/cart/update?id="+id+"&num="+num;
    }
</script>

<div id="divpagecontent">
    <table width="100%" border="0" cellspacing="0">
        <tr>

            <td>
                <div style="text-align:right; margin:5px 10px 5px 0px">
                    <a href="/index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车
                </div>

                <table cellspacing="0" class="infocontent">
                    <tr>
                        <td><img src="/images/ad/page_ad.jpg" width="666" height="89"/>
                            <table width="100%" border="0" cellspacing="0">
                                <tr>
                                    <td><img src="/images/buy1.gif" width="635" height="38"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <table cellspacing="1" class="carttable">
                                            <tr>
                                                <td width="10%">序号</td>
                                                <td width="30%">商品名称</td>
                                                <td width="10%">价格</td>
                                                <td width="20%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数量</td>
                                                <td width="10%">库存</td>
                                                <td width="10%">小计</td>
                                                <td width="10%">取消</td>
                                            </tr>
                                        </table>
                                        <c:set var="sum" value="0"> </c:set>
                                        <c:forEach items="${mapCart}" var="entry" varStatus="vs">
                                            <table width="100%" border="0" cellspacing="0">
                                                <tr>
                                                    <td width="10%">${vs.count }</td>
                                                    <td width="30%">${entry.value.name }</td>
                                                    <td width="10%">${entry.value.price }</td>
                                                    <td width="20%">
                                                        <input type="button" value='-' style="width:20px" onclick="changeNum('${entry.key.cart_id}','${entry.key.product_num-1 }','${entry.value.pnum }')">
                                                        <input name="text" type="text" value="${entry.key.product_num}" style="width:40px;text-align:center"/>
                                                        <input type="button" value='+' style="width:20px" onclick="changeNum('${entry.key.cart_id}','${entry.key.product_num+1 }','${entry.value.pnum }')">
                                                    </td>
                                                    <td width="10%">${entry.value.pnum }</td>
                                                    <td width="10%">${entry.key.product_num * entry.value.price }</td>

                                                    <td width="10%">
                                                        <a href="${pageContext.request.contextPath}//cart/update?id=${entry.key.cart_id}&num=0" style="color:#FF0000; font-weight:bold">X</a></td>
                                                </tr>
                                            </table>
                                            <c:set var="sum" value="${sum+entry.key.product_num*entry.value.price }"> </c:set>
                                        </c:forEach>

                                        <table cellspacing="1" class="carttable">
                                            <tr>
                                                <td style="text-align:right; padding-right:40px;"><font
                                                        style="color:#FF6600; font-weight:bold">合计：&nbsp;&nbsp;${sum}元</font>
                                                </td>
                                            </tr>
                                        </table>
                                        <div style="text-align:right; margin-top:10px">
                                            <a
                                                    href="${pageContext.request.contextPath}/product/list"><img
                                                    src="/images/gwc_jx.gif" border="0"/> </a>

                                            &nbsp;&nbsp;&nbsp;&nbsp;<a
                                                href="${pageContext.request.contextPath}/order/settlement"><img
                                                src="/images/gwc_buy.gif" border="0"/> </a>
                                        </div>
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
