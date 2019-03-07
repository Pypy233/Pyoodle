document.write("<script language=javascript src='/js/notification.js'></script>");
$(document).ready(function () {
    listCourse();

});

function listCourse() {
    var s1 = '<div class="nature-card">\n' +
        '\t\t\t\t<div class="uk-card uk-card-small uk-card-default">\n' +
        '\t\t\t\t\t<div class="uk-card-header">\n' +
        '\t\t\t\t\t\t<div class="uk-grid uk-grid-small uk-text-small" data-uk-grid>\n' +
        '\t\t\t\t\t\t\t<div class="uk-width-expand">\n' +
        '\t\t\t\t\t\t\t\t<span class="cat-txt">Nature / Outdoor</span>\n' +
        '\t\t\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t\t\t<div class="uk-width-auto uk-text-right uk-text-muted">\n' +
        '\t\t\t\t\t\t\t\t<span data-uk-icon="icon:clock; ratio: 0.8"></span> 3 min.\n' +
        '\t\t\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t<div class="uk-card-media">\n' +
        '\t\t\t\t\t\t<div class="uk-inline-clip uk-transition-toggle" tabindex="0">\n' +
        '\t\t\t\t\t\t\t<img class="lazy" data-src=""'; // 路径

    var s3 = 'data-width="400" data-height="300" data-uk-img alt="" onclick = "click_on_course_img(this)" src="';
    var s5 = '" value="'; // courseName
    var s4 =    '">\n' +
        '\t\t\t\t\t\t\t<div class="uk-transition-slide-bottom uk-position-bottom uk-overlay uk-overlay-primary">\n' +
        '\t\t\t\t\t\t\t\t<span data-uk-icon="icon:heart; ratio: 0.8"></span> 12.345 <span data-uk-icon="icon:comment; ratio: 0.8"></span> 12.345\n' +
        '\t\t\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t\t</div>\n' +
        '\n' +
        '\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t<div class="uk-card-body">\n' +
        '\t\t\t\t\t\t<h6 class="uk-margin-small-bottom uk-margin-remove-adjacent uk-text-bold">';
    var s2 = '</h6>\n' +
        '\t\t\t\t\t\t<p class="uk-text-small uk-text-muted">Duis aute irure dolor in reprehenderit in voluptate velit</p>\n' +
        '\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t<div class="uk-card-footer">\n' +
        '\t\t\t\t\t\t<div class="uk-grid uk-grid-small uk-grid-divider uk-flex uk-flex-middle" data-uk-grid>\n' +
        '\t\t\t\t\t\t\t<div class="uk-width-expand uk-text-small">\n' +
        '\t\t\t\t\t\t\t\tJohn Doe\n' +
        '\t\t\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t\t\t<div class="uk-width-auto uk-text-right">\n' +
        '\t\t\t\t\t\t\t\t<a href="#" data-uk-tooltip="title: Twitter" class="uk-icon-link" data-uk-icon="icon:twitter; ratio: 0.8"></a>\n' +
        '\t\t\t\t\t\t\t\t<a href="#" data-uk-tooltip="title: Instagram" class="uk-icon-link" data-uk-icon="icon:instagram; ratio: 0.8"></a>\n' +
        '\t\t\t\t\t\t\t\t<a href="#" data-uk-tooltip="title: Behance" class="uk-icon-link" data-uk-icon="icon:behance; ratio: 0.8"></a>\n' +
        '\t\t\t\t\t\t\t\t<a href="#" data-uk-tooltip="title: Pinterest" class="uk-icon-link" data-uk-icon="icon:pinterest; ratio: 0.8"></a>\n' +
        '\t\t\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t\t\t<div class="uk-width-auto uk-text-right">\n' +
        '\t\t\t\t\t\t\t\t<a data-uk-tooltip="title: Drag this card" href="#" class="uk-icon-link drag-icon" data-uk-icon="icon:move; ratio: 1"></a>\n' +
        '\t\t\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t\t</div>\n' +
        '\t\t\t\t\t</div>\n' +
        '\t\t\t\t</div>\n' +
        '\t\t\t</div>';

    $.ajax({
        type: "GET",
        url: "/course/listAll",
        dataType: "json",
        success: function (data) {
            if (data.success) {
                var stub = data.data;
                for(var i = 0; i < stub.length; i++) {
                    s = s1 + stub[i]['picPath'] + s3 + stub[i]['picPath'] + s5 + stub[i]['name'] + s4 + stub[i]['name'] + s2;
                    $("#course_cards").append(s);
                }
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试" );
        }
    });
}

function click_on_course_img(el) {
    localStorage.setItem('courseName', el.getAttribute('value'));
    window.location.href = '/reUpload.html';
    notifySuccess('欢迎进入' + el.getAttribute('value'));
}