package com.toast.provider.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.toast.provider.member.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 获取用户角色
     * @param mid 用户ID
     * @return
     */
    Set<String> findAllByMember(String mid);

    /**
     * 根据用户id查询对应角色详情信息
     * @param mid 用户ID
     * @return
     */
    List<Role> findAllDetailsByMember(String mid);
}
