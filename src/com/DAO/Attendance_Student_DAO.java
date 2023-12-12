/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Entity.Attendance_Student;
import com.Utils.JDBCHelper;
import com.Utils.Message;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duong Minh Binh
 */
public class Attendance_Student_DAO implements myInterFace<Attendance_Student, String> {

    private final String SELECT_ALL_SQL = "SELECT * FROM Attendance_Student;";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Attendance_Student WHERE ID_Attendance = ?;";
    private final String UPDATE_SQL = "UPDATE Attendance_Student SET Status_Attendance = ? WHERE ID_Attendance = ?;";

    @Override
    public void insert(Attendance_Student entity) {
        return;
    }

    @Override
    public void update(Attendance_Student entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL, entity.isStatus_Attendance(), entity.getID_Attendance());
    }

    @Override
    public void delete(String id) {
        return;
    }

    @Override
    public boolean checkID(Attendance_Student entity) {
        final String sql = "SELECT 1 FROM Attendance_Student WHERE ID_Attendance = ?;";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, entity.getID_Attendance());
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public void find(Attendance_Student entity) {
        return;
    }

    @Override
    public Attendance_Student selectById(String id) {
        List<Attendance_Student> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Attendance_Student> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Attendance_Student> selectBySql(String sql, Object... args) {
        List<Attendance_Student> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Attendance_Student attendance = new Attendance_Student();
                attendance.setID_Attendance(rs.getString("ID_Attendance"));
                attendance.setID_Student_Attendance(rs.getString("ID_Student_Attendance"));
                attendance.setID_Teacher_Attendance(rs.getString("ID_Teacher_Attendance"));
                attendance.setID_Class_Attendance(rs.getString("ID_Class_Attendance"));
                attendance.setID_Subject_Attendance(rs.getString("ID_Subject_Attendance"));
                attendance.setAttendance_Date(rs.getDate("Attendance_Date"));
                attendance.setNote(rs.getString("Note"));
                attendance.setStatus_Attendance(rs.getBoolean("Status_Attendance"));
                list.add(attendance);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Attendance_Student> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Attendance_Student WHERE ID_Attendance LIKE ?;";
        return this.selectBySql(sql, "%" + keyword + "%");
    }
}
