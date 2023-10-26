package com.qf.utils;

import com.alibaba.fastjson2.JSON;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import com.auth0.jwt.JWT;

import java.util.*;

/**
 * @author : sin
 * @date : 2023/10/25 0:36
 * @Description :
 */

@Slf4j
public class JwtUtil {

    private static String SECRET = "123456";

    /**
     *  生成JWT token
     */
    public static String token(Authentication authentication){
        return JWT.create()
                .withExpiresAt(new Date(System.currentTimeMillis()+ 1000L * 60 * 60 * 24 * 30))  //设置过期时间:单位毫秒
                .withAudience(JSON.toJSONString(authentication)) //设置接受方信息，一般时登录用户
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static void tokenVerify(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
        jwtVerifier.verify(token);//没报错说明验证成功
        System.out.println("===");

        JWT.decode(token).getExpiresAt();
        String json = JWT.decode(token).getAudience().get(0);

        JwtAuthentication jwtAuthentication = JSON.parseObject(json, JwtAuthentication.class);

        SecurityContextHolder.getContext().setAuthentication(jwtAuthentication);
    }

    public static void main(String[] args) {
        tokenVerify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ7XCJhdXRoZW50aWNhdGVkXCI6dHJ1ZSxcImF1dGhvcml0aWVzXCI6W3tcImF1dGhvcml0eVwiOlwiYWRtaW5cIn1dLFwiZGV0YWlsc1wiOntcInJlbW90ZUFkZHJlc3NcIjpcIjA6MDowOjA6MDowOjA6MVwifSxcIm5hbWVcIjpcImFkbWluXCIsXCJwcmluY2lwYWxcIjp7XCJhY2NvdW50Tm9uRXhwaXJlZFwiOnRydWUsXCJhY2NvdW50Tm9uTG9ja2VkXCI6dHJ1ZSxcImF1dGhvcml0aWVzXCI6W3tcImF1dGhvcml0eVwiOlwiYWRtaW5cIn1dLFwiY3JlYXRlQnlcIjpcInFmXCIsXCJjcmVhdGVUaW1lXCI6XCIyMDIzLTEwLTI2IDE3OjA0OjMyXCIsXCJjcmVkZW50aWFsc05vbkV4cGlyZWRcIjp0cnVlLFwiZGVsU3RhdHVzXCI6dHJ1ZSxcImVuYWJsZVwiOnRydWUsXCJlbmFibGVkXCI6dHJ1ZSxcImlkXCI6MSxcIm1vYmlsZVwiOlwiMTIzNDU2Nzg5MDFcIixcIm5pY2tuYW1lXCI6XCLmuIXpo45cIixcInBhc3N3b3JkXCI6XCIkMmEkMTAkemcySENkVHpXUDNubEZTQUpRaWJLLnk4Nmh0RDJtTU8yMnptZy9PR29YUWo5MlRlNDUzdTJcIixcInJvbGVzXCI6W3tcImNyZWF0ZVRpbWVcIjpcIjIwMjMtMTAtMjYgMTc6MDQ6MzJcIixcImRlbFN0YXR1c1wiOnRydWUsXCJpZFwiOjEsXCJuYW1lXCI6XCLnrqHnkIblkZhcIixcInRhZ1wiOlwiYWRtaW5cIixcInVwZGF0ZVRpbWVcIjpcIjIwMjMtMTAtMjYgMTc6MDQ6MzVcIn1dLFwic2V4XCI6XCIxXCIsXCJ1cGRhdGVCeVwiOlwicWZcIixcInVwZGF0ZVRpbWVcIjpcIjIwMjMtMTAtMjYgMTc6MDQ6MzVcIixcInVzZXJuYW1lXCI6XCJhZG1pblwifX0iLCJleHAiOjE3MDA5MjIzOTh9.2vzvrWqZKHfd9Shuy8euBvfNEjxbTr3bMwSK2n0_peI");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encode = passwordEncoder.encode("123456");
        val matches = passwordEncoder.matches("admin123", "{bcrypt}$2a$10$zpJ/X5qNYcM9/wbmf2nA4uDRecORWSsYiqHpn0VjxJ8bYCULAQMvC");
        System.out.println(matches);
        System.out.println(encode.length());
    }

    /**
     * 检查token是否过期
     *
     * @param  token token
     * @return boolean
     */
    //public boolean isExpiration(String token) {
    //    Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    //    return claims.getExpiration().before(new Date());
    //}
    //public boolean validateJwtToken(String authToken) {
    //    try {
    //        Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
    //        return true;
    //    } catch (SignatureException e) {
    //        log.error("Invalid JWT signature: {}", e.getMessage());
    //    } catch (MalformedJwtException e) {
    //        log.error("Invalid JWT token: {}", e.getMessage());
    //    } catch (ExpiredJwtException e) {
    //        log.error("JWT token is expired: {}", e.getMessage());
    //    } catch (UnsupportedJwtException e) {
    //        log.error("JWT token is unsupported: {}", e.getMessage());
    //    } catch (IllegalArgumentException e) {
    //        log.error("JWT claims string is empty: {}", e.getMessage());
    //    }
    //    return false;
    //}
}