package com.tawasal.util.selector

open class SelectorArg(
    var key: String,
    var value: String,
    var contains: Boolean = false,
    var normalizeSpace: Boolean = false,
    var caseSensitive: Boolean = true,
    var wrapValue: Boolean = true) {

    fun toXpath(): String {
        var key = key
        var value = processValue(value)

        if(normalizeSpace) {
            key = "normalize-space($key)"
        }

        if(!caseSensitive) {
            key = translate(key)
            value = translate(value)
        }

        return if (contains) {
            "contains($key, $value)"
        } else {
            "$key = $value"
        }
    }

    private fun processValue(str: String): String {
        return if(wrapValue) {
            if(str.contains("'")) {
                var strings = str.split("'")
                "concat('${strings[0]}',\"'\",'${strings[1].removePrefix("'")}')"
            } else "'$str'"

        } else str
    }

    private fun translate(str: String) = "translate($str, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')"

    companion object {
        fun textParam(text: String,
                      contains: Boolean=false,
                      caseSensitive: Boolean=false,
                      normalizeSpace: Boolean=true)
            = SelectorArg("@text", text, contains, normalizeSpace, caseSensitive)
    }
}
