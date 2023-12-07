/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Entity.Courses;
import com.Utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duong Minh Binh
 */
public class CoursesDAO implements myInterFace<Courses, String> {

    private final String INSERT_SQL = "INSERT INTO Course(ID_Course, Course_Name, Year, Note ) VALUES (?, ?, ?, ?);";
    private final String UPDATE_SQL = "UPDATE Course SET Course_Name = ?, Year=?, Note=? WHERE ID_Course = ?;";
    private final String SELECT_ALL_SQL = "SELECT * FROM Course;";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Course where ID_Course = ? ;";
    private final String DELETE_SQL = "DELETE FROM Course WHERE ID_Course=?";

    @Override
    public void insert(Courses entity) {
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getID_Course(), entity.getCourse_Name(),
                entity.getYear(), entity.getNote());
    }

    @Override
    public void update(Courses entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL, entity.getCourse_Name(),
                entity.getYear(), entity.getNote(), entity.getID_Course());
    }

    @Override
    public void delete(String id) {
        JDBCHelper.executeUpdate(DELETE_SQL, id);
    }

    @Override
    public boolean checkID(Courses entity) {
        String sql = "SELECT COUNT(ID_Course) FROM Course WHERE ID_Course = ?";
        ResultSet rs = JDBCHelper.executeQuery(sql, entity.getID_Course());
        try {
            while (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return true;
    }

    @Override
    public void find(Courses entity) {
    }

    @Override
    public Courses selectById(String id) {
        List<Courses> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Courses> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Courses> selectBySql(String sql, Object... args) {
        List<Courses> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Courses courses = new Courses();
                courses.setID_Course(rs.getString("ID_Course"));
                courses.setCourse_Name(rs.getString("Course_Name"));
                courses.setYear(rs.getInt("Year"));
                courses.setNote(rs.getString("Note"));
                list.add(courses);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.getMessage();
        }
        return list;
    }

    public List<Courses> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Course where ID_Course LIKE ? OR Course_Name LIKE ?;";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    public List<String> selectAllIDCourses() {
        final String sql = "SELECT ID_Course FROM Course;";
        List<String> list = new ArrayList<>();
        try {
            ResultSet resultSet = JDBCHelper.executeQuery(sql);
            while (resultSet.next()) {
                list.add(resultSet.getString("ID_Course"));
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return list;
    }
}
