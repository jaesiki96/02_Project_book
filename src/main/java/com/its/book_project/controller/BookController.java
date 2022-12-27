package com.its.book_project.controller;

import com.its.book_project.dto.BookDTO;
import com.its.book_project.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    // 책 등록 페이지 출력
    @GetMapping("/save")
    public String saveForm() {
        return "bookPages/bookSave";
    }

    // 책 등록 처리
    @PostMapping("/save")
    public String save(@ModelAttribute BookDTO bookDTO) throws IOException {
        bookService.save(bookDTO);
        return "bookPages/bookList";
    }

}
