<div class="row">
    <div class="translations-main-block">
        <g:form action="saveFile" name="${language.languageTag}" class="form-horizontal">
            <h3>${language.languageTag.toUpperCase()}</h3>
            <g:textField name="lang" value="${language.languageTag}" hidden="true"/>
            <g:textField name="bundleName" value="${language.bundle.name}" hidden="true"/>
            <g:textField name="langFile" value="${language.languageFile.path}" hidden="true"/>
            <div class="form-group">
                <g:each in="${language.translations}" var="translation">
                    <g:set var="key" value="${ts.propertyKey(property: translation)}"/>
                    <div class="col-sm-4">
                        <label class="control-label">${key}</label>
                    </div>
                    <div class="col-sm-8">
                        <g:textField class="form-control" name="translations.${key}"
                                     value="${ts.propertyValue(property: translation)}" />
                    </div>
                    <br/>
                </g:each>
            </div>

            <div class="pull-right">
                <g:link class="btn btn-default" action="export" params="[bundleName: language.bundle.name,
                    langTag: language.languageTag]">
                    <g:message code="plugin.translations.action.export"/>
                </g:link>
                <g:submitButton name="save" class="btn btn-primary"
                                value="${g.message(code: 'plugin.translations.action.save')}"/>
            </div>
        </g:form>
    </div>
</div>