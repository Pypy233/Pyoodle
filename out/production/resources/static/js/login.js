$(document).keydown(function (event) {
    if (event.keyCode == 13) {
        login();
    }
});

$("#login").on('click', function () {
    login();
});


function login() {
    var user = $('#Username').val();
    var password = $('#Password').val();

    $.ajax({
        type: "GET",
        url: "/login",
        dataType: "json",
        data: {
            username: user,
            password: password
        },
       
        success: function (data) {
            console.log(user)
            if (data.success) {
                localStorage.setItem('username', user);
                localStorage.username = user;
                if (user === 'admin') {
                    window.location.href="/dashboard_admin.html";
                } else if (data.data == 'TEACHER') {
                    window.location.href = "/dashboard_teacher.html";
                } else {
                    window.location.href = "/dashboard_student.html";
                }
            } else
                notifyWarning('连接问题请重试');
        },
        error: function () {
            alert("连接问题请重试");
        }
    });
}