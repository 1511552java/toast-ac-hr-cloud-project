package com.toast.provider.rating.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.toast.common.dto.RatingDTO;
import com.toast.common.response.R;
import com.toast.common.service.IRatingService;
import com.toast.common.service.abs.AbstractService;
import com.toast.provider.rating.entity.Rating;
import com.toast.provider.rating.mapper.RatingMapper;
import com.toast.util.bean.DeepBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe
 */
@Service
public class RatingServiceImpl extends AbstractService implements IRatingService {
    @Autowired
    private RatingMapper ratingMapper;
    @Override
    public List<RatingDTO> list() {
        List<RatingDTO> allRatings = DeepBeanUtils.copyListProperties( 	// 集合数据拷贝
                this.ratingMapper.selectList(new QueryWrapper<Rating>()), RatingDTO::new); // 查询全部数据
        return allRatings;
    }

    @Override
    public RatingDTO get(String rtid) {
        Rating rating = this.ratingMapper.selectById(rtid); // 数据ID查询
        RatingDTO dto = new RatingDTO();
        DeepBeanUtils.copyProperties(rating, dto);
        return dto;
    }
}
