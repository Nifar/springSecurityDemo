package com.example.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("notSecure")
public class NotSecureController {

    @GetMapping
    public String getMethod() {
        return "This is notSecure controller";
    }

}
