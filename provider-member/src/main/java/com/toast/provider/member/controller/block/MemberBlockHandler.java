package com.toast.provider.member.controller.block;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.toast.common.dto.MemberDTO;

import java.util.ArrayList;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
public class MemberBlockHandler {
    /**
     * 用户拦截处理
     * @param ex
     * @return
     */
    public static Object listHandler(BlockException ex) {
        return new ArrayList<MemberDTO>(); // 数据响应
    }
}
