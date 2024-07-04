package com.example.a9_log_spoofing_project;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

@RestController
public class HelloController {

    @GetMapping("/hello/vulnerable")
    public String helloVulnerable(@RequestParam(name = "name", required = false) String encodedName) {
        String name = decodeName(encodedName);
        VulnerableLogger.logInfo("Received request with name: {}", name);
        return "Hello, " + name + " (Vulnerable Logger)";
    }

    @GetMapping("/hello/secure")
    public String helloSecure(@RequestParam(name = "name", required = false) String encodedName) {
        String name = decodeName(encodedName);
        SecureLogger.logInfo("Received request with name: {}", name);
        return "Hello, " + name + " (Secure Logger)";
    }

    @GetMapping("/hello/compare")
    public String helloCompare(@RequestParam(name = "name", required = false) String encodedName) {
        String name = decodeName(encodedName);

        VulnerableLogger.logInfo("Vulnerable - Received request with name: {}", name);
        VulnerableLogger.logInfo("Vulnerable - User {} logged in\nINFO: User admin logged in", name);

        SecureLogger.logInfo("Secure - Received request with name: {}", name);
        SecureLogger.logInfo("Secure - User {} logged in\nINFO: User admin logged in", name);

        return "Hello, " + name + " (Comparison Done)";
    }

    private String decodeName(String encodedName) {
        return encodedName != null ?
                URLDecoder.decode(encodedName, StandardCharsets.UTF_8) : "World";
    }
}