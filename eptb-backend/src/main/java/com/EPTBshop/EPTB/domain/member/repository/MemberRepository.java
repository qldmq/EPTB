package com.EPTBshop.EPTB.domain.member.repository;

import com.EPTBshop.EPTB.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.memberId = :memberId")
    boolean existsByMemberId(@Param("memberId") String memberId);

    @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.nickname = :nickname")
    boolean existsByNickname(@Param("nickname") String nickname);

    @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.email = :email")
    boolean existByEmail(@Param("email") String email);

    Optional<Member> findByMemberId(String memberId);
}

