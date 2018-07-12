<!DOCTYPE html>
    <head>
        <meta charset="utf-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <c:set var="titleKey"><tiles:insertAttribute name="title" ignore="true"/></c:set>
        <title><spring:message code="${titleKey}"/></title>
        <script type="text/javascript"></script>
    </head>
    <body>
        <div>
            <tiles:insertAttribute name="header"/>
            <tiles:insertAttribute name="body"/>
            <tiles:insertAttribute name="footer"/>
        </div>
    </body>
</html>