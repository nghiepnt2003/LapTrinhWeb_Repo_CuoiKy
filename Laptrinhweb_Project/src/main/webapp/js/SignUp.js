function sendOtp() {


    var nameValue = document.querySelector('input[name="name"]').value;
    var emailValue = document.querySelector('input[name="email"]').value;
    $.ajax({
        type: 'POST',
        url: 'otp',
        data: {
            action: 'register',
            name: nameValue,
            email: emailValue
        }

    });
}


function registerServlet() {
    var nameValue = document.querySelector('input[name="name"]').value;
    var phoneValue = document.querySelector('input[name="phone"]').value;
    var emailValue = document.querySelector('input[name="email"]').value;
    var addressValue = document.querySelector('input[name="address"]').value;
    var passwordValue = document.querySelector('input[name="password"]').value;
    var otpValue = document.querySelector('input[name="otp"]').value;

    $('#spinner').css('display', 'inline-block');
    $.ajax({
        type: 'GET',
        url: 'register',
        data: {
            name: nameValue,
            phone: phoneValue,
            email: emailValue,
            address: addressValue,
            password: passwordValue,
            otp: otpValue
        },
        success:
            function (response) {
                if (response === "login_customer.jsp") {
                    window.location.href = response;
                } else {
                    var messageElement = document.getElementById('message');
                    messageElement.innerHTML = response;
                }
                $('#spinner').css('display', 'none');
            }
    });

}