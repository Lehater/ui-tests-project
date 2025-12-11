package com.example.tests.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By productTitle = By.cssSelector(".product_main h1");
    private final By price = By.cssSelector(".product_main .price_color");
    private final By availability = By.cssSelector(".product_main .availability");
    private final By addToBasketButton = By.cssSelector("form#add_to_basket_form button");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getProductTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
        return title.getText().trim();
    }

    public boolean isPriceDisplayed() {
        return !driver.findElements(price).isEmpty()
                && driver.findElement(price).isDisplayed();
    }

    public String getPriceText() {
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(price));
        return priceElement.getText().trim();
    }

    public boolean isAvailabilityDisplayed() {
        return !driver.findElements(availability).isEmpty()
                && driver.findElement(availability).isDisplayed();
    }

    public String getAvailabilityText() {
        WebElement availabilityElement = wait.until(ExpectedConditions.visibilityOfElementLocated(availability));
        return availabilityElement.getText().trim();
    }

    public boolean isAddToBasketButtonDisplayed() {
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(addToBasketButton));
        return button.isDisplayed();
    }
}
