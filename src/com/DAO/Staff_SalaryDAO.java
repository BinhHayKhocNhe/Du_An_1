/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Entity.Staff_Salary;
import com.Utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Staff_SalaryDAO implements myInterFace<Staff_Salary, String> {

    @Override
    public void insert(Staff_Salary entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Staff_Salary entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean checkID(Staff_Salary entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void find(Staff_Salary entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Staff_Salary selectById(String id) {
        String sql = "Select * from Staff_Salary where ID_Staff like ?;";
        List<Staff_Salary> list = selectBySql(sql,"%" + id + "%");
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Staff_Salary> selectAll() {
        String sql = "select * from Staff_Salary ";
        return selectBySql(sql);
    }

    @Override
    public List<Staff_Salary> selectBySql(String sql, Object... args) {
        List<Staff_Salary> listSalary = new ArrayList();

        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Staff_Salary staff_Salary = new Staff_Salary();
                staff_Salary.setID_Staff(rs.getString("ID_Staff"));
                staff_Salary.setID_Staff_Salary(rs.getString("ID_Staff_Salary"));
                staff_Salary.setNumber_Of_Working_Days(rs.getString("Number_Of_Working_Days"));
                staff_Salary.setDaily_Wage(rs.getString("Daily_Wage"));
                staff_Salary.setNote(rs.getString("Note"));
                staff_Salary.setMonth(rs.getInt("Month"));
                staff_Salary.setYear(rs.getInt("Year"));
                listSalary.add(staff_Salary);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return listSalary;
    }

}
