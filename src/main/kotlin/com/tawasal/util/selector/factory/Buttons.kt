package com.tawasal.util.selector.factory

import com.tawasal.util.selector.Selector

object Buttons {
    val yes: Selector
        get() = AndroidTags.button.text("yes")

    val no: Selector
        get() = AndroidTags.button.text("no")
}
