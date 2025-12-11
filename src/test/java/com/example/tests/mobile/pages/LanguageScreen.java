package com.example.tests.mobile.pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Экран выбора языков (упрощённая модель, может потребовать корректировки локаторов под текущую версию приложения).
 */
public class LanguageScreen {

    private final AppiumDriver driver;
    private final WebDriverWait wait;

    private final By languageListItem = AppiumBy.id("org.wikipedia:id/language_local_name");
    private final By addLanguageButton = AppiumBy.id("org.wikipedia:id/add_language_button");

    public LanguageScreen(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void addLanguageIfNeeded(String languageName) {
        // Нажимаем "Add language", если есть такая кнопка
        List<WebElement> addButtons = driver.findElements(addLanguageButton);
        if (!addButtons.isEmpty()) {
            addButtons.get(0).click();
        }
        selectLanguage(languageName);
    }

    public void selectLanguage(String languageName) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(languageListItem));
        for (WebElement el : driver.findElements(languageListItem)) {
            if (el.getText().trim().equalsIgnoreCase(languageName)) {
                el.click();
                return;
            }
        }
        throw new IllegalStateException("Language not found: " + languageName);
    }
}
