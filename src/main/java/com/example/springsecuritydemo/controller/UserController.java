package com.example.springsecuritydemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping
    public String getMethod(Principal principal) {
        return String.format("Hello %s!", principal.getName());
    }

    @GetMapping("/innerUser")
    public String getInnerMethod(Principal principal) {
        return String.format("Hello inner %s!", principal.getName());
    }

}
