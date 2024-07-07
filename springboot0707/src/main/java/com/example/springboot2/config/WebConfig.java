/**
 * function:
 * author: suhsbeba
 * date: 2024/3/25 17:14
 */
package com.example.springboot2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    //配置跨域问题
    public void addCorsMappings(CorsRegistry registry) {
        //前端发送的请求路径中，后端中允许哪些路径可以被处理（简单来说：允许哪些RequestMapping的语句被执行）。/**表示：前端发送的所有请求路径，后端都可以执行


        registry.addMapping("/**") // 所有接口
            .allowCredentials(true) // 是否发送 Cookie
            .allowedOriginPatterns("*") // 支持域
            .allowedMethods(new String[]{"GET", "POST", "PUT", "OPTIONS"}) // 支持方法
            .allowedHeaders("*")
            .exposedHeaders("*");

    }
}