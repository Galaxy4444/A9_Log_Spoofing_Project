package com.example.a9_log_spoofing_project;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecureLogger {
    private static final Logger logger = LoggerFactory.getLogger(SecureLogger.class);

    public static void logInfo(String message, Object... params) {
        logger.info(sanitize(message), sanitizeParams(params));
    }

    public static void logError(String message, Throwable throwable) {
        logger.error(sanitize(message), throwable);
    }

    private static String sanitize(String input) {
        if (input == null) {
            return null;
        }
        // Replace newlines and carriage returns with spaces
        return input.replace("\n", "\\n").replace("\r", "\\r");
    }

    private static Object[] sanitizeParams(Object[] params) {
        Object[] sanitizedParams = new Object[params.length];
        for (int i = 0; i < params.length; i++) {
            if (params[i] instanceof String) {
                sanitizedParams[i] = sanitize((String) params[i]);
            } else {
                sanitizedParams[i] = params[i];
            }
        }
        return sanitizedParams;
    }
}
