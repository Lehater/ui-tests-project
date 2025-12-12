package com.example.tests.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By categoryList = By.cssSelector(".side_categories ul li ul li a");
    private final By firstBookTitle = By.cssSelector("section div ol li:first-child h3 a");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open(String baseUrl) {
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
    }

    public void openCategoryByName(String categoryName) {
        for (WebElement el : driver.findElements(categoryList)) {
            if (el.getText().trim().equalsIgnoreCase(categoryName)) {
                el.click();
                return;
            }
        }
        throw new IllegalStateException("Category not found: " + categoryName);
    }

    public String getFirstBookTitleOnHome() {
        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(firstBookTitle));
        return titleElement.getAttribute("title");
    }

    public void openFirstBookOnHome() {
        WebElement titleElement = wait.until(ExpectedConditions.elementToBeClickable(firstBookTitle));
        titleElement.click();
    }
}
