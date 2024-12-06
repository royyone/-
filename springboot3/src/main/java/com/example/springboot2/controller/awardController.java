package com.example.springboot2.controller;

import com.example.springboot2.Dao.awardDao;

import com.example.springboot2.Utils.Result;
import com.example.springboot2.pojo.Certificate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8080")
//@CrossOrigin
@RestController
@RequestMapping("/awardController")
public class awardController {

    @PostMapping("/awardSelect")
    public Result updateData(@RequestBody Certificate certificate) throws IOException {
        List<Certificate> data = awardDao.selectAward(certificate);
        return Result.success(data);
    }
    @PostMapping("/stuSelect")
    public Result stuData(@RequestBody Certificate certificate) throws IOException {
        List<Certificate> data = awardDao.stuSelect(certificate);
//        System.out.println(certificate.toString());
        System.out.println(data.toString());
        return Result.success(data);
    }


}
