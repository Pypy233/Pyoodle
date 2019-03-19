document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    init_table();
});


function init_table() {
    var s1 = '<tbody>' +
        '            <tr>\n' +
        '                <td>';

    var s3 = '</td>\n' +
        '                <td>';
    var s4 = '</td>\n' +
        '                <td>';
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
        data: {
            userName: localStorage.username
        },

        success: function (data) {
            if (data.success) {
                var stub = data.data;
                for (var i = 0; i < stub.length; i++) {
                    // console.log(stub[i]['courseName']);
                    var courseName = stub[i]['courseName'].split(' ')[stub[i]['courseName'].split(' ').length - 1];
                    console.log(courseName);
                    var s = s1 + stub[i]['studentName'] + s3 + stub[i]['studentNum'] + s4 +
                        stub[i]['courseName'] + s3 + stub[i]['score'] + s5 + '</tbody>';
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