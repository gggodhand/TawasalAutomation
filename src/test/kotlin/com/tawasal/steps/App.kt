package com.tawasal.steps

import com.tawasal.pages.MainPage
import com.tawasal.pages.settings.ProfilePage
import com.tawasal.pages.settings.SettingsPage
import com.tawasal.util.selector.click
import com.tawasal.util.selector.factory.Buttons

object App {
    fun logout() {
        if(!ProfilePage.isOpen) {
            if(MainPage.isOpen) {
                MainPage.Footer.btnSettings.click()
                SettingsPage.UserInfo.open()
            }
        }

        ProfilePage.btnLogout.click()
        Buttons.yes.click()
    }
}
