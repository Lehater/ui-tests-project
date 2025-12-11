package com.example.tests.web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryPage extends BasePage {

    private final By pageHeader = By.cssSelector("div.page-header h1");
    private final By bookTitles = By.cssSelector("section div ol li h3 a");
    private final By nextButton = By.cssSelector(".next a");

    public CategoryPage(WebDriver driver) {
        super(driver);
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
    }

    /**
     * Заголовок категории (совместимо с getCategoryTitle() из старых тестов).
     */
    public String getHeaderText() {
        return driver.findElement(pageHeader).getText();
    }

    /**
     * Поддержка старого имени метода из NavigationTest.
     */
    public String getCategoryTitle() {
        return getHeaderText();
    }

    public BookPage openFirstBook() {
        driver.findElement(bookTitles).click();
        return new BookPage(driver);
    }

    public List<String> getAllBookTitlesOnPage() {
        return driver.findElements(bookTitles)
                .stream()
                .map(e -> e.getAttribute("title"))
                .collect(Collectors.toList());
    }

    /**
     * Поддержка старого имени метода из PaginationTest.
     */
    public List<String> getBookTitlesOnPage() {
        return getAllBookTitlesOnPage();
    }

    public CategoryPage goToNextPage() {
        WebElement next = driver.findElement(nextButton);
        next.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(bookTitles));
        return new CategoryPage(driver);
    }

    /**
     * Используется в PaginationTest для проверки наличия следующей страницы.
     */
    public boolean hasNextPage() {
        List<WebElement> nextButtons = driver.findElements(nextButton);
        return !nextButtons.isEmpty() && nextButtons.get(0).isDisplayed();
    }
}
