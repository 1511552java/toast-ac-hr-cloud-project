package com.toast.endpoint.service;

import com.toast.common.dto.RatingDTO;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
public interface IRatingEndpointService {
    /*
     * 工资等级信息列表
     */
    List<RatingDTO> list();
}
