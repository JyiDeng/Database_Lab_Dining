package com.example.pj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class IndexController {

    @GetMapping({"", "/"})
    public String index() {
        return "Welcome to **Index** Page! --------- Enter in the url \"user\" to see user index and \"merchant\" to see merchant index.";
    }
}
