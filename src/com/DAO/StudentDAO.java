package com.DAO;

import com.Entity.Student;
import com.Utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements myInterFace<Student, String> {

    private final String SELECT_BY_ID_SQL = "SELECT * FROM Student WHERE ID_Student = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM Student";
    private final String UPDATE_SQL = "UPDATE Student SET First_Name = ?, Middle_Name = ?, Last_Name = ?, "
            + "Gender = ?, Address_Student = ?, Status_Student = ?, Avatar = ?, Date_Of_Birth = ?, "
            + "Month_Of_Birth = ?, Year_Of_Birth = ?, Note = ? WHERE ID_Student = ?";

    @Override
    public void insert(Student entity) {
        return;
    }

    @Override
    public void update(Student entity) {
        JDBCHelper.executeUpdate(UPDATE_SQL, entity.getFirst_Name(), entity.getMiddle_Name(), entity.getLast_Name(),
                entity.isGender(), entity.getAddress_Student(), entity.isStatus_Student(), entity.getAvatar(),
                entity.getDate_Of_Birth(), entity.getMonth_Of_Birth(), entity.getYear_Of_Birth(), entity.getNote(),
                entity.getID_Student());
    }

    @Override
    public void delete(String id) {
        return;
    }

    @Override
    public boolean checkID(Student entity) {
        return false;
    }

    @Override
    public void find(Student entity) {
        return;
    }

    @Override
    public Student selectById(String id) {
        List<Student> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Student> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Student> selectBySql(String sql, Object... args) {
        List<Student> studentList = new ArrayList<>();
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, args);
            while (rs.next()) {
                Student student = new Student(rs.getString("ID_Student"), rs.getString("First_Name"),
                        rs.getString("Middle_Name"), rs.getString("Last_Name"), rs.getString("Address_Student"),
                        rs.getString("Avatar"), rs.getString("Note"), rs.getBoolean("Gender"),
                        rs.getBoolean("Status_Student"), rs.getInt("Date_Of_Birth"),
                        rs.getInt("Month_Of_Birth"), rs.getInt("Year_Of_Birth"));
                studentList.add(student);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public List<Student> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Student WHERE ID_Student LIKE ? OR Last_Name LIKE ?;";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }
}
