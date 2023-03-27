package com.toast.provider.record.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author 土司先生
 * @time 2023/3/27
 * @describe
 */
@Data
@TableName("record")
public class Record {
    @TableId
    private Long recid;
    private Date udate;
    private String mid;
    private String name;
    private String operate;
    private String data;
    private String tab;
}
