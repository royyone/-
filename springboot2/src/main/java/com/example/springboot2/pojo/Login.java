/**
 * function:
 * author: suhsbeba
 * date: 2024/2/12 15:32
 */
package com.example.springboot2.pojo;

import lombok.Data;

@Data
public class Login {
    private Integer user_id;
    private String user_name;
    private String user_pwd;
    private String token;
    private String truth_name;
    private Integer isadmin;
}
