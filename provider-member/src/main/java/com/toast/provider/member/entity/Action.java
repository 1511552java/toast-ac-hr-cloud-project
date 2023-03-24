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
@TableName("action")
public class Action {
    @TableId
    private String aid;
    private String rid;
    private String name;
    private String note;
}
