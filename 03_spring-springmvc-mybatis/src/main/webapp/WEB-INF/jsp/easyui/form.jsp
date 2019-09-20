<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div style="padding:10px">
    <form id="itemEditForm" class="itemForm">
        <input type="hidden" name="defaultVal"/>
        <input type="hidden" name="version"/>

        <table cellpadding="5">
            <tr>
                <td>id:</td>
                <td><input type="text" name="id" readonly="readonly" style="width: 300px;border: 0px"
                           onkeypress="return false"
                           onkeydown="return false"/></td>
            </tr>
            <tr>
                <td>name:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="name"
                           data-options="required:true,multiline:true,validType:'length[0,150]'"
                           style="width: 280px;height:60px;"/>
                </td>
            </tr>
            <tr>
                <td>price:</td>
                <td>
                    <input class="easyui-numberbox" type="text" name="price"
                           data-options="required:true,min:1,max:99999999,precision:2"/>
                </td>
            </tr>
            <tr>
                <td>status:</td>
                <td>
                    <select class="easyui-combobox" name="status" style="">
                        <option value="0">正常</option>
                        <option value="1">错误</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
    <div style="padding:5px">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>
    </div>
</div>

<script>
    //重置表单
    function clearForm() {
        $.messager.confirm("确认", "确认重置表单？所有数据将恢复为初始值", function (r) {
            if (r) {
                //获取表单初始值
                var defaultVal = $("input[name='defaultVal']").val();

                //编辑/新增
                if (defaultVal) {
                    defaultVal = JSON.parse(defaultVal);
                    $("#itemEditForm").form("load", defaultVal);
                } else {
                    $('#itemEditForm').form('reset');
                }
            }
        });
    }

    //提交表单
    function submitForm() {
        //有效性验证
        if (!$('#itemEditForm').form('validate')) {
            $.messager.alert('提示', '表单还未填写完成!');
            return;
        }

        $.POST("${pageContext.request.contextPath}/easyui/api/grid", $("#itemEditForm").serialize(), function (data) {
            //获取表单初始值，根据此值来判断 新增/编辑
            var defaultVal = $("input[name='defaultVal']").val();

            if (data.status == 200) {
                if (defaultVal) {
                    $("input[name='version']").val(parseInt($("input[name='version']").val()) + 1);
                    $.messager.alert('提示', '保存成功！');
                } else {
                    $.messager.confirm("确认", "保存成功！确认要继续新增？", function (r) {
                        if (!r) {
                            $.easyuiWinClose();
                        }
                    });
                }
            } else {
                $.messager.alert('提示', '保存失败！' + data.msg);
                if (defaultVal) {
                    $.easyuiWinClose();
                }
            }
        });
    }

</script>