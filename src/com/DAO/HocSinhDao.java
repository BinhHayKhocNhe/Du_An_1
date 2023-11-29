
package com.DAO;

import com.Entity.Student;
import com.Utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HocSinhDao implements myInterFace<Object, Object>{
 public static List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM Student";

        try (ResultSet rs = JDBCHelper.executeQuery(sql)) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("ID_Student"),
                        rs.getString("First_Name"),
                        rs.getString("Middle_Name"),
                        rs.getString("Last_Name"),
                        rs.getString("Address_Student"),
                        rs.getString("Avatar"),
                        rs.getString("Note"),
                        rs.getBoolean("Gender"),
                        rs.getBoolean("Status_Student"),
                        rs.getInt("Date_Of_Birth"),
                        rs.getInt("Month_Of_Birth"),
                        rs.getInt("Year_Of_Birth")
                );

                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }
 
 public static List<Student> searchStudentsByIdAndClass(String studentId, String className) {
        List<Student> searchResults = new ArrayList<>();
        String sql = "SELECT * FROM Student WHERE ID_Student = ? AND ID_Class = ?";

        try (ResultSet rs = JDBCHelper.prepareStatement(sql, studentId, className).executeQuery()) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("ID_Student"),
                        rs.getString("First_Name"),
                        rs.getString("Middle_Name"),
                        rs.getString("Last_Name"),
                        rs.getString("Address_Student"),
                        rs.getString("Avatar"),
                        rs.getString("Note"),
                        rs.getBoolean("Gender"),
                        rs.getBoolean("Status_Student"),
                        rs.getInt("Date_Of_Birth"),
                        rs.getInt("Month_Of_Birth"),
                        rs.getInt("Year_Of_Birth")
                );

                searchResults.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return searchResults;
    }

    @Override
    public void insert(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean checkID(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void find(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Object selectById(Object id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Object> selectBySql(String sql, Object... args) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
