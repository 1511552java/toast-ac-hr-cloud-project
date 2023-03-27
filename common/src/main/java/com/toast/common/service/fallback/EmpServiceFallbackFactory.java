package com.toast.common.service.fallback;

import com.toast.common.dto.EmpDTO;
import com.toast.common.service.IEmpService;
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
public class EmpServiceFallbackFactory implements FallbackFactory<IEmpService> {
    @Override
    public IEmpService create(Throwable cause) {
        return new IEmpService() {
            @Override
            public Map<String, Object> split(long currentPage, long lineSize, String column, String keyword) {
                log.warn("IEmpService split() fallback due to: {}, {}" + cause.getMessage(), cause);
                return Map.of();
            }

            @Override
            public EmpDTO get(long empno) {
                log.warn("IEmpService get() fallback due to: {}, {}" + cause.getMessage(), cause);
                return null;
            }

            @Override
            public boolean add(EmpDTO emp) {
                log.warn("IEmpService add() fallback due to: {}, {}" + cause.getMessage(), cause);
                return false;
            }

            @Override
            public boolean edit(EmpDTO emp) {
                log.warn("IEmpService edit() fallback due to: {}, {}" + cause.getMessage(), cause);
                return false;
            }

            @Override
            public boolean remove(long empno) {
                log.warn("IEmpService remove() fallback due to: {}, {}" + cause.getMessage(), cause);
                return false;
            }

            @Override
            public boolean editClearDeptno(long deptno) {
                log.warn("IEmpService editClearDeptno() fallback due to: {}, {}" + cause.getMessage(), cause);
                return false;
            }

            @Override
            public Integer getDeptEmpCount(long deptno) {
                log.warn("IEmpService getDeptEmpCount() fallback due to: {}, {}" + cause.getMessage(), cause);
                return -1;
            }
        };
    }
}
