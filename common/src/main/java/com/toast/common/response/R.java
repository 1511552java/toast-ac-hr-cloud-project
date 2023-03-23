package com.toast.common.response;

import java.io.Serializable;

/**
 * @author 土司先生
 * @time 2023/3/22
 * @describe 服务节点之间传输结构响应体
 */
public class R<T> implements Serializable {
    private T data;
    private int code;
    private String message;
    private boolean success;

    public R() {}

    private R(IResultCode code) {
        this(null, code);
    }
    private R(T data, IResultCode code) {
        this(data, code.getCode(), code.getMessage());
    }
    private R(IResultCode code, String message) {
        this(null, code, message);
    }
    private R(T data, IResultCode code, String message) {
        this(data, code.getCode(), message);
    }

    private R(T data, int code, String message) {
        this.data = data;
        this.code = code;
        this.message = message;
        this.success = ResultCode.SUCCESS.code == this.code;
    }

    public static <T> R<T> data(T data) {
        return new R(data, ResultCode.SUCCESS);
    }
    public static <T> R<T> data(T data, IResultCode code) {
        return new R(data, code.getCode(), code.getMessage());
    }
    public static <T> R<T> data(T data, IResultCode code, String message) {
        return new R(data, code.getCode(), message);
    }

    public static <T> R<T> success(String message) {
        return new R(ResultCode.SUCCESS, message);
    }

    public static <T> R<T> success(IResultCode resultCode) {
        return new R(resultCode);
    }

    public static <T> R<T> success(IResultCode resultCode, String message) {
        return new R(resultCode, message);
    }

    public static <T> R<T> fail(String message) {
        return new R(ResultCode.FAILURE, message);
    }

    public static <T> R<T> fail(int code, String message) {
        return new R((Object)null, code,  message);
    }

    public static <T> R<T> fail(IResultCode resultCode) {
        return new R(resultCode);
    }

    public static <T> R<T> fail(IResultCode resultCode, String message) {
        return new R(resultCode, message);
    }

    public static <T> R<T> status(boolean flag) {
        return flag ? success("操作成功") : fail("操作失败");
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "R{" +
                "data=" + data +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", success=" + success +
                '}';
    }
}
