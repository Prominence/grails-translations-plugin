package by.prominence.translations

class TranslationsOverviewController {

    def bundleService

    def index() { }

    def showBundles() {

        render view: 'showBundles', model: [bundles: bundleService.getBundles()]
    }
}
