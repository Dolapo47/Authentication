package com.softaliance.authentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class Ping {

    @GetMapping("/ping")
    public String hello() {
        return "pong!";
    }

}
