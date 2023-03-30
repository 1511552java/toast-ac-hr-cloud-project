package com.toast.endpoint.service;

import com.toast.common.dto.RecordDTO;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe 日志采集端
 */
public interface IRecordMessageService {
    public void sendRecord(RecordDTO record);            // 发送部门消息
}
