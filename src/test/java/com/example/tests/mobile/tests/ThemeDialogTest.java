package com.example.tests.mobile.tests;

import com.example.tests.mobile.pages.WikipediaArticlePage;
import com.example.tests.mobile.pages.WikipediaHomePage;
import com.example.tests.mobile.pages.WikipediaSearchPage;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class ThemeDialogTest extends BaseMobileTest {

    @Test(groups = "mobile")
    public void openThemeDialogFromArticle_andSeeThemeOptions() {
        RemoteWebDriver remoteDriver = driver;
        AppiumDriver appiumDriver = (AppiumDriver) remoteDriver;

        // Open article as in other scenarios
        WikipediaHomePage homePage = new WikipediaHomePage(remoteDriver);
        WikipediaSearchPage searchPage = homePage.tapOnSearch();

        searchPage.searchFor("Selenium");
        WikipediaArticlePage articlePage = searchPage.openFirstResult();
        Assert.assertFalse(articlePage.getTitle().isEmpty(), "Article title should not be empty before opening theme dialog");

        // Open theme dialog from bottom toolbar
        By themeButton = AppiumBy.id("org.wikipedia:id/page_theme");
        WebDriverWait wait = new WebDriverWait(appiumDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(themeButton)).click();

        // Verify that at least one theme-related option is visible
        By matchSystemThemeSwitch = AppiumBy.id("org.wikipedia:id/theme_chooser_match_system_theme_switch");
        wait.until(ExpectedConditions.visibilityOfElementLocated(matchSystemThemeSwitch));

        boolean hasThemeSwitch = !appiumDriver.findElements(matchSystemThemeSwitch).isEmpty();
        Assert.assertTrue(hasThemeSwitch, "Theme dialog should show match-system-theme switch");
    }
}
