/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Utils.JDBCHelper;
import com.Entity.Staff;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class StaffDAO implements myInterFace<Staff, String> {

    @Override
    public void insert(Staff entity) {

    }

    @Override
    public void update(Staff entity) {

    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean checkID(Staff entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void find(Staff entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Staff selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Staff> selectAll() {
        String sql = "select * from Staff ";
        return selectBySql(sql);
    }

    @Override
    public List<Staff> selectBySql(String sql, Object... args) {
        List<Staff> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.executeQuery(sql, args);
                while (rs.next()) {
                    Staff entity = new Staff();
                    entity.setID_Staff(rs.getString("ID_Staff"));
                    entity.setFirst_Name(rs.getString("First_Name") + " "
                            + rs.getString("Middle_Name") + " "
                            + rs.getString("Last_Name"));
                    entity.setEmail(rs.getString("Email"));
                    entity.setGender(rs.getBoolean("Gender"));
                    entity.setStatus_Staff(rs.getBoolean("Status_Staff"));
                    entity.setStart_Date(rs.getDate("Start_Date"));
                    entity.setPosition(rs.getString("Position"));
                    entity.setNote(rs.getString("Note"));

                    list.add(entity);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }
    public List<Staff> selectByKeyword(String keyword){
        String sql="SELECT * FROM Staff WHERE ID_Staff LIKE ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
}
