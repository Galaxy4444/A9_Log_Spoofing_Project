package com.example.a9_log_spoofing_project;

import com.example.a9_log_spoofing_project.SecureLogger;
import com.example.a9_log_spoofing_project.VulnerableLogger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogSpoofingDemoController {

    @PostMapping("/login/vulnerable")
    public String vulnerableLogin(@RequestBody LoginRequest request) {
        VulnerableLogger.logInfo("User {} attempted to log in", request.getUsername());
        // Simulating authentication logic
        if ("admin".equals(request.getUsername()) && "password123".equals(request.getPassword())) {
            VulnerableLogger.logInfo("User {} successfully logged in", request.getUsername());
            return "Login successful";
        } else {
            VulnerableLogger.logInfo("Login failed for user {}", request.getUsername());
            return "Login failed";
        }
    }

    @PostMapping("/login/secure")
    public String secureLogin(@RequestBody LoginRequest request) {
        SecureLogger.logInfo("User {} attempted to log in", request.getUsername());
        // Simulating authentication logic
        if ("admin".equals(request.getUsername()) && "password123".equals(request.getPassword())) {
            SecureLogger.logInfo("User {} successfully logged in", request.getUsername());
            return "Login successful";
        } else {
            SecureLogger.logInfo("Login failed for user {}", request.getUsername());
            return "Login failed";
        }
    }

    // Inner class for the login request
    private static class LoginRequest {
        private String username;
        private String password;

        // Getters and setters
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}