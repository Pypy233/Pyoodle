document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    init_table();

    $("#btnSubmit").click(function (event) {
        join_course();

    });


});

function join_course() {

    $.ajax({
        type: "POST",
        url: "/course/join",
        data: 'courseNameList=' + chosenK,
        dataType: "json",

        success: function (data) {
            if (data.success) {
                notifySuccess('选课成功');
            } else
                alert('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}

function init_user() {

    // $.ajax({
    //     type: "GET",
    //     url: "/course/listJoin",
    //     dataType: "json",
    //     data: {userName: localStorage.username},
    //
    //     success: function (data) {
    //         if (data.success) {
    //             var stub = data.data;
    //             for (var i = 0; i < stub.length; i++) {
    //                 var s = s1 + stub[i]['name'] + s2 + s3 + localDateForMat(stub[i]['time']) + s4 + stub[i]['name'] + s5 + stub[i]['teacher'] + s6 + stub[i]['limit'] + s5 + stub[i]['current'] + s7;
    //                 $('#course_table').append(s);
    //             }
    //         } else
    //             notifyWarning('连接问题请重试');
    //     },
    //     error: function () {
    //         alert("连接问题请重试");
    //     }
    // });
}
    function localDateForMat(obj) {
        da = new Date(obj);
        var year = da.getFullYear();
        var month = da.getMonth() + 1;
        var date = da.getDate();
        //console.log([year, month, date].join(‘/‘));
        return [year, month, date].join('-');
    }



}