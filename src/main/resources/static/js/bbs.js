document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    listCourse();

    $("#btnSubmit").unbind('click').click(function (event) {
        saveCourse();

    });


});

function listCourse() {

    $.ajax({
        type: "GET",
        url: "/course/listJoined",
        data: {userName: localStorage.username},
        dataType: "json",
        success: function (data) {
            if (data.success) {
                var stub = data.data;
                for(var i = 0; i < stub.length; i++) {
                    var s = '<option value="' + stub[i]['name'] + '">' + stub[i]['name'] + '</option>';
                    $("#coursebtn").append(s);
                }
                notifySuccess('请选择课程');
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}

function saveCourse() {
   localStorage.setItem('courseName', $('#coursebtn').val());
   window.location.href = '/bbs_detail.html';
}

