package com.its.book_project.dto;

import com.its.book_project.entity.MemberEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class MemberDTO {
    private Long id;
    private String memberEmail;
    private String memberPassword;
    private String memberName;
    private String memberPhone;
    private String memberAddress;
    private LocalDateTime memberCreatedDate;

    public static MemberDTO toDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPassword(memberEntity.getMemberPassword());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setMemberPhone(memberEntity.getMemberPhone());
        memberDTO.setMemberAddress(memberEntity.getMemberAddress());
        memberDTO.setMemberCreatedDate(memberEntity.getCreatedTime());
        return memberDTO;
    }
}
