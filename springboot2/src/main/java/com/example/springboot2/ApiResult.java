package com.example.springboot2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult {
    private int code;
    private Object data;
    private String message;

    public ApiResult(int code, Object data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    // 省略getter和setter方法

    public static String getApiResult(int code, Object data, String[] msg) {
        Map<String, Object> output = new HashMap<>();
        output.put("code", code);
        output.put("data", data);
        output.put("message", msg[code]);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(output);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}