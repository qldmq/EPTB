package com.EPTBshop.EPTB.entity;

import com.EPTBshop.EPTB.dto.MemberDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@Table(name = "member")
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memberNum")
    private Integer memberNum;

    @Column(name = "memberId")
    private String memberId;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "loginType")
    private Integer loginType;

    @Column(name = "nickname")
    private String nickname;

    public static Member toEntity(MemberDto dto) {
        return Member.builder()
                .memberNum(dto.getMemberNum())
                .memberId(dto.getMemberId())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .loginType(dto.getLoginType())
                .nickname(dto.getNickname())
                .build();
    }
}
