package com.example.tests.web.tests;

import com.example.tests.common.Config;
import com.example.tests.common.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseWebTest {

    protected WebDriver driver;
    protected String baseUrl;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        driver = WebDriverFactory.createWebDriver();
        baseUrl = Config.get("web.baseUrl", "https://books.toscrape.com/");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

