package com.DAO;

import com.Entity.Schedule;
import com.Utils.JDBCHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO implements myInterFace<Schedule, String> {

    private final String SELECT_BY_ID_SQL = "SELECT * FROM Schedule WHERE ID_Course = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM Schedule";
    private final String INSERT_SQL = "INSERT INTO Schedule (ID_Course, ID_Teacher, ID_Student, ID_Class, ID_Subject, School_Day, Schedule_Date, Course_Name, Note) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE Schedule SET ID_Teacher = ?, ID_Student = ?, ID_Class = ?, ID_Subject = ?, School_Day = ?, Schedule_Date = ?, Course_Name = ?, Note = ? WHERE ID_Course = ?";
    private final String DELETE_SQL = "DELETE FROM Schedule WHERE ID_Course = ?";

    @Override
    public void insert(Schedule entity) {
        JDBCHelper.executeUpdate(
                INSERT_SQL,
                entity.getID_Course(),
                entity.getID_Teacher(),
                entity.getID_Student(),
                entity.getID_Class(),
                entity.getID_Subject(),
                entity.getSchoolDay(),
                entity.getScheduleDate(),
                entity.getCourseName(),
                entity.getNote()
        ); // Handle the exception as needed
    }

    @Override
    public void update(Schedule entity) {
        JDBCHelper.executeUpdate(
                UPDATE_SQL,
                entity.getID_Teacher(),
                entity.getID_Student(),
                entity.getID_Class(),
                entity.getID_Subject(),
                entity.getSchoolDay(),
                entity.getScheduleDate(),
                entity.getCourseName(),
                entity.getNote(),
                entity.getID_Course()
        ); // Handle the exception as needed
    }

    @Override
    public void delete(String id) {
        JDBCHelper.executeUpdate(DELETE_SQL, id); // Handle the exception as needed
    }

    @Override
    public boolean checkID(Schedule entity) {
        // Implement the checkID operation here if needed
        return false;
    }

    @Override
    public void find(Schedule entity) {
        // Implement the find operation here if needed
    }

    @Override
    public Schedule selectById(String id) {
        List<Schedule> list = selectBySql(SELECT_BY_ID_SQL, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<Schedule> selectAll() {
        return selectBySql(SELECT_ALL_SQL);
    }

    @Override
    public List<Schedule> selectBySql(String sql, Object... args) {
        List<Schedule> scheduleList = new ArrayList<>();
        try {
            ResultSet resultSet = JDBCHelper.executeQuery(sql, args);
            while (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setID_Course(resultSet.getString("ID_Course"));
                schedule.setID_Teacher(resultSet.getString("ID_Teacher"));
                schedule.setID_Student(resultSet.getString("ID_Student"));
                schedule.setID_Class(resultSet.getString("ID_Class"));
                schedule.setID_Subject(resultSet.getString("ID_Subject"));
                schedule.setSchoolDay(resultSet.getDate("School_Day"));
                schedule.setScheduleDate(resultSet.getTimestamp("Schedule_Date"));
                schedule.setCourseName(resultSet.getString("Course_Name"));
                schedule.setNote(resultSet.getString("Note"));
                scheduleList.add(schedule);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle the exception as needed
        }
        return scheduleList;
    }
     public List<String> get_sub() {
        return getUniqueColumnValues("ID_Subject");
    }
       public List<String> get_day() {
        return getUniqueColumnValues("School_Day");
    }
    // Phương thức trợ giúp để lấy giá trị độc nhất từ một cột cụ thể
    private List<String> getUniqueColumnValues(String columnName) {
        List<String> values = new ArrayList<>();
        String sql = "SELECT DISTINCT " + columnName + " FROM Schedule";
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
}
