$(function () {
    $.getJSON("data/icql.json", function (data, status) {
        var interfaceNum = getJsonLength(data.interface);
        for (i in data.interface) {
            $("#interface").append("<option value ='" + i + "'>" + i + "</option>");
        }
    });
});

$("#instructions").click(function () {
    $.getJSON("data/icql.json", function (data, status) {
        alert(data.instructions.title + '：\n' + data.instructions.content);
    });
});

$("#changelog").click(function () {
    $.getJSON("data/icql.json", function (data, status) {
        alert(data.changelog.title + '：\n' + data.changelog.content);
    });
});

$("#btnAlipay").click(function () {
    $("#alipay").toggle();
});



/* js方法 */
function play() {
    if ($("#url").val() == "") {
        alert("url地址不能为空！");
    }
    else {
        $.getJSON("data/icql.json", function (data, status) {
            var interface = data.interface[$("#interface").val()];
            if ($("#interface").val().substring(0,1) != "+") {
                $("#playIframe").attr("src", interface + $("#url").val());
            }
            else {
                window.open(interface + $("#url").val(), '_blank');
            }
        });
    }
}

function getJsonLength(jsonData) {
    var jsonLength = 0;
    for (var item in jsonData) {
        jsonLength++;
    }
    return jsonLength;
}