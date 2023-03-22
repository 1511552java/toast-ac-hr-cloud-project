package com.toast.provider.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.toast.provider.access.entity.Access;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe Access数据映射层
 */
@Mapper
public interface IAccessMapper extends BaseMapper<Access> {
    /**
     * 更新系统接入token
     * @param params 传入以下数据
     *               key = aid : 接入系统id
     *               key = token: 接入系统token
     * @return
     */
    boolean doUpdateToken(Map<String, String> params);
}
