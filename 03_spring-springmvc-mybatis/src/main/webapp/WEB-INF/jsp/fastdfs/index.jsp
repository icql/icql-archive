<%--
  Created by IntelliJ IDEA.
  User: icql
  Date: 2018/11/11
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>test</title>
</head>
<body>
<form method="post" action="/fastdfs/api/upload" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
