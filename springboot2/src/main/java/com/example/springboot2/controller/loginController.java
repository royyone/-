package com.example.springboot2.controller;

import com.auth0.jwt.JWT;
import com.example.springboot2.Dao.loginDao;
import com.example.springboot2.Exception.ExceptionCodeMsg;
import com.example.springboot2.Exception.ServiceException;
import com.example.springboot2.Result;
import com.example.springboot2.Utils.TokenUtils;
import com.example.springboot2.pojo.Login;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;


import javax.xml.transform.Source;
import java.io.IOException;
import java.rmi.ServerException;
import java.rmi.server.ExportException;

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
        if(result != null) {
            String token = TokenUtils.genToken(result.getUser_name(), result.getUser_pwd());
            Login data = new Login();
            data.setIsadmin(result.getIsadmin());
            data.setToken(token);
            data.setTruth_name(result.getTruth_name());
            return Result.success(data);
        }
        else {
            return Result.error("登陆失败，请检查用户名和密码");
        }
//        return Result.success();
    }
    @PostMapping("/teacherCheck")
    public Result teacherCheck(HttpServletRequest request) throws IOException {
        String username = JWT.decode(request.getHeader("Authorization").substring(7)).getAudience().get(0);
        Login login = loginDao.getIsAdmin(username);
        if(login.getIsadmin().equals(0)) {
            throw new ServiceException(ExceptionCodeMsg.LOGIN_TOKEN_INVALID);
        }
        else {
            return Result.success("身份验证成功");
        }
//        return Result.success();
    }

    @PostMapping("/studentCheck")
    public Result studentCheck(HttpServletRequest request) throws IOException {
        String username = JWT.decode(request.getHeader("Authorization").substring(7)).getAudience().get(0);
        Login login = loginDao.getIsAdmin(username);
        if(login.getIsadmin().equals(0)) {
            return Result.success("身份验证成功");
        }
        else {
            throw new ServiceException(ExceptionCodeMsg.LOGIN_TOKEN_INVALID);
        }
//        return Result.success();
    }
}