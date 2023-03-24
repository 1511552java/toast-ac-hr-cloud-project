package com.toast.provider.dept.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Data
@TableName("dept")
public class Dept {
    @TableId
    private Long deptno;
    private String dname;
    private Integer bound;
    private Integer current;
    private Long empno;
    private String mname;
}
