package com.example.springboot2.controller;

import com.example.springboot2.Dao.loginDao;
import com.example.springboot2.Result;
import com.example.springboot2.pojo.Login;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/loginController")
public class loginController {
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/loginCheck")
    public Result loginCheck(@RequestBody Login login) throws IOException {
        Integer result = loginDao.loginCheck(login);
        if(result.equals(1)) {
            return Result.success();
        }
        else {
            return Result.error("请检查用户名和密码！！");
        }
    }
}