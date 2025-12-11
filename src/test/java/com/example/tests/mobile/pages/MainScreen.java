package com.example.tests.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Главный экран Wikipedia (экран "Explore"/"Главная").
 */
public class MainScreen {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    // Локатор строки поиска на главном экране
    private final By searchContainer = AppiumBy.id("org.wikipedia:id/search_container");

    // (опционально) кнопка меню/настроек, если понадобится для смены языка
    private final By menuButton = AppiumBy.accessibilityId("Navigate up");

    public MainScreen(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void waitForLoaded() {
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
