package com.tawasal.unittests

import com.tawasal.util.selector.SelectorArg
import org.testng.annotations.Test
import kotlin.test.assertEquals

class SelectorArgTests {

    @Test
    fun `Argument with all addons`() {
        assertEquals("contains(translate(normalize-space(@text), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), translate('Some Text', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'))",
            SelectorArg("@text", "Some Text",
                contains = true, normalizeSpace = true, caseSensitive = false).toXpath())
    }

    @Test
    fun `Argument with no addons`() {
        assertEquals("@text = 'Some Text'",
            SelectorArg("@text", "Some Text",
                contains = false, normalizeSpace = false, caseSensitive = true).toXpath())
    }

    @Test
    fun `Argument for position`() {
        assertEquals("position() = last()",
            SelectorArg("position()", "last()",
                contains = false, normalizeSpace = false, caseSensitive = true, wrapValue = false).toXpath())
    }

    @Test
    fun `Argument with apostrophe`() {
        assertEquals("@text = concat('let',\"'\",'s go')",
            SelectorArg("@text", "let's go").toXpath())
    }
}
