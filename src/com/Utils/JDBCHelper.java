/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCHelper {

    private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

//    Thuận
//    private static String url = "jdbc:sqlserver://MSI:1433;databaseName=Du_An_1; encrypt = false;";
//    private static String username = "sa";
//    private static String password = "songlong";
//    Việt
//    private static String url = "jdbc:sqlserver://VIET-DESKTOP:1433;databaseName=Du_An_1;encrypt=false";
//    private static String username = "sa";
//    private static String password = "sa";
//    private static String url = "jdbc:sqlserver://VIET-DESKTOP:1433;databaseName=Du_An_1;encrypt=false";
//    private static String username = "sa";
//    private static String password = "sa";
//    Bình
    private static String url = "jdbc:sqlserver://LAPTOP-BD4VIBB3\\BINH:1433;databaseName=Du_An_1;encrypt=false";
    private static String username = "sa";
    private static String password = "123";

    static {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("Không thể khởi tạo driver!", ex);
        }
    }

    public static PreparedStatement prepareStatement(String sql, Object... args) throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        PreparedStatement pstmt = null;
        if (sql.trim().startsWith("{")) {
            pstmt = connection.prepareCall(sql);
        } else {
            pstmt = connection.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            pstmt.setObject(i + 1, args[i]);
        }
        return pstmt;
    }

    public static void executeUpdate(String sql, Object... args) {
        try (PreparedStatement stmt = prepareStatement(sql, args)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement stmt = prepareStatement(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi khi thực hiện truy vấn!", e);
        }
    }

    public static Object value(String sql, Object... args) {
        try (ResultSet rs = JDBCHelper.executeQuery(sql, args)) {
            if (rs.next()) {
                return rs.getObject(1);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy giá trị!", e);
        }
    }
}
