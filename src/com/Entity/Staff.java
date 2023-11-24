/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Staff {

    private String ID_Staff, Password_Staff, First_Name, Middle_Name, Last_Name,
            Email, Phone_Number, Position, Address_Staff, Avatar, Note;
    private boolean Gender, Status_Staff;
    private int Date_Of_Birth, Month_Of_Birth, Year_Of_Birth;
    private Date Start_Date;

    public Staff() {
    }

    public Staff(String ID_Staff, String Password_Staff, String First_Name, String Middle_Name, String Last_Name, String Email, String Phone_Number, String Position, String Address_Staff, String Avatar, String Note, boolean Gender, boolean Status_Staff, int Date_Of_Birth, int Month_Of_Birth, int Year_Of_Birth, Date Start_Date) {
        this.ID_Staff = ID_Staff;
        this.Password_Staff = Password_Staff;
        this.First_Name = First_Name;
        this.Middle_Name = Middle_Name;
        this.Last_Name = Last_Name;
        this.Email = Email;
        this.Phone_Number = Phone_Number;
        this.Position = Position;
        this.Address_Staff = Address_Staff;
        this.Avatar = Avatar;
        this.Note = Note;
        this.Gender = Gender;
        this.Status_Staff = Status_Staff;
        this.Date_Of_Birth = Date_Of_Birth;
        this.Month_Of_Birth = Month_Of_Birth;
        this.Year_Of_Birth = Year_Of_Birth;
        this.Start_Date = Start_Date;
    }

    public String getID_Staff() {
        return ID_Staff;
    }

    public void setID_Staff(String ID_Staff) {
        this.ID_Staff = ID_Staff;
    }

    public String getPassword_Staff() {
        return Password_Staff;
    }

    public void setPassword_Staff(String Password_Staff) {
        this.Password_Staff = Password_Staff;
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

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getAddress_Staff() {
        return Address_Staff;
    }

    public void setAddress_Staff(String Address_Staff) {
        this.Address_Staff = Address_Staff;
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

    public boolean isStatus_Staff() {
        return Status_Staff;
    }

    public void setStatus_Staff(boolean Status_Staff) {
        this.Status_Staff = Status_Staff;
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
