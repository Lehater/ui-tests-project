package com.example.tests.common;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class MobileDriverFactory {

    public static RemoteWebDriver createAndroidDriver(Properties properties) throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options()
                .setPlatformName(properties.getProperty("mobile.platformName", "Android"))
                .setDeviceName(properties.getProperty("mobile.deviceName", "Android Emulator"))
                .setAutomationName(properties.getProperty("mobile.automationName", "UiAutomator2"))
                .setAppPackage(properties.getProperty("mobile.appPackage"))
                .setAppActivity(properties.getProperty("mobile.appActivity"));

        String appiumServerUrl = properties.getProperty("mobile.appiumServerUrl", "http://127.0.0.1:4723/");
        return new AndroidDriver(new URL(appiumServerUrl), options);
    }
}
