<g:form action="saveFile" name="${language.languageTag}">
    <h3>${language.languageTag.toUpperCase()}</h3>
    <g:each in="${language.translations}" var="translation">
        <g:set var="key" value="${ts.propertyKey(property: translation)}"/>
        <label>${key}</label> <g:textField name="${key}" value="${ts.propertyValue(property: translation)}" />
        <br/>
    </g:each>
    <g:submitButton name="save" class="btn btn-primary" value="${g.message(code: 'plugin.translations.action.save')}"/>
</g:form>