package com.example.springboot2.controller;

import com.example.springboot2.ApiResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunql
 * @description controller层
 * @date 2020/12/29 15:30
 */
@RestController
@RequestMapping(value = "/hello")
public class TestController {

    @RequestMapping(value = "/test")
    public String test(){
        Map<String, Integer> tt = new HashMap<>();

        tt.put("helllo",1);
        return ApiResult.getApiResult(0, tt, new String[]{"11", "22"});
//        return "index";
    }
//    class User {
//        private int a;
//        User(int a) {
//            this.a = a;
//        }
//    }

}