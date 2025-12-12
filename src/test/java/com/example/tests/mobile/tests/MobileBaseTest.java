package com.example.tests.mobile.tests;

import com.example.tests.common.drivers.AppiumDriverFactory;
import io.appium.java_client.AppiumDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class MobileBaseTest {

    protected AppiumDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        // TODO: Move Appium URL and capabilities to config if this base class is used
        String appiumUrl = "http://127.0.0.1:4723/";
        driver = AppiumDriverFactory.createAndroidDriver(
                appiumUrl,
                "Android",
                "Android Emulator",
                "UiAutomator2",
                "org.wikipedia",
                "org.wikipedia.main.MainActivity"
        );
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

