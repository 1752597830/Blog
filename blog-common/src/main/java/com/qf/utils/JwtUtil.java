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