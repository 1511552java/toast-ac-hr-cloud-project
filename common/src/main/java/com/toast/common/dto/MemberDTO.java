package com.toast.common.dto;

import lombok.Data;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Data
public class MemberDTO {
    /**
     * 成员ID
     */
    private String mid;

    /**
     * 用户名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;


    private int flag;
}
