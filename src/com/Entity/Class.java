
package com.Entity;

public class Class {


    private String ID_Class;
    private String Class_Name;
    private String ID_Teacher;
    private String ID_Student;
    private int Quantity;
    private String Note;

    public String getID_Class() {
        return ID_Class;
    }

    public void setID_Class(String ID_Class) {
        this.ID_Class = ID_Class;
    }

    public String getClass_Name() {
        return Class_Name;
    }

    public void setClass_Name(String Class_Name) {
        this.Class_Name = Class_Name;
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

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public Class(String ID_Class, String Class_Name, String ID_Teacher, String ID_Student, int Quantity, String Note) {
        this.ID_Class = ID_Class;
        this.Class_Name = Class_Name;
        this.ID_Teacher = ID_Teacher;
        this.ID_Student = ID_Student;
        this.Quantity = Quantity;
        this.Note = Note;
    }

    public Class() {
    }
}


