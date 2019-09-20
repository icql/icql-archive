<%--
  Created by IntelliJ IDEA.
  User: A6924
  Date: 2018/6/4
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="divcontent">
    <table width="850px" border="0" cellspacing="0">
        <tr>
            <td style="padding:30px; text-align:center">
                <table width="60%"
                       border="0" cellspacing="0" style="margin-top:70px">
                    <tr>
                        <td style="width:98"><img
                                src="/images/IconTexto_WebDev_009.jpg" width="128" height="128"/>
                        </td>
                        <td style="padding-top:30px"><font
                                style="font-weight:bold; color:#FF0000">注册成功,别忘记激活帐户啊</font><br/>
                            <br/> <a href="index.jsp"><span id="second">5</span>秒后自动为您转跳回首页</a>
                        </td>
                    </tr>
                </table>
                <h1>&nbsp;</h1></td>
        </tr>
    </table>
</div>


<script>
    var interval;

    function changeSecond() {
        var second = document.getElementById("second");
        var svalue = second.innerHTML;
        svalue = svalue - 1;

        if (svalue == 0) {
            window.clearInterval(interval);
            location.href = "/index.jsp";
            return;
        }
        second.innerHTML = svalue;
    }

    function startSecond() {
        interval = window.setInterval("changeSecond()", 1000);
    };

    window.onload = function () {
        startSecond();
    };
</script>