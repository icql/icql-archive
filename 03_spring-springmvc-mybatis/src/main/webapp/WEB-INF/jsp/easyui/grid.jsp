<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table class="easyui-datagrid" id="itemList" title="数据列表"
       data-options="url:'${pageContext.request.contextPath}/easyui/api/grid',method:'get',rownumbers:true,fitColumns:true,onDblClickRow:gridDblClickRow,toolbar:toolbar,singleSelect:false,collapsible:true,pagination:true,pageSize:20">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'id',width:300">ID</th>
        <th data-options="field:'name',width:200">name</th>
        <th data-options="field:'price',width:70,align:'right',formatter:$.easyuiFormatPrice">price</th>
        <th data-options="field:'status',align:'center',formatter:$.easyuiFormatStatus">status</th>
        <th data-options="field:'created',width:130,align:'center',formatter:$.DATEFORMAT">created</th>
        <th data-options="field:'updated',width:130,align:'center',formatter:$.DATETIMEFORMAT">updated</th>
    </tr>
    </thead>
</table>

<div id="itemEditWindow" class="easyui-window" title="新增/编辑"
     data-options="modal:true,closed:true,iconCls:'icon-save',href:'form'" style="width:80%;height:80%;padding:10px;">
</div>

<script>
    function gridDblClickRow(rowIndex, rowData) {
        rowData.defaultVal = JSON.stringify(rowData);
        $.easyuiWinOpen({
            title: "编辑",
            href: '${pageContext.request.contextPath}/easyui/form',
            onClose: function () {
                $("#itemList").datagrid("reload");
            },
            onLoad: function () {
                $('#itemEditForm').form('reset');
                $("#itemEditForm").form("load", rowData);
            }
        });
    }

    var toolbar = [
        {
            text: 'name：<input id="name" type="text" autocomplete="off" style="border-radius:5px;width:120px" />'
        }, {
            text: '查询',
            iconCls: 'icon-search',
            handler: function () {
                $('#itemList').datagrid('load', {
                    name: $('#name').val()
                });
            }
        },
        '-',
        {
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                $.easyuiWinOpen({
                    title: "新增",
                    href: '${pageContext.request.contextPath}/easyui/form',
                    onClose: function () {
                        $("#itemList").datagrid("reload");
                    },
                    onLoad: function () {
                        $('#itemEditForm').form('reset');
                    }
                });
            }
        }, {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                var ids = ICQL.easyui.gridGetSelIds("itemList");
                if (ids.length == 0 || ids.indexOf(',') > 0) {
                    $.messager.alert('提示', '必须只能选择一个才能编辑!');
                    return;
                }
                var data = $("#itemList").datagrid("getSelections")[0];
                data.defaultVal = JSON.stringify(data);
                $.easyuiWinOpen({
                    title: "编辑",
                    href: '${pageContext.request.contextPath}/easyui/form',
                    onClose: function () {
                        $("#itemList").datagrid("reload");
                    },
                    onLoad: function () {
                        $('#itemEditForm').form('reset');
                        $("#itemEditForm").form("load", data);
                    }
                });
            }
        }, {
            text: '删除',
            iconCls: 'icon-cancel',
            handler: function () {
                var ids = $.easyuiGridGetSelIds("itemList");
                if (ids.length == 0) {
                    $.messager.alert('提示', '未选中数据!');
                    return;
                }
                $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的数据吗？', function (r) {
                    if (r) {
                        var params = {"ids": ids};
                        $.DELETE("${pageContext.request.contextPath}/easyui/api/grid", params, function (data) {
                            if (data.status == 200) {
                                $.messager.alert('提示', '删除成功!', null, function () {
                                    $("#itemList").datagrid("reload");
                                });
                            }
                        });
                    }
                });
            }
        },
        '-',
        {
            text: '导入',
            iconCls: 'icon-remove',
            handler: function () {
                $.easyuiWinOpen({
                    title: "导入",
                    href: "${pageContext.request.contextPath}/easyui/form-import",
                    width: 500,
                    height: 250,
                    onClose: function () {
                        $("#itemList").datagrid("reload");
                    }
                });
            }
        },
        {
            text: '导出',
            iconCls: 'icon-remove',
            handler: function () {
                $.messager.confirm("确认", "确认导出已查询出的所有数据？", function (r) {
                    if (r) {
                        var params = {"name": $("#name").val()};
                        $.FILEDOWNLOAD("${pageContext.request.contextPath}/easyui/api/grid/excel", params);
                    }
                });
            }
        }];
</script>