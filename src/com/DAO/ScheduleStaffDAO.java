/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Entity.Schedule_Staff;
import com.Utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ScheduleStaffDAO implements myInterFace<Schedule_Staff, String> {

    @Override
    public void insert(Schedule_Staff entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Schedule_Staff entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean checkID(Schedule_Staff entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void find(Schedule_Staff entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Schedule_Staff selectById(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Schedule_Staff> selectAll() {
        final String sql = "Select * from Schedule_Staff ";
        return selectBySql(sql);
    }

    @Override
    public List<Schedule_Staff> selectBySql(String sql, Object... args) {
        List<Schedule_Staff> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Schedule_Staff entity = new Schedule_Staff();
                entity.setID_Schedule(rs.getInt("ID_Schedule"));
                entity.setID_Staff(rs.getString("ID_Staff"));
                entity.setWork_Date(rs.getDate("Work_Date"));
                entity.setDay_Of_Week(rs.getString("Day_Of_Week"));
                entity.setStart_Time(rs.getTime("Start_Time"));
                entity.setEnd_Time(rs.getTime("End_Time"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;

    }


    public List<Schedule_Staff> selectSchedule(String id) {
        final String sql = "Select * from Schedule_Staff where ID_Staff = ?";
        return selectBySql(sql,id);
    }
}
