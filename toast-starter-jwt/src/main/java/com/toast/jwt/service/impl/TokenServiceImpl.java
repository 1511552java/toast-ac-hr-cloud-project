package com.toast.jwt.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toast.jwt.config.JWTConfigProperties;
import com.toast.jwt.service.ITokenService;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/21
 * @describe
 */
public class TokenServiceImpl implements ITokenService {
    @Value("${spring.application.name}")
    private String applicationName;
    @Autowired
    private JWTConfigProperties jwtConfigProperties;
    @Autowired
    private ObjectMapper objectMapper;

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; // 签名算法

    @Override
    public SecretKey generalKey() {
        byte[] encodeKey = Base64.decodeBase64(Base64.encodeBase64(this.jwtConfigProperties.getSecret().getBytes()));
        SecretKey key = new SecretKeySpec(encodeKey, 0, encodeKey.length, "AES");
        return key;
    }

    @Override
    public String createToken(String id, Map<String, Object> subject) {
        /*
         *  使用JWT数据结构进行开发，目的之一就是不需要进行JWT数据的分布式存储，所以所谓的缓存组件、数据库都用不到
         *  所有的Token都存在有保存时效的问题，所以就需要通过当前时间来进行计算
         */
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + this.jwtConfigProperties.getExpire() * 1000);
        Map<String, Object> cliams = new HashMap<>(); // 设置附加数据
        cliams.put("message", "土司面包");
        cliams.put("site", "toast_bread");
        cliams.put("nice", "Good Good Good");
        Map<String, Object> headers = new HashMap<>();
        headers.put("author", "toast");
        headers.put("module", this.applicationName);
        JwtBuilder builder = null;
        try {
            builder = Jwts.builder()
                    .setClaims(cliams)
                    .setHeader(headers)
                    .setId(id)
                    .setIssuer(this.jwtConfigProperties.getIssuer())
                    .setIssuedAt(nowDate)
                    .setSubject(this.objectMapper.writeValueAsString(subject)) // 所要的附加数据转为JSON
                    .signWith(this.signatureAlgorithm, this.generalKey())
                    .setExpiration(expireDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return builder.compact();
    }

    @Override
    public Jws<Claims> parseToken(String token) {
        if (this.verifyToken(token)) { // token正确的时候才解析
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.generalKey()).parseClaimsJws(token);
            return claims;
        }
        return null;
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.generalKey()).parseClaimsJws(token.trim()).getBody();
            return true; // 没有异常就返回true
        } catch (Exception e) {}
        return false;
    }

    @Override
    public String refreshToken(String token) {
        if (this.verifyToken(token)) {
            Jws<Claims> jws = this.parseToken(token); // 解析TOKEN数据
            try {
                return this.createToken(jws.getBody().getId(), this.objectMapper.readValue(jws.getBody().getSubject(), Map.class));
            } catch (JsonProcessingException e) {}
        }
        return null;
    }
}
