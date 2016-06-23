package by.prominence.translations

class Bundle {

    String name
    List<File> propertiesList = new ArrayList<>()

    public void addPropertyFile(File file) {
        propertiesList.add(file)
    }
}
