package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping("/public")
    public String publicApi() {
        return "This is PUBLIC API";
    }

    @GetMapping("/private")
    public String privateApi() {
        return "This is PRIVATE API";
    }
    
 // ✅ Public POST API
    @PostMapping("/public")
    public String publicPost(@RequestBody(required = false) String message) {
        if (message == null || message.isEmpty()) {
            message = "Public POST API";
        }
        return "PUBLIC POST API received: " + message;
    }

    // ✅ Private POST API (needs authentication)
    @PostMapping("/private")
    public String privatePost(@RequestBody(required = false) String message) {
        if (message == null || message.isEmpty()) {
            message = "Private POST API";
        }
        return "PRIVATE POST API received: " + message;
    }
}
