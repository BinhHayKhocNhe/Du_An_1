/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

/**
 *
 * @author admin
 */
public class Staff_Salary {
    private String ID_Staff_Salary, ID_Staff, Note;
    private int Month, Year ,Number_Of_Working_Days;
    private float Daily_Wage;

    public String getID_Staff_Salary() {
        return ID_Staff_Salary;
    }

    public void setID_Staff_Salary(String ID_Staff_Salary) {
        this.ID_Staff_Salary = ID_Staff_Salary;
    }

    public String getID_Staff() {
        return ID_Staff;
    }

    public void setID_Staff(String ID_Staff) {
        this.ID_Staff = ID_Staff;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int Month) {
        this.Month = Month;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public int getNumber_Of_Working_Days() {
        return Number_Of_Working_Days;
    }

    public void setNumber_Of_Working_Days(int Number_Of_Working_Days) {
        this.Number_Of_Working_Days = Number_Of_Working_Days;
    }

    public float getDaily_Wage() {
        return Daily_Wage;
    }

    public void setDaily_Wage(float Daily_Wage) {
        this.Daily_Wage = Daily_Wage;
    }

    public Staff_Salary(String ID_Staff_Salary, String ID_Staff, String Note, int Month, int Year, int Number_Of_Working_Days, float Daily_Wage) {
        this.ID_Staff_Salary = ID_Staff_Salary;
        this.ID_Staff = ID_Staff;
        this.Note = Note;
        this.Month = Month;
        this.Year = Year;
        this.Number_Of_Working_Days = Number_Of_Working_Days;
        this.Daily_Wage = Daily_Wage;
    }

    public Staff_Salary() {
    }


}
