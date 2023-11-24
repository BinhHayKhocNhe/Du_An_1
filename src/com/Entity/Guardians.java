/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

/**
 *
 * @author admin
 */
public class Guardians {

    private String ID_Guardians, Password_Guardians, First_Name, Middle_Name, Last_Name,
            Email, Phone_Number, Address_Guardians, Job, Note;
    private boolean Gender;

    public String getID_Guardians() {
        return ID_Guardians;
    }

    public void setID_Guardians(String ID_Guardians) {
        this.ID_Guardians = ID_Guardians;
    }

    public String getPassword_Guardians() {
        return Password_Guardians;
    }

    public void setPassword_Guardians(String Password_Guardians) {
        this.Password_Guardians = Password_Guardians;
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

    public String getAddress_Guardians() {
        return Address_Guardians;
    }

    public void setAddress_Guardians(String Address_Guardians) {
        this.Address_Guardians = Address_Guardians;
    }

    public String getJob() {
        return Job;
    }

    public void setJob(String Job) {
        this.Job = Job;
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

    public Guardians(String ID_Guardians, String Password_Guardians, String First_Name, String Middle_Name, String Last_Name, String Email, String Phone_Number, String Address_Guardians, String Job, String Note, boolean Gender) {
        this.ID_Guardians = ID_Guardians;
        this.Password_Guardians = Password_Guardians;
        this.First_Name = First_Name;
        this.Middle_Name = Middle_Name;
        this.Last_Name = Last_Name;
        this.Email = Email;
        this.Phone_Number = Phone_Number;
        this.Address_Guardians = Address_Guardians;
        this.Job = Job;
        this.Note = Note;
        this.Gender = Gender;
    }

    public Guardians() {
    }
}
