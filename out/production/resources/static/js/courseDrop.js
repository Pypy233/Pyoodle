document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    init_table();

    $("#btnSubmit").click(function (event) {
        drop_course();

    });


});

function drop_course() {
    var chk = $("input[name = 'box']");
    var chosenK = []
    chosenK.push(localStorage.username);
    for (k in chk) {
        if (chk[k].checked) {
            chosenK.push(chk[k].value);
            //  alert(chosenK);
        }
    }

    $.ajax({
        type: "POST",
        url: "/course/drop",
        data: 'courseNameList=' + chosenK,
        dataType: "json",

        success: function (data) {
            if (data.success) {
                notifySuccess('成功退课')
            } else
                alert('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}

function init_table() {
   // var stub = [{'name': '操作系统', 'time': '2019-03-03', 'limit': 230, 'current': 100, 't': 'whr'}, {'name': '数据结构', 'time': '2019-03-03', 'limit': 230, 'current': 100, 't': 'fx'}]
    var s1 = ' <tbody>\n' +
        '            <tr>\n' +
        '                <td><input class="uk-checkbox" type="checkbox" name="box", value="';
    var s2 = '">';

    var s3 = '</td>\n' +
        '                <td>';
    var s4 = '</td>\n' +
        '                <td class="uk-text-truncate">';
    // '                    <a class="uk-link-reset" href=""></a>' +
    // '';
    var s5 = '</td>\n' +
        '                <td class="uk-text-truncate">';
    var s6 = '</td>\n' +
        '                <td class="uk-text-truncate">';
    var s7 = '            </td>\n</tr>\n' +
        '\n' +
        '            </tbody>';
    $.ajax({
        type: "GET",
        url: "/course/listJoined",
        dataType: "json",
        data: {userName: localStorage.username},

        success: function (data) {
            if (data.success) {
                var stub = data.data;
                for (var i = 0; i < stub.length; i++) {
                    var s = s1 + stub[i]['name'] + s2 + s3 + localDateForMat(stub[i]['time']) + s4 + stub[i]['name'] + s5 + stub[i]['teacher'] + s6 + stub[i]['limit'] + s5 + stub[i]['current'] + s7;
                    $('#course_table').append(s);
                }
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });

    function localDateForMat(obj) {
        da = new Date(obj);
        var year = da.getFullYear();
        var month = da.getMonth() + 1;
        var date = da.getDate();
        //console.log([year, month, date].join(‘/‘));
        return [year, month, date].join('-');
    }



}