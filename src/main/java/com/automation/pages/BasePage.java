package com.automation.pages;

import com.automation.utils.Logger;
import com.automation.waits.WaitManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

/**
 * Base page class with common methods for all pages
 */
public class BasePage {
    protected WebDriver driver;
    protected WaitManager waitManager;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitManager = new WaitManager(driver);
    }

    /**
     * Navigate to URL
     */
    public void navigateTo(String url) {
        driver.get(url);
        Logger.info("Navigated to: " + url);
    }

    /**
     * Get page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Get current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * Find element
     */
    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    /**
     * Click on element
     */
    public void clickElement(By locator) {
        WebElement element = waitManager.waitForElementToBeClickable(locator);
        element.click();
        Logger.info("Clicked on element: " + locator);
    }

    /**
     * Send text to element
     */
    public void sendText(By locator, String text) {
        WebElement element = waitManager.waitForElementToBeVisible(locator);
        element.clear();
        element.sendKeys(text);
        Logger.info("Sent text '" + text + "' to element: " + locator);
    }

    /**
     * Get text from element
     */
    public String getText(By locator) {
        WebElement element = waitManager.waitForElementToBeVisible(locator);
        String text = element.getText();
        Logger.info("Retrieved text '" + text + "' from element: " + locator);
        return text;
    }

    /**
     * Check if element is displayed
     */
    public boolean isElementDisplayed(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if element is enabled
     */
    public boolean isElementEnabled(By locator) {
        try {
            WebElement element = driver.findElement(locator);
            return element.isEnabled();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get attribute value
     */
    public String getAttributeValue(By locator, String attributeName) {
        WebElement element = driver.findElement(locator);
        return element.getAttribute(attributeName);
    }

    /**
     * Double click on element
     */
    public void doubleClickElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.doubleClick(element).perform();
        Logger.info("Double clicked on element: " + locator);
    }

    /**
     * Right click on element
     */
    public void rightClickElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.contextClick(element).perform();
        Logger.info("Right clicked on element: " + locator);
    }

    /**
     * Hover over element
     */
    public void hoverElement(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        Logger.info("Hovered on element: " + locator);
    }

    /**
     * Wait for page to load
     */
    public void waitForPageToLoad() {
        driver.executeScript("return document.readyState").equals("complete");
        Logger.info("Page loaded successfully");
    }
}