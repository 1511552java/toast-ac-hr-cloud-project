package com.toast.common.service.fallback;

import com.toast.common.dto.RatingDTO;
import com.toast.common.service.IRatingService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe 失败回退处理
 */
@Slf4j
@Component
public class RatingServiceFallbackFactory implements FallbackFactory<IRatingService> {

    @Override
    public IRatingService create(Throwable cause) {
        return new IRatingService() {
            @Override
            public List<RatingDTO> list() {
                log.warn("fallback due to: " + cause.getMessage(), cause);
                return List.of();
            }

            @Override
            public RatingDTO get(String rtid) {
                log.info("fallback due to: " + cause.getMessage(), cause);
                return new RatingDTO();
            }
        };
    }
}

