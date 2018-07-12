/**
 * 验证Email<br>
 * 前台验证只是对规范用户输入的内容，并不能通过这种方式做必须的验证（需要持久化的数据要做必须的验证）。<br>
 *     @param email
 *     @return 验证成功或失败
 */
function validateEmail(email) {
    var pattern = /^(\w)+(\.\w+)*@(\w)+((\.\w+)+)$/;
    if (email.search(pattern) != -1) {
        return true;
    } else {
        return false;
    }
}

/**
 * 验证参数是否为空<br>
 *     @param param
 *     @return TRUE/FALSE
 */
function isEmpty(param) {
    return (email == null || email == "" || typeof(email) == 'undefined');
}

/**
 * 使用<P>元素显示用户输入验证的Message<br>
 *     @param id JQuery的ID筛选器
 *     @param clazz <p>元素的class内容
 *     @param icon icon的class内容
 *     @param text Message文本
 */
function showMessages(errId, clazz, icon, text) {
    var message = '<p class="' + clazz + '">' +
        '<span class="' + icon + '" aria-hidden="true"></span>' +
        '&nbsp;<span>' + text + '</span></p>';
    $("#" + errId).append(message);
}
/** [定制1]用户输入验证：居右、红色、有X图标<br> */
function showSampleMessages1(id, errId, text) {
    $("#" + id).addClass("error-input");
    showMessages(errId, "text-danger float-r", "glyphicon glyphicon-remove-circle sec-t2", text);
}

/**
 * 清空页面上所有message<br>
 */
function clearAllMessages() {
    $('input[type="text"],input[type="password"]').removeClass("error-input");
    $('[id^="msg-"]').empty();
}

/**
 * 给InputCheck Error包裹外部的CSS样式<br>
 */
function addStyleToInputCheckError() {
    $('span[id$="\\.errors"]').wrap("<p class=\"text-danger float-r\"><p>")
        .before("<span class=\"glyphicon glyphicon-remove-circle sec-t2\" aria-hidden=\"true\"></span>&nbsp;");
}