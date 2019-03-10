document.write("<script language=javascript src='notification.js'></script>");
$(document).ready(function () {
    $('h3').html(localStorage.courseName);
    $('#btnSubmit').unbind('click').click(function (event) {
        createHw();

    });

});

function createHw() {
    var title = $('#title').val();
    var size = $('#limit').val();
    var type = $('#typebtn').val();
    var courseName = localStorage.courseName;
    var ddl = $('#datepicker').val();
    var description = $('#requirements').val();

    $.ajax({
        type: "POST",
        url: "/hw/save",
        data: {
                name: title,
                courseName: courseName,
                size: size,
                type: type,
                ddl: ddl,
                description: description
        },

        success: function (data) {
            if (data.success) {
                notifySuccess('成功创建作业');
            } else
                alert('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });

}

function getDate() {
    var picker = new Pikaday({ field: document.getElementById('datepicker') });
    return picker.getDate();

}
