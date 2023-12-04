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
    private String ID_Staff_Salary, ID_Staff, Number_Of_Working_Days,  Daily_Wage, Note;
    private int Month, Year;

    public Staff_Salary(String ID_Staff_Salary, String ID_Staff, String Number_Of_Working_Days, String Daily_Wage, String Note, int Month, int Year) {
        this.ID_Staff_Salary = ID_Staff_Salary;
        this.ID_Staff = ID_Staff;
        this.Number_Of_Working_Days = Number_Of_Working_Days;
        this.Daily_Wage = Daily_Wage;
        this.Note = Note;
        this.Month = Month;
        this.Year = Year;
    }

    public Staff_Salary() {
    }

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

    public String getNumber_Of_Working_Days() {
        return Number_Of_Working_Days;
    }

    public void setNumber_Of_Working_Days(String Number_Of_Working_Days) {
        this.Number_Of_Working_Days = Number_Of_Working_Days;
    }

    public String getDaily_Wage() {
        return Daily_Wage;
    }

    public void setDaily_Wage(String Daily_Wage) {
        this.Daily_Wage = Daily_Wage;
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
}
