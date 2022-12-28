package com.its.book_project.service;
import com.its.book_project.dto.MemberDTO;
import com.its.book_project.entity.MemberEntity;
import com.its.book_project.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    // 로그인 처리
    public MemberDTO login(MemberDTO memberDTO) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(memberDTO.getMemberId());
        if (optionalMemberEntity.isPresent()) {
            MemberEntity memberEntity = optionalMemberEntity.get();
            if (memberEntity.getMemberPassword().equals(memberDTO.getMemberPassword())) {
                return MemberDTO.toDTO(memberEntity);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    // 회원목록 처리
    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toDTO(memberEntity));
        }
        return memberDTOList;
    }

    // 회원조회 처리
    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    // 회원수정 페이지 출력
    public MemberDTO findByMemberId(String loginId) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberId(loginId);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    // 회원수정 처리
    public void update(MemberDTO memberDTO) {
        MemberEntity toUpdateEntity = MemberEntity.toUpdateEntity(memberDTO);
        memberRepository.save(toUpdateEntity);
    }
}
