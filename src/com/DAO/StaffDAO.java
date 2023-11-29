/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Utils.JDBCHelper;
import com.Entity.Staff;
import com.Utils.Message;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class StaffDAO implements myInterFace<Staff, String> {

    private final String INSERT_SQL = "INSERT INTO Staff (ID_Staff, Password_Staff, First_Name, Middle_Name, Last_Name, "
            + "Email, Phone_Number, Gender, Status_Staff, Position, Address_Staff, Avatar, "
            + "Date_Of_Birth, Month_Of_Birth, Year_Of_Birth, Note) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE Staff SET First_Name = ?, Middle_Name = ?, Last_Name = ?, Email = ?, "
            + "Phone_Number = ?, Gender = ?, Status_Staff = ?, Position = ?, Address_Staff = ?, Avatar = ?,"
            + "Date_Of_Birth = ?, Month_Of_Birth = ?, Year_Of_Birth = ?, Note = ? WHERE ID_Staff = ?;";

    @Override
    public void insert(Staff entity) {
        String defaultPassword = "12345678";
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getID_Staff(), defaultPassword, entity.getFirst_Name(),
                entity.getMiddle_Name(), entity.getLast_Name(), entity.getEmail(), entity.getPhone_Number(),
                entity.isGender(), entity.isStatus_Staff(), entity.getPosition(), entity.getAddress_Staff(),
                entity.getAvatar(), entity.getDate_Of_Birth(), entity.getMonth_Of_Birth(), entity.getYear_Of_Birth(),
                entity.getNote());
    }

    @Override
    public void update(Staff entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL, entity.getFirst_Name(),
                entity.getMiddle_Name(), entity.getLast_Name(), entity.getEmail(), entity.getPhone_Number(),
                entity.isGender(), entity.isStatus_Staff(), entity.getPosition(), entity.getAddress_Staff(),
                entity.getAvatar(), entity.getDate_Of_Birth(), entity.getMonth_Of_Birth(), entity.getYear_Of_Birth(),
                entity.getNote(), entity.getID_Staff());
    }

    @Override
    public void delete(String id) {
        return;
    }

    @Override
    public boolean checkID(Staff entity) {
        String sql = "Select count(ID_Staff) from Staff where ID_Staff = ? and Password_Staff = ? ";
        ResultSet rs = JDBCHelper.executeQuery(sql, entity.getID_Staff(), entity.getPassword_Staff());
        try {
            while (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public void find(Staff entity) {
        return;
    }

    @Override
    public Staff selectById(String id) {
        String sql = "SELECT * FROM Staff WHERE ID_Staff LIKE ?";
        List<Staff> list = selectBySql(sql, id + "%");
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Staff> selectAll() {
        String sql = "select * from Staff";
        return selectBySql(sql);
    }

    @Override
    public List<Staff> selectBySql(String sql, Object... args) {
        List<Staff> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Staff entity = new Staff();
                entity.setID_Staff(rs.getString("ID_Staff"));
                entity.setPassword_Staff(rs.getString("Password_Staff"));
                entity.setFirst_Name(rs.getString("First_Name"));
                entity.setMiddle_Name(rs.getString("Middle_Name"));
                entity.setLast_Name(rs.getString("Last_Name"));
                entity.setEmail(rs.getString("Email"));
                entity.setGender(rs.getBoolean("Gender"));
                entity.setStatus_Staff(rs.getBoolean("Status_Staff"));
                entity.setStart_Date(rs.getDate("Start_Date"));
                entity.setPosition(rs.getString("Position"));
                entity.setNote(rs.getString("Note"));
                entity.setAvatar(rs.getString("Avatar"));
                entity.setAddress_Staff(rs.getString("Address_Staff"));
                entity.setYear_Of_Birth(rs.getInt("Year_Of_Birth"));
                entity.setMonth_Of_Birth(rs.getInt("Month_Of_Birth"));
                entity.setDate_Of_Birth(rs.getInt("Date_Of_Birth"));
                entity.setPhone_Number(rs.getString("Phone_Number"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    public List<Staff> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Staff WHERE ID_Staff LIKE ? or Last_Name like ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    public void changePassword(String ID, String currentPassword, String newPassword) {
        final String checkCurrentPassword = "SELECT Password_Staff FROM Staff WHERE ID_Staff = ?";
        final String sql = "UPDATE Staff SET Password_Staff = ? WHERE ID_Staff = ? and Password_Staff=?";
        try {
            ResultSet resultSet = JDBCHelper.executeQuery(checkCurrentPassword, ID);

            if (!resultSet.next()) {
                Message.alert(null, "User not found!");
                return;
            }

            String storedPassword = resultSet.getString("Password_Staff");

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

    public void adminResetPassStaff(String ID) {
        final String sql = "UPDATE Staff SET Password_Staff = '12345678' WHERE ID_Staff = ?;";
        try {
            JDBCHelper.executeUpdate(sql, ID);
            Message.alert(null, "Reset password successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkCountIDStaff(String id) {
        try {
            final String sql = "SELECT COUNT(ID_Staff) FROM Staff WHERE ID_Staff = ?;";
            ResultSet rs = JDBCHelper.executeQuery(sql, id);
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
public String ReturnPosition(String ID) {
    final String sql = "SELECT Position FROM Staff WHERE ID_Staff = ?";
    String position = null;
    
    try {
        // Truyền tham số vào câu SQL
        ResultSet rs = JDBCHelper.executeQuery(sql, ID);

        // Kiểm tra xem có dữ liệu không
        if (rs.next()) {
            // Di chuyển con trỏ đến dòng đầu tiên và lấy giá trị từ cột "Position"
            position = rs.getString("Position");
        }

        // Đóng ResultSet
        rs.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    
    return position;
}
}
