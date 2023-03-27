package com.toast.provider.emp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.toast.provider.emp.entity.Emp;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 土司先生
 * @time 2023/3/27
 * @describe
 */
@Mapper
public interface EmpMapper extends BaseMapper<Emp> {
    /**
     * 部门信息删除时，删除对应雇员的部门编号
     * @param deptno 要部门编号
     * @return 更新状态
     */
    boolean doEditDeptno(Long deptno);
}
