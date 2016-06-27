<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="plugin.translations.overview.index.page.title" /></title>
    <r:require modules="bootstrap"/>
</head>

<body>
    <g:if test="${message}">
        <div class="alert alert-danger">
            ${message}
        </div>
    </g:if>
    <g:if test="${bundles}">
        <ol>
            <g:each in="${bundles}" var="bundle">
                <li><g:link action="show" params="${[bundleName: bundle.name]}" >${bundle.name}</g:link></li>
            </g:each>
        </ol>
        <g:message code="plugin.translations.totalBundles" args="${[bundles.size()]}"/>
    </g:if>
    <g:else>
        There is no bundles!
    </g:else>
</body>
</html>