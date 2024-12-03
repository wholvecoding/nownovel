package com.wolvescoding.nownovel.core.util;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty("nownovel.jwt.secret")
public class JwtUtils {
    @Value("${nownovel.jwt.secret}")
    private String secret;
    //定义系统密钥
    private static final String HEADER_SYSTEM_KEY = "system_key_Header";

    /**
     *
     * @param uid       用户id
     * @param systemKey 系统标识
     * @return JWT token
     */
    public String generateToken(Long uid, String systemKey){
        return Jwts.builder()
                .setHeaderParam(HEADER_SYSTEM_KEY, systemKey)
                .setSubject(uid.toString())
                .signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
    public Long paraseToken(String token, String systemKey){
        Jws<Claims> claimsJws;
        try{
            claimsJws = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token);
            if(Objects.equals(systemKey, claimsJws.getHeader().get(HEADER_SYSTEM_KEY))){
                return Long.parseLong(claimsJws.getBody().getSubject());
            }
        }catch(JwtException e){
            log.warn("Jwt parse error", token);
        }
        return null;
    }
}
