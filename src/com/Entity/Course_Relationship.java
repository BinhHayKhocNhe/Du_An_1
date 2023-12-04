/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

/**
 *
 * @author Duong Minh Binh
 */
public class Course_Relationship {

    private String ID_Course, ID_Class, ID_Student, ID_Teacher;

    public Course_Relationship(String ID_Course, String ID_Class, String ID_Student, String ID_Teacher) {
        this.ID_Course = ID_Course;
        this.ID_Class = ID_Class;
        this.ID_Student = ID_Student;
        this.ID_Teacher = ID_Teacher;
    }

    public Course_Relationship() {
    }

    public String getID_Course() {
        return ID_Course;
    }

    public void setID_Course(String ID_Course) {
        this.ID_Course = ID_Course;
    }

    public String getID_Class() {
        return ID_Class;
    }

    public void setID_Class(String ID_Class) {
        this.ID_Class = ID_Class;
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
}
