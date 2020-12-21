package com.tawasal.unittests

import com.tawasal.util.selector.factory.SelectorFactory.cdescSelector
import com.tawasal.util.selector.factory.SelectorFactory.tagSelector
import com.tawasal.util.selector.factory.SelectorFactory.textSelector
import org.testng.annotations.Test
import kotlin.test.assertEquals

class SelectorTests {

    @Test
    fun `check id selector`() {
        assertEquals("//*[@content-desc = 'some_id']",
            cdescSelector("some_id").toXpath())
    }

    @Test
    fun `check text selector`() {
        assertEquals("//*[translate(normalize-space(@text), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = translate('some_text', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')]",
            textSelector("some_text").toXpath())
    }

    @Test
    fun `check text selector with apostrophe`() {
        assertEquals("//*[translate(normalize-space(@text), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz') = translate(concat('let',\"'\",'s go'), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')]",
            textSelector("let's go").toXpath())
    }

    @Test
    fun `check tag selector`() {
        assertEquals("//div", tagSelector("div").toXpath())
    }

    @Test
    fun `check tag selector with position`() {
        assertEquals("//div[position() = '1']", tagSelector("div").pos("'1'").toXpath())
    }

    @Test
    fun `check tag selector with position = last`() {
        assertEquals("//div[position() = last()]", tagSelector("div").pos("last()").toXpath())
    }

    @Test
    fun `check tag selector with res position`() {
        assertEquals("(//div)[2]", tagSelector("div")[2].toXpath())
    }
}
