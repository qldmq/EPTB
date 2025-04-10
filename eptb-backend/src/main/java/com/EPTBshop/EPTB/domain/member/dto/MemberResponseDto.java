package com.EPTBshop.EPTB.domain.member.dto;

import com.EPTBshop.EPTB.domain.member.domain.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {
    // Service → Controller → Client로 응답할 때 사용 / 조회 결과, 응답 데이터 등

    private Integer memberNum;
    private String memberId;
    private String email;
    private String nickname;

    public MemberResponseDto(Member member) {
        this.memberNum = member.getMemberNum();
        this.memberId = member.getMemberId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
    }
}
