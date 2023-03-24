package com.toast.provider.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.toast.common.dto.RoleDTO;
import com.toast.common.service.IRoleService;
import com.toast.common.service.abs.AbstractService;
import com.toast.provider.member.entity.Role;
import com.toast.provider.member.mapper.RoleMapper;
import com.toast.util.bean.DeepBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Service
public class RoleServiceImpl extends AbstractService implements IRoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Override
    public List<RoleDTO> list() {
        QueryWrapper<Role> wrapper = new QueryWrapper<>();	// 查询包装器
        List<RoleDTO> allRoles = DeepBeanUtils.copyListProperties( 	// 集合数据拷贝
                this.roleMapper.selectList(wrapper), RoleDTO::new); // 查询全部数据
        return allRoles;
    }

    @Override
    public List<RoleDTO> listByMember(String mid) {
        List<RoleDTO> allRoles = DeepBeanUtils.copyListProperties( 	// 集合数据拷贝
                this.roleMapper.findAllDetailsByMember(mid), RoleDTO::new); // 查询全部数据
        return allRoles;
    }

    @Override
    public Set<String> listIdByMember(String mid) {
        return this.roleMapper.findAllByMember(mid); // 查询角色集合
    }
}
