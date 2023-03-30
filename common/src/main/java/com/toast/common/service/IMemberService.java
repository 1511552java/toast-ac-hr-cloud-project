package com.toast.common.service;

import com.toast.common.dto.MemberDTO;
import com.toast.common.service.config.FeignConfig;
import com.toast.common.service.fallback.MemberServiceFallbackFactor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe 成员服务层
 */
@FeignClient(
        value = "hr.gateway",
        configuration = FeignConfig.class,
        fallbackFactory = MemberServiceFallbackFactor.class
)
public interface IMemberService {
    /**
     * 根据用户ID查询用户完整信息
     * @param mid 用户ID
     * @return
     */
    @GetMapping("/hr/provider/member/get")
    MemberDTO get(@RequestParam("mid") String mid);

    /**
     * 用户信息列表
     * @param currentPage   当前页
     * @param lineSize      每页大小
     * @param column        模糊查询
     * @param keyword       查询关键字
     * @return  返回结果包含有用户数据集合和行数统计，使用Map存储，包含的数据项信息如下：
     *  key = count : 数据行数统计
     *  key = data : 用户数据
     */
    Map<String, Object> split(
            @RequestParam("currentPage") long currentPage,
            @RequestParam("lineSize") long lineSize,
            @RequestParam("column") String column,
            @RequestParam("keyword") String keyword);
}
