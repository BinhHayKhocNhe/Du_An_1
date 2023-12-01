/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Entity.Guardians;
import com.Utils.JDBCHelper;
import com.Utils.Message;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duong Minh Binh
 */
public class Guardians_DAO implements myInterFace<Guardians, String> {

    private final String INSERT_SQL = "INSERT INTO Guardians (ID_Guardians, Password_Guardians, First_Name, Middle_Name, "
            + "Last_Name, Email, Phone_Number, Gender, Address_Guardians, Job, Note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE Guardians SET First_Name = ?, Middle_Name = ?, Last_Name = ?, Email = ?, "
            + "Phone_Number = ?, Gender = ?, Address_Guardians = ?, Job = ?, Note = ? WHERE ID_Guardians = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM Guardians;";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Guardians where ID_Guardians = ?;";

    @Override
    public void insert(Guardians entity) {
        String defaultPassword = "12345678";
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getID_Guardians(), defaultPassword, entity.getFirst_Name(),
                entity.getMiddle_Name(), entity.getLast_Name(), entity.getEmail(), entity.getPhone_Number(),
                entity.isGender(), entity.getAddress_Guardians(), entity.getJob(), entity.getNote());
    }

    @Override
    public void update(Guardians entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL, entity.getFirst_Name(), entity.getMiddle_Name(), entity.getLast_Name(),
                entity.getEmail(), entity.getPhone_Number(), entity.isGender(), entity.getAddress_Guardians(),
                entity.getJob(), entity.getNote(), entity.getID_Guardians());
    }

    @Override
    public void delete(String id) {
        return;
    }

    @Override
    public boolean checkID(Guardians entity) {
        String sql = "Select count(ID_Guardians) from Guardians where ID_Guardians = ? and Password_Guardians = ? ";
        ResultSet rs = JDBCHelper.executeQuery(sql, entity.getID_Guardians(), entity.getPassword_Guardians());
        try {
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
    public void find(Guardians entity) {
        return;
    }

    @Override
    public Guardians selectById(String id) {
        List<Guardians> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Guardians> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Guardians> selectBySql(String sql, Object... args) {
        List<Guardians> guardiansList = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Guardians guardians = new Guardians();
                guardians.setID_Guardians(rs.getString("ID_Guardians"));
                guardians.setPassword_Guardians(rs.getString("Password_Guardians"));
                guardians.setFirst_Name(rs.getString("First_Name"));
                guardians.setMiddle_Name(rs.getString("Middle_Name"));
                guardians.setLast_Name(rs.getString("Last_Name"));
                guardians.setEmail(rs.getString("Email"));
                guardians.setPhone_Number(rs.getString("Phone_Number"));
                guardians.setAddress_Guardians(rs.getString("Address_Guardians"));
                guardians.setJob(rs.getString("Job"));
                guardians.setNote(rs.getString("Note"));
                guardians.setGender(rs.getBoolean("Gender"));
                guardiansList.add(guardians);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return guardiansList;
    }

    public List<Guardians> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Guardians WHERE ID_Guardians LIKE ? OR Last_Name LIKE ?;";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    public boolean checkCountIDGuardians(String id) {
        try {
            final String sql = "SELECT COUNT(ID_Guardians) FROM Guardians WHERE ID_Guardians = ?;";
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

    public void adminResetPassParent(String ID) {
        final String sql = "UPDATE Guardians SET Password_Guardians = '12345678' WHERE ID_Guardians = ?;";
        try {
            JDBCHelper.executeUpdate(sql, ID);
            Message.alert(null, "Reset password successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
