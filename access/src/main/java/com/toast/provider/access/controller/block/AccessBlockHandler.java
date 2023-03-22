package com.toast.provider.access.controller.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;

import java.util.ArrayList;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe
 */
public class AccessBlockHandler {
    public static Object getHandler(BlockException ex) {    // 接入系统服务拦截
        return "不好意思，接入系统繁忙中，请重试！";
    }
}
