package com.its.book_project.controller;

import com.its.book_project.dto.BookDTO;
import com.its.book_project.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
        return "redirect:/book/";
    }

    // 책 목록 처리
    @GetMapping("/")
    public String findAll(Model model) {
        List<BookDTO> bookDTOList = bookService.findAll();
        model.addAttribute("bookList", bookDTOList);
        return "bookPages/bookList";
    }

    // 책 조회 처리
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        BookDTO bookDTO = bookService.findById(id);
        model.addAttribute("book", bookDTO);
        return "bookPages/bookDetail";
    }

    // 페이징 처리
    @GetMapping
    public String paging(@PageableDefault(page = 1) Pageable pageable, Model model) {
        System.out.println("page" + pageable.getPageNumber());
        Page<BookDTO> bookDTOList = bookService.paging(pageable);
        model.addAttribute("bookList", bookDTOList);
        int blockLimit = 3;
        int startPage = (((int) (Math.ceil((double) pageable.getPageNumber() / blockLimit))) - 1) * blockLimit + 1;
        int endPage = ((startPage + blockLimit - 1) < bookDTOList.getTotalPages()) ? startPage + blockLimit - 1 : bookDTOList.getTotalPages();
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "bookPages/bookPaging";
    }
}
