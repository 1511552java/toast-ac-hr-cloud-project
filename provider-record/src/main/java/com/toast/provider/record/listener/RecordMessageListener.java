package com.toast.provider.record.listener;

import com.toast.common.dto.RecordDTO;
import com.toast.provider.record.entity.Record;
import com.toast.provider.record.mapper.RecordMapper;
import com.toast.util.bean.DeepBeanUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @author 土司先生
 * @time 2023/3/27
 * @describe
 */
@Slf4j
@Component
public class RecordMessageListener {
    @Autowired
    private RecordMapper recordMapper;

    @StreamListener(Sink.INPUT) // 监听通道
    public void receive(Message<RecordDTO> record) {    // 接收消息
        log.info(record.toString()); // 日志输出
        Record r = new Record();
        DeepBeanUtils.copyProperties(record.getPayload(), r);
        this.recordMapper.insert(r); // 保存数据
    }
}
