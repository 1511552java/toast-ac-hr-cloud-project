package com.toast.common.dto;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe 工资等级传输类
 */
public class RatingDTO {
    private String rtid;

    /**
     * 等级名称
     */
    private String name;

    /**
     * 最低等级工资
     */
    private Double low;

    /**
     * 最高等级工资
     */
    private Double high;

    /**
     * 其他描述
     */
    private String note;
}
