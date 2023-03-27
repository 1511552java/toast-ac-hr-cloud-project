package com.toast.provider.emp.entity;

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
@TableName("emp")
public class Emp {
    @TableId
    private Long empno;
    private Long deptno;
    private String rtid;
    private String ename;
    private Date hiredate;
    private Double sal;
    private String job;
}
