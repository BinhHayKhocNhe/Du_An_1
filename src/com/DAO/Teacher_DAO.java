package com.DAO;

import com.Entity.Teacher;
import com.Utils.JDBCHelper;
import com.Utils.Message;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duong Minh Binh
 */
public class Teacher_DAO implements myInterFace<Teacher, String> {

    private final String INSERT_SQL = "INSERT INTO Teacher (ID_Teacher, Password_Teacher, First_Name, Middle_Name, Last_Name, "
            + "Email, Phone_Number, Gender, Status_Teacher, Level_Teacher, Address_Teacher, Avatar, Date_Of_Birth, Month_Of_Birth, Year_Of_Birth, Note) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE Teacher SET First_Name = ?, Middle_Name = ?, Last_Name = ?, "
            + "Email = ?, Phone_Number = ?, Gender = ?, Status_Teacher = ?, Level_Teacher = ?, "
            + "Address_Teacher = ?, Avatar = ?, Date_Of_Birth = ?, Month_Of_Birth = ?, "
            + "Year_Of_Birth = ?, Note = ? WHERE ID_Teacher = ?";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Teacher where ID_Teacher = ?;";
    private final String SELECT_ALL_SQL = "SELECT * FROM Teacher;";

    @Override
    public void insert(Teacher entity) {
        String defaultPassword = "12345678";
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getID_Teacher(), defaultPassword, entity.getFirst_Name(),
                entity.getMiddle_Name(), entity.getLast_Name(), entity.getEmail(), entity.getPhone_Number(),
                entity.isGender(), entity.isStatus_Teacher(), entity.getLevel_Teacher(), entity.getAddress_Teacher(),
                entity.getAvatar(), entity.getDate_Of_Birth(), entity.getMonth_Of_Birth(), entity.getYear_Of_Birth(),
                entity.getNote());
    }

    @Override
    public void update(Teacher entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL, entity.getFirst_Name(),
                entity.getMiddle_Name(), entity.getLast_Name(), entity.getEmail(), entity.getPhone_Number(),
                entity.isGender(), entity.isStatus_Teacher(), entity.getLevel_Teacher(), entity.getAddress_Teacher(),
                entity.getAvatar(), entity.getDate_Of_Birth(), entity.getMonth_Of_Birth(), entity.getYear_Of_Birth(),
                entity.getNote(), entity.getID_Teacher());
    }

    @Override
    public void delete(String id) {
        return;
    }

    @Override
    public boolean checkID(Teacher entity) {
        String sql = "Select count(ID_Teacher) from Teacher where ID_Teacher = ? and Password_Teacher = ? ";
        ResultSet rs = JDBCHelper.executeQuery(sql, entity.getID_Teacher(), entity.getPassword_Teacher());
        try {
            while (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    return true;
                }
            }
        } catch (java.sql.SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void find(Teacher entity) {
        return;
    }

    @Override
    public Teacher selectById(String id) {
        List<Teacher> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Teacher> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Teacher> selectBySql(String sql, Object... args) {
        List<Teacher> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Teacher teacher = new Teacher(rs.getString("ID_Teacher"), rs.getString("Password_Teacher")
                        , rs.getString("First_Name"), rs.getString("Middle_Name"),
                        rs.getString("Last_Name"), rs.getString("Email"), rs.getString("Phone_Number"),
                        rs.getString("Level_Teacher"), rs.getString("Address_Teacher"), rs.getString("Avatar"),
                        rs.getString("Note"), rs.getBoolean("Gender"), rs.getBoolean("Status_Teacher"),
                        rs.getInt("Date_Of_Birth"), rs.getInt("Month_Of_Birth"), rs.getInt("Year_Of_Birth"),
                        rs.getDate("Start_Date"));
                list.add(teacher);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.getMessage();
        }
        return list;
    }

    public boolean checkCountIDTeacher(String keyword) {
        try {
            String CHECK_ID_SQL = "SELECT COUNT(ID_Teacher) FROM Teacher where ID_Teacher = ?;";
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

    public List<Teacher> selectByKeyword(String keyword) {
        String sql = "SELECT *"
                + " FROM Teacher WHERE ID_Teacher LIKE ? OR Last_Name LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    public void adminResetPassTeacher(String ID) {
        final String sql = "UPDATE Teacher SET Password_Teacher = '12345678' WHERE ID_Teacher = ?;";
        try {
            JDBCHelper.executeUpdate(sql, ID);
            Message.alert(null, "Reset password successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changePassword(String ID, String currentPassword, String newPassword) {
        final String checkCurrentPassword = "SELECT Password_Teacher FROM Teacher WHERE ID_Teacher = ?";
        final String sql = "UPDATE Teacher SET Password_Teacher = ? WHERE ID_Teacher = ? and Password_Teacher=?";
        try {
            ResultSet resultSet = JDBCHelper.executeQuery(checkCurrentPassword, ID);

            if (!resultSet.next()) {
                Message.alert(null, "User not found!");
                return;
            }

            String storedPassword = resultSet.getString("Password_Teacher");

            if (!storedPassword.equals(currentPassword)) {
                Message.alert(null, "Current password is incorrect!");
                return;
            }

            if (currentPassword.equals(newPassword)) {
                Message.alert(null, "The new password must not be the same as the current password!");
                return;
            }

            JDBCHelper.executeUpdate(sql, newPassword, ID, currentPassword);
            Message.alert(null, "Changed password successfully!");

            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
