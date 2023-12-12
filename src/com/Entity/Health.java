/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

/**
 *
 * @author admin
 */
public class Health {
    private String ID_Course, Course_Name, ID_Staff, ID_Student, Note;
    private boolean Status;
    private int Height, Weight;

    public String getID_Course() {
        return ID_Course;
    }

    public void setID_Course(String ID_Course) {
        this.ID_Course = ID_Course;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String Course_Name) {
        this.Course_Name = Course_Name;
    }

    public String getID_Staff() {
        return ID_Staff;
    }

    public void setID_Staff(String ID_Staff) {
        this.ID_Staff = ID_Staff;
    }

    public String getID_Student() {
        return ID_Student;
    }

    public void setID_Student(String ID_Student) {
        this.ID_Student = ID_Student;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int Weight) {
        this.Weight = Weight;
    }

    public Health(String ID_Course, String Course_Name, String ID_Staff, String ID_Student, String Note, boolean Status, int Height, int Weight) {
        this.ID_Course = ID_Course;
        this.Course_Name = Course_Name;
        this.ID_Staff = ID_Staff;
        this.ID_Student = ID_Student;
        this.Note = Note;
        this.Status = Status;
        this.Height = Height;
        this.Weight = Weight;
    }

    public Health() {
    }
}
