package com.toast.endpoint.service.impl;

import com.toast.common.dto.RatingDTO;
import com.toast.common.service.IRatingService;
import com.toast.endpoint.service.IRatingEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Service
public class RatingEndpointServiceImpl implements IRatingEndpointService {
    @Autowired
    private IRatingService ratingService;
    @Override
    public List<RatingDTO> list() {
        return this.ratingService.list();
    }
}
