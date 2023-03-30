package com.toast.common.service;

import com.toast.common.dto.RoleDTO;
import com.toast.common.service.config.FeignConfig;
import com.toast.common.service.fallback.RatingServiceFallbackFactory;
import com.toast.common.service.fallback.RoleServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe 角色权限服务接口
 */
@FeignClient(
        value = "hr.gateway",
        configuration = FeignConfig.class,
        fallbackFactory = RoleServiceFallbackFactory.class
)
public interface IRoleService {
    /**
     * @return 获取全部角色信息
     */
    @GetMapping("/hr/provider/member/role/list")
    public List<RoleDTO> list();

    /**
     * @param mid 用户ID
     * @return 返回用户的所有角色信息
     */
    @GetMapping("/hr/provider/member/role/list_by_member")
    public List<RoleDTO> listByMember(@RequestParam("mid") String mid);

    /**
     * @param mid 用户ID
     * @return 根据用户ID查询对应权限数据
     */
    @GetMapping("/hr/provider/member/role/list_id_by_member")
    public Set<String> listIdByMember(@RequestParam("mid") String mid); //
}