<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>login</title>
</head>
<body>
<form method="post" action="/shiro/api/login">
    用户名<input type="text" name="username" autocomplete="off"/>
    密码<input type="password" name="password" autocomplete="off"/>
    <input type="submit" value="登录"/>
</form>
</body>
</html>
