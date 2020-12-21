package com.tawasal.unittests

import com.tawasal.util.selector.SelectorArg
import com.tawasal.util.selector.SelectorArgChain
import org.testng.annotations.Test
import kotlin.test.assertEquals

class SelectorArgChainTests {

    @Test
    fun `empty`() {
        assertEquals("", SelectorArgChain().toXpath())
    }

    @Test
    fun `default one param`() {
        assertEquals("[@text = 'some value']",
            SelectorArgChain().and(
                SelectorArg("@text", "some value")).toXpath())
    }

    @Test
    fun `default two param and`() {
        assertEquals("[@text = 'some value' AND @text = 'some value2']",
            SelectorArgChain()
                .and(SelectorArg("@text", "some value"))
                .and(SelectorArg("@text", "some value2"))
                .toXpath())
    }

    @Test
    fun `default two param or`() {
        assertEquals("[@text = 'some value' OR @text = 'some value2']",
            SelectorArgChain()
                .and(SelectorArg("@text", "some value"))
                .or(SelectorArg("@text", "some value2"))
                .toXpath())
    }
}
