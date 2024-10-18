package com.ureca.idle.idleoriginapi.presentaion.web.jwt;

import com.ureca.idle.idleoriginapi.presentaion.web.auth.IdAndAuthority;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Getter
@Component
@RequiredArgsConstructor
public class JwtProvider {

    private final JwtProperties jwtProperties;
    private Key key;

    @PostConstruct
    private void init() {
        key = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }

    public String create(Long userId, String role) {
        Claims claims = Jwts.claims();
        claims.put("id", userId);
        claims.put("role", role);
        return createToken(claims);
    }

    private String createToken(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(issuedAt())
                .setExpiration(expiredAt())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    private Date issuedAt() {
        LocalDateTime now = LocalDateTime.now();
        return Date.from(now.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Date expiredAt() {
        LocalDateTime now = LocalDateTime.now();
        return Date.from(now.plusHours(jwtProperties.getExpiration()).atZone(ZoneId.systemDefault()).toInstant());
    }

    public IdAndAuthority extract(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret().getBytes())
                    .parseClaimsJws(token)
                    .getBody();
            return new IdAndAuthority(claims.get("id", Long.class), claims.get("role", String.class));
        } catch (MalformedJwtException e) {
            throw new JwtException("토큰의 길이 및 형식이 올바르지 않습니다.");
        } catch (ExpiredJwtException e) {
            throw new JwtException("이미 만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            throw new JwtException("지원되지 않는 토큰입니다.");
        } catch (SignatureException e) {
            throw new JwtException("토큰의 서명이 유효하지 않습니다.");
        }
    }
}
