$(document).ready(function () {

    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        fire_ajax_submit();

    });

    $("#create").click(function (event) {



    });

});

function fire_ajax_submit() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);

    data.append("CustomField", "This is some extra data, testing");

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/api/upload/multi",
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

        },
        error: function (e) {

            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}
//
// // <script>
// // $('#create').click(function () {
// //     var data = new FormData($('#pptUploader')[0]);//表单id
// //     $.ajax({
// //         url: "/courseBase/uploadPPT",
// //         type: "POST",
// //         data: {
// //             data: data
// //         },
// //         /**
// //          *必须false才会自动加上正确的Content-Type
// //          */
// //         contentType: false,
// //         /**
// //          * 必须false才会避开jQuery对 formdata 的默认处理
// //          * XMLHttpRequest会对 formdata 进行正确的处理
// //          */
// //         processData: false,
// //         success: function (data) {
// //             if (data.status == "true") {
// //                 alert("上传成功！");
// //             }
// //             if (data.status == "error") {
// //                 alert(file.msg);
// //             }
// //         },
// //         error: function () {
// //             alert("上传失败！");
// //         }
// //     });
// // });
// // </script>
//
// function savePPT() {
//
//     // Get form
//     var form = $('#pptUploader')[0];
//
//     var data = new FormData(form);
//
//     data.append("CustomField", "This is some extra data, testing");
//
//     console.log(data.values());
//
//     $.ajax({
//         type: "POST",
//         enctype: 'multipart/form-data',
//         url: "courseBase/uploadPPT",
//         data: data,
//         //http://api.jquery.com/jQuery.ajax/
//         //http://developer.mozilla.org/en-US/docs/Web/API/FormData/Using_FormData_Objects
//         processData: false, //prevent jQuery from automatically transforming the data into a query string
//         contentType: false,
//         cache: false,
//         timeout: 600000,
//         success: function (data) {
//
//           alert('Success')
//
//         },
//         error: function (e) {
//
//            alert(e.message)
//         }
//     });
// //
// }