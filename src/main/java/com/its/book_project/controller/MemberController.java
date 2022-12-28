package com.its.book_project.controller;

import com.its.book_project.dto.MemberDTO;
import com.its.book_project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

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
        return "redirect:/book/";
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

    // 로그인 페이지 출력
    @GetMapping("/login")
    public String loginForm() {
        return "memberPages/memberLogin";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            session.setAttribute("loginId", memberDTO.getMemberId());
            return "redirect:/book/";
        } else {
            return "memberPages/memberLogin";
        }
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/book/";
    }

    // 회원목록 처리
    @GetMapping("/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList", memberDTOList);
        return "memberPages/memberList";
    }

    // 회원조회 처리
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "memberPages/memberDetail";
    }

    // 회원수정 페이지 출력
    @GetMapping("/update")
    public String updateForm(Model model, HttpSession session) {
        String loginId = (String) session.getAttribute("loginId");
        MemberDTO memberDTO = memberService.findByMemberId(loginId);
        model.addAttribute("member", memberDTO);
        return "memberPages/memberUpdate";
    }

    // 회원수정 처리
    @PostMapping("/update")
    public String update(@ModelAttribute MemberDTO memberDTO) {
        memberService.update(memberDTO);
        return "redirect:/book/";
    }
}
