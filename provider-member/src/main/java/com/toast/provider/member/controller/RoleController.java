package com.toast.provider.member.controller;

import com.toast.common.service.IRoleService;
import com.toast.jwt.annotation.JWTCheckToken;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@RestController
@RequestMapping("/hr/provider/member/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "角色列表", notes = "获取系统中的全部角色信息")
    @JWTCheckToken(role = "member", action = "member:list")
    @GetMapping("list")
    public Object list() {
        return this.roleService.list();
    }

    @ApiOperation(value = "获取用户角色", notes = "查询用户对应的所有角色数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mid", value = "用户id", required = true, dataType = "string")
    })
    @GetMapping("list_by_member")
    public Object listByMember(String mid) {
        return this.roleService.listByMember(mid);
    }

    @ApiOperation(value = "获取用户角色ID", notes = "查询用户对应的所有角色编号集合，用于生成前端现实菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mid", value = "用户id", required = true, dataType = "string")
    })
    @GetMapping("list_id_by_member")
    public Object listIDByMember(String mid) {
        return this.roleService.listIdByMember(mid);
    }
}
