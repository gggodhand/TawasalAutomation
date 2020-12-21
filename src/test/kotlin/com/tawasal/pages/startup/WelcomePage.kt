package com.tawasal.pages.startup

import com.tawasal.pages.MainPage
import com.tawasal.util.blocks.Page
import com.tawasal.util.selector.ReflectionHelper
import com.tawasal.util.selector.Selector
import com.tawasal.util.selector.factory.SelectorFactory.textSelector
import com.tawasal.util.selector.click
import com.tawasal.util.selector.factory.SelectorFactory.compose

object WelcomePage: Page() {
    val lblTitle = textSelector("Tawasal SuperApp")
    val btnLetsGo = textSelector("ok, let's go")

    override val checkSelector: Selector?
        get() = compose(lblTitle, btnLetsGo)

    fun skip() = btnLetsGo.click()

    init {
        ReflectionHelper.scanObject(this)
    }
}
