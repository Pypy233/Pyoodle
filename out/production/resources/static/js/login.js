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
    alert(password)
    $.ajax({
        type: "GET",
        url: "/login",
        dataType: "json",
        data: {
            username: user,
            password: password
        },
       
        success: function (data) {
            alert(data.success)
            if (data.success) {
               window.location.href="/dashboard.html";
            } else
                alert("Error!");
        },
        error: function () {
            alert("Network warning");
        }
    });
}