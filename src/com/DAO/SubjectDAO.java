package com.DAO;

import com.Entity.Subject;
import com.Utils.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements myInterFace<Subject, String> {
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Subject WHERE ID_Subject = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM Subject";
    private final String INSERT_SQL = "INSERT INTO Subject (ID_Subject, Subject_Name, Note) VALUES (?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE Subject SET Subject_Name = ?, Note = ? WHERE ID_Subject = ?";
    private final String DELETE_SQL = "DELETE FROM Subject WHERE ID_Subject = ?";

    @Override
    public void insert(Subject entity) {
        JDBCHelper.executeUpdate(
                INSERT_SQL,
                entity.getID_Subject(),
                entity.getSubject_Name(),
                entity.getNote()
        ); // Handle the exception as needed
    }

    @Override
    public void update(Subject entity) {
        JDBCHelper.executeUpdate(
                UPDATE_SQL,
                entity.getSubject_Name(),
                entity.getNote(),
                entity.getID_Subject()
        ); // Handle the exception as needed
    }

    @Override
    public void delete(String id) {
        JDBCHelper.executeUpdate(DELETE_SQL, id); // Handle the exception as needed
    }

    @Override
    public boolean checkID(Subject entity) {
        // Implement the checkID operation here if needed
        return false;
    }

    @Override
    public void find(Subject entity) {
        // Implement the find operation here if needed
    }

    @Override
    public Subject selectById(String id) {
       List<Subject> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Subject> selectAll() {
   return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Subject> selectBySql(String sql, Object... args) {
        List<Subject> subjectList = new ArrayList<>();
        try (ResultSet resultSet = JDBCHelper.executeQuery(sql, args)) {
            while (resultSet.next()) {
                Subject sb = new Subject();
                sb.setID_Subject(resultSet.getString("ID_Subject"));
                sb.setSubject_Name(resultSet.getString("Subject_Name"));
                sb.setNote(resultSet.getString("Note"));
                subjectList.add(sb);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception as needed
        }
        return subjectList;
    }
}
