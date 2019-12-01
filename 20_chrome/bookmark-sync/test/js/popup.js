document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('setting').addEventListener('click', setting);
    document.getElementById('home').addEventListener('click', home);
    document.getElementById('vip').addEventListener('click', vip);
});

function home() {
    window.open("https://api.icql.work/bookmark");
}

function setting(e) {
    var reg = new RegExp("^[0-9]+$");
    var nameOrEmail = document.getElementById("nameOrEmail");
    var password = document.getElementById("password");
    var timeInterval = document.getElementById("timeInterval");

    if (nameOrEmail.value.trim() == "") {
        alert("用户名或邮箱不能为空!");
        return false;
    } else if (password.value.trim() == "") {
        alert("密码不能为空!");
        return false;
    } else if (!reg.test(timeInterval.value)) {
        alert("同步时间间隔必须为正整数！");
        return false;
    } else {
        $.ajax({
            type: 'POST',
            url: "https://api.icql.work/auth",
            data: {
                "account": nameOrEmail.value,
                "password": password.value,
                "expire": -1
            },
            dataType: "json",
            success: function (result) {
                localStorage.token = result.data;
                localStorage.nameOrEmail = nameOrEmail.value;
                localStorage.password = Base64.encode("icql@auth&" + password.value);
                localStorage.bookmarkversion = 0;
                alert("登录并设置成功！");
            },
            error: function (result) {
                if (result.responseJSON) {
                    let msg = result.responseJSON.msg + "，" + result.responseJSON.data;
                    console.log(msg);
                    alert(msg);
                } else {
                    alert("登录失败");
                }
            }
        });

        localStorage.timeInterval = parseInt(timeInterval.value.trim());
    }
    return true;
}

function vip(e) {
    chrome.tabs.query({'active': true, 'lastFocusedWindow': true}, function (tabs) {
        let url = 'https://api.927jx.com/vip/jx.php?url=' + tabs[0].url;
        window.open(url);
    });
}

window.onload = function () {
    document.getElementById("bookmarkversion").innerHTML = localStorage.bookmarkversion ? localStorage.bookmarkversion : "";
    document.getElementById("nameOrEmail").value = localStorage.nameOrEmail ? localStorage.nameOrEmail : "";
    document.getElementById("password").value = localStorage.password ? Base64.decode(localStorage.password).substring(10) : "";
    document.getElementById("timeInterval").value = localStorage.timeInterval ? localStorage.timeInterval : "";
}