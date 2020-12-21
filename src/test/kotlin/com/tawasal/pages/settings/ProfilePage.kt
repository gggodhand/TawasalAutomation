package com.tawasal.pages.settings

import com.tawasal.models.UserModel
import com.tawasal.util.blocks.Page
import com.tawasal.util.selector.ReflectionHelper
import com.tawasal.util.selector.Selector
import com.tawasal.util.selector.factory.SelectorFactory.cdescSelector
import com.tawasal.util.selector.factory.SelectorFactory.textSelector
import com.tawasal.util.selector.factory.SelectorFactory.xpathSelector
import com.tawasal.util.selector.text

object ProfilePage: Page() {
    val lblTile = textSelector("Profile")

    val firstName = cdescSelector("profilepage_entry_firstname")
    val lastName = cdescSelector("profilepage_entry_lastname")
    val userName = cdescSelector("profilepage_entry_usernameset")
    val aboutMe = cdescSelector("profilepage_entry_bio")
    val phoneNumber = xpathSelector("//android.view.ViewGroup[8]/android.widget.TextView")

    val btnLogout = textSelector("logout")

    fun getUserModel(): UserModel {
        val model = UserModel(
            firstName.text,
            lastName.text,
            userName.text,
            phoneNumber.text
        )

        if(model.login == "@username") {
            model.login = ""
        }

        return model
    }

    override val checkSelector: Selector?
        get() = lblTile

    init {
        ReflectionHelper.scanObject(this)
    }
}
