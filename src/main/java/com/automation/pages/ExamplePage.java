package com.automation.pages;

import com.automation.utils.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Example page object model
 */
public class ExamplePage extends BasePage {

    // Locators
    private static final By TITLE = By.tagName("h1");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private static final By USERNAME_INPUT = By.id("username");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By SUBMIT_BUTTON = By.id("submit");
    private static final By ERROR_MESSAGE = By.className("error-message");

    public ExamplePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Get page title text
     */
    public String getPageTitleText() {
        return getText(TITLE);
    }

    /**
     * Click login button
     */
    public void clickLoginButton() {
        clickElement(LOGIN_BUTTON);
        Logger.info("Clicked login button");
    }

    /**
     * Enter username
     */
    public void enterUsername(String username) {
        sendText(USERNAME_INPUT, username);
    }

    /**
     * Enter password
     */
    public void enterPassword(String password) {
        sendText(PASSWORD_INPUT, password);
    }

    /**
     * Submit login form
     */
    public void submitForm() {
        clickElement(SUBMIT_BUTTON);
        Logger.info("Submitted login form");
    }

    /**
     * Get error message
     */
    public String getErrorMessage() {
        return getText(ERROR_MESSAGE);
    }

    /**
     * Login with credentials
     */
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        submitForm();
        Logger.info("Login completed with username: " + username);
    }
}