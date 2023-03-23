package com.toast.gateway.predicate.factory;

import com.toast.gateway.predicate.config.TimeSubsectionConfig;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe
 */
@Component
public class DefaultTimeSubsectionRoutePredicateFactory
        extends AbstractRoutePredicateFactory<TimeSubsectionConfig> {
    // 既然需要根据当前的时间进行判断，所以这个时候就需要有一个时间的转换处理类
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    public DefaultTimeSubsectionRoutePredicateFactory() {
        super(TimeSubsectionConfig.class); // 传递配置项
    }
    @Override
    public Predicate<ServerWebExchange> apply(TimeSubsectionConfig config) {
        return serverWebExchange -> {
            String now = LocalTime.now().format(FORMATTER); // 获取当前的时和分
            return config.getSection().contains(now); // 存在判断
        };
    }
    @Override
    public List<String> shortcutFieldOrder() { // 配置项
        // 按照“,”定义多个不同的配置项，而后此时会自动进行数据的拆分
        return Collections.singletonList("section"); // 配置项的名称定义
    }

    @Override
    public ShortcutType shortcutType() { // 明确的定义分割项
        return ShortcutType.GATHER_LIST;
    }
}