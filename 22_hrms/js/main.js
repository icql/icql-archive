$(function() {
    //左侧导航界面收缩
    $("img.mid-btn").click(function() {
        if ($("nav").css("display") == "block") {
            $("nav").css("display", "none");
            $("img.mid-btn").attr("src", "./img/midbtn2.gif");
            $("img.mid-btn").attr("alt", "显示导航条");
            $("img.mid-btn").attr("title", "显示导航条");
            $("#imgBtn").css("left", "4px");
            $("#sec").css("left", "12px");
        } else {
            $("nav").css("display", "block");
            $("img.mid-btn").attr({
                "src": "./img/midbtn1.gif",
                "alt": "隐藏导航条",
                "title": "隐藏导航条"
            });
            $("#imgBtn").css("left", "169px");
            $("#sec").css("left", "178px");
        }
    });
    //导航树结构
    $("li.home").click(function() {
        $("ul.root").toggle();
    });
    $("span.root-all").click(function() {
        $("li.basic").toggle();
        if ($("#rootImg").attr("src") == "./img/nopen.gif") {
            $("#rootImg").attr("src", "./img/nclose.gif");
        } else {
            $("#rootImg").attr("src", "./img/nopen.gif");
        }
    });
    //读取json文本数据
    $.getJSON("./data/a6924.json?t=" + Math.random(), function(data, status) {
        //头部欢迎信息
        if (data.userinfo.sex == "男") {
            $("#mainName").html(data.userinfo.username + " 先生:");
        } else {
            $("#mainName").html(data.userinfo.username+ " 女士:");
        }
    });
});
