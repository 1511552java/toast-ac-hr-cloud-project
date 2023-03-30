package com.toast.endpoint.service.impl;

import com.toast.common.service.IRecordService;
import com.toast.endpoint.service.IRecordEndpointService;
import com.toast.endpoint.service.abs.AbstractEndpointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
@Service
public class RecordEndpointServiceImpl extends AbstractEndpointService implements IRecordEndpointService {
    @Autowired
    private IRecordService recordService;

    @Override
    public Map<String, Object> list(long currentPage, long lineSize, String column, String keyword) {
        return this.recordService.split(currentPage, lineSize, column, keyword);
    }
}
