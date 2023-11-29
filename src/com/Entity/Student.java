package com.Entity;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Student {

    private String ID_Student, First_Name, Middle_Name, Last_Name, Address_Student,
            Avatar, Note;
    private boolean Gender, Status_Student;
    private int Date_Of_Birth, Month_Of_Birth, Year_Of_Birth;

    public String getID_Student() {
        return ID_Student;
    }

    public void setID_Student(String ID_Student) {
        this.ID_Student = ID_Student;
    }

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String First_Name) {
        this.First_Name = First_Name;
    }

    public String getMiddle_Name() {
        return Middle_Name;
    }

    public void setMiddle_Name(String Middle_Name) {
        this.Middle_Name = Middle_Name;
    }

    public String getLast_Name() {
        return Last_Name;
    }

    public void setLast_Name(String Last_Name) {
        this.Last_Name = Last_Name;
    }

    public String getAddress_Student() {
        return Address_Student;
    }

    public void setAddress_Student(String Address_Student) {
        this.Address_Student = Address_Student;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String Avatar) {
        this.Avatar = Avatar;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean Gender) {
        this.Gender = Gender;
    }

    public boolean isStatus_Student() {
        return Status_Student;
    }

    public void setStatus_Student(boolean Status_Student) {
        this.Status_Student = Status_Student;
    }

    public int getDate_Of_Birth() {
        return Date_Of_Birth;
    }

    public void setDate_Of_Birth(int Date_Of_Birth) {
        this.Date_Of_Birth = Date_Of_Birth;
    }

    public int getMonth_Of_Birth() {
        return Month_Of_Birth;
    }

    public void setMonth_Of_Birth(int Month_Of_Birth) {
        this.Month_Of_Birth = Month_Of_Birth;
    }

    public int getYear_Of_Birth() {
        return Year_Of_Birth;
    }

    public void setYear_Of_Birth(int Year_Of_Birth) {
        this.Year_Of_Birth = Year_Of_Birth;
    }

    public Student(String ID_Student, String First_Name, String Middle_Name, String Last_Name, String Address_Student, String Avatar, String Note, boolean Gender, boolean Status_Student, int Date_Of_Birth, int Month_Of_Birth, int Year_Of_Birth) {
        this.ID_Student = ID_Student;
        this.First_Name = First_Name;
        this.Middle_Name = Middle_Name;
        this.Last_Name = Last_Name;
        this.Address_Student = Address_Student;
        this.Avatar = Avatar;
        this.Note = Note;
        this.Gender = Gender;
        this.Status_Student = Status_Student;
        this.Date_Of_Birth = Date_Of_Birth;
        this.Month_Of_Birth = Month_Of_Birth;
        this.Year_Of_Birth = Year_Of_Birth;
    }

    public Student() {
    }

}
