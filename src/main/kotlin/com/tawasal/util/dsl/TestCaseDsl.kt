package com.tawasal.util.dsl

import com.tawasal.util.driver.AppiumSystem
import io.qameta.allure.Allure
import io.qameta.allure.Step
import io.qameta.allure.model.Status
import org.testng.Assert
import org.testng.Assert.assertTrue
import org.testng.Assert.fail
import org.testng.TestNG

fun<T> step(msg: String, lambda: ()->T): T {
    return Allure.step(msg, Allure.ThrowableRunnable {
        val res = lambda()
        AppiumSystem.takeScreenshot()
        res
    })
}

@Step
fun checkThat(msg: String, lambda: ()->Boolean)  {
    println(msg)
    assertTrue(lambda())
}

fun fail(msg: String) {
    Allure.step(msg, Status.FAILED)
    AppiumSystem.takeScreenshot()
    Assert.fail(msg)
}
