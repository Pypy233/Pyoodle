document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    init_table();


});


function init_table() {
    var stub = [{'name': '操作系统', 'time': '2019-03-03', 'limit': 230, 'current': 100, 't': 'whr'}, {'name': '数据结构', 'time': '2019-03-03', 'limit': 230, 'current': 100, 't': 'fx'}]
    var s1= '<td>';
    var s2 = '</td>';
    $.ajax({
        type: "GET",
        url: "/course/lsDrop",
        dataType: "json",
        data: {userName: localStorage.username},

        success: function (data) {
            if (data.success) {
                var stub = data.data;
                for (var i = 0; i < stub.length; i++) {
                    var s = s1 + stub[i]['courseName'] + s2;
                    $('#course_table').append(s);
                }
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });


}