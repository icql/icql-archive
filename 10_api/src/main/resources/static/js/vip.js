$(function () {
    ICQL.GET("/vip/message", null, function (result) {
        $("#playIframe").contents().find("body").html(result.data);
    });

    ICQL.GET("/vip/api", null, function (result) {
        var num = 1;
        for (i in result.data) {
            $("#interface").append("<option value ='" + result.data[i] + "'>" + num + "号接口</option>");
            num++;
        }
    });
});

$("#instructions").click(function () {
    ICQL.GET("/vip/instructions", null, function (result) {
        alert(result.data);
    });
});

$("#changelog").click(function () {
    ICQL.GET("/vip/updatelog", null, function (result) {
        alert(result.data);
    });
});

$("#btnAlipay").click(function () {
    $("#alipay").toggle();
});


/* js方法 */
function play(type) {
    if ($("#url").val() == "") {
        alert("url地址不能为空！");
        return;
    }
    if (type = 0) {
        $("#playIframe").attr("src", $("#interface").val() + $("#url").val());
    } else {
        window.open($("#interface").val() + $("#url").val(), '_blank');
    }
}