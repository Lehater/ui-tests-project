package com.example.tests.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Main Wikipedia screen ("Explore").
 */
public class MainScreen {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    private final By searchContainer = AppiumBy.id("org.wikipedia:id/search_container");
    private final By onboardingSkipButton = AppiumBy.id("org.wikipedia:id/fragment_onboarding_skip_button");
    private final By menuButton = AppiumBy.accessibilityId("Navigate up");

    public MainScreen(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void waitForLoaded() {
        // Dismiss onboarding if it is shown
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.visibilityOfElementLocated(onboardingSkipButton)).click();
        } catch (org.openqa.selenium.TimeoutException ignored) {
            // Onboarding not shown
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(searchContainer));
    }

    public void tapSearchField() {
        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(searchContainer));
        search.click();
    }

    public void openMenu() {
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        menu.click();
    }
}
