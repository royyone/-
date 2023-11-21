package com.example.springboot2.controller;

import com.example.springboot2.ApiResult;
import com.example.springboot2.SQL;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/awardController")
public class awardController {

    @PostMapping("/awardSelect")
    public String updateData() throws SQLException {
        ResultSet res = SQL.op("select * from awardresult");
        List<Map<String, Object>> data = new ArrayList<>();
        // todo 数据封装返回前端。
        while (res.next()) {
            Map<String, Object> rowData = new HashMap<>();
            rowData.put("collegeName", res.getString("college_name"));
            rowData.put("teamName", res.getString("team_name"));
            rowData.put("stuName", res.getString("stu_name"));
            rowData.put("rkName", res.getString("award"));
            rowData.put("explainText", res.getString("comment"));
            rowData.put("status", res.getString("status"));
            rowData.put("ctPath", res.getString("file_path"));
            rowData.put("fla", false);
            data.add(rowData);
        }
        String[] msg = {"获奖信息查询成功", "获奖信息查询失败"};
        return ApiResult.getApiResult(0, data, msg);
    }

}
