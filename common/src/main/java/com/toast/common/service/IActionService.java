package com.toast.common.service;

import com.toast.common.dto.ActionDTO;
import com.toast.common.service.config.FeignConfig;
import com.toast.common.service.fallback.ActionServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@FeignClient(
        value = "hr.gateway",
        url = "gateway:9051",
        configuration = FeignConfig.class,
        fallbackFactory = ActionServiceFallbackFactory.class
)
public interface IActionService {
    /**
     * 根据角色获取权限数据
     * @param rid 角色ID
     * @return 返回指定角色的权限数据
     */
    @GetMapping("/hr/provider/member/action/list_by_role")
    List<ActionDTO> listByRole(@RequestParam("rid") String rid);

    /**
     * 根据角色获取对应的权限数据
     * @param mid   用户ID
     * @return 返回用户的权限数据
     */
    @GetMapping("/hr/provider/member/action/list_by_member")
    Set<String> listIdByMember(@RequestParam("mid") String mid);
}
