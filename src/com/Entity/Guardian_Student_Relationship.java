/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

/**
 *
 * @author Duong Minh Binh
 */
public class Guardian_Student_Relationship {

    private String ID_Student, ID_Guardians;

    public Guardian_Student_Relationship() {
    }

    public Guardian_Student_Relationship(String ID_Student, String ID_Guardians) {
        this.ID_Student = ID_Student;
        this.ID_Guardians = ID_Guardians;
    }

    public String getID_Student() {
        return ID_Student;
    }

    public void setID_Student(String ID_Student) {
        this.ID_Student = ID_Student;
    }

    public String getID_Guardians() {
        return ID_Guardians;
    }

    public void setID_Guardians(String ID_Guardians) {
        this.ID_Guardians = ID_Guardians;
    }
}
