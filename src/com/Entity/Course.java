/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

/**
 *
 * @author admin
 */
public class Course {

    private String ID_Course, Course_Name, ID_Student, ID_Teacher, ID_Class, Note;
    private int Year;

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

    public String getID_Student() {
        return ID_Student;
    }

    public void setID_Student(String ID_Student) {
        this.ID_Student = ID_Student;
    }

    public String getID_Teacher() {
        return ID_Teacher;
    }

    public void setID_Teacher(String ID_Teacher) {
        this.ID_Teacher = ID_Teacher;
    }

    public String getID_Class() {
        return ID_Class;
    }

    public void setID_Class(String ID_Class) {
        this.ID_Class = ID_Class;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public Course(String ID_Course, String Course_Name, String ID_Student, String ID_Teacher, String ID_Class, String Note, int Year) {
        this.ID_Course = ID_Course;
        this.Course_Name = Course_Name;
        this.ID_Student = ID_Student;
        this.ID_Teacher = ID_Teacher;
        this.ID_Class = ID_Class;
        this.Note = Note;
        this.Year = Year;
    }

    public Course() {
    }
}
