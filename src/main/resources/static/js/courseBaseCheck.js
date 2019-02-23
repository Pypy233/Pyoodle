document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    init_table();

    $("#btnSubmit").click(function (event) {
        join_course();

    });


});

function join_course() {
    var chk = $("input[name = 'box']");
    var chosenK = []
    for (k in chk) {
        if (chk[k].checked) {
            chosenK.push(chk[k].value);
            alert(chosenK);
        }
    }
}

function init_table() {
    var stub = [{'name': '操作系统', 'teacher': 'whr'}, {'name': '数据结构', 'time': '2019-03-03', 'limit': 230, 'current': 100, 'teacher': 'fx'}]
    var s1 = ' <tbody>\n' +
        '            <tr>\n' +
        '                <td>';

    var s2 = '</td>\n' +
        '                <td>';
    var s3 = '</td>\n' +
        '                <td class="uk-text-truncate">';
    // '                    <a class="uk-link-reset" href=""></a>' +
    // '';
    var s4 = '</td>\n' +
        '                <td class="uk-text-truncate">';
    var s5 = '</td>\n' +
        '                <td class="uk-text-truncate">';
    var s6 = '            </td>\n</tr>\n' +
        '\n' +
        '            </tbody>';
    // for (var i = 0; i < stub.length; i++) {
    //     var s = s1 + stub[i]['name'] + s2 + s3 + stub[i]['time'] + s4 + stub[i]['name'] + s5 + stub[i]['t'] + s6 + stub[i]['limit'] + s5 + stub[i]['current'] + s7;
    //     $('#course_table').append(s);
    // }
    // $.ajax({
    //     type: "GET",
    //     url: "/course/listJoin",
    //     dataType: "json",
    //     data: {userName: localStorage.username},
    //
    //     success: function (data) {
    //         if (data.success) {
    //             var stub = data.data;
    //             for (var i = 0; i < stub.length; i++) {
    //                 var s = s1 + stub[i]['name'] + s2 + s3 + localDateForMat(stub[i]['time']) + s4 + stub[i]['name'] + s5 + stub[i]['teacher'] + s6 + stub[i]['limit'] + s5 + stub[i]['current'] + s7;
    //                 $('#course_table').append(s);
    //             }
    //         } else
    //             notifyWarning('连接问题请重试');
    //     },
    //     error: function () {
    //         alert("连接问题请重试");
    //     }
    // });



}