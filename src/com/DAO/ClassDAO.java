package com.DAO;

import com.Utils.JDBCHelper;
import com.Entity.Class;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements myInterFace<Class, String> {


    private final String SELECT_ALL_SQL = "SELECT * FROM Class";
    private final String INSERT_SQL = "INSERT INTO Class (ID_Class, Class_Name, ID_Teacher, "
            + "Quantity, Note) VALUES(?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE Class SET Class_Name = ?, ID_Teacher = ?, Quantity = ?, "
            + "Note = ? WHERE ID_Class = ?;";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM Class WHERE ID_Class = ?;";


    @Override
    public void insert(Class entity) {
        JDBCHelper.executeUpdate(INSERT_SQL, entity.getID_Class(), entity.getClass_Name(),
                entity.getID_Teacher(), entity.getQuantity(), entity.getNote());
    }

    @Override
    public void update(Class entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL, entity.getClass_Name(),
                entity.getID_Teacher(), entity.getQuantity(), entity.getNote(), entity.getID_Class());
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean checkID(Class entity) {
        final String sql = "SELECT COUNT(ID_Class) FROM Class WHERE ID_Class = ?;";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, entity.getID_Class());
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
    public void find(Class entity) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Class selectById(String id) {
        List<Class> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<Class> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Class> selectBySql(String sql, Object... args) {
        List<Class> classList = new ArrayList<>();

        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);

            while (rs.next()) {
                Class list = new Class();
                list.setID_Class(rs.getString("ID_Class"));
                list.setClass_Name(rs.getString("Class_Name"));
                list.setID_Teacher(rs.getString("ID_Teacher"));
                list.setQuantity(rs.getInt("Quantity"));
                list.setNote(rs.getString("Note"));
                classList.add(list);
            }
            rs.getStatement().getConnection().close();

        } catch (Exception e) {

            e.printStackTrace();
        }
        return classList;
    }

    public List<Class> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Class WHERE ID_Class LIKE ? OR Class_Name LIKE ?;";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }
}
