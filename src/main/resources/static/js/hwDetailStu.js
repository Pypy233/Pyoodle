document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    listHw();
    getLink();
    $('#btnSubmit').unbind('click').click(function (event) {
        hw_submit();

    });
});


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
                // console.log(localStorage.courseName);
                // console.log(localStorage.hwName);
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
                console.log(data.data['studentNum']);
                localStorage.setItem('studentNum', data.data['studentNum']);
               return data.data['studentNumber'];
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
        url: "/user",
        dataType: "json",
        data: {
            userName: localStorage.username
        },
        success: function (data) {
            if (data.success) {
                //console.log(data.data['studentNum']);
                localStorage.setItem('studentNum', data.data['studentNum']);
                return data.data['studentNumber'];
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });



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

function getLink() {
    $.ajax({
        type: "GET",
        url: "/hw/download",
        dataType: "json",
        data: {
            courseName: localStorage.courseName,
            hwName: localStorage.hwName,
            userName: localStorage.username,
        },
        success: function (data) {
            var stub = data.data;
            const lft_bracket = '<li>';
            const rht_bracket = '</li>';

            const lft_a = '<a href="';
            const rht_a = ' " download="';
            const rht_a1 = '">';
            const rht_a2 ='</a>';
            var s = lft_bracket + lft_a + stub['path'] + rht_a +
                stub['name'] + rht_a1 + stub['name'] + rht_a2 + rht_bracket;
            $('#dw').append(s);
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}



function hw_submit() {

    var studentNum = '';

    $.ajax({
        type: "GET",
        url: "/user",
        dataType: "json",
        data: {
            userName: localStorage.username
        },
        success: function (data) {
            if (data.success) {
                console.log(data.data['studentNumber']);
                studentNum = data.data['studentNumber'];
                localStorage.setItem('studentNum', studentNum);
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });

    // Get form
    var form = $('#hwUploadForm')[0];

    var data = new FormData(form);

    data.append("courseName", localStorage.courseName);
    data.append("hwName", localStorage.hwName);
    data.append('studentNum', localStorage.studentNum);
    // console.log(localStorage.courseName);
    // console.log(localStorage.hwName);
    // console.log(localStorage.studentNum);

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

