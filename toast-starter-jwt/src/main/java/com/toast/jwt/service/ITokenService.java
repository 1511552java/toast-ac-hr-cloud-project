package com.toast.jwt.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import javax.crypto.SecretKey;
import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/21
 * @describe    Token生成与解析类
 */
public interface ITokenService {
    /**
     * @return  获取token加密的密钥KEY
     */
    SecretKey generalKey();

    /**
     * 创建token同时并要求存储保存了用户ID和附加信息
     * @param id 用户ID
     * @param subject 附加数据
     * @return
     */
    String createToken(String id, Map<String, Object> subject);

    /**
     * 解析token
     * @param token 要解析的token
     * @return
     */
    Jws<Claims> parseToken(String token);

    /**
     * 校验Token
     * @param token 要校验的token
     * @return true - token内容格式正确无误 | false - token内容格式有误
     */
    boolean verifyToken(String token);

    /**
     * 刷新token
     * @param token 要刷新的token
     * @return 返回新的token
     */
    String refreshToken(String token);
}
