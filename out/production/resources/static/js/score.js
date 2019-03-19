document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    listCourse();
    $("#btnSubmit").unbind('click').click(function (event) {
       hw_submit();

    });


});

function listCourse() {


    $.ajax({
        type: "GET",
        url: "/course/score",
        dataType: "json",
        data: {
            userName: localStorage.username
        },
        success: function (data) {
            if (data.success) {
                var stub = data.data;
                for(var i = 0; i < stub.length; i++) {
                    var s = '<option value="' + stub[i]['name'] + '">' + stub[i]['name']  + '</option>';
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

function hw_submit() {

    // Get form
    var form = $('#hwUploadForm')[0];

    var data = new FormData(form);

    // data.append("CustomField", "This is some extra data, testing");
    // console.log(localStorage.courseName)
    data.append("courseName", localStorage.courseName);
    data.append("all", $('#permit').val());
    // console.log(data['courseName']);

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/upload/score",
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
            notifySuccess('成绩上传成功');
            localStorage.setItem('userName', localStorage.username);
            setTimeout( function(){
                window.location.href = 'score_detail.html';
            }, 5 * 1000 );//延迟5

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);
            notifyDanger('作业上传失败');

        }
    });
}