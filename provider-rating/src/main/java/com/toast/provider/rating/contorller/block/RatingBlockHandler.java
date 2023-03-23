package com.toast.provider.rating.contorller.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe
 */
public class RatingBlockHandler {
    public static Object listHandler(BlockException exception) {
        return "工资系统繁忙，无法查阅！";
    }
}
