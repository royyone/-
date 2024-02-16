/**
 * function:
 * author: suhsbeba
 * date: 2024/2/13 13:56
 */
package com.example.springboot2.pojo;

import lombok.Data;

@Data
public class Certificate {
    private Integer game_id;
    private Integer award_id;
    private String match_name;
    private String school_name;
    private String college_Name;
    private String stu_name;
    private String team_name;
    private String award;
    private Integer status;
    private String file_path;
    private String adviser;
}

