package com.example.tests.mobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WikipediaArticlePage extends BaseMobilePage {

    private final By articleTitle = By.id("org.wikipedia:id/view_page_title_text");

    public WikipediaArticlePage(RemoteWebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(articleTitle));
    }

    public String getTitle() {
        return driver.findElement(articleTitle).getText();
    }
}

