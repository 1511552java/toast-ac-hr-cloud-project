package com.toast.endpoint.controller;

import com.toast.common.response.R;
import com.toast.endpoint.controller.abs.AbstractEndpointController;
import com.toast.endpoint.service.IRecordEndpointService;
import com.toast.jwt.annotation.JWTCheckToken;
import org.bouncycastle.asn1.ocsp.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
public class RecordEndpointController extends AbstractEndpointController {
    @Autowired
    private IRecordEndpointService recordEndpointService;
    @JWTCheckToken(role = "record", action = "record:list")
    @GetMapping("list")
    public R<Map<String, Object>> list(Long currentPage, Long lineSize, String column, String keyword) {
        return R.success(this.recordEndpointService.list(currentPage, lineSize, column, keyword));
    }
}
