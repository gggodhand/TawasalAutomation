package com.tawasal.util

import com.tawasal.util.driver.DriverManager
import com.tawasal.util.selector.ReflectionHelper
import org.openqa.selenium.WebDriver
import org.testng.SkipException
import org.testng.annotations.BeforeClass

open class UITest {
    var driver: WebDriver? = null

    @BeforeClass
    fun initDriver() {
        val driver = DriverManager.getDriver()

        if(driver != null) {
            this.driver = driver
        } else {
            SkipException("Can't initialize driver")
        }
    }
}
