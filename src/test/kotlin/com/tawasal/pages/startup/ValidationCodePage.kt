package com.tawasal.pages.startup

import com.tawasal.pages.MainPage
import com.tawasal.util.blocks.Page
import com.tawasal.util.selector.ReflectionHelper
import com.tawasal.util.selector.Selector
import com.tawasal.util.selector.factory.AndroidTags
import com.tawasal.util.selector.factory.SelectorFactory.compose
import com.tawasal.util.selector.factory.SelectorFactory.textSelector
import com.tawasal.util.selector.input
import com.tawasal.util.selector.waitForVisible

object ValidationCodePage: Page() {
    val lblTitle = textSelector("Enter verification code")

    val inputCode1 = AndroidTags.editText[1]
    val inputCode2 = AndroidTags.editText[2]
    val inputCode3 = AndroidTags.editText[3]
    val inputCode4 = AndroidTags.editText[4]
    val inputCode5 = AndroidTags.editText[5]

    val btnResendSms = textSelector("Resend SMS")

    override val checkSelector: Selector?
        get() = compose(lblTitle, btnResendSms)

    fun enterCode(code: String = "11111") {
        inputCode1.input(code)
        compose(MainPage.checkSelector, RegisterUserPage.checkSelector).waitForVisible()
    }

    init {
        ReflectionHelper.scanObject(this)
    }
}
