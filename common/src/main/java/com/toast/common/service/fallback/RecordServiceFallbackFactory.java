package com.toast.common.service.fallback;

import com.toast.common.service.IRecordService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
@Slf4j
@Component
public class RecordServiceFallbackFactory implements FallbackFactory<IRecordService> {
    @Override
    public IRecordService create(Throwable cause) {
        return new IRecordService() {
            @Override
            public Map<String, Object> split(long currentPage, long lineSize, String column, String keyword) {
                log.warn("IRecordService split() fallback due to: " + cause.getMessage(), cause);
                return Map.of();
            }
        };
    }
}
