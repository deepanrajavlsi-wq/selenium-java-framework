package com.automation.waits;

import com.automation.config.Config;
import com.automation.utils.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Wait manager class for handling explicit waits
 */
public class WaitManager {
    private WebDriver driver;
    private WebDriverWait wait;
    private int timeout;

    public WaitManager(WebDriver driver) {
        this.driver = driver;
        this.timeout = Config.getExplicitWait();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public WaitManager(WebDriver driver, int timeout) {
        this.driver = driver;
        this.timeout = timeout;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    /**
     * Wait for element to be visible
     */
    public WebElement waitForElementToBeVisible(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            Logger.info("Element is now visible: " + locator);
            return element;
        } catch (Exception e) {
            Logger.error("Failed to wait for element to be visible: " + locator, e);
            throw e;
        }
    }

    /**
     * Wait for element to be clickable
     */
    public WebElement waitForElementToBeClickable(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            Logger.info("Element is now clickable: " + locator);
            return element;
        } catch (Exception e) {
            Logger.error("Failed to wait for element to be clickable: " + locator, e);
            throw e;
        }
    }

    /**
     * Wait for element to be present in DOM
     */
    public WebElement waitForElementToBePresent(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            Logger.info("Element is now present: " + locator);
            return element;
        } catch (Exception e) {
            Logger.error("Failed to wait for element to be present: " + locator, e);
            throw e;
        }
    }

    /**
     * Wait for element to disappear
     */
    public boolean waitForElementToBeInvisible(By locator) {
        try {
            boolean isInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            Logger.info("Element is now invisible: " + locator);
            return isInvisible;
        } catch (Exception e) {
            Logger.error("Failed to wait for element to be invisible: " + locator, e);
            throw e;
        }
    }

    /**
     * Wait for URL to change
     */
    public boolean waitForUrlToChange(String url) {
        try {
            boolean changed = wait.until(ExpectedConditions.urlContains(url));
            Logger.info("URL changed to contain: " + url);
            return changed;
        } catch (Exception e) {
            Logger.error("Failed to wait for URL to change: " + url, e);
            throw e;
        }
    }
}