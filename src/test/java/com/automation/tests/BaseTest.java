package com.automation.tests;

import com.automation.config.Config;
import com.automation.drivers.DriverFactory;
import com.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Base test class with setup and teardown
 */
public class BaseTest {
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        Logger.info("========== Test Setup Started ==========");
        driver = DriverFactory.createDriver();
        Logger.info("========== Test Setup Completed ==========");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        Logger.info("========== Test Teardown Started ==========");
        if (driver != null) {
            DriverFactory.quitDriver(driver);
        }
        Logger.info("========== Test Teardown Completed ==========");
    }
}