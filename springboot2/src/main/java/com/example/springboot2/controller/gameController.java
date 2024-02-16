/**
 * function:
 * author: suhsbeba
 * date: 2024/2/12 13:01
 */
package com.example.springboot2.controller;

import com.example.springboot2.Dao.gameDao;
import com.example.springboot2.Result;
import com.example.springboot2.SQL;
import com.example.springboot2.pojo.Game;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/gameController")
public class gameController {
    @PostMapping("/gameInsert")
    public Result insertData(@RequestBody Game game) throws IOException {
        Integer res = gameDao.insertGame(game);
        System.out.println(game.toString());
        if(res.equals(1)) {
            return Result.success();
        }
        else {
            return Result.error("gameController/gameInsert BUG！！！");
        }
    }

    @PostMapping("/gameDelete")
    public Result deleteData(@RequestBody Game game) throws IOException {
        Integer res = gameDao.deleteGame(game);
        if(res.equals(1)) {
            return Result.success();
        }
        else {
            return Result.error("gameController/gameDelete BUG！！！");
        }
    }

    @PostMapping("/gameSelect")
    public Result selectData(@RequestBody Game game) throws IOException {
        List<Game> res = gameDao.selectGame();
        if(res.isEmpty()) {
            return Result.error("gameController/gameSelect BUG！！！");
        }
        else {
            return Result.success(res);
        }
    }
}

