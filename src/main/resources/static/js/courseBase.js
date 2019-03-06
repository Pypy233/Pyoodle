document.write("<script language=javascript src='notification.js'></script>");
function saveCourseBase() {
    $.ajax({
        type: "POST",
        url: "/courseBase/save",
        dataType: "json",
        data: {
            courseName:  localStorage.courseName,
            user: localStorage.username
        },

        success: function (data) {
            if (data.success) {
                localStorage.username = user;
                notifySuccess('创建课程申请提交成功');
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}