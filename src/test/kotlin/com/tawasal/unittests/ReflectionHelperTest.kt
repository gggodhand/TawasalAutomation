package com.tawasal.unittests

import com.tawasal.pages.settings.ProfilePage
import org.testng.annotations.Test
import kotlin.test.assertEquals

class ReflectionHelperTest {
    @Test
    fun scanTest() {
        assertEquals("ProfilePage.aboutMe", ProfilePage.aboutMe.name)
    }
}
