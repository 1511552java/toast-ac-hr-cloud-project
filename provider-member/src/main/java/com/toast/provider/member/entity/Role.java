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
@TableName("role")
public class Role {
    @TableId
    private String rid;

    /**
     * 角色名称
     */
    private String name;

    /**
     * note节点
     */
    private String note;
}

