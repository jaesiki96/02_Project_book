package com.its.book_project.controller;

import com.its.book_project.dto.BookDTO;
import com.its.book_project.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

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
        return "/bookPages/bookList";
    }

    // 책 목록 처리
    @GetMapping("/")
    public String findAll(Model model) {
        List<BookDTO> bookDTOList = bookService.findAll();
        model.addAttribute("bookList", bookDTOList);
        return "bookPages/bookList";
    }
}
