package com.toast.common.service.fallback;

import com.toast.common.dto.RoleDTO;
import com.toast.common.service.IRoleService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
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
public class RoleServiceFallbackFactory implements FallbackFactory<IRoleService> {
    @Override
    public IRoleService create(Throwable cause) {
        return new IRoleService() {
            @Override
            public Set<String> listIdByMember(String mid) {
                log.warn("RoleService listIdByMember() fallback due to: " + cause.getMessage(), cause);
                return Set.of();
            }

            @Override
            public List<RoleDTO> list() {
                log.warn("RoleService list() fallback due to: " + cause.getMessage(), cause);
                return List.of();
            }

            @Override
            public List<RoleDTO> listByMember(String mid) {
                log.warn("RoleService listByMember() fallback due to: " + cause.getMessage(), cause);
                return List.of();
            }
        };
    }
}
