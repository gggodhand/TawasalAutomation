package com.tawasal.util.driver

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidKeyCode
import io.appium.java_client.android.nativekey.AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent
import io.qameta.allure.Attachment
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.remote.Response


object AppiumSystem {

    fun keyboardBack() {
        (DriverManager.driver as AndroidDriver<*>).pressKeyCode(AndroidKeyCode.BACK)
    }

    fun hideKeyboard() {
        val appiumDriver = DriverManager.driver as AppiumDriver<*>
        if(isKeyboardPresent) {
            appiumDriver.hideKeyboard()
        }
    }

    val isKeyboardPresent: Boolean
        get() {
            val appiumDriver = DriverManager.driver as AppiumDriver<*>
            val isKeyboardShown: Response = appiumDriver.execute("isKeyboardShown")
            val res = isKeyboardShown.value
            return res as Boolean
        }

    @Attachment(value = "Page screenshot", type = "image/png")
    fun takeScreenshot(): ByteArray? {
        return (DriverManager.driver as TakesScreenshot).getScreenshotAs<ByteArray>(OutputType.BYTES)
    }
}
