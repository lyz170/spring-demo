$(function () {
    // 给InputCheck Error包裹外部的CSS样式
    addStyleToInputCheckError();

    // 返回登录（SEC01SC01）页面
    $("#btn-to-sign-in").on("click", function () {
        window.location.href = contextPath + "/";
    });

    // 发送验证码事件
    $("#btn-sec-code").on("click", function () {
        var email = $("#email").val();
        // 判断email是否为空
        if (isEmpty(email)) {
            clearAllMessages();
            showSampleMessages1("email", "msg-email", emailNotNullMsg);
        }
        // 判断email是否匹配正则表达式
        else if (!validateEmail(email)) {
            clearAllMessages();
            showSampleMessages1("email", "msg-email", emailFormatMsg);
        }
        // 启动倒计时，发送Ajax请求
        else {
            timer(reSendMinutes * 60);
            sendSecCodeAjax(email);
        }
    });

    // 注册事件
    $("#btn-sign-up").on("click", function () {
        var password = $("#password").val();
        var passwordConfirm = $("#passwordConfirm").val();
        if (!isEmpty(password) && !isEmpty(passwordConfirm) && password != passwordConfirm) {
            clearAllMessages();
            showSampleMessages1("passwordConfirm", "msg-passwordConfirm", notsameMsg);
        } else {
            $("#SEC01SC02Form").submit();
        }
    });
});

// 发送验证码的ajax方法
function sendSecCodeAjax(email) {
    $.ajax(contextPath + "/sec01/sign_up?send_sec_code_ajax", {
        type: "GET",
        cache: false,
        data: {email: email},
        timeout: 5000,
        dataType: "json",
    }).done(function (json) {
        if (json.returnCode == "1") {
            showSampleMessages1("email", "msg-email", json.message);
        } else if (json.returnCode == "2") {
            // 重新启动倒计时
            timer(json.value);
            // 弹出框 TODO
        }
    }).fail(function (xhr) {
        if (400 <= xhr.status && xhr.status <= 499) {
            // TODO
        } else {
            // TODO
        }
    });
}

// 倒计时方法
function timer(param) {
    var sec = parseInt(param);
    window.setInterval(function () {
        if (sec == 0) {
            $("#btn-sec-code").removeProp("disabled").empty().append(getMsg);
        } else {
            $("#btn-sec-code").prop("disabled", true).empty().append(reGetMsg + "(" + sec + "s)");
            sec--;
        }
    }, 1000);
}