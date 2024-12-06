/**
 * function:
 * author: suhsbeba
 * date: 2024/2/13 13:56
 */
package com.example.springboot2.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class Certificate {
    private Integer game_id;
    private Integer award_id;
    private String school_name;
    private String college_name;
    private String stu_name;
    private String team_name;
    private String award;
    private Integer status;
    private String file_path;
    private String adviser;
    private String game_type;
    private String game_level;
    private Date game_date;
    private String game_name;
    private String game_author;
    private Integer game_status;
    private String template_type;
    private String award_team;
    private String work_name;
}

