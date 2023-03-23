package com.toast.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.endpoint.event.RefreshEvent;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe
 */
@Slf4j
@Service
public class DynamicRouteService implements ApplicationEventPublisherAware {
    @Autowired
    private RouteDefinitionWriter routeDefinitionWriter; // 路由数据的写入
    private ApplicationEventPublisher publisher; // 事件发布器

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher; // 保存事件发布器
    }

    public boolean add(RouteDefinition definition) {    // 追加新的路由配置
        log.info("增加路由配置项，新的路由ID为：{}", definition.getId()); // 日志输出
        try {
            this.routeDefinitionWriter.save(Mono.just(definition)).subscribe(); // 配置写入
            this.publisher.publishEvent(new RefreshRoutesEvent(this)); // 发布路由事件
        } catch (Exception e) {
            e.printStackTrace();
            log.error("路由增加失败，增加的路由ID为：{}", definition.getId());
            return false;
        }
        return true;
    }

    public Mono<ResponseEntity<Object>> delete(String id) { // 根据id删除数据
        log.info("删除路由配置项，删除的路由ID为：{}", id); // 日志输出
        return this.routeDefinitionWriter.delete(Mono.just(id)).then(Mono.defer(() -> {
            return Mono.just(ResponseEntity.ok().build());
        })).onErrorResume((t) -> {
            return t instanceof NotFoundException;
        }, (r) -> {
            return Mono.just(ResponseEntity.notFound().build());
        });
    }

    public boolean update(RouteDefinition definition) {    // 修改已有的路由配置
        log.info("更新路由配置项，新的路由ID为：{}", definition.getId()); // 日志输出
        try {
            this.delete(definition.getId()); // 根据ID删除已有路由
            this.routeDefinitionWriter.save(Mono.just(definition)).subscribe(); // 配置写入
            this.publisher.publishEvent(new RefreshRoutesEvent(this)); // 发布路由事件
        } catch (Exception e) {
            log.error("路由更新失败，增加的路由ID为：{}", definition.getId());
            return false;
        }
        return true;
    }
}
