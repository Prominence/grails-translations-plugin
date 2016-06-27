<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="plugin.translations.overview.show.page.title" args="${[bundle.name]}" /></title>
    <r:require modules="bootstrap"/>
</head>

<body>
    <h1><g:message code="plugin.translations.overview.show.page.title" args="${[bundle.name]}" /></h1>
    <br/>
    <table>
        <thead>
            <tr>
                <g:each in="${bundle.propertiesList}" var="propertyFile">
                    <th>${propertyFile.name}</th>
                </g:each>
            </tr>
        </thead>
        <tbody>
            <tr>
                <g:each in="${bundle.propertiesList}" var="propertyFile">
                    <td>${propertyFile.text}</td>
                </g:each>
            </tr>
            <tr>
                <g:each in="${bundle.propertiesList}" var="propertyFile">
                    <td><g:message code="plugin.translations.totalRecords" args="${propertyFile.readLines()?.size() ?: '0'}"/></td>
                </g:each>
            </tr>
        </tbody>
    </table>
</body>
</html>