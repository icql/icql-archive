$(function () {
    //页面索引地址区域
    var infoHead = "<header class='info-head'><ul>" +
        "<li class='info-headtext'>当前位置:员工自助>>个人信息查询</li>" +
        "</ul></header>"
    $("#info").append(infoHead);
    //人员基本信息区域
    userInfoTable();
    //考勤打卡信息区域
    attendTable();
    //迟到早退区域
    absenteeism();
    //请假记勤区域
    requestAbsence();
    //加班信息区域
    overTime();
    //加班时间核算区域
    overTimeCount();
    //切换月份更新所有数据
    var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth() + 1;
    var yearMonth1 = year + (month < 10 ? '0' : '') + month;
    var yearMonth2 = (month == 1 ? (year - 1) : year) + (month - 1 < 10 ? '0' : '') + (month == 1 ? '12' : (month - 1));
    var yearMonth3 = (month <= 2 ? (year - 1) : year) + (month - 2 < 10 ? '0' : '') + (month <= 2 ? (month + 10) : (month - 2));
    $("#month1").text(yearMonth1.substr(0, 4) + "-" + yearMonth1.substr(4, 2));
    $("#month2").text(yearMonth2.substr(0, 4) + "-" + yearMonth2.substr(4, 2));
    $("#month3").text(yearMonth3.substr(0, 4) + "-" + yearMonth3.substr(4, 2));
    $("#month1").css("color", "#FF0000");
    showData(yearMonth1.substr(0, 4), yearMonth1.substr(4, 2), yearMonth1); //写入默认当前月数据
    $("#month1").click(function () {
        $("#month1").css("color", "#FF0000");
        $("#month2,#month3").css("color", "#96F");
        showData(yearMonth1.substr(0, 4), yearMonth1.substr(4, 2), yearMonth1);
    });
    $("#month2").click(function () {
        showData(yearMonth2.substr(0, 4), yearMonth2.substr(4, 2), yearMonth2);
        $("#month2").css("color", "#FF0000");
        $("#month1,#month3").css("color", "#96F");
    });
    $("#month3").click(function () {
        showData(yearMonth3.substr(0, 4), yearMonth3.substr(4, 2), yearMonth3);
        $("#month3").css("color", "#FF0000");
        $("#month1,#month2").css("color", "#96F");
    });
})

//写入所有数据函数
function showData(yearData, monthData, keyData) {
    var firstDay = new Date(yearData, monthData - 1, 1); //当前月的第1天
    var lastDay = new Date(yearData, monthData, 0); //当前月的最后1天
    $("#titleYearMonth").text(yearData + "年" + (monthData.substr(0, 1) == "0" ? monthData.substr(1, 1) : monthData) + "月"); //写入表头年月信息,4位年+2位月
    for (var i = 0; i < $(".three3").length; i++) {
        var thisDay = new Date(yearData, monthData - 1, i + 1 - firstDay.getDay());
        var thisDayStr = (thisDay.getMonth() + 1) + "月" + thisDay.getDate() + "日";
        $(".three3").eq(i).text(thisDayStr);
        $(".three3").eq(i).attr("class", "three3");
        if ((thisDay.getFullYear() + thisDay.getMonth()) != (firstDay.getFullYear() + firstDay.getMonth())) {
            $(".three3").eq(i).attr("class", "three3 other-month");
        }
    }
    $.getJSON("./data/a6924.json?t=" + Math.random(), function (data, status) {
        //人员基本信息数据
        var infoItem = ["人员编号", "人员类别", "部门", "姓名", "性别"];
        var infoContent = [
            data.userinfo.usercode,
            data.userinfo.category,
            data.userinfo.department,
            data.userinfo.username,
            data.userinfo.sex
        ];
        for (var i = 0; i < 6; i++) {
            $(".one1").eq(i).text(infoItem[i]);
            $(".one2").eq(i).text(infoContent[i]);
        }
        //考勤打卡信息数据
        for (var i = 0; i < 42; i++) {
            $(".three4").eq(i).text("");
        }
        for (var i = 0, j = firstDay.getDay(); i < lastDay.getDate(); i++ , j++) {
            if (data[keyData][i][1] != undefined) {
                var timeData = String(data[keyData][i].slice(1));
                $(".three4").eq(j).text(timeData);
            }
        }
        //迟到早退数据
        var markNum1 = 0;
        $(".table-four tbody").empty();
        for (var i = 0; i < lastDay.getDate(); i++) {
            var thisDay = new Date(yearData, monthData - 1, i + 1);
            var month = thisDay.getMonth() + 1;
            var thisDayStr = thisDay.getFullYear() + "-" + (month < 10 ? '0' : '') + month + "-" + (i < 10 ? '0' : '') + (i + 1);
            var thisDayStrDate = thisDayStr.replace(/-/g, "");
            var absenceData = data.reqabsence[thisDayStrDate];
            var startTime = data[keyData][i][1];
            var endTime = data[keyData][i][2];

            if ((thisDay.getDay() != 0) & (thisDay.getDay() != 6)) {
                if ((startTime > "08:40") | (endTime < "17:27")) {
                    markNum1 += 1;
                    if ((startTime > "08:40") & (endTime < "17:27")) {
                        var all = "<tr><td>" +
                            thisDayStr + "</td><td>" +
                            startTime + ":00" + "</td><td>" +
                            endTime + ":00" + "</td><td>" +
                            (diffTime("08:40", roundTime(startTime)) + diffTime(roundTime(endTime), "17:27")).toFixed(1) + "0" + "</td><td>" +
                            ((absenceData != undefined) ? absenceData.reason : "") + "</td></tr>";
                        $("#tableFourBody").append(all);
                    } else if (startTime > "08:40") {
                        var late = "<tr><td>" +
                            thisDayStr + "</td><td>" +
                            startTime + ":00" + "</td><td>" +
                            "</td><td>" +
                            diffTime("08:40", roundTime(startTime)).toFixed(1) + "0" + "</td><td>" +
                            ((absenceData != undefined) ? absenceData.reason : "") + "</td></tr>";
                        $("#tableFourBody").append(late);
                    } else {
                        var early = "<tr><td>" +
                            thisDayStr + "</td><td>" +
                            "</td><td>" +
                            endTime + ":00" + "</td><td>" +
                            diffTime(roundTime(endTime), "17:27").toFixed(1) + "0" + "</td><td>" +
                            ((absenceData != undefined) ? absenceData.reason : "") + "</td></tr>";
                        $("#tableFourBody").append(early);
                    }
                }
            }
        }
        if (markNum1 == 0) {
            var nonedata = "<tr><td colspan='5'>没有满足条件的记录</td></tr>";
            $("#tableFourBody").append(nonedata);
        }
        $("#tableFourBody tr:even td").addClass("four4");
        $("#tableFourBody tr:odd td").addClass("four5");
        //请假记勤数据
        $(".table-five tbody").empty();
        var markNum2 = 0;
        for (var i = 0; i < lastDay.getDate(); i++) {
            var thisDay = new Date(yearData, monthData - 1, i + 1);
            var month = thisDay.getMonth() + 1;
            var thisDayStr = thisDay.getFullYear() + (month < 10 ? '0' : '') + month + (i < 10 ? '0' : '') + (i + 1);
            var absenceData = data.reqabsence[thisDayStr];
            if (absenceData != undefined) {
                markNum2 += 1;
                var start = absenceData.dateStart.replace(/-/g, "/") + " " + absenceData.timeStart;
                var end = absenceData.dateEnd.replace(/-/g, "/") + " " + absenceData.timeEnd;
                var absenceBody = "<tr><td>" +
                    absenceData.reason + "</td><td>" +
                    absenceData.dateStart + "</td><td>" +
                    absenceData.timeStart + "</td><td>" +
                    absenceData.dateEnd + "</td><td>" +
                    absenceData.timeEnd + "</td><td>" +
                    diffDay(start, end).toFixed(1) + "0" + "</td></tr>";
                $("#tableFiveBody").append(absenceBody);
            }
        }
        if (markNum2 == 0) {
            var nonedata = "<tr><td colspan='6'>没有满足条件的记录</td></tr>";
            $("#tableFiveBody").append(nonedata);
        }
        $("#tableFiveBody tr:even td").addClass("four4");
        $("#tableFiveBody tr:odd td").addClass("four5");

        //加班信息数据
        var markNum3 = 0;
        $(".table-six tbody").empty();
        for (var i = 0; i < lastDay.getDate(); i++) {
            var thisDay = new Date(yearData, monthData - 1, i + 1);
            var month = thisDay.getMonth() + 1;
            var thisDayStr = thisDay.getFullYear() + "-" + (month < 10 ? '0' : '') + month + "-" + (i < 9 ? '0' : '') + (i + 1);
            var thisDayOverTime = thisDayStr.replace(/-/g, "");
            var startTime = data[keyData][i][1];
            var endTime = (data[keyData][i][3] == undefined ? data[keyData][i][2] : data[keyData][i][3]);
            var timeRequest = data.overtime[thisDayOverTime];
            if ((thisDay.getDay() != 0) & (thisDay.getDay() != 6)) {
                if (endTime > "18:00") {
                    if (timeRequest != undefined) {
                        markNum3 += 1;
                        var requestTime = diffTime(timeRequest[0], timeRequest[1]).toFixed(1) + "0";
                        var reaTime = diffTime("18:00:00", roundTime(endTime)).toFixed(1) + "0";
                        var overTimeData = "<tr><td>" +
                            thisDayStr + "</td><td>" +
                            timeRequest[0] + "</td><td>" +
                            timeRequest[1] + "</td><td>" +
                            "17:30:00" + "</td><td>" +
                            endTime + ":00" + "</td><td>" +
                            requestTime + "</td><td class='delayed'>" +
                            (requestTime > reaTime ? reaTime : requestTime) + "</td><td>" +
                            "1.5" + "</td></tr>";
                        $("#tableSixBody").append(overTimeData);
                    }
                }
            } else {
                if (timeRequest != undefined) {
                    markNum3 += 1;
                    var requestTime = (diffTime(timeRequest[0], timeRequest[1]) - 1).toFixed(1) + "0";
                    var reaTime = 0;
                    var temp1 = (startTime > timeRequest[0] ? startTime : timeRequest[0]);
                    var temp2 = (endTime < timeRequest[1] ? endTime : timeRequest[1]);
                    temp2 = roundTime(temp2);
                    if (temp1 <= "12:00") {
                        reaTime = (diffTime(temp1, temp2) - 1).toFixed(1) + "0";
                    } else if (temp1 > "13:00") {
                        reaTime = diffTime(temp1, temp2).toFixed(1) + "0";
                    } else {
                        reaTime = diffTime("13:00", temp2).toFixed(1) + "0";
                    }
                    var overTimeData = "<tr><td>" +
                        thisDayStr + "</td><td>" +
                        timeRequest[0] + "</td><td>" +
                        timeRequest[1] + "</td><td>" +
                        startTime + ":00" + "</td><td>" +
                        endTime + ":00" + "</td><td>" +
                        requestTime + "</td><td class='dayoff'>" +
                        reaTime + "</td><td>" +
                        "2" + "</td></tr>";
                    $("#tableSixBody").append(overTimeData);
                }
            }
        }
        if (markNum3 == 0) {
            var nonedata = "<tr><td colspan='8'>没有满足条件的记录</td></tr>";
            $("#tableSixBody").append(nonedata);
        }
        $("#tableSixBody tr:even td").addClass("four4");
        $("#tableSixBody tr:odd td").addClass("four5");
        //加班时间核算数据
        $(".table-seven").empty();
        var delayedTime = 0,
            dayoffTime = 0,
            businessTime = 0,
            holidayTime = 0;
        for (var i = 0; i < $(".delayed").length; i++) {
            var delayedTime = delayedTime + Number($(".delayed").eq(i).text());
        }
        for (var i = 0; i < $(".dayoff").length; i++) {
            var dayoffTime = dayoffTime + Number($(".dayoff").eq(i).text());
        }
        var allTime = delayedTime * 1.5 + dayoffTime * 2 + businessTime * 1.5 + holidayTime * 3;
        var allData = "<tr><td>" +
            "延时加班" + delayedTime.toFixed(1) +
            "小时,假日加班" + dayoffTime.toFixed(1) +
            "小时,出差加班" + businessTime.toFixed(1) +
            "小时,节日加班" + holidayTime.toFixed(1) +
            "小时,折算后合计" + allTime.toFixed(1) + "小时" +
            "</td></tr>";
        $(".table-seven").append(allData);
    });
}

//创建人员基本信息表格函数
function userInfoTable() {
    $(".info-head").after("<h2>人员基本信息</h2><table class='table-one'></table>");
    var html = "";
    for (i = 0; i < 12; i++) {
        if (i % 2 == 0) {
            html += "<td class='one1'></td>";
        } else {
            html += "<td class='one2'></td>";
        }
        if (((i + 1) % 6) == 0) {
            html += "</tr><tr>";
        }
    }
    $(".table-one").append("<tr>" + html + "</tr>");
}

//创建考勤信息区域函数
function attendTable() {
    var tableTwoThree = "<table class='table-two'><tr>" +
        "<td id='month3'></td>" +
        "<td id='month2'></td>" +
        "<td id='month1'></td>" +
        "</tr></table>" +
        "<h2>考勤打卡信息</h2>" +
        "<table class='table-three' title='考勤打卡信息'></table>";
    $(".table-one").after(tableTwoThree);
    var tHead = "<thead><tr>" +
        "<th class='three1' id='titleYearMonth' colspan='7'></th>" +
        "</tr><tr>" +
        "<th class='three2'>星期日</th>" +
        "<th class='three2'>星期一</th>" +
        "<th class='three2'>星期二</th>" +
        "<th class='three2'>星期三</th>" +
        "<th class='three2'>星期四</th>" +
        "<th class='three2'>星期五</th>" +
        "<th class='three2'>星期六</th>" +
        "</tr></thead>";
    var tBody = "";
    for (var i = 0; i < 6; i++) {
        tBody += "<tr>" +
            "<td class='three3'></td>" +
            "<td class='three3'></td>" +
            "<td class='three3'></td>" +
            "<td class='three3'></td>" +
            "<td class='three3'></td>" +
            "<td class='three3'></td>" +
            "<td class='three3'></td>" +
            "</tr>" +
            "<tr>" +
            "<td class='three4'></td>" +
            "<td class='three4'></td>" +
            "<td class='three4'></td>" +
            "<td class='three4'></td>" +
            "<td class='three4'></td>" +
            "<td class='three4'></td>" +
            "<td class='three4'></td>" +
            "</tr>";
    }
    tBody = "<tbody>" + tBody + "</tbody>";
    $(".table-three").append(tHead + tBody);
}
//创建迟到早退表格函数
function absenteeism() {
    var tableFour = "<h2>迟 到 早 退</h2><table class='table-four'><thead id='tableFourHead'><tr>" +
        "<th class='four1'>日期</th>" +
        "<th class='four1'>迟到</th>" +
        "<th class='four1'>早退</th>" +
        "<th class='four2'>缺勤(小时)</th>" +
        "<th class='four2'>请假信息</th>" +
        "</tr></thead>" +
        "<tbody id='tableFourBody'></tbody></table>";
    $(".table-three").after(tableFour);
}
//创建请假记勤表格函数
function requestAbsence() {
    var tableFive = "<h2>请 假 记 勤</h2><table class='table-five'><thead id='tableFiveHead'><tr>" +
        "<th class='five1'>假别</th>" +
        "<th class='five2'>开始日期</th>" +
        "<th class='five2'>开始时间</th>" +
        "<th class='five2'>结束日期</th>" +
        "<th class='five2'>结束时间</th>" +
        "<th class='five2'>请假时间(小时)</th>" +
        "</tr></thead>" +
        "<tbody id='tableFiveBody'></tbody></table>";
    $(".table-four").after(tableFive);
}
//创建加班信息表格函数
function overTime() {
    var tableSix = "<h2>加 班 信 息</h2><table class='table-six'><thead id='tableSixHead'><tr>" +
        "<th class='six1'>日期</th>" +
        "<th class='six2'>申报开始时间</th>" +
        "<th class='six2'>申报结束时间</th>" +
        "<th class='six2'>开始实际刷卡</th>" +
        "<th class='six2'>结束实际刷卡</th>" +
        "<th class='six2'>申报加班小时</th>" +
        "<th class='six2'>实际加班小时</th>" +
        "<th class='six2'>折算系数</th>" +
        "</tr></thead>" +
        "<tbody id='tableSixBody'></tbody></table>";
    $(".table-five").after(tableSix);
}
//创建加班时间核算表格函数
function overTimeCount() {
    var tableSeven = "<table class='table-seven'></table>";
    $(".table-six").after(tableSeven);
}
//加班时间差计算函数
function diffTime(timeStart, timeEnd) {
    var time1 = new Date("2017/01/01 " + timeStart); //开始时间  
    var time2 = new Date("2017/01/01 " + timeEnd); //结束时间  
    var time3 = time2.getTime() - time1.getTime(); //时间差的毫秒数  
    var diffHour = Number((time3 / (3600 * 1000)).toFixed(3));
    return diffHour;
}
//加班打卡3分钟计算函数
function roundTime(Time) {
    Time = String(Time);
    var now = "";
    // alert(Time.slice(-1));
    if (Time.slice(-1) < 7) {
        now = new Date("2017/01/01 " + Time);
        now.setMinutes(now.getMinutes() - Time.slice(-1));
    } else {
        now = new Date("2017/01/01 " + Time);
        now.setMinutes(now.getMinutes() + 10 - Time.slice(-1));
    }
    now = String(now).slice(16, 21);
    return now;
}
//请假时间差计算函数
function diffDay(x, y) {
    var date1 = new Date(x); //开始时间
    var date2 = new Date(y); //结束时间
    var hours = 0,
        timeLast = 0,
        timeFirst = 0;
    //请假的最后一天
    if (y.slice(11) > "17:30") {
        timeLast = 8;
    } else if ((y.slice(11) < "17:30") & (y.slice(11) > "13:00")) {
        timeLast = (date2.getTime() - (new Date((y.slice(0, 11) + "08:30"))).getTime()) / (3600 * 1000) - 1;
    } else if ((y.slice(11) <= "13:00") & (y.slice(11) > "12:00")) {
        timeLast = 3.5;
    } else if ((y.slice(11) <= "12:00") & (y.slice(11) > "08:30")) {
        timeLast = (date2.getTime() - (new Date(y.slice(0, 11) + "08:30")).getTime()) / (3600 * 1000);
    } else {
        timeLast = 0;
    }
    //请假的第一天
    if (x.slice(11) >= "17:30") {
        timeFirst = 0;
    } else if ((x.slice(11) < "17:30") & (x.slice(11) > "13:00")) {
        timeFirst = ((new Date(x.slice(0, 11) + "17:30")).getTime() - date1.getTime()) / (3600 * 1000);
    } else if ((x.slice(11) <= "13:00") & (x.slice(11) > "12:00")) {
        timeFirst = 4.5;
    } else if ((x.slice(11) <= "12:00") & (x.slice(11) > "08:30")) {
        timeFirst = ((new Date(x.slice(0, 11) + "17:30")).getTime() - date1.getTime()) / (3600 * 1000) - 1;
    } else {
        timeFirst = 8;
    }

    if (x.slice(0, 11) != y.slice(0, 11)) {
        //有问题,周末时间没有减去,节假日没减去

        var timeMiddle = (((new Date(y.slice(0, 11) + "00:00")).getTime() - (new Date(x.slice(0, 11) + "00:00")).getTime()) / (24 * 3600 * 1000) - 1) * 8;
        hours = Number((timeMiddle + timeLast + timeFirst).toFixed(3));
    } else {
        hours = Number((timeLast + timeFirst).toFixed(3));
    }
    return hours;
}
