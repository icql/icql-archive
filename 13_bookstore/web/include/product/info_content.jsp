<%--
  Created by IntelliJ IDEA.
  User: icql
  Date: 2018/6/6
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="divpagecontent">
    <table width="100%" border="0" cellspacing="0">
        <tr>

            <td>
                <div style="text-align:right; margin:5px 10px 5px 0px">
                    <a href="#">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;<a
                        href="#">&nbsp;${b.category}</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${b.name}
                </div>


                <table cellspacing="0" class="infocontent">
                    <tr>
                        <td><img src="/images/ad/page_ad.jpg" width="645" height="84"/>

                            <table width="100%%" border="0" cellspacing="0">
                                <tr>
                                    <td width="30%">

                                        <div class="divbookcover">
                                            <p>
                                                <img src="#" width="213" height="269" border="0"/>
                                            </p>
                                        </div>

                                        <div style="text-align:center; margin-top:25px">
                                            <a href="${pageContext.request.contextPath }/cart/add?id=${b.id}">
                                                <img src="/images/buybutton.gif" border="0"/>
                                            </a>
                                        </div>
                                    </td>
                                    <td style="padding:20px 5px 5px 5px"><img
                                            src="/images/miniicon3.gif" width="16" height="13"/><font
                                            class="bookname">&nbsp;&nbsp;${b.name }</font>

                                        <hr/>
                                        售价：<font color="#FF0000">￥:${b.price }元</font>
                                        <hr/>
                                        类别：${b.category }

                                        <hr/>
                                        <p>
                                            <b>内容简介：</b>
                                        </p>${b.description }</td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
