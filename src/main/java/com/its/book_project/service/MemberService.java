package com.its.book_project.service;
import com.its.book_project.dto.MemberDTO;
import com.its.book_project.entity.MemberEntity;
import com.its.book_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    // 회원가입 처리
    public Long save(MemberDTO memberDTO) {
        Long savedId = memberRepository.save(MemberEntity.toSaveEntity(memberDTO)).getId();
        return savedId;
    }

    // 아이디 중복 체크
    public String idDupCheck(String memberId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberId);
        if (optionalMemberEntity.isEmpty()) {
            return "ok";
        } else {
            return null;
        }
    }

    // 전화번호 중복 체크
    public String phoneDupCheck(String memberPhone) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberPhone(memberPhone);
        if (optionalMemberEntity.isEmpty()) {
            return "ok";
        } else {
            return null;
        }
    }
}
