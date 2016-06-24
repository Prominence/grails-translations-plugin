package by.prominence.translations

class BundleService {

    private HashSet<Bundle> cachedBundles

    private static final String DEFAULT_FOLDER = "i18n"
    private static final String DEFAULT_EXTENSION = '.properties'

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
                    result.get(bundleName).addPropertyFile(file)
                }
            }
        }

        return result
    }

    private boolean isTranslationProperty(File file) {
        return file.parent.contains(DEFAULT_FOLDER)
    }

    private String getFileNameWithoutExtension(String filename) {
        return filename.substring(0, filename.lastIndexOf('.'))
    }

    private String getBundleName(String filename) {
        return filename.indexOf('_') < 0 ?
                getFileNameWithoutExtension(filename) :
                filename.substring(0, filename.indexOf('_'))
    }
}
