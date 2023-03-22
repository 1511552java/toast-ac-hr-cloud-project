package com.toast.provider.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.toast.provider.access.entity.Action;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe
 */
@Mapper
public interface IActionMapper extends BaseMapper<Action> {
    /**
     * 获取用户权限
     * @param aid 系统ID
     * @return
     */
    Set<String> findAllByAccess(String aid);
}
