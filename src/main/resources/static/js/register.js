$("#sendEmail").on('click', function () {
    sendEmail();
});


function sendEmail() {
    var user = $('#Username').val();
    var password0 = $('#Password').val();
    var password1 = $('#Password1').val();
    var email = $('#email').val();
    if (password0 != password1) {
       alert('密码不一致，请重新确认')

    }


    $.ajax({
        type: "POST",
        url: "/register/mail",
        dataType: "json",
        data: {
            username: user,
            password: password0,
            email: email
        },

        success: function (data) {
            alert(data.success)
            if (data.success) {
                alert('验证邮件已发送，请确认查收')
            } else
                alert("Error!");
        },
        error: function () {
            alert("Network warning");
        }
    });
}
