package com.tawasal.pages.startup

import com.tawasal.models.UserModel
import com.tawasal.pages.MainPage
import com.tawasal.util.blocks.Page
import com.tawasal.util.selector.*
import com.tawasal.util.selector.factory.AndroidTags
import com.tawasal.util.selector.factory.SelectorFactory.textSelector

object RegisterUserPage: Page() {
    val lblTitle = textSelector("A little bit left :)")
    val inputName = AndroidTags.editText[1]
    val inputUsername = AndroidTags.editText[2]
    val btnDone = textSelector("Done")

    override val checkSelector: Selector?
        get() = lblTitle

    fun register(name: String="", userName: String="") {
        if(name.isNotEmpty()) {
            inputName.input(name)
        }

        if(userName.isNotEmpty()) {
            inputUsername.input(userName)
        }
        btnDone.click()
        MainPage.waitForLoadComplete()
    }

    fun getUserModel(): UserModel {
        return UserModel(
            inputName.prevInput, "",
            inputUsername.prevInput,
            "+999"+RegisterPhonePage.inputPhone.prevInput)
    }

    init {
        ReflectionHelper.scanObject(this)
    }
}
