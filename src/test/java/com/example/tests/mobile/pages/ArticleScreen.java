package com.example.tests.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Экран открытой статьи Wikipedia.
 */
public class ArticleScreen {

    private final AppiumDriver<?> driver;
    private final WebDriverWait wait;

    private final By articleTitle = AppiumBy.id("org.wikipedia:id/view_page_title_text");

    public ArticleScreen(AppiumDriver<?> driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public String getTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(articleTitle));
        return title.getText().trim();
    }

    public void scrollDown() {
        // Простейший жест прокрутки: свайп от середины экрана к нижней трети.
        var size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = (int) (size.getHeight() * 0.7);
        int endY = (int) (size.getHeight() * 0.3);

        new io.appium.java_client.TouchAction<>(driver)
                .press(startX, startY)
                .waitAction(java.time.Duration.ofMillis(500))
                .moveTo(startX, endY)
                .release()
                .perform();
    }

    public boolean waitForElementWithText(String text) {
        By locator = AppiumBy.androidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).scrollTextIntoView(\"" + text + "\")");
        try {
            WebElement el = driver.findElement(locator);
            return el.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
