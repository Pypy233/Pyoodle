document.write("<script language=javascript src='notification.js'></script>");
$(document).ready(function () {
    $('h3').html(localStorage.courseName);
    $('#btnSubmit').unbind('click').click(function (event) {
        hw_submit();
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

function hw_submit() {

    // Get form
    var form = $('#hwUploadForm')[0];

    var data = new FormData(form);

    // data.append("CustomField", "This is some extra data, testing");
    // console.log(localStorage.courseName)
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

function getDate() {
    var picker = new Pikaday({ field: document.getElementById('datepicker') });
    return picker.getDate();

}
