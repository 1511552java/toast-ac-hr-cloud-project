package com.toast.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Data
public class EmpDTO {
    private Long empno;
    private Long deptno;
    private String rtid;
    private String ename;
    private Date hiredate;
    private Double sal;
    private String job;
}
