package com.tawasal.pages

import com.tawasal.pages.settings.SettingsPage
import com.tawasal.util.blocks.Block
import com.tawasal.util.blocks.Page
import com.tawasal.util.selector.ReflectionHelper
import com.tawasal.util.selector.Selector
import com.tawasal.util.selector.click
import com.tawasal.util.selector.factory.AndroidTags
import com.tawasal.util.selector.factory.SelectorFactory.idSelector

object MainPage: Page() {
    val inputSearch = idSelector("search_src_text")

    override val checkSelector: Selector?
        get() = inputSearch

    object Footer: Block() {
        val btnChat = AndroidTags.textView["last()-3"]
        val btnCall = AndroidTags.textView["last()-2"]
        val btnDiscover = AndroidTags.textView["last()-1"]
        val btnSettings = AndroidTags.textView["last()"]
    }

    init {
        ReflectionHelper.scanObject(this)
        ReflectionHelper.scanObject(Footer)
    }

    fun openSettings() {
        Footer.btnSettings.click()
        SettingsPage.waitForLoadComplete()
    }
}
