<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>bundles</title>
</head>

<body>
    <g:if test="${bundles}">
        <g:each in="${bundles}" var="bundle">
            ${bundle.name} <br />
            <g:each in="${bundle.propertiesList}" var="properties">
                -> ${properties.name} <br />
                ${properties.text.eachLine { line ->
                    print('----------> ' + line + '<br/>')
                }}
            </g:each>
        </g:each>
    </g:if>
    <g:else>
        There is no bundles!
    </g:else>
</body>
</html>