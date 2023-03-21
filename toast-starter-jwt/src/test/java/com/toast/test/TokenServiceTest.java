package com.toast.test;

import com.toast.jwt.StartJWTConfiguration;
import com.toast.jwt.service.ITokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 土司先生
 * @time 2023/3/21
 * @describe
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = StartJWTConfiguration.class)
public class TokenServiceTest {
    @Autowired
    private ITokenService tokenService;

    private static String token = "eyJhdXRob3IiOiJ0b2FzdCIsIm1vZHVsZSI6IlRPQVNULUhSLVRPS0VOIiwiYWxnIjoiSFMyNTYifQ.eyJzdWIiOiJ7XCJoZWFkXCI6XCItWG1zMmcgLVhteDJnXCIsXCJ0aHJlYWRfc3RhY2tfc2l6ZVwiOlwiOE1CXCIsXCJzdGFja19sZW5ndGhcIjpcIjEwMjRcIixcIm1ldGhvZF9hcmVhXCI6XCItWFg6TWF4TWV0YXNwYWNlU2l6ZSA9IC0xIOWNs-ayoeaciemZkOWItu-8jOS5n-WwseaYr-ivtO-8jOm7mOiupOaDheWGteS4i--8jOiZmuaLn-acuuS8muiAl-WwveaJgOacieWPr-eUqOeahOezu-e7n-WGheWtmFwifSIsInNpdGUiOiJ0b2FzdF9icmVhZCIsImlzcyI6InRvYXN0X2JyZWFkIiwibWVzc2FnZSI6IuWcn-WPuOmdouWMhSIsImV4cCI6MTY3OTQxNTcxNywiaWF0IjoxNjc5NDA3MDc3LCJuaWNlIjoiR29vZCBHb29kIEdvb2QiLCJqdGkiOiJ0b2FzdGViMTdhNzM3LWM5OGUtNDYzYS05NjNlLWRmZjcwMWI2MmQ2MCJ9.VvvRQVtUYNB4ueOoboHUtGyyI2k3jieR7noj_wnfeCA";

    @Test
    public void testCreateToken() {
        Map<String, Object> subject = new HashMap<>();
        subject.put("head", "-Xms2g -Xmx2g");
        subject.put("stack_length", "1024");
        subject.put("thread_stack_size", "8MB");
        subject.put("method_area", "-XX:MaxMetaspaceSize = -1 即没有限制，也就是说，默认情况下，虚拟机会耗尽所有可用的系统内存");
        String id = "toast" + UUID.randomUUID();
        System.out.println("token: " + this.tokenService.createToken(id, subject));
    }

    @Test
    public void testParesToken() {
        Jws<Claims> jws = this.tokenService.parseToken(token);
        System.out.println("【token头部信息】" + jws.getHeader());
        System.out.println("【toten信息体】" + jws.getBody());
        System.out.println("【token加密算法】" + jws.getSignature());

    }

    @Test
    public void testVerifyToken() {
        System.out.println(this.tokenService.verifyToken(token));
    }

    @Test
    public void testRefreshToken() {
        String newToken = this.tokenService.refreshToken(token);
        boolean result = this.tokenService.verifyToken(newToken);
        System.out.println("【新TOKEN】" + newToken);
        System.out.println("【校验结果】" + result);
        if (result) {
            Jws<Claims> jws = this.tokenService.parseToken(newToken);
            System.out.println("【token头部信息】" + jws.getHeader());
            System.out.println("【toten信息体】" + jws.getBody());
            System.out.println("【token加密算法】" + jws.getSignature());
        }
    }
}