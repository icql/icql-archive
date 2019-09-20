var ICQL = {
    rest: {
        GET: $.GET = function (url, data, callback) {
            $.ajax({
                url: url,
                type: "get",
                dataType: "json",
                timeout: 10000,
                data: data,
                success: function (data) {
                    callback(data);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest.status);
                    console.log(XMLHttpRequest.readyState);
                    console.log(textStatus);
                }
            });
        },
        POST: $.POST = function (url, data, callback) {
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data: data,
                timeout: 60000,
                success: function (msg) {
                    callback(msg);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest.status);
                    console.log(XMLHttpRequest.readyState);
                    console.log(textStatus);
                }
            });
        },
        PUT: $.PUT = function (url, data, callback) {
            data._method = "PUT";
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data: data,
                timeout: 20000,
                success: function (msg) {
                    callback(msg);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest.status);
                    console.log(XMLHttpRequest.readyState);
                    console.log(textStatus);
                }
            });
        },
        DELETE: $.DELETE = function (url, data, callback) {
            data._method = "DELETE";
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                data: data,
                success: function (msg) {
                    callback(msg);
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest.status);
                    console.log(XMLHttpRequest.readyState);
                    console.log(textStatus);
                }
            });
        },
        GETJSONP: $.GETJSONP = function (url, callbackFuncName, data) {
            $.ajax({
                url: url,
                type: "GET",
                dataType: "jsonp",
                data: data,
                jsonp: "callback", ///指定参数名称，默认为callback
                jsonpCallback: callbackFuncName, //指定回调函数名称
                timeout: 20000,
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    console.log(XMLHttpRequest.status);
                    console.log(XMLHttpRequest.readyState);
                    console.log(textStatus);
                }
            });
        }
    },
    formatter: {
        dateFormat: Date.prototype.format = function (format) {
            var o = {
                "M+": this.getMonth() + 1, //month
                "d+": this.getDate(), //day
                "h+": this.getHours(), //hour
                "m+": this.getMinutes(), //minute
                "s+": this.getSeconds(), //second
                "q+": Math.floor((this.getMonth() + 3) / 3), //quarter
                "S": this.getMilliseconds() //millisecond
            };
            if (/(y+)/.test(format)) {
                format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp("(" + k + ")").test(format)) {
                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
                }
            }
            return format;
        },
        formatDate: $.DATEFORMAT = function (value) {
            var date = new Date(value);
            return date.format("yyyy-MM-dd");
        },
        formatDateTime: $.DATETIMEFORMAT = function (value) {
            var date = new Date(value);
            return date.format("yyyy-MM-dd hh:mm:ss");
        }
    },
    fun: {
        FILEDOWNLOAD: $.FILEDOWNLOAD = function (url, data) {
            url += "?";
            for (var d in data) {
                url += encodeURIComponent(d) + "=" + encodeURIComponent(data[d]) + "&";
            }
            window.location.href = url;
        },
    },


    easyui: {
        treeInit: $.easyuiTreeInit = function (treeEleId, url) {
            $('#' + treeEleId).tree({
                url: url,
                method: "get",
                onClick: function (node) {
                    var tabs = $("#tabs");
                    if ($('#menu').tree("isLeaf", node.target)) {
                        var tab = tabs.tabs("getTab", node.text);
                        if (tab) {
                            tabs.tabs("select", node.text);
                        } else {
                            tabs.tabs('add', {
                                title: node.text,
                                href: node.attributes.url,
                                closable: true,
                                bodyCls: "content"
                            });
                        }
                    } else {
                        $('#menu').tree("toggle", node.target);
                    }
                }
            })
        },
        winOpen: $.easyuiWinOpen = function (options) {
            var newOptions = {
                title: options.title ? options.title : " ",
                href: options.href,
                width: options.width ? options.width : "80%",
                height: options.height ? options.height : "80%",
                style: options.style ? "padding:10px;" + options.style : "padding:10px;",
                iconCls: options.iconCls ? options.iconCls : "icon-save",
                modal: true,
                closed: true,
                onClose: function () {
                    if (options.onClose) {
                        options.onClose.call(this);//调用回调函数
                        $(this).window("destroy");//销毁窗口
                    } else {
                        $(this).window("destroy");
                    }
                },
                onLoad: function () {
                    if (options.onLoad) {
                        options.onLoad.call(this);
                    }
                }
            };

            $("body").append("<div id='window'></div>");
            $('#window').window(newOptions).window("open");
        },
        winClose: $.easyuiWinClose = function () {
            $('#window').window('close', false);
        },
        gridGetSelIds: $.easyuiGridGetSelIds = function (gridId) {
            var list = $("#" + gridId);
            var sels = list.datagrid("getSelections");
            var ids = [];
            for (var i in sels) {
                ids.push(sels[i].id);
            }
            ids = ids.join(",");
            return ids;
        },
        formatPrice: $.easyuiFormatPrice = function (value, row, index) {
            return value.toFixed(2);
        },
        formatStatus: $.easyuiFormatStatus = function (value, row, index) {
            if (value == 0) {
                return '正常';
            } else if (value == 1) {
                return '<span style="color:red;">错误</span>';
            } else {
                return '未知';
            }
        }
    },

    kindeditor: {
        KindEditor: $.kindEditor = function (inputName) {
            KindEditor.create($("[name=" + inputName + "]"));
        },
        PluginMultiImage: $.kindeditorMultiImage = function (btnElementId, inputName, url, filePostName) {
            KindEditor.ready(function (K) {
                var editor = K.editor({
                    filePostName: filePostName,//后台文件参数名
                    uploadJson: url,//上传地址
                    dir: "image"//类型
                });
                var ele = $("#" + btnElementId);
                ele.after("<div id='imgView'></div>");
                K(ele).click(function () {
                    editor.loadPlugin('multiimage', function () {
                        editor.plugin.multiImageDialog({
                            clickFn: function (urlList) {
                                var div = K('#imgView');
                                div.html('');
                                var imgUrlArray = [];
                                K.each(urlList, function (i, data) {
                                    imgUrlArray.push(data.url);
                                    div.append('<img src="' + data.url + '">');
                                });
                                $("[name=" + inputName + "]").val(imgUrlArray.join(","));
                                editor.hideDialog();
                            }
                        });
                    });
                });
            });
        }
    }
};
