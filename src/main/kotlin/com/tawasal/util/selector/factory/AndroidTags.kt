package com.tawasal.util.selector.factory

import com.tawasal.util.selector.Selector
import com.tawasal.util.selector.factory.SelectorFactory.tagSelector

object AndroidTags {
    val editText: Selector
        get() = tagSelector("android.widget.EditText")

    val textView: Selector
        get() = tagSelector("android.widget.TextView")

    val viewGroup: Selector
        get() = tagSelector("android.view.ViewGroup")

    val button: Selector
        get() = tagSelector("android.widget.Button")
}
