package com.example.tests.mobile.tests;

import com.example.tests.common.Config;
import com.example.tests.common.MobileDriverFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.util.Properties;

public abstract class BaseMobileTest {

    protected RemoteWebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws MalformedURLException {
        Properties properties = Config.asProperties();
        driver = MobileDriverFactory.createAndroidDriver(properties);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

