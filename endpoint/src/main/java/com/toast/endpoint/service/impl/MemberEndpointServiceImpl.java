package com.toast.endpoint.service.impl;

import com.toast.common.dto.MemberDTO;
import com.toast.common.service.IActionService;
import com.toast.common.service.IMemberService;
import com.toast.common.service.IRoleService;
import com.toast.endpoint.service.IMemberEndpointService;
import com.toast.endpoint.service.abs.AbstractEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
@Service
public class MemberEndpointServiceImpl extends AbstractEndpointService implements IMemberEndpointService {
    @Autowired
    private IMemberService memberService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IActionService actionService;

    @Override
    public Map<String, Object> login(MemberDTO dto) {
        Map<String, Object> result = new HashMap<>(); // 保存登录结果
        MemberDTO member = this.memberService.get(dto.getMid()); // 根据ID查询
        if (member != null && member.getPassword().equals(dto.getPassword())) { // 密码验证
            result.put("mid", member.getMid()); // 保存用户id
            result.put("name", member.getName()); // 保存真实姓名
            result.put("flag", member.getFlag()); // 保存管理员标记
            result.put("roles", this.roleService.listIdByMember(member.getMid())); // 保存用户授权信息
            result.put("actions", this.actionService.listIdByMember(member.getMid())); // 保存用户权限信息
        }
        return result;
    }

    @Override
    public Map<String, Object> split(long currentPage, long lineSize, String column, String keyword) {
        return this.memberService.split(currentPage, lineSize, column, keyword);
    }
}
