/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Entity.Teacher;
import com.Utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duong Minh Binh
 */
public class Teacher_DAO implements myInterFace<Teacher, String> {

    private final String SELECT_BY_ID_SQL = "SELECT * FROM Teacher where ID_Teacher = ?;";
    private final String SELECT_ALL_SQL = "SELECT * FROM Teacher;";

    @Override
    public void insert(Teacher entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Teacher entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        return;
    }

    @Override
    public boolean checkID(Teacher entity) {
        return false;
    }

    @Override
    public void find(Teacher entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Teacher selectById(String id) {
        List<Teacher> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Teacher> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Teacher> selectBySql(String sql, Object... args) {
        List<Teacher> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getString("ID_Teacher"), rs.getString("First_Name"), rs.getString("Middle_Name"),
                        rs.getString("Last_Name"), rs.getString("Email"), rs.getString("Phone_Number"),
                        rs.getString("Level_Teacher"), rs.getString("Address_Teacher"), rs.getString("Avatar"),
                        rs.getString("Note"), rs.getBoolean("Gender"), rs.getBoolean("Status_Teacher"),
                        rs.getInt("Date_Of_Birth"), rs.getInt("Month_Of_Birth"), rs.getInt("Year_Of_Birth"),
                        rs.getDate("Start_Date"));
                list.add(teacher);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.getMessage();
        }
        return list;
    }

    public boolean checkIDTeacher(String keyword) {
        try {
            String CHECK_ID_SQL = "SELECT COUNT(ID_Teacher) FROM Teacher where ID_Teacher = ? OR Last_Name = ?;";
            ResultSet rs = JDBCHelper.executeQuery(CHECK_ID_SQL, keyword, keyword);
            while (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    return true;
                }
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }
//    java.sql.Date sqlDate;
//    public Date convertDate(java.util.Date date) {
//        sqlDate = new java.sql.Date(date.getTime());
//        return sqlDate;
//    }
}
