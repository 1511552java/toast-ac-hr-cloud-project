package com.toast.endpoint.service;

import com.toast.common.dto.EmpDTO;
import com.toast.common.type.EmpResponseType;

import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/29
 * @describe
 */
public interface IEmpEndpointService {

    /**
     * 雇员分页查询
     * @param currentPage 当前页
     * @param lineSize 当前显示行数
     * @param column 列名称
     * @param keyword 关键字
     * @return
     */
    Map<String, Object> split(long currentPage,
                              long lineSize,
                              String column,
                              String keyword);

    /**
     * 雇员信息增加前的数据查询
     * @return 需要返回部门与员工等级信息，包含数据如下：
     * key = depts: 所有的部门数据
     * key = ratings: 所有的等级数据
     */
    Map<String, Object> preAdd();

    /**
     * 新增雇员信息
     * @return  true - 新增成功| 新增失败
     */
    EmpResponseType add(EmpDTO dto);

    /**
     * 雇员更新前数据查询操作
     * @param empno 要修改的雇员编号
     * @return 等级数据，部门数据以及指定ID的雇员数据
     * key = emp: 要更新的雇员信息
     * key = depts: 所有部门信息
     * key = ratings: 所有等级数据
     */
    Map<String, Object> preEdit(long empno);

    /**
     * 更新雇员信息
     * @param dto 将要更新的雇员信息
     * @return true - 更新成功 | 更新失败
     */
    EmpResponseType edit(EmpDTO dto);

    /**
     * 根据empno雇员编号删除雇员信息
     * @param empno 雇员编号
     * @return true - 删除成功 | false - 删除失败
     */
    EmpResponseType remove(long empno);
}
