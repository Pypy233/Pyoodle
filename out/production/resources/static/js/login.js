$("#btn").on('click', function () {
    login();
});

$(document).keydown(function (event) {
    if (event.keyCode == 13) {
        login();
    }
});

function login() {
    var user = $('#Username').val();
    var password = $('#Password').val();
    $.ajax({
        type: "GET",
        url: "/index.html",
        dataType: "json",
        data: {
            user: user,
            password: password
        },

        success: function (data) {
            if (data.success) {
                setCookie("username", user, 1, "/");
                setCookie("password", password, 1, "/");

                if (user == "ADMIN")
                    window.location.href = "dashboard.html";
                else {
                    $.ajax({
                        type: "GET",
                        url: "/getUserInfo",
                        dataType: "json",
                        data: {
                            userName: user
                        },
                        success: function (data) {

                            var userType = data.data.userType;

                            if (userType == "STUDENT")
                                window.location.href = "dashboard.html";
                            else if (userType == "TEACHER")
                                window.location.href = "dashboard.html";
                            else
                                alert("Login Error!")
                        }

                    })
                }
            } else
                alert("Error!");
        },
        error: function () {
            alert("Network warning");
        }
    });
}