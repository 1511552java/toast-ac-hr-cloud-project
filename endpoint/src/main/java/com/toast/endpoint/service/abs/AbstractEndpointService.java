package com.toast.endpoint.service.abs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
public abstract class AbstractEndpointService {
    @Autowired
    protected ObjectMapper objectMapper;
}
