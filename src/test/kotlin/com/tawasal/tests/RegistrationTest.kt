package com.tawasal.tests

import com.tawasal.pages.MainPage
import com.tawasal.pages.settings.ProfilePage
import com.tawasal.pages.settings.SettingsPage
import com.tawasal.pages.startup.RegisterPhonePage
import com.tawasal.pages.startup.RegisterUserPage
import com.tawasal.pages.startup.WelcomePage
import com.tawasal.steps.App
import com.tawasal.util.UITest
import com.tawasal.util.dsl.step
import com.tawasal.util.selector.isHidden
import io.qameta.allure.Epic
import io.qameta.allure.Feature
import org.testng.Assert.assertEquals
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test


@Epic("End2End Tests")
@Feature("Регистрация")
class RegistrationTest: UITest() {

    @BeforeMethod
    fun logoutBeforeTest() {
        if(WelcomePage.btnLetsGo.isHidden) {
            step("Пользователь авторизован. Делаем выход") {
                App.logout()
            }
        }
    }

    @Test(description =
    "ДОПУСТИМ пользователь зарегестрировался введя имя и фамилию через проблем " +
    "ТОГДА на Странице Профиля имя и фамилия должны быть в соответствующих полях")
    fun `check registration with first and second name`() = test("Elton John")

    @Test(description =
    "ДОПУСТИМ пользователь зарегестрировался введя имя без пробела " +
    "ТОГДА на Странице Профиля имя и фамилия должны быть в соответствующих полях")
    fun `check registration with first name`() = test("MyUsername")

    private fun test(firstName: String) {
        step("Инициализация, доходим до страницы регистрации") {
            WelcomePage.skip()
            RegisterPhonePage.enterNewPhone()
        }

        step("Регистрируемся") {
            RegisterUserPage.register(firstName)
        }

        step("Открываем страницу с профилем") {
            MainPage.openSettings()
            SettingsPage.UserInfo.open()
        }

        step("Данные пользователя со страницы профиля должны соответствовать данным при регистрации") {
            val expected = RegisterUserPage.getUserModel()
            val actual = ProfilePage.getUserModel()

            assertEquals(expected.firstName, actual.firstName, "Имя должно соответствовать")
            assertEquals(expected.secondName, actual.secondName, "Фамилия должна соответствовать")
            assertEquals(expected.login, actual.login, "Логин должен соответствовать")
            assertEquals(expected.phone, actual.phone, "Телефон должен соответствовать")
        }
    }
}
