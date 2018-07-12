<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <!-- 默认的UTF-8编码格式-->
        <meta charset="utf-8">
        <!-- Bootstrap不支持IE古老的兼容模式。为了让IE浏览器运行最新的渲染模式下，建议将此<meta>标签加入到页面中 -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <!-- 对移动设备友好，可以适应在移动设备上显示 -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title><spring:message code="SEC01SC02.screen.title"/></title>
        <link href="${pageContext.request.contextPath}/resources/common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/common/app/common.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/sec01/SEC01SC02.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/resources/common/jquery/jquery-3.2.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/common/bootstrap/js/bootstrap.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/common/app/common.js"></script>
        <script>
            var contextPath = '${pageContext.request.contextPath}';
            var getMsg = '<spring:message code="SEC01SC02.screen.getSecurityCode"/>';
            var reGetMsg = '<spring:message code="SEC01SC02.screen.reGet"/>';
            var emailNotNullMsg = '<spring:message code="SEC01SC02.message.email.notNull"/>';
            var emailFormatMsg = '<spring:message code="SEC01SC02.message.email.format"/>';
            var reSendMinutes = '<spring:message code="seccode.verify.minutes"/>';
            var notsameMsg = '<spring:message code="SEC01SC02.message.password.notsame"/>';
        </script>
        <script src="${pageContext.request.contextPath}/resources/sec01/SEC01SC02.js"></script>
    </head>
    <body>
        <c:set var="emailMag"><spring:message code="SEC01SC02.screen.email"/></c:set>
        <c:set var="securityCodeMag"><spring:message code="SEC01SC02.screen.securityCode"/></c:set>
        <c:set var="passwordMag"><spring:message code="SEC01SC02.screen.password"/></c:set>
        <c:set var="passwordConfirmMag"><spring:message code="SEC01SC02.screen.passwordConfirm"/></c:set>
        <div class="container">
            <form:form id="SEC01SC02Form" class="form-signup" modelAttribute="SEC01Form"
                       action="${pageContext.request.contextPath}/sec01/sign_up" method="post">
                <h3 class="form-signup-heading text-primary"><b><spring:message code="SEC01SC02.screen.head"/></b></h3>
                <form:input type="text" path="email" class="form-control" placeholder="${emailMag}"
                            autofocus="autofocus" maxlength="32" cssErrorClass="form-control error-input" />
                <div id="msg-email" class="sec-h20"><form:errors path="email"/></div>
                <div class="row">
                    <div class="col-md-5">
                        <button id="btn-sec-code" type="button" class="btn btn-info form-control">
                            <spring:message code="SEC01SC02.screen.getSecurityCode"/>
                        </button>
                    </div>
                    <div class="col-md-7">
                        <form:input type="text" path="securityCode" class="form-control" placeholder="${securityCodeMag}"
                            maxlength="6" cssErrorClass="form-control error-input"/>
                    </div>
                </div>
                <div id="msg-securityCode" class="sec-h20"><form:errors path="securityCode"/></div>
                <form:input type="password" path="password" class="form-control"
                            placeholder="${passwordMag}" maxlength="20" cssErrorClass="form-control error-input"/>
                <div id="msg-password" class="sec-h20"><form:errors path="password"/></div>
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="${passwordConfirmMag}" maxlength="20" cssErrorClass="form-control error-input"/>
                <div id="msg-passwordConfirm" class="sec-h20"><form:errors path="passwordConfirm"/></div>
                <button id="btn-sign-up" type="button" class="btn btn-lg btn-primary btn-block">
                    <spring:message code="SEC01SC02.screen.signUp"/>
                </button>
                <button id="btn-to-sign-in" type="button" class="btn btn-link">
                    <spring:message code="SEC01SC02.screen.returnToSignIn"/>
                </button>
            </form:form>
        </div>
    </body>
</html>