package com.toast.provider.dept.controller.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.toast.common.dto.DeptDTO;

import java.util.ArrayList;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
public class DeptBlockHandler {
    public static Object listHandler(BlockException ex) {    // 部门列表拦截
        return new ArrayList<DeptDTO>(); // 数据响应
    }
}
