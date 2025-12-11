package com.example.tests.mobile.tests;

import com.example.tests.mobile.pages.WikipediaArticlePage;
import com.example.tests.mobile.pages.WikipediaHomePage;
import com.example.tests.mobile.pages.WikipediaSearchPage;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ArticleScrollTest extends BaseMobileTest {

    @Test(groups = "mobile")
    public void scrollArticleDownAndCheckTitleStillVisible() {
        WikipediaHomePage homePage = new WikipediaHomePage(driver);
        WikipediaSearchPage searchPage = homePage.tapOnSearch();

        searchPage.searchFor("Selenium");
        WikipediaArticlePage articlePage = searchPage.openFirstResult();
        String titleBeforeScroll = articlePage.getTitle();

        if (driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", new java.util.HashMap<String, Object>() {{
                put("left", 100);
                put("top", 400);
                put("width", 800);
                put("height", 1200);
                put("direction", "down");
                put("percent", 0.7);
            }});
        } else {
            // fallback: send PAGE_DOWN key if supported
            try {
                io.appium.java_client.android.AndroidDriver androidDriver =
                        (io.appium.java_client.android.AndroidDriver) driver;
                androidDriver.pressKey(new KeyEvent(AndroidKey.PAGE_DOWN));
            } catch (ClassCastException ignored) {
                // if cast fails, we just rely on the initial state
            }
        }

        String titleAfterScroll = articlePage.getTitle();
        Assert.assertEquals(titleAfterScroll, titleBeforeScroll, "Article title should remain the same after scroll");
    }
}
