package com.tawasal.pages.startup

import com.tawasal.pages.MainPage
import com.tawasal.steps.App
import com.tawasal.util.blocks.Page
import com.tawasal.util.dsl.fail
import com.tawasal.util.dsl.step
import com.tawasal.util.selector.*
import com.tawasal.util.selector.factory.AndroidTags
import com.tawasal.util.selector.factory.SelectorFactory.textSelector
import com.tawasal.util.selector.factory.SelectorFactory
import com.tawasal.util.selector.factory.SelectorFactory.compose
import kotlin.random.Random

object RegisterPhonePage: Page() {
    val lblTitle = textSelector("Enter your phone number")
    val inputCountryCode = AndroidTags.editText
    val inputPhone = AndroidTags.editText[2]
    val btnNext = textSelector("next")

    override val checkSelector: Selector?
        get() = lblTitle

    fun enterNewPhone() {
        for (i in 0..10) {
            inputRandomPhone()
            ValidationCodePage.enterCode()
            if(MainPage.isOpen) {
                step("Пользователь с таким телефон уже зарегестрирован." +
                    " Пробуем авторизоваться повторно под другим номером...") {
                    App.logout()
                    WelcomePage.skip()
                }
            } else {
                return
            }
        }
    }

    fun inputRandomPhone() = inputPhone("661${Random.nextInt(1111, 9999)}")

    fun inputPhone(phone: String) {
        inputPhone.input(phone)
        btnNext.click()

        ValidationCodePage.checkSelector?.waitForVisible()
        if(!ValidationCodePage.isOpen) {
            fail("Страница с подтверждением кода не была отображена")
        }
    }

    init {
        ReflectionHelper.scanObject(this)
    }
}
