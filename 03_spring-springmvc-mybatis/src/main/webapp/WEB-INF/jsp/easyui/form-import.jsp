<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="formImpot" style="padding:30px 70px 50px 70px">
    <div style="margin-bottom:10px">
        模板:<a href="${pageContext.request.contextPath}/file/grid-import-templete.xls">下载</a>
    </div>
    <div style="margin-bottom:10px">
        <input class="easyui-filebox" name="file" data-options="buttonText:'选择文件',required:true" style="width:100%">
    </div>
    <div>
        <a href="#" class="easyui-linkbutton" style="width:100%" onclick="submitForm()">上传</a>
    </div>
</form>
<script>
    function submitForm() {
        var file = $("input[name='file']").val();
        if (file.substr(file.indexOf(".")) != ".xls") {
            $.messager.alert("提示", "只能上传 .xls 文件！");
            return;
        }

        $.messager.progress({'text': '正在上传,请稍后......', 'showType': 'fade'});
        $('#formImpot').ajaxSubmit({
            url: "${pageContext.request.contextPath}/easyui/api/grid/import",
            method: "post",
            dataType: "json",
            success: function (data) {
                if (data.status == 200) {
                    $.messager.alert('提示', '导入成功！');
                } else {
                    $.messager.alert('提示', '导入失败！' + data.msg);
                }
                $.messager.progress('close')
            }
        });
    }
</script>
