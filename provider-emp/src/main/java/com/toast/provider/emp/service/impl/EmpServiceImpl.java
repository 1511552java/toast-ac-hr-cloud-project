package com.toast.provider.emp.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toast.common.dto.EmpDTO;
import com.toast.common.service.IEmpService;
import com.toast.common.service.abs.AbstractService;
import com.toast.provider.emp.entity.Emp;
import com.toast.provider.emp.mapper.EmpMapper;
import com.toast.util.bean.DeepBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author 土司先生
 * @time 2023/3/27
 * @describe
 */
@Slf4j
@Service
public class EmpServiceImpl extends AbstractService implements IEmpService {
    @Autowired
    private EmpMapper empMapper;
    @Override
    public Map<String, Object> split(long currentPage, long lineSize, String column, String keyword) {
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<Emp> wrapper = new QueryWrapper<>(); // 获取查询包装器
        if (super.isLike(column, keyword)) { // 判断是否需要模糊匹配
            wrapper.like(column, keyword); // 设置模糊查询列
        }
        Integer count = this.empMapper.selectCount(wrapper); // 数据查询
        result.put("count", count); // 保存查询统计结果
        Page<Emp> empPage = new Page<>(currentPage, lineSize, count); // 创建分页器
        IPage<Emp> resultPage = this.empMapper.selectPage(empPage, wrapper); // 数据查询
        List<EmpDTO> allEmps = DeepBeanUtils.copyListProperties( 	// 集合数据拷贝
                resultPage.getRecords(), EmpDTO::new); // 查询全部数据
        result.put("data", allEmps); // 获取查询结果
        result.put("totalPage", resultPage.getTotal());
        return result;
    }

    @Override
    public EmpDTO get(long empno) {
        Emp emp = this.empMapper.selectById(empno);
        if (emp != null) {
            EmpDTO dto = new EmpDTO();
            DeepBeanUtils.copyProperties(emp, dto);
            return dto;
        }
        return null;
    }

    @Override
    public boolean add(EmpDTO empDTO) {
        Emp emp = new Emp();
        DeepBeanUtils.copyProperties(empDTO, emp);
        return this.empMapper.insert(emp) > 0;
    }

    @Override
    public boolean edit(EmpDTO empDTO) {
        Emp emp = new Emp();
        DeepBeanUtils.copyProperties(empDTO, emp);
        return this.empMapper.updateById(emp) > 0;
    }

    @Override
    public boolean remove(long empno) {
        return this.empMapper.deleteById(empno) > 0;
    }

    @Override
    public boolean editClearDeptno(long deptno) {
        return this.empMapper.doEditDeptno(deptno);
    }

    @Override
    public Integer getDeptEmpCount(long deptno) {
        return this.empMapper.selectCount(
                new QueryWrapper<Emp>().eq("deptno", deptno));
    }
}
