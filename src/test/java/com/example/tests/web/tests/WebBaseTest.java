package com.example.tests.web.tests;

import com.example.tests.common.drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class WebBaseTest {

    protected WebDriver driver;
    protected String baseUrl = "https://books.toscrape.com/";

    @BeforeClass
    public void setUp() {
        driver = WebDriverFactory.createChromeDriver();
        driver.get(baseUrl);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
