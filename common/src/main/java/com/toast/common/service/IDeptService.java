package com.toast.common.service;

import com.toast.common.dto.DeptDTO;
import com.toast.common.service.config.FeignConfig;
import com.toast.common.service.fallback.DeptServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@FeignClient(value = "hr.gateway", // 使用网关的名称进行访问
        configuration = FeignConfig.class, // 定义要访问的微服务实例名称
        fallbackFactory = DeptServiceFallbackFactory.class)
public interface IDeptService {
    @GetMapping("/hr/provider/dept/list")
    public List<DeptDTO> list(); // 查询全部部门数据

    /**
     * 更新部门对应的领导信息：
     * 1、查询部门当前领导是否为要操作的雇员信息；
     * 2、更新部门对应的领导信息
     * @param dto 部门领导信息
     * @return 返回更新状态
     */
    @PostMapping
    public boolean editMgr(DeptDTO dto);

    /**
     * 增加部门信息
     * @param dto 部门数据
     * @return 返回更新状态
     */
    @PostMapping("/hr/provider/dept/add")
    public boolean add(DeptDTO dto);

    /**
     * 更新部门数据
     * @param dto 修改后的部门数据
     * @return 返回更新状态
     */
    @PostMapping("/hr/provider/dept/edit")
    public boolean edit(DeptDTO dto);

    /**
     * 删除部门信息
     * @param deptno 部门id
     * @return 返回更新状态
     */
    @DeleteMapping("/hr/provider/dept/remove")
    public boolean remove(@RequestParam("deptno") long deptno);

    /**
     * 根据部门ID查看部门信息
     * @param deptno 部门编号
     * @return 部门完整数据
     */
    @GetMapping("/hr/provider/dept/get")
    public DeptDTO get(@RequestParam("deptno") long deptno);

    /**
     * 增长当前人数
     * @param deptno 要增长的部门ID
     * @return 数据更新结果
     */
    @PostMapping("/hr/provider/dept/edit_increment_current")
    public boolean editIncrementCurrent(@RequestParam("deptno") long deptno);
    /**
     * 减少当前人数
     * @param deptno 要减少的部门ID
     * @return 数据更新结果
     */
    @PostMapping("/hr/provider/dept/edit_decrement_current")
    public boolean editDecrementCurrent(@RequestParam("deptno") long deptno);

    /**
     * 根据雇员编号和部门编号，查询指定雇员担任领导的部门信息
     * @param deptno 部门编号
     * @param empno 雇员编号
     * @return 查询到部门返回DeptDTO实例，查询不到返回null
     */
    @GetMapping("/hr/provider/dept/get_dept_mgr")
    public DeptDTO getDeptByMgr(@RequestParam("deptno") long deptno, @RequestParam("empno") long empno);

    /**
     * 更新部门领导信息，更新领导信息时同时更新部门领导姓名
     * @param dto 更新领导的部门编号
     * @return 更新成功返回true，否则返回false
     */
    @PostMapping("/hr/provider/dept/edit_dept_mgr")
    public boolean editDeptMgr(DeptDTO dto);

    /**
     * 清除部门领导信息
     * @param deptno 部门编号
     * @return 删除操作的结果
     */
    @PostMapping("/hr/provider/dept/clear_dept_mgr")
    public boolean removeDeptMgr(@RequestParam("deptno") long deptno);
}

