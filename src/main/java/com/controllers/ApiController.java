package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.models.factory.TileBoardFactory;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private TileBoardFactory tileBoardFactory;

    @GetMapping("/hello")
    String hello() {
        return "Hello World!";
    }

    @GetMapping("/generatemap")
    String mapResult() {
        return tileBoardFactory.generateBasicBoard().toString();
    }
}
