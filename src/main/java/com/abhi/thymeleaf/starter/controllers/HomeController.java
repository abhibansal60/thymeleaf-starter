package com.abhi.thymeleaf.starter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("features", Arrays.asList(
                "This is F1",
                "This is F2",
                "This is F3",
                "This is F4"
        ));
        return "index";
    }
}
