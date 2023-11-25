package com.example.springboot2.controller;

import com.example.springboot2.Result;
import com.example.springboot2.SQL;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/awardController")
public class awardController {

    @PostMapping("/awardSelect")
    public Result updateData() throws SQLException {
        List<Map<String, Object>> data = new ArrayList<>();
        Connection conn = DriverManager.getConnection(SQL.url, SQL.user, SQL.password);
        Statement stmt = null;
        stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery("select * from result");

        // todo 数据封装返回前端。
        while (res.next()) {
            Map<String, Object> rowData = new HashMap<>();
            rowData.put("id", res.getInt("id"));
            rowData.put("match_name", res.getString("match_name"));
            rowData.put("school_name", res.getString("school_name"));
            rowData.put("college_Name", res.getString("college_Name"));
            rowData.put("stu_name", res.getString("stu_name"));
            rowData.put("team_name", res.getString("team_name"));
            rowData.put("award", res.getString("award"));
            rowData.put("status", res.getInt("status"));
            rowData.put("file_path", res.getString("file_path"));
            rowData.put("adviser", res.getString("adviser"));
            rowData.put("isSelect", false);
            data.add(rowData);
        }
        String[] msg = {"获奖信息查询成功", "获奖信息查询失败"};
        stmt.close();
        conn.close();
        return Result.success(data);
    }

}
