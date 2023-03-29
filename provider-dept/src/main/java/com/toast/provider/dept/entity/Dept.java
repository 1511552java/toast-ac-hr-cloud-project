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
    /**
     * 部门ID
     */
    @TableId
    private Long deptno;

    /**
     * 部门名称
     */
    private String dname;

    /**
     * 部门最大人数
     */
    private Integer bound;

    /**
     * 部门当前人数
     */
    private Integer current;

    /**
     * 部门领导ID
     */
    private Long empno;

    /**
     * 部门领导名称
     */
    private String mname;
}
