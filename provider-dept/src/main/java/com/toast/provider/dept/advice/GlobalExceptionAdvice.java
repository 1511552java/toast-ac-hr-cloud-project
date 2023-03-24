package com.toast.provider.dept.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe
 */
@ControllerAdvice
public class GlobalExceptionAdvice {
    @ExceptionHandler(Exception.class)            // 捕获全部异常
    @ResponseBody
    public Object exceptionHandler(Exception e) {
        HttpServletRequest request = ((ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes()).getRequest();
        Map<String, Object> map = new HashMap<>();
        map.put("message", e);            // 异常信息
        map.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);                // HTTP状态码
        map.put("exception", e.getClass().getName());    // 异常类型
        map.put("content", "服务器端程序出错。");        // 错误信息
        map.put("path", request.getRequestURL());        // 错误路径
        return map;
    }
}
