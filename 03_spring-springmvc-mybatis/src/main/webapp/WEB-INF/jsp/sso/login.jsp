<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>sso login</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
</head>
<body>
sso login
<form id="form">
    用户名<input type="text" id="username" name="username" autocomplete="off"/>
    密码<input type="password" id="password" name="password" autocomplete="off"/>
    <input type="button" id="submit" value="登录"/>
</form>
<script type="text/javascript">
    var service = "${service}";
    var LOGIN = {
        checkInput: function () {
            if ($("#username").val() == "") {
                alert("用户名不能为空");
                $("#username").focus();
                return false;
            }
            if ($("#password").val() == "") {
                alert("密码不能为空");
                $("#password").focus();
                return false;
            }
            return true;
        },
        login: function () {
            if (this.checkInput()) {
                $.post("/sso/login4form", $("#form").serialize(), function (data) {
                    if (data.status == 200) {
                        alert("登录成功！");

                        if (service == "") {
                            location.href = "/sso/success?ticket=" + data.data;
                        } else {
                            location.href = service + "?ticket=" + data.data;
                        }
                    } else {
                        alert("登录失败，原因是：" + data.msg);
                        $("#username").focus();
                    }
                });
            }
        }

    };

    $(function () {
        $("#submit").click(function () {
            LOGIN.login();
        });
    });
</script>
</body>
</html>
