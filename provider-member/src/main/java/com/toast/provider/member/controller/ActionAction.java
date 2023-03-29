package com.toast.provider.member.controller;

import com.toast.common.service.IActionService;
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
@RequestMapping("/hr/provider/member/action/*")
public class ActionAction {
    @Autowired
    private IActionService actionService; // 用户服务接口实例

    @ApiOperation(value = "查看角色对应的权限集合", notes = "根据指定的系统角色查询对应的所有权限数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rid", value = "角色ID", required = true, dataType = "string")
    })
    @GetMapping("list_by_role")
    public Object listByRole(String rid) { // 查询全部工资等级
        return this.actionService.listByRole(rid); // 响应数据
    }

    @ApiOperation(value = "查看用户对应的角色编号集合", notes = "根据指定的用户ID查询该用户对应的角色ID，以便于前端菜单的生成")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mid", value = "角色ID", required = true, dataType = "string")
    })
    @GetMapping("list_by_member")
    public Object listByMember(String mid) { // 查询全部工资等级
        return this.actionService.listIdByMember(mid); // 响应数据
    }
}

