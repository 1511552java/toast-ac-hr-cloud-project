package com.toast.provider.member.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.toast.common.dto.MemberDTO;
import com.toast.common.service.IMemberService;
import com.toast.jwt.annotation.JWTCheckToken;
import com.toast.provider.member.controller.block.MemberBlockHandler;
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
@RequestMapping("/hr/provider/member/member/*")
public class MemberController {
    @Autowired
    private IMemberService memberService;

    @ApiOperation(value = "用户列表", notes = "查看全部用户的信息显示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前所在页码", required = true, dataType = "long"),
            @ApiImplicitParam(name = "lineSize", value = "每次加载的数据行数", required = true, dataType = "long"),
            @ApiImplicitParam(name = "column", value = "模糊查询列名称", required = true, dataType = "string"),
            @ApiImplicitParam(name = "keyword", value = "模糊查询关键字", required = true, dataType = "string")
    })
    @SentinelResource(value = "list", blockHandlerClass = MemberBlockHandler.class, blockHandler = "listHandler")
    @JWTCheckToken(role = "member", action = "member:list")
    @GetMapping("get")
    public Object list(Long currentPage, Long lineSize, String column, String keyword) { // 查询全部工资等级
        return this.memberService.split(currentPage, lineSize, column, keyword); // 响应数据
    }



    @ApiOperation(value = "获取用户信息", notes = "根据用户ID查询用户完整数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mid", value = "用户ID", required = true, dataType = "string")
    })
    @GetMapping("get")
    public MemberDTO get(String mid) {
        MemberDTO dto = this.memberService.get(mid);
        return dto; // 数据响应
    }
}
