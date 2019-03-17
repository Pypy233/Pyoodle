document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    init_table();

    $("#btnSubmit").click(function (event) {
        join_course();

    });


});

function join_course() {
    var nameScoreList = new Array();
    nameScoreList.push(localStorage.courseName);
    var tb = $("#course_table");
    console.log('val: ' + tb.rows[0].cells[0]);
    for(var i = 0; i < tb.rows.length; i++) {
       // var tdArr = rows[i][0];
        var userName = tb.rows[i].cells[0];
        var score = tb.rows[i].cells[2];
        var userScore = userName + ' ' + score;
        console.log(userScore);
        nameScoreList.push(userScore);
    }

    $.ajax({
        type: "POST",
        url: "/score/save",
        data: 'nameScoreList' + nameScoreList,
        dataType: "json",

        success: function (data) {
            if (data.success) {
                notifySuccess('选课成功');
            } else
                alert('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}

function init_table() {
    var s1 = '' +
        '            <tr>\n' +
        '                <td>';

    var s3 = '</td>\n' +
        '                <td>';
    var s4 = '</td>\n' +
        '                <td> <input type="text" class="input-s input-w input-hs">';
    var s5 = '</td>\n' +
        '               </tr>';
    var s6 = '</td>\n';
        // '                <td class="uk-text-truncate">';
    var s7 = '            </td>\n</tr>\n' +
        '\n' +
        '            </tbody>';
    $.ajax({
        type: "GET",
        url: "/score/get",
        dataType: "json",
        data: {courseName: localStorage.courseName},

        success: function (data) {
            if (data.success) {
                var stub = data.data;
                for (var i = 0; i < stub.length; i++) {
                    var s = s1 + stub[i]['studentName'] + s3 + stub[i]['studentNum'] + s4 +
                        stub[i]['score'] + s5;
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