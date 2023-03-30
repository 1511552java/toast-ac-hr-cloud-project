package com.toast.endpoint.service.impl;

import com.toast.common.dto.RecordDTO;
import com.toast.endpoint.service.IRecordMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @author 土司先生
 * @time 2023/3/30
 * @describe
 */
@Service
public class RecordMessageServiceImpl implements IRecordMessageService {
    @Autowired
    private MessageChannel output;            // 消息通道

    @Override
    public void sendRecord(RecordDTO record) {
        this.output.send(MessageBuilder.withPayload(record).build());    // 发送消息
    }
}
