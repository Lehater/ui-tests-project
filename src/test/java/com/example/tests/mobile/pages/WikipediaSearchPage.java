package com.example.tests.mobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class WikipediaSearchPage extends BaseMobilePage {

    private final By searchInput = By.id("org.wikipedia:id/search_src_text");
    private final By firstSearchResult = By.id("org.wikipedia:id/page_list_item_title");

    public WikipediaSearchPage(RemoteWebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
    }

    public void searchFor(String text) {
        driver.findElement(searchInput).sendKeys(text);
        driver.findElement(searchInput).sendKeys(Keys.ENTER);
    }

    public WikipediaArticlePage openFirstResult() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstSearchResult));
        driver.findElement(firstSearchResult).click();
        return new WikipediaArticlePage(driver);
    }
}

