package com.toast.provider.emp.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.toast.common.dto.EmpDTO;
import com.toast.common.service.IEmpService;
import com.toast.jwt.annotation.JWTCheckToken;
import com.toast.provider.emp.controller.block.EmpBlockHandler;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 土司先生
 * @time 2023/3/27
 * @describe
 */
@Slf4j
@RestController
@RequestMapping("/hr/provider/emp/*")
public class EmpController {
    @Autowired
    private IEmpService empService;

    @ApiOperation(value = "雇员列表", notes = "基于分页形式实现雇员列表查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前所在页码", required = true, dataType = "long"),
            @ApiImplicitParam(name = "lineSize", value = "每次加载的数据行数", required = true, dataType = "long"),
            @ApiImplicitParam(name = "column", value = "模糊查询列名称", required = true, dataType = "string"),
            @ApiImplicitParam(name = "keyword", value = "模糊查询关键字", required = true, dataType = "string")
    })
    @SentinelResource(value = "list", blockHandlerClass = EmpBlockHandler.class, blockHandler = "listHandler")
    @JWTCheckToken(role = "emp", action = "emp:list")
    @GetMapping("list")
    public Object list(Long currentPage, Long lineSize, String column, String keyword) {
        return this.empService.split(currentPage, lineSize, column, keyword);
    }

    @ApiOperation(value = "雇员详情", notes = "根据雇员编号查询雇员的详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empno", value = "雇员编号", required = true, dataType = "long")
    })
    @SentinelResource(value = "get", blockHandlerClass = EmpBlockHandler.class, blockHandler = "getHandler")
    @JWTCheckToken(role = "emp", action = "emp:list")
    @GetMapping("get")
    public Object get(Long empno) {
        return this.empService.get(empno);
    }

    @ApiOperation(value = "雇员增加", notes = "增加雇员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dto", value = "雇员信息", required = true, dataType = "json")
    })
    @SentinelResource(value = "add", blockHandlerClass = EmpBlockHandler.class, blockHandler = "addHandler")
    @JWTCheckToken(role = "emp", action = "emp:add")
    @PostMapping
    public Object add(@RequestBody EmpDTO dto) {
        log.info("雇员微服务增加数据。{}", dto);
        return this.empService.add(dto);
    }

    @ApiOperation(value = "雇员修改", notes = "修改雇员数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dto", value = "雇员信息", required = true, dataType = "json")
    })
    @SentinelResource(value = "edit", blockHandlerClass = EmpBlockHandler.class, blockHandler = "editHandler")
    @JWTCheckToken(role = "emp", action = "emp:edit")
    @PutMapping
    public Object edit(@RequestBody EmpDTO dto) {
        log.info("雇员微服务修改数据。{}", dto);
        return this.empService.edit(dto);
    }


    @ApiOperation(value = "雇员删除", notes = "根据雇员编号删除雇员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "empno", value = "雇员编号", required = true, dataType = "long")
    })
    @SentinelResource(value = "remove", blockHandlerClass = EmpBlockHandler.class, blockHandler = "removeHandler")
    @JWTCheckToken(role = "emp", action = "emp:edit")
    @DeleteMapping
    public Object remove(Long empno) {
        return this.empService.remove(empno);
    }

    @ApiOperation(value = "清除部门编号", notes = "根据要删除的部门编号删除雇员边中对应的部门编号数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptno", value = "部门编号", required = true, dataType = "long")
    })
    @SentinelResource(value = "clear_deptno", blockHandlerClass = EmpBlockHandler.class, blockHandler = "clearDeptnoHandler")
    @JWTCheckToken(role = "emp", action = "emp:edit")
    @PostMapping("clear_deptno")
    public Object clearDeptno(Long deptno) {
        return this.empService.editClearDeptno(deptno);
    }

    @ApiOperation(value = "获取部门员工数量", notes = "动态查询当前已有的雇员人数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptno", value = "部门编号", required = true, dataType = "long")
    })
    @SentinelResource(value = "get_dept_emp_count", blockHandlerClass = EmpBlockHandler.class, blockHandler = "getDeptEmpCountHandler")
    @JWTCheckToken(role = "emp", action = "emp:list")
    @GetMapping("get_dept_emp_count")
    public Object getDeptEmpCount(Long deptno) {
        return this.empService.getDeptEmpCount(deptno);
    }
}