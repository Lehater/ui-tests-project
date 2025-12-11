package com.example.tests.common.drivers;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class AppiumDriverFactory {

    public static AppiumDriver createAndroidDriver(String appiumServerUrl,
                                                   String platformName,
                                                   String deviceName,
                                                   String automationName,
                                                   String appPackage,
                                                   String appActivity) throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platformName", platformName);
        caps.setCapability("deviceName", deviceName);
        caps.setCapability("automationName", automationName);
        caps.setCapability("appPackage", appPackage);
        caps.setCapability("appActivity", appActivity);
        caps.setCapability("newCommandTimeout", 300);

        AppiumDriver driver = new AndroidDriver(new URL(appiumServerUrl), caps);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
}
