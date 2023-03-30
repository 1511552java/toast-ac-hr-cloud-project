package com.toast.endpoint.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.toast.common.dto.DeptDTO;
import com.toast.common.dto.EmpDTO;
import com.toast.common.dto.RecordDTO;
import com.toast.common.service.IDeptService;
import com.toast.common.service.IEmpService;
import com.toast.common.type.DeptResponseType;
import com.toast.common.type.RecordOperateType;
import com.toast.common.type.TableName;
import com.toast.endpoint.service.IDeptEndpointService;
import com.toast.endpoint.service.IRecordMessageService;
import com.toast.endpoint.service.abs.AbstractEndpointService;
import com.toast.util.MemberStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
@Slf4j
@Service
public class DeptEndpointServiceImpl extends AbstractEndpointService implements IDeptEndpointService {
    @Autowired
    private IEmpService empService;
    @Autowired
    private IDeptService deptService;
    @Autowired
    private IRecordMessageService recordMessageService;
    @Override
    public List<DeptDTO> list() {
        return this.deptService.list();
    }

    @Override
    public DeptResponseType editMgr(DeptDTO dto) {
        // 1、查询部门信息，便于判断当前设置的领导信息是否重复
        DeptDTO dept = this.deptService.get(dto.getDeptno());// 查询部门信息
        // 2、判断当前部门领导
        if (dept.getEmpno() == null || !dept.getEmpno().equals(dto.getEmpno())) { // 当前未变更领导信息
            // 3、判断当前的雇员是否为本部门的员工，如果不是则不允许更新
            EmpDTO emp = this.empService.get(dto.getEmpno()); // 查询雇员信息
            if (emp == null) { // 雇员不存在
                return DeptResponseType.UNKNOW_EMP;
            }
            if (!emp.getDeptno().equals(dto.getDeptno())) { // 雇员所在部门不匹配
                return DeptResponseType.DEPT_INVALIDATE; // 返回错误信息
            }
            // 4、判断员工级别
            if (emp.getRtid().compareTo("YOOTK-5") < 0) { // 错误的级别
                return DeptResponseType.RATING_INVALIDATE; // 返回错误信息
            }
            // 5、更新部门领导信息
            dto.setMname(emp.getEname()); // 绑定领导姓名
            if (this.deptService.editDeptMgr(dto)) { // 更新领导信息
                RecordDTO record = new RecordDTO(); // 实例化记录操作对象实例
                record.setMid(MemberStore.getMid()); // 用户ID
                record.setName(MemberStore.getName()); // 用户姓名
                record.setOperate(RecordOperateType.EDIT); // 设置操作类型
                record.setTab(TableName.DEPT); // 设置操作表名称
                record.setUdate(new Date()); // 记录存储日期
                try {
                    record.setData(this.objectMapper.writeValueAsString(dept)); // 操作数据转为json保存
                } catch (JsonProcessingException e) {}
                this.recordMessageService.sendRecord(record); // 消息发送
                return DeptResponseType.SUCCESS;
            }
        } else { // 当前需要变更领导信息
            return DeptResponseType.FAILURE;
        }
        return DeptResponseType.FAILURE;
    }

    @Override
    public DeptResponseType add(DeptDTO dto) {
        dto.setCurrent(0); // 新部门的雇员人数为0
        if (this.deptService.add(dto)) { // 部门数据增加成功
            RecordDTO record = new RecordDTO(); // 实例化记录操作对象实例
            record.setMid(MemberStore.getMid()); // 用户ID
            record.setName(MemberStore.getName()); // 用户姓名
            record.setOperate(RecordOperateType.ADD); // 设置操作类型
            record.setTab(TableName.DEPT); // 设置操作表名称
            record.setUdate(new Date()); // 记录存储日期
            try {
                record.setData(this.objectMapper.writeValueAsString(dto)); // 操作数据转为json保存
            } catch (JsonProcessingException e) {}
            this.recordMessageService.sendRecord(record);
            return DeptResponseType.SUCCESS; // 增加成功
        }
        return DeptResponseType.FAILURE; // 增加失败
    }

    @Override
    public DeptResponseType edit(DeptDTO dto) {
        // 1、查询雇员人数
        Integer count = this.empService.getDeptEmpCount(dto.getDeptno());
        log.info("查询更新部门已有的雇员人数，数量为：{}", count);
        // 2、人数信息判断
        if (dto.getBound() < count) { // 人数错误
            return DeptResponseType.DEPT_MORE_NUMBER; // 返回错误状态
        }
        // 3、如果人数满足要求，则直接更新部门信息
        if (this.deptService.edit(dto)) {
            RecordDTO record = new RecordDTO(); // 实例化记录操作对象实例
            record.setMid(MemberStore.getMid()); // 用户ID
            record.setName(MemberStore.getName()); // 用户姓名
            record.setOperate(RecordOperateType.EDIT); // 设置操作类型
            record.setTab(TableName.DEPT); // 设置操作表名称
            record.setUdate(new Date()); // 记录存储日期
            try {
                record.setData(this.objectMapper.writeValueAsString(dto)); // 操作数据转为json保存
            } catch (JsonProcessingException e) {}
            this.recordMessageService.sendRecord(record);
            return DeptResponseType.SUCCESS; // 更新成功
        } else {
            return DeptResponseType.FAILURE; // 更新失败
        }
    }

    @Override
    public DeptResponseType remove(long deptno) {
        // 1、查询部门数据，为便于进行操作记录
        DeptDTO dto = this.deptService.get(deptno); // 获取部门数据
        log.info("部门删除前的查询。{}", dto);
        if (dto == null) {
            return DeptResponseType.SUCCESS;
        }
        // 2、删除该部门对应的雇员信息
        if (dto.getCurrent() > 0) { // 该部门有数据
            this.empService.editClearDeptno(deptno); // 清空部门雇员对应信息
        }
        // 3、删除部门数据
        this.deptService.remove(deptno); // 删除部门数据
        RecordDTO record = new RecordDTO(); // 实例化记录操作对象实例
        record.setMid(MemberStore.getMid()); // 用户ID
        record.setName(MemberStore.getName()); // 用户姓名
        record.setOperate(RecordOperateType.REMOVE); // 设置操作类型
        record.setTab(TableName.DEPT); // 设置操作表名称
        record.setUdate(new Date()); // 记录存储日期
        try {
            record.setData(this.objectMapper.writeValueAsString(dto)); // 操作数据转为json保存
        } catch (JsonProcessingException e) {}
        this.recordMessageService.sendRecord(record); // 保存操作记录
        return DeptResponseType.SUCCESS;
    }

    @Override
    public DeptDTO get(long deptno) {
        return this.deptService.get(deptno);
    }
}
