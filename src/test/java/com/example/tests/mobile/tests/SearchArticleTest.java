package com.example.tests.mobile.tests;

import com.example.tests.mobile.pages.WikipediaArticlePage;
import com.example.tests.mobile.pages.WikipediaHomePage;
import com.example.tests.mobile.pages.WikipediaSearchPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchArticleTest extends BaseMobileTest {

    @Test(groups = "mobile")
    public void searchSeleniumArticleAndCheckTitleContainsQuery() {
        WikipediaHomePage homePage = new WikipediaHomePage(driver);
        WikipediaSearchPage searchPage = homePage.tapOnSearch();

        String query = "Selenium";
        searchPage.searchFor(query);
        WikipediaArticlePage articlePage = searchPage.openFirstResult();

        Assert.assertFalse(articlePage.getTitle().isEmpty(),
                "Article title should not be empty");
    }
}
