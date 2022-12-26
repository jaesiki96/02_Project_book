package com.its.book_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // index 페이지
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 관리자 페이지
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }
}
