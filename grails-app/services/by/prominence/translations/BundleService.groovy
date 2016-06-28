package by.prominence.translations

class BundleService {

    static transactional = false

    private HashSet<Bundle> cachedBundles

    private static final String DEFAULT_FOLDER = "i18n"
    private static final String DEFAULT_EXTENSION = '.properties'
    private static final String STANDARD_LANGUAGE_TAG = 'standard'

    HashSet<Bundle> getBundles(boolean noCache = false) {
        if (!cachedBundles || noCache) {
            cachedBundles = searchForBundles().values()
        }
        return cachedBundles
    }

    Bundle findBundleByName(String name) {

        getBundles().find { Bundle bundle ->
            bundle.name == name
        }
    }

    private Map<String, Bundle> searchForBundles() {
        Map<String, Bundle> result = new HashMap<>()

        new File('.').eachFileRecurse() { File file ->
            if(file.name.endsWith(DEFAULT_EXTENSION)) {
                if (isTranslationProperty(file)) {

                    String bundleName = getBundleName(file.name)
                    if (!result.get(bundleName)) {
                        Bundle bundle = new Bundle(name: bundleName)
                        result.put(bundleName, bundle)
                    }
                    Language lang = createLanguage(file)
                    lang.bundle = result.get(bundleName)
                    result.get(bundleName).addLanguage(lang)
                }
            }
        }

        return result
    }

    private boolean isTranslationProperty(File file) {
        return file.path.contains(DEFAULT_FOLDER)
    }

    private String getFileNameWithoutExtension(String filename) {
        return filename.substring(0, filename.lastIndexOf('.'))
    }

    private String getBundleName(String filename) {
        return filename.indexOf('_') < 0 ?
                getFileNameWithoutExtension(filename) :
                filename.substring(0, filename.indexOf('_'))
    }
    private Language createLanguage(File file) {

        Language lang = new Language()
        lang.languageFile = file

        String filename = getFileNameWithoutExtension(file.name)

        // setting language tag
        if (filename.contains('_')) {
            String tag = filename.substring(filename.indexOf('_') + 1)
            lang.languageTag = tag
        } else {
            lang.languageTag = STANDARD_LANGUAGE_TAG
        }

        file.text.readLines().each { String line ->
            if (!line.equals('')) {
                lang.translations.put(getKey(line), getValue(line))
            }
        }

        return lang
    }

    private String getKey(String line) {
        return line.substring(0, line.indexOf('=')).trim()
    }

    private String getValue(String line) {
        return line.substring(line.indexOf('=') + 1).trim()
    }
}
