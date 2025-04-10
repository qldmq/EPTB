package com.EPTBshop.EPTB.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRedisRepository {

    private final RedisTemplate<String, Object> redisTemplate;

    public void save(String memberNum, String refreshToken) {
        redisTemplate.opsForValue().set(memberNum, refreshToken, 7, TimeUnit.DAYS);
    }

    public String findByMemberNum(String memberNum) {
        return (String) redisTemplate.opsForValue().get(memberNum);
    }

    public void delete(String memberNum) {
        redisTemplate.delete(memberNum);
    }
}
