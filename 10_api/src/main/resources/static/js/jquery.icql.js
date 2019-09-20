var ICQL = {
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
    POST: $.POST = function (url, data, successCallback, errCallback) {
        $.ajax({
            url: url,
            type: "post",
            contentType: 'application/json;charset=utf-8',
            dataType: "json",
            data: data,
            timeout: 60000,
            success: function (msg) {
                successCallback(msg);
            },
            error: function (msg) {
                errCallback(msg);
            }
        });
    },
    PUT: $.PUT = function (url, data, successCallback, errCallback) {
        data._method = "PUT";
        $.ajax({
            url: url,
            type: "post",
            contentType: 'application/json;charset=utf-8',
            dataType: "json",
            data: data,
            timeout: 60000,
            success: function (msg) {
                successCallback(msg);
            },
            error: function (msg) {
                errCallback(msg);
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
    }
};
