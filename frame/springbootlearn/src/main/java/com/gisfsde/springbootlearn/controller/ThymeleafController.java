package com.gisfsde.springbootlearn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
    @GetMapping("/thymeleaf")
    public String index(ModelMap map) {
        return "thy/thymeleaf";
    }
}
