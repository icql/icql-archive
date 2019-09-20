<%--
  Created by IntelliJ IDEA.
  User: A6924
  Date: 2018/6/4
  Time: 11:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="divcontent">
    <form action="/user/register"
          method="post">
        <table width="850px" border="0" cellspacing="0">
            <tr>
                <td style="padding:30px">
                    <h1>新会员注册 ${requestScope.msgRegister}</h1>

                    <table width="70%" border="0" cellspacing="2" class="upline">
                        <tr>
                            <td style="text-align:right; width:20%">会员邮箱：</td>
                            <td style="width:40%">
                                <input type="text" class="textinput" name="email" value="${param.email}"/>
                            </td>
                            <td><font color="#999999">请输入有效的邮箱地址</font></td>
                        </tr>
                        <tr>
                            <td style="text-align:right">会员名：</td>
                            <td>
                                <input type="text" class="textinput" name="username" value="${param.username}" />
                            </td>
                            <td>
                            <font color="#999999">用户名设置至少6位</font>
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align:right">密码：</td>
                            <td>
                                <input type="password" class="textinput" name="password" value="${param.password}" />
                            </td>
                            <td><font color="#999999">密码设置至少6位</font></td>
                        </tr>
                        <tr>
                            <td style="text-align:right">重复密码：</td>
                            <td>
                                <input type="password" class="textinput" name="repassword" value="${param.repassword}" />
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td style="text-align:right">性别：</td>
                            <td colspan="2">
                                &nbsp;&nbsp;
                                <input type="radio" name="gender" value="男" checked="checked"/> 男
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                <input type="radio" name="gender" value="女"/> 女
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align:right">联系电话：</td>
                            <td colspan="2">
                                <input type="text" class="textinput" style="width:350px" name="telephone" value="${param.telephone}" />
                            </td>
                        </tr>
                        <tr>
                            <td style="text-align:right">个人介绍：</td>
                            <td colspan="2"><textarea class="textarea" name="introduce">${param.introduce}</textarea>
                            </td>
                        </tr>

                    </table>


                    <h1>注册校验</h1>
                    <table width="80%" border="0" cellspacing="2" class="upline">
                        <tr>
                            <td style="text-align:right; width:20%">输入校验码：</td>
                            <td style="width:50%"><input type="text" class="textinput" name="validateCode"/>
                            </td>
                            <td>${requestScope.msgValidateCode}</td>
                        </tr>
                        <tr>
                            <td style="text-align:right;width:20%;">&nbsp;</td>
                            <td colspan="2" style="width:50%"><img
                                    src="/validateCode" width="180"
                                    height="30" class="textinput" style="height:30px;" id="img"/>&nbsp;&nbsp;
                                <a href="javascript:void(0);" onclick="changeImage()">看不清换一张</a>
                            </td>
                        </tr>
                    </table>


                    <table width="70%" border="0" cellspacing="0">
                        <tr>
                            <td style="padding-top:20px; text-align:center"><input
                                    type="image" src="/images/signup.gif" name="submit" border="0">
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </form>
</div>

<script>
    //更改验证码
    function changeImage() {
        document.getElementById("img").src = "/validateCode?time="
            + new Date().getTime();
    }
</script>