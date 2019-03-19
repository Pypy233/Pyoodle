document.write("<script language=javascript src='notification.js'></script>");


$(document).ready(function () {
    $('h2').html(localStorage.courseName);
    $("#btnSubmit").click(function (event) {
        fire_ajax_submit();
        hw_submit();
    });
});


function hw_submit() {

    // Get form
    var form = $('#hwUploadForm')[0];

    var data = new FormData(form);
    data.append("courseName", localStorage.courseName);
    // console.log(data['courseName']);

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/upload/multiHW",
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

function fire_ajax_submit() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);
    console.log(data.get('files'));

    data.append("courseName", localStorage.courseName);

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/upload/multiPPT",
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
            notifySuccess('课件上传成功');

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);
            notifyDanger('课件上传失败');

        }
    });

}