package com.EPTBshop.EPTB.repository;

import com.EPTBshop.EPTB.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

    @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.memberId = :memberId")
    boolean existsByMemberId(@Param("memberId") String memberId);

    @Query("SELECT COUNT(m) > 0 FROM Member m WHERE m.nickname = :nickname")
    boolean existsByNickname(@Param("nickname") String nickname);
}
