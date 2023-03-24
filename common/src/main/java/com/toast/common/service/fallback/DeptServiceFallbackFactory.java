package com.toast.common.service.fallback;

import com.toast.common.dto.DeptDTO;
import com.toast.common.service.IDeptService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@Slf4j
@Component
public class DeptServiceFallbackFactory implements FallbackFactory<IDeptService> {
    @Override
    public IDeptService create(Throwable cause) {
        return new IDeptService() {
            @Override
            public List<DeptDTO> list() {
                log.warn("IDeptService list() fallback due to: {}, {}", cause.getMessage(), cause);
                return List.of();
            }

            @Override
            public boolean editMgr(DeptDTO dto) {
                log.warn("IDeptService editMgr() fallback due to: {}, {}", cause.getMessage(), cause);
                return false;
            }

            @Override
            public boolean add(DeptDTO dto) {
                log.warn("IDeptService add() fallback due to: {}, {}", cause.getMessage(), cause);
                return false;
            }

            @Override
            public boolean edit(DeptDTO dto) {
                log.warn("IDeptService edit() fallback due to: {}, {}", cause.getMessage(), cause);
                return false;
            }

            @Override
            public boolean remove(long deptno) {
                log.warn("IDeptService remove() fallback due to: {}, {}", cause.getMessage(), cause);
                return false;
            }

            @Override
            public DeptDTO get(long deptno) {
                log.warn("IDeptService get() fallback due to: {}, {}", cause.getMessage(), cause);
                return null;
            }

            @Override
            public boolean editIncrementCurrent(long deptno) {
                log.warn("IDeptService editIncrementCurrent() fallback due to: {}, {}", cause.getMessage(), cause);
                return false;
            }

            @Override
            public boolean editDecrementCurrent(long deptno) {
                log.warn("IDeptService editDecrementCurrent() fallback due to: {}, {}", cause.getMessage(), cause);
                return false;
            }

            @Override
            public DeptDTO getDeptByMgr(long deptno, long empno) {
                log.warn("IDeptService getDeptByMgr() fallback due to: {}, {}", cause.getMessage(), cause);
                return null;
            }

            @Override
            public boolean editDeptMgr(DeptDTO dto) {
                log.warn("IDeptService editDeptMgr() fallback due to: {}, {}", cause.getMessage(), cause);
                return false;
            }

            @Override
            public boolean removeDeptMgr(long deptno) {
                log.warn("IDeptService removeDeptMgr() fallback due to: {}, {}", cause.getMessage(), cause);
                return false;
            }
        };
    }
}
