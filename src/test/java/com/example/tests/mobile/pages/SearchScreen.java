package com.example.tests.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Экран поиска Wikipedia.
 */
public class SearchScreen {

    private final AppiumDriver<?> driver;
    private final WebDriverWait wait;

    private final By searchInput = AppiumBy.id("org.wikipedia:id/search_src_text");
    private final By searchResultTitle = AppiumBy.id("org.wikipedia:id/page_list_item_title");

    public SearchScreen(AppiumDriver<?> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void typeQuery(String text) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(text);
    }

    public void selectResultByIndex(int index) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchResultTitle));
        var results = driver.findElements(searchResultTitle);
        if (results.isEmpty() || index >= results.size()) {
            throw new IllegalStateException("Not enough search results. Requested index: " + index);
        }
        results.get(index).click();
    }
}
