<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="plugin.translations.overview.edit.page.title" args="${[bundle.name]}"/></title>
</head>

<body>
    <g:each in="${bundle.languages}" var="language">
        <g:render template="templates/language" model="${[language: language]}" />
    </g:each>
</body>
</html>