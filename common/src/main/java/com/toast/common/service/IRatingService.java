package com.toast.common.service;

import com.toast.common.dto.RatingDTO;
import com.toast.common.response.R;
import com.toast.common.service.config.FeignConfig;
import com.toast.common.service.fallback.RatingServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe
 */
@FeignClient(
        value = "hr.gateway",
        configuration = FeignConfig.class,
        fallbackFactory = RatingServiceFallbackFactory.class)
public interface IRatingService {
    /**
     * @return 远程REST接口
     */
    @GetMapping("/hr/provider/rating/list")
    List<RatingDTO> list();

    /**
     * @param rtid 远程REST接口
     */
    @GetMapping("/hr/provider/rating/get")
    RatingDTO get(@RequestParam("rtid") String rtid);
}