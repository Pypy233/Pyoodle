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
            if (data.success) {
                if (data.data == 'STUDENT') {
                    window.location.href="dashboard_student.html";
                } else if (data.data == 'TEACHER') {
                    window.location.href = "dashboard_teacher.html";
                } else {
                    window.location.href = "dashboard_admin.html";
                }
            } else
                alert("Error!");
        },
        error: function () {
            alert("Network warning");
        }
    });
}