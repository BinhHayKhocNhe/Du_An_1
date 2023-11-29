
package com.DAO;

import com.Utils.JDBCHelper;
import com.Entity.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
public static List<Class> getAllClasses() {
        List<Class> classList = new ArrayList<>();
        String sql = "SELECT * FROM Class";

        try (ResultSet rs = JDBCHelper.executeQuery(sql)) {
            while (rs.next()) {
                Class classEntity = new Class(
                        rs.getString("ID_Class"),
                        rs.getString("Class_Name"),
                        rs.getString("ID_Teacher"),
                        rs.getString("ID_Student"),
                        rs.getInt("Quantity"),
                        rs.getString("Note")
                );

                classList.add(classEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return classList;
    }
}
