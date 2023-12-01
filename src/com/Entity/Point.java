package com.Entity;

public class Point {

    private String ID_Student;
    private String ID_Class;
    private String ID_Subject;
    private String ID_Teacher;
    private int Year;
    private float Point;
    private String Course_Name;
    private String Note;

    public Point() {
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

    public String getID_Teacher() {
        return ID_Teacher;
    }

    public void setID_Teacher(String ID_Teacher) {
        this.ID_Teacher = ID_Teacher;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public float getPoint() {
        return Point;
    }

    public void setPoint(float Point) {
        this.Point = Point;
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

    public Point(String ID_Student, String ID_Class, String ID_Subject, String ID_Teacher, int Year, float Point, String Course_Name, String Note) {
        this.ID_Student = ID_Student;
        this.ID_Class = ID_Class;
        this.ID_Subject = ID_Subject;
        this.ID_Teacher = ID_Teacher;
        this.Year = Year;
        this.Point = Point;
        this.Course_Name = Course_Name;
        this.Note = Note;
    }
}
