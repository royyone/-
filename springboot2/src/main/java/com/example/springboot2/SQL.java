package com.example.springboot2;

import java.sql.*;

public class SQL {
    public static String url = "jdbc:mysql://localhost:3306/_Project";
    public static String user = "root";
    public static String password = "root";
    public static ResultSet op(String sql) throws SQLException {

        Connection conn = DriverManager.getConnection(url, user, password);
        Statement stmt = null;
        ResultSet rs = null;
        stmt = conn.createStatement();
        rs = stmt.executeQuery(sql);
        return rs;
    }
}
