document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    listCourse();
    $('#btnSubmit').unbind('click').click(function (event) {
        mailAll();

    });

});

function mailAll() {
    var title = $('#title').val();
    var text = $('#text').val();
    var cla = $('#classbtn').val();

    $.ajax({
        type: "POST",
        url: "/mail/all",
        dataType: "json",
        data: {
            courseName: cla,
            title: title,
            content: text
        },

        success: function (data) {
            if (data.success) {
               notifySuccess('群发邮件成功');
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}

function listCourse() {
    $.ajax({
        type: "GET",
        url: "/course/passed",
        dataType: "json",
        success: function (data) {
            if (data.success) {
                var stub = data.data;
                for(var i = 0; i < stub.length; i++) {
                    s = '<option value="1">' + stub[i]['name'] + '</option>';
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