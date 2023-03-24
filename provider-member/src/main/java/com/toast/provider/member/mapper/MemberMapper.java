package com.toast.provider.member.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.toast.provider.member.entity.Member;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {
}
