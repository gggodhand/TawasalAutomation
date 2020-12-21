package com.tawasal.util.blocks

import com.tawasal.util.driver.AppiumSystem
import com.tawasal.util.selector.Selector
import com.tawasal.util.selector.isHidden
import com.tawasal.util.selector.isVisible
import com.tawasal.util.selector.waitForVisible

open class Page(base: Selector? = null): Block(base) {
    var name: String=""

    open val checkSelector: Selector? = null
    open val checkNotSelector: Selector? = null

    val isOpen: Boolean
        get() = checkSelector?.isVisible == true && (checkNotSelector == null || checkNotSelector?.isHidden == true)

    open fun back() {
        AppiumSystem.keyboardBack()
    }

    open fun navigate() {}
    open fun waitForLoadComplete() {
        checkSelector?.waitForVisible()
    }
}
