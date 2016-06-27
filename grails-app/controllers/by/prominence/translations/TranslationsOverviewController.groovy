package by.prominence.translations

class TranslationsOverviewController {

    def bundleService

    def index() {

        render view: 'index', model: [bundles: bundleService.getBundles()]
    }

    def show() {

        if (!params.bundleName) {
            redirect action: 'index'
            return
        }

        Bundle bundle = bundleService.findBundleByName(params.bundleName as String)

        if (!bundle) {
            render view: 'index', model: [message: g.message(code: 'plugin.translations.error.bundleNotFound', args: [params.bundleName as String])]
            return
        }

        render view: 'show', model: [bundle: bundle]
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

    }

}
