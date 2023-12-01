
package com.Entity;

import java.util.Date; 

public class Schedule {
    private String ID_Course;
    private String ID_Teacher;
    private String ID_Student;
    private String ID_Class;
    private String ID_Subject;
    private Date schoolDay;
    private Date scheduleDate;
    private String courseName;
    private String note;

    // Constructors
    public Schedule(String ID_Course, String ID_Teacher, String ID_Student, String ID_Class, String ID_Subject,
                    Date schoolDay, Date scheduleDate, String courseName, String note) {
        this.ID_Course = ID_Course;
        this.ID_Teacher = ID_Teacher;
        this.ID_Student = ID_Student;
        this.ID_Class = ID_Class;
        this.ID_Subject = ID_Subject;
        this.schoolDay = schoolDay;
        this.scheduleDate = scheduleDate;
        this.courseName = courseName;
        this.note = note;
    }

    // Getters and setters
    public String getID_Course() {
        return ID_Course;
    }

    public void setID_Course(String ID_Course) {
        this.ID_Course = ID_Course;
    }

    public String getID_Teacher() {
        return ID_Teacher;
    }

    public void setID_Teacher(String ID_Teacher) {
        this.ID_Teacher = ID_Teacher;
    }

    public String getID_Student() {
        return ID_Student;
    }

    public void setID_Student(String ID_Student) {
        this.ID_Student = ID_Student;
    }

    public String getID_Class() {
        return ID_Class;
    }

    public void setID_Class(String ID_Class) {
        this.ID_Class = ID_Class;
    }

    public String getID_Subject() {
        return ID_Subject;
    }

    public void setID_Subject(String ID_Subject) {
        this.ID_Subject = ID_Subject;
    }

    public Date getSchoolDay() {
        return schoolDay;
    }

    public void setSchoolDay(Date schoolDay) {
        this.schoolDay = schoolDay;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    public Schedule(){}
}