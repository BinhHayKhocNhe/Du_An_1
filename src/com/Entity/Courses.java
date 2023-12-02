/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

/**
 *
 * @author Duong Minh Binh
 */
public class Courses {

    private String ID_Course, Course_Name, Note;
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

    public Courses(String ID_Course, String Course_Name, String Note, int Year) {
        this.ID_Course = ID_Course;
        this.Course_Name = Course_Name;
        this.Note = Note;
        this.Year = Year;
    }

    public Courses() {
    }
}
