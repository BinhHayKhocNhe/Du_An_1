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

    @Override
    public void insert(Subject entity) {
        JDBCHelper.executeUpdate(
                INSERT_SQL,
                entity.getID_Subject(),
                entity.getSubject_Name(),
                entity.getNote()
        );
    }

    @Override
    public void update(Subject entity) {
        JDBCHelper.executeUpdate(
                UPDATE_SQL,
                entity.getSubject_Name(),
                entity.getNote(),
                entity.getID_Subject()
        );
    }

    @Override
    public void delete(String id) {
        return;
    }

    @Override
    public boolean checkID(Subject entity) {
        final String SQL = "SELECT 1 FROM Subject WHERE ID_Subject = ?;";
        try {
            ResultSet rs = JDBCHelper.executeQuery(SQL, entity.getID_Subject());
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void find(Subject entity) {
        return;
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
        }
        return subjectList;
    }

    public List<Subject> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Subject WHERE ID_Subject LIKE ? OR Subject_Name LIKE ?;";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }
}
