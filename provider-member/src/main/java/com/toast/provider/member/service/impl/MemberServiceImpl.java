package com.toast.provider.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.toast.common.dto.MemberDTO;
import com.toast.common.service.IMemberService;
import com.toast.common.service.abs.AbstractService;
import com.toast.provider.member.entity.Member;
import com.toast.provider.member.mapper.MemberMapper;
import com.toast.util.bean.DeepBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Service
public class MemberServiceImpl extends AbstractService implements IMemberService {
    @Autowired
    private MemberMapper memberMapper;
    @Override
    public MemberDTO get(String mid) {
        MemberDTO dto = new MemberDTO();
        DeepBeanUtils.copyProperties(memberMapper.selectById(mid), dto);
        return dto;
    }

    @Override
    public Map<String, Object> split(long currentPage, long lineSize, String column, String keyword) {
        Map<String, Object> result = new HashMap<>();
        QueryWrapper<Member> wrapper = new QueryWrapper<>(); // 获取查询包装器
        if (super.isLike(column, keyword)) { // 判断是否需要模糊匹配
            wrapper.like(column, "%" + keyword + "%"); // 设置模糊查询列
        }
        Integer count = this.memberMapper.selectCount(wrapper); // 数据查询
        result.put("count", count); // 保存查询统计结果
        Page<Member> empPage = new Page<>(currentPage, lineSize, count); // 创建分页器
        IPage<Member> resultPage = this.memberMapper.selectPage(empPage, wrapper); // 数据查询
        List<MemberDTO> allMembers = DeepBeanUtils.copyListProperties( 	// 集合数据拷贝
                resultPage.getRecords(), MemberDTO::new); // 查询全部数据
        result.put("data", allMembers); // 获取查询结果
        result.put("totalPage", resultPage.getTotal());
        return result;
    }
}