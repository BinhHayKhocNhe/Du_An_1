package com.DAO;

import com.Entity.Point;
import com.Utils.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PointDAO implements myInterFace<Point, String> {

    private final String SELECT_BY_ID_SQL = "SELECT * FROM Point WHERE ID_Student = ? AND ID_Class = ? AND ID_Subject = ?"
            + " AND ID_Teacher = ? AND Year = ? AND Course_Name = ?;";

    private final String SELECT_ALL_SQL = "SELECT * FROM Point";
    private final String INSERT_SQL = "INSERT INTO Point (ID_Student, ID_Class, ID_Subject, ID_Teacher"
            + ", Year, Point, Course_Name, Note) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE Point SET Point = ?, Note = ? "
            + "WHERE ID_Student = ? AND ID_Class = ? AND ID_Subject = ?"
            + " AND ID_Teacher = ? AND Year = ? AND Course_Name = ?";

    private final String DELETE_SQL = "DELETE FROM Point WHERE ID_Student = ?";

    @Override
    public void insert(Point entity) {
        JDBCHelper.executeUpdate(
                INSERT_SQL,
                entity.getID_Student(),
                entity.getID_Class(),
                entity.getID_Subject(),
                entity.getID_Teacher(),
                entity.getYear(),
                entity.getPoint(),
                entity.getCourse_Name(),
                entity.getNote()
        ); // Handle the exception as needed
    }

    @Override
    public void update(Point entity) {
        JDBCHelper.executeUpdate(
                UPDATE_SQL,
                entity.getPoint(),
                entity.getNote(),
                entity.getID_Student(),
                entity.getID_Class(),
                entity.getID_Subject(),
                entity.getID_Teacher(),
                entity.getYear(),
                entity.getCourse_Name()
        ); // Handle the exception as needed
    }

    @Override
    public void delete(String id) {
        JDBCHelper.executeUpdate(DELETE_SQL, id); // Handle the exception as needed
    }

    @Override
    public boolean checkID(Point entity) {
        final String sql = "SELECT 1 FROM Point WHERE ID_Student = ? AND ID_Class = ? AND ID_Subject = ?"
                + " AND ID_Teacher = ? AND Year = ? AND Course_Name = ?;";
        try {
            ResultSet rs = JDBCHelper.executeQuery(sql, entity.getID_Student(), entity.getID_Class(), entity.getID_Subject(),
                    entity.getID_Teacher(), entity.getYear(), entity.getCourse_Name());
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    @Override
    public void find(Point entity) {
        // Implement the find operation here if needed
    }

    @Override
    public Point selectById(String id) {
        List<Point> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Point> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Point> selectBySql(String sql, Object... args) {
        List<Point> pointList = new ArrayList<>();
        try {
            ResultSet resultSet = JDBCHelper.executeQuery(sql, args);
            while (resultSet.next()) {
                Point point = new Point();
                point.setID_Student(resultSet.getString("ID_Student"));
                point.setID_Class(resultSet.getString("ID_Class"));
                point.setID_Subject(resultSet.getString("ID_Subject"));
                point.setID_Teacher(resultSet.getString("ID_Teacher"));
                point.setYear(resultSet.getInt("Year"));
                point.setPoint(resultSet.getFloat("Point"));
                point.setCourse_Name(resultSet.getString("Course_Name"));
                point.setNote(resultSet.getString("Note"));
                pointList.add(point);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception as needed
        }
        return pointList;
    }

    public List<String> getUniqueStudentIDs() {
        return getUniqueColumnValues("ID_Student");
    }

    public List<String> getUniqueClassIDs() {
        return getUniqueColumnValues("ID_Class");
    }

    public List<String> getUniqueSubjectIDs() {
        return getUniqueColumnValues("ID_Subject");
    }

    public List<String> getUniqueTeacherIDs() {
        return getUniqueColumnValues("ID_Teacher");
    }

    // Phương thức trợ giúp để lấy giá trị độc nhất từ một cột cụ thể
    private List<String> getUniqueColumnValues(String columnName) {
        List<String> values = new ArrayList<>();
        String sql = "SELECT DISTINCT " + columnName + " FROM Point";
        try (ResultSet resultSet = JDBCHelper.executeQuery(sql)) {
            while (resultSet.next()) {
                values.add(resultSet.getString(columnName));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception as needed
        }
        return values;
    }

    public List<Point> selectByKeyword(String keyword) {
        String sql = "SELECT * FROM Point WHERE ID_Student LIKE ? or ID_Class like ?";
        return this.selectBySql(sql, "%" + keyword + "%", "%" + keyword + "%");
    }

    public Point selectByIdPoint(String... arg) {
        List<Point> list = selectBySql(SELECT_BY_ID_SQL, arg);
        return list.isEmpty() ? null : list.get(0);
    }
//    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
//        try {
//            List<Object[]> list = new ArrayList<>();
//            ResultSet rs = JDBCHelper.executeQuery(sql, args);
//            while (rs.next()) {
//                Object[] vals = new Object[cols.length];
//                for (int i = 0; i < cols.length; i++) {
//                    vals[i] = rs.getObject(cols[i]);
//                }
//                list.add(vals);
//            }
//            rs.getStatement().getConnection().close();
//            return list;
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public List<Object[]> getGPA() {
//        String sql = "{CALL GetAllStudentPerformance}";
//        String[] cols = {"ID_Student", "ID_Class", "Year", "Course_Name", "ID_Teacher", "GPA", "Classification", "Note"};
//        return this.getListOfArray(sql, cols);
//    }
}
