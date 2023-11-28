
package com.Entity;

public class Class {

    private String idClass;
    private String className;
    private String idTeacher;
    private String idStudent;
    private int quantity;
    private String note;

    public Class(String idClass, String className, String idTeacher, String idStudent, int quantity, String note) {
        this.idClass = idClass;
        this.className = className;
        this.idTeacher = idTeacher;
        this.idStudent = idStudent;
        this.quantity = quantity;
        this.note = note;
    }

    public String getIdClass() {
        return idClass;
    }

    public void setIdClass(String idClass) {
        this.idClass = idClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(String idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}


