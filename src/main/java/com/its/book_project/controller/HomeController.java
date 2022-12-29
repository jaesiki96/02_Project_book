package com.its.book_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Home 출력
    @GetMapping("/")
    public String Home() {
        return "redirect:/book/";
    }
}
