package com.tawasal.util.driver

import com.tawasal.util.constants.Timeouts
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities
import org.yaml.snakeyaml.Yaml
import java.io.FileInputStream
import java.net.URL
import java.time.Duration
import java.util.concurrent.TimeUnit

object DriverManager {
    private val DEFAULT_CONFIG = System.getProperty("config", "src/main/resources/config.yml")
    private val yaml = Yaml()
    const val HOST = "http://0.0.0.0:4723/wd/hub"

    lateinit var driver: WebDriver

    fun getDriver(configPath: String = DEFAULT_CONFIG): WebDriver? {
        val capsMap = loadFile(configPath)
        if(capsMap != null) {
            val caps = getCapabilities(capsMap)
            driver = AndroidDriver<WebElement>(URL(HOST), caps)
            setDefaultTimeout()
            return driver
        }
        return null
    }

    private fun getCapabilities(capsMap: Map<String, *>): DesiredCapabilities {
        val res = DesiredCapabilities.android()

        capsMap.forEach {
            res.setCapability(it.key, it.value)
        }

        return res
    }

    private fun loadFile(pathToFile: String) =
        FileInputStream(pathToFile).use { input ->
            yaml.load<Map<String, Any>>(input)
        }

    fun setDefaultTimeout() = setTimeout(Timeouts.default)
    fun setTimeout(duration: Duration) = setTimeout(duration.toMillis())
    fun setTimeout(ms: Long) {
        driver.manage().timeouts().implicitlyWait(ms, TimeUnit.MILLISECONDS);
    }
}
