<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="easyui-layout" fit="true">
    <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
        <form id="editPasswordForm">
            <table cellpadding=3>
                <tr>
                    <td>旧密码：</td>
                    <td><input id="txtOldPass" type="Password" class="easyui-validatebox"
                               required="true" autocomplete="off" data-options="validType:'length[4,8]'"
                    /></td>
                </tr>
                <tr>
                    <td>新密码：</td>
                    <td><input id="txtNewPass" type="Password" class="easyui-validatebox"
                               required="true" autocomplete="off" data-options="validType:'length[4,8]'"
                    /></td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td><input id="txtRePass" type="Password" class="easyui-validatebox"
                               required="true" autocomplete="off" data-options="validType:'length[4,8]'"
                    /></td>
                </tr>
            </table>
        </form>
    </div>
    <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
        <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0)" onclick="submitForm()">确定</a>
        <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0)" onclick="ICQL.easyui.winClose()">取消</a>
    </div>
</div>
<script>
    function submitForm() {
        //进行表单校验
        var v = $("#editPasswordForm").form("validate");//对应表单中的所有输入框进行校验
        if (v) {//表单校验通过
            //判断两次输入是否一致
            var v1 = $("#txtNewPass").val();
            var v2 = $("#txtRePass").val();
            if (v1 == v2) {
                //输入一致，发送ajax请求，修改当前用户的密码
                var url = "${pageContext.request.contextPath}/";
                $.post(url, {"password": v1}, function (data) {
                    if (data.msg == 200) {
                        //修改密码成功
                        $.messager.alert("提示信息", "密码修改成功！", "info");
                    } else {
                        //修改失败
                        $.messager.alert("提示信息", "密码修改失败！", "warning");
                    }
                    //关闭修改密码的窗口
                    ICQL.easyui.winClose();
                });
            } else {
                //输入不一致，提示用户输入不一致
                $.messager.alert("提示信息", "两次输入密码不一致！", "warning");
            }
        }
    }
</script>

