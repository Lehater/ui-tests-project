package com.example.tests.web.tests;

import com.example.tests.web.pages.BookPage;
import com.example.tests.web.pages.CategoryPage;
import com.example.tests.web.pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BooksSiteTest extends BaseWebTest {

    @Test(groups = "web")
    public void travelCategoryHeaderShouldBeCorrect() {
        HomePage homePage = new HomePage(driver);
        homePage.open(baseUrl);

        CategoryPage categoryPage = homePage.openTravelCategory();
        Assert.assertEquals(categoryPage.getHeaderText(), "Travel");
    }

    @Test(groups = "web")
    public void firstBookDetailsShouldBeDisplayed() {
        HomePage homePage = new HomePage(driver);
        homePage.open(baseUrl);

        CategoryPage categoryPage = homePage.openTravelCategory();
        BookPage bookPage = categoryPage.openFirstBook();

        Assert.assertFalse(bookPage.getBookTitle().isEmpty(), "Book title should not be empty");
        Assert.assertFalse(bookPage.getPrice().isEmpty(), "Book price should not be empty");
        Assert.assertFalse(bookPage.getAvailabilityText().isEmpty(), "Availability text should not be empty");
        Assert.assertTrue(bookPage.isAddToBasketButtonDisplayed(), "Add to basket button should be visible");
    }

    @Test(groups = "web")
    public void bookTitleShouldMatchBetweenListAndDetails() {
        HomePage homePage = new HomePage(driver);
        homePage.open(baseUrl);

        String expectedTitle = homePage.getFirstBookTitleOnHomePage();
        BookPage bookPage = homePage.openFirstBookFromHomePage();

        Assert.assertEquals(bookPage.getBookTitle(), expectedTitle, "Book title should match between list and details page");
    }

    @Test(groups = "web")
    public void paginationShouldChangeBooksList() {
        HomePage homePage = new HomePage(driver);
        homePage.open(baseUrl);

        List<String> titlesOnFirstPage = homePage.getAllBookTitlesOnHomePage();

        Assert.assertTrue(homePage.hasNextPage(), "Home page should have next page for pagination test");

        homePage.goToNextPage();
        List<String> titlesOnNextPage = homePage.getAllBookTitlesOnHomePage();

        Assert.assertNotEquals(titlesOnFirstPage, titlesOnNextPage, "Books list should change after pagination");
    }
}
