package com.example.springboot2.controller;

import com.example.springboot2.ApiResult;
import com.example.springboot2.SQL;
import org.springframework.web.bind.annotation.*;


import java.sql.*;
import java.util.HashMap;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/loginController")
public class loginController {
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/loginCheck")
    public String loginCheck(@RequestBody Login login) throws SQLException {
        int code = 0;
        Map<String, String> data = new HashMap<>();
        String msg[] = new String[]{"登录失败", "登录成功"};

        ResultSet res = SQL.op("select * from users where user_name = '"+login.getUsername()+"' and user_pwd = '"+login.getPassword()+"'");
        if(res.next()) code = 1;
        else code = 0;
        System.out.println(login.getUsername() + "\n" + login.getPassword());
        return ApiResult.getApiResult(code, data, msg);
    }
}
class Login {


    private String username;
    private String password;
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



}