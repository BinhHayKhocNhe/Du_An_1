/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Entity.Administrators;
import com.Utils.JDBCHelper;
import com.Utils.Message;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duong Minh Binh
 */
public class Administrators_DAO implements myInterFace<Administrators, String> {

    private final String CHECK_ID_SQL = "select COUNT(ID_Administrator) from Administrators "
            + "where ID_Administrator = ? and Password_Administrator = ?;";
    private final String UPDATE_SQL = "UPDATE Administrators SET First_Name = ?, Middle_Name = ?, Last_Name = ?, Email = ?, \n"
            + "Phone_Number = ?, Gender = ?, Address = ?, Note = ? WHERE ID_Administrator = ?";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Administrators WHERE ID_Administrator = ?";

    @Override
    public void insert(Administrators entity) {
        return;
    }

    @Override
    public void update(Administrators entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL, entity.getFirst_Name(), entity.getMiddle_Name(), entity.getLast_Name(),
                entity.getEmail(), entity.getPhone_Number(), entity.isGender(), entity.getAddress(),
                entity.getNote(), entity.getID_Administrator());
    }

    @Override
    public void delete(String id) {
        return;
    }

    @Override
    public boolean checkID(Administrators entity) {
        try {
            ResultSet rs = JDBCHelper.executeQuery(CHECK_ID_SQL, entity.getID_Administrator(), entity.getPassword_Administrator());
            while (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Administrators selectById(String id) {
        List<Administrators> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Administrators> selectAll() {
        return null;
    }

    @Override
    public List<Administrators> selectBySql(String sql, Object... args) {
        List<Administrators> list = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Administrators admin = new Administrators(rs.getString("ID_Administrator"), rs.getString("First_Name"),
                        rs.getString("Middle_Name"), rs.getString("Last_Name"), rs.getString("Email"),
                        rs.getString("Phone_Number"), rs.getString("Address"),
                        rs.getString("Note"), rs.getBoolean("Gender"));
                list.add(admin);
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return list;
    }

    public void changePassword(String ID, String currentPassword, String newPassword) {
        final String checkCurrentPassword = "SELECT Password_Administrator FROM Administrators WHERE ID_Administrator = ?";
        final String sql = "UPDATE Administrators SET Password_Administrator = ? WHERE ID_Administrator = ? and Password_Administrator=?";
        try {
            ResultSet resultSet = JDBCHelper.executeQuery(checkCurrentPassword, ID);

            if (!resultSet.next()) {
                Message.alert(null, "User not found!");
                return;
            }

            String storedPassword = resultSet.getString("Password_Administrator");

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

    @Override
    public void find(Administrators entity) {
        return;
    }
}
