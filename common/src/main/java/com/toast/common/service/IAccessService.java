package com.toast.common.service;

import com.toast.common.service.config.FeignConfig;
import com.toast.common.service.fallback.AccessServiceFallbackFactory;
import com.toast.common.service.fallback.MemberServiceFallbackFactor;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe 系统接入服务层
 */
@FeignClient(
        url = "gateway:9501",
        value = "hr.gateway",
        configuration = FeignConfig.class,
        fallbackFactory = AccessServiceFallbackFactory.class
)
public interface IAccessService {
    /**
     * 根据接入系统id返回token
     * @param aid   系统id
     * @return 返回指定的系统token
     */
    String token(String aid);
}
