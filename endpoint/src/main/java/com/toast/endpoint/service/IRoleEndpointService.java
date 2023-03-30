package com.toast.endpoint.service;

import com.toast.common.dto.RoleDTO;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
public interface IRoleEndpointService {
    /**
     * @return 获取全部的角色信息
     */
    List<RoleDTO> list();

    /**
     * 查看用户已有的角色信息
     * @param mid 用户ID
     * @return 返回用户已有的角色信息
     */
    List<RoleDTO> listByMember(String mid); //
}
