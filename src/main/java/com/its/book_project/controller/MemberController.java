package com.its.book_project.controller;

import com.its.book_project.dto.MemberDTO;
import com.its.book_project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    // 회원가입 페이지 출력
    @GetMapping("/save")
    public String saveForm() {
        return "memberPages/memberSave";
    }

    // 회원가입 처리
    @PostMapping("/save")
    public String save(@ModelAttribute MemberDTO memberDTO) {
        memberService.save(memberDTO);
        return "index";
    }

    // 아이디 중복 체크
    @PostMapping("/idDupCheck")
    public ResponseEntity idDupCheck(@RequestParam("inputId") String memberId) {
        String checkResult = memberService.idDupCheck(memberId);
        if (checkResult != null) {
            return new ResponseEntity<>("사용가능", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("사용불가", HttpStatus.CONFLICT);
        }
    }

    // 전화번호 중복 체크
    @PostMapping("/phoneDupCheck")
    public ResponseEntity phoneDupCheck(@RequestParam("inputPhone") String memberPhone) {
        String checkResult = memberService.phoneDupCheck(memberPhone);
        if (checkResult != null) {
            return new ResponseEntity<>("사용가능", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("사용불가", HttpStatus.CONFLICT);
        }
    }
}
