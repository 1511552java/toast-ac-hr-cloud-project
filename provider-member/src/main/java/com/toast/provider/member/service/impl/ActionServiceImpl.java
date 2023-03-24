package com.toast.provider.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.toast.common.dto.ActionDTO;
import com.toast.common.service.IActionService;
import com.toast.common.service.abs.AbstractService;
import com.toast.provider.member.entity.Action;
import com.toast.provider.member.mapper.ActionMapper;
import com.toast.util.bean.DeepBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Service
public class ActionServiceImpl extends AbstractService implements IActionService {
    @Autowired
    private ActionMapper actionMapper;
    @Override
    public List<ActionDTO> listByRole(String rid) {
        QueryWrapper<Action> wrapper = new QueryWrapper<>();
        wrapper.eq("rid", rid); // 设置rid查询条件
        List<ActionDTO> allActions = DeepBeanUtils.copyListProperties( 	// 集合数据拷贝
                this.actionMapper.selectList(wrapper), ActionDTO::new); // 查询全部数据
        return allActions;
    }

    @Override
    public Set<String> listIdByMember(String mid) {
        return this.actionMapper.findAllByMember(mid);
    }
}
