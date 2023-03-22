package com.toast.provider.access.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe
 */
@Data
@TableName("role")
public class Role {
    @TableId
    private String rid;
    private String name;
    private String note;
}
