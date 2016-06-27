package by.prominence.translations

class PropertyTagLib {

    static namespace = "ts"

    def propertyKey = { attrs, body ->

        assert attrs.property

        String property = attrs.property as String

        out << property.substring(0, property.indexOf('=')).trim()
    }

    def propertyValue = {attrs, body ->

        assert attrs.property

        String property = attrs.property as String

        out << property.substring(property.indexOf('=') + 1).trim()
    }
}
