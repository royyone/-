package org.suhsbeba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 作者：suhsbeba
 * 日期：2023/5/23
 * 功能：项目入口类
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        // 参数1：入口类示例；参数2：命令行参数
        SpringApplication.run(Application.class, args);
    }
}
