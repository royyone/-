/**
 * function:
 * author: suhsbeba
 * date: 2024/2/12 14:34
 */
package com.example.springboot2.pojo;

import lombok.Data;

import java.sql.Date;

@Data
public class Game {
    private Integer game_id;
    private String game_type;
    private String game_level;
    private Date game_date;
    private String game_name;
    private String game_author;
    private Integer game_state;
}