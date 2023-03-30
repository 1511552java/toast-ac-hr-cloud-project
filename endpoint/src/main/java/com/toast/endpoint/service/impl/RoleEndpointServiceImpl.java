package com.toast.endpoint.service.impl;

import com.toast.common.dto.RoleDTO;
import com.toast.common.service.IRoleService;
import com.toast.endpoint.service.IRoleEndpointService;
import com.toast.endpoint.service.abs.AbstractEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
@Service
public class RoleEndpointServiceImpl extends AbstractEndpointService implements IRoleEndpointService {
    @Autowired
    private IRoleService roleService;
    @Override
    public List<RoleDTO> list() {
        return this.roleService.list();
    }

    @Override
    public List<RoleDTO> listByMember(String mid) {
        return this.roleService.listByMember(mid);
    }
}
