package com.toast.endpoint.service;

import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
public interface IRecordEndpointService {
    /**
     * 列出全部的角色信息
     * @param currentPage 当前页
     * @param lineSize 显示行数大小
     * @param column 查询列
     * @param keyword 关键字
     * @return
     */
    Map<String, Object> list(long currentPage, long lineSize, String column, String keyword);
}
