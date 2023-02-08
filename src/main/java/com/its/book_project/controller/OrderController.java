package com.its.book_project.controller;

import com.its.book_project.dto.OrderDTO;
import com.its.book_project.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody OrderDTO orderDTO, HttpSession session) {
        String memberEmail = (String) session.getAttribute("loginId");
        orderService.save(orderDTO, memberEmail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
