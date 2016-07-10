package by.prominence.translations

class TranslationsManagementController {

    def bundleService

    def index() {

        render view: 'index', model: [bundles: bundleService.getBundles()]
    }

    def edit() {

        if (!params.bundleName) {
            redirect action: 'index'
            return
        }

        Bundle bundle = bundleService.findBundleByName(params.bundleName as String)

        if (!bundle) {
            render view: 'index', model: [message: g.message(code: 'plugin.translations.error.bundleNotFound', args: [params.bundleName as String])]
            return
        }

        render view: 'edit', model: [bundle: bundle]
    }

    def saveFile() {

        Bundle bundle = bundleService.findBundleByName(params.bundleName as String)
        Language language = bundle.languages.find { Language lang ->
            lang.languageTag == params.lang
        }

        params.each { String key, value ->
            if (key.startsWith('translations.')) {
                key = key.substring(key.indexOf('.') + 1)
                language.translations."${key}" = value
            }
        }

        language.languageFile.newWriter().withWriter { w ->
            language.translations.each { key, value ->
                w << ("${key} = ${value}\n")
            }
        }

        if (params.bundleName) {
            redirect action: 'edit', model: [bundleName: params.bundleName]
            return
        }

        redirect action: 'index'
    }

}
