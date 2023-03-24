package com.toast.common.controller.abs;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author 土司先生
 * @time 2023/3/24
 * @describe 公共的Controller抽象类
 */
public abstract class AbstractBaseController {
    /**
     * 在现在的开发之中如果要将字符串转为日期时间，考虑到多线程环境下的并发问题，所以一定要使用LocalDate
     */
    private static final DateTimeFormatter LOCAL_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(java.util.Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                LocalDate localDate = LocalDate.parse(text, LOCAL_DATE_FORMAT);
                Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
                super.setValue(java.util.Date.from(instant));
            }
        });
    }

    /**
     * 字符串转整形
     * @param data
     * @return
     */
    public int parseInteger(String data) {
        if (data.matches("\\d+")) {
            return Integer.parseInt(data);
        } else {
            return 0;
        }
    }

    /**
     * 字符串转长整型
     * @param data
     * @return
     */
    public long parseLong(String data) {
        if (data.matches("\\d+")) {
            return Long.parseLong(data);
        } else {
            return 0L;
        }
    }

    /**
     * 字符串转双精度浮点型
     * @param data
     * @return
     */
    public double parseDouble(String data) {
        if (data.matches("\\d+(\\.\\d+)?")) {
            return Double.parseDouble(data);
        } else {
            return 0.0;
        }
    }

    /**
     * 字符串转布尔型
     * @param data
     * @return
     */
    public Boolean parseBoolean(String data) {
        return Boolean.parseBoolean(data);
    }
}
