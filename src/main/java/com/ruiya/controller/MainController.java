package com.ruiya.controller;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class MainController {
    public static void main(String[] args) {
        SpringApplication.run(MainController.class, args);
    }
}
