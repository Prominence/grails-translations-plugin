package by.prominence.translations

import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

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

    def export() {

        if (params.bundleName) {

            Bundle bundle = bundleService.findBundleByName(params.bundleName)

            if (params.langTag) {
                File exportFile = bundle.languages.find { lang ->
                    lang.languageTag == params.langTag
                }?.languageFile

                if (exportFile && exportFile.exists()) {
                    response.setHeader "Content-disposition", "attachment; filename=${exportFile.name}"
                    response.contentType = 'text/plain'
                    response.contentLength = exportFile.size()
                    response.outputStream << exportFile.bytes

                }
            } else {
                response.contentType = 'application/octet-stream'
                response.setHeader('Content-disposition', "attachment; filename=\"${bundle.name}.zip\"")

                ZipOutputStream zipOutputStream = new ZipOutputStream(response.outputStream)
                bundle.languages.each { lang ->
                    ZipEntry language = new ZipEntry(lang.languageFile.name)
                    zipOutputStream.putNextEntry(language)
                    zipOutputStream.write(lang.languageFile.bytes)
                }
                zipOutputStream.close()
            }
        } else {
            redirect action: 'index'
        }
    }

}
