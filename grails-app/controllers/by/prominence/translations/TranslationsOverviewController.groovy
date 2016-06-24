package by.prominence.translations

class TranslationsOverviewController {

    def bundleService

    def index() {

        render view: 'index', model: [bundles: bundleService.getBundles()]
    }

    def show() {

        if (!params.bundleName) {
            // render error
        }

        Bundle bundle = bundleService.findBundleByName(params.bundleName as String)

        if (!bundle) {
            // render error
        }

        render view: 'show', model: [bundle: bundle]
    }

}
