package com.toast.provider.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.toast.provider.member.entity.Action;
import com.toast.provider.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.Set;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Mapper
public interface ActionMapper extends BaseMapper<Action> {
    /**
     * 获取用户权限
     * @param mid 用户ID
     * @return 返回用户所有权限数据
     */
    Set<String> findAllByMember(String mid);
}
