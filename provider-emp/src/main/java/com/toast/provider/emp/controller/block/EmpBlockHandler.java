package com.toast.provider.emp.controller.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.toast.common.dto.EmpDTO;

import java.util.HashMap;

/**
 * @author 土司先生
 * @time 2023/3/27
 * @describe
 */
public class EmpBlockHandler {
    public static Object listHandler(BlockException ex) {    // 部门列表拦截
        return new HashMap<String, Object>();
    }
    public static Object addHandler(BlockException ex) {    // 新增拦截
        return false;
    }
    public static Object editHandler(BlockException ex) {    // 修改拦截
        return false;
    }
    public static Object removeHandler(BlockException ex) {    // 删除拦截
        return false;
    }
    public static Object getHandler(BlockException ex) {    // 获取拦截
        return new EmpDTO();
    }
    public static Object getDeptEmpCountHandler(BlockException ex) {    // 获取部门雇员数量拦截
        return 0;
    }
    public static Object clearDeptnoHandler(BlockException ex) {    // 清除部门拦截
        return false;
    }
}
