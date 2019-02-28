document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    init_table();

    $("#btnSubmit").click(function (event) {
        get_chosen_area();

    });


});

function list_course_to_be_checked() {
    $.ajax({
        type: "GET",
        url: "/courseBase/showCheck",
        dataType: "json",

        success: function (data) {
            if (data.success) {
                var stub = data.data;
                return stub;
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}



function init_table() {
    var stub = list_course_to_be_checked();
    console.log(stub[0]['name']);

    var s1 = ' <tbody>\n' +
        '            <tr>\n' +
        '                <td class="uk-text-truncate">';
    var s2 = '</td>\n' +
        '                <td class="uk-text-truncate">';
    var s3 = '</td>\n' +
        '                <td class="uk-text-truncate"><label><input class="uk-radio" type="radio" name="' ;
    var s4 = '_1"> 通过</label>\n' +
        '                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label><input class="uk-radio" type="radio" name="';
    var s5 = '_0"> 拒绝</label></td>\n' +
        '            </tr>\n' +
        '   ';
    for (var i = 0; i < stub.length; i++) {
        var s = s1 + stub[i]['name'] + s2 + stub[i]['teacher']['name'] + s3 + stub[i]['name'] + s4 + stub[i]['name'] + s5;
        $('#course_table').append(s);
    }




}

function get_chosen_area () {
    var radios = $('.uk-radio');
    for (radio in radios) {
        if (radios[radio].checked) {
            alert(radios[radio].name);
        }
    }

}