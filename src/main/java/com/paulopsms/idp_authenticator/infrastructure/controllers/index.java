package com.paulopsms.idp_authenticator.infrastructure.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class index {

    @RequestMapping("/")
    public String index() {
        return "Hello World!";
    }
}
