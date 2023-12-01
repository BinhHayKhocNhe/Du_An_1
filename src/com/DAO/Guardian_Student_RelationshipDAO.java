/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

import com.Entity.Guardian_Student_Relationship;
import com.Utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Duong Minh Binh
 */
public class Guardian_Student_RelationshipDAO implements myInterFace<Guardian_Student_Relationship, String> {

    private final String INSERT_SQL = "INSERT INTO Guardian_Student_Relationship (ID_Student, ID_Guardians) VALUES (?, ?);";
    private final String SELECT_ALL_SQL = "SELECT * FROM Guardian_Student_Relationship;";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Guardian_Student_Relationship where ID_Guardians = ?";
    private final String DELETE_SQL = "DELETE FROM Guardian_Student_Relationship WHERE ID_Student = ? AND ID_Guardians = ?";

    @Override

    public void insert(Guardian_Student_Relationship entity) {
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getID_Student(), entity.getID_Guardians());
    }

    @Override
    public void update(Guardian_Student_Relationship entity) {
        return;
    }

    @Override
    public void delete(String id) {
        return;
    }

    @Override
    public boolean checkID(Guardian_Student_Relationship entity) {
        return false;
    }

    @Override
    public void find(Guardian_Student_Relationship entity) {
        return;
    }

    @Override
    public Guardian_Student_Relationship selectById(String id) {
        List<Guardian_Student_Relationship> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Guardian_Student_Relationship> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Guardian_Student_Relationship> selectBySql(String sql, Object... args) {
        List<Guardian_Student_Relationship> guardiansList = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Guardian_Student_Relationship list = new Guardian_Student_Relationship();
                list.setID_Guardians(rs.getString("ID_Guardians"));
                list.setID_Student(rs.getString("ID_Student"));
                guardiansList.add(list);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return guardiansList;
    }

    public List<Guardian_Student_Relationship> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Guardian_Student_Relationship where ID_Guardians LIKE ? OR ID_Student LIKE ?;";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    public boolean isRelationshipExists(String studentID, String guardianID) {
        String sql = "SELECT 1 FROM Guardian_Student_Relationship WHERE ID_Student = ? AND ID_Guardians = ?";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, studentID, guardianID);
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteRelationship(Guardian_Student_Relationship entity) {
        JDBCHelper.executeUpdate(DELETE_SQL, entity.getID_Student(), entity.getID_Guardians());
    }
}
