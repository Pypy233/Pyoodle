document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    init_table();

    $("#btnSubmit").click(function (event) {
        list_course_to_be_checked();
    });


});

function list_course_to_be_checked() {

    $.ajax({
        type: "POST",
        url: "/course/check",
        data: "courseMap=" + get_chosen_area(),
        dataType: "json",

        success: function (data) {
            if (data.success) {
                notifySuccess('成功审批开课信息');
            } else
                alert('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });

}



function init_table() {
    var s1 = ' <tbody>\n' +
        '            <tr>\n' +
        '                <td class="uk-text-truncate">';
    var s2 = '</td>\n' +
        '                <td class="uk-text-truncate">';
    var courseS0 = '</td>\n <td class="uk-text-truncate">';


    var s3 = '</td>\n' +
        '                <td class="uk-text-truncate"><label><input class="uk-radio" type="radio" name="' ;
    var s4 = '_1"> 通过</label>\n' +
        '                   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label><input class="uk-radio" type="radio" name="';
    var s5 = '_0"> 拒绝</label></td>\n' +
        '            </tr>\n' +
        '   ';


    $.ajax({
        type: "GET",
        url: "/course/listChecking",
        dataType: "json",

        success: function (data) {
            if (data.success) {
                var stub = data.data;
                for (var i = 0; i < stub.length; i++) {
                    var name = stub[i]['name'] + '_' + stub[i]['teacherName'] + '_' + stub[i]['time'];
                    var s = s1 + stub[i]['name'] + s2 + stub[i]['teacherName'] + courseS0 + stub[i]['time'] + s3 + name + s4 + name + s5;
                    $('#course_table').append(s);
                }
            } else
                alert('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });





}

function get_chosen_area () {
    var chosen_area = new Array();
    var radios = $('.uk-radio');
    for (radio in radios) {
        if (radios[radio].checked) {
            chosen_area.push(radios[radio]['name']);
        }
    }
    return chosen_area;

}