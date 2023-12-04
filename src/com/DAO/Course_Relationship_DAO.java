/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Entity.Course_Relationship;
import com.Utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;

/**
 *
 * @author Duong Minh Binh
 */
public class Course_Relationship_DAO implements myInterFace<Course_Relationship, String> {

    private final String INSERT_SQL = "INSERT INTO Course_Relationship (ID_Course, ID_Class, ID_Student, ID_Teacher)\n"
            + "VALUES (?, ?, ?, ?);";
    private final String SELECT_ALL_SQL = "SELECT * FROM Course_Relationship;";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Course_Relationship WHERE ID_Course = ? "
            + "AND ID_Class = ? AND ID_Student = ? AND ID_Teacher = ?";
    private final String DELETE_SQL = "DELETE FROM Course_Relationship where ID_Course = ? "
            + "AND ID_Class = ? AND ID_Student = ? AND ID_Teacher = ?";

    public void insert(Course_Relationship entity) {
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getID_Course(), entity.getID_Class(),
                entity.getID_Student(), entity.getID_Teacher());
    }

    @Override
    public void update(Course_Relationship entity) {
        return;
    }

    @Override
    public void delete(String id) {
        return;
    }

    @Override
    public boolean checkID(Course_Relationship entity) {
        final String SQL = "SELECT 1 FROM Course_Relationship WHERE ID_Course = ? AND ID_Class = ? AND ID_Student = ? AND ID_Teacher = ?;";
        try {
            ResultSet rs = JDBCHelper.executeQuery(SQL, entity.getID_Course(), entity.getID_Class(),
                    entity.getID_Student(), entity.getID_Teacher());
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void find(Course_Relationship entity) {
        return;
    }

    @Override
    public Course_Relationship selectById(String id) {
        return null;
    }

    @Override
    public List<Course_Relationship> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Course_Relationship> selectBySql(String sql, Object... args) {
        List<Course_Relationship> course_Relationship = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Course_Relationship list = new Course_Relationship();
                list.setID_Class(rs.getString("ID_Class"));
                list.setID_Student(rs.getString("ID_Student"));
                list.setID_Course(rs.getString("ID_Course"));
                list.setID_Teacher(rs.getString("ID_Teacher"));
                course_Relationship.add(list);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return course_Relationship;
    }

    public List<Course_Relationship> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Course_Relationship where ID_Course LIKE ? OR ID_Class LIKE ?;";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    public Course_Relationship selectByIdCourse_Relationship(String... arg) {
        List<Course_Relationship> list = selectBySql(SELECT_BY_ID_SQL, arg);
        return list.isEmpty() ? null : list.get(0);
    }

    public void deleteCourse_Relationship(Course_Relationship entity) {
        JDBCHelper.executeUpdate(DELETE_SQL, entity.getID_Course(), entity.getID_Class(),
                entity.getID_Student(), entity.getID_Teacher());
    }
}
