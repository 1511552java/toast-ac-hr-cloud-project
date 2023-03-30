package com.toast.endpoint.service;

import com.toast.common.dto.DeptDTO;
import com.toast.common.type.DeptResponseType;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
public interface IDeptEndpointService {
    public List<DeptDTO> list(); // 查询全部部门数据

    /**
     * 更新部门对应的领导信息：
     * 1、查询部门当前领导是否为要操作的雇员信息；
     * 2、更新部门对应的领导信息
     * @param dto 部门领导信息
     * @return 返回更新状态
     */
    DeptResponseType editMgr(DeptDTO dto);

    /**
     * 增加部门信息
     * @param dto 部门数据
     * @return 返回更新状态
     */
    DeptResponseType add(DeptDTO dto);

    /**
     * 更新部门数据
     * @param dto 修改后的部门数据
     * @return 返回更新状态
     */
    DeptResponseType edit(DeptDTO dto);

    /**
     * 删除部门信息
     * @param deptno 部门id
     * @return 返回更新状态
     */
    DeptResponseType remove(long deptno);

    /**
     * 根据部门ID查看部门信息
     * @param deptno 部门编号
     * @return 部门完整数据
     */
    DeptDTO get(long deptno);
}
