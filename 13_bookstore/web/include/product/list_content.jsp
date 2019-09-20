<%--
  Created by IntelliJ IDEA.
  User: A6924
  Date: 2018/6/6
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="divpagecontent">
    <table width="100%" border="0" cellspacing="0">
        <tr>
            <td>
                <div style="text-align:right; margin:5px 10px 5px 0px">
                    <a href="/index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${mapWhere['category']}&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;图书列表
                </div>

                <table cellspacing="0" class="listcontent">
                    <tr>
                        <td>
                            <h1>商品目录</h1>
                            <hr/>
                            <h1>${mapWhere['category']}</h1>&nbsp;&nbsp;&nbsp;&nbsp;共${page.count}种商品
                            <hr/>
                            <div style="margin-top:20px; margin-bottom:5px">
                                <img src="/images/productlist.gif" width="100%" height="38"/>
                            </div>

                            <table cellspacing="0" class="booklist">
                                <tr>
                                    <c:forEach items="${page.lsT }" var="p">
                                        <td>
                                            <div class="divbookpic">
                                                <p>
                                                    <a href="#">
                                                        <img src="/upload/${p.imgurl}" width="115" height="129" border="0"/>
                                                    </a>
                                                </p>
                                            </div>

                                            <div class="divlisttitle">
                                                <a href="${pageContext.request.contextPath }/product/info?id=${p.id}">
                                                    书名:${p.name}<br/>售价:${p.price }
                                                </a>
                                            </div>
                                        </td>
                                    </c:forEach>
                                </tr>
                            </table>


                            <div class="pagination">
                                <ul>
                                    <li class="disablepage">
                                        <a href="${pageContext.request.contextPath  }/product/list?currentPage=${page.currentPage==1?1:page.currentPage-1}&category=${mapWhere['category']}&name=${mapWhere['name']}">
                                            &lt;&lt;上一页
                                        </a>
                                    </li>
                                    <li>第${page.currentPage }页/共${page.totalPage }页</li>

                                    <li class="nextPage">
                                        <a href="${pageContext.request.contextPath  }/product/list?currentPage=${page.currentPage==page.totalPage?page.totalPage:page.currentPage+1}&category=${mapWhere['category']}&name=${mapWhere['name']}">
                                            &lt;&lt;下一页
                                        </a>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
