<g:form action="saveFile" name="${language.languageTag}">
    <h3>${language.languageTag.toUpperCase()}</h3>
    <g:textField name="lang" value="${language.languageTag}" hidden="true"/>
    <g:textField name="bundleName" value="${language.bundle.name}" hidden="true"/>
    <g:textField name="langFile" value="${language.languageFile.path}" hidden="true"/>
    <g:each in="${language.translations}" var="translation">
        <g:set var="key" value="${ts.propertyKey(property: translation)}"/>
        <label>${key}</label> <g:textField name="translations.${key}" value="${ts.propertyValue(property: translation)}" />
        <br/>
    </g:each>
    <g:submitButton name="save" class="btn btn-primary" value="${g.message(code: 'plugin.translations.action.save')}"/>
</g:form>