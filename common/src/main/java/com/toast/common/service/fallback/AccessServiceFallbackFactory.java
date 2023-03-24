package com.toast.common.service.fallback;

import com.toast.common.service.IAccessService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Slf4j
@Component
public class AccessServiceFallbackFactory implements FallbackFactory<IAccessService> {
    @Override
    public IAccessService create(Throwable cause) {
        return new IAccessService() {
            @Override
            public String token(String aid) {
                log.warn("AccessService token() fallback due to: {}, {}" + cause.getMessage(), cause);
                return null;
            }
        };
    }
}
