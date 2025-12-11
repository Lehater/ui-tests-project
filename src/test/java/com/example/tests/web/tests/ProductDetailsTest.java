package com.example.tests.web.tests;

import com.example.tests.web.pages.CategoryPage;
import com.example.tests.web.pages.MainPage;
import com.example.tests.web.pages.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductDetailsTest extends BaseWebTest {

    @Test(groups = "web-legacy")
    public void openFirstProductFromCategory_shouldShowProductPageWithPriceAndAvailability() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open(baseUrl);
        mainPage.openCategoryByName("Travel");

        CategoryPage categoryPage = new CategoryPage(driver);
        categoryPage.openFirstBook();

        ProductPage productPage = new ProductPage(driver);

        Assert.assertFalse(productPage.getProductTitle().isEmpty(), "Product title should not be empty");
        Assert.assertTrue(productPage.isPriceDisplayed(), "Price should be displayed");
        Assert.assertTrue(productPage.isAvailabilityDisplayed(), "Availability should be displayed");
        Assert.assertTrue(productPage.isAddToBasketButtonDisplayed(), "Add to basket button should be visible");
    }

    @Test(groups = "web-legacy")
    public void openFirstBookFromHome_shouldKeepTitleConsistent() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open(baseUrl);
        String expectedTitle = mainPage.getFirstBookTitleOnHome();

        mainPage.openFirstBookOnHome();

        ProductPage productPage = new ProductPage(driver);
        String actualTitle = productPage.getProductTitle();

        Assert.assertEquals(actualTitle, expectedTitle,
                "Title on product page should match the title from the main page");
    }
}
