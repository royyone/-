package org.suhsbeba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者：梁辰兴
 * 日期：2023/5/23
 * 功能：Hello控制器
 */
@RestController
public class demo1024 {
    @GetMapping("/hello")
    public String hello() {
        return "<h1 style='color: red; text-align: center'>Hello Spring Boot World~</h1>";
    }
}
