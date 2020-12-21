package com.tawasal.pages.settings

import com.tawasal.util.blocks.Page
import com.tawasal.util.selector.ReflectionHelper
import com.tawasal.util.selector.Selector
import com.tawasal.util.selector.factory.SelectorFactory.xpathSelector
import com.tawasal.util.selector.click
import com.tawasal.util.selector.factory.SelectorFactory.textSelector

object SettingsPage: Page() {
    val lblTitle = textSelector("Settings")
    val XPATH = "//android.view.ViewGroup[android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@text='Saved Messages']]"

    object UserInfo {
        val XPATH = SettingsPage.XPATH + "/android.view.ViewGroup"
        val base = xpathSelector(XPATH)

        fun open() {
            base.click()
            ProfilePage.waitForLoadComplete()
        }
    }

    init {
        ReflectionHelper.scanObject(this)
        ReflectionHelper.scanObject(UserInfo)
    }

    override val checkSelector: Selector?
        get() = lblTitle
}
