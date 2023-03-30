package com.toast.endpoint.controller;

import com.toast.common.dto.MemberDTO;
import com.toast.common.response.R;
import com.toast.common.response.ResultCode;
import com.toast.endpoint.controller.abs.AbstractEndpointController;
import com.toast.endpoint.service.IMemberEndpointService;
import com.toast.jwt.annotation.JWTCheckToken;
import com.toast.jwt.service.IEncryptService;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
@RestController
@RequestMapping("/hr/endpoint/member/*")
public class MemberEndpointController extends AbstractEndpointController {
    @Autowired
    private IMemberEndpointService memberEndpointService;
    @Autowired
    private IEncryptService encryptService;
    @PostMapping("login") // 只能以POST提交类型为住
    public Object login(@RequestBody MemberDTO dto) {
        dto.setPassword(this.encryptService.getEncryptPassword(dto.getPassword())); // 密码加密
        Map<String, Object> result = this.memberEndpointService.login(dto); // 登录认证处理
        // 业务处理完成后，如果用户认证成功，则会在Map集合之中保存有mid数据项
        if (result.containsKey("mid")) { // 登录成功，进行JWT处理
            return R.success(super.tokenService.createToken("toast-" + UUID.randomUUID(), result)); // 生成JWT的Token数据 将数据响应给客户端
        } else {// 登录失败
            return R.fail(ResultCode.INTERNAL_SERVER_ERROR); // 认证失败返回500错误
        }
    }
    @JWTCheckToken(role = "member", action = "member:list")
    @GetMapping("list")
    public R<Map<String, Object>> list(Long currentPage, Long lineSize, String column, String keyword) {
        return R.success(this.memberEndpointService.split(currentPage, lineSize, column, keyword));
    }

}
