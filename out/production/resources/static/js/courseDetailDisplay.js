document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    listPPT();
    listHw();

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
                for(var i = 0; i < stub.length; i++) {
                    var s = lft_bracket + lft_a + stub[i]['path'] + rht_a +
                        stub[i]['name'] + rht_a1 + stub[i]['name']+ rht_a2 + rht_bracket;
                    $("#pptList").append(s);
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