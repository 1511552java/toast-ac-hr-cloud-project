package com.toast.provider.member.service.impl;

import com.toast.common.dto.MemberDTO;
import com.toast.common.service.IMemberService;
import com.toast.common.service.abs.AbstractService;
import com.toast.provider.member.mapper.MemberMapper;
import com.toast.util.bean.DeepBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}