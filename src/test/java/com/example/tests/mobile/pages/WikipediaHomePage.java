package com.example.tests.mobile.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WikipediaHomePage extends BaseMobilePage {

    private final By searchContainer = By.id("org.wikipedia:id/search_container");
    private final By onboardingSkipButton = By.id("org.wikipedia:id/fragment_onboarding_skip_button");

    public WikipediaHomePage(RemoteWebDriver driver) {
        super(driver);

        // Dismiss onboarding if it is shown
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(onboardingSkipButton)).click();
        } catch (TimeoutException ignored) {
            // Onboarding not shown
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(searchContainer));
    }

    public WikipediaSearchPage tapOnSearch() {
        driver.findElement(searchContainer).click();
        return new WikipediaSearchPage(driver);
    }
}
