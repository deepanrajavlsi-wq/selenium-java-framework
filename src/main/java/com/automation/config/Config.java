package com.automation.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration class for managing test environment settings
 */
public class Config {
    private static Properties properties;
    private static final String CONFIG_FILE = "src/main/resources/config.properties";

    static {
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            properties.load(fis);
        } catch (IOException e) {
            System.err.println("Failed to load configuration file: " + CONFIG_FILE);
            e.printStackTrace();
        }
    }

    public static String getBrowser() {
        return properties.getProperty("browser", "chrome");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getTestUrl() {
        return properties.getProperty("test.url");
    }

    public static String getProdUrl() {
        return properties.getProperty("prod.url");
    }

    public static int getImplicitWait() {
        return Integer.parseInt(properties.getProperty("implicit.wait", "10"));
    }

    public static int getExplicitWait() {
        return Integer.parseInt(properties.getProperty("explicit.wait", "15"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(properties.getProperty("page.load.timeout", "20"));
    }

    public static String getLogLevel() {
        return properties.getProperty("log.level", "INFO");
    }

    public static String getValidUsername() {
        return properties.getProperty("valid.username");
    }

    public static String getValidPassword() {
        return properties.getProperty("valid.password");
    }
}