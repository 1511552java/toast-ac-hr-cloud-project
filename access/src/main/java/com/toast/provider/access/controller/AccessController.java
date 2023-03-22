package com.toast.provider.access.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.toast.common.response.R;
import com.toast.common.service.IAccessService;
import com.toast.provider.access.controller.block.AccessBlockHandler;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe
 */
@RestController
@RequestMapping("/system/provider/access/*")
public class AccessController {
    @Autowired
    private IAccessService accessService;
    @SentinelResource(value = "token", blockHandlerClass = AccessBlockHandler.class, blockHandler = "tokenHandler")
    @ApiOperation(value = "获取微服务Token", notes = "获取接入服务节点集群的Token数据")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "aid", value = "接入系统id", required = true, dataType = "string")
    )
    @GetMapping("token")
    public R<String> token(String aid) {
        return R.success(accessService.token(aid));
    }
}
