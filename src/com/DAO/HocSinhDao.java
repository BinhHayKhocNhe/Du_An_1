
package com.DAO;

import com.Entity.Student;
import com.Utils.JDBCHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HocSinhDao {
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
}
