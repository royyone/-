package com.example.springboot2.controller;

import com.example.springboot2.Dao.loginDao;
import com.example.springboot2.Result;
import com.example.springboot2.Utils.TokenUtils;
import com.example.springboot2.pojo.Login;
import org.springframework.web.bind.annotation.*;


import javax.xml.transform.Source;
import java.io.IOException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/loginController")
public class loginController {
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/loginCheck")
    public Result loginCheck(@RequestBody Login login) throws IOException {
//        System.out.println(login.toString());
        Login result = loginDao.loginCheck(login);
//        System.out.println(result.toString());
        if(result.getUser_id()!=null) {
            String token = TokenUtils.genToken(result.getUser_name(), result.getUser_pwd());
            Login data = new Login();
            data.setIsadmin(result.getIsadmin());
            data.setToken(token);
            data.setTruth_name(result.getTruth_name());
            return Result.success(data);
        }
        else {
            return Result.error("请检查用户名和密码！！");
        }
//        return Result.success();
    }
}