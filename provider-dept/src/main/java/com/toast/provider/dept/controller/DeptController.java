package com.toast.provider.dept.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.toast.common.dto.DeptDTO;
import com.toast.common.service.IDeptService;
import com.toast.jwt.annotation.JWTCheckToken;
import com.toast.provider.dept.controller.block.DeptBlockHandler;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@RestController
@RequestMapping("")
public class DeptController {
    @Autowired
    private IDeptService deptService; // 注入部门业务实例
    @ApiOperation(value = "部门列表", notes = "根据部门编号查询部门详细信息")
    @SentinelResource(value="list", blockHandlerClass = DeptBlockHandler.class, blockHandler = "listHandler")
    @JWTCheckToken(role = "dept", action = "dept:list")
    @GetMapping("list")
    public Object list() {
        return this.deptService.list();
    }

    @ApiOperation(value = "部门基础信息修改", notes = "修改部门的基础信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dto", value = "部门JSON数据",
                    required = true, dataType = "string")
    })
    @JWTCheckToken(role = "dept", action = "dept:edit")
    @PostMapping("edit")
    public Object edit(@RequestBody DeptDTO dto) {
        return this.deptService.edit(dto);
    }

    @ApiOperation(value = "更新部门领导信息", notes = "根据部门编号、部门领导编号（雇员编号），更新部门领导姓名")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptno", value = "查询部门ID",
                    required = true, dataType = "int")
    })
    @JWTCheckToken(role = "dept", action = "dept:edit")
    @PostMapping("edit_dept_mgr")
    public Object editDeptMgr(@RequestBody DeptDTO dto) {
        return this.deptService.editDeptMgr(dto);
    }

    @ApiOperation(value = "部门详情", notes = "根据部门编号查询部门详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptno", value = "查询部门ID",
                    required = true, dataType = "int")
    })
    @JWTCheckToken(role = "dept", action = "dept:edit")
    @GetMapping("get")
    public Object get(long deptno) {
        return this.deptService.get(deptno);
    }
    @ApiOperation(value = "部门领导变更", notes = "修改部门已有的领导信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dto", value = "部门JSON数据",
                    required = true, dataType = "string")
    })

    @JWTCheckToken(role = "dept", action = "dept:edit")
    @PostMapping("editMgr")
    public Object editMgr(@RequestBody DeptDTO dto) {
        return this.deptService.editMgr(dto);
    }

    @ApiOperation(value = "部门创建", notes = "传入新建部门数据项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "dto", value = "部门JSON数据",
                    required = true, dataType = "string")
    })
    @JWTCheckToken(role = "dept", action = "dept:add")
    @PostMapping("add")
    public Object add(@RequestBody DeptDTO dto) {
        return this.deptService.add(dto);
    }

    @ApiOperation(value = "部门删除", notes = "根据部门编号删除部门信息，删除时会自动将该部门员工的对应部门编号清空")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptno", value = "部门ID",
                    required = true, dataType = "int")
    })
    @JWTCheckToken(role = "dept", action = "dept:remove")
    @DeleteMapping("remove")
    public Object remove(long deptno) {
        return this.deptService.remove(deptno);
    }

    @JWTCheckToken(role = "dept", action = "dept:edit")
    @PostMapping("edit_increment_current")
    @ApiOperation(value = "增加部门当前人数", notes = "部门雇员增加之后需要对当前部门人数进行增加处理，每次增加1个数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptno", value = "部门ID",
                    required = true, dataType = "int")
    })
    public Object editIncrementCurrent(long deptno) {
        return this.deptService.editIncrementCurrent(deptno);
    }

    @JWTCheckToken(role = "dept", action = "dept:edit")
    @PostMapping("edit_decrement_current")
    @ApiOperation(value = "减少部门当前人数", notes = "部门雇员删除之后需要对当前部门人数进行增加处理，每次减少1个数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptno", value = "部门ID",
                    required = true, dataType = "int")
    })
    public Object editDecrementCurrent(long deptno) {
        return this.deptService.editDecrementCurrent(deptno);
    }

    @JWTCheckToken(role = "dept", action = "dept:edit")
    @ApiOperation(value = "获取领导所在部门", notes = "根据部门编号和雇员编号（领导编号）查询部门信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deptno", value = "部门ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "empno", value = "雇员ID", required = true, dataType = "long")
    })
    @GetMapping("get_dept_mgr")
    public Object getDeptMgr(long deptno, long empno) {
        return this.deptService.getDeptByMgr(deptno, empno);
    }

    @PostMapping("clear_dept_mgr")
    public Object clearDeptMgr(long deptno) {
        return this.deptService.removeDeptMgr(deptno);
    }
}
