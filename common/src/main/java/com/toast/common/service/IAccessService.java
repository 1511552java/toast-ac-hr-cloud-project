package com.toast.common.service;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe 系统接入服务层
 */
public interface IAccessService {
    /**
     * 根据接入系统id返回token
     * @param aid   系统id
     * @return 返回指定的系统token
     */
    String token(String aid);
}
