document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    listTopic();
    $("#create").unbind('click').click(function (event) {
        saveTopic();
    });

});

const lft_bracket = '<li class="tp0">';
const rht_bracket = '</li>';


function listTopic() {

    $.ajax({
        type: "GET",
        url: "/bbs/ls",
        data: {'courseName': localStorage.courseName},
        dataType: "json",
        success: function (data) {
            if (data.success) {
                var stub = data.data;
                for (var i = 0; i < stub.length; i++) {
                    var s = lft_bracket + stub[i]['title'] + rht_bracket;
                    $("#topicList").append(s);
                }
                    $('.tp0').click(function() {
                        localStorage.setItem('topic', $(this).text());
                        window.location.href = '/topic.html';
                    });
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}

function saveTopic() {
    $.ajax({
        type: "POST",
        url: "/bbs/topic",
        data: {
                'courseName': localStorage.courseName,
                'userName': localStorage.username,
                'topicName': $('#topic').val(),
        },
        dataType: "json",
        success: function (data) {
            if (data.success) {
                notifySuccess('成功发起话题');
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}

function enterTopic() {


}



