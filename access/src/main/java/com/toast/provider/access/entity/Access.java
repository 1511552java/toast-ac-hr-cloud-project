package com.toast.provider.access.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe 系统访问实体类
 * 每一个系统都会有一个Access的注册信息，这个注册信息必须在系统正式接入的时候才允许提供，有了相应的授权信息之后，
 * 在进行TOKEN生成的时候就可以通过系统注册的aid编号查询到对应的角色以及权限数据
 */
@Data
@TableName("access")
public class Access {
    /**
     * 管理系统ID，erp, oa, cms等
     */
    @TableId
    private String aid;

    /**
     * 管理系统名称
     */
    private String name;

    /**
     * 访问系统的token令牌
     */
    private String token;
}