package com.example.springboot2;

import java.sql.*;

public class SQL {
    public static ResultSet op(String sql) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/_Project";
        Connection conn = DriverManager.getConnection(url, "root", "root");
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        return rs;
    }
}
