package com.toast.endpoint.controller;

import com.toast.common.dto.RatingDTO;
import com.toast.common.response.R;
import com.toast.endpoint.service.IRatingEndpointService;
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

    @GetMapping("/list")
    public R<List<RatingDTO>> list() {
        return R.data(endpointService.list());
    }
}
