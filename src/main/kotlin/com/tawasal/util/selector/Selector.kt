package com.tawasal.util.selector

open class Selector(
    private val axe: String = "//",
    private var tag: String = "*",
    private var xpath: String = ""
) {
    var name: String = ""
    var base: Selector? = null

    protected val args = SelectorArgChain()
    protected var resPos = ""

    fun paramAnd(arg: SelectorArg): Selector {
        args.and(arg)
        return this
    }

    fun paramOr(arg: SelectorArg): Selector {
        args.or(arg)
        return this
    }

    fun tag(value: String): Selector {
        this.tag = tag
        return this
    }

    open fun toXpath(): String {
        if(xpath.isNotEmpty()) {
            return xpath
        }

        val res = axe + tag + args.toXpath()
        return if(resPos.isNotEmpty()) "($res)[$resPos]" else res
    }

    operator fun get(index: Int): Selector {
        resPos = index.toString()
        return this
    }

    operator fun get(value: String): Selector {
        resPos = value
        return this
    }

    fun pos(index: Int): Selector {
        args.and(SelectorArg("position()", index.toString()))
        return this
    }

    fun pos(value: String): Selector {
        args.and(SelectorArg("position()", value, wrapValue = false))
        return this
    }

    fun text(text: String,
                     contains: Boolean=false,
                     caseSensitive: Boolean=false,
                     normalizeSpace: Boolean=true) = this
        .paramAnd(SelectorArg.textParam(text, contains, caseSensitive, normalizeSpace))

    companion object {
        val prevInputMap = HashMap<String, String>()
    }
}
