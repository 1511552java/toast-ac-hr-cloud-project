package com.toast.provider.access.service.impl;

import com.toast.common.service.IAccessService;
import com.toast.common.service.abs.AbstractService;
import com.toast.jwt.service.ITokenService;
import com.toast.provider.access.entity.Access;
import com.toast.provider.access.mapper.IAccessMapper;
import com.toast.provider.access.mapper.IActionMapper;
import com.toast.provider.access.mapper.IRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe
 */
@Service
public class AccessServiceImpl extends AbstractService implements IAccessService {
    @Autowired
    private IAccessMapper accessMapper;
    @Autowired
    private IRoleMapper roleMapper;
    @Autowired
    private IActionMapper actionMapper;
    @Autowired
    private ITokenService tokenService;// 注入Token操作业务接口实例
    @Override
    public String token(String aid) {
        // 根据系统接入id获取系统信息
        Access access = this.accessMapper.selectById(aid);
        if (access.getToken() != null || "".matches(access.getToken())) { // 没有token数据
            String id = "system-access-" + UUID.randomUUID(); // 随意生成一个JWT-ID数据
            Map<String, Object> result = new HashMap<>();
            result.put("name", access.getName());
            result.put("mid", access.getAid());
            result.put("roles", this.roleMapper.findAllDetailsByAccess(aid));
            result.put("actions", this.actionMapper.findAllByAccess(aid));
            String token = this.tokenService.createToken(id, result); // 生成token
            this.accessMapper.doUpdateToken(Map.of("aid", aid, "token", token)); // 数据更新
            return token;
        }
        return access.getToken();
    }
}
