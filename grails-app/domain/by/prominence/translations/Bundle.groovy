package by.prominence.translations

class Bundle {

    String name
    List<Language> languages = new ArrayList<>()

    public void addLanguage(Language lang) {
        languages.add(lang)
    }
}
