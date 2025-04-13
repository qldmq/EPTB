package com.EPTBshop.EPTB.domain.member.domain;

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

    @Column(name = "role")
    private String role;
}
