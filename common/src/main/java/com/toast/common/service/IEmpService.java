package com.toast.common.service;

import com.toast.common.dto.EmpDTO;
import com.toast.common.service.config.FeignConfig;
import com.toast.common.service.fallback.EmpServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@FeignClient(
        value = "hr.gateway", // 使用网关的名称进行访问
        configuration = FeignConfig.class, // 定义要访问的微服务实例名称
        fallbackFactory = EmpServiceFallbackFactory.class) // Fallback处理
public interface IEmpService {
    /**
     * 雇员信息列表显示
     * @param currentPage 当前所在页
     * @param lineSize  每页显示数据行数
     * @param column 模糊查询列
     * @param keyword 查询关键字
     * @return 返回结果包含有雇员集合和行数统计，使用Map存储，包含的数据信息如下：
     * key = count: 数据行数
     * key = data : 雇员数据集合
     */
    @GetMapping("/hr/provider/emp/list")
    Map<String, Object> split(
            @RequestParam("currentPage") long currentPage,
            @RequestParam("lineSize") long lineSize,
            @RequestParam("column") String column,
            @RequestParam("keyword") String keyword);

    /**
     * 根据雇员编号获取详情信息
     * @param empno 雇员编号
     * @return 返回雇员信息
     */
    @GetMapping("/hr/provider/emp/get")
    EmpDTO get(@RequestParam("empno") long empno);

    /**
     * 增加新雇员
     * @param empDTO 新增雇员信息
     * @return true - 新增成功 | false - 新增失败
     */
    @PostMapping("/hr/provider/emp/add")
    boolean add(EmpDTO empDTO);

    /**
     * 编辑雇员信息
     * @param empDTO 待编辑的雇员信息
     * @return true - 编辑成功 | false - 编辑失败
     */
    @PutMapping("/hr/provider/emp/edit")
    boolean edit(EmpDTO empDTO);

    /**
     * 根据雇员编号删除雇员信息
     * @param empno 待删除的雇员信息
     * @return true - 删除成功 | false - 删除失败
     */
    boolean remove(@RequestParam("empno") long empno);

    /**
     * 根据指定部门编号清除雇员表中的deptno数据项
     * @param deptno 部门编号
     * @return true- 清除成功 | false - 清除失败
     */
    @PostMapping("/hr/provider/emp/clear_deptno")
    boolean editClearDeptno(@RequestParam("deptno") long deptno);

    /**
     * 查询指定部门的雇员数量
     * @param deptno 部门编号
     * @return 部门中的雇员人数
     */
    @GetMapping("/hr/provider/emp/get_dept_emp_count")
    Integer getDeptEmpCount(@RequestParam("deptno") long deptno);
}
