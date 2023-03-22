package com.toast.provider.access.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.toast.provider.access.entity.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe
 */
@Mapper
public interface IRoleMapper extends BaseMapper<Role> {
    /**
     * 获取接入系统角色
     * @param aid
     * @return
     */
    Set<String> findAllByAccess(String aid);

    /**
     * 根据接入系统id查询对应角色
     * @param aid   系统id
     * @return
     */
    List<Role> findAllDetailsByAccess(String aid);
}
