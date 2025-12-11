package com.example.tests.mobile.pages;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseMobilePage {

    protected final RemoteWebDriver driver;
    protected final WebDriverWait wait;

    protected BaseMobilePage(RemoteWebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
}

