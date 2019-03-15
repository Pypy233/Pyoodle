document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    listHw();


});

var stuentNum = '';

function f() {
    $.ajax({
        type: "GET",
        url: "/hw/name",
        dataType: "json",
        data: {
            courseName: localStorage.courseName,
            hwName: localStorage.hwName
        },
        success: function (data) {
            if (data.success) {
                var stub = data.data;
                console.log(localStorage.courseName);
                console.log(localStorage.hwName);
                console.log(data['name']);
                $('h3').html(stub['name']);
                $('#hwDdl').text(stub['ddl']);
                $('#requirements').text(stub['description']);
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}

function listHw() {

    $.ajax({
        type: "GET",
        url: "/hw/name",
        dataType: "json",
        data: {
            courseName: localStorage.courseName,
            hwName: localStorage.hwName
        },
        success: function (data) {
            if (data.success) {
                var stub = data.data;
                console.log(localStorage.courseName);
                console.log(localStorage.hwName);
                console.log(data['name']);
                    $('h3').html(stub['name']);
                    $('#hwDdl').text(stub['ddl']);
                    $('#requirements').text(stub['description']);
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}

function hw_submit() {

    // Get form
    var form = $('#hwUploadForm')[0];

    var data = new FormData(form);

    // data.append("CustomField", "This is some extra data, testing");
    // console.log(localStorage.courseName)
    data.append("courseName", localStorage.courseName);
    data.append("hwName", localStorage.hwName)
    data.append("studentNum", )
    // console.log(data['courseName']);

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/upload/hw",
        data: data,
        //http://api.jquery.com/jQuery.ajax/
        //http://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {

            $("#result").text(data);
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);
            notifySuccess('作业上传成功');

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);
            notifyDanger('作业上传失败');

        }
    });
}

