package com.toast.jwt.code;

import javax.servlet.http.HttpServletResponse;

/**
 * @author 土司先生
 * @time 2023/3/23
 * @describe 定义一个枚举类
 */
public enum JWTResponseCode {
    SUCCESS_CODE(HttpServletResponse.SC_OK, "Token数据正确，服务正常访问！"),
    TOKEN_TIMEOUT_CODE(HttpServletResponse.SC_BAD_REQUEST, "Token信息已经失效，需要重新申请！"),
    NO_AUTH_CODE(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "没有找到匹配的Token信息，无法进行服务访问！");
    private int code;
    private String result;
    private JWTResponseCode(int code, String result) {
        this.code = code;
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public String getResult() {
        return result;
    }

    public String toJSON() {
        return "{\'code\':" + this.code + ",\'result\': '" + this.result + "'}";
    }
}