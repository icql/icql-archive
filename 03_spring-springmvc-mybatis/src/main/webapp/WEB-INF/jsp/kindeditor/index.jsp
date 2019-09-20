<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>kindeditor</title>
    <link href="${pageContext.request.contextPath}/js/kindeditor-4.1.10/themes/default/default.css" type="text/css"
          rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery/jquery.min.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/kindeditor-all-min.js"></script>
    <script type="text/javascript" charset="utf-8"
            src="${pageContext.request.contextPath}/js/kindeditor-4.1.10/lang/zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.icql.js"></script>
</head>
<body>
<input type="button" id="selectImage" value="批量图片上传"/>
<input type="hidden" name="image"/>

<form method="post" action="/kindeditor/api/form">
    <textarea style="width:800px;height:300px;visibility:hidden;" name="desc"></textarea>
    <input type="submit" value="提交"/>
</form>

<script>
    $(function () {
        $.kindEditor("desc");
        $.kindeditorMultiImage("selectImage", "image", "/kindeditor/api/upload", "file");
    });
</script>
</body>
</html>
