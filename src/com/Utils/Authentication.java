/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Utils;

import com.Entity.Administrators;
import com.Entity.Guardians;
import com.Entity.Staff;
import com.Entity.Teacher;

/**
 *
 * @author Duong Minh Binh
 */
public class Authentication {

    public static Administrators admin = null;
    public static Staff staff = null;
    public static Teacher teacher = null;
    public static Guardians guardians = null;

    //admin
    public static void clear_Admin() {
        Authentication.admin = null;
    }

    public static boolean isLogin_Admin() {
        return Authentication.admin != null;
    }

    public static boolean isManager_Admin() {
        return Authentication.isLogin_Admin();
    }

    //staff
    public static void clear_Staff() {
        Authentication.staff = null;
    }

    public static boolean isLogin_Staff() {
        return Authentication.staff != null;
    }

    public static boolean isManager_Staff() {
        return Authentication.isLogin_Staff();
    }

    //teacher
    public static void clear_Teacher() {
        Authentication.teacher = null;
    }

    public static boolean isLogin_Teacher() {
        return Authentication.teacher != null;
    }

    public static boolean isManager_Teacher() {
        return Authentication.isLogin_Teacher();
    }

    //Guardians
    public static void clear_Guardians() {
        Authentication.guardians = null;
    }

    public static boolean isLogin_Guardians() {
        return Authentication.guardians != null;
    }

    public static boolean isManager_Guardians() {
        return Authentication.isLogin_Guardians();
    }
}
