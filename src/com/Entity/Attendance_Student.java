/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

import java.util.Date;

/**
 *
 * @author Duong Minh Binh
 */
public class Attendance_Student {
    private String ID_Attendance, ID_Student_Attendance, ID_Class_Attendance,
            ID_Subject_Attendance, ID_Teacher_Attendance, Note;
    private boolean Status_Attendance;
    private Date Attendance_Date;

    public String getID_Attendance() {
        return ID_Attendance;
    }

    public void setID_Attendance(String ID_Attendance) {
        this.ID_Attendance = ID_Attendance;
    }

    public String getID_Student_Attendance() {
        return ID_Student_Attendance;
    }

    public void setID_Student_Attendance(String ID_Student_Attendance) {
        this.ID_Student_Attendance = ID_Student_Attendance;
    }

    public String getID_Class_Attendance() {
        return ID_Class_Attendance;
    }

    public void setID_Class_Attendance(String ID_Class_Attendance) {
        this.ID_Class_Attendance = ID_Class_Attendance;
    }

    public String getID_Subject_Attendance() {
        return ID_Subject_Attendance;
    }

    public void setID_Subject_Attendance(String ID_Subject_Attendance) {
        this.ID_Subject_Attendance = ID_Subject_Attendance;
    }

    public String getID_Teacher_Attendance() {
        return ID_Teacher_Attendance;
    }

    public void setID_Teacher_Attendance(String ID_Teacher_Attendance) {
        this.ID_Teacher_Attendance = ID_Teacher_Attendance;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public boolean isStatus_Attendance() {
        return Status_Attendance;
    }

    public void setStatus_Attendance(boolean Status_Attendance) {
        this.Status_Attendance = Status_Attendance;
    }

    public Date getAttendance_Date() {
        return Attendance_Date;
    }

    public void setAttendance_Date(Date Attendance_Date) {
        this.Attendance_Date = Attendance_Date;
    }

    public Attendance_Student(String ID_Attendance, String ID_Student_Attendance, String ID_Class_Attendance, String ID_Subject_Attendance, String ID_Teacher_Attendance, String Note, boolean Status_Attendance, Date Attendance_Date) {
        this.ID_Attendance = ID_Attendance;
        this.ID_Student_Attendance = ID_Student_Attendance;
        this.ID_Class_Attendance = ID_Class_Attendance;
        this.ID_Subject_Attendance = ID_Subject_Attendance;
        this.ID_Teacher_Attendance = ID_Teacher_Attendance;
        this.Note = Note;
        this.Status_Attendance = Status_Attendance;
        this.Attendance_Date = Attendance_Date;
    }

    public Attendance_Student() {
    }
}
