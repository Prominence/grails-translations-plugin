<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="plugin.translations.overview.page.title" /></title>
    <r:require modules="bootstrap"/>
</head>

<body>
    <g:if test="${bundles}">
        <ol>
            <g:each in="${bundles}" var="bundle">
                <li>${bundle.name}<li>
                <ul>
                    <g:each in="${bundle.propertiesList}" var="properties">
                        <li>${properties.name}</li>
                        ${properties.text.eachLine { line ->
                            print('----------> ' + line + '<br/>')
                        }}
                    </g:each>
                </ul>
            </g:each>
        </ol>
    </g:if>
    <g:else>
        There is no bundles!
    </g:else>
    <button class="btn btn-default" type="button">
        Test bootstrap button
    </button>
</body>
</html>