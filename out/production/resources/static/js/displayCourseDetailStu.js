document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    listHw();

});

function listHw() {
    var stub = ['作业1', '作业2', '作业3', '作业4'];
    var lft_list = '<li class="hw_list">';
    var rht_list = '</li>';
    var space = '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;';
    var btn_check = '<button class="uk-button uk-button-primary uk-dropdown-right-center">查看详情</button>';
    var btn_upload = '<button class="uk-button uk-button-green uk-dropdown-right-center">上传作业</button>';
    for (var i = 0; i < stub.length; i++) {
        var s = lft_list + stub[i] +":" +  space + space + space + btn_check + btn_upload + rht_list;
        $('#hw_list').append(s);
    }
    $('.hw_list').click(function () {
        var str = $(this).text();
        var hwName = str.split(":")[0];
        localStorage.setItem('hwName', hwName);
    });
}
