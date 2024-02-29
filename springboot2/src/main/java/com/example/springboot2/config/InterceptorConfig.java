/**
 * function:
 * author: suhsbeba
 * date: 2024/2/20 15:14
 */
package com.example.springboot2.config;

import com.example.springboot2.config.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")  // 拦截所有请求，通过判断token是否合法来决定是否需要登录
                .excludePathPatterns("/loginController/loginCheck","/fileController/preview/**","/fileController/downloadFile/**"
                ,"/fileController/downloadTemplate");
//                .excludePathPatterns("/user/login", "/user/register", "/**/export", "/**/import","/file/**");//排除这些，这些不用验证，直接登录
    }
    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }

}
