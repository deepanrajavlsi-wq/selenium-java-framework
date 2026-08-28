package com.automation.utils;

import org.apache.logging.log4j.LogManager;

/**
 * Logger wrapper class for unified logging
 */
public class Logger {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void info(String message, Object... params) {
        logger.info(message, params);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void debug(String message, Object... params) {
        logger.debug(message, params);
    }

    public static void warn(String message) {
        logger.warn(message);
    }

    public static void warn(String message, Object... params) {
        logger.warn(message, params);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    public static void error(String message, Object... params) {
        logger.error(message, params);
    }
}