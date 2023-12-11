/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Entity.Bonus;
import com.Utils.JDBCHelper;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Duong Minh Binh
 */
public class BonusDAO implements myInterFace<Bonus, String> {

    private final String SELECT_BY_ID_SQL = "SELECT * FROM Bonus WHERE ID_Bonus = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM Bonus";
    private final String INSERT_SQL = "INSERT INTO Bonus (Year, Course_Name, Level, ID_Student, GPA, Note)\n"
            + "VALUES( ?, ?, ?, ?, ?, ?);";

    @Override
    public void insert(Bonus entity) {
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getYear(), entity.getCourse_Name(),
                entity.getLevel(), entity.getID_Student(), entity.getGPA(), entity.getNote());
    }

    @Override
    public void update(Bonus entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean checkID(Bonus entity) {
        final String sql = "SELECT COUNT(ID_Bonus) FROM Bonus WHERE ID_Bonus = ?;";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, entity.getID_Bonus());
            while (rs.next()) {
                int count = rs.getInt(1);
                if (count > 0) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public void find(Bonus entity) {
        return;
    }

    @Override
    public Bonus selectById(String id) {
        List<Bonus> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Bonus> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Bonus> selectBySql(String sql, Object... args) {
        List<Bonus> bonusList = new ArrayList<>();
        try {
            ResultSet resultSet = JDBCHelper.executeQuery(sql, args);
            while (resultSet.next()) {
                Bonus bonus = new Bonus();
                bonus.setID_Student(resultSet.getString("ID_Student"));
                bonus.setID_Bonus(resultSet.getInt("ID_Bonus"));
                bonus.setYear(resultSet.getInt("Year"));
                bonus.setLevel(resultSet.getString("Level"));
                bonus.setCourse_Name(resultSet.getString("Course_Name"));
                bonus.setNote(resultSet.getString("Note"));
                bonus.setGPA(resultSet.getFloat("GPA"));
                bonusList.add(bonus);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception as needed
        }
        return bonusList;
    }

    public List<Bonus> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Bonus WHERE ID_Bonus LIKE ?";
        return this.selectBySql(sql, "%" + keyword + "%");
    }
}
