document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    init_table();

});

function init_table() {
    var stub = [{'name': '操作系统', 'time': '2019-03-03', 'limit': 230, 'current': 100}]
    var s1 = ' <tbody>\n' +
        '            <tr>\n' +
        '                <td><input class="uk-checkbox" type="checkbox"></td>\n' +
        '                <td>';
    var s2 = '</td>\n' +
        '                <td class="uk-text-truncate">';
        // '                    <a class="uk-link-reset" href=""></a>' +
        // '';
    var s3 = '</td>\n' +
        '                <td class="uk-text-truncate">';
    var s4 = '</td>\n' +
        '                <td class="uk-text-truncate">';
    var s5 = '            </td>\n</tr>\n' +
        '\n' +
        '            </tbody>';

    for (var i = 0; i < stub.length; i++) {
        var s = s1 + stub[i]['time'] + s2 + stub[i]['name'] + s3 + stub[i]['limit'] + s4 + stub[i]['current'] + s5;
        $('#course_table').append(s);

    }

}