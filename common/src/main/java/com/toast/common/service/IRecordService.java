package com.toast.common.service;

import com.toast.common.service.config.FeignConfig;
import com.toast.common.service.fallback.RecordServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@FeignClient(value = "hr.gateway", // 使用网关的名称进行访问
        configuration = FeignConfig.class, // 定义要访问的微服务实例名称
        fallbackFactory = RecordServiceFallbackFactory.class) // Fallback处理
public interface IRecordService {

    /**
     * 日志列表显示
     * @param currentPage 当前所在页
     * @param lineSize 每页显示数据行数
     * @param column 模糊查询列
     * @param keyword 查询关键字
     * @return 返回结果包含有用户数据集合和行数统计，使用Map存储，包含的数据项信息如下：
     * key = count：数据行数统计
     * key = data：记录数据集合
     */
    Map<String, Object> split(
            @RequestParam("currentPage") long currentPage,
            @RequestParam("lineSize") long lineSize,
            @RequestParam("column") String column,
            @RequestParam("keyword") String keyword);
}
