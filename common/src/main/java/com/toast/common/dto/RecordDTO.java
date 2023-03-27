package com.toast.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author 土司先生
 * @time 2023/3/27
 * @describe
 */
@Data
public class RecordDTO {
    private Long recid;
    private Date udate;
    private String mid;
    private String name;
    private String operate;
    private String data;
    private String tab;
}
