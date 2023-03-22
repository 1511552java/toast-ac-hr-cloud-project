package com.toast.common.response;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe
 */
public enum ResultCode implements IResultCode{
    SUCCESS(200, "操作成功"),
    FAILURE(400, "业务异常"),
    UN_AUTHORIZED(401, "请求未授权"),
    NOT_FOUND(404, "请求地址未找到"),
    METHOD_NOT_SUPPORTED(405, "不支持当前请求方法"),
    MEDIA_TYPE_NOT_SUPPORTED(415, "不支持当前媒体类"),
    REQ_REJECT(403, "请求被拒绝"),
    INTERNAL_SERVER_ERROR(500,"服务器异常"),
    ;


    final int code;
    final String message;
    @Override
    public int getCode() {
        return this.code;
    }
    @Override
    public String getMessage() {
        return this.message;
    }

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
