/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Entity.Health;
import com.Utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class HealthDAO implements myInterFace<Health, String> {

    private final String INSERT_SQL = "INSERT INTO Health(ID_Course, Course_Name, ID_Staff, ID_Student, Status, Height, Weight, Note ) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    @Override
    public void insert(Health entity) {
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getID_Course(), entity.getCourse_Name(),
                entity.getID_Staff(),entity.getID_Student(), entity.isStatus(),
                entity.getHeight(), entity.getWeight(), entity.getNote());
    }

    @Override
    public void update(Health entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean checkID(Health entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void find(Health entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Health selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Health> selectAll() {
        String sql = "select * from Health";
        return selectBySql(sql);
    }

    @Override
    public List<Health> selectBySql(String sql, Object... args) {
        List<Health> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Health entity = new Health();
                entity.setID_Course(rs.getString("ID_Course"));
                entity.setCourse_Name(rs.getString("Course_Name"));
                entity.setID_Staff(rs.getString("ID_Staff"));
                entity.setID_Student(rs.getString("ID_Student"));
                entity.setStatus(rs.getBoolean("Status"));
                entity.setWeight(rs.getInt("Weight"));
                entity.setHeight(rs.getInt("Height"));
                entity.setNote(rs.getString("Note"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

}
