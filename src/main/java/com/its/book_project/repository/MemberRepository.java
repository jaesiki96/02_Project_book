package com.its.book_project.repository;

import com.its.book_project.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 아이디 중복체크
    Optional<MemberEntity> findByMemberId(String memberId);
}
