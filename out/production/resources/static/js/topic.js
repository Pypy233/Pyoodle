
document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    listTopic();
    $("#create").unbind('click').click(function (event) {
        saveTopic0();
    });

});

const lft_bracket = '<li class="tp0">';
const rht_bracket = '</li>';
var btn_check = '<button class="uk-button uk-button-primary uk-dropdown-right-center">评论回复</button>';


function listTopic() {

    $.ajax({
        type: "GET",
        url: "/bbs/detail",
        data: {'topicName': localStorage.topic},
        dataType: "json",
        success: function (data) {
            if (data.success) {
                var stub = data.data;
                for (var i = 0; i < stub.length; i++) {
                    var userName = stub[i]['user']['name'];
                    var s = lft_bracket + userName + stub[i]['explanation'] + stub[i]['content'] + btn_check + rht_bracket;
                    $("#topicList").append(s);
                }
                $('.tp0').click(function() {
                    var word = prompt("请输入回复评论","");
                    if (word) {
                        saveTopic1(word);
                        window.location.reload();
                    }
                });
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}

function saveTopic0() {
    $.ajax({
        type: "POST",
        url: "/bbs/comment",
        data: {
            'topicName': localStorage.topic,
            'userName': localStorage.username,
            'content': $('#topic').val(),
            'explanation': '评论: ',
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

function saveTopic1(word) {
    $.ajax({
        type: "POST",
        url: "/bbs/comment",
        data: {
            'topicName': localStorage.topic,
            'userName': localStorage.username,
            'content': word,
            'explanation': '回复: ',
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
    });}