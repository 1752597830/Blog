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

    public static void main(String[] args) {
        tokenVerify("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJ7XCJhdXRoZW50aWNhdGVkXCI6dHJ1ZSxcImF1dGhvcml0aWVzXCI6W3tcImF1dGhvcml0eVwiOlwiYWRtaW5cIn1dLFwiZGV0YWlsc1wiOntcInJlbW90ZUFkZHJlc3NcIjpcIjA6MDowOjA6MDowOjA6MVwiLFwic2Vzc2lvbklkXCI6XCJBMEE4RUQ3OUE1MTEzNjFCOTJGMUM4RkM2OUU4QTY5MVwifSxcIm5hbWVcIjpcImFkbWluMTIzXCIsXCJwcmluY2lwYWxcIjp7XCJhY2NvdW50Tm9uRXhwaXJlZFwiOnRydWUsXCJhY2NvdW50Tm9uTG9ja2VkXCI6dHJ1ZSxcImF1dGhvcml0aWVzXCI6W3tcImF1dGhvcml0eVwiOlwiYWRtaW5cIn1dLFwiY3JlYXRlVGltZVwiOlwiMjAyMy0wNi0yOSAxNDowMTo0NFwiLFwiY3JlZGVudGlhbHNOb25FeHBpcmVkXCI6dHJ1ZSxcImRlbGV0ZVN0YXR1c1wiOjEsXCJkZXB0SWRcIjoxLFwiZW1haWxcIjpcIjQ0MDc1MDlAcXEuY29tXCIsXCJlbmFibGVkXCI6dHJ1ZSxcImlkXCI6MSxcIm1vYmlsZVwiOlwiMTczNjg1Nzg4ODhcIixcIm5pY2tuYW1lXCI6XCJhZG1pbjEyM1wiLFwicGFzc3dvcmRcIjpcIntiY3J5cHR9JDJhJDEwJHpwSi9YNXFOWWNNOS93Ym1mMm5BNHVEUmVjT1JXU3NZaXFIcG4wVmp4SjhiWUNVTEFRTXZDXCIsXCJwZXJtc1wiOlt7XCJjcmVhdGVUaW1lXCI6XCIyMDIzLTA3LTAxIDIzOjE1OjU2XCIsXCJkZWxldGVTdGF0dXNcIjoxLFwiaWRcIjoxLFwibWVudUlkXCI6MixcIm5hbWVcIjpcIuaJgOacieadg-mZkFwiLFwidGFnXCI6XCJwZXJtX2FsbFwiLFwidXBkYXRlVGltZVwiOlwiMjAyMy0wOC0xMSAwNTo1MzoxMlwifV0sXCJyb2xlc1wiOlt7XCJjcmVhdGVUaW1lXCI6XCIyMDIzLTA2LTI5IDE0OjQ5OjQ5XCIsXCJkZWxldGVTdGF0dXNcIjoxLFwiaWRcIjoxLFwibmFtZVwiOlwi566h55CG5ZGYXCIsXCJ0YWdcIjpcImFkbWluXCIsXCJ1cGRhdGVUaW1lXCI6XCIyMDIzLTA3LTAzIDEzOjQwOjMzXCJ9XSxcInNleFwiOjEsXCJ1cGRhdGVUaW1lXCI6XCIyMDIzLTA4LTExIDA1OjUyOjM4XCIsXCJ1c2VybmFtZVwiOlwiYWRtaW4xMjNcIn19IiwiZXhwIjoxNzAwODc2MzUwfQ.V2WVGCAJJKjS45I2TL-V-VX8puqtvKg-IdnZp4oqk6s");
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