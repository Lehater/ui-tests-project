package com.example.tests.mobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WikipediaArticlePage extends BaseMobilePage {

    private final By webView = By.className("android.webkit.WebView");
    private final By gamesDialogContainer = By.id("org.wikipedia:id/dialogContainer");
    private final By gamesDialogCloseButton = By.id("org.wikipedia:id/closeButton");

    public WikipediaArticlePage(RemoteWebDriver driver) {
        super(driver);
        waitForArticleReady();
    }

    public String getTitle() {
        return driver.findElements(webView)
                .stream()
                .map(e -> e.getAttribute("text"))
                .filter(s -> s != null && !s.isEmpty())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No WebView with non-empty text found"));
    }

    private void waitForArticleReady() {
        WebDriverWait longWait = new WebDriverWait(driver, java.time.Duration.ofSeconds(20));
        longWait.until(d -> {
            if (!driver.findElements(gamesDialogContainer).isEmpty()
                    && !driver.findElements(gamesDialogCloseButton).isEmpty()) {
                driver.findElement(gamesDialogCloseButton).click();
            }
            return !driver.findElements(webView).isEmpty();
        });
    }
}
