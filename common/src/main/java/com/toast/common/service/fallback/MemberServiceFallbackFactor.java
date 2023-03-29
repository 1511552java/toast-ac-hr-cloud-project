package com.toast.common.service.fallback;

import com.toast.common.dto.MemberDTO;
import com.toast.common.service.IMemberService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Slf4j
@Component
public class MemberServiceFallbackFactor implements FallbackFactory<IMemberService> {
    @Override
    public IMemberService create(Throwable cause) {
        return new IMemberService() {
            @Override
            public MemberDTO get(String mid) {
                log.warn("memberService get() fallback; message: {}, cause: {}", cause.getMessage(), cause.getCause());
                return new MemberDTO();
            }

            @Override
            public Map<String, Object> split(long currentPage, long lineSize, String column, String keyword) {
                log.warn("memberService get() fallback; message: {}, cause: {}", cause.getMessage(), cause.getCause());
                return Map.of();
            }
        };
    }
}
