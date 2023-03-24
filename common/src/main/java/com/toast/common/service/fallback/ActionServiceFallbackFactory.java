package com.toast.common.service.fallback;

import com.toast.common.dto.ActionDTO;
import com.toast.common.service.IActionService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Slf4j
@Component
public class ActionServiceFallbackFactory implements FallbackFactory<IActionService> {
    @Override
    public IActionService create(Throwable cause) {
        return new IActionService() {
            @Override
            public List<ActionDTO> listByRole(String rid) {
                log.warn("ActionService listByRole() fallback due to: {}, {}", cause.getMessage(), cause);
                return List.of();
            }

            @Override
            public Set<String> listIdByMember(String mid) {
                log.warn("ActionService listIdByMember() fallback due to: {}, {}", cause.getMessage(), cause);
                return Set.of();
            }
        };
    }
}
