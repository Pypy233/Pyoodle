document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    console.log('-----' + localStorage.courseName);
    listHw();
    listPPT();
    localStorage.setItem('studentNum', getUser());
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
                var lft_list = '<li class="hw_list">';
                var rht_list = '</li>';
                var space = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
                var btn_check = '<button class="uk-button uk-button-primary uk-dropdown-right-center uk-align-right">查看详情</button>';

                for (var i = 0; i < stub.length; i++) {
                    var s = lft_list + stub[i]['name'] +":" + btn_check + rht_list;
                    $('#hwList').append(s);
                }
                $('.hw_list').click(function () {
                    var str = $(this).text();
                    var hwName = str.split(":")[0];
                    localStorage.setItem('hwName', hwName);
                    console.log(localStorage.teacher)
                    if (localStorage.username ==="py") {
                        window.location.href = "/hw_detail_stu.html";
                    } else {
                        window.location.href = "/hw_detail_tea.html";
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
