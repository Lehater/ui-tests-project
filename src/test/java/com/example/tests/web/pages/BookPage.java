package com.example.tests.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BookPage extends BasePage {

    private final By title = By.cssSelector(".product_main h1");
    private final By price = By.cssSelector(".product_main .price_color");
    private final By availability = By.cssSelector(".product_main .availability");
    // На странице книги кнопка "Add to basket" может меняться по классам,
    // поэтому используем более общий, но устойчивый селектор по форме и кнопке.
    private final By addToBasketButton = By.cssSelector("form#add_to_basket_form button");

    public BookPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(title));
    }

    public String getBookTitle() {
        return driver.findElement(title).getText();
    }

    public String getPrice() {
        return driver.findElement(price).getText();
    }

    public String getAvailabilityText() {
        return driver.findElement(availability).getText();
    }

    public boolean isAddToBasketButtonDisplayed() {
        return driver.findElement(addToBasketButton).isDisplayed();
    }
}
