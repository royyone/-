package com.example.springboot2.controller;

import com.example.springboot2.ApiResult;
import com.example.springboot2.SQL;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.sql.*;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/loginController")
public class loginController {
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/loginCheck")
    public String loginCheck(@RequestParam("username") String username,
                                             @RequestParam("password") String password) throws SQLException {
        int code = 0;
        Map<String, String> data = new HashMap<>();
        String msg[] = new String[]{"登录失败", "登录成功"};

        ResultSet res = SQL.op("select * from users where user_name = '"+username+"' and user_pwd = '"+password+"'");
        if(res.next()) code = 1;
        else code = 0;
        return ApiResult.getApiResult(code, data, msg);
    }
}