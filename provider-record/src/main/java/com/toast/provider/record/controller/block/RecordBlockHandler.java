package com.toast.provider.record.controller.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.toast.common.dto.RecordDTO;

import java.util.ArrayList;

/**
 * @author 土司先生
 * @time 2023/3/27
 * @describe
 */
public class RecordBlockHandler {
    public static Object listHandler(BlockException ex) {    // 部门列表拦截
        return new ArrayList<RecordDTO>(); // 数据响应
    }
}
