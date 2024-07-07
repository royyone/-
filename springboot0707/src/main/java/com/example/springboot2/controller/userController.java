///**
// * function:
// * author: suhsbeba
// * date: 2024/3/7 13:40
// */
//package com.example.springboot2.controller;
//
//import com.example.springboot2.Dao.userDao;
//import com.example.springboot2.Utils.Result;
//import com.example.springboot2.pojo.Login;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//
////@CrossOrigin(origins = "http://localhost:8080")
////@CrossOrigin
//@RestController
//@RequestMapping("/userController")
//public class userController {
//    @PostMapping("/userCheck")
//    public Result userCheck(@RequestBody Login login) throws IOException {
//        if(userDao.selectUser(login).equals(0)) {
//            return Result.success();
//        }
//        else {
//            return Result.error("用户已存在");
//        }
//    }
//    @PostMapping("/userCreate")
//    public Result userCreate(@RequestBody Login login) throws IOException {
//        if(userDao.insertUser(login).equals(1)) {
//            return Result.success();
//        }
//        else {
//            return Result.error("添加失败，请联系管理员");
//        }
//    }
//}
