package com.tawasal.util.selector.factory

import com.tawasal.util.selector.ComposeSelector
import com.tawasal.util.selector.Selector
import com.tawasal.util.selector.SelectorArg

object SelectorFactory {
    fun textSelector(text: String,
                     contains: Boolean=false,
                     caseSensitive: Boolean=false,
                     normalizeSpace: Boolean=true) = Selector()
        .paramAnd(SelectorArg.textParam(text, contains, caseSensitive, normalizeSpace))

    fun cdescSelector(id: String,
                      contains: Boolean=false,
                      caseSensitive: Boolean=true,
                      normalizeSpace: Boolean=false) = Selector()
        .paramAnd(SelectorArg("@content-desc", id, contains, normalizeSpace, caseSensitive))

    fun idSelector(id: String,
                   contains: Boolean=true,
                   caseSensitive: Boolean=true,
                   normalizeSpace: Boolean=false) = Selector()
        .paramAnd(SelectorArg("@resource-id", id, contains, normalizeSpace, caseSensitive))

    fun tagSelector(tag: String) = Selector(tag = tag)

    fun xpathSelector(xpath: String) = Selector(xpath = xpath)

    fun compose(vararg selectors: Selector?) = ComposeSelector().addAll(selectors.toList())
}
