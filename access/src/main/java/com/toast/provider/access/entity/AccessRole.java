package com.toast.provider.access.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe
 */
@Data
@TableName("access_role")
public class AccessRole {
    private String aid;
    private String rid;
}
