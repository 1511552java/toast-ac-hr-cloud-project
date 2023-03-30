package com.toast.endpoint.service.impl;

import ch.qos.logback.classic.db.names.TableName;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.toast.common.dto.DeptDTO;
import com.toast.common.dto.EmpDTO;
import com.toast.common.dto.RatingDTO;
import com.toast.common.dto.RecordDTO;
import com.toast.common.service.IDeptService;
import com.toast.common.service.IEmpService;
import com.toast.common.service.IRatingService;
import com.toast.common.type.EmpResponseType;
import com.toast.endpoint.service.IEmpEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/29
 * @describe
 */
@Service
public class EmpEndpointServiceImpl implements IEmpEndpointService {
    @Autowired
    private IEmpService empService;
    @Autowired
    private IDeptService deptService;
    @Autowired
    private IRatingService ratingService;
    @Override
    public Map<String, Object> split(long currentPage, long lineSize, String column, String keyword) {
        return this.empService.split(currentPage, lineSize, column, keyword);
    }

    @Override
    public Map<String, Object> preAdd() {
        return Map.of("depts", this.deptService.list(), "ratings", this.ratingService.list());
    }

    @Override
    public EmpResponseType add(EmpDTO dto) {
        EmpDTO empDTO = this.empService.get(dto.getEmpno());
        if (empDTO != null) { // 雇员编号存在
            return EmpResponseType.EMPNO_EXISTS;
        }

        DeptDTO deptDTO = this.deptService.get(dto.getDeptno()); // 获取部门编号
        if (deptDTO == null) { // 部门不存在
            return EmpResponseType.FAILURE;
        }

        if (deptDTO.getCurrent() >= deptDTO.getBound()) { // 人数已经满员
            return EmpResponseType.DEPT_FULL;
        }


        RatingDTO ratingDTO = this.ratingService.get(dto.getRtid()); // 获取职位信息
        if (dto.getSal() < ratingDTO.getLow() || dto.getSal() > ratingDTO.getHigh()) {    // 工资范围错误
            return EmpResponseType.RATING_ERROR; // 返回响应结果
        }

        dto.setHiredate(new Date());
        if (this.empService.add(dto)) {    // 数据增加成功
            this.deptService.editIncrementCurrent(dto.getDeptno()); //更新部门中的已有雇员数量
        }
        return EmpResponseType.SUCCESS;
    }

    @Override
    public Map<String, Object> preEdit(long empno) {
        return null;
    }

    @Override
    public EmpResponseType edit(EmpDTO dto) {
        // 1、查询原始雇员数据，主要是确认本次更新是否引起了部门信息的更新
        EmpDTO originEmpDTO = this.empService.get(dto.getEmpno());
        // 2、判断当前的雇员是否为部门领导，如果是则不允许轻易更新
        DeptDTO mgrDept = this.deptService.getDeptByMgr(originEmpDTO.getDeptno(), originEmpDTO.getEmpno());
        if (mgrDept != null) {  // 雇员是领导
            return EmpResponseType.DEPT_MGR_ERROR;
        }
        if (!originEmpDTO.getDeptno().equals(dto.getDeptno())) { // 部门发生改变
            // 3、判断新部门是否有空余位置
            DeptDTO deptDTO = this.deptService.get(dto.getDeptno()); // 获取部门数据
            if (deptDTO.getCurrent() >= deptDTO.getBound()) { // 人数已经满员
                return EmpResponseType.DEPT_FULL;
            }
        }
        // 4、判断工资范围
        RatingDTO ratingDTO = this.ratingService.get(dto.getRtid()); // 获取职位信息
        if (dto.getSal() < ratingDTO.getLow() || dto.getSal() > ratingDTO.getHigh()) {    // 工资范围错误
            return EmpResponseType.RATING_ERROR;
        }
        // 5、执行数据更新操作
        this.empService.edit(dto);
        // 6、部门修改判断
        if (!originEmpDTO.getDeptno().equals(dto.getDeptno())) { // 部门编号发生改变
            this.deptService.editDecrementCurrent(originEmpDTO.getDeptno()); // 减少原始部门的人数
            this.deptService.editIncrementCurrent(dto.getDeptno()); // 增加新部门的人数
        }
        // 7、更新部门领导姓名
        if (mgrDept != null && !originEmpDTO.getEname().equals(dto.getEname())) {
            DeptDTO mgrDTO = new DeptDTO();
            mgrDTO.setDeptno(dto.getDeptno());
            mgrDTO.setEmpno(dto.getEmpno());
            mgrDTO.setMname(dto.getEname());;
            this.deptService.editDeptMgr(mgrDTO); // 更新部门数据
        }
        return EmpResponseType.SUCCESS;
    }

    @Override
    public EmpResponseType remove(long empno) {
        // 1、如果要想进行部门数据的更新，则首先应该查询出要删除雇员的原始信息
        EmpDTO emp = this.empService.get(empno);
        if (emp == null) { // 没有对应的雇员信息
            return EmpResponseType.FAILURE;
        }
        // 2、查询部门数据
        DeptDTO dept = this.deptService.get(emp.getDeptno());
        if (dept != null && dept.getEmpno().equals(empno)) { // 雇员为部门领导
            // 3、更新部门中的领导信息，此时如果要删除的雇员是部门领导，则会清除部门表中指定行的部分字段信息，如果不是则不会清除
            this.deptService.removeDeptMgr(dept.getDeptno()); // 清除领导信息
        }
        // 4、由于删除了雇员信息，需要减少对应部门中的当前人数
        this.deptService.editDecrementCurrent(emp.getDeptno());
        // 5、删除当前雇员信息
        this.empService.remove(empno);

        return EmpResponseType.SUCCESS;
    }
}
