/**
 * function: 用于处理登录请求
 * author: suhsbeba
 * date: 2024/7/7 15:07
 */
package com.example.springboot2.controller;

import com.auth0.jwt.JWT;
import com.example.springboot2.Dao.StudentMessageMapper;
import com.example.springboot2.Exception.ExceptionCodeMsg;
import com.example.springboot2.Exception.ServiceException;
import com.example.springboot2.Utils.Result;
import com.example.springboot2.Utils.TokenUtils;
import com.example.springboot2.entity.Student;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;


//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/loginController")
public class loginController {
    @Resource
    private StudentMessageMapper studentMessageMapper;
    // todo 邮箱发送验证码
//    @Resource
//    private JavaMailSender javaMailSender;
//    @PostMapping("/EmailSend")
//    public Result sendEmail(@RequestBody Login login) {
//        code = Integer.toString(new Random().nextInt(900000) + 100000);
//
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setSubject("**注册邮箱验证码");
//        message.setText("尊敬的用户" + login.getEmail() + ": \n\n您好!\n\n您的校验验证码为: " + code );
//        message.setTo(login.getEmail());
//        try {
//            // 对方看到的发送人（发件人的邮箱，根据实际业务进行修改，一般填写的是企业邮箱）
//            message.setFrom(new InternetAddress(MimeUtility.encodeText("潘志程") + "<1991016977@qq.com>").toString());
//            // 发送邮件
//            javaMailSender.send(message);
//            //可以进行加密
//            return Result.success(code);
//        } catch (Exception e) {
//
//            return Result.error();
//
//        }
//
//    }
    // todo 邮箱登录
//    @PostMapping("/EmailLogin")
//    public Result EmailLogin(@RequestBody Login login) throws IOException {
//        System.out.println(login.toString());
//        if(code.equals(login.getCode()))
//            loginDao.EmailLogin(login);
//        else return Result.error("验证码错误");
//        return Result.success();
//
//    }
    @PostMapping("/studentCheck")
    public Result loginCheck(@RequestBody Student student) throws IOException {
        List<Student> students = studentMessageMapper.selectList(null);

//        System.out.println(login.toString());
//        Login result = loginDao.loginCheck(login);
////        System.out.println(result.toString());
//        if(result != null) {
//            String token = TokenUtils.genToken(result.getUser_name(), result.getUser_pwd());
//            Login data = new Login();
//            data.setIsadmin(result.getIsadmin());
//            data.setToken(token);
//            data.setTruth_name(result.getTruth_name());
//            return Result.success(data);
//        }
//        else {
//            return Result.error("登陆失败，请检查用户名和密码");
//        }
        return Result.success(students);
    }
//    @PostMapping("/teacherCheck")
//    public Result teacherCheck(HttpServletRequest request) throws IOException {
//        String username = JWT.decode(request.getHeader("Authorization").substring(7)).getAudience().get(0);
//        Login login = loginDao.getIsAdmin(username);
//        if(login.getIsadmin().equals(0)) {
//            throw new ServiceException(ExceptionCodeMsg.LOGIN_TOKEN_INVALID);
//        }
//        else {
//            return Result.success("身份验证成功");
//        }
////        return Result.success();
//    }
//
//    @PostMapping("/studentCheck")
//    public Result studentCheck(HttpServletRequest request) throws IOException {
//        String username = JWT.decode(request.getHeader("Authorization").substring(7)).getAudience().get(0);
//        Login login = loginDao.getIsAdmin(username);
//        if(login.getIsadmin().equals(0)) {
//            return Result.success("身份验证成功");
//        }
//        else {
//            throw new ServiceException(ExceptionCodeMsg.LOGIN_TOKEN_INVALID);
//        }
////        return Result.success();
//    }
}