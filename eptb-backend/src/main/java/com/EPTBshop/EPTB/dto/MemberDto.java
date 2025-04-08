package com.EPTBshop.EPTB.dto;

import com.EPTBshop.EPTB.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Integer memberNum;
    private String memberId;
    private String email;
    private String password;
    private Integer loginType;
    private String nickname;

    public static MemberDto toDto(Member entity) {
        return MemberDto.builder()
                .memberNum(entity.getMemberNum())
                .memberId(entity.getMemberId())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .loginType(entity.getLoginType())
                .nickname(entity.getNickname())
                .build();
    }
}
