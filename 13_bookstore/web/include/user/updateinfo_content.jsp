<%--
  Created by IntelliJ IDEA.
  User: A6924
  Date: 2018/6/5
  Time: 14:37
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
                        <td class="listtd"><img src="/images/miniicon.gif" width="9" height="6"/>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="${pageContext.request.contextPath }/user/getinfo?username=${user.username}">用户信息修改</a>
                        </td>
                    </tr>

                    <tr>
                        <td class="listtd"><img src="/images/miniicon.gif" width="9" height="6"/>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/order/list">订单查询</a>
                        </td>
                    </tr>

                    <tr>
                        <td class="listtd"><img src="/images/miniicon.gif" width="9" height="6"/>&nbsp;&nbsp;&nbsp;&nbsp;
                            <a href="/user/logout">用戶退出</a></td>
                    </tr>
                </table>
            </td>
            <td>
                <div style="text-align:right; margin:5px 10px 5px 0px">
                    <a href="index.jsp">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;
                    <a href="/user/account.jsp">&nbsp;我的帐户</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;用户信息修改
                </div>


                <table cellspacing="0" class="infocontent">
                    <tr>
                        <td>
                            <form action="${pageContext.request.contextPath }/user/updateinfo" method="post">
                                <table width="100%" border="0" cellspacing="2" class="upline">
                                    <tr>
                                        <input name="username" value="${reqUser.username}" type="hidden"/>
                                        <td style="text-align:right">会员名：</td>
                                        <td style="padding-left:20px">${reqUser.username }</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align:right">注册时间：</td>
                                        <td style="padding-left:20px">${reqUser.registTime}</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align:right; width:20%">会员邮箱：</td>
                                        <td>
                                            <input name="email" type="text" value="${reqUser.email}" class="textinput"/>
                                        </td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align:right">修改密码：</td>
                                        <td><input type="password" name="password" class="textinput"/></td>
                                        <td><font color="#999999">密码设置至少6位，请区分大小写</font></td>
                                    </tr>
                                    <tr>
                                        <td style="text-align:right">重复密码：</td>
                                        <td><input type="password" class="textinput"/></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td style="text-align:right">性别：</td>
                                        <td colspan="2">&nbsp;&nbsp;
                                            <input type="radio" name="gender" value="男" ${reqUser.gender== "男"? "checked='checked' ":"" } />
                                            男 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="radio" name="gender" value="女" ${reqUser.gender== "女"? "checked='checked' ":"" } />
                                            女
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align:right">联系方式：</td>
                                        <td colspan="2">
                                            <input name="telephone" type="text" value="${reqUser.telephone }" class="textinput"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align:right">用户介绍：</td>
                                        <td colspan="2">
                                            <textarea name="introduce" id="" cols="30" rows="10">${reqUser.introduce}</textarea>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td style="text-align:right">&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>


                                <p style="text-align:center">

                                    <input type="image" src="/images/botton_gif_025.gif" border="0">

                                </p>
                                <p style="text-align:center">&nbsp;</p>
                            </form>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>