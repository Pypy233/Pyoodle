document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    listCourse();

    $("#btnSubmit").unbind('click').click(function (event) {
        localStorage.setItem('courseName', $('#coursebtn').val());
        window.location.href = '/score_detail.html';

    });


});

function listCourse() {


    $.ajax({
        type: "GET",
        url: "/course/listByTeacher",
        dataType: "json",
        data: {
            userName: localStorage.username
        },
        success: function (data) {
            if (data.success) {
                var stub = data.data;
                for(var i = 0; i < stub.length; i++) {
                    var s = '<option value="' + stub[i]['courseName'] + '">' + stub[i]['courseName']  + '</option>';
                    $("#coursebtn").append(s);
                }
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}