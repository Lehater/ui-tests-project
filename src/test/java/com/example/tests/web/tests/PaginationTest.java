package com.example.tests.web.tests;

import com.example.tests.web.pages.CategoryPage;
import com.example.tests.web.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PaginationTest extends BaseWebTest {

    @Test(groups = "web-legacy")
    public void nextPage_shouldChangeBookList() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open(baseUrl);
        mainPage.openCategoryByName("Mystery");

        CategoryPage categoryPage = new CategoryPage(driver);
        List<String> firstPageTitles = categoryPage.getBookTitlesOnPage();

        Assert.assertTrue(categoryPage.hasNextPage(), "Category should have next page for pagination test");

        categoryPage.goToNextPage();
        List<String> secondPageTitles = categoryPage.getBookTitlesOnPage();

        Assert.assertNotEquals(secondPageTitles, firstPageTitles,
                "Book list on the next page should differ from the first page");
    }
}
