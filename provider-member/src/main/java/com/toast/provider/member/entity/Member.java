package com.toast.provider.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Data
@TableName("member")
public class Member {
    @TableId
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