package com.tawasal.util.selector

class ComposeSelector: Selector() {
    private val selectors = ArrayList<Selector>()

    fun addAll(selectors: Collection<Selector?>): ComposeSelector {
        selectors.forEach {
            if(it != null) {
                this.selectors.add(it)
            }
        }
        return this
    }

    override fun toXpath(): String {
        var res = ""

        selectors.forEach {
            res += "${it.toXpath()} | "
        }
        res = res.removeSuffix(" | ")

        if(resPos.isNotEmpty()) {
            res = "($res)[position() = '$resPos']"
        }
        return res
    }
}
