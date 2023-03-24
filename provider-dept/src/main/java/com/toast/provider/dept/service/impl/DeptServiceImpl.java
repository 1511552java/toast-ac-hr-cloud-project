package com.toast.provider.dept.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.toast.common.dto.DeptDTO;
import com.toast.common.service.IDeptService;
import com.toast.common.service.abs.AbstractService;
import com.toast.provider.dept.entity.Dept;
import com.toast.provider.dept.mapper.DeptMapper;
import com.toast.util.bean.DeepBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Slf4j
@Service
public class DeptServiceImpl extends AbstractService implements IDeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<DeptDTO> list() {
        QueryWrapper<Dept> wrapper = new QueryWrapper<>();    // 查询包装器
        List<DeptDTO> allDepts = DeepBeanUtils.copyListProperties(    // 集合数据拷贝
                this.deptMapper.selectList(wrapper), DeptDTO::new); // 查询全部数据
        return allDepts;
    }

    @Override
    public boolean editMgr(DeptDTO dto) {
        return this.deptMapper.doEditMgr(Map.of("deptno", dto.getDeptno(),
                "empno", dto.getEmpno(), "mname", dto.getMname()));
    }

    @Override
    public boolean add(DeptDTO dto) {
        Dept dept = new Dept(); // 实例化VO对象
        DeepBeanUtils.copyProperties(dto, dept); // 对象数据拷贝
        dept.setCurrent(0); // 新部门的雇员人数为0
        if (this.deptMapper.insert(dept) > 0) { // 部门数据增加成功
            return true; // 增加成功
        }
        return false; // 增加失败
    }

    @Override
    public boolean edit(DeptDTO dto) {
        Dept dept = new Dept(); // 实例化VO对象
        DeepBeanUtils.copyProperties(dto, dept); // 属性拷贝
        return this.deptMapper.doUpdateBase(dept); // 更新成功
    }

    @Override
    public boolean remove(long deptno) {
        return this.deptMapper.deleteById(deptno) > 0; // 删除部门信息
    }

    @Override
    public DeptDTO get(long deptno) {
        Dept dept = this.deptMapper.selectById(deptno); // 根据id查看用户信息
        if (dept != null) {
            DeptDTO dto = new DeptDTO(); // 实例化DTO对象
            DeepBeanUtils.copyProperties(dept, dto); // 属性拷贝
            return dto;
        }
        return null;
    }

    @Override
    public boolean editIncrementCurrent(long deptno) {
        return this.deptMapper.doIncrementCurrent(deptno);
    }

    @Override
    public boolean editDecrementCurrent(long deptno) {
        return this.deptMapper.doDecrementCurrent(deptno);
    }

    @Override
    public DeptDTO getDeptByMgr(long deptno, long empno) {
        QueryWrapper<Dept> deptQuery = new QueryWrapper<>();
        deptQuery.eq("deptno", deptno); // 部门编号判断
        deptQuery.eq("empno", empno); // 领导编号判断
        Dept dept = this.deptMapper.selectOne(deptQuery); // 部门查询
        log.info("查询领导对应的部门。{}", dept);
        if (dept != null) { // 存在查询结果
            DeptDTO dto = new DeptDTO(); // 实例化DTO对象实例
            DeepBeanUtils.copyProperties(dept, dto); // 对象属性拷贝
            return dto;
        }
        return null;
    }

    @Override
    public boolean editDeptMgr(DeptDTO dto) {
        log.info("更新部门领导。{}", dto);
        return this.deptMapper.doEditMgr(Map.of("deptno", dto.getDeptno(),
                "empno", dto.getEmpno(), "mname", dto.getMname()));
    }

    @Override
    public boolean removeDeptMgr(long deptno) {
        return this.deptMapper.doClearMgr(deptno);
    }
}
