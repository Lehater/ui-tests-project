package com.example.tests.mobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WikipediaHomePage extends BaseMobilePage {

    private final By searchContainer = By.id("org.wikipedia:id/search_container");

    public WikipediaHomePage(RemoteWebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchContainer));
    }

    public WikipediaSearchPage tapOnSearch() {
        driver.findElement(searchContainer).click();
        return new WikipediaSearchPage(driver);
    }
}

