package com.example.a9_log_spoofing_project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class VulnerableLogger {
    private static final Logger logger = LoggerFactory.getLogger(VulnerableLogger.class);

    public static void logInfo(String message, Object... params) {
        logger.info(message, params);
    }

    public static void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}