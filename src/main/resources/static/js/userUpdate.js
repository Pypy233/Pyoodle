document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    getUser();
    $('#btnSubmit').unbind('click').click(function (event) {
        updateUser();

    });
});


function getUser() {
    console.log(localStorage.username);
    $.ajax({
        type: "GET",
        url: "/user",
        dataType: "json",
        data: {
            userName: localStorage.username
        },
        success: function (data) {
            if (data.success) {
                var user = data.data;
               $('#userName').val(user['name']);
               $('#number').val(user['studentNumber']);
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}

function updateUser() {
    $.ajax({
        type: "POST",
        url: "/update",
        dataType: "json",
        data: {
            ordinaryName: localStorage.username,
            newUserName: $('#userName').val(),
            studentNumber: $('#number').val()
        },
        success: function (data) {
            if (data.success) {
                notifySuccess('更新信息成功');
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}

