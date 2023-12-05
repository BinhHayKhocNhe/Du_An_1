/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Entity;

import java.util.List;

/**
 *
 * @author admin
 */
public class Subject {

    private String ID_Subject, Subject_Name, Note;

    public String getID_Subject() {
        return ID_Subject;
    }

    public void setID_Subject(String ID_Subject) {
        this.ID_Subject = ID_Subject;
    }

    public String getSubject_Name() {
        return Subject_Name;
    }

    public void setSubject_Name(String Subject_Name) {
        this.Subject_Name = Subject_Name;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public Subject(String ID_Subject, String Subject_Name, String Note) {
        this.ID_Subject = ID_Subject;
        this.Subject_Name = Subject_Name;
        this.Note = Note;
    }

    public Subject() {
    }

}
