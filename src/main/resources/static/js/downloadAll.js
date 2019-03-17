document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    listCourse();

    $("#btnSubmit").unbind('click').click(function (event) {
        downloadAll();

    });


});

function listCourse() {


    $.ajax({
        type: "GET",
        url: "/hw/ls",
        dataType: "json",
        data: {
            userName: localStorage.username
        },
        success: function (data) {
            if (data.success) {
                var stub = data.data;
                for(var i = 0; i < stub.length; i++) {
                    var s = '<option value="' + stub[i]['courseName'] + ':' + stub[i]['hwName'] + '">' + stub[i]['courseName'] +':' + stub[i]['hwName'] + '</option>';
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

function downloadAll() {
    var courseHwStr = $('#coursebtn').val();
    var courseName = courseHwStr.split(':')[0];
    var hwName = courseHwStr.split(':')[1];
    console.log(courseHwStr);
    console.log(courseName);
    console.log(hwName);
    var url = "files/" + courseName + '/' + hwName;
    console.log(url);
    location.href= url;
}