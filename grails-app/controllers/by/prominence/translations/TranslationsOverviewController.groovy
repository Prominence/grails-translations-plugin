package by.prominence.translations

class TranslationsOverviewController {

    def bundleService

    def index() {

        render view: 'index', model: [bundles: bundleService.getBundles()]
    }

    def showBundles() {

        render view: 'showBundles', model: [bundles: bundleService.getBundles()]
    }
}
