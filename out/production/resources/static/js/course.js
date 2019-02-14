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
        url: "/courseBase/show",
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

function saveCourse() {
    $.ajax({
        type: "POST",
        url: "/course/save",
        data: {
            username: localStorage.username,
            time: $('#datepicker').val(),
            classNum: parseInt($('#classbtn').val()),
            limit: parseInt($('#limit').val())
        },
        dataType: "json",
        success: function (data) {
            if (data.success) {
               notifySuccess('成功提交申请');
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}


function createCourse() {
    var cla = $('#classbtn').val();
    return cla;

}

function getDate() {
    var picker = new Pikaday({ field: document.getElementById('datepicker') });
    return picker.getDate();
    
}