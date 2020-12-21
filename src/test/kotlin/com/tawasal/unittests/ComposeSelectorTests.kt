package com.tawasal.unittests

import com.tawasal.util.selector.factory.SelectorFactory.compose
import com.tawasal.util.selector.factory.SelectorFactory.tagSelector
import org.testng.annotations.Test
import kotlin.test.assertEquals

class ComposeSelectorTests {

    @Test
    fun `empty selectors should not return any xpath`() {
        assertEquals("", compose().toXpath())
    }

    @Test
    fun `compose selector with one arg should be treated as normal selector`() {
        assertEquals("//div", compose(tagSelector("div")).toXpath())
    }

    @Test
    fun `compose selector with two arg should build xpath with compose operator`() {
        assertEquals("//a | //b",
            compose(tagSelector("a"), tagSelector("b")).toXpath())
    }

    @Test
    fun `compose selector with two arg and position should build xpath with compose operator`() {
        assertEquals("(//a | //b)[position() = '2']",
            compose(tagSelector("a"), tagSelector("b"))[2].toXpath())
    }
}
