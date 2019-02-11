$(function () {
    init();
});

function init() {
    var username = localStorage.username;
    console.log(username);
    $('h4').html(username);
}