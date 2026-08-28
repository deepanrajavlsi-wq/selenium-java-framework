package com.automation.tests;

import com.automation.config.Config;
import com.automation.pages.ExamplePage;
import com.automation.utils.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Example test cases
 */
public class ExampleTest extends BaseTest {

    @Test(description = "Test page title")
    public void testPageTitle() {
        Logger.info("Starting test: testPageTitle");
        ExamplePage examplePage = new ExamplePage(driver);
        examplePage.navigateTo(Config.getBaseUrl());
        
        String title = examplePage.getPageTitle();
        Assert.assertNotNull(title, "Page title should not be null");
        Logger.info("Page title: " + title);
    }

    @Test(description = "Test element visibility")
    public void testElementVisibility() {
        Logger.info("Starting test: testElementVisibility");
        ExamplePage examplePage = new ExamplePage(driver);
        examplePage.navigateTo(Config.getBaseUrl());
        
        // Note: Update locator based on actual page
        String titleText = examplePage.getPageTitleText();
        Assert.assertNotNull(titleText, "Title should be visible");
        Logger.info("Title element is visible with text: " + titleText);
    }

    @Test(description = "Test login functionality")
    public void testLogin() {
        Logger.info("Starting test: testLogin");
        ExamplePage examplePage = new ExamplePage(driver);
        examplePage.navigateTo(Config.getBaseUrl());
        
        String username = Config.getValidUsername();
        String password = Config.getValidPassword();
        
        examplePage.login(username, password);
        Logger.info("Login test completed");
    }

    @Test(description = "Test URL navigation")
    public void testUrlNavigation() {
        Logger.info("Starting test: testUrlNavigation");
        ExamplePage examplePage = new ExamplePage(driver);
        examplePage.navigateTo(Config.getBaseUrl());
        
        String currentUrl = examplePage.getCurrentUrl();
        Assert.assertNotNull(currentUrl, "Current URL should not be null");
        Logger.info("Current URL: " + currentUrl);
    }
}