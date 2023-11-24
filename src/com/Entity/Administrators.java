/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

/**
 *
 * @author admin
 */
public class Administrators {

    private String ID_Administrator, Password_Administrator, First_Name,
            Middle_Name, Last_Name, Email, Phone_Number, Address, Note;
    private boolean Gender;

    public String getID_Administrator() {
        return ID_Administrator;
    }

    public void setID_Administrator(String ID_Administrator) {
        this.ID_Administrator = ID_Administrator;
    }

    public String getPassword_Administrator() {
        return Password_Administrator;
    }

    public void setPassword_Administrator(String Password_Administrator) {
        this.Password_Administrator = Password_Administrator;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
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

    public Administrators(String ID_Administrator, String Password_Administrator, String First_Name, String Middle_Name, String Last_Name, String Email, String Phone_Number, String Address, String Note, boolean Gender) {
        this.ID_Administrator = ID_Administrator;
        this.Password_Administrator = Password_Administrator;
        this.First_Name = First_Name;
        this.Middle_Name = Middle_Name;
        this.Last_Name = Last_Name;
        this.Email = Email;
        this.Phone_Number = Phone_Number;
        this.Address = Address;
        this.Note = Note;
        this.Gender = Gender;
    }

    public Administrators() {
    }
}
