/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

import java.util.Date;
import java.sql.Time;
/**
 *
 * @author admin
 */
public class Schedule_Staff {

    private String ID_Staff, Day_Of_Week;
    private int ID_Schedule;
    private Date Work_Date;
    private Time End_Time, Start_Time;

    public String getID_Staff() {
        return ID_Staff;
    }

    public void setID_Staff(String ID_Staff) {
        this.ID_Staff = ID_Staff;
    }

    public String getDay_Of_Week() {
        return Day_Of_Week;
    }

    public void setDay_Of_Week(String Day_Of_Week) {
        this.Day_Of_Week = Day_Of_Week;
    }

    public int getID_Schedule() {
        return ID_Schedule;
    }

    public void setID_Schedule(int ID_Schedule) {
        this.ID_Schedule = ID_Schedule;
    }

    public Date getWork_Date() {
        return Work_Date;
    }

    public void setWork_Date(Date Work_Date) {
        this.Work_Date = Work_Date;
    }

    public Time getEnd_Time() {
        return End_Time;
    }

    public void setEnd_Time(Time End_Time) {
        this.End_Time = End_Time;
    }

    public Time getStart_Time() {
        return Start_Time;
    }

    public void setStart_Time(Time Start_Time) {
        this.Start_Time = Start_Time;
    }

    public Schedule_Staff(String ID_Staff, String Day_Of_Week, int ID_Schedule, Date Work_Date, Time End_Time, Time Start_Time) {
        this.ID_Staff = ID_Staff;
        this.Day_Of_Week = Day_Of_Week;
        this.ID_Schedule = ID_Schedule;
        this.Work_Date = Work_Date;
        this.End_Time = End_Time;
        this.Start_Time = Start_Time;
    }

    public Schedule_Staff() {
    }

}
