package com.automation.drivers;

import com.automation.config.Config;
import com.automation.utils.Logger;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

/**
 * Factory class for creating WebDriver instances
 */
public class DriverFactory {

    /**
     * Create WebDriver instance based on configured browser
     */
    public static WebDriver createDriver() {
        String browser = Config.getBrowser().toLowerCase();
        WebDriver driver;

        switch (browser) {
            case "chrome":
                driver = createChromeDriver();
                break;
            case "firefox":
                driver = createFirefoxDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Config.getImplicitWait()));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Config.getPageLoadTimeout()));

        Logger.info("WebDriver initialized for browser: " + browser);
        return driver;
    }

    /**
     * Create Chrome WebDriver
     */
    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        if (Config.isHeadless()) {
            options.addArguments("--headless");
        }

        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-gpu",
                "--window-size=1920,1080"
        );

        return new ChromeDriver(options);
    }

    /**
     * Create Firefox WebDriver
     */
    private static WebDriver createFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();

        if (Config.isHeadless()) {
            options.addArguments("--headless");
        }

        return new FirefoxDriver(options);
    }

    /**
     * Quit WebDriver
     */
    public static void quitDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
            Logger.info("WebDriver closed");
        }
    }
}