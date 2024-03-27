/**
 * function:
 * author: suhsbeba
 * date: 2024/2/20 15:13
 */
package com.example.springboot2.config.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springboot2.Dao.loginDao;
import com.example.springboot2.Exception.ExceptionCodeMsg;
import com.example.springboot2.Exception.ServiceException;
import com.example.springboot2.pojo.Login;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;


// 拦截器
public class JwtInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(ServiceException.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        return false;
        if("OPTIONS".equals(request.getMethod().toUpperCase())) {
            return true;
        }
        System.out.println(request.getHeader("Authorization"));
        String token = request.getHeader("Authorization").substring(7);

//        System.out.println(token);
        // 如果不是映射到方法直接通过
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        // 执行认证
        if (StrUtil.isBlank(token)) {
            throw new ServiceException(ExceptionCodeMsg.LOGIN_NO_TOKEN);
        }
        // 获取 token 中的 username
        String username;
        try {
            username = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new ServiceException(ExceptionCodeMsg.LOGIN_TOKEN_INVALID);
        }
//        // 根据token中的username查询数据库
        Login login = loginDao.getUsername(username);
        if (login == null) {
            throw new ServiceException(ExceptionCodeMsg.LOGIN_NO_USERNAME);
        }
        // 用户密码加签验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(login.getUser_pwd())).build();
        try {
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException(ExceptionCodeMsg.LOGIN_TOKEN_INVALID);
        }
        return true;
    }
}
