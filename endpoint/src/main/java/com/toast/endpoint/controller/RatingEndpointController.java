package com.toast.endpoint.controller;

import com.toast.common.dto.RatingDTO;
import com.toast.common.response.R;
import com.toast.endpoint.service.IRatingEndpointService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@RestController
@RequestMapping("/hr/endpoint/rating")
public class RatingEndpointController {
    @Autowired
    private IRatingEndpointService endpointService;

    @ApiOperation(value = "工资列表", notes = "获取所有工资等级列表信息")
    @GetMapping("/list")
    public R<List<RatingDTO>> list() {
        return R.data(endpointService.list());
    }

    @ApiOperation(value = "工资等级详情", notes = "获取指定工资等级详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "rtid", value = "工资等级编号", required = true, dataType = "string")
    })
    @GetMapping("/get")
    public R<RatingDTO> get(String rtid) {
        return R.data(endpointService.get(rtid));
    }
}