package com.example.springboot2.mapper;

import com.example.springboot2.pojo.Game;

import java.sql.Date;
import java.util.List;

public interface gameMapper {
    Integer insertGame(String game_type,
                       String game_level,
                       Date game_date,
                       String game_name,
                       String game_author
                       );
    Integer deleteGame(Integer game_id);
    List<Game> selectGame();
}
