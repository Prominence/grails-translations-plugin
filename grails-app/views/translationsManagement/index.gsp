<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title><g:message code="plugin.translations.overview.index.page.title" /></title>
</head>

<body>
<div class="raw">
    <h2 align="center"><g:message code="plugin.translations.overview.index.page.title"/></h2>
    <g:if test="${message}">
        <div class="alert alert-danger">
            ${message}
        </div>
    </g:if>
    <g:if test="${bundles}">
        <table class="table table-hover">
            <thead>
                <tr>
                    <td>#</td>
                    <td width="95%" colspan="2">
                        <g:message code="plugin.translations.header.bundleName"/>
                    </td>
                </tr>
            </thead>
            <tbody>
                <g:each in="${bundles}" var="bundle" status="index">
                    <tr>
                        <td width="5%">
                            ${index + 1}
                        </td>
                        <td width="75%">
                            <b>${bundle.name}</b>
                        </td>
                        <td>
                            <g:link action="export" params="${[bundleName: bundle.name]}"
                                    class="btn btn-default">
                                <g:message code="plugin.translations.action.export"/>
                            </g:link>
                            <g:link action="edit" params="${[bundleName: bundle.name]}"
                                    class="btn btn-primary">
                                <g:message code="plugin.translations.action.edit"/>
                            </g:link>
                        </td>
                    </tr>
                </g:each>
            </tbody>
        </table>
        <div class="translations-total-label">
            <g:message code="plugin.translations.totalBundles" args="${[bundles.size()]}"/>
        </div>
    </g:if>
    <g:else>
        <div class="alert alert-warning">
            <g:message code="plugin.translations.message.noBundles"/>
        </div>
    </g:else>
</div>
</body>
</html>