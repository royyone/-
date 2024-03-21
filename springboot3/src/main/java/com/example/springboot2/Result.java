/**
 * function:
 * author: suhsbeba
 * date: 2023/11/24 12:09
 */
package com.example.springboot2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Result {
    private static final String CODE_SUCCESS = "200";
    private static final String CODE_DEFINE_ERROR = "501"; // 自定义错误

    private String code;
    private String msg;
    private Object data;
    public static Result success() {
        return new Result(CODE_SUCCESS, "请求成功", null);
    }
    public static Result success(Object data) {
        return new Result(CODE_SUCCESS, "请求成功", data);
    }
    public static Result error(String msg) {
        return new Result(CODE_DEFINE_ERROR, msg, null);
    }
    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }
    public static Result error() {
        return new Result(CODE_DEFINE_ERROR, "系统错误", null);
    }


}