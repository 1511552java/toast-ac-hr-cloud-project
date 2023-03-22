package com.toast.common.response;

import java.io.Serializable;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe 响应体结果获取标准
 */
public interface IResultCode extends Serializable {
    int getCode();

    String getMessage();
}
