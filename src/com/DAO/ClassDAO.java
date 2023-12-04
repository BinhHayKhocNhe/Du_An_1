package com.DAO;

import com.Utils.JDBCHelper;
import com.Entity.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements myInterFace<Class, String> {

    @Override
    public void insert(Class entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Class entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean checkID(Class entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void find(Class entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Class selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Class> selectAll() {
        String sql = "SELECT * FROM Class";
        return selectBySql(sql);
    }

    @Override
    public List<Class> selectBySql(String sql, Object... args) {
        List<Class> classList = new ArrayList<>();
        try (ResultSet rs = JDBCHelper.executeQuery(sql)) {
            while (rs.next()) {
                Class classEntity = new Class(
                        rs.getString("ID_Class"),
                        rs.getString("Class_Name"),
                        rs.getString("ID_Teacher"),
                        rs.getInt("Quantity"),
                        rs.getString("Note")
                );

                classList.add(classEntity);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classList;
    }
}
