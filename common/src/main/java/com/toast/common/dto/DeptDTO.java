package com.toast.common.dto;

import lombok.Data;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Data
public class DeptDTO {
    private Long deptno;
    private String dname;
    private Integer bound;
    private Integer current;
    private Long empno;
    private String mname;
}
