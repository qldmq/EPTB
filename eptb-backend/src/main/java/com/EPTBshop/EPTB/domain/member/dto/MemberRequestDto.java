package com.EPTBshop.EPTB.domain.member.dto;

import lombok.Getter;

@Getter
public class MemberRequestDto {
    // requestDto : Controller → Service로 전달할 때 사용 / 회원가입 정보, 로그인 요청 등

    private String memberId;
    private String email;
    private String password;
    private String nickname;
    private Integer loginType;
}
