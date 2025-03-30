package com.EPTBshop.EPTB.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    private Integer memberNum;
    private String memberId;
    private String email;
    private String password;
    private Integer loginType;
    private String nickname;


}
