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
        <title><spring:message code="SEC01SC01.screen.title"/></title>
        <link href="${pageContext.request.contextPath}/resources/common/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/common/app/common.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/sec01/SEC01SC01.css" rel="stylesheet">
        <script src="${pageContext.request.contextPath}/resources/common/jquery/jquery-3.2.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/common/bootstrap/js/bootstrap.min.js"></script>
        <script>
            var contextPath = '${pageContext.request.contextPath}';
        </script>
        <script src="${pageContext.request.contextPath}/resources/sec01/SEC01SC01.js"></script>
    </head>
    <body>
        <c:set var="userIdMsg"><spring:message code="SEC01SC01.screen.userId"/></c:set>
        <c:set var="passwordMsg"><spring:message code="SEC01SC01.screen.password"/></c:set>
        <div class="container">
            <form:form class="form-signin" modelAttribute="SEC01Form"
                       action="${pageContext.request.contextPath}/login" method="post">
                <h3 class="form-signin-heading text-primary"><b><spring:message code="SEC01SC01.screen.head"/></b></h3>
                <tag:messages />
                <form:input type="text" path="accountName" class="form-control"
                            placeholder="${userIdMsg}" autofocus="autofocus" maxlength="32"/>
                <form:input type="password" path="password" class="form-control"
                            placeholder="${passwordMsg}" maxlength="20"/>
                <div class="checkbox">
                    <label class="sec-w80">
                        <input type="checkbox" value="remember-me"><spring:message code="SEC01SC01.screen.rememberMe"/>
                    </label>
                </div>
                <button type="submit" class="btn btn-lg btn-primary btn-block">
                    <spring:message code="SEC01SC01.screen.signIn"/>
                </button>
                <button id="btn-sign-up" type="button" class="btn btn-link">
                    <spring:message code="SEC01SC01.screen.signUp"/>
                </button>
                <spring:message code="SEC01SC01.screen.separator"/>
                <button id="btn-forget-password" type="button" class="btn btn-link">
                    <spring:message code="SEC01SC01.screen.forgetPassword"/>
                </button>
            </form:form>
        </div>
    </body>
</html>
