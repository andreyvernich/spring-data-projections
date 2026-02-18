package org.example.springdataprojection.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PrivateController {

    @GetMapping("/private")
    public String home() {
        return "Booyah!";
    }
}
