package com.toast.jwt.service;

/**
 * @author 土司先生
 * @time 2023/3/21
 * @describe 加密服务
 */
public interface IEncryptService {

    /**
     * @param password 要加密的密码
     * @return 返回密码的密文
     */
    String getEncryptPassword(String password);
}
