package com.tawasal.util.selector

import com.tawasal.util.constants.Timeouts
import com.tawasal.util.driver.DriverManager
import com.tawasal.util.dsl.step
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.testng.Assert.fail
import java.time.Duration
import com.tawasal.util.driver.AppiumSystem

operator fun Selector.invoke() = toWebElem()!!

fun Selector.toWebElem(): WebElement? {
    try {
        return DriverManager.driver.findElement(By.xpath(this.toXpath()))
    } catch (e: Exception) {
    }

    return null
}

fun Selector.click() {
    step("Click at $name") {
        val elem = toWebElem()
        if(elem == null) {
            fail("Element with xpath: '${this.toXpath()} can't be found'")
        } else {
            elem.click()
        }
    }
}

fun Selector.input(value: String, closeKeyboard: Boolean = true) {
    step("Input '$value' to $name") {
        this().click()

        this().clear()
        this().sendKeys(value)

      /*  if(this().text != value) {
            this().sendKeys(value)

            if(this().text != value) {
                fail("Невозможно ввести текст в $name")
            }
        }*/

        if(closeKeyboard) {
            AppiumSystem.hideKeyboard()
        }

        Selector.prevInputMap[name] = value
    }
}

fun Selector.waitForVisible(timeout: Duration = Timeouts.default): Boolean {
    DriverManager.setTimeout(timeout.toMillis())
    val res = toWebElem() != null
    DriverManager.setDefaultTimeout()
    return res
}

val Selector.prevInput: String
    get() = Selector.prevInputMap[name] ?: ""

val Selector.text: String
    get() = this()!!.text

val Selector.isVisible: Boolean
    get() {
        DriverManager.setTimeout(100)
        val res = toWebElem() != null
        DriverManager.setDefaultTimeout()
        return res
    }

val Selector.isHidden: Boolean
    get() = !isVisible
