package com.example.a9_log_spoofing_project;

public class LogSpoofingDemo {
    public static void main(String[] args) {
        // Vulnerable logger
        VulnerableLogger.logInfo("User {} logged in", "alice");
        VulnerableLogger.logInfo("User {} logged in\nINFO: User admin logged in", "mallory");

        // Secure logger
        SecureLogger.logInfo("User {} logged in", "alice");
        SecureLogger.logInfo("User {} logged in\nINFO: User admin logged in", "mallory");
    }
}
