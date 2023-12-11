/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

/**
 *
 * @author Duong Minh Binh
 */
public class Bonus {

    private String Course_Name, Level, ID_Student, Note;
    private int year, ID_Bonus;
    private float GPA;

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String Course_Name) {
        this.Course_Name = Course_Name;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String Level) {
        this.Level = Level;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getGPA() {
        return GPA;
    }

    public void setGPA(float GPA) {
        this.GPA = GPA;
    }

    public Bonus() {
    }

    public Bonus(String Course_Name, String Level, String ID_Student, String Note, int year, int ID_Bonus, float GPA) {
        this.Course_Name = Course_Name;
        this.Level = Level;
        this.ID_Student = ID_Student;
        this.Note = Note;
        this.year = year;
        this.ID_Bonus = ID_Bonus;
        this.GPA = GPA;
    }

    public int getID_Bonus() {
        return ID_Bonus;
    }

    public void setID_Bonus(int ID_Bonus) {
        this.ID_Bonus = ID_Bonus;
    }

}
