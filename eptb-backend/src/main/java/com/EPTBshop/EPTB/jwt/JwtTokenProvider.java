package com.EPTBshop.EPTB.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.access-token-validity}")
    private long accessTokenValidity;

    @Value("${jwt.refresh-token-validity}")
    private long refreshTokenValidity;

    // Access Token 생성
    public String createAccessToken(String memberId) {
        Claims claims = Jwts.claims().setSubject(memberId);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessTokenValidity))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // Refresh Token 생성
    public String createRefreshToken(String email) {
        Claims claims = Jwts.claims().setSubject(email);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + refreshTokenValidity))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT에서 이메일 추출
    public String getEmailFromToken(String token) {
        Claims claims = parseClaims(token);  // JWT에서 claims 추출
        return claims.getSubject();  // claims에서 subject(이메일) 가져오기
    }

    // JWT에서 추가적인 정보 추출 (예: nickname)
    public String getNicknameFromToken(String token) {
        Claims claims = parseClaims(token);  // JWT에서 claims 추출
        return claims.get("nickname", String.class);  // claims에서 nickname 가져오기
    }

    // JWT 검증
    public boolean validateToken(String token) {
        try {
            parseClaims(token);  // Claims 추출 시 유효성 검사 자동으로 발생
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // JWT에서 Claims 추출
    private Claims parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
