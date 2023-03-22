package com.toast.common.service.abs;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe 提供Service层抽象类
 */
public abstract class AbstractService {
    /**
     * 判断当前的业务查询是否需要进行数据模糊匹配
     * @param column    当前列
     * @param keyword   查询关键字
     * @return  如果需要模糊匹配返回true, 是否返回false
     */
    public boolean isLike(String column, String keyword) {
        return column != null && !column.equals("") && keyword != null && !keyword.equals("");
    }
}
