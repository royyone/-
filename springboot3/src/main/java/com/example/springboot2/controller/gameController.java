/**
 * function:
 * author: suhsbeba
 * date: 2024/2/12 13:01
 */
package com.example.springboot2.controller;

import com.example.springboot2.Dao.gameDao;
import com.example.springboot2.Result;
import com.example.springboot2.pojo.Certificate;
import com.example.springboot2.pojo.Game;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/gameController")
public class gameController {
    @CrossOrigin(origins = "http://localhost:8080")
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
    @CrossOrigin(origins = "http://localhost:8080")
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
    @CrossOrigin(origins = "http://localhost:8080")
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
    @PostMapping("/gameLock")
    public Result gameLock(@RequestBody Game game) throws IOException {
        Integer result = gameDao.updateStatusById(game.getGame_id());
        if(result.equals(1)) {
            return Result.success();
        }
        else {
            return Result.error("gameController/gameSelect BUG！！！");
        }
    }
}

