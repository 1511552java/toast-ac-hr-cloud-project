package com.toast.endpoint.controller;

import com.toast.common.response.R;
import com.toast.endpoint.controller.abs.AbstractEndpointController;
import com.toast.endpoint.service.IRoleEndpointService;
import com.toast.jwt.annotation.JWTCheckToken;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
public class RoleEndpoint extends AbstractEndpointController {
    @Autowired
    private IRoleEndpointService roleEndpointService;

    @JWTCheckToken(role = "member")
    @GetMapping("list")
    public Object list() {
        return R.success(this.roleEndpointService.list());
    }
    @JWTCheckToken(role = "member")
    @GetMapping("list_member_role")
    public Object listByMember(String mid) {
        return R.success(this.roleEndpointService.listByMember(mid));
    }
}
