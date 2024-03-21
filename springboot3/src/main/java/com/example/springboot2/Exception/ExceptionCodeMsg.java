package com.example.springboot2.Exception;

public enum ExceptionCodeMsg {
    // todo 各种错误状态码和信息
    LOGIN_NO_TOKEN(401, "无token，请重新登录"),
    LOGIN_TOKEN_INVALID(401, "token验证失败，请重新登录"),
    LOGIN_NO_USERNAME(401, "用户不存在，请重新登录");
    ;



    private Integer code;
    private String msg;
    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
    ExceptionCodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
