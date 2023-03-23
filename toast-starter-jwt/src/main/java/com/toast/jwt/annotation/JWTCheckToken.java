package com.toast.jwt.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JWTCheckToken {
    /**
     * 配置启用，认证排查
     * @return
     */
    boolean required() default true;

    /**
     * 检查角色
     * @return
     */
    String role() default "";

    /**
     * 权限检查
     * @return
     */
    String action() default "";
}
