document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    listHw();
    listPPT();
});

const lft_bracket = '<li>';
const rht_bracket = '</li>';

const lft_a = '<a href="';
const rht_a = ' " download="';
const rht_a1 = '">';
const rht_a2 ='</a>';


function listPPT() {
    $.ajax({
        type: "GET",
        url: "/course/listPPT",
        data: {'courseName': localStorage.courseName},
        dataType: "json",
        success: function (data) {
            if (data.success) {
                var stub = data.data;
                localStorage.setItem('pptList', stub);
                for (var i = 0; i < stub.length; i++) {
                    var s = lft_bracket + lft_a + stub[i]['path'] + rht_a +
                        stub[i]['name'] + rht_a1 + stub[i]['name'] + rht_a2 + rht_bracket;
                    $("#pptList").append(s);
                }
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
        url: "/course/listHw",
        data: {'courseName': localStorage.courseName},
        dataType: "json",
        success: function (data) {
            if (data.success) {
                var stub = data.data;
                localStorage.setItem('hwList', stub);
                for(var i = 0; i < stub.length; i++) {
                    var s = lft_bracket + lft_a + stub[i]['path'] + rht_a +
                        stub[i]['name'] + rht_a1 + stub[i]['name']+ rht_a2 + rht_bracket;
                    $("#hwList").append(s);
                }
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试" );
        }
    });
}

function listHw() {

    $.ajax({
        type: "GET",
        url: "/course/listHw",
        data: {'courseName': localStorage.courseName},
        dataType: "json",
        success: function (data) {
            if (data.success) {
                var stub = data.data;
                localStorage.setItem('hwList', stub);
                var lft_list = '<li class="hw_list">';
                var rht_list = '</li>';
                var space = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                var btn_check = '<button class="uk-button uk-button-primary uk-dropdown-right-center uk-align-right">查看详情</button>';
                // // var btn_upload = '<button class="uk-button uk-button-green uk-dropdown-right-center uk-align-right">上传作业</button>';
                // var btn_upload = '<input type="file" name="files" class="uk-input uk-form-contrast" multiple/>';
                for (var i = 0; i < stub.length; i++) {
                    var s = lft_list + stub[i]['name'] +":" + btn_check + rht_list;
                    $('#hwList').append(s);
                }
                $('.hw_list').click(function () {
                    var str = $(this).text();
                    var hwName = str.split(":")[0];
                    localStorage.setItem('hwName', hwName);
                    console.log(localStorage.teacher)
                    if ("1" === localStorage.teacher) {
                        window.location.href = "/hw_detail_tea.html";
                    } else {
                        window.location.href = "/hw_detail_stu.html";
                    }
                });
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试" );
        }
    });


}
