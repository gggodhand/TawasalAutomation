package com.tawasal.util.selector

class SelectorArgElem(val selectorArg: SelectorArg, val oper: String)

class SelectorArgChain {
    private val args = ArrayList<SelectorArgElem>()

    fun and(arg: SelectorArg): SelectorArgChain {
        args.add(SelectorArgElem(arg, "AND"))
        return this
    }

    fun or(arg: SelectorArg): SelectorArgChain {
        args.add(SelectorArgElem(arg, "OR"))
        return this
    }

    fun toXpath(): String {
        return if(args.isEmpty()) {
            ""
        } else {
            var res = ""
            for(i in 0 until args.size-1) {
                res += args[i].selectorArg.toXpath() + " ${args[i+1].oper} "
            }
            res += args[args.size-1].selectorArg.toXpath()

            "[$res]"
        }
    }
}
