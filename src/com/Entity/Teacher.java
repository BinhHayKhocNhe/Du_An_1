package com.Entity;

import java.util.Date;

public class Teacher {

    private String ID_Teacher, Password_Teacher, First_Name, Middle_Name, Last_Name,
            Email, Phone_Number, Level_Teacher, Address_Teacher, Avatar, Note;
    private boolean Gender, Status_Teacher;
    private int Date_Of_Birth, Month_Of_Birth, Year_Of_Birth;
    private Date Start_Date;

    public Teacher() {
    }

    @Override
    public String toString() {
        return ID_Teacher;
    }

    public Teacher(String ID_Teacher, String Password_Teacher, String First_Name, String Middle_Name, String Last_Name, String Email, String Phone_Number, String Level_Teacher, String Address_Teacher, String Avatar, String Note, boolean Gender, boolean Status_Teacher, int Date_Of_Birth, int Month_Of_Birth, int Year_Of_Birth, Date Start_Date) {
        this.ID_Teacher = ID_Teacher;
        this.Password_Teacher = Password_Teacher;
        this.First_Name = First_Name;
        this.Middle_Name = Middle_Name;
        this.Last_Name = Last_Name;
        this.Email = Email;
        this.Phone_Number = Phone_Number;
        this.Level_Teacher = Level_Teacher;
        this.Address_Teacher = Address_Teacher;
        this.Avatar = Avatar;
        this.Note = Note;
        this.Gender = Gender;
        this.Status_Teacher = Status_Teacher;
        this.Date_Of_Birth = Date_Of_Birth;
        this.Month_Of_Birth = Month_Of_Birth;
        this.Year_Of_Birth = Year_Of_Birth;
        this.Start_Date = Start_Date;
    }

    public String getID_Teacher() {
        return ID_Teacher;
    }

    public void setID_Teacher(String ID_Teacher) {
        this.ID_Teacher = ID_Teacher;
    }

    public String getPassword_Teacher() {
        return Password_Teacher;
    }

    public void setPassword_Teacher(String Password_Teacher) {
        this.Password_Teacher = Password_Teacher;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String Phone_Number) {
        this.Phone_Number = Phone_Number;
    }

    public String getLevel_Teacher() {
        return Level_Teacher;
    }

    public void setLevel_Teacher(String Level_Teacher) {
        this.Level_Teacher = Level_Teacher;
    }

    public String getAddress_Teacher() {
        return Address_Teacher;
    }

    public void setAddress_Staff(String Address_Staff) {
        this.Address_Teacher = Address_Teacher;
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

    public boolean isStatus_Teacher() {
        return Status_Teacher;
    }

    public void setStatus_Teacher(boolean Status_Teacher) {
        this.Status_Teacher = Status_Teacher;
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

    public Date getStart_Date() {
        return Start_Date;
    }

    public void setStart_Date(Date Start_Date) {
        this.Start_Date = Start_Date;
    }

}
