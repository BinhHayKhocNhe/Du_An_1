package com.DAO;

import com.Entity.Student;
import com.Utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;

public class StudentDAO implements myInterFace<Student, String> {

    private final String SELECT_BY_ID_SQL = "SELECT * FROM Student WHERE ID_Student = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM Student";
    private final String UPDATE_SQL = "UPDATE Student SET First_Name = ?, Middle_Name = ?, Last_Name = ?, "
            + "Gender = ?, Address_Student = ?, ID_Class=?, Status_Student = ?, Avatar = ?, Date_Of_Birth = ?, "
            + "Month_Of_Birth = ?, Year_Of_Birth = ?, Note = ? WHERE ID_Student = ?";
    private final String selectQuery = "	SELECT \n"
            + "    Student.*, \n"
            + "    Class.Class_Name\n"
            + "FROM \n"
            + "    Student\n"
            + "INNER JOIN \n"
            + "    Class ON Student.ID_Class = Class.ID_Class;"
            + "Month_Of_Birth = ?, Year_Of_Birth = ?, Note = ?";

    @Override
    public void insert(Student entity) {
        String sql = "INSERT INTO Student (ID_Student, First_Name, Middle_Name, Last_Name, Gender, Address_Student, "
                + "ID_Class, Status_Student, Avatar, Date_Of_Birth, Month_Of_Birth, Year_Of_Birth, Note) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        JDBCHelper.executeUpdate(sql,
                entity.getID_Student(), entity.getFirst_Name(), entity.getMiddle_Name(), entity.getLast_Name(),
                entity.isGender(), entity.getAddress_Student(), entity.getID_Class(), entity.isStatus_Student(), entity.getAvatar(),
                entity.getDate_Of_Birth(), entity.getMonth_Of_Birth(), entity.getYear_Of_Birth(), entity.getNote());
    }

    @Override
    public void update(Student entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL, entity.getFirst_Name(), entity.getMiddle_Name(), entity.getLast_Name(),
                entity.isGender(), entity.getAddress_Student(), entity.getID_Class(), entity.isStatus_Student(), entity.getAvatar(),
                entity.getDate_Of_Birth(), entity.getMonth_Of_Birth(), entity.getYear_Of_Birth(), entity.getNote(),
                entity.getID_Student());
    }

    @Override
    public void delete(String id) {
        return;
    }

    @Override
    public boolean checkID(Student entity) {
        return false;
    }

    @Override
    public void find(Student entity) {
        return;
    }

    @Override
    public Student selectById(String id) {
        List<Student> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Student> selectAll() {

        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Student> selectBySql(String sql, Object... args) {
        List<Student> studentList = new ArrayList<>();
        try (ResultSet rs = JDBCHelper.executeQuery(sql, args)) {
            while (rs.next()) {
                Student stu = new Student();
                stu.setID_Student(rs.getString("ID_Student"));
                stu.setFirst_Name(rs.getString("First_Name"));
                stu.setMiddle_Name(rs.getString("Middle_Name"));
                stu.setLast_Name(rs.getString("Last_Name"));
                stu.setGender(rs.getBoolean("Gender"));
                stu.setAddress_Student(rs.getString("Address_Student"));
                stu.setID_Class(rs.getString("ID_Class"));
                stu.setStatus_Student(rs.getBoolean("Status_Student"));
                stu.setAvatar(rs.getString("Avatar"));
                stu.setDate_Of_Birth(rs.getInt("Date_Of_Birth"));
                stu.setMonth_Of_Birth(rs.getInt("Month_Of_Birth"));
                stu.setYear_Of_Birth(rs.getInt("Year_Of_Birth"));
                stu.setNote(rs.getString("Note"));
//                stu.setClass_Name(rs.getString("Class_Name"));

                studentList.add(stu);
            }
        } catch (java.sql.SQLException ex) {

        }

        return studentList;
    }

    public List<Student> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Student WHERE ID_Student LIKE ? OR Last_Name LIKE ?;";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    public List<String> getidlclass() {
        return getUniqueColumnValues("ID_Class");
    }

    // Phương thức trợ giúp để lấy giá trị độc nhất từ một cột cụ thể
    private List<String> getUniqueColumnValues(String columnName) {
        List<String> values = new ArrayList<>();
        String sql = "SELECT DISTINCT " + columnName + " FROM Class";
        try (ResultSet resultSet = JDBCHelper.executeQuery(sql)) {
            while (resultSet.next()) {
                values.add(resultSet.getString(columnName));
            }
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
            // Handle the exception as needed
        }
        return values;
    }

    public List<Student> selectByKeyword2(String keyword) {
        String sql = "SELECT * FROM Student WHERE ID_Class LIKE ? OR Last_Name LIKE ?;";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    public boolean checkCountIDStudent(String keyword) {
        try {
            String CHECK_ID_SQL = "SELECT COUNT(ID_Student) FROM Student where ID_Student = ?;";
            ResultSet rs = JDBCHelper.executeQuery(CHECK_ID_SQL, keyword);
            while (rs.next()) {
                int count = rs.getInt(1);
                if (count == 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }
}
