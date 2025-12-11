package com.example.tests.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    private static final String TRAVEL_CATEGORY_NAME = "Travel";

    private final By categoryList = By.cssSelector(".nav-list ul li a");
    private final By firstBookTitle = By.cssSelector("section div ol li:first-child h3 a");
    private final By bookTitles = By.cssSelector("section div ol li h3 a");
    private final By nextButton = By.cssSelector(".next a");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open(String baseUrl) {
        driver.get(baseUrl);
        wait.until(ExpectedConditions.visibilityOfElementLocated(categoryList));
    }

    public CategoryPage openTravelCategory() {
        for (WebElement category : driver.findElements(categoryList)) {
            if (TRAVEL_CATEGORY_NAME.equalsIgnoreCase(category.getText().trim())) {
                category.click();
                break;
            }
        }
        return new CategoryPage(driver);
    }

    public String getFirstBookTitleOnHomePage() {
        return driver.findElement(firstBookTitle).getAttribute("title");
    }

    public BookPage openFirstBookFromHomePage() {
        driver.findElement(firstBookTitle).click();
        return new BookPage(driver);
    }

    public java.util.List<String> getAllBookTitlesOnHomePage() {
        return driver.findElements(bookTitles)
                .stream()
                .map(e -> e.getAttribute("title"))
                .toList();
    }

    public boolean hasNextPage() {
        return !driver.findElements(nextButton).isEmpty();
    }

    public void goToNextPage() {
        WebElement next = driver.findElement(nextButton);
        next.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookTitles));
    }
}
