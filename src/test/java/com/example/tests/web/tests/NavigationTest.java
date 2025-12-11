package com.example.tests.web.tests;

import com.example.tests.web.pages.CategoryPage;
import com.example.tests.web.pages.MainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseWebTest {

    @Test(groups = "web-legacy")
    public void openCategoryByName_shouldShowCorrectTitle() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open(baseUrl);

        String categoryName = "Travel";
        mainPage.openCategoryByName(categoryName);

        CategoryPage categoryPage = new CategoryPage(driver);
        String actualTitle = categoryPage.getCategoryTitle();

        Assert.assertEquals(actualTitle, categoryName,
                "Category title should match clicked category name");
    }
}
