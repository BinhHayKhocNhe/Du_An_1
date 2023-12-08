/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Main;

import com.DAO.Administrators_DAO;
import com.DAO.BonusDAO;
import com.DAO.ClassDAO;
import com.DAO.Course_Relationship_DAO;
import com.DAO.CoursesDAO;
import com.DAO.Guardian_Student_RelationshipDAO;
import com.DAO.Guardians_DAO;
import com.DAO.PointDAO;
import com.DAO.ScheduleDAO;
import com.DAO.StaffDAO;
import com.DAO.StudentDAO;
import com.DAO.SubjectDAO;
import com.DAO.Teacher_DAO;
import com.Entity.Administrators;
import com.Entity.Bonus;
import com.Entity.Course_Relationship;
import com.Entity.Courses;
import com.Entity.Guardian_Student_Relationship;
import com.Entity.Guardians;
import com.Entity.Staff;
import com.Entity.Class;
import com.Entity.Point;
import com.Entity.Schedule;
import com.Entity.Student;
import com.Entity.Subject;
import com.Entity.Teacher;
import com.Utils.Authentication;
import com.Utils.ExcelUtils;
import com.Utils.IsValidForm;
import com.Utils.Message;
import com.Utils.QR_Code_Util;
import com.Utils.XDate;
import com.Utils.XImage;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Year;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

/**
 *
 * @author User
 */
public class Menu_Admin extends javax.swing.JFrame {

    private int x = 210;    //chieu rong
    private int y = 800;    //chieu cao
    private Administrators_DAO adminDAO = new Administrators_DAO();
    private Teacher_DAO teacher_DAO = new Teacher_DAO();
    private StaffDAO staff_DAO = new StaffDAO();
    private Guardians_DAO guardians_DAO = new Guardians_DAO();
    private DefaultTableModel tableModelTeacher = new DefaultTableModel();
    private DefaultTableModel tableModelResetPassTeacher = new DefaultTableModel();
    private DefaultTableModel tableModelStaff = new DefaultTableModel();
    private DefaultTableModel tableModelResetPassStaff = new DefaultTableModel();
    private JFileChooser fileChooser = new JFileChooser();
    private StudentDAO studentDAO = new StudentDAO();
    private DefaultTableModel tableModelStudent = new DefaultTableModel();
    private DefaultTableModel tableModelParent = new DefaultTableModel();
    private DefaultTableModel tableModelResetPassParent = new DefaultTableModel();
    private DefaultTableModel tableModelRelationship = new DefaultTableModel();
    private Guardian_Student_RelationshipDAO guardian_Student_RelationshipDAO = new Guardian_Student_RelationshipDAO();
    private DefaultTableModel tableModelCourse = new DefaultTableModel();
    private CoursesDAO coursesDAO = new CoursesDAO();
    private DefaultTableModel tableModelCourse_Information = new DefaultTableModel();
    private Course_Relationship_DAO course_Relationship_DAO = new Course_Relationship_DAO();
    private DefaultTableModel tableModelClass = new DefaultTableModel();
    private ClassDAO classDAO = new ClassDAO();
    private SubjectDAO subjectDAO = new SubjectDAO();
    private DefaultTableModel tableModelSubject = new DefaultTableModel();
    private DefaultTableModel tableModelSchedule = new DefaultTableModel();
    private DefaultTableModel tableModelPoint = new DefaultTableModel();
    private ScheduleDAO scheduleDAO = new ScheduleDAO();
    private PointDAO pointDAO = new PointDAO();
    private DefaultTableModel tableModelBonus = new DefaultTableModel();
    private BonusDAO bonusDAO = new BonusDAO();

    /**
     * Creates new form Menu
     */
    public Menu_Admin() {
        initComponents();
        this.setResizable(false);
        jplSlideMenu.setSize(210, 600);
        this.setCardFalse();
        this.setIconImage(XImage.getAppIcon());
        this.setTitle("Group 5 - Viet Duc School");
        this.txtID.setEditable(false);
        this.txtStartDateTeacher.setEditable(false);
        this.txtScheduleCreatedDate.setEditable(false);
        this.txtCurrentStudents.setEditable(false);
        this.txtIDTeacherPoint.setEditable(false);
        this.rdONTeacher.setSelected(true);
        this.rdMaleTeacher.setSelected(true);
        this.rdONStaff.setSelected(true);
        this.rdMaleStaff.setSelected(true);
        this.rdONStudent.setSelected(true);
        this.rdMaleParent.setSelected(true);
        this.rdMaleStudent.setSelected(true);
        this.txtStartDateStaff.setEditable(false);
        this.btnAddPoint.setEnabled(false);
        this.uploadComboboxTeacher();
        this.uploadComboboxStaff();
        this.initTableTeacher();
        this.initTableStaff();
        this.initTableParent();
        this.initTableRelationship();
        this.initTableCourse();
        this.initTableCourseInformation();
        this.initTableClass();
        this.initTableSubject();
        this.initTableSchedule();
        this.initTablePoint();
        this.initTableBonus();
        this.setFormAdmin();
        this.fillFindResetPassTeacher();
        this.fillFindTableTeacher();
        this.fillTableStaff();
        this.fillFindResetPassStaff();
        this.initTableStudent();
        this.fillTableStudent();
        this.fillTableParent();
        this.fillTableResetPassParent();
        this.fillComboboxBonus();
        this.uploadComboboxStudent();
        this.uploadComboboxParent();
        this.uploadComboboxCourse();
        this.uploadComboboxCourseInformation();
        this.fillComboboxPoint();
        this.fillComboboxSchedule();
        this.fillTableRelationship();
        this.fillTableCourse();
        this.fillTableCourseInformation();
        this.fillTableClass();
        this.fillTableSubject();
        this.fillTableSchedule();
        this.fillTablePoint();
        this.fillTableBonus();

    }

    private void openMenu() {
        jplSlideMenu.setSize(x, y);
        if (x == 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i <= 210; i++) {
                            jplSlideMenu.setSize(i, y);
                            Thread.sleep(1);
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
            x = 210;
        }
    }

    private void closeMenu() {
        jplSlideMenu.setSize(x, y);
        this.setTimeSlide();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        buttonGroup4 = new javax.swing.ButtonGroup();
        buttonGroup5 = new javax.swing.ButtonGroup();
        buttonGroup6 = new javax.swing.ButtonGroup();
        buttonGroup7 = new javax.swing.ButtonGroup();
        buttonGroup8 = new javax.swing.ButtonGroup();
        jComboBox3 = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jplSlideMenu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lbNameAdmin = new javax.swing.JLabel();
        lblCloseMenu = new javax.swing.JLabel();
        lbGuardians = new javax.swing.JLabel();
        lblTrangChu = new javax.swing.JLabel();
        lblTaiKhoan = new javax.swing.JLabel();
        lbTeacher = new javax.swing.JLabel();
        lblthoat = new javax.swing.JLabel();
        lbQRCode = new javax.swing.JLabel();
        lbStaff = new javax.swing.JLabel();
        lbStudent = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jpllMenuBar = new javax.swing.JPanel();
        lblOpenMenu = new javax.swing.JLabel();
        jplTitle = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        jplMain = new javax.swing.JPanel();
        cardTrangChu = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        cardInformation = new javax.swing.JPanel();
        tabAccount = new javax.swing.JTabbedPane();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtMidName = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        rdFemale = new javax.swing.JRadioButton();
        rdMale = new javax.swing.JRadioButton();
        jLabel18 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        btnUpdate = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtCurrentPass = new javax.swing.JPasswordField();
        txtNewPass = new javax.swing.JPasswordField();
        txtEnterPass = new javax.swing.JPasswordField();
        btnAccept = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lbShowCurrentPass = new javax.swing.JLabel();
        lbShowEnterPass = new javax.swing.JLabel();
        lbShowNewPass = new javax.swing.JLabel();
        cardListTeacher = new javax.swing.JPanel();
        tabTeacher = new javax.swing.JTabbedPane();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        lbAvatarTeacher = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtIDTeacher = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        txtFirstNameTeacher = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        txtLastNameTeacher = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        txtMidNameTeacher = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtEmailTeacher = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtPhoneTeacher = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        rdMaleTeacher = new javax.swing.JRadioButton();
        rdFemaleTeacher = new javax.swing.JRadioButton();
        jLabel31 = new javax.swing.JLabel();
        btnUploadImgTeacher = new javax.swing.JButton();
        rdONTeacher = new javax.swing.JRadioButton();
        rdOFFTeacher = new javax.swing.JRadioButton();
        jLabel32 = new javax.swing.JLabel();
        cbLevelTeacher = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        cbYearTeacher = new javax.swing.JComboBox<>();
        cbMonthTeacher = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        cbDateTeacher = new javax.swing.JComboBox<>();
        jLabel36 = new javax.swing.JLabel();
        txtStartDateTeacher = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNoteTeacher = new javax.swing.JTextArea();
        btnAddTeacher = new javax.swing.JButton();
        btnUpdateTeacher = new javax.swing.JButton();
        btnResetFormTeacher = new javax.swing.JButton();
        txtAddressTeacher = new javax.swing.JTextField();
        jLayeredPane4 = new javax.swing.JLayeredPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableTeacher = new javax.swing.JTable();
        txtFindTeacher = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnExportTeacher = new javax.swing.JButton();
        btnImportTeacher = new javax.swing.JButton();
        jLayeredPane5 = new javax.swing.JLayeredPane();
        jLabel39 = new javax.swing.JLabel();
        txtIDResetPassTeacher = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableResetPassTeacher = new javax.swing.JTable();
        btnResetPassTeacher = new javax.swing.JButton();
        btnExitResetPassTeacher = new javax.swing.JButton();
        cardListStaff = new javax.swing.JPanel();
        tabStaff = new javax.swing.JTabbedPane();
        jLayeredPane6 = new javax.swing.JLayeredPane();
        lbAvatarStaff = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtIDStaff = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        txtFirstNameStaff = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        txtLastNameStaff = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        txtMidNameStaff = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        txtEmailStaff = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        txtPhoneStaff = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        rdMaleStaff = new javax.swing.JRadioButton();
        rdFemaleStaff = new javax.swing.JRadioButton();
        jLabel47 = new javax.swing.JLabel();
        btnUploadImgStaff = new javax.swing.JButton();
        rdONStaff = new javax.swing.JRadioButton();
        rdOFFStaff = new javax.swing.JRadioButton();
        jLabel48 = new javax.swing.JLabel();
        cbPositionStaff = new javax.swing.JComboBox<>();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        cbYearStaff = new javax.swing.JComboBox<>();
        cbMonthStaff = new javax.swing.JComboBox<>();
        jLabel51 = new javax.swing.JLabel();
        cbDateStaff = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        txtStartDateStaff = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtNoteStaff = new javax.swing.JTextArea();
        btnAddStaff = new javax.swing.JButton();
        btnUpdateStaff = new javax.swing.JButton();
        btnResetFormStaff = new javax.swing.JButton();
        txtAddressStaff = new javax.swing.JTextField();
        jLayeredPane7 = new javax.swing.JLayeredPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        tableStaff = new javax.swing.JTable();
        txtFindStaff = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLayeredPane8 = new javax.swing.JLayeredPane();
        jLabel54 = new javax.swing.JLabel();
        txtIDResetPassStaff = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableResetPassStaff = new javax.swing.JTable();
        btnResetPassStaff = new javax.swing.JButton();
        btnExitResetPassStaff = new javax.swing.JButton();
        cardListStudent = new javax.swing.JPanel();
        tabStudent = new javax.swing.JTabbedPane();
        jLayeredPane9 = new javax.swing.JLayeredPane();
        lbAvatarStudent = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        txtIDStudent = new javax.swing.JTextField();
        jLabel56 = new javax.swing.JLabel();
        txtFirstNameStudent = new javax.swing.JTextField();
        jLabel57 = new javax.swing.JLabel();
        txtLastNameStudent = new javax.swing.JTextField();
        txtMidNameStudent = new javax.swing.JTextField();
        jLabel59 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        rdMaleStudent = new javax.swing.JRadioButton();
        rdFemaleStudent = new javax.swing.JRadioButton();
        jLabel63 = new javax.swing.JLabel();
        btnUploadImgStudent = new javax.swing.JButton();
        rdONStudent = new javax.swing.JRadioButton();
        rdOFFStudent = new javax.swing.JRadioButton();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        cbYearStudent = new javax.swing.JComboBox<>();
        cbMonthStudent = new javax.swing.JComboBox<>();
        jLabel67 = new javax.swing.JLabel();
        cbDateStudent = new javax.swing.JComboBox<>();
        jLabel69 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtNoteStudent = new javax.swing.JTextArea();
        btnAddStudent = new javax.swing.JButton();
        btnUpdateStudent = new javax.swing.JButton();
        btnResetFormStudent = new javax.swing.JButton();
        txtAddressStudent = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        txtClassStudent = new javax.swing.JTextField();
        jLayeredPane10 = new javax.swing.JLayeredPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        tableStudent = new javax.swing.JTable();
        txtFindStudent = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLayeredPane20 = new javax.swing.JLayeredPane();
        jScrollPane24 = new javax.swing.JScrollPane();
        tablePoint = new javax.swing.JTable();
        jLabel80 = new javax.swing.JLabel();
        cbIDClassPoint = new javax.swing.JComboBox<>();
        jLabel83 = new javax.swing.JLabel();
        cbIDStudentPoint = new javax.swing.JComboBox<>();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        cbYearPoint = new javax.swing.JComboBox<>();
        cbIDSubjectPoint = new javax.swing.JComboBox<>();
        jLabel86 = new javax.swing.JLabel();
        jScrollPane25 = new javax.swing.JScrollPane();
        txtNotePoint = new javax.swing.JTextArea();
        jLabel87 = new javax.swing.JLabel();
        cbCourseNamePoint = new javax.swing.JComboBox<>();
        jLabel88 = new javax.swing.JLabel();
        txtPoint = new javax.swing.JTextField();
        jLabel89 = new javax.swing.JLabel();
        txtIDTeacherPoint = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        txtFindPoint = new javax.swing.JTextField();
        btnAddPoint = new javax.swing.JButton();
        btnUpdatePoint = new javax.swing.JButton();
        btnResetPoint = new javax.swing.JButton();
        btnImportPoint = new javax.swing.JButton();
        btnExportPoint = new javax.swing.JButton();
        jLayeredPane21 = new javax.swing.JLayeredPane();
        jLabel91 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        cbYearBonus = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jLabel122 = new javax.swing.JLabel();
        jScrollPane26 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane27 = new javax.swing.JScrollPane();
        tableBonus = new javax.swing.JTable();
        jLabel123 = new javax.swing.JLabel();
        cbBonusName = new javax.swing.JComboBox<>();
        jLabel124 = new javax.swing.JLabel();
        cblevelBnous = new javax.swing.JComboBox<>();
        txtFindBonus = new javax.swing.JTextField();
        jLabel125 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        cbStudentBonus = new javax.swing.JComboBox<>();
        cardListParent = new javax.swing.JPanel();
        tabParent = new javax.swing.JTabbedPane();
        jLayeredPane11 = new javax.swing.JLayeredPane();
        jLabel58 = new javax.swing.JLabel();
        txtIDParent = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        txtFirstNameParent = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        txtLastNameParent = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        txtMidNameParent = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        txtEmailParent = new javax.swing.JTextField();
        jLabel72 = new javax.swing.JLabel();
        txtPhoneParent = new javax.swing.JTextField();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        rdMaleParent = new javax.swing.JRadioButton();
        rdFemaleParent = new javax.swing.JRadioButton();
        jLabel76 = new javax.swing.JLabel();
        cbJobParent = new javax.swing.JComboBox<>();
        jLabel81 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        txtNoteParent = new javax.swing.JTextArea();
        btnAddParent = new javax.swing.JButton();
        btnUpdateParent = new javax.swing.JButton();
        btnResetFormParent = new javax.swing.JButton();
        txtAddressParent = new javax.swing.JTextField();
        jLayeredPane12 = new javax.swing.JLayeredPane();
        jScrollPane12 = new javax.swing.JScrollPane();
        tableParent = new javax.swing.JTable();
        txtFindParent = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLayeredPane13 = new javax.swing.JLayeredPane();
        jLabel82 = new javax.swing.JLabel();
        txtIDResetPassParent = new javax.swing.JTextField();
        jScrollPane13 = new javax.swing.JScrollPane();
        tableResetPassParent = new javax.swing.JTable();
        btnResetPassParent = new javax.swing.JButton();
        btnExitResetPassParent = new javax.swing.JButton();
        jLayeredPane14 = new javax.swing.JLayeredPane();
        jLabel7 = new javax.swing.JLabel();
        txtFindRelationship = new javax.swing.JTextField();
        jScrollPane14 = new javax.swing.JScrollPane();
        tableRelationship = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnDeleteRelationship = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtRelationshipGuadians = new javax.swing.JTextField();
        txtRelationshipStudent = new javax.swing.JTextField();
        btnResetFormReletionship = new javax.swing.JButton();
        cardLearningManagement = new javax.swing.JPanel();
        tabCourse = new javax.swing.JTabbedPane();
        jLayeredPane15 = new javax.swing.JLayeredPane();
        jLabel75 = new javax.swing.JLabel();
        txtIDCourse = new javax.swing.JTextField();
        jLabel77 = new javax.swing.JLabel();
        txtFindCourse = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        cbCourse = new javax.swing.JComboBox<>();
        jLabel92 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        txtNoteCourse = new javax.swing.JTextArea();
        btnAddCourse = new javax.swing.JButton();
        btnUpdateTeacher1 = new javax.swing.JButton();
        btnResetFormCourse = new javax.swing.JButton();
        jScrollPane18 = new javax.swing.JScrollPane();
        tableCourse = new javax.swing.JTable();
        jLabel78 = new javax.swing.JLabel();
        txtYearCourse = new javax.swing.JTextField();
        jLayeredPane16 = new javax.swing.JLayeredPane();
        jLabel93 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tableCourse_Relationship = new javax.swing.JTable();
        jLabel100 = new javax.swing.JLabel();
        txtFindCourseInformation = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        btnAddCourse_Relationship = new javax.swing.JButton();
        btnDeleteCourse_Relationship = new javax.swing.JButton();
        btnResetFormCourseInformation = new javax.swing.JButton();
        cbTeacherCourse = new javax.swing.JComboBox<>();
        cbClassCourse = new javax.swing.JComboBox<>();
        cbStudentCourse = new javax.swing.JComboBox<>();
        cbCourse_Infor = new javax.swing.JComboBox<>();
        jLayeredPane17 = new javax.swing.JLayeredPane();
        jLabel94 = new javax.swing.JLabel();
        txtIDClass = new javax.swing.JTextField();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        txtClassName = new javax.swing.JTextField();
        jLabel97 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        jLabel98 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        txtNoteClass = new javax.swing.JTextArea();
        jScrollPane17 = new javax.swing.JScrollPane();
        tableClass = new javax.swing.JTable();
        txtFindClass = new javax.swing.JTextField();
        jLabel99 = new javax.swing.JLabel();
        btnAddClass = new javax.swing.JButton();
        btnUpdateClass = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        cbClassTeacher = new javax.swing.JComboBox<>();
        jLabel118 = new javax.swing.JLabel();
        txtCurrentStudents = new javax.swing.JTextField();
        jLayeredPane18 = new javax.swing.JLayeredPane();
        jLabel104 = new javax.swing.JLabel();
        txtNameSubject = new javax.swing.JTextField();
        jLabel105 = new javax.swing.JLabel();
        txtIDSubject = new javax.swing.JTextField();
        jLabel106 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        txtNoteSubject = new javax.swing.JTextArea();
        jLabel107 = new javax.swing.JLabel();
        txtFindSubject = new javax.swing.JTextField();
        jScrollPane21 = new javax.swing.JScrollPane();
        tableSubject = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnResetSubject = new javax.swing.JButton();
        jLayeredPane19 = new javax.swing.JLayeredPane();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        cbScheduleClass = new javax.swing.JComboBox<>();
        cbScheduleCourse = new javax.swing.JComboBox<>();
        cbScheduleSubject = new javax.swing.JComboBox<>();
        cbScheduleTeacher = new javax.swing.JComboBox<>();
        cbScheduleStudent = new javax.swing.JComboBox<>();
        jLabel113 = new javax.swing.JLabel();
        cbScheduleCourseName = new javax.swing.JComboBox<>();
        jLabel114 = new javax.swing.JLabel();
        txtScheduleCreatedDate = new javax.swing.JTextField();
        jLabel115 = new javax.swing.JLabel();
        jLabel116 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        txtScheduleNote = new javax.swing.JTextArea();
        txtScheduleSchoolDay = new javax.swing.JTextField();
        jScrollPane23 = new javax.swing.JScrollPane();
        tableSchedule = new javax.swing.JTable();
        jLabel117 = new javax.swing.JLabel();
        txtFindSchedule = new javax.swing.JTextField();
        btnAddSchedule = new javax.swing.JButton();
        btnUpdateSchedule = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnDeleteSchedule = new javax.swing.JButton();
        cardQRCode = new javax.swing.JPanel();
        QRCode = new javax.swing.JLabel();
        cardDoimk = new javax.swing.JPanel();

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jplSlideMenu.setBackground(new java.awt.Color(255, 255, 255));
        jplSlideMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jplSlideMenu.setPreferredSize(new java.awt.Dimension(190, 590));
        jplSlideMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/user.png"))); // NOI18N

        lbNameAdmin.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lbNameAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbNameAdmin.setText("Admin");

        lblCloseMenu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblCloseMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCloseMenu.setText("X");
        lblCloseMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbNameAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCloseMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCloseMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbNameAdmin)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jplSlideMenu.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 130));

        lbGuardians.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbGuardians.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbGuardians.setText("Guardians");
        lbGuardians.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbGuardiansMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lbGuardians, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 190, 30));

        lblTrangChu.setBackground(new java.awt.Color(255, 255, 255));
        lblTrangChu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrangChu.setText("Home");
        lblTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lblTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 190, 30));

        lblTaiKhoan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTaiKhoan.setText("Account");
        lblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTaiKhoanMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lblTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 190, 30));

        lbTeacher.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbTeacher.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTeacher.setText("Teacher");
        lbTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTeacherMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lbTeacher, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 190, 30));

        lblthoat.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblthoat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblthoat.setText("Log Out");
        lblthoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblthoatMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lblthoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 540, 190, 30));

        lbQRCode.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbQRCode.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbQRCode.setText("QR Code");
        lbQRCode.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbQRCodeMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lbQRCode, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 190, 30));

        lbStaff.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbStaff.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbStaff.setText("Staff");
        lbStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbStaffMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lbStaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 190, 30));

        lbStudent.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbStudent.setText("Student");
        lbStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbStudentMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lbStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 190, 30));

        jLabel11.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Learning Management");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        jplSlideMenu.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 190, 30));
        jplSlideMenu.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 210, -1));

        jPanel1.add(jplSlideMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 0, 600));

        jpllMenuBar.setBackground(new java.awt.Color(255, 255, 255));

        lblOpenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/menu.png"))); // NOI18N
        lblOpenMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOpenMenuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jpllMenuBarLayout = new javax.swing.GroupLayout(jpllMenuBar);
        jpllMenuBar.setLayout(jpllMenuBarLayout);
        jpllMenuBarLayout.setHorizontalGroup(
            jpllMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpllMenuBarLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblOpenMenu)
                .addContainerGap(891, Short.MAX_VALUE))
        );
        jpllMenuBarLayout.setVerticalGroup(
            jpllMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblOpenMenu)
        );

        jPanel1.add(jpllMenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 940, 30));

        jplTitle.setBackground(new java.awt.Color(0, 168, 255));

        lbTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Viet Duc School");

        javax.swing.GroupLayout jplTitleLayout = new javax.swing.GroupLayout(jplTitle);
        jplTitle.setLayout(jplTitleLayout);
        jplTitleLayout.setHorizontalGroup(
            jplTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 1058, Short.MAX_VALUE)
        );
        jplTitleLayout.setVerticalGroup(
            jplTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jplTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1050, 30));

        jplMain.setBackground(new java.awt.Color(255, 255, 255));
        jplMain.setLayout(new java.awt.CardLayout());

        cardTrangChu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/education.png"))); // NOI18N

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(204, 204, 204));
        jTextPane1.setText("Congratulations on successfully logging in! \nYou have officially become a part of our academic community. Get ready for dynamic learning journeys, where new knowledge awaits, and personal development takes center stage.\nDive into the exciting classes, participate in activities, and share your thoughts. We believe each step you take will bring you closer to great achievements.\nCheers to starting your new learning journey with excitement!\n\nEnvironmental requirements:\n1. Any operating system\n2. JDK 1.8 or higher\n3. SQL Server 2008 or later");
        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout cardTrangChuLayout = new javax.swing.GroupLayout(cardTrangChu);
        cardTrangChu.setLayout(cardTrangChuLayout);
        cardTrangChuLayout.setHorizontalGroup(
            cardTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTrangChuLayout.createSequentialGroup()
                .addContainerGap(275, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
        );
        cardTrangChuLayout.setVerticalGroup(
            cardTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTrangChuLayout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addGroup(cardTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addContainerGap(190, Short.MAX_VALUE))
        );

        jplMain.add(cardTrangChu, "card2");

        cardInformation.setBackground(new java.awt.Color(255, 255, 255));

        tabAccount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("ID:");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("First Name:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Middle Name:");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Last Name:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Email:");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Phone:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Gender:");

        buttonGroup1.add(rdFemale);
        rdFemale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdFemale.setText("Female");

        buttonGroup1.add(rdMale);
        rdMale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdMale.setText("Male");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Address:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Note:");

        txtNote.setColumns(20);
        txtNote.setRows(5);
        jScrollPane2.setViewportView(txtNote);

        btnUpdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/edit-24px.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnRefresh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnRefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        jLayeredPane3.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtID, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtFirstName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtLastName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtMidName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtEmail, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtPhone, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(rdFemale, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(rdMale, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtAddress, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jScrollPane2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(btnUpdate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(btnRefresh, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane3Layout = new javax.swing.GroupLayout(jLayeredPane3);
        jLayeredPane3.setLayout(jLayeredPane3Layout);
        jLayeredPane3Layout.setHorizontalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(23, 23, 23)
                                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtMidName, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                    .addComponent(txtFirstName)
                                    .addComponent(txtLastName)))
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(70, 70, 70)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(53, 53, 53)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                            .addComponent(rdMale, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(rdFemale)
                                            .addGap(21, 21, 21)))))))
                    .addGroup(jLayeredPane3Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addGap(78, 78, 78)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnRefresh))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(57, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel15)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel16)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMidName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17)
                    .addComponent(rdFemale)
                    .addComponent(rdMale))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel18)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnRefresh))
                .addContainerGap(64, Short.MAX_VALUE))
        );

        tabAccount.addTab("INFORMATION", jLayeredPane3);

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Current password:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("New password:");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Enter the password:");

        btnAccept.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAccept.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Accept.png"))); // NOI18N
        btnAccept.setText("Accept");
        btnAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAcceptActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Exit.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        lbShowCurrentPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/show.png"))); // NOI18N
        lbShowCurrentPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbShowCurrentPassMouseClicked(evt);
            }
        });

        lbShowEnterPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/show.png"))); // NOI18N
        lbShowEnterPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbShowEnterPassMouseClicked(evt);
            }
        });

        lbShowNewPass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/show.png"))); // NOI18N
        lbShowNewPass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbShowNewPassMouseClicked(evt);
            }
        });

        jLayeredPane1.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtCurrentPass, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtNewPass, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtEnterPass, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnAccept, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnExit, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lbShowCurrentPass, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lbShowEnterPass, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lbShowNewPass, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(jLabel22))
                        .addGap(43, 43, 43)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(btnAccept)
                                .addGap(18, 18, 18)
                                .addComponent(btnExit))
                            .addComponent(txtNewPass, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(txtCurrentPass)
                            .addComponent(txtEnterPass)))
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbShowCurrentPass)
                    .addComponent(lbShowEnterPass)
                    .addComponent(lbShowNewPass))
                .addContainerGap(190, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtCurrentPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbShowCurrentPass))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbShowNewPass))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(txtEnterPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbShowEnterPass))
                .addGap(45, 45, 45)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAccept)
                    .addComponent(btnExit))
                .addContainerGap(200, Short.MAX_VALUE))
        );

        tabAccount.addTab("CHANGE PASSWORD", jLayeredPane1);

        javax.swing.GroupLayout cardInformationLayout = new javax.swing.GroupLayout(cardInformation);
        cardInformation.setLayout(cardInformationLayout);
        cardInformationLayout.setHorizontalGroup(
            cardInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardInformationLayout.createSequentialGroup()
                .addContainerGap(235, Short.MAX_VALUE)
                .addComponent(tabAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 719, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );
        cardInformationLayout.setVerticalGroup(
            cardInformationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabAccount, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jplMain.add(cardInformation, "card3");

        cardListTeacher.setBackground(new java.awt.Color(255, 255, 255));
        cardListTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardListTeacherMouseClicked(evt);
            }
        });

        tabTeacher.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabTeacher.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tabTeacherAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tabTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabTeacherMouseClicked(evt);
            }
        });

        lbAvatarTeacher.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("ID Teacher:");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("First Name:");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Middle Name:");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Email:");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Last Name:");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Phone:");

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Address:");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Gender:");

        buttonGroup2.add(rdMaleTeacher);
        rdMaleTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdMaleTeacher.setText("Male");

        buttonGroup2.add(rdFemaleTeacher);
        rdFemaleTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdFemaleTeacher.setText("Female");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Status:");

        btnUploadImgTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUploadImgTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Upload.png"))); // NOI18N
        btnUploadImgTeacher.setText("Upload");
        btnUploadImgTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImgTeacherActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdONTeacher);
        rdONTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdONTeacher.setText("ON");

        buttonGroup3.add(rdOFFTeacher);
        rdOFFTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdOFFTeacher.setText("OFF");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Level:");

        cbLevelTeacher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Year of birth:");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("Month of birth:");

        cbYearTeacher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbMonthTeacher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("Date of birth:");

        cbDateTeacher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel36.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel36.setText("Start Date:");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel37.setText("Note:");

        txtNoteTeacher.setColumns(20);
        txtNoteTeacher.setRows(5);
        jScrollPane3.setViewportView(txtNoteTeacher);

        btnAddTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        btnAddTeacher.setText("ADD");
        btnAddTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddTeacherActionPerformed(evt);
            }
        });

        btnUpdateTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdateTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/edit-24px.png"))); // NOI18N
        btnUpdateTeacher.setText("UPDATE");
        btnUpdateTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTeacherActionPerformed(evt);
            }
        });

        btnResetFormTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetFormTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        btnResetFormTeacher.setText("REFRESH");
        btnResetFormTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFormTeacherActionPerformed(evt);
            }
        });

        jLayeredPane2.setLayer(lbAvatarTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtIDTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtFirstNameTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtLastNameTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel26, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtMidNameTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel27, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtEmailTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel28, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtPhoneTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel29, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel30, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rdMaleTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rdFemaleTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel31, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnUploadImgTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rdONTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(rdOFFTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel32, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(cbLevelTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel33, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel34, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(cbYearTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(cbMonthTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel35, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(cbDateTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel36, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtStartDateTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jLabel37, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnAddTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnUpdateTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(btnResetFormTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane2.setLayer(txtAddressTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(jLabel37)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnAddTeacher)
                                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                            .addComponent(jLabel33)
                                            .addGap(18, 18, 18)
                                            .addComponent(cbYearTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                            .addComponent(jLabel32)
                                            .addGap(18, 18, 18)
                                            .addComponent(cbLevelTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                            .addComponent(jLabel34)
                                            .addGap(18, 18, 18)
                                            .addComponent(cbMonthTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                            .addComponent(jLabel36)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtStartDateTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateTeacher)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetFormTeacher))
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel27))
                                .addGap(18, 18, 18)
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtFirstNameTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                            .addComponent(txtMidNameTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                            .addComponent(txtIDTeacher))
                                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addComponent(jLabel26)
                                                .addGap(38, 38, 38)
                                                .addComponent(txtEmailTeacher))
                                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                                        .addComponent(jLabel31)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(rdONTeacher)
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel35)
                                                            .addComponent(rdOFFTeacher)))
                                                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel29)
                                                            .addComponent(jLabel28))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtAddressTeacher, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                                            .addComponent(txtPhoneTeacher)))))))
                                    .addComponent(txtLastNameTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addComponent(jLabel30)
                                .addGap(18, 18, 18)
                                .addComponent(rdMaleTeacher)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdFemaleTeacher)))
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbAvatarTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))
                            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                        .addGap(102, 102, 102)
                                        .addComponent(btnUploadImgTeacher))
                                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cbDateTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(88, 88, 88))))))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane2Layout.createSequentialGroup()
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane2Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(txtIDTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)
                            .addComponent(txtEmailTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel28)
                                .addComponent(txtPhoneTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFirstNameTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(txtMidNameTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel29)
                            .addComponent(txtAddressTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLastNameTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27))
                        .addGap(22, 22, 22)
                        .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30)
                            .addComponent(rdMaleTeacher)
                            .addComponent(rdFemaleTeacher)
                            .addComponent(jLabel31)
                            .addComponent(rdONTeacher)
                            .addComponent(rdOFFTeacher))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbAvatarTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUploadImgTeacher)
                        .addGap(12, 12, 12)))
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(cbYearTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(cbMonthTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(cbDateTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(cbLevelTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(txtStartDateTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddTeacher)
                        .addComponent(btnUpdateTeacher)
                        .addComponent(btnResetFormTeacher))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        tabTeacher.addTab("INFORMATION", jLayeredPane2);

        tableTeacher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableTeacherMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tableTeacher);

        txtFindTeacher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindTeacherKeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Find:");

        btnExportTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExportTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Down.png"))); // NOI18N
        btnExportTeacher.setText("Excel Export");
        btnExportTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportTeacherActionPerformed(evt);
            }
        });

        btnImportTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnImportTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Up.png"))); // NOI18N
        btnImportTeacher.setText("Excel Import");

        jLayeredPane4.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(txtFindTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(btnExportTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane4.setLayer(btnImportTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane4Layout = new javax.swing.GroupLayout(jLayeredPane4);
        jLayeredPane4.setLayout(jLayeredPane4Layout);
        jLayeredPane4Layout.setHorizontalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane4Layout.createSequentialGroup()
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE))
                    .addGroup(jLayeredPane4Layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addComponent(btnExportTeacher)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnImportTeacher)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFindTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(310, 310, 310))
        );
        jLayeredPane4Layout.setVerticalGroup(
            jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane4Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jLayeredPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnExportTeacher)
                    .addComponent(btnImportTeacher))
                .addGap(32, 32, 32))
        );

        tabTeacher.addTab("LIST OF TEACHER", jLayeredPane4);

        jLabel39.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel39.setText("ID Teacher:");

        txtIDResetPassTeacher.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIDResetPassTeacherKeyReleased(evt);
            }
        });

        tableResetPassTeacher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableResetPassTeacher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableResetPassTeacherMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tableResetPassTeacher);

        btnResetPassTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetPassTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        btnResetPassTeacher.setText("Reset Password");
        btnResetPassTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPassTeacherActionPerformed(evt);
            }
        });

        btnExitResetPassTeacher.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExitResetPassTeacher.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Log out.png"))); // NOI18N
        btnExitResetPassTeacher.setText("Exit");
        btnExitResetPassTeacher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitResetPassTeacherActionPerformed(evt);
            }
        });

        jLayeredPane5.setLayer(jLabel39, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(txtIDResetPassTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(jScrollPane5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnResetPassTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane5.setLayer(btnExitResetPassTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane5Layout = new javax.swing.GroupLayout(jLayeredPane5);
        jLayeredPane5.setLayout(jLayeredPane5Layout);
        jLayeredPane5Layout.setHorizontalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addGap(18, 18, 18)
                                .addComponent(txtIDResetPassTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jLayeredPane5Layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(btnResetPassTeacher)
                        .addGap(18, 18, 18)
                        .addComponent(btnExitResetPassTeacher)))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        jLayeredPane5Layout.setVerticalGroup(
            jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDResetPassTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnResetPassTeacher)
                    .addComponent(btnExitResetPassTeacher))
                .addContainerGap(134, Short.MAX_VALUE))
        );

        tabTeacher.addTab("REFRESH PASSWORD", jLayeredPane5);

        javax.swing.GroupLayout cardListTeacherLayout = new javax.swing.GroupLayout(cardListTeacher);
        cardListTeacher.setLayout(cardListTeacherLayout);
        cardListTeacherLayout.setHorizontalGroup(
            cardListTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardListTeacherLayout.createSequentialGroup()
                .addContainerGap(219, Short.MAX_VALUE)
                .addComponent(tabTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        cardListTeacherLayout.setVerticalGroup(
            cardListTeacherLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardListTeacherLayout.createSequentialGroup()
                .addComponent(tabTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, 505, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 36, Short.MAX_VALUE))
        );

        jplMain.add(cardListTeacher, "card3");

        cardListStaff.setBackground(new java.awt.Color(255, 255, 255));

        tabStaff.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabStaff.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tabStaffAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tabStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabStaffMouseClicked(evt);
            }
        });

        lbAvatarStaff.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel38.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel38.setText("ID Staff:");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setText("First Name:");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel41.setText("Middle Name:");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel42.setText("Email:");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setText("Last Name:");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setText("Phone:");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setText("Address:");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel46.setText("Gender:");

        buttonGroup4.add(rdMaleStaff);
        rdMaleStaff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdMaleStaff.setText("Male");

        buttonGroup4.add(rdFemaleStaff);
        rdFemaleStaff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdFemaleStaff.setText("Female");

        jLabel47.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel47.setText("Status:");

        btnUploadImgStaff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUploadImgStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Upload.png"))); // NOI18N
        btnUploadImgStaff.setText("Upload");
        btnUploadImgStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImgStaffActionPerformed(evt);
            }
        });

        buttonGroup5.add(rdONStaff);
        rdONStaff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdONStaff.setText("ON");

        buttonGroup5.add(rdOFFStaff);
        rdOFFStaff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdOFFStaff.setText("OFF");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel48.setText("Position:");

        cbPositionStaff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel49.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel49.setText("Year of birth:");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel50.setText("Month of birth:");

        cbYearStaff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbMonthStaff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel51.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel51.setText("Date of birth:");

        cbDateStaff.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel52.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel52.setText("Start Date:");

        jLabel53.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel53.setText("Note:");

        txtNoteStaff.setColumns(20);
        txtNoteStaff.setRows(5);
        jScrollPane6.setViewportView(txtNoteStaff);

        btnAddStaff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        btnAddStaff.setText("ADD");
        btnAddStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStaffActionPerformed(evt);
            }
        });

        btnUpdateStaff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdateStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/edit-24px.png"))); // NOI18N
        btnUpdateStaff.setText("UPDATE");
        btnUpdateStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateStaffActionPerformed(evt);
            }
        });

        btnResetFormStaff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetFormStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        btnResetFormStaff.setText("REFRESH");
        btnResetFormStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFormStaffActionPerformed(evt);
            }
        });

        jLayeredPane6.setLayer(lbAvatarStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel38, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(txtIDStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel40, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(txtFirstNameStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel41, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(txtLastNameStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel42, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(txtMidNameStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel43, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(txtEmailStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel44, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(txtPhoneStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel45, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel46, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(rdMaleStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(rdFemaleStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel47, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(btnUploadImgStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(rdONStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(rdOFFStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel48, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(cbPositionStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel49, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel50, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(cbYearStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(cbMonthStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel51, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(cbDateStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel52, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(txtStartDateStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jLabel53, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(jScrollPane6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(btnAddStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(btnUpdateStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(btnResetFormStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane6.setLayer(txtAddressStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane6Layout = new javax.swing.GroupLayout(jLayeredPane6);
        jLayeredPane6.setLayout(jLayeredPane6Layout);
        jLayeredPane6Layout.setHorizontalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                .addComponent(jLabel53)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnAddStaff)
                                .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                    .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                            .addComponent(jLabel49)
                                            .addGap(18, 18, 18)
                                            .addComponent(cbYearStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                            .addComponent(jLabel48)
                                            .addGap(18, 18, 18)
                                            .addComponent(cbPositionStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGap(18, 18, 18)
                                    .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                            .addComponent(jLabel50)
                                            .addGap(18, 18, 18)
                                            .addComponent(cbMonthStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                            .addComponent(jLabel52)
                                            .addGap(18, 18, 18)
                                            .addComponent(txtStartDateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateStaff)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetFormStaff))
                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel40)
                                    .addComponent(jLabel38)
                                    .addComponent(jLabel41)
                                    .addComponent(jLabel43))
                                .addGap(18, 18, 18)
                                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtFirstNameStaff, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                            .addComponent(txtMidNameStaff, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                            .addComponent(txtIDStaff))
                                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addComponent(jLabel42)
                                                .addGap(38, 38, 38)
                                                .addComponent(txtEmailStaff))
                                            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                                        .addComponent(jLabel47)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(rdONStaff)
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel51)
                                                            .addComponent(rdOFFStaff)))
                                                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel45)
                                                            .addComponent(jLabel44))
                                                        .addGap(18, 18, 18)
                                                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(txtAddressStaff, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                                            .addComponent(txtPhoneStaff)))))))
                                    .addComponent(txtLastNameStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                .addComponent(jLabel46)
                                .addGap(18, 18, 18)
                                .addComponent(rdMaleStaff)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdFemaleStaff)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbAvatarStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))
                            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                        .addGap(102, 102, 102)
                                        .addComponent(btnUploadImgStaff))
                                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cbDateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(88, 88, 88))))))
        );
        jLayeredPane6Layout.setVerticalGroup(
            jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane6Layout.createSequentialGroup()
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane6Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel38)
                            .addComponent(txtIDStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42)
                            .addComponent(txtEmailStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40)
                            .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel44)
                                .addComponent(txtPhoneStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFirstNameStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel41)
                            .addComponent(txtMidNameStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45)
                            .addComponent(txtAddressStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLastNameStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43))
                        .addGap(22, 22, 22)
                        .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46)
                            .addComponent(rdMaleStaff)
                            .addComponent(rdFemaleStaff)
                            .addComponent(jLabel47)
                            .addComponent(rdONStaff)
                            .addComponent(rdOFFStaff))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbAvatarStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUploadImgStaff)
                        .addGap(12, 12, 12)))
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(cbYearStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50)
                    .addComponent(cbMonthStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51)
                    .addComponent(cbDateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel48)
                    .addComponent(cbPositionStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52)
                    .addComponent(txtStartDateStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel53)
                    .addGroup(jLayeredPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddStaff)
                        .addComponent(btnUpdateStaff)
                        .addComponent(btnResetFormStaff))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        tabStaff.addTab("INFORMATION", jLayeredPane6);

        tableStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableStaffMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tableStaff);

        txtFindStaff.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindStaffKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Find:");

        jLayeredPane7.setLayer(jScrollPane7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane7.setLayer(txtFindStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane7.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane7Layout = new javax.swing.GroupLayout(jLayeredPane7);
        jLayeredPane7.setLayout(jLayeredPane7Layout);
        jLayeredPane7Layout.setHorizontalGroup(
            jLayeredPane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 821, Short.MAX_VALUE)
            .addGroup(jLayeredPane7Layout.createSequentialGroup()
                .addGap(261, 261, 261)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(txtFindStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane7Layout.setVerticalGroup(
            jLayeredPane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane7Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(jLayeredPane7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabStaff.addTab("LIST OF STAFF", jLayeredPane7);

        jLabel54.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel54.setText("ID Staff:");

        txtIDResetPassStaff.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIDResetPassStaffKeyReleased(evt);
            }
        });

        tableResetPassStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableResetPassStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableResetPassStaffMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tableResetPassStaff);

        btnResetPassStaff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetPassStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        btnResetPassStaff.setText("Reset Password");
        btnResetPassStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPassStaffActionPerformed(evt);
            }
        });

        btnExitResetPassStaff.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExitResetPassStaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Log out.png"))); // NOI18N
        btnExitResetPassStaff.setText("Exit");
        btnExitResetPassStaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitResetPassStaffActionPerformed(evt);
            }
        });

        jLayeredPane8.setLayer(jLabel54, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane8.setLayer(txtIDResetPassStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane8.setLayer(jScrollPane8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane8.setLayer(btnResetPassStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane8.setLayer(btnExitResetPassStaff, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane8Layout = new javax.swing.GroupLayout(jLayeredPane8);
        jLayeredPane8.setLayout(jLayeredPane8Layout);
        jLayeredPane8Layout.setHorizontalGroup(
            jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane8Layout.createSequentialGroup()
                .addGroup(jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane8Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane8Layout.createSequentialGroup()
                                .addComponent(jLabel54)
                                .addGap(18, 18, 18)
                                .addComponent(txtIDResetPassStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jLayeredPane8Layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(btnResetPassStaff)
                        .addGap(18, 18, 18)
                        .addComponent(btnExitResetPassStaff)))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        jLayeredPane8Layout.setVerticalGroup(
            jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane8Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDResetPassStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnResetPassStaff)
                    .addComponent(btnExitResetPassStaff))
                .addContainerGap(170, Short.MAX_VALUE))
        );

        tabStaff.addTab("REFRESH PASSWORD", jLayeredPane8);

        javax.swing.GroupLayout cardListStaffLayout = new javax.swing.GroupLayout(cardListStaff);
        cardListStaff.setLayout(cardListStaffLayout);
        cardListStaffLayout.setHorizontalGroup(
            cardListStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardListStaffLayout.createSequentialGroup()
                .addContainerGap(219, Short.MAX_VALUE)
                .addComponent(tabStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        cardListStaffLayout.setVerticalGroup(
            cardListStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabStaff)
        );

        jplMain.add(cardListStaff, "card3");

        cardListStudent.setBackground(new java.awt.Color(255, 255, 255));

        tabStudent.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabStudent.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tabStudentAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tabStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabStudentMouseClicked(evt);
            }
        });

        lbAvatarStudent.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel55.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel55.setText("ID Student:");

        jLabel56.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel56.setText("First Name:");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel57.setText("Middle Name:");

        jLabel59.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel59.setText("Last Name:");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel61.setText("Address:");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel62.setText("Gender:");

        buttonGroup6.add(rdMaleStudent);
        rdMaleStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdMaleStudent.setText("Male");

        buttonGroup6.add(rdFemaleStudent);
        rdFemaleStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdFemaleStudent.setText("Female");

        jLabel63.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel63.setText("Status:");

        btnUploadImgStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUploadImgStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Upload.png"))); // NOI18N
        btnUploadImgStudent.setText("Upload");
        btnUploadImgStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadImgStudentActionPerformed(evt);
            }
        });

        buttonGroup7.add(rdONStudent);
        rdONStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdONStudent.setText("ON");

        buttonGroup7.add(rdOFFStudent);
        rdOFFStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdOFFStudent.setText("OFF");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel65.setText("Year of birth:");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel66.setText("Month of birth:");

        cbYearStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbMonthStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbMonthStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMonthStudentActionPerformed(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel67.setText("Date of birth:");

        cbDateStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel69.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel69.setText("Note:");

        txtNoteStudent.setColumns(20);
        txtNoteStudent.setRows(5);
        jScrollPane9.setViewportView(txtNoteStudent);

        btnAddStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        btnAddStudent.setText("ADD");
        btnAddStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStudentActionPerformed(evt);
            }
        });

        btnUpdateStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdateStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/edit-24px.png"))); // NOI18N
        btnUpdateStudent.setText("UPDATE");
        btnUpdateStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateStudentActionPerformed(evt);
            }
        });

        btnResetFormStudent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetFormStudent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        btnResetFormStudent.setText("REFRESH");
        btnResetFormStudent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFormStudentActionPerformed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel60.setText("Class:");

        jLayeredPane9.setLayer(lbAvatarStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(jLabel55, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(txtIDStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(jLabel56, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(txtFirstNameStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(jLabel57, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(txtLastNameStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(txtMidNameStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(jLabel59, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(jLabel61, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(jLabel62, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(rdMaleStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(rdFemaleStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(jLabel63, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(btnUploadImgStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(rdONStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(rdOFFStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(jLabel65, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(jLabel66, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(cbYearStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(cbMonthStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(jLabel67, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(cbDateStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(jLabel69, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(jScrollPane9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(btnAddStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(btnUpdateStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(btnResetFormStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(txtAddressStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(jLabel60, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane9.setLayer(txtClassStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane9Layout = new javax.swing.GroupLayout(jLayeredPane9);
        jLayeredPane9.setLayout(jLayeredPane9Layout);
        jLayeredPane9Layout.setHorizontalGroup(
            jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane9Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane9Layout.createSequentialGroup()
                        .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane9Layout.createSequentialGroup()
                                .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel61)
                                    .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel55)))
                                .addGap(18, 18, 18)
                                .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMidNameStudent, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                    .addComponent(txtIDStudent)
                                    .addComponent(txtAddressStudent))
                                .addGap(40, 40, 40)
                                .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel56)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel59)
                                        .addComponent(jLabel63)
                                        .addComponent(jLabel60)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtLastNameStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFirstNameStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jLayeredPane9Layout.createSequentialGroup()
                                        .addComponent(rdONStudent)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdOFFStudent))
                                    .addComponent(txtClassStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jLayeredPane9Layout.createSequentialGroup()
                                .addComponent(jLabel62)
                                .addGap(18, 18, 18)
                                .addComponent(rdMaleStudent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdFemaleStudent))
                            .addGroup(jLayeredPane9Layout.createSequentialGroup()
                                .addComponent(jLabel65)
                                .addGap(18, 18, 18)
                                .addComponent(cbYearStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel66)
                                .addGap(18, 18, 18)
                                .addComponent(cbMonthStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel67)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbDateStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 5, Short.MAX_VALUE)
                        .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane9Layout.createSequentialGroup()
                                .addComponent(lbAvatarStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54))
                            .addGroup(jLayeredPane9Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(btnUploadImgStudent)
                                .addGap(88, 88, 88))))
                    .addGroup(jLayeredPane9Layout.createSequentialGroup()
                        .addComponent(jLabel69)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnAddStudent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdateStudent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnResetFormStudent)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jLayeredPane9Layout.setVerticalGroup(
            jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane9Layout.createSequentialGroup()
                .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane9Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFirstNameStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56)
                            .addComponent(txtIDStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtLastNameStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel59)
                            .addComponent(txtMidNameStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel57))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane9Layout.createSequentialGroup()
                                .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel60)
                                    .addComponent(txtClassStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAddressStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel61))
                                .addGap(34, 34, 34)
                                .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel62)
                                    .addComponent(rdMaleStudent)
                                    .addComponent(rdFemaleStudent)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane9Layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel63)
                                    .addComponent(rdONStudent)
                                    .addComponent(rdOFFStudent))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel65)
                            .addComponent(cbYearStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel66)
                            .addComponent(cbMonthStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel67)
                            .addComponent(cbDateStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jLayeredPane9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbAvatarStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnUploadImgStudent)))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69)
                    .addGroup(jLayeredPane9Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jLayeredPane9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddStudent)
                            .addComponent(btnUpdateStudent)
                            .addComponent(btnResetFormStudent)))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(133, Short.MAX_VALUE))
        );

        tabStudent.addTab("INFORMATION", jLayeredPane9);

        tableStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableStudentMouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(tableStudent);

        txtFindStudent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindStudentKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Find:");

        jLayeredPane10.setLayer(jScrollPane10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane10.setLayer(txtFindStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane10.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane10Layout = new javax.swing.GroupLayout(jLayeredPane10);
        jLayeredPane10.setLayout(jLayeredPane10Layout);
        jLayeredPane10Layout.setHorizontalGroup(
            jLayeredPane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane10Layout.createSequentialGroup()
                .addGap(284, 284, 284)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtFindStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 809, Short.MAX_VALUE)
                .addContainerGap())
        );
        jLayeredPane10Layout.setVerticalGroup(
            jLayeredPane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(115, Short.MAX_VALUE))
        );

        tabStudent.addTab("LIST OF STUDENT", jLayeredPane10);

        tablePoint.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablePoint.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablePointMouseClicked(evt);
            }
        });
        jScrollPane24.setViewportView(tablePoint);

        jLabel80.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel80.setText("ID Student:");

        cbIDClassPoint.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel83.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel83.setText("ID Class:");

        cbIDStudentPoint.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel84.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel84.setText("ID Subject:");

        jLabel85.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel85.setText("Year:");

        cbYearPoint.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbIDSubjectPoint.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel86.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel86.setText("Note:");

        txtNotePoint.setColumns(20);
        txtNotePoint.setRows(5);
        jScrollPane25.setViewportView(txtNotePoint);

        jLabel87.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel87.setText("Course Name:");

        cbCourseNamePoint.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel88.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel88.setText("Point:");

        jLabel89.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel89.setText("ID Teacher:");

        jLabel90.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel90.setText("Find:");

        txtFindPoint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindPointKeyReleased(evt);
            }
        });

        btnAddPoint.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnAddPoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        btnAddPoint.setText("ADD");
        btnAddPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPointActionPerformed(evt);
            }
        });

        btnUpdatePoint.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnUpdatePoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/edit-24px.png"))); // NOI18N
        btnUpdatePoint.setText("UPDATE");
        btnUpdatePoint.setToolTipText("");
        btnUpdatePoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdatePointActionPerformed(evt);
            }
        });

        btnResetPoint.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnResetPoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        btnResetPoint.setText("REFRESH");
        btnResetPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPointActionPerformed(evt);
            }
        });

        btnImportPoint.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnImportPoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Up.png"))); // NOI18N
        btnImportPoint.setText("EXCEL IMPORT");
        btnImportPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportPointActionPerformed(evt);
            }
        });

        btnExportPoint.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        btnExportPoint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Down.png"))); // NOI18N
        btnExportPoint.setText("EXCEL EXPORT");
        btnExportPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportPointActionPerformed(evt);
            }
        });

        jLayeredPane20.setLayer(jScrollPane24, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(jLabel80, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(cbIDClassPoint, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(jLabel83, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(cbIDStudentPoint, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(jLabel84, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(jLabel85, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(cbYearPoint, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(cbIDSubjectPoint, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(jLabel86, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(jScrollPane25, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(jLabel87, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(cbCourseNamePoint, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(jLabel88, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(txtPoint, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(jLabel89, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(txtIDTeacherPoint, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(jLabel90, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(txtFindPoint, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(btnAddPoint, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(btnUpdatePoint, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(btnResetPoint, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(btnImportPoint, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane20.setLayer(btnExportPoint, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane20Layout = new javax.swing.GroupLayout(jLayeredPane20);
        jLayeredPane20.setLayout(jLayeredPane20Layout);
        jLayeredPane20Layout.setHorizontalGroup(
            jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane20Layout.createSequentialGroup()
                .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane20Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane20Layout.createSequentialGroup()
                                .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jLayeredPane20Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel80)
                                            .addComponent(jLabel83))
                                        .addGap(49, 49, 49)
                                        .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cbIDClassPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbIDStudentPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jLayeredPane20Layout.createSequentialGroup()
                                        .addComponent(jLabel87)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cbCourseNamePoint, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(53, 53, 53)
                                .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane20Layout.createSequentialGroup()
                                        .addComponent(jLabel84)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbIDSubjectPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel86))
                                    .addGroup(jLayeredPane20Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane20Layout.createSequentialGroup()
                                                .addComponent(jLabel85)
                                                .addGap(52, 52, 52))
                                            .addGroup(jLayeredPane20Layout.createSequentialGroup()
                                                .addComponent(jLabel88)
                                                .addGap(46, 46, 46)))
                                        .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jLayeredPane20Layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(cbYearPoint, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(16, 16, 16)))))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane20Layout.createSequentialGroup()
                                .addComponent(jLabel89)
                                .addGap(50, 50, 50)
                                .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane20Layout.createSequentialGroup()
                                        .addGap(16, 16, 16)
                                        .addComponent(jLabel90)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtFindPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtIDTeacherPoint, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jLayeredPane20Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnUpdatePoint, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnResetPoint, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnImportPoint, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExportPoint, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddPoint, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jLayeredPane20Layout.setVerticalGroup(
            jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane20Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane20Layout.createSequentialGroup()
                        .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel80)
                            .addComponent(cbIDStudentPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel84)
                            .addComponent(cbIDSubjectPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel86))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel83)
                            .addComponent(cbIDClassPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel85)
                            .addComponent(cbYearPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel87)
                            .addComponent(cbCourseNamePoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel88)
                            .addComponent(txtPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel89)
                    .addComponent(txtIDTeacherPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90)
                    .addComponent(txtFindPoint, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane24, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane20Layout.createSequentialGroup()
                        .addComponent(btnAddPoint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdatePoint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnResetPoint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnImportPoint)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExportPoint)))
                .addGap(87, 87, 87))
        );

        tabStudent.addTab("POINT", jLayeredPane20);

        jLabel91.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel91.setText("ID Bonus:");

        jLabel119.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel119.setText("Year:");

        jLabel120.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel120.setText("ID Student:");

        jLabel121.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel121.setText("GPA:");

        cbYearBonus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel122.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel122.setText("Note:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane26.setViewportView(jTextArea1);

        tableBonus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane27.setViewportView(tableBonus);

        jLabel123.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel123.setText("Level:");

        cbBonusName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel124.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel124.setText("Course_Name");

        cblevelBnous.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtFindBonus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindBonusKeyReleased(evt);
            }
        });

        jLabel125.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel125.setText("Find:");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        jButton5.setText("ADD");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/edit-24px.png"))); // NOI18N
        jButton6.setText("UPDATE");

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Trash.png"))); // NOI18N
        jButton8.setText("DELETE");

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        jButton9.setText("RESET");

        cbStudentBonus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLayeredPane21.setLayer(jLabel91, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jTextField1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jLabel119, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jLabel120, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jLabel121, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(cbYearBonus, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jTextField3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jLabel122, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jScrollPane26, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jScrollPane27, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jLabel123, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(cbBonusName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jLabel124, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(cblevelBnous, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(txtFindBonus, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jLabel125, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jButton5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jButton6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jButton8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(jButton9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane21.setLayer(cbStudentBonus, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane21Layout = new javax.swing.GroupLayout(jLayeredPane21);
        jLayeredPane21.setLayout(jLayeredPane21Layout);
        jLayeredPane21Layout.setHorizontalGroup(
            jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane21Layout.createSequentialGroup()
                .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane21Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel91)
                            .addComponent(jLabel119)
                            .addComponent(jLabel123))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbYearBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cblevelBnous, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(60, 60, 60)
                        .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel120)
                            .addComponent(jLabel121)
                            .addComponent(jLabel124))
                        .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane21Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbStudentBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63))
                            .addGroup(jLayeredPane21Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(cbBonusName, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane21Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel125)
                        .addGap(18, 18, 18)
                        .addComponent(txtFindBonus, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142)))
                .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel122))
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane21Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 778, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(jLayeredPane21Layout.createSequentialGroup()
                .addGap(255, 255, 255)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane21Layout.setVerticalGroup(
            jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane21Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel120)
                    .addComponent(jLabel122)
                    .addComponent(cbStudentBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane21Layout.createSequentialGroup()
                        .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel119)
                            .addComponent(jLabel121)
                            .addComponent(cbYearBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel123)
                                .addComponent(cblevelBnous, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cbBonusName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel124)))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFindBonus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel125))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane27, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jLayeredPane21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addGap(38, 38, 38))
        );

        tabStudent.addTab("BONUS", jLayeredPane21);

        javax.swing.GroupLayout cardListStudentLayout = new javax.swing.GroupLayout(cardListStudent);
        cardListStudent.setLayout(cardListStudentLayout);
        cardListStudentLayout.setHorizontalGroup(
            cardListStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardListStudentLayout.createSequentialGroup()
                .addContainerGap(219, Short.MAX_VALUE)
                .addComponent(tabStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        cardListStudentLayout.setVerticalGroup(
            cardListStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardListStudentLayout.createSequentialGroup()
                .addComponent(tabStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 533, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 16, Short.MAX_VALUE))
        );

        jplMain.add(cardListStudent, "card3");

        cardListParent.setBackground(new java.awt.Color(255, 255, 255));

        tabParent.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabParent.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tabParentAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tabParent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabParentMouseClicked(evt);
            }
        });

        jLabel58.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel58.setText("ID Guardians:");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel64.setText("First Name:");

        jLabel68.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel68.setText("Middle Name:");

        jLabel70.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel70.setText("Email:");

        jLabel71.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel71.setText("Last Name:");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel72.setText("Phone:");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel73.setText("Address:");

        jLabel74.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel74.setText("Gender:");

        buttonGroup8.add(rdMaleParent);
        rdMaleParent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdMaleParent.setText("Male");

        buttonGroup8.add(rdFemaleParent);
        rdFemaleParent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdFemaleParent.setText("Female");

        jLabel76.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel76.setText("Job:");

        cbJobParent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel81.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel81.setText("Note:");

        txtNoteParent.setColumns(20);
        txtNoteParent.setRows(5);
        jScrollPane11.setViewportView(txtNoteParent);

        btnAddParent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddParent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        btnAddParent.setText("ADD");
        btnAddParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddParentActionPerformed(evt);
            }
        });

        btnUpdateParent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdateParent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/edit-24px.png"))); // NOI18N
        btnUpdateParent.setText("UPDATE");
        btnUpdateParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateParentActionPerformed(evt);
            }
        });

        btnResetFormParent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetFormParent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        btnResetFormParent.setText("REFRESH");
        btnResetFormParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFormParentActionPerformed(evt);
            }
        });

        jLayeredPane11.setLayer(jLabel58, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(txtIDParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(jLabel64, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(txtFirstNameParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(jLabel68, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(txtLastNameParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(jLabel70, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(txtMidNameParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(jLabel71, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(txtEmailParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(jLabel72, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(txtPhoneParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(jLabel73, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(jLabel74, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(rdMaleParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(rdFemaleParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(jLabel76, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(cbJobParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(jLabel81, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(jScrollPane11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(btnAddParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(btnUpdateParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(btnResetFormParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane11.setLayer(txtAddressParent, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane11Layout = new javax.swing.GroupLayout(jLayeredPane11);
        jLayeredPane11.setLayout(jLayeredPane11Layout);
        jLayeredPane11Layout.setHorizontalGroup(
            jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane11Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane11Layout.createSequentialGroup()
                        .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel64)
                            .addComponent(jLabel58)
                            .addComponent(jLabel68)
                            .addComponent(jLabel71))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane11Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(btnAddParent)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdateParent)
                                .addGap(18, 18, 18)
                                .addComponent(btnResetFormParent))
                            .addGroup(jLayeredPane11Layout.createSequentialGroup()
                                .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane11Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtFirstNameParent, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                            .addComponent(txtMidNameParent, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                                            .addComponent(txtIDParent))
                                        .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jLayeredPane11Layout.createSequentialGroup()
                                                .addGap(40, 40, 40)
                                                .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel73)
                                                    .addComponent(jLabel72)))
                                            .addGroup(jLayeredPane11Layout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addComponent(jLabel70))))
                                    .addComponent(txtLastNameParent, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtPhoneParent, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                                    .addComponent(txtEmailParent, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtAddressParent)))))
                    .addGroup(jLayeredPane11Layout.createSequentialGroup()
                        .addComponent(jLabel74)
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbJobParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane11Layout.createSequentialGroup()
                                .addComponent(rdMaleParent)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdFemaleParent))
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel81)
                    .addComponent(jLabel76))
                .addGap(0, 179, Short.MAX_VALUE))
        );
        jLayeredPane11Layout.setVerticalGroup(
            jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane11Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(txtIDParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel70)
                    .addComponent(txtEmailParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel64)
                    .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel72)
                        .addComponent(txtPhoneParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtFirstNameParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(txtMidNameParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel73)
                    .addComponent(txtAddressParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLastNameParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel71))
                .addGap(22, 22, 22)
                .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(rdMaleParent)
                    .addComponent(rdFemaleParent))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(cbJobParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane11Layout.createSequentialGroup()
                        .addComponent(jLabel81)
                        .addGap(63, 63, 63))
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddParent)
                    .addComponent(btnUpdateParent)
                    .addComponent(btnResetFormParent))
                .addContainerGap(67, Short.MAX_VALUE))
        );

        tabParent.addTab("INFORMATION", jLayeredPane11);

        tableParent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableParent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableParentMouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(tableParent);

        txtFindParent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindParentKeyReleased(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Find:");

        jLayeredPane12.setLayer(jScrollPane12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane12.setLayer(txtFindParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane12.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane12Layout = new javax.swing.GroupLayout(jLayeredPane12);
        jLayeredPane12.setLayout(jLayeredPane12Layout);
        jLayeredPane12Layout.setHorizontalGroup(
            jLayeredPane12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
            .addGroup(jLayeredPane12Layout.createSequentialGroup()
                .addGap(238, 238, 238)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtFindParent, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane12Layout.setVerticalGroup(
            jLayeredPane12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane12Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addGroup(jLayeredPane12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindParent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabParent.addTab("LIST OF GUARDIANS", jLayeredPane12);

        jLabel82.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel82.setText("ID Guardians:");

        txtIDResetPassParent.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIDResetPassParentKeyReleased(evt);
            }
        });

        tableResetPassParent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableResetPassParent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableResetPassParentMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(tableResetPassParent);

        btnResetPassParent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetPassParent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        btnResetPassParent.setText("Reset Password");
        btnResetPassParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetPassParentActionPerformed(evt);
            }
        });

        btnExitResetPassParent.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnExitResetPassParent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Log out.png"))); // NOI18N
        btnExitResetPassParent.setText("Exit");
        btnExitResetPassParent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitResetPassParentActionPerformed(evt);
            }
        });

        jLayeredPane13.setLayer(jLabel82, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane13.setLayer(txtIDResetPassParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane13.setLayer(jScrollPane13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane13.setLayer(btnResetPassParent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane13.setLayer(btnExitResetPassParent, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane13Layout = new javax.swing.GroupLayout(jLayeredPane13);
        jLayeredPane13.setLayout(jLayeredPane13Layout);
        jLayeredPane13Layout.setHorizontalGroup(
            jLayeredPane13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane13Layout.createSequentialGroup()
                .addGroup(jLayeredPane13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane13Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(jLayeredPane13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane13Layout.createSequentialGroup()
                                .addComponent(jLabel82)
                                .addGap(18, 18, 18)
                                .addComponent(txtIDResetPassParent, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jLayeredPane13Layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(btnResetPassParent)
                        .addGap(18, 18, 18)
                        .addComponent(btnExitResetPassParent)))
                .addContainerGap(132, Short.MAX_VALUE))
        );
        jLayeredPane13Layout.setVerticalGroup(
            jLayeredPane13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane13Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDResetPassParent, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel82))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnResetPassParent)
                    .addComponent(btnExitResetPassParent))
                .addContainerGap(170, Short.MAX_VALUE))
        );

        tabParent.addTab("REFRESH PASSWORD", jLayeredPane13);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Find:");

        txtFindRelationship.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindRelationshipKeyReleased(evt);
            }
        });

        tableRelationship.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableRelationship.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableRelationshipMouseClicked(evt);
            }
        });
        jScrollPane14.setViewportView(tableRelationship);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        jButton1.setText("ADD");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnDeleteRelationship.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDeleteRelationship.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Trash.png"))); // NOI18N
        btnDeleteRelationship.setText("DELETE");
        btnDeleteRelationship.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRelationshipActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("ID Guardians:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("ID Student:");

        btnResetFormReletionship.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetFormReletionship.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        btnResetFormReletionship.setText("REFRESH");
        btnResetFormReletionship.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFormReletionshipActionPerformed(evt);
            }
        });

        jLayeredPane14.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane14.setLayer(txtFindRelationship, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane14.setLayer(jScrollPane14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane14.setLayer(jButton1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane14.setLayer(btnDeleteRelationship, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane14.setLayer(jLabel8, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane14.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane14.setLayer(txtRelationshipGuadians, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane14.setLayer(txtRelationshipStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane14.setLayer(btnResetFormReletionship, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane14Layout = new javax.swing.GroupLayout(jLayeredPane14);
        jLayeredPane14.setLayout(jLayeredPane14Layout);
        jLayeredPane14Layout.setHorizontalGroup(
            jLayeredPane14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane14Layout.createSequentialGroup()
                .addContainerGap(142, Short.MAX_VALUE)
                .addGroup(jLayeredPane14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane14Layout.createSequentialGroup()
                            .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(134, 134, 134))
                        .addGroup(jLayeredPane14Layout.createSequentialGroup()
                            .addGap(94, 94, 94)
                            .addComponent(jButton1)
                            .addGap(18, 18, 18)
                            .addComponent(btnDeleteRelationship)
                            .addGap(18, 18, 18)
                            .addComponent(btnResetFormReletionship)
                            .addContainerGap()))
                    .addGroup(jLayeredPane14Layout.createSequentialGroup()
                        .addGroup(jLayeredPane14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane14Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFindRelationship, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane14Layout.createSequentialGroup()
                                .addGap(249, 249, 249)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRelationshipStudent, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane14Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRelationshipGuadians, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jLayeredPane14Layout.setVerticalGroup(
            jLayeredPane14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane14Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtFindRelationship, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jLayeredPane14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(txtRelationshipGuadians, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRelationshipStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(btnDeleteRelationship)
                    .addComponent(btnResetFormReletionship))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        tabParent.addTab("RELATIONSHIP", jLayeredPane14);

        javax.swing.GroupLayout cardListParentLayout = new javax.swing.GroupLayout(cardListParent);
        cardListParent.setLayout(cardListParentLayout);
        cardListParentLayout.setHorizontalGroup(
            cardListParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardListParentLayout.createSequentialGroup()
                .addContainerGap(219, Short.MAX_VALUE)
                .addComponent(tabParent, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(99, 99, 99))
        );
        cardListParentLayout.setVerticalGroup(
            cardListParentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabParent)
        );

        jplMain.add(cardListParent, "card3");

        cardLearningManagement.setBackground(new java.awt.Color(255, 255, 255));

        tabCourse.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tabCourse.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tabCourseAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        tabCourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabCourseMouseClicked(evt);
            }
        });

        jLabel75.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel75.setText("ID Course:");

        jLabel77.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel77.setText("Year:");

        txtFindCourse.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindCourseKeyReleased(evt);
            }
        });

        jLabel79.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel79.setText("Course Name:");

        cbCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel92.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel92.setText("Note:");

        txtNoteCourse.setColumns(20);
        txtNoteCourse.setRows(5);
        jScrollPane15.setViewportView(txtNoteCourse);

        btnAddCourse.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddCourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        btnAddCourse.setText("ADD");
        btnAddCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCourseActionPerformed(evt);
            }
        });

        btnUpdateTeacher1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdateTeacher1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/edit-24px.png"))); // NOI18N
        btnUpdateTeacher1.setText("UPDATE");
        btnUpdateTeacher1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateTeacher1ActionPerformed(evt);
            }
        });

        btnResetFormCourse.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetFormCourse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        btnResetFormCourse.setText("REFRESH");
        btnResetFormCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFormCourseActionPerformed(evt);
            }
        });

        tableCourse.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableCourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCourseMouseClicked(evt);
            }
        });
        jScrollPane18.setViewportView(tableCourse);

        jLabel78.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel78.setText("Find:");

        jLayeredPane15.setLayer(jLabel75, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane15.setLayer(txtIDCourse, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane15.setLayer(jLabel77, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane15.setLayer(txtFindCourse, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane15.setLayer(jLabel79, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane15.setLayer(cbCourse, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane15.setLayer(jLabel92, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane15.setLayer(jScrollPane15, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane15.setLayer(btnAddCourse, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane15.setLayer(btnUpdateTeacher1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane15.setLayer(btnResetFormCourse, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane15.setLayer(jScrollPane18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane15.setLayer(jLabel78, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane15.setLayer(txtYearCourse, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane15Layout = new javax.swing.GroupLayout(jLayeredPane15);
        jLayeredPane15.setLayout(jLayeredPane15Layout);
        jLayeredPane15Layout.setHorizontalGroup(
            jLayeredPane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane15Layout.createSequentialGroup()
                .addGroup(jLayeredPane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane15Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jLayeredPane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane15Layout.createSequentialGroup()
                                .addComponent(jLabel77)
                                .addGap(258, 258, 258)
                                .addComponent(jLabel79))
                            .addGroup(jLayeredPane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jLayeredPane15Layout.createSequentialGroup()
                                    .addComponent(jLabel78)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtFindCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jLayeredPane15Layout.createSequentialGroup()
                                    .addComponent(jLabel75)
                                    .addGap(22, 22, 22)
                                    .addGroup(jLayeredPane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtIDCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtYearCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(59, 59, 59)
                                    .addComponent(jLabel92))))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jLayeredPane15Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 573, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jLayeredPane15Layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(btnAddCourse)
                        .addGap(18, 18, 18)
                        .addComponent(btnUpdateTeacher1)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetFormCourse)))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        jLayeredPane15Layout.setVerticalGroup(
            jLayeredPane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane15Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jLayeredPane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(txtIDCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79)
                    .addComponent(cbCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane15Layout.createSequentialGroup()
                        .addGroup(jLayeredPane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel77)
                            .addComponent(jLabel92)
                            .addComponent(txtYearCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel78)
                            .addComponent(txtFindCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(jLayeredPane15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnResetFormCourse)
                    .addComponent(btnUpdateTeacher1)
                    .addComponent(btnAddCourse))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        tabCourse.addTab("COURSE", jLayeredPane15);

        jLabel93.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel93.setText("ID Course:");

        tableCourse_Relationship.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableCourse_Relationship.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCourse_RelationshipMouseClicked(evt);
            }
        });
        jScrollPane19.setViewportView(tableCourse_Relationship);

        jLabel100.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel100.setText("ID Student:");

        txtFindCourseInformation.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindCourseInformationKeyReleased(evt);
            }
        });

        jLabel101.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel101.setText("ID Teacher:");

        jLabel102.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel102.setText("ID Class:");

        jLabel103.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel103.setText("Find:");

        btnAddCourse_Relationship.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddCourse_Relationship.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        btnAddCourse_Relationship.setText("ADD");
        btnAddCourse_Relationship.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCourse_RelationshipActionPerformed(evt);
            }
        });

        btnDeleteCourse_Relationship.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnDeleteCourse_Relationship.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Trash.png"))); // NOI18N
        btnDeleteCourse_Relationship.setText("DELETE");
        btnDeleteCourse_Relationship.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCourse_RelationshipActionPerformed(evt);
            }
        });

        btnResetFormCourseInformation.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetFormCourseInformation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        btnResetFormCourseInformation.setText("REFRESH");
        btnResetFormCourseInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetFormCourseInformationActionPerformed(evt);
            }
        });

        cbTeacherCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbClassCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbStudentCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbCourse_Infor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLayeredPane16.setLayer(jLabel93, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane16.setLayer(jScrollPane19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane16.setLayer(jLabel100, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane16.setLayer(txtFindCourseInformation, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane16.setLayer(jLabel101, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane16.setLayer(jLabel102, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane16.setLayer(jLabel103, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane16.setLayer(btnAddCourse_Relationship, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane16.setLayer(btnDeleteCourse_Relationship, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane16.setLayer(btnResetFormCourseInformation, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane16.setLayer(cbTeacherCourse, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane16.setLayer(cbClassCourse, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane16.setLayer(cbStudentCourse, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane16.setLayer(cbCourse_Infor, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane16Layout = new javax.swing.GroupLayout(jLayeredPane16);
        jLayeredPane16.setLayout(jLayeredPane16Layout);
        jLayeredPane16Layout.setHorizontalGroup(
            jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane16Layout.createSequentialGroup()
                .addGroup(jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane16Layout.createSequentialGroup()
                        .addGap(300, 300, 300)
                        .addComponent(btnAddCourse_Relationship)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteCourse_Relationship)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetFormCourseInformation))
                    .addGroup(jLayeredPane16Layout.createSequentialGroup()
                        .addGap(129, 129, 129)
                        .addGroup(jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane16Layout.createSequentialGroup()
                                .addGroup(jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane16Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel100)
                                            .addComponent(jLabel93))
                                        .addGap(18, 18, 18)
                                        .addGroup(jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cbStudentCourse, 0, 90, Short.MAX_VALUE)
                                            .addComponent(cbCourse_Infor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel101)
                                            .addComponent(jLabel102))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(jLayeredPane16Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel103)
                                        .addGap(32, 32, 32)
                                        .addComponent(txtFindCourseInformation, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(16, 16, 16)))
                                .addGroup(jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbClassCourse, 0, 90, Short.MAX_VALUE)
                                    .addComponent(cbTeacherCourse, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(69, 69, 69)))))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jLayeredPane16Layout.setVerticalGroup(
            jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane16Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93)
                    .addComponent(jLabel101)
                    .addComponent(cbTeacherCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbCourse_Infor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100)
                    .addComponent(jLabel102)
                    .addComponent(cbClassCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbStudentCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindCourseInformation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel103))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddCourse_Relationship)
                    .addComponent(btnDeleteCourse_Relationship)
                    .addComponent(btnResetFormCourseInformation))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        tabCourse.addTab("COURSE INFORMATION", jLayeredPane16);

        jLabel94.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel94.setText("ID Class:");

        txtIDClass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIDClassKeyReleased(evt);
            }
        });

        jLabel95.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel95.setText("Class Name:");

        jLabel96.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel96.setText("ID Teacher:");

        jLabel97.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel97.setText("Quantity:");

        jLabel98.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel98.setText("Note:");

        txtNoteClass.setColumns(20);
        txtNoteClass.setRows(5);
        jScrollPane16.setViewportView(txtNoteClass);

        tableClass.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableClass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableClassMouseClicked(evt);
            }
        });
        jScrollPane17.setViewportView(tableClass);

        txtFindClass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindClassKeyReleased(evt);
            }
        });

        jLabel99.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel99.setText("Find:");

        btnAddClass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAddClass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        btnAddClass.setText("ADD");
        btnAddClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddClassActionPerformed(evt);
            }
        });

        btnUpdateClass.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpdateClass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/edit-24px.png"))); // NOI18N
        btnUpdateClass.setText("UPDATE");
        btnUpdateClass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateClassActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        jButton4.setText("REFRESH");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        cbClassTeacher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel118.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel118.setText("Current Students:");

        jLayeredPane17.setLayer(jLabel94, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(txtIDClass, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(jLabel95, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(jLabel96, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(txtClassName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(jLabel97, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(txtQuantity, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(jLabel98, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(jScrollPane16, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(jScrollPane17, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(txtFindClass, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(jLabel99, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(btnAddClass, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(btnUpdateClass, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(jButton4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(cbClassTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(jLabel118, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane17.setLayer(txtCurrentStudents, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane17Layout = new javax.swing.GroupLayout(jLayeredPane17);
        jLayeredPane17.setLayout(jLayeredPane17Layout);
        jLayeredPane17Layout.setHorizontalGroup(
            jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane17Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jLayeredPane17Layout.createSequentialGroup()
                        .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel94)
                            .addComponent(jLabel96)
                            .addComponent(jLabel118))
                        .addGap(25, 25, 25)
                        .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane17Layout.createSequentialGroup()
                                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jLayeredPane17Layout.createSequentialGroup()
                                        .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtIDClass, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                            .addComponent(cbClassTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCurrentStudents))
                                        .addGap(86, 86, 86)
                                        .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel95)
                                            .addComponent(jLabel97)
                                            .addComponent(jLabel98)))
                                    .addGroup(jLayeredPane17Layout.createSequentialGroup()
                                        .addComponent(jLabel99)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtFindClass, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(19, 19, 19)))
                                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jLayeredPane17Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtClassName)
                                            .addComponent(txtQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)))))
                            .addGroup(jLayeredPane17Layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(btnAddClass)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdateClass)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4)))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jLayeredPane17Layout.setVerticalGroup(
            jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane17Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(txtIDClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel95)
                    .addComponent(txtClassName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96)
                    .addComponent(jLabel97)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbClassTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane17Layout.createSequentialGroup()
                        .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel98)
                            .addGroup(jLayeredPane17Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCurrentStudents, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel118))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFindClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel99)))
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(btnUpdateClass)
                    .addComponent(btnAddClass))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        tabCourse.addTab("CLASS", jLayeredPane17);

        jLabel104.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel104.setText("Find:");

        jLabel105.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel105.setText("Subject Name:");

        jLabel106.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel106.setText("Note:");

        txtNoteSubject.setColumns(20);
        txtNoteSubject.setRows(5);
        jScrollPane20.setViewportView(txtNoteSubject);

        jLabel107.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel107.setText("ID Subject:");

        txtFindSubject.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindSubjectKeyReleased(evt);
            }
        });

        tableSubject.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableSubjectMouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(tableSubject);

        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        jButton2.setText("ADD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/edit-24px.png"))); // NOI18N
        jButton3.setText("UPDATE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnResetSubject.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnResetSubject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        btnResetSubject.setText("REFRESH");
        btnResetSubject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSubjectActionPerformed(evt);
            }
        });

        jLayeredPane18.setLayer(jLabel104, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane18.setLayer(txtNameSubject, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane18.setLayer(jLabel105, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane18.setLayer(txtIDSubject, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane18.setLayer(jLabel106, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane18.setLayer(jScrollPane20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane18.setLayer(jLabel107, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane18.setLayer(txtFindSubject, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane18.setLayer(jScrollPane21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane18.setLayer(jButton2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane18.setLayer(jButton3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane18.setLayer(btnResetSubject, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane18Layout = new javax.swing.GroupLayout(jLayeredPane18);
        jLayeredPane18.setLayout(jLayeredPane18Layout);
        jLayeredPane18Layout.setHorizontalGroup(
            jLayeredPane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane18Layout.createSequentialGroup()
                .addGroup(jLayeredPane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane18Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(jLayeredPane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 524, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane18Layout.createSequentialGroup()
                                .addGroup(jLayeredPane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel105)
                                    .addComponent(jLabel107))
                                .addGap(18, 18, 18)
                                .addGroup(jLayeredPane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNameSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jLayeredPane18Layout.createSequentialGroup()
                                        .addComponent(txtIDSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabel106)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jLayeredPane18Layout.createSequentialGroup()
                        .addGap(249, 249, 249)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetSubject))
                    .addGroup(jLayeredPane18Layout.createSequentialGroup()
                        .addGap(293, 293, 293)
                        .addComponent(jLabel104)
                        .addGap(18, 18, 18)
                        .addComponent(txtFindSubject, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(133, Short.MAX_VALUE))
        );
        jLayeredPane18Layout.setVerticalGroup(
            jLayeredPane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane18Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jLayeredPane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane18Layout.createSequentialGroup()
                        .addGroup(jLayeredPane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIDSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel106)
                            .addComponent(jLabel107))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtNameSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel105)))
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFindSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel104))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnResetSubject)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addContainerGap(35, Short.MAX_VALUE))
        );

        tabCourse.addTab("SUBJECT", jLayeredPane18);

        jLabel108.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel108.setText("ID Subject:");

        jLabel109.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel109.setText("ID Teacher:");

        jLabel110.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel110.setText("ID Course:");

        jLabel111.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel111.setText("ID Class:");

        jLabel112.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel112.setText("ID Student:");

        cbScheduleClass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbScheduleCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbScheduleSubject.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbScheduleTeacher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbScheduleStudent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel113.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel113.setText("Course Name:");

        cbScheduleCourseName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel114.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel114.setText("Created Date:");

        jLabel115.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel115.setText("School Day:");

        jLabel116.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel116.setText("Note:");

        txtScheduleNote.setColumns(20);
        txtScheduleNote.setRows(5);
        jScrollPane22.setViewportView(txtScheduleNote);

        tableSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tableSchedule.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableScheduleMouseClicked(evt);
            }
        });
        jScrollPane23.setViewportView(tableSchedule);

        jLabel117.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel117.setText("Find:");

        txtFindSchedule.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindScheduleKeyReleased(evt);
            }
        });

        btnAddSchedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        btnAddSchedule.setText("ADD");
        btnAddSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddScheduleActionPerformed(evt);
            }
        });

        btnUpdateSchedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/edit-24px.png"))); // NOI18N
        btnUpdateSchedule.setText("UPDATE");
        btnUpdateSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateScheduleActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Refresh.png"))); // NOI18N
        jButton7.setText("REFRESH");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        btnDeleteSchedule.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Trash.png"))); // NOI18N
        btnDeleteSchedule.setText("DELETE");
        btnDeleteSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteScheduleActionPerformed(evt);
            }
        });

        jLayeredPane19.setLayer(jLabel108, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jLabel109, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jLabel110, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jLabel111, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jLabel112, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(cbScheduleClass, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(cbScheduleCourse, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(cbScheduleSubject, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(cbScheduleTeacher, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(cbScheduleStudent, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jLabel113, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(cbScheduleCourseName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jLabel114, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(txtScheduleCreatedDate, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jLabel115, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jLabel116, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jScrollPane22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(txtScheduleSchoolDay, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jScrollPane23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jLabel117, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(txtFindSchedule, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(btnAddSchedule, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(btnUpdateSchedule, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(jButton7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane19.setLayer(btnDeleteSchedule, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane19Layout = new javax.swing.GroupLayout(jLayeredPane19);
        jLayeredPane19.setLayout(jLayeredPane19Layout);
        jLayeredPane19Layout.setHorizontalGroup(
            jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane19Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel110)
                    .addComponent(jLabel111)
                    .addComponent(jLabel112)
                    .addComponent(jLabel115))
                .addGap(23, 23, 23)
                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane19Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(jLabel117)
                        .addGap(18, 18, 18)
                        .addComponent(txtFindSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jLayeredPane19Layout.createSequentialGroup()
                        .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtScheduleSchoolDay)
                            .addComponent(cbScheduleStudent, 0, 106, Short.MAX_VALUE)
                            .addComponent(cbScheduleCourse, 0, 106, Short.MAX_VALUE)
                            .addComponent(cbScheduleClass, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(78, 78, 78)
                        .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jLayeredPane19Layout.createSequentialGroup()
                                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel109)
                                    .addComponent(jLabel108))
                                .addGap(62, 62, 62)
                                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbScheduleSubject, 0, 96, Short.MAX_VALUE)
                                    .addComponent(cbScheduleTeacher, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jLayeredPane19Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel113)
                                    .addComponent(jLabel114))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtScheduleCreatedDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbScheduleCourseName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel116))
                        .addGap(70, 70, 70))))
            .addGroup(jLayeredPane19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 673, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnUpdateSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDeleteSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddSchedule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jLayeredPane19Layout.setVerticalGroup(
            jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane19Layout.createSequentialGroup()
                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jLayeredPane19Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel110)
                            .addComponent(jLabel109)
                            .addComponent(cbScheduleCourse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbScheduleTeacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel116)))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jLayeredPane19Layout.createSequentialGroup()
                        .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel111)
                                .addComponent(cbScheduleClass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel108)
                                .addComponent(cbScheduleSubject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbScheduleStudent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel112)
                            .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel113)
                                .addComponent(cbScheduleCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel114)
                                .addComponent(txtScheduleCreatedDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel115)
                                .addComponent(txtScheduleSchoolDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane22))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel117)
                    .addComponent(txtFindSchedule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane19Layout.createSequentialGroup()
                        .addComponent(btnAddSchedule)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdateSchedule)
                        .addGap(12, 12, 12)
                        .addComponent(btnDeleteSchedule)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7))
                    .addComponent(jScrollPane23, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49))
        );

        tabCourse.addTab("SCHEDULE", jLayeredPane19);

        javax.swing.GroupLayout cardLearningManagementLayout = new javax.swing.GroupLayout(cardLearningManagement);
        cardLearningManagement.setLayout(cardLearningManagementLayout);
        cardLearningManagementLayout.setHorizontalGroup(
            cardLearningManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardLearningManagementLayout.createSequentialGroup()
                .addContainerGap(219, Short.MAX_VALUE)
                .addComponent(tabCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        cardLearningManagementLayout.setVerticalGroup(
            cardLearningManagementLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabCourse)
        );

        jplMain.add(cardLearningManagement, "card3");

        cardQRCode.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout cardQRCodeLayout = new javax.swing.GroupLayout(cardQRCode);
        cardQRCode.setLayout(cardQRCodeLayout);
        cardQRCodeLayout.setHorizontalGroup(
            cardQRCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardQRCodeLayout.createSequentialGroup()
                .addContainerGap(297, Short.MAX_VALUE)
                .addComponent(QRCode, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(253, 253, 253))
        );
        cardQRCodeLayout.setVerticalGroup(
            cardQRCodeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardQRCodeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(QRCode, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jplMain.add(cardQRCode, "card3");

        cardDoimk.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout cardDoimkLayout = new javax.swing.GroupLayout(cardDoimk);
        cardDoimk.setLayout(cardDoimkLayout);
        cardDoimkLayout.setHorizontalGroup(
            cardDoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1050, Short.MAX_VALUE)
        );
        cardDoimkLayout.setVerticalGroup(
            cardDoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );

        jplMain.add(cardDoimk, "card3");

        jPanel1.add(jplMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1050, 540));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblCloseMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMenuMouseClicked
        this.closeMenu();
    }//GEN-LAST:event_lblCloseMenuMouseClicked

    private void lblOpenMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpenMenuMouseClicked
        this.openMenu();
    }//GEN-LAST:event_lblOpenMenuMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jplSlideMenu.setSize(0, y);
        x = 0;
    }//GEN-LAST:event_formWindowOpened

    private void lblTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseClicked
        this.setCardFalse();
        cardTrangChu.setVisible(true);
        this.setTimeSlide();
        lbTitle.setText("Viet Duc School - Home");
    }//GEN-LAST:event_lblTrangChuMouseClicked

    private void lblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoanMouseClicked
        this.setCardFalse();
        cardInformation.setVisible(true);
        lbTitle.setText("Viet Duc School - Account");
        this.setTimeSlide();
        this.resetFormChangePassWord();

    }//GEN-LAST:event_lblTaiKhoanMouseClicked

    private void lbTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTeacherMouseClicked
        this.setCardFalse();
        cardListTeacher.setVisible(true);
        lbTitle.setText("Viet Duc School - Teacher");
        this.resetFormTeacher();
        this.setTimeSlide();
        this.tabTeacher.setSelectedIndex(0);
    }//GEN-LAST:event_lbTeacherMouseClicked

    private void cardListTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardListTeacherMouseClicked

    }//GEN-LAST:event_cardListTeacherMouseClicked

    private void lbStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStaffMouseClicked
        this.setCardFalse();
        cardListStaff.setVisible(true);
        lbTitle.setText("Viet Duc School - Staff");
        this.resetFormStaff();
        this.setTimeSlide();
        this.tabStaff.setSelectedIndex(0);
    }//GEN-LAST:event_lbStaffMouseClicked

    private void lbGuardiansMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbGuardiansMouseClicked
        this.setCardFalse();
        cardListParent.setVisible(true);
        lbTitle.setText("Viet Duc School - Guardians");
        this.resetFormParent();
        this.setTimeSlide();
        this.tabParent.setSelectedIndex(0);

    }//GEN-LAST:event_lbGuardiansMouseClicked

    private void lbQRCodeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbQRCodeMouseClicked
        this.setCardFalse();
        cardQRCode.setVisible(true);
        lbTitle.setText("Viet Duc School - QR Code");
        this.setTimeSlide();
        this.showQRCode();
    }//GEN-LAST:event_lbQRCodeMouseClicked

    private void lblthoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblthoatMouseClicked
        if (Message.confirm(this, "Do you want log out ?")) {
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_lblthoatMouseClicked

    private void lbStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbStudentMouseClicked
        this.setCardFalse();
        cardListStudent.setVisible(true);
        lbTitle.setText("Viet Duc School - Student");
        this.resetFormStudent();
        this.setTimeSlide();
        this.tabStudent.setSelectedIndex(0);
    }//GEN-LAST:event_lbStudentMouseClicked

    private void lbShowCurrentPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbShowCurrentPassMouseClicked
        boolean isPasswordVisible = txtCurrentPass.getEchoChar() == 0;
        txtCurrentPass.setEchoChar(isPasswordVisible ? '\u2022' : (char) 0);
        lbShowCurrentPass.setIcon(isPasswordVisible ? new ImageIcon(getClass().getResource("/com/Icon/show.png"))
                : new ImageIcon(getClass().getResource("/com/Icon/eye-crossed.png")));
    }//GEN-LAST:event_lbShowCurrentPassMouseClicked

    private void lbShowNewPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbShowNewPassMouseClicked
        boolean isPasswordVisible = txtNewPass.getEchoChar() == 0;
        txtNewPass.setEchoChar(isPasswordVisible ? '\u2022' : (char) 0);
        lbShowNewPass.setIcon(isPasswordVisible ? new ImageIcon(getClass().getResource("/com/Icon/show.png"))
                : new ImageIcon(getClass().getResource("/com/Icon/eye-crossed.png")));
    }//GEN-LAST:event_lbShowNewPassMouseClicked

    private void lbShowEnterPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbShowEnterPassMouseClicked
        boolean isPasswordVisible = txtEnterPass.getEchoChar() == 0;
        txtEnterPass.setEchoChar(isPasswordVisible ? '\u2022' : (char) 0);
        lbShowEnterPass.setIcon(isPasswordVisible ? new ImageIcon(getClass().getResource("/com/Icon/show.png"))
                : new ImageIcon(getClass().getResource("/com/Icon/eye-crossed.png")));
    }//GEN-LAST:event_lbShowEnterPassMouseClicked

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        if (!checkNull()) {
            return;
        }
        this.changePassword();
    }//GEN-LAST:event_btnAcceptActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        if (Message.confirm(this, "Do you want exit ?")) {
            this.setCardFalse();
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        this.updateInformation();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        this.resetFormInformation();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnResetFormTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFormTeacherActionPerformed
        this.resetFormTeacher();
    }//GEN-LAST:event_btnResetFormTeacherActionPerformed

    private void btnUploadImgTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImgTeacherActionPerformed
        this.chooseImageTeacher();
    }//GEN-LAST:event_btnUploadImgTeacherActionPerformed

    private void tableTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableTeacherMouseClicked
        tableTeacher.setEnabled(false);
        if (evt.getClickCount() == 2) {
            this.clickTable();
            tableTeacher.setEnabled(true);
        }
    }//GEN-LAST:event_tableTeacherMouseClicked

    private void btnAddTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddTeacherActionPerformed
        this.addTeacher();
    }//GEN-LAST:event_btnAddTeacherActionPerformed

    private void btnUpdateTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTeacherActionPerformed
        this.updateTeacher();
    }//GEN-LAST:event_btnUpdateTeacherActionPerformed

    private void txtFindTeacherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindTeacherKeyReleased
        this.fillFindTableTeacher();
    }//GEN-LAST:event_txtFindTeacherKeyReleased

    private void tabTeacherAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tabTeacherAncestorAdded

    }//GEN-LAST:event_tabTeacherAncestorAdded

    private void tabTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabTeacherMouseClicked
        int selectedIndex = tabTeacher.getSelectedIndex();
        if (selectedIndex == 1) {
            txtFindTeacher.setText("");
            this.fillFindTableTeacher();
        } else if (selectedIndex == 2) {
            txtIDResetPassTeacher.setText("");
            this.fillFindResetPassTeacher();
        }
    }//GEN-LAST:event_tabTeacherMouseClicked

    private void txtIDResetPassTeacherKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDResetPassTeacherKeyReleased
        this.fillFindResetPassTeacher();
    }//GEN-LAST:event_txtIDResetPassTeacherKeyReleased

    private void btnResetPassTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPassTeacherActionPerformed
        if (txtIDResetPassTeacher.getText().isEmpty()) {
            Message.alert(this, "Please enter information !");
            txtIDResetPassTeacher.requestFocus();
            return;
        }
        if (Message.confirm(this, "Do you want to reset the password for this teacher?")) {
            teacher_DAO.adminResetPassTeacher(txtIDResetPassTeacher.getText());
            this.fillFindResetPassTeacher();
            tabTeacher.setSelectedIndex(0);
            txtIDResetPassTeacher.setText("");
        }
    }//GEN-LAST:event_btnResetPassTeacherActionPerformed

    private void btnExitResetPassTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitResetPassTeacherActionPerformed
        if (Message.confirm(this, "Do you want exit ?")) {
            this.setCardFalse();
        }
    }//GEN-LAST:event_btnExitResetPassTeacherActionPerformed

    private void btnUploadImgStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImgStaffActionPerformed
        this.chooseImageStaff();
    }//GEN-LAST:event_btnUploadImgStaffActionPerformed

    private void btnAddStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStaffActionPerformed
        this.addStaff();
    }//GEN-LAST:event_btnAddStaffActionPerformed

    private void btnUpdateStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateStaffActionPerformed
        this.updateStaff();
    }//GEN-LAST:event_btnUpdateStaffActionPerformed

    private void btnResetFormStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFormStaffActionPerformed
        this.resetFormStaff();
    }//GEN-LAST:event_btnResetFormStaffActionPerformed

    private void tableStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableStaffMouseClicked
        tableStaff.setEnabled(false);
        if (evt.getClickCount() == 2) {
            this.clickTableStaff();
            tableStaff.setEnabled(true);
        }
    }//GEN-LAST:event_tableStaffMouseClicked

    private void txtFindStaffKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindStaffKeyReleased
        this.fillTableStaff();
    }//GEN-LAST:event_txtFindStaffKeyReleased

    private void txtIDResetPassStaffKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDResetPassStaffKeyReleased
        this.fillFindResetPassStaff();
    }//GEN-LAST:event_txtIDResetPassStaffKeyReleased

    private void btnResetPassStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPassStaffActionPerformed
        if (txtIDResetPassStaff.getText().isEmpty()) {
            Message.alert(this, "Please enter information !");
            txtIDResetPassStaff.requestFocus();
            return;
        }
        if (Message.confirm(this, "Do you want to reset the password for this teacher?")) {
            staff_DAO.adminResetPassStaff(txtIDResetPassStaff.getText());
            this.fillFindResetPassStaff();
            tabStaff.setSelectedIndex(0);
            txtIDResetPassStaff.setText("");
        }
    }//GEN-LAST:event_btnResetPassStaffActionPerformed

    private void btnExitResetPassStaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitResetPassStaffActionPerformed
        if (Message.confirm(this, "Do you want exit ?")) {
            this.setCardFalse();
        }
    }//GEN-LAST:event_btnExitResetPassStaffActionPerformed

    private void tabStaffAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tabStaffAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tabStaffAncestorAdded

    private void tabStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabStaffMouseClicked
        int selectedIndex = tabStaff.getSelectedIndex();
        if (selectedIndex == 1) {
            txtFindStaff.setText("");
            this.fillTableStaff();
        } else if (selectedIndex == 2) {
            txtIDResetPassStaff.setText("");
            this.fillFindResetPassStaff();
        }
    }//GEN-LAST:event_tabStaffMouseClicked

    private void tableResetPassStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableResetPassStaffMouseClicked
        int index = tableResetPassStaff.getSelectedRow();
        String ID_Staff = tableResetPassStaff.getValueAt(index, 1).toString();
        txtIDResetPassStaff.setText(ID_Staff);
    }//GEN-LAST:event_tableResetPassStaffMouseClicked

    private void tableResetPassTeacherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableResetPassTeacherMouseClicked
        int index = tableResetPassTeacher.getSelectedRow();
        String ID_Teacher = tableResetPassTeacher.getValueAt(index, 1).toString();
        txtIDResetPassTeacher.setText(ID_Teacher);
    }//GEN-LAST:event_tableResetPassTeacherMouseClicked

    private void tabStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabStudentMouseClicked
        int selectedIndex = tabStudent.getSelectedIndex();
        if (selectedIndex == 0) {
            txtFindStudent.setText("");
            this.fillTableStudent();
        }
    }//GEN-LAST:event_tabStudentMouseClicked

    private void tabStudentAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tabStudentAncestorAdded

    }//GEN-LAST:event_tabStudentAncestorAdded

    private void txtFindStudentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindStudentKeyReleased
        this.fillTableStudent();
    }//GEN-LAST:event_txtFindStudentKeyReleased

    private void tableStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableStudentMouseClicked
        tableStudent.setEnabled(false);
        if (evt.getClickCount() == 2) {
            this.clickTableStudent();
            tableStudent.setEnabled(true);
        }
    }//GEN-LAST:event_tableStudentMouseClicked

    private void btnResetFormStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFormStudentActionPerformed
        this.resetFormStudent();
    }//GEN-LAST:event_btnResetFormStudentActionPerformed

    private void btnUpdateStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateStudentActionPerformed
        this.updateStudent();
    }//GEN-LAST:event_btnUpdateStudentActionPerformed

    private void btnAddStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStudentActionPerformed
        this.addStudent();
    }//GEN-LAST:event_btnAddStudentActionPerformed

    private void btnUploadImgStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadImgStudentActionPerformed
        this.chooseImageStudent();
    }//GEN-LAST:event_btnUploadImgStudentActionPerformed

    private void btnAddParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddParentActionPerformed
        this.addParent();
    }//GEN-LAST:event_btnAddParentActionPerformed

    private void btnUpdateParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateParentActionPerformed
        this.updateParent();
    }//GEN-LAST:event_btnUpdateParentActionPerformed

    private void btnResetFormParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFormParentActionPerformed
        this.resetFormParent();
    }//GEN-LAST:event_btnResetFormParentActionPerformed

    private void tableParentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableParentMouseClicked
        tableParent.setEnabled(false);
        if (evt.getClickCount() == 2) {
            this.clickTableParent();
            tableParent.setEnabled(true);
        }
    }//GEN-LAST:event_tableParentMouseClicked

    private void txtFindParentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindParentKeyReleased
        this.fillTableParent();
    }//GEN-LAST:event_txtFindParentKeyReleased

    private void txtIDResetPassParentKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDResetPassParentKeyReleased
        this.fillTableResetPassParent();
    }//GEN-LAST:event_txtIDResetPassParentKeyReleased

    private void tableResetPassParentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableResetPassParentMouseClicked
        int index = tableResetPassParent.getSelectedRow();
        String ID_Parent = tableResetPassParent.getValueAt(index, 1).toString();
        txtIDResetPassParent.setText(ID_Parent);
    }//GEN-LAST:event_tableResetPassParentMouseClicked

    private void btnResetPassParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPassParentActionPerformed
        if (txtIDResetPassParent.getText().isEmpty()) {
            Message.alert(this, "Please enter information !");
            txtIDResetPassParent.requestFocus();
            return;
        }
        if (Message.confirm(this, "Do you want to reset the password for this guardians?")) {
            guardians_DAO.adminResetPassParent(txtIDResetPassParent.getText());
            this.fillTableResetPassParent();
            tabParent.setSelectedIndex(0);
            txtIDResetPassParent.setText("");
        }
    }//GEN-LAST:event_btnResetPassParentActionPerformed

    private void btnExitResetPassParentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitResetPassParentActionPerformed
        if (Message.confirm(this, "Do you want exit ?")) {
            this.setCardFalse();
        }
    }//GEN-LAST:event_btnExitResetPassParentActionPerformed

    private void tabParentAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tabParentAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tabParentAncestorAdded

    private void tabParentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabParentMouseClicked
        int selectedIndex = tabParent.getSelectedIndex();
        if (selectedIndex == 1) {
            txtFindParent.setText("");
            this.fillTableParent();
        } else if (selectedIndex == 2) {
            txtIDResetPassParent.setText("");
            this.fillTableResetPassParent();
        } else if (selectedIndex == 3) {
            txtFindRelationship.setText("");
            this.fillTableRelationship();
        }
    }//GEN-LAST:event_tabParentMouseClicked

    private void cbMonthStudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMonthStudentActionPerformed

    }//GEN-LAST:event_cbMonthStudentActionPerformed

    private void txtFindRelationshipKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindRelationshipKeyReleased
        this.fillTableRelationship();
    }//GEN-LAST:event_txtFindRelationshipKeyReleased

    private void tableRelationshipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableRelationshipMouseClicked
        tableRelationship.setEnabled(false);
        if (evt.getClickCount() == 2) {
            this.clickTableRelationship();
            tableRelationship.setEnabled(true);
        }
    }//GEN-LAST:event_tableRelationshipMouseClicked

    private void btnResetFormReletionshipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFormReletionshipActionPerformed
        this.resetFormRelationship();
    }//GEN-LAST:event_btnResetFormReletionshipActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.addRelationship();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnDeleteRelationshipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRelationshipActionPerformed
        this.deleteRelationship();
    }//GEN-LAST:event_btnDeleteRelationshipActionPerformed

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        this.setCardFalse();
        cardLearningManagement.setVisible(true);
        lbTitle.setText("Viet Duc School - Learning Management");
        this.resetFormCourse();
        this.resetFormCourseInformation();
        this.resetFormClass();
        this.resetFormSubject();
        this.setTimeSlide();
        this.tabCourse.setSelectedIndex(0);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void tabCourseAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tabCourseAncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_tabCourseAncestorAdded

    private void tabCourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabCourseMouseClicked
        int selectedIndex = tabCourse.getSelectedIndex();
        if (selectedIndex == 0) {
            txtFindCourse.setText("");
            this.fillTableCourse();
        } else if (selectedIndex == 1) {
            txtFindCourseInformation.setText("");
            this.fillTableCourseInformation();
        } else if (selectedIndex == 2) {
            txtFindClass.setText("");
            this.fillTableClass();
        } else if (selectedIndex == 3) {
            txtFindSubject.setText("");
            this.fillTableSubject();
        }
    }//GEN-LAST:event_tabCourseMouseClicked

    private void btnResetFormCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFormCourseActionPerformed
        this.resetFormCourse();
    }//GEN-LAST:event_btnResetFormCourseActionPerformed

    private void btnUpdateTeacher1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateTeacher1ActionPerformed
        this.updateCourse();
    }//GEN-LAST:event_btnUpdateTeacher1ActionPerformed

    private void btnAddCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCourseActionPerformed
        this.addCourse();
    }//GEN-LAST:event_btnAddCourseActionPerformed

    private void txtIDClassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDClassKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDClassKeyReleased

    private void txtFindCourseInformationKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindCourseInformationKeyReleased
        this.fillTableCourseInformation();
    }//GEN-LAST:event_txtFindCourseInformationKeyReleased

    private void btnAddCourse_RelationshipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCourse_RelationshipActionPerformed
        this.addCourseRelationship();
    }//GEN-LAST:event_btnAddCourse_RelationshipActionPerformed

    private void btnResetFormCourseInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetFormCourseInformationActionPerformed
        this.resetFormCourseInformation();
    }//GEN-LAST:event_btnResetFormCourseInformationActionPerformed

    private void tableCourseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCourseMouseClicked
        tableCourse.setEnabled(false);
        if (evt.getClickCount() == 2) {
            this.clickTableCourse();
            tableCourse.setEnabled(true);
        }
    }//GEN-LAST:event_tableCourseMouseClicked

    private void txtFindCourseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindCourseKeyReleased
        this.fillTableCourse();
    }//GEN-LAST:event_txtFindCourseKeyReleased

    private void tableCourse_RelationshipMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCourse_RelationshipMouseClicked
        tableCourse_Relationship.setEnabled(false);
        if (evt.getClickCount() == 2) {
            this.clickTableCourseInformation();
            tableCourse_Relationship.setEnabled(true);
        }
    }//GEN-LAST:event_tableCourse_RelationshipMouseClicked

    private void btnDeleteCourse_RelationshipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCourse_RelationshipActionPerformed
        this.deleteCourseRelationship();
    }//GEN-LAST:event_btnDeleteCourse_RelationshipActionPerformed

    private void txtFindClassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindClassKeyReleased
        this.fillTableClass();
    }//GEN-LAST:event_txtFindClassKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.resetFormClass();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void tableClassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableClassMouseClicked
        tableClass.setEnabled(false);
        if (evt.getClickCount() == 2) {
            this.clickTableClass();
            tableClass.setEnabled(true);
        }
    }//GEN-LAST:event_tableClassMouseClicked

    private void btnAddClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddClassActionPerformed
        this.addClass();
    }//GEN-LAST:event_btnAddClassActionPerformed

    private void btnUpdateClassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateClassActionPerformed
        this.updateClass();
    }//GEN-LAST:event_btnUpdateClassActionPerformed

    private void btnResetSubjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSubjectActionPerformed
        this.resetFormSubject();
    }//GEN-LAST:event_btnResetSubjectActionPerformed

    private void tableSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableSubjectMouseClicked
        tableSubject.setEnabled(false);
        if (evt.getClickCount() == 2) {
            this.clickTableSubject();
            tableSubject.setEnabled(true);
        }
    }//GEN-LAST:event_tableSubjectMouseClicked

    private void txtFindSubjectKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindSubjectKeyReleased
        this.fillTableSubject();
    }//GEN-LAST:event_txtFindSubjectKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.addSubject();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.updateSubject();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnExportTeacherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportTeacherActionPerformed
        try {
            List<Teacher> teacherList = teacher_DAO.selectAll();
            Workbook workbook = ExcelUtils.exportToExcelTeacher(teacherList);
            chooseDirectoryToSave(workbook);
        } catch (Exception e) {
            e.getMessage();
        }
    }//GEN-LAST:event_btnExportTeacherActionPerformed

    private void tableScheduleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableScheduleMouseClicked
        tableSchedule.setEnabled(false);
        if (evt.getClickCount() == 2) {
            this.clickTableSchedule();
            tableSchedule.setEnabled(true);
        }
    }//GEN-LAST:event_tableScheduleMouseClicked

    private void txtFindScheduleKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindScheduleKeyReleased
        this.fillTableSchedule();
    }//GEN-LAST:event_txtFindScheduleKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.resetFormSchedule();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void btnAddScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddScheduleActionPerformed
        this.addSchedule();
    }//GEN-LAST:event_btnAddScheduleActionPerformed

    private void btnUpdateScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateScheduleActionPerformed
        this.updateSchedule();
    }//GEN-LAST:event_btnUpdateScheduleActionPerformed

    private void btnDeleteScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteScheduleActionPerformed
        this.deleteSchedule();
    }//GEN-LAST:event_btnDeleteScheduleActionPerformed

    private void btnResetPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetPointActionPerformed
        this.resetFormPoint();
    }//GEN-LAST:event_btnResetPointActionPerformed

    private void txtFindPointKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindPointKeyReleased
        this.fillTablePoint();
    }//GEN-LAST:event_txtFindPointKeyReleased

    private void tablePointMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePointMouseClicked
        tablePoint.setEnabled(false);
        if (evt.getClickCount() == 2) {
            this.clickTablePoint();
            tablePoint.setEnabled(true);
        }
    }//GEN-LAST:event_tablePointMouseClicked

    private void btnAddPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPointActionPerformed
        this.addPoint();
    }//GEN-LAST:event_btnAddPointActionPerformed

    private void btnUpdatePointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdatePointActionPerformed
        this.updatePoint();
    }//GEN-LAST:event_btnUpdatePointActionPerformed

    private void btnExportPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportPointActionPerformed
        try {
            List<Point> points = pointDAO.selectAll();
            Workbook workbook = ExcelUtils.exportToExcelPoint(points);
            chooseDirectoryToSave(workbook);
            
        } catch (Exception e) {
            e.getMessage();
        }
    }//GEN-LAST:event_btnExportPointActionPerformed

    private void btnImportPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportPointActionPerformed
        File file = this.chonFileExcelImportPoint();
        if (file == null) {
            Message.alert(this, "Lỗi dọc tập tin Excel!");
        } else {
            this.importToExcelPoint(file);
        }
    }//GEN-LAST:event_btnImportPointActionPerformed

    private void txtFindBonusKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindBonusKeyReleased
        fillTableBonus();
    }//GEN-LAST:event_txtFindBonusKeyReleased
    private File chonFileExcelImportPoint() {
        File excelFile = null;
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            excelFile = fileChooser.getSelectedFile();
        }
        return excelFile;
    }

    private void importToExcelPoint(File excelFile) {
        try {
            FileInputStream file = new FileInputStream(excelFile);
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            rowIterator.next(); // Skip the header row

            Point point = new Point();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                point.setID_Student(getCellValue(row.getCell(0)));
                point.setID_Class(getCellValue(row.getCell(1)));
                point.setID_Subject(getCellValue(row.getCell(2)));
                point.setID_Teacher(getCellValue(row.getCell(3)));

                String yearCellValue = getCellValue(row.getCell(4));
                if (yearCellValue.contains(".")) {
                    point.setYear((int) Float.parseFloat(yearCellValue));
                } else {
                    point.setYear(Integer.parseInt(yearCellValue));
                }

                point.setPoint(Float.parseFloat(getCellValue(row.getCell(6))));
                point.setCourse_Name(getCellValue(row.getCell(5)));
                point.setNote(getCellValue(row.getCell(7)));

                if (pointDAO.checkID(point) == true) {
                    Message.alert(this, "Duplicate information !");
                    return;
                }
                pointDAO.insert(point);
            }

            file.close();
            fillTablePoint();
            Message.alert(this, "Import successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    return String.valueOf(cell.getDateCellValue()); // Xử lý trường hợp ngày tháng
                } else {
                    return String.valueOf((float) cell.getNumericCellValue()); // Đổi kiểu dữ liệu thành float
                }
            default:
                return "";
        }
    }

    private void setCardFalse() {
        cardTrangChu.setVisible(false);
        cardInformation.setVisible(false);
        cardListStudent.setVisible(false);
        cardDoimk.setVisible(false);
        cardListTeacher.setVisible(false);
        cardListStaff.setVisible(false);
        cardQRCode.setVisible(false);
        cardListParent.setVisible(false);
        cardLearningManagement.setVisible(false);
    }

    private void setTimeSlide() {
        if (x == 210) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 210; i >= 0; i--) {
                            jplSlideMenu.setSize(i, y);
                            Thread.sleep(2);
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
            x = 0;
        }
    }

    private boolean checkNull() {
        JPasswordField passwordField[] = {txtCurrentPass, txtNewPass};
        if (!IsValidForm.checkNull(passwordField)) {
            return false;
        } else if (!Arrays.equals(txtNewPass.getPassword(), txtEnterPass.getPassword())) {
            txtEnterPass.requestFocus();
            Message.alert(this, "New password does not match when re-entered !");
            return false;
        }
        return true;
    }

    private void resetFormChangePassWord() {
        JTextField text[] = {txtCurrentPass, txtNewPass, txtEnterPass};
        IsValidForm.refreshForm(text);
        tabAccount.setSelectedIndex(0);
    }

    private void setFormAdmin() {
        Administrators admin = adminDAO.selectById(Authentication.admin.getID_Administrator());
        txtID.setText(admin.getID_Administrator());
        txtFirstName.setText(admin.getFirst_Name());
        txtMidName.setText(admin.getMiddle_Name());
        txtLastName.setText(admin.getLast_Name());
        txtEmail.setText(admin.getEmail());
        txtPhone.setText(admin.getPhone_Number());
        txtNote.setText(admin.getNote());
        txtAddress.setText(admin.getAddress());
        (admin.isGender() ? rdMale : rdFemale).setSelected(true);
        lbNameAdmin.setText(admin.getMiddle_Name() + " " + admin.getLast_Name() + " " + admin.getFirst_Name());
    }

    private Administrators getFormAdmin() {
        Administrators admin = new Administrators();
        admin.setID_Administrator(Authentication.admin.getID_Administrator());
        admin.setFirst_Name(txtFirstName.getText());
        admin.setMiddle_Name(txtMidName.getText());
        admin.setLast_Name(txtLastName.getText());
        admin.setEmail(txtEmail.getText());
        admin.setPhone_Number(txtPhone.getText());
        admin.setAddress(txtAddress.getText());
        admin.setNote(txtNote.getText());
        admin.setGender(rdMale.isSelected() ? true : false);
        return admin;
    }

    private void changePassword() {
        adminDAO.changePassword(Authentication.admin.getID_Administrator(),
                String.valueOf(txtCurrentPass.getPassword()), String.valueOf(txtEnterPass.getPassword()));
        this.resetFormChangePassWord();
    }

    private void resetFormInformation() {
        JTextComponent text[] = {txtFirstName, txtMidName, txtLastName, txtEmail, txtPhone, txtAddress, txtNote};
        IsValidForm.refreshForm(text);
    }

    private void updateInformation() {
        JTextField textField[] = {txtFirstName, txtLastName, txtEmail, txtPhone, txtAddress};
        if (!IsValidForm.checkNull(textField)) {
            return;
        } else if (!IsValidForm.isValidEmail(txtEmail.getText())) {
            Message.alert(this, "Email is not valid !");
            txtEmail.requestFocus();
            return;
        } else if (!IsValidForm.isValidPhoneNumber(txtPhone.getText())) {
            Message.alert(this, "Phone number is not valid !");
            txtPhone.requestFocus();
            return;
        }

        if (Message.confirm(this, "You want to change information?")) {
            Administrators admin = getFormAdmin();
            adminDAO.update(admin);
            Message.alert(null, "Edited information successfully !");
        }
    }

    private void initTableTeacher() {
        String columns[] = {"ID", "Fullname", "Email", "Phone", "Address", "Gender", "Status", "Level", "Date of birth", "Start date"};
        tableModelTeacher.setColumnIdentifiers(columns);
        tableTeacher.setModel(tableModelTeacher);
        String columnsResetPass[] = {"No.", "ID Teacher", "Fullname", "Password"};
        tableModelResetPassTeacher.setColumnIdentifiers(columnsResetPass);
        tableResetPassTeacher.setModel(tableModelResetPassTeacher);
    }

    private void uploadComboboxTeacher() {
        DefaultComboBoxModel cbYearModel = new DefaultComboBoxModel();
        DefaultComboBoxModel cbMonthModel = new DefaultComboBoxModel();
        DefaultComboBoxModel cbLevel = new DefaultComboBoxModel();
        for (int i = 1970; i < 2005; i++) {
            cbYearModel.addElement(i);
        }
        for (int i = 1; i <= 12; i++) {
            cbMonthModel.addElement(i);
        }
        cbYearTeacher.setModel(cbYearModel);
        cbMonthTeacher.setModel(cbMonthModel);
        updateDays();
        cbMonthTeacher.addActionListener((e) -> {
            updateDays();
        });
        String datas[] = {"College", "High School", "Intermediate", "Master", "Middle School", "Primary School", "University"};
        for (String data : datas) {
            cbLevel.addElement(data);
        }
        cbLevelTeacher.setModel(cbLevel);
    }

    private int getDaysInMonth(int month, int year) {
        switch (month) {
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
            default:
                return 31;
        }
    }

    private void updateDays() {
        DefaultComboBoxModel cbDayModel = new DefaultComboBoxModel<>();
        int selectedMonth = (Integer) cbMonthTeacher.getSelectedItem();
        int selectedYear = (Integer) cbYearTeacher.getSelectedItem();
        int daysInMonth = getDaysInMonth(selectedMonth, selectedYear);
        cbDateTeacher.removeAllItems();
        for (int i = 1; i <= daysInMonth; i++) {
            cbDayModel.addElement(i);
        }
        cbDateTeacher.setModel(cbDayModel);
        cbDateStaff.setModel(cbDayModel);
        cbDateStudent.setModel(cbDayModel);
    }

    private void updateDaysStaff() {
        DefaultComboBoxModel cbDayModel = new DefaultComboBoxModel<>();
        int selectedMonth = (Integer) cbMonthStaff.getSelectedItem();
        int selectedYear = (Integer) cbYearStaff.getSelectedItem();
        int daysInMonth = getDaysInMonth(selectedMonth, selectedYear);
        cbDateStaff.removeAllItems();
        for (int i = 1; i <= daysInMonth; i++) {
            cbDayModel.addElement(i);
        }
        cbDateStaff.setModel(cbDayModel);
    }

    private void updateDaysStudent() {
        DefaultComboBoxModel cbDayModel = new DefaultComboBoxModel<>();
        int selectedMonth = (Integer) cbMonthStudent.getSelectedItem();
        int selectedYear = (Integer) cbYearStudent.getSelectedItem();
        int daysInMonth = getDaysInMonth(selectedMonth, selectedYear);
        cbDateStudent.removeAllItems();
        for (int i = 1; i <= daysInMonth; i++) {
            cbDayModel.addElement(i);
        }
        cbDateStudent.setModel(cbDayModel);
    }

    private void resetFormTeacher() {
        JTextComponent text[] = {txtIDTeacher, txtFirstNameTeacher, txtMidNameTeacher, txtLastNameTeacher, txtEmailTeacher,
            txtPhoneTeacher, txtAddressTeacher, txtNoteTeacher, txtStartDateTeacher};
        IsValidForm.refreshForm(text);
        rdMaleTeacher.setSelected(true);
        rdONTeacher.setSelected(true);
        cbYearTeacher.setSelectedIndex(0);
        cbMonthTeacher.setSelectedIndex(0);
        cbDateTeacher.setSelectedIndex(0);
        cbLevelTeacher.setSelectedIndex(0);
        lbAvatarTeacher.setIcon(null);
        txtIDTeacher.requestFocus();
    }

    private void chooseImageTeacher() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lbAvatarTeacher.setIcon(icon);
            lbAvatarTeacher.setToolTipText(file.getName());
        }
    }

    private void setFormTeacher(Teacher teacher) {
        txtIDTeacher.setText(teacher.getID_Teacher());
        txtFirstNameTeacher.setText(teacher.getFirst_Name());
        txtMidNameTeacher.setText(teacher.getMiddle_Name());
        txtLastNameTeacher.setText(teacher.getLast_Name());
        txtEmailTeacher.setText(teacher.getEmail());
        txtPhoneTeacher.setText(teacher.getPhone_Number());
        txtAddressTeacher.setText(teacher.getAddress_Teacher());
        txtNoteTeacher.setText(teacher.getNote());
        (teacher.isGender() ? rdMaleTeacher : rdFemaleTeacher).setSelected(true);
        (teacher.isStatus_Teacher() ? rdONTeacher : rdOFFTeacher).setSelected(true);
        cbMonthTeacher.setSelectedItem(teacher.getMonth_Of_Birth());
        cbYearTeacher.setSelectedItem(teacher.getYear_Of_Birth());
        cbDateTeacher.setSelectedItem(teacher.getDate_Of_Birth());
        cbLevelTeacher.setSelectedItem(teacher.getLevel_Teacher());
        txtStartDateTeacher.setText(String.valueOf(teacher.getStart_Date()));
        lbAvatarTeacher.setIcon(XImage.read(teacher.getAvatar()));
    }

    private void clickTable() {
        int index = tableTeacher.getSelectedRow();
        String ID_Teacher = tableTeacher.getValueAt(index, 0).toString();
        Teacher teacher = teacher_DAO.selectById(ID_Teacher);
        this.setFormTeacher(teacher);
        tabTeacher.setSelectedIndex(0);
    }

    private Teacher getFormTeacher() {
        Teacher teacher = new Teacher();
        teacher.setID_Teacher(txtIDTeacher.getText());
        teacher.setFirst_Name(txtFirstNameTeacher.getText());
        teacher.setMiddle_Name(txtMidNameTeacher.getText());
        teacher.setLast_Name(txtLastNameTeacher.getText());
        teacher.setEmail(txtEmailTeacher.getText());
        teacher.setPhone_Number(txtPhoneTeacher.getText());
        teacher.setAddress_Staff(txtAddressTeacher.getText());
        teacher.setNote(txtNoteTeacher.getText());
        teacher.setDate_Of_Birth((int) cbDateTeacher.getSelectedItem());
        teacher.setMonth_Of_Birth((int) cbMonthTeacher.getSelectedItem());
        teacher.setYear_Of_Birth((int) cbYearTeacher.getSelectedItem());
        teacher.setLevel_Teacher((String) cbLevelTeacher.getSelectedItem());
        teacher.setGender(rdMale.isSelected() ? true : false);
        teacher.setStatus_Teacher(rdONTeacher.isSelected() ? true : false);
        teacher.setAvatar(lbAvatarTeacher.getToolTipText());
        return teacher;
    }

    private void addTeacher() {
        JTextComponent text[] = {txtIDTeacher, txtFirstNameTeacher, txtLastNameTeacher,
            txtEmailTeacher, txtPhoneTeacher, txtAddressTeacher};
        IsValidForm.checkNull(text);
        if (lbAvatarTeacher.getToolTipText() == null) {
            Message.alert(this, "Please choose a representative photo !");
            return;
        }
        Teacher teacher = getFormTeacher();
        if (teacher_DAO.checkCountIDTeacher(txtIDTeacher.getText()) == false) {
            Message.alert(this, "The teacher code is already in use !");
            return;
        }
        try {
            teacher_DAO.insert(teacher);
            this.fillFindTableTeacher();
            this.resetFormTeacher();
            Message.alert(this, "Teacher added successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTeacher() {
        JTextField textField[] = {txtIDTeacher, txtFirstNameTeacher, txtLastNameTeacher,
            txtEmailTeacher, txtPhoneTeacher, txtAddressTeacher};
        if (!IsValidForm.checkNull(textField)) {
            return;
        } else if (!IsValidForm.isValidEmail(txtEmailTeacher.getText())) {
            Message.alert(this, "Email is not valid !");
            txtEmailTeacher.requestFocus();
            return;
        } else if (!IsValidForm.isValidPhoneNumber(txtPhoneTeacher.getText())) {
            Message.alert(this, "Phone number is not valid !");
            txtPhoneTeacher.requestFocus();
            return;
        }

        if (Message.confirm(this, "You want to change information?")) {
            Teacher teacher = getFormTeacher();
            teacher_DAO.update(teacher);
            this.fillFindTableTeacher();
            Message.alert(this, "Edited information successfully !");
            this.resetFormTeacher();
        }
    }

    private void fillFindTableTeacher() {
        tableModelTeacher.setRowCount(0);
        try {
            List<Teacher> list = teacher_DAO.selectByKeyword(txtFindTeacher.getText());
            for (Teacher teacher : list) {
                Object rows[] = {teacher.getID_Teacher(), teacher.getMiddle_Name() + " " + teacher.getLast_Name() + " " + teacher.getFirst_Name(),
                    teacher.getEmail(), teacher.getPhone_Number(), teacher.getAddress_Teacher(),
                    teacher.isGender() ? "Male" : "Female", teacher.isStatus_Teacher() ? "ON" : "OFF",
                    teacher.getLevel_Teacher(), teacher.getYear_Of_Birth() + "-" + teacher.getMonth_Of_Birth() + "-" + teacher.getDate_Of_Birth(),
                    teacher.getStart_Date()};
                tableModelTeacher.addRow(rows);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void fillFindResetPassTeacher() {
        tableModelResetPassTeacher.setRowCount(0);
        try {
            List<Teacher> list = teacher_DAO.selectByKeyword(txtIDResetPassTeacher.getText());
            int i = 1;
            for (Teacher teacher : list) {
                String hashedPassword = teacher.getPassword_Teacher();
                String hiddenPassword = "*".repeat(hashedPassword.length());
                Object rows[] = {i++, teacher.getID_Teacher(), teacher.getMiddle_Name() + " " + teacher.getLast_Name() + " "
                    + teacher.getFirst_Name(), hiddenPassword};
                tableModelResetPassTeacher.addRow(rows);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTableStaff() {
        String columns[] = {"ID", "Fullname", "Email", "Phone", "Address", "Gender", "Status", "Position", "Date of birth", "Start date"};
        tableModelStaff.setColumnIdentifiers(columns);
        tableStaff.setModel(tableModelStaff);
        String columnsResetPass[] = {"No.", "ID Staff", "Fullname", "Password"};
        tableModelResetPassStaff.setColumnIdentifiers(columnsResetPass);
        tableResetPassStaff.setModel(tableModelResetPassStaff);
    }

    private void uploadComboboxStaff() {
        DefaultComboBoxModel cbYearModel = new DefaultComboBoxModel();
        DefaultComboBoxModel cbMonthModel = new DefaultComboBoxModel();
        DefaultComboBoxModel cbPosition = new DefaultComboBoxModel();
        for (int i = 1970; i < 2005; i++) {
            cbYearModel.addElement(i);
        }
        for (int i = 1; i <= 12; i++) {
            cbMonthModel.addElement(i);
        }
        cbYearStaff.setModel(cbYearModel);
        cbMonthStaff.setModel(cbMonthModel);
        updateDaysStaff();
        cbMonthStaff.addActionListener((e) -> {
            updateDaysStaff();
        });
        String datas[] = {"Chef", "Doctor", "IT", "Laborer", "Office", "Security Guard"};
        for (String data : datas) {
            cbPosition.addElement(data);
        }
        cbPositionStaff.setModel(cbPosition);
    }

    private void resetFormStaff() {
        JTextComponent text[] = {txtIDStaff, txtFirstNameStaff, txtMidNameStaff, txtLastNameStaff, txtEmailStaff,
            txtPhoneStaff, txtAddressStaff, txtNoteStaff, txtStartDateStaff};
        IsValidForm.refreshForm(text);
        rdMaleStaff.setSelected(true);
        rdONStaff.setSelected(true);
        cbYearStaff.setSelectedIndex(0);
        cbMonthStaff.setSelectedIndex(0);
        cbDateStaff.setSelectedIndex(0);
        cbPositionStaff.setSelectedIndex(0);
        lbAvatarStaff.setIcon(null);
        txtIDStaff.requestFocus();
    }

    private void fillTableStaff() {
        tableModelStaff.setRowCount(0);
        try {
            String keyword = txtFindStaff.getText();
            List<Staff> list = staff_DAO.selectByKeyword(keyword);
            for (Staff sta : list) {
                Object[] row = {
                    sta.getID_Staff(),
                    sta.getFirst_Name() + " " + sta.getMiddle_Name() + " " + sta.getLast_Name(),
                    sta.getEmail(),
                    sta.getPhone_Number(),
                    sta.getAddress_Staff(),
                    sta.isGender() ? "Male" : "Female",
                    sta.isStatus_Staff() ? "ON" : "OFF",
                    sta.getPosition(),
                    sta.getYear_Of_Birth() + "-" + sta.getMonth_Of_Birth() + "-" + sta.getDate_Of_Birth(),
                    sta.getStart_Date(),
                    sta.getNote(),};
                tableModelStaff.addRow(row);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    private void fillFindResetPassStaff() {
        tableModelResetPassStaff.setRowCount(0);
        try {
            String keyword = txtIDResetPassStaff.getText();
            List<Staff> list = staff_DAO.selectByKeyword(keyword);
            int i = 1;
            for (Staff staff : list) {
                String hashedPassword = staff.getPassword_Staff();
                String hiddenPassword = "*".repeat(hashedPassword.length());
                Object[] row = {i++, staff.getID_Staff(), staff.getFirst_Name() + " " + staff.getMiddle_Name()
                    + " " + staff.getLast_Name(), hiddenPassword};
                tableModelResetPassStaff.addRow(row);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

    private void setFormStaff(Staff staff) {
        txtIDStaff.setText(staff.getID_Staff());
        txtFirstNameStaff.setText(staff.getFirst_Name());
        txtMidNameStaff.setText(staff.getMiddle_Name());
        txtLastNameStaff.setText(staff.getLast_Name());
        txtEmailStaff.setText(staff.getEmail());
        txtPhoneStaff.setText(staff.getPhone_Number());
        txtAddressStaff.setText(staff.getAddress_Staff());
        txtNoteStaff.setText(staff.getNote());
        (staff.isGender() ? rdMaleStaff : rdFemaleStaff).setSelected(true);
        (staff.isStatus_Staff() ? rdONStaff : rdOFFStaff).setSelected(true);
        cbMonthStaff.setSelectedItem(staff.getMonth_Of_Birth());
        cbYearStaff.setSelectedItem(staff.getYear_Of_Birth());
        cbDateStaff.setSelectedItem(staff.getDate_Of_Birth());
        cbPositionStaff.setSelectedItem(staff.getPosition());
        txtStartDateStaff.setText(String.valueOf(staff.getStart_Date()));
        lbAvatarStaff.setIcon(XImage.read(staff.getAvatar()));
    }

    private void clickTableStaff() {
        int index = tableStaff.getSelectedRow();
        String ID_Staff = tableStaff.getValueAt(index, 0).toString();
        Staff staff = staff_DAO.selectById(ID_Staff);
        this.setFormStaff(staff);
        tabStaff.setSelectedIndex(0);
    }

    private Staff getFormStaff() {
        Staff staff = new Staff();
        staff.setID_Staff(txtIDStaff.getText());
        staff.setFirst_Name(txtFirstNameStaff.getText());
        staff.setMiddle_Name(txtMidNameStaff.getText());
        staff.setLast_Name(txtLastNameStaff.getText());
        staff.setEmail(txtEmailStaff.getText());
        staff.setPhone_Number(txtPhoneStaff.getText());
        staff.setAddress_Staff(txtAddressStaff.getText());
        staff.setNote(txtNoteStaff.getText());
        staff.setDate_Of_Birth((int) cbDateStaff.getSelectedItem());
        staff.setMonth_Of_Birth((int) cbMonthStaff.getSelectedItem());
        staff.setYear_Of_Birth((int) cbYearStaff.getSelectedItem());
        staff.setPosition((String) cbPositionStaff.getSelectedItem());
        staff.setGender(rdMale.isSelected() ? true : false);
        staff.setStatus_Staff(rdONStaff.isSelected() ? true : false);
        staff.setAvatar(lbAvatarStaff.getToolTipText());
        return staff;
    }

    private void chooseImageStaff() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lbAvatarStaff.setIcon(icon);
            lbAvatarStaff.setToolTipText(file.getName());
        }
    }

    private void addStaff() {
        JTextComponent textComponent[] = {txtIDStaff, txtFirstNameStaff,
            txtLastNameStaff, txtEmailStaff, txtPhoneStaff, txtAddressStaff};
        if (!IsValidForm.checkNull(textComponent)) {
            return;
        } else if (!IsValidForm.isValidEmail(txtEmailStaff.getText())) {
            Message.alert(this, "Email is not valid !");
            txtEmailStaff.requestFocus();
            return;
        } else if (!IsValidForm.isValidPhoneNumber(txtPhoneStaff.getText())) {
            Message.alert(this, "Phone number is not valid !");
            txtPhoneStaff.requestFocus();
            return;
        } else if (lbAvatarStaff.getToolTipText() == null) {
            Message.alert(this, "Please choose a representative photo !");
            return;
        } else if (staff_DAO.checkCountIDStaff(txtIDStaff.getText()) == false) {
            txtIDStaff.requestFocus();
            Message.alert(this, "Employee code already in use !");
            return;
        }
        Staff staff = getFormStaff();
        try {
            staff_DAO.insert(staff);
            this.fillTableStaff();
            this.resetFormStaff();
            Message.alert(this, "Staff added successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateStaff() {
        JTextComponent textComponent[] = {txtIDStaff, txtFirstNameStaff,
            txtLastNameStaff, txtEmailStaff, txtPhoneStaff, txtAddressStaff};
        if (!IsValidForm.checkNull(textComponent)) {
            return;
        } else if (!IsValidForm.isValidEmail(txtEmailStaff.getText())) {
            Message.alert(this, "Email is not valid !");
            txtEmailStaff.requestFocus();
            return;
        } else if (!IsValidForm.isValidPhoneNumber(txtPhoneStaff.getText())) {
            Message.alert(this, "Phone number is not valid !");
            txtPhoneStaff.requestFocus();
            return;
        }

        if (Message.confirm(this, "You want to change information?")) {
            Staff staff = getFormStaff();
            staff_DAO.update(staff);
            this.fillFindTableTeacher();
            Message.alert(this, "Edited information successfully !");
            this.resetFormStaff();
        }
    }

    private void initTableStudent() {
        String columns[] = {"ID", "Fullname", "Address", "Class", "Gender", "Status", "Date of birth"};
        tableModelStudent.setColumnIdentifiers(columns);
        tableStudent.setModel(tableModelStudent);
    }

    private void uploadComboboxStudent() {
        DefaultComboBoxModel cbYearModel = new DefaultComboBoxModel();
        DefaultComboBoxModel cbMonthModel = new DefaultComboBoxModel();
        for (int i = 2010; i < 2024; i++) {
            cbYearModel.addElement(i);
        }
        for (int i = 1; i <= 12; i++) {
            cbMonthModel.addElement(i);
        }
        cbYearStudent.setModel(cbYearModel);
        cbMonthStudent.setModel(cbMonthModel);
        updateDaysStudent();
        cbMonthStudent.addActionListener((e) -> {
            updateDaysStudent();
        });
    }

    private void fillTableStudent() {
        tableModelStudent.setRowCount(0);
        try {
            String keyword = txtFindStudent.getText();
            List<Student> list = studentDAO.selectByKeyword(keyword);
            for (Student student : list) {
                Object[] row = {
                    student.getID_Student(),
                    student.getFirst_Name() + " " + student.getMiddle_Name() + " " + student.getLast_Name(),
                    student.getAddress_Student(),
                    student.getID_Class(),
                    student.isGender() ? "Male" : "Female",
                    student.isStatus_Student() ? "ON" : "OFF",
                    student.getYear_Of_Birth() + "-" + student.getMonth_Of_Birth() + "-" + student.getDate_Of_Birth()
                };
                tableModelStudent.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFormStudent(Student student) {
        txtIDStudent.setText(student.getID_Student());
        txtFirstNameStudent.setText(student.getFirst_Name());
        txtMidNameStudent.setText(student.getMiddle_Name());
        txtLastNameStudent.setText(student.getLast_Name());
        txtAddressStudent.setText(student.getAddress_Student());
        txtNoteStudent.setText(student.getNote());
        (student.isGender() ? rdMaleStudent : rdFemaleStudent).setSelected(true);
        (student.isStatus_Student() ? rdONStudent : rdOFFStudent).setSelected(true);
        cbMonthStudent.setSelectedItem(student.getMonth_Of_Birth());
        cbDateStudent.setSelectedItem(student.getDate_Of_Birth());
        cbYearStudent.setSelectedItem(student.getYear_Of_Birth());
        lbAvatarStudent.setIcon(XImage.read(student.getAvatar()));
        txtClassStudent.setText(student.getID_Class());
    }

    private void clickTableStudent() {
        int index = tableStudent.getSelectedRow();
        String ID_Student = tableStudent.getValueAt(index, 0).toString();
        Student student = studentDAO.selectById(ID_Student);
        this.setFormStudent(student);
        tabStudent.setSelectedIndex(0);
    }

    private void resetFormStudent() {
        JTextComponent textComponent[] = {txtIDStudent, txtFirstNameStudent, txtMidNameStudent, txtLastNameStudent,
            txtAddressStudent, txtClassStudent, txtNoteStudent};
        IsValidForm.refreshForm(textComponent);
        lbAvatarStudent.setIcon(null);
        cbDateStudent.setSelectedIndex(0);
        cbMonthStudent.setSelectedIndex(0);
        cbYearStudent.setSelectedIndex(0);
        rdMaleStudent.setSelected(true);
        rdONStudent.setSelected(true);
        txtIDStudent.requestFocus();
    }

    private Student getFormStudent() {
        Student student = new Student();
        student.setID_Student(txtIDStudent.getText());
        student.setFirst_Name(txtFirstNameStudent.getText());
        student.setMiddle_Name(txtMidNameStudent.getText());
        student.setLast_Name(txtLastNameStudent.getText());
        student.setAddress_Student(txtAddressStudent.getText());
        student.setNote(txtNoteStudent.getText());
        student.setDate_Of_Birth((int) cbDateStudent.getSelectedItem());
        student.setMonth_Of_Birth((int) cbMonthStudent.getSelectedItem());
        student.setYear_Of_Birth((int) cbYearStudent.getSelectedItem());
        student.setGender(rdMaleStudent.isSelected() ? true : false);
        student.setStatus_Student(rdONStudent.isSelected() ? true : false);
        student.setAvatar(lbAvatarStudent.getToolTipText());
        student.setID_Class(txtClassStudent.getText());
        return student;
    }

    private void chooseImageStudent() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lbAvatarStudent.setIcon(icon);
            lbAvatarStudent.setToolTipText(file.getName());
        }
    }

    private void addStudent() {
        JTextComponent textComponent[] = {txtIDStudent, txtFirstNameStudent, txtLastNameStudent, txtAddressStudent, txtClassStudent};
        if (!IsValidForm.checkNull(textComponent)) {
            return;
        } else if (lbAvatarStudent.getToolTipText() == null) {
            Message.alert(this, "Please choose a representative photo !");
            return;
        } else if (studentDAO.checkCountIDStudent(txtIDStudent.getText()) == false) {
            Message.alert(this, "Student code is already in use !");
            txtIDStudent.requestFocus();
            return;
        }
        int count = studentDAO.selectCountStudent(txtClassStudent.getText());
        int Quantity = classDAO.selectQuantity(txtClassStudent.getText());
        if (count >= Quantity) {
            Message.alert(this, "The number of students in this class is sufficient !");
            txtClassStudent.requestFocus();
            return;
        }
        try {
            Student student = getFormStudent();
            studentDAO.insert(student);
            this.fillTableStudent();
            Message.alert(this, "Add student information successfully !");
            this.resetFormStudent();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void updateStudent() {
        JTextComponent textComponent[] = {txtIDStudent, txtFirstNameStudent, txtLastNameStudent, txtAddressStudent, txtClassStudent};
        if (!IsValidForm.checkNull(textComponent)) {
            return;
        } else if (Message.confirm(this, "You want to change information?")) {
            Student student = getFormStudent();
            studentDAO.update(student);
            this.fillTableStudent();
            Message.alert(this, "Edited information successfully !");
            this.resetFormStudent();
        }
    }

    private void resetFormParent() {
        JTextComponent textComponent[] = {txtIDParent, txtFirstNameParent, txtMidNameParent, txtLastNameParent, txtEmailParent,
            txtPhoneParent, txtAddressParent, txtNoteParent};
        IsValidForm.refreshForm(textComponent);
        rdMaleParent.setSelected(true);
        cbJobParent.setSelectedIndex(0);
    }

    private void initTableParent() {
        String columns[] = {"ID", "Fullname", "Address", "Email", "Phone", "Gender", "Job", "Note"};
        tableModelParent.setColumnIdentifiers(columns);
        tableParent.setModel(tableModelParent);
        String columnsResetPass[] = {"No.", "ID Guardians", "Fullname", "Password"};
        tableModelResetPassParent.setColumnIdentifiers(columnsResetPass);
        tableResetPassParent.setModel(tableModelResetPassParent);
    }

    private void uploadComboboxParent() {
        DefaultComboBoxModel cbbox = new DefaultComboBoxModel();
        String datas[] = {
            "Engineer", "Doctor", "Police Officer", "Nurse", "Architect",
            "Psychologist", "Counselor", "Social Worker", "Teacher", "Artist",
            "Lawyer", "Entrepreneur", "Scientist", "Writer", "Chef",
            "Athlete", "Engineer", "Executive", "Fashion Designer", "Scientist", "Other"
        };
        for (String data : datas) {
            cbbox.addElement(data);
        }
        cbJobParent.setModel(cbbox);
    }

    private void setFormParent(Guardians guardians) {
        txtIDParent.setText(guardians.getID_Guardians());
        txtFirstNameParent.setText(guardians.getFirst_Name());
        txtMidNameParent.setText(guardians.getMiddle_Name());
        txtLastNameParent.setText(guardians.getLast_Name());
        txtAddressParent.setText(guardians.getAddress_Guardians());
        txtNoteParent.setText(guardians.getNote());
        (guardians.isGender() ? rdMaleParent : rdFemaleParent).setSelected(true);
        txtEmailParent.setText(guardians.getEmail());
        txtPhoneParent.setText(guardians.getPhone_Number());
        cbJobParent.setSelectedItem(guardians.getJob());
    }

    private Guardians getFormGuardians() {
        Guardians guardians = new Guardians();
        guardians.setID_Guardians(txtIDParent.getText());
        guardians.setFirst_Name(txtFirstNameParent.getText());
        guardians.setMiddle_Name(txtMidNameParent.getText());
        guardians.setLast_Name(txtLastNameParent.getText());
        guardians.setAddress_Guardians(txtAddressParent.getText());
        guardians.setNote(txtNoteParent.getText());
        guardians.setGender(rdMaleParent.isSelected() ? true : false);
        guardians.setEmail(txtEmailParent.getText());
        guardians.setPhone_Number(txtPhoneParent.getText());
        guardians.setJob((String) cbJobParent.getSelectedItem());
        return guardians;
    }

    private void clickTableParent() {
        int index = tableParent.getSelectedRow();
        String ID_Guardians = tableParent.getValueAt(index, 0).toString();
        Guardians guardians = guardians_DAO.selectById(ID_Guardians);
        this.setFormParent(guardians);
        tabParent.setSelectedIndex(0);
    }

    private void fillTableParent() {
        tableModelParent.setRowCount(0);
        try {
            String keyword = txtFindParent.getText();
            List<Guardians> list = guardians_DAO.selectByKeyword(keyword);
            for (Guardians guardians : list) {
                Object[] row = {
                    guardians.getID_Guardians(),
                    guardians.getFirst_Name() + " " + guardians.getMiddle_Name() + " " + guardians.getLast_Name(),
                    guardians.getAddress_Guardians(),
                    guardians.getEmail(),
                    guardians.getPhone_Number(),
                    guardians.isGender() ? "Male" : "Female",
                    guardians.getJob(),
                    guardians.getNote()
                };
                tableModelParent.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillTableResetPassParent() {
        tableModelResetPassParent.setRowCount(0);
        try {
            String keyword = txtIDResetPassParent.getText();
            List<Guardians> list = guardians_DAO.selectByKeyword(keyword);
            int i = 1;
            for (Guardians guardians : list) {
                String hashedPassword = guardians.getID_Guardians();
                String hiddenPassword = "*".repeat(hashedPassword.length());
                Object[] row = {
                    i++,
                    guardians.getID_Guardians(),
                    guardians.getFirst_Name() + " " + guardians.getMiddle_Name() + " " + guardians.getLast_Name(), hiddenPassword};
                tableModelResetPassParent.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addParent() {
        JTextComponent textComponent[] = {txtIDParent, txtFirstNameParent,
            txtLastNameParent, txtEmailParent, txtPhoneParent, txtAddressParent};
        if (!IsValidForm.checkNull(textComponent)) {
            return;
        } else if (!IsValidForm.isValidEmail(txtEmailParent.getText())) {
            Message.alert(this, "Email is not valid !");
            txtEmailParent.requestFocus();
            return;
        } else if (!IsValidForm.isValidPhoneNumber(txtPhoneParent.getText())) {
            Message.alert(this, "Phone number is not valid !");
            txtPhoneParent.requestFocus();
            return;
        } else if (guardians_DAO.checkCountIDGuardians(txtIDParent.getText()) == false) {
            txtIDParent.requestFocus();
            Message.alert(this, "Guardians code already in use !");
            return;
        }
        try {
            Guardians guardians = getFormGuardians();
            guardians_DAO.insert(guardians);
            this.fillTableParent();
            this.resetFormParent();
            Message.alert(this, "Guardians added successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateParent() {
        JTextComponent textComponent[] = {txtIDParent, txtFirstNameParent,
            txtLastNameParent, txtEmailParent, txtPhoneParent, txtAddressParent};
        if (!IsValidForm.checkNull(textComponent)) {
            return;
        } else if (Message.confirm(this, "You want to change information?")) {
            Guardians guardians = getFormGuardians();
            guardians_DAO.update(guardians);
            this.fillTableStudent();
            Message.alert(this, "Edited information successfully !");
            this.resetFormParent();
        }
    }

    private void initTableRelationship() {
        String columns[] = {"No.", "ID_Guardians", "ID Student"};
        tableModelRelationship.setColumnIdentifiers(columns);
        tableRelationship.setModel(tableModelRelationship);
    }

    private void fillTableRelationship() {
        tableModelRelationship.setRowCount(0);
        try {
            String keyword = txtFindRelationship.getText();
            List<Guardian_Student_Relationship> list = guardian_Student_RelationshipDAO.selectByKeyword(keyword);
            int i = 1;
            for (Guardian_Student_Relationship relationship : list) {
                Object[] row = {
                    i++,
                    relationship.getID_Guardians(), relationship.getID_Student()};
                tableModelRelationship.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFormRelationship(Guardian_Student_Relationship relationship) {
        txtRelationshipStudent.setText(relationship.getID_Student());
        txtRelationshipGuadians.setText(relationship.getID_Guardians());
    }

    private void clickTableRelationship() {
        int index = tableRelationship.getSelectedRow();
        String ID = tableRelationship.getValueAt(index, 1).toString();
        Guardian_Student_Relationship relationship = guardian_Student_RelationshipDAO.selectById(ID);
        this.setFormRelationship(relationship);
    }

    private void resetFormRelationship() {
        txtRelationshipGuadians.setText("");
        txtRelationshipStudent.setText("");
        txtFindRelationship.setText("");
        txtFindRelationship.requestFocus();
        fillTableRelationship();
    }

    private Guardian_Student_Relationship getFormRelationship() {
        Guardian_Student_Relationship relationship = new Guardian_Student_Relationship();
        relationship.setID_Guardians(txtRelationshipGuadians.getText());
        relationship.setID_Student(txtRelationshipStudent.getText());
        return relationship;
    }

    private void addRelationship() {
        if (!IsValidForm.checkNull(txtRelationshipGuadians, txtRelationshipStudent)) {
            return;
        } else if (guardian_Student_RelationshipDAO.isRelationshipExists(txtRelationshipStudent.getText(),
                txtRelationshipGuadians.getText()) == true) {
            Message.alert(this, "The relationship already exists !");
            return;
        }
        try {
            Guardian_Student_Relationship relationship = getFormRelationship();
            guardian_Student_RelationshipDAO.insert(relationship);
            this.fillTableRelationship();
            this.resetFormRelationship();
            Message.alert(this, "The relationship was added successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteRelationship() {
        if (Message.confirm(this, "Do you want to delete this relationship ?")) {
            Guardian_Student_Relationship relationship = getFormRelationship();
            guardian_Student_RelationshipDAO.deleteRelationship(relationship);
            this.fillTableRelationship();
            this.resetFormRelationship();
            Message.alert(this, "The relationship has been successfully deleted!");
            tableRelationship.setEnabled(true);
        }
    }

    private void resetFormCourse() {
        JTextComponent textComponent[] = {txtIDCourse, txtYearCourse,
            txtNoteCourse, txtFindCourse};
        IsValidForm.refreshForm(textComponent);
        cbCourse.setSelectedIndex(0);
        txtIDCourse.requestFocus();
        fillTableCourse();
        tableCourse.setEnabled(true);
    }

    private void initTableCourse() {
        String columns[] = {"No.", "ID Course", "Course Name", "Year", "Note"};
        tableModelCourse.setColumnIdentifiers(columns);
        tableCourse.setModel(tableModelCourse);
    }

    private void uploadComboboxCourse() {
        final String courseName[] = {"Spring", "Summer", "Fall"};
        IsValidForm.fillComboBox(new DefaultComboBoxModel(courseName), Arrays.asList(courseName), cbCourse);
    }

    private void fillTableCourse() {
        tableModelCourse.setRowCount(0);
        try {
            String keyword = txtFindCourse.getText();
            List<Courses> list = coursesDAO.selectByKeyword(keyword);
            int i = 1;
            for (Courses courses : list) {
                Object[] row = {
                    i++,
                    courses.getID_Course(), courses.getCourse_Name(), courses.getYear(), courses.getNote()};
                tableModelCourse.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFormCourse(Courses courses) {
        txtIDCourse.setText(courses.getID_Course());
        txtYearCourse.setText(String.valueOf(courses.getYear()));
        txtNoteCourse.setText(courses.getNote());
        cbCourse.setSelectedItem(courses.getCourse_Name());
    }

    private Courses getFormCourse() {
        Courses courses = new Courses();
        courses.setID_Course(txtIDCourse.getText());
        courses.setCourse_Name((String) cbCourse.getSelectedItem());
        courses.setNote(txtNoteCourse.getText());
        courses.setYear(Integer.valueOf(txtYearCourse.getText()));
        return courses;
    }

    private void clickTableCourse() {
        int index = tableCourse.getSelectedRow();
        String ID = tableCourse.getValueAt(index, 1).toString();
        Courses courses = coursesDAO.selectById(ID);
        this.setFormCourse(courses);
    }

    private void addCourse() {
        JTextComponent textComponent[] = {txtIDCourse, txtYearCourse};
        if (!IsValidForm.checkNull(textComponent)) {
            return;
        }
        try {
            Integer year = Integer.parseInt(txtYearCourse.getText());
            if (year < 0) {
                Message.alert(this, "Please enter a positive integer greater than 0 !");
                txtYearCourse.requestFocus();
                return;
            }
        } catch (Exception e) {
            Message.alert(this, "Year must be an integer !");
            txtYearCourse.requestFocus();
            return;
        }
        Courses courses = getFormCourse();
        if (coursesDAO.checkID(courses) == false) {
            Message.alert(this, "The course code is already in use !");
            txtIDCourse.requestFocus();
            return;
        }
        try {
            coursesDAO.insert(courses);
            this.fillTableCourse();
            this.resetFormCourse();
            Message.alert(this, "Add course successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateCourse() {
        JTextComponent textComponent[] = {txtIDCourse, txtYearCourse};
        if (!IsValidForm.checkNull(textComponent)) {
            return;
        }
        try {
            Integer year = Integer.parseInt(txtYearCourse.getText());
            if (year < 0) {
                Message.alert(this, "Please enter a positive integer greater than 0 !");
                txtYearCourse.requestFocus();
                return;
            }
        } catch (Exception e) {
            Message.alert(this, "Year must be an integer !");
            txtYearCourse.requestFocus();
            return;
        }
        Courses courses = getFormCourse();
        if (coursesDAO.checkID(courses) == true) {
            Message.alert(this, "There is no code for this course !");
            txtIDCourse.requestFocus();
            return;
        }
        try {
            coursesDAO.update(courses);
            this.fillTableCourse();
            this.resetFormCourse();
            Message.alert(this, "Add course successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTableCourseInformation() {
        String columns[] = {"No.", "ID Course", "ID Class", "ID Teacher", "ID Student"};
        tableModelCourse_Information.setColumnIdentifiers(columns);
        tableCourse_Relationship.setModel(tableModelCourse_Information);
    }

    private void setFormCourseInformation(Course_Relationship courses) {
        cbCourse_Infor.setSelectedItem(courses.getID_Course());
        cbStudentCourse.setSelectedItem(courses.getID_Student());
        cbTeacherCourse.setSelectedItem(courses.getID_Teacher());
        cbClassCourse.setSelectedItem(courses.getID_Class());
    }

    private void uploadComboboxCourseInformation() {
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), teacher_DAO.selectAllIDTeacher(), cbClassTeacher);
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), teacher_DAO.selectAllIDTeacher(), cbTeacherCourse);
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), classDAO.selectAllIDClass(), cbClassCourse);
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), studentDAO.selectAllIDStudent(), cbStudentCourse);
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), coursesDAO.selectAllIDCourses(), cbCourse_Infor);
    }

    private void clickTableCourseInformation() {
        int index = tableCourse_Relationship.getSelectedRow();
        String ID_Course = tableCourse_Relationship.getValueAt(index, 1).toString();
        String ID_Class = tableCourse_Relationship.getValueAt(index, 2).toString();
        String ID_Teacher = tableCourse_Relationship.getValueAt(index, 3).toString();
        String ID_Student = tableCourse_Relationship.getValueAt(index, 4).toString();
        Course_Relationship courses = course_Relationship_DAO.selectByIdCourse_Relationship(ID_Course,
                ID_Class, ID_Student, ID_Teacher);
        this.setFormCourseInformation(courses);
    }

    private void fillTableCourseInformation() {
        tableModelCourse_Information.setRowCount(0);
        try {
            String keyword = txtFindCourseInformation.getText();
            List<Course_Relationship> list = course_Relationship_DAO.selectByKeyword(keyword);
            int i = 1;
            for (Course_Relationship courses : list) {
                Object[] row = {
                    i++,
                    courses.getID_Course(), courses.getID_Class(), courses.getID_Teacher(), courses.getID_Student()};
                tableModelCourse_Information.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetFormCourseInformation() {
        cbCourse_Infor.setSelectedIndex(0);
        cbStudentCourse.setSelectedIndex(0);
        cbTeacherCourse.setSelectedIndex(0);
        cbClassCourse.setSelectedIndex(0);
        fillTableCourseInformation();
        tableCourse_Relationship.setEnabled(true);
    }

    private Course_Relationship getFormCourse_Relationship() {
        Course_Relationship courses = new Course_Relationship();
        courses.setID_Course((String) cbCourse_Infor.getSelectedItem());
        courses.setID_Class((String) cbClassCourse.getSelectedItem());
        courses.setID_Teacher((String) cbTeacherCourse.getSelectedItem());
        courses.setID_Student((String) cbStudentCourse.getSelectedItem());
        return courses;
    }

    private void addCourseRelationship() {
        Course_Relationship course = getFormCourse_Relationship();
        if (course_Relationship_DAO.checkID(course) == true) {
            Message.alert(this, "This course code already has matching information !");
            return;
        }
        try {
            course_Relationship_DAO.insert(course);
            this.fillTableCourseInformation();
            this.resetFormCourseInformation();
            Message.alert(this, "More course information successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCourseRelationship() {
        Course_Relationship course = getFormCourse_Relationship();
        if (course_Relationship_DAO.checkID(course) == false) {
            Message.alert(this, "Information for this course is not available! !");
            return;
        }
        try {
            course_Relationship_DAO.deleteCourse_Relationship(course);
            this.fillTableCourseInformation();
            this.resetFormCourseInformation();
            Message.alert(this, "Successfully deleted course information !");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void initTableClass() {
        String columns[] = {"No.", "ID Class", "Class Name", "ID Teacher", "Current Students", "Quantity", "Note"};
        tableModelClass.setColumnIdentifiers(columns);
        tableClass.setModel(tableModelClass);
    }

    private void fillTableClass() {
        tableModelClass.setRowCount(0);
        try {
            String keyword = txtFindClass.getText();
            List<Class> list = classDAO.selectByKeyword(keyword);
            int i = 1;
            for (Class getClass : list) {
                Object[] row = {
                    i++,
                    getClass.getID_Class(), getClass.getClass_Name(), getClass.getID_Teacher(),
                    studentDAO.selectCountStudent(getClass.getID_Class()), getClass.getQuantity(), getClass.getNote()};
                tableModelClass.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setFormClass(Class formClass) {
        txtIDClass.setText(formClass.getID_Class());
        txtClassName.setText(formClass.getClass_Name());
        cbClassTeacher.setSelectedItem(formClass.getID_Teacher());
        txtQuantity.setText(String.valueOf(formClass.getQuantity()));
        txtNoteClass.setText(formClass.getNote());
        txtCurrentStudents.setText(String.valueOf(studentDAO.selectCountStudent(formClass.getID_Class())));
    }

    private void clickTableClass() {
        int index = tableClass.getSelectedRow();
        String ID = tableClass.getValueAt(index, 1).toString();
        Class formClass = classDAO.selectById(ID);
        this.setFormClass(formClass);
    }

    private Class getFormClass() {
        Class getClass = new Class();
        getClass.setID_Class(txtIDClass.getText());
        getClass.setClass_Name(txtClassName.getText());
        getClass.setID_Teacher((String) cbClassTeacher.getSelectedItem());
        getClass.setQuantity(Integer.parseInt(txtQuantity.getText()));
        getClass.setNote(txtNoteClass.getText());
        return getClass;
    }

    private void resetFormClass() {
        JTextComponent textComponent[] = {txtIDClass, txtClassName,
            txtQuantity, txtNoteClass, txtFindClass, txtCurrentStudents};
        IsValidForm.refreshForm(textComponent);
        cbClassTeacher.setSelectedIndex(0);
        txtIDClass.requestFocus();
        fillTableClass();
        tableClass.setEnabled(true);
    }

    private void addClass() {
        JTextComponent textComponent[] = {txtIDClass, txtClassName,
            txtQuantity};
        if (!IsValidForm.checkNull(textComponent)) {
            return;
        }
        try {
            Integer quantity = Integer.parseInt(txtQuantity.getText());
            if (quantity < 1) {
                Message.alert(this, "The number of students must be larger !");
                txtQuantity.requestFocus();
                return;
            }
        } catch (Exception e) {
            Message.alert(this, "The number of students must be an integer !");
            txtQuantity.requestFocus();
            return;
        }
        Class getClass = getFormClass();
        if (classDAO.checkID(getClass) == true) {
            Message.alert(this, "This class code already has matching information !");
            txtIDClass.requestFocus();
            return;
        }
        try {
            classDAO.insert(getClass);
            this.fillTableClass();
            this.resetFormClass();
            Message.alert(this, "Added new class successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateClass() {
        JTextComponent textComponent[] = {txtIDClass, txtClassName,
            txtQuantity};
        if (!IsValidForm.checkNull(textComponent)) {
            return;
        }
        try {
            Integer quantity = Integer.parseInt(txtQuantity.getText());
            if (quantity < 1) {
                Message.alert(this, "The number of students must be larger !");
                txtQuantity.requestFocus();
                return;
            }
        } catch (Exception e) {
            Message.alert(this, "The number of students must be an integer !");
            txtQuantity.requestFocus();
            return;
        }
        Class getClass = getFormClass();
        if (classDAO.checkID(getClass) == false) {
            Message.alert(this, "There is no such class !");
            txtIDClass.requestFocus();
            return;
        }
        try {
            classDAO.update(getClass);
            this.fillTableClass();
            this.resetFormClass();
            Message.alert(this, "Update class successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTableSubject() {
        String columns[] = {"No.", "ID Subject", "Subject Name", "Note"};
        tableModelSubject.setColumnIdentifiers(columns);
        tableSubject.setModel(tableModelSubject);
    }

    private void resetFormSubject() {
        JTextComponent textComponent[] = {txtIDSubject, txtNameSubject,
            txtNoteSubject, txtFindSubject};
        IsValidForm.refreshForm(textComponent);
        txtIDSubject.requestFocus();
        fillTableSubject();
        tableSubject.setEnabled(true);
    }

    private void fillTableSubject() {
        tableModelSubject.setRowCount(0);
        try {
            String keyword = txtFindSubject.getText();
            List<Subject> list = subjectDAO.selectByKeyword(keyword);
            int i = 1;
            for (Subject subject : list) {
                Object[] row = {
                    i++,
                    subject.getID_Subject(), subject.getSubject_Name(), subject.getNote()};
                tableModelSubject.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setFormSubject(Subject subject) {
        txtIDSubject.setText(subject.getID_Subject());
        txtNameSubject.setText(subject.getSubject_Name());
        txtNoteSubject.setText(subject.getNote());
    }

    private void clickTableSubject() {
        int index = tableSubject.getSelectedRow();
        String ID = tableSubject.getValueAt(index, 1).toString();
        Subject subject = subjectDAO.selectById(ID);
        this.setFormSubject(subject);
    }

    private Subject getFormSubject() {
        Subject subject = new Subject();
        subject.setID_Subject(txtIDSubject.getText());
        subject.setSubject_Name(txtNameSubject.getText());
        subject.setNote(txtNoteSubject.getText());
        return subject;
    }

    private void addSubject() {
        JTextComponent textComponent[] = {txtIDSubject, txtNameSubject};
        if (!IsValidForm.checkNull(textComponent)) {
            return;
        }
        Subject subject = getFormSubject();
        if (subjectDAO.checkID(subject) == true) {
            Message.alert(this, "Subject codes were used !");
            txtIDSubject.requestFocus();
            return;
        }
        try {
            subjectDAO.insert(subject);
            this.fillTableSubject();
            this.resetFormSubject();
            Message.alert(this, "Added new subject successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSubject() {
        JTextComponent textComponent[] = {txtIDSubject, txtNameSubject};
        if (!IsValidForm.checkNull(textComponent)) {
            return;
        }
        Subject subject = getFormSubject();
        if (subjectDAO.checkID(subject) == false) {
            Message.alert(this, "Subject code does not exist! !");
            txtIDSubject.requestFocus();
            return;
        }
        try {
            subjectDAO.update(subject);
            this.fillTableSubject();
            this.resetFormSubject();
            Message.alert(this, "Update subject successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showQRCode() {
        String qrCodeImage = "";
        try {
            String qrCodeText = "https://github.com/BinhHayKhocNhe/Du_An_1.git";
            String filePath = "Du_An_1.jpg";
            File destination = new File("img_QR_Code", filePath);
            filePath = Paths.get(destination.getAbsolutePath()).toString();
            int size = 500;
            String fileType = "jpg";
            File qrFile = new File(filePath);
            QR_Code_Util.createQRImage(qrFile, qrCodeText, size, fileType);
            qrCodeImage = filePath;
            QRCode.setIcon(new ImageIcon(qrCodeImage));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//export excel teacher

    private void chooseDirectoryToSave(Workbook workbook) {
        JFileChooser fileChooser = new JFileChooser();
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                // Đường dẫn được chọn từ hộp thoại chọn file
                String filePath = selectedFile.getAbsolutePath();

                // Thêm phần mở rộng .xls nếu không được tự động thêm
                if (!filePath.toLowerCase().endsWith(".xls")) {
                    filePath += ".xls";
                }

                // Ghi workbook vào file đã chọn
                try (FileOutputStream outFile = new FileOutputStream(filePath)) {
                    workbook.write(outFile);
                }

                workbook.close();

                Message.alert(this, "Export Excel successfully to: " + filePath);
            } catch (IOException ex) {
                ex.printStackTrace();
                Message.alert(this, "Error exporting Excel: " + ex.getMessage());
            }
        }
    }

    private void initTableSchedule() {
        String columns[] = {"ID Course", "ID Teacher", "ID Class", "ID Subject", "ID Student",
            "Course Name", "School Day", "Created Date"};
        tableModelSchedule.setColumnIdentifiers(columns);
        tableSchedule.setModel(tableModelSchedule);
    }

    private void fillTableSchedule() {
        tableModelSchedule.setRowCount(0);
        final String datePattern = "yyyy-MM-dd";
        try {
            String keyword = txtFindSchedule.getText();
            List<Schedule> list = scheduleDAO.selectByKeyword(keyword);
            for (Schedule schedule : list) {
                Object[] row = {
                    schedule.getID_Course(), schedule.getID_Teacher(), schedule.getID_Class(), schedule.getID_Subject(),
                    schedule.getID_Student(), schedule.getCourseName(), schedule.getSchoolDay(), XDate.toString(schedule.getScheduleDate(), datePattern)};
                tableModelSchedule.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void fillComboboxSchedule() {
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), teacher_DAO.selectAllIDTeacher(), cbScheduleTeacher);
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), classDAO.selectAllIDClass(), cbScheduleClass);
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), studentDAO.selectAllIDStudent(), cbScheduleStudent);
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), coursesDAO.selectAllIDCourses(), cbScheduleCourse);
        final String courseName[] = {"Spring", "Summer", "Fall"};
        IsValidForm.fillComboBox(new DefaultComboBoxModel(courseName), Arrays.asList(courseName), cbScheduleCourseName);
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), subjectDAO.selectAllIDSubject(), cbScheduleSubject);
    }

    private void resetFormSchedule() {
        JTextComponent textComponent[] = {txtScheduleSchoolDay, txtScheduleCreatedDate, txtScheduleNote, txtFindSchedule};
        IsValidForm.refreshForm(textComponent);
        JComboBox comboBox[] = {cbScheduleCourse, cbScheduleTeacher, cbScheduleClass,
            cbScheduleSubject, cbScheduleStudent, cbScheduleCourseName};
        for (JComboBox jComboBox : comboBox) {
            jComboBox.setSelectedIndex(0);
        }
        fillTableSchedule();
        tableSchedule.setEnabled(true);
    }

    private void setFormSchedule(Schedule schedule) {
        final String datePattern = "yyyy-MM-dd";
        txtScheduleSchoolDay.setText(XDate.toString(schedule.getSchoolDay(), datePattern));
        txtScheduleCreatedDate.setText(String.valueOf(schedule.getScheduleDate()));
        cbScheduleCourse.setSelectedItem(schedule.getID_Course());
        cbScheduleTeacher.setSelectedItem(schedule.getID_Teacher());
        cbScheduleClass.setSelectedItem(schedule.getID_Class());
        cbScheduleSubject.setSelectedItem(schedule.getID_Subject());
        cbScheduleStudent.setSelectedItem(schedule.getID_Student());
        cbScheduleCourseName.setSelectedItem(schedule.getCourseName());
        txtScheduleNote.setText(schedule.getNote());
    }

    private void clickTableSchedule() {
        int index = tableSchedule.getSelectedRow();
        String ID_Course = tableSchedule.getValueAt(index, 0).toString();
        String ID_Teacher = tableSchedule.getValueAt(index, 1).toString();
        String ID_Class = tableSchedule.getValueAt(index, 2).toString();
        String ID_Subject = tableSchedule.getValueAt(index, 3).toString();
        String ID_Student = tableSchedule.getValueAt(index, 4).toString();
        String courseName = tableSchedule.getValueAt(index, 5).toString();
        String schoolDate = tableSchedule.getValueAt(index, 6).toString();
        Schedule schedule = scheduleDAO.selectByIdSchedule(ID_Course, ID_Teacher, ID_Student,
                ID_Class, ID_Subject, courseName, schoolDate);
        this.setFormSchedule(schedule);
    }

    private Schedule getFormSchedule() {
        Schedule schedule = new Schedule();
        schedule.setID_Course((String) cbScheduleCourse.getSelectedItem());
        schedule.setID_Teacher((String) cbScheduleTeacher.getSelectedItem());
        schedule.setID_Class((String) cbScheduleClass.getSelectedItem());
        schedule.setID_Subject((String) cbScheduleSubject.getSelectedItem());
        schedule.setID_Student((String) cbScheduleStudent.getSelectedItem());
        schedule.setCourseName((String) cbScheduleCourseName.getSelectedItem());

        final String datePattern = "yyyy-MM-dd";
        Date schoolDay = XDate.toDate(txtScheduleSchoolDay.getText(), datePattern);
        schedule.setSchoolDay(schoolDay);
        schedule.setNote(txtScheduleNote.getText());

        final String dateTimePattern = "yyyy-MM-dd HH:mm:ss";
        Date createDay = XDate.toDate(txtScheduleCreatedDate.getText(), dateTimePattern);
        schedule.setScheduleDate(createDay);
        return schedule;
    }

    private void addSchedule() {
        if (!IsValidForm.checkNull(txtScheduleSchoolDay)) {
            return;
        } else if (IsValidForm.isValidDate(txtScheduleSchoolDay.getText()) == false) {
            return;
        }
        Schedule schedule = getFormSchedule();
        try {
            scheduleDAO.insert(schedule);
            this.fillTableSchedule();
            this.resetFormSchedule();
            Message.alert(this, "Added new schedule successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSchedule() {
        if (!IsValidForm.checkNull(txtScheduleSchoolDay)) {
            return;
        }
        Date scheduleSchoolDay = XDate.toDate(txtScheduleSchoolDay.getText(), "yyyy-MM-dd");
        if (IsValidForm.canUpdateSchedule(scheduleSchoolDay) == false) {
            Message.alert(this, "The information cannot be edited because the current date has passed !");
            return;
        }
        Schedule schedule = getFormSchedule();
        if (scheduleDAO.checkID(schedule) == false) {
            Message.alert(this, "This class schedule is not available !");
            return;
        }

        try {
            scheduleDAO.update(schedule);
            this.fillTableSchedule();
            this.resetFormSchedule();
            Message.alert(this, "Update schedule successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteSchedule() {
        if (!IsValidForm.checkNull(txtScheduleSchoolDay)) {
            return;
        }
        Date scheduleSchoolDay = XDate.toDate(txtScheduleSchoolDay.getText(), "yyyy-MM-dd");
        if (IsValidForm.canUpdateSchedule(scheduleSchoolDay) == false) {
            Message.alert(this, "The information cannot be edited because the current date has passed !");
            return;
        }
        Schedule schedule = getFormSchedule();
        if (scheduleDAO.checkID(schedule) == false) {
            Message.alert(this, "This class schedule is not available !");
            return;
        }

        try {
            scheduleDAO.deleteSchedule(schedule);
            this.fillTableSchedule();
            this.resetFormSchedule();
            Message.alert(this, "Delete schedule successfully !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillComboboxPoint() {
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), classDAO.selectAllIDClass(), cbIDClassPoint);
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), studentDAO.selectAllIDStudent(), cbIDStudentPoint);
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), subjectDAO.selectAllIDSubject(), cbIDSubjectPoint);
        final String courseName[] = {"Spring", "Summer", "Fall"};
        IsValidForm.fillComboBox(new DefaultComboBoxModel(courseName), Arrays.asList(courseName), cbCourseNamePoint);

        final int currentYear = Year.now().getValue();
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel<>();
        for (int i = currentYear; i >= 2015; i--) {
            comboBoxModel.addElement(i);
        }
        cbYearPoint.setModel(comboBoxModel);
    }

    private void initTablePoint() {
        String columns[] = {"ID Student", "ID Class", "ID Subject", "Year", "Course Name", "ID Teacher", "Point", "Note"};
        tableModelPoint.setColumnIdentifiers(columns);
        tablePoint.setModel(tableModelPoint);
    }

    private void fillTablePoint() {
        tableModelPoint.setRowCount(0);
        try {
            String keyword = txtFindPoint.getText();
            List<Point> list = pointDAO.selectByKeyword(keyword);
            for (Point point : list) {
                Object[] row = {
                    point.getID_Student(), point.getID_Class(), point.getID_Subject(), point.getYear(),
                    point.getCourse_Name(), point.getID_Teacher(), point.getPoint(), point.getNote()};
                tableModelPoint.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resetFormPoint() {
        JTextComponent textComponent[] = {txtPoint, txtIDTeacherPoint, txtFindPoint, txtNotePoint};
        IsValidForm.refreshForm(textComponent);
        JComboBox comboBox[] = {cbIDStudentPoint, cbIDSubjectPoint, cbIDClassPoint, cbYearPoint, cbCourseNamePoint};
        for (JComboBox jComboBox : comboBox) {
            jComboBox.setSelectedIndex(0);
        }
        fillTablePoint();
    }

    private void setFormPoint(Point point) {
        cbIDStudentPoint.setSelectedItem(point.getID_Student());
        cbIDSubjectPoint.setSelectedItem(point.getID_Subject());
        cbIDClassPoint.setSelectedItem(point.getID_Class());
        cbYearPoint.setSelectedItem(point.getYear());
        cbCourseNamePoint.setSelectedItem(point.getCourse_Name());
        txtPoint.setText(String.valueOf(point.getPoint()));
        txtIDTeacherPoint.setText(point.getID_Teacher());
        txtNotePoint.setText(point.getNote());
    }

    private Point getFormPoint() {
        Point point = new Point();



        point.setID_Student((String) cbIDStudentPoint.getSelectedItem());
        point.setID_Subject((String) cbIDSubjectPoint.getSelectedItem());
        point.setID_Class((String) cbIDClassPoint.getSelectedItem());
        point.setYear((Integer) cbYearPoint.getSelectedItem());
        point.setCourse_Name((String) cbCourseNamePoint.getSelectedItem());
        point.setPoint(Float.valueOf(txtPoint.getText()));
        point.setID_Teacher(txtIDTeacherPoint.getText());
        point.setNote(txtNotePoint.getText());

        return point;
    }

    private void clickTablePoint() {
        int index = tablePoint.getSelectedRow();
        String ID_Student = tablePoint.getValueAt(index, 0).toString();
        String ID_Class = tablePoint.getValueAt(index, 1).toString();
        String ID_Subject = tablePoint.getValueAt(index, 2).toString();
        String year = tablePoint.getValueAt(index, 3).toString();
        String courseName = tablePoint.getValueAt(index, 4).toString();
        String ID_Teacher = tablePoint.getValueAt(index, 5).toString();
        Point point = pointDAO.selectByIdPoint(ID_Student, ID_Class, ID_Subject, ID_Teacher, year, courseName);
        this.setFormPoint(point);
    }

    private void addPoint() {
        if (txtPoint.getText().isEmpty()) {
            Message.alert(this, "Please enter complete information !");
            txtPoint.requestFocus();
            return;
        }
        Point entity = getFormPoint();
        if (pointDAO.checkID(entity) == true) {
            Message.alert(this, "The information already exists, new data cannot be added !");
            return;
        }
        try {
            float point = Float.parseFloat(txtPoint.getText());
            if (point < 0 || point > 10) {
                Message.alert(this, "Point must be from 0 to 10 !");
                return;
            }
        } catch (Exception e) {
            Message.alert(this, "Points must be numbers !");
            return;
        }
        try {
            pointDAO.insert(entity);
            this.fillTablePoint();
            this.resetFormPoint();
            Message.alert(this, "Added point successfully !");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void updatePoint() {
        if (txtPoint.getText().isEmpty()) {
            Message.alert(this, "Please enter complete information !");
            txtPoint.requestFocus();
            return;
        }
        Point entity = getFormPoint();
        if (pointDAO.checkID(entity) == false) {
            Message.alert(this, "This information is not available !");
            return;
        }
        try {
            float point = Float.parseFloat(txtPoint.getText());
            if (point < 0 || point > 10) {
                Message.alert(this, "Point must be from 0 to 10 !");
                return;
            }
        } catch (Exception e) {
            Message.alert(this, "Points must be numbers !");
            return;
        }
        try {
            pointDAO.update(entity);
            this.fillTablePoint();
            this.resetFormPoint();
            Message.alert(this, "Added point successfully !");
        } catch (Exception e) {
            e.getMessage();
        }
    }

    private void initTableBonus() {
        String columns[] = {"ID Bonus", "Year", "Course Name", "Level", "ID Student",
            "GPA"};
        tableModelBonus.setColumnIdentifiers(columns);
        tableBonus.setModel(tableModelBonus);
    }

    private void fillTableBonus() {
        tableModelBonus.setRowCount(0);
        try {
            String keyword = txtFindBonus.getText();
            List<Bonus> list = bonusDAO.selectByKeyword(keyword);
            for (Bonus bonus : list) {
                Object[] row = {bonus.getID_Bonus(), bonus.getYear(), bonus.getCourse_Name(), bonus.getLevel(),
                    bonus.getID_Student(), bonus.getGPA()};
                tableModelBonus.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void fillComboboxBonus() {
        IsValidForm.fillComboBox(new DefaultComboBoxModel(), studentDAO.selectAllIDStudent(), cbStudentBonus);
        final String courseName[] = {"Spring", "Summer", "Fall"};
        IsValidForm.fillComboBox(new DefaultComboBoxModel(courseName), Arrays.asList(courseName), cbBonusName);

        final int currentYear = Year.now().getValue();
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel<>();
        for (int i = currentYear; i >= 2015; i--) {
            comboBoxModel.addElement(i);
        }
        cbYearBonus.setModel(comboBoxModel);
        final String level[] = {"Good", "Medium", "Excellent"};
        IsValidForm.fillComboBox(new DefaultComboBoxModel(level), Arrays.asList(level), cblevelBnous);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_Admin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_Admin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_Admin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_Admin.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_Admin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel QRCode;
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnAddClass;
    private javax.swing.JButton btnAddCourse;
    private javax.swing.JButton btnAddCourse_Relationship;
    private javax.swing.JButton btnAddParent;
    private javax.swing.JButton btnAddPoint;
    private javax.swing.JButton btnAddSchedule;
    private javax.swing.JButton btnAddStaff;
    private javax.swing.JButton btnAddStudent;
    private javax.swing.JButton btnAddTeacher;
    private javax.swing.JButton btnDeleteCourse_Relationship;
    private javax.swing.JButton btnDeleteRelationship;
    private javax.swing.JButton btnDeleteSchedule;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnExitResetPassParent;
    private javax.swing.JButton btnExitResetPassStaff;
    private javax.swing.JButton btnExitResetPassTeacher;
    private javax.swing.JButton btnExportPoint;
    private javax.swing.JButton btnExportTeacher;
    private javax.swing.JButton btnImportPoint;
    private javax.swing.JButton btnImportTeacher;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnResetFormCourse;
    private javax.swing.JButton btnResetFormCourseInformation;
    private javax.swing.JButton btnResetFormParent;
    private javax.swing.JButton btnResetFormReletionship;
    private javax.swing.JButton btnResetFormStaff;
    private javax.swing.JButton btnResetFormStudent;
    private javax.swing.JButton btnResetFormTeacher;
    private javax.swing.JButton btnResetPassParent;
    private javax.swing.JButton btnResetPassStaff;
    private javax.swing.JButton btnResetPassTeacher;
    private javax.swing.JButton btnResetPoint;
    private javax.swing.JButton btnResetSubject;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton btnUpdateClass;
    private javax.swing.JButton btnUpdateParent;
    private javax.swing.JButton btnUpdatePoint;
    private javax.swing.JButton btnUpdateSchedule;
    private javax.swing.JButton btnUpdateStaff;
    private javax.swing.JButton btnUpdateStudent;
    private javax.swing.JButton btnUpdateTeacher;
    private javax.swing.JButton btnUpdateTeacher1;
    private javax.swing.JButton btnUploadImgStaff;
    private javax.swing.JButton btnUploadImgStudent;
    private javax.swing.JButton btnUploadImgTeacher;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.ButtonGroup buttonGroup8;
    private javax.swing.JPanel cardDoimk;
    private javax.swing.JPanel cardInformation;
    private javax.swing.JPanel cardLearningManagement;
    private javax.swing.JPanel cardListParent;
    private javax.swing.JPanel cardListStaff;
    private javax.swing.JPanel cardListStudent;
    private javax.swing.JPanel cardListTeacher;
    private javax.swing.JPanel cardQRCode;
    private javax.swing.JPanel cardTrangChu;
    private javax.swing.JComboBox<String> cbBonusName;
    private javax.swing.JComboBox<String> cbClassCourse;
    private javax.swing.JComboBox<String> cbClassTeacher;
    private javax.swing.JComboBox<String> cbCourse;
    private javax.swing.JComboBox<String> cbCourseNamePoint;
    private javax.swing.JComboBox<String> cbCourse_Infor;
    private javax.swing.JComboBox<String> cbDateStaff;
    private javax.swing.JComboBox<String> cbDateStudent;
    private javax.swing.JComboBox<String> cbDateTeacher;
    private javax.swing.JComboBox<String> cbIDClassPoint;
    private javax.swing.JComboBox<String> cbIDStudentPoint;
    private javax.swing.JComboBox<String> cbIDSubjectPoint;
    private javax.swing.JComboBox<String> cbJobParent;
    private javax.swing.JComboBox<String> cbLevelTeacher;
    private javax.swing.JComboBox<String> cbMonthStaff;
    private javax.swing.JComboBox<String> cbMonthStudent;
    private javax.swing.JComboBox<String> cbMonthTeacher;
    private javax.swing.JComboBox<String> cbPositionStaff;
    private javax.swing.JComboBox<String> cbScheduleClass;
    private javax.swing.JComboBox<String> cbScheduleCourse;
    private javax.swing.JComboBox<String> cbScheduleCourseName;
    private javax.swing.JComboBox<String> cbScheduleStudent;
    private javax.swing.JComboBox<String> cbScheduleSubject;
    private javax.swing.JComboBox<String> cbScheduleTeacher;
    private javax.swing.JComboBox<String> cbStudentBonus;
    private javax.swing.JComboBox<String> cbStudentCourse;
    private javax.swing.JComboBox<String> cbTeacherCourse;
    private javax.swing.JComboBox<String> cbYearBonus;
    private javax.swing.JComboBox<String> cbYearPoint;
    private javax.swing.JComboBox<String> cbYearStaff;
    private javax.swing.JComboBox<String> cbYearStudent;
    private javax.swing.JComboBox<String> cbYearTeacher;
    private javax.swing.JComboBox<String> cblevelBnous;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane10;
    private javax.swing.JLayeredPane jLayeredPane11;
    private javax.swing.JLayeredPane jLayeredPane12;
    private javax.swing.JLayeredPane jLayeredPane13;
    private javax.swing.JLayeredPane jLayeredPane14;
    private javax.swing.JLayeredPane jLayeredPane15;
    private javax.swing.JLayeredPane jLayeredPane16;
    private javax.swing.JLayeredPane jLayeredPane17;
    private javax.swing.JLayeredPane jLayeredPane18;
    private javax.swing.JLayeredPane jLayeredPane19;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JLayeredPane jLayeredPane20;
    private javax.swing.JLayeredPane jLayeredPane21;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JLayeredPane jLayeredPane4;
    private javax.swing.JLayeredPane jLayeredPane5;
    private javax.swing.JLayeredPane jLayeredPane6;
    private javax.swing.JLayeredPane jLayeredPane7;
    private javax.swing.JLayeredPane jLayeredPane8;
    private javax.swing.JLayeredPane jLayeredPane9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane26;
    private javax.swing.JScrollPane jScrollPane27;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel jplMain;
    private javax.swing.JPanel jplSlideMenu;
    private javax.swing.JPanel jplTitle;
    private javax.swing.JPanel jpllMenuBar;
    private javax.swing.JLabel lbAvatarStaff;
    private javax.swing.JLabel lbAvatarStudent;
    private javax.swing.JLabel lbAvatarTeacher;
    private javax.swing.JLabel lbGuardians;
    private javax.swing.JLabel lbNameAdmin;
    private javax.swing.JLabel lbQRCode;
    private javax.swing.JLabel lbShowCurrentPass;
    private javax.swing.JLabel lbShowEnterPass;
    private javax.swing.JLabel lbShowNewPass;
    private javax.swing.JLabel lbStaff;
    private javax.swing.JLabel lbStudent;
    private javax.swing.JLabel lbTeacher;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lblCloseMenu;
    private javax.swing.JLabel lblOpenMenu;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JLabel lblthoat;
    private javax.swing.JRadioButton rdFemale;
    private javax.swing.JRadioButton rdFemaleParent;
    private javax.swing.JRadioButton rdFemaleStaff;
    private javax.swing.JRadioButton rdFemaleStudent;
    private javax.swing.JRadioButton rdFemaleTeacher;
    private javax.swing.JRadioButton rdMale;
    private javax.swing.JRadioButton rdMaleParent;
    private javax.swing.JRadioButton rdMaleStaff;
    private javax.swing.JRadioButton rdMaleStudent;
    private javax.swing.JRadioButton rdMaleTeacher;
    private javax.swing.JRadioButton rdOFFStaff;
    private javax.swing.JRadioButton rdOFFStudent;
    private javax.swing.JRadioButton rdOFFTeacher;
    private javax.swing.JRadioButton rdONStaff;
    private javax.swing.JRadioButton rdONStudent;
    private javax.swing.JRadioButton rdONTeacher;
    private javax.swing.JTabbedPane tabAccount;
    private javax.swing.JTabbedPane tabCourse;
    private javax.swing.JTabbedPane tabParent;
    private javax.swing.JTabbedPane tabStaff;
    private javax.swing.JTabbedPane tabStudent;
    private javax.swing.JTabbedPane tabTeacher;
    private javax.swing.JTable tableBonus;
    private javax.swing.JTable tableClass;
    private javax.swing.JTable tableCourse;
    private javax.swing.JTable tableCourse_Relationship;
    private javax.swing.JTable tableParent;
    private javax.swing.JTable tablePoint;
    private javax.swing.JTable tableRelationship;
    private javax.swing.JTable tableResetPassParent;
    private javax.swing.JTable tableResetPassStaff;
    private javax.swing.JTable tableResetPassTeacher;
    private javax.swing.JTable tableSchedule;
    private javax.swing.JTable tableStaff;
    private javax.swing.JTable tableStudent;
    private javax.swing.JTable tableSubject;
    private javax.swing.JTable tableTeacher;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAddressParent;
    private javax.swing.JTextField txtAddressStaff;
    private javax.swing.JTextField txtAddressStudent;
    private javax.swing.JTextField txtAddressTeacher;
    private javax.swing.JTextField txtClassName;
    private javax.swing.JTextField txtClassStudent;
    private javax.swing.JPasswordField txtCurrentPass;
    private javax.swing.JTextField txtCurrentStudents;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmailParent;
    private javax.swing.JTextField txtEmailStaff;
    private javax.swing.JTextField txtEmailTeacher;
    private javax.swing.JPasswordField txtEnterPass;
    private javax.swing.JTextField txtFindBonus;
    private javax.swing.JTextField txtFindClass;
    private javax.swing.JTextField txtFindCourse;
    private javax.swing.JTextField txtFindCourseInformation;
    private javax.swing.JTextField txtFindParent;
    private javax.swing.JTextField txtFindPoint;
    private javax.swing.JTextField txtFindRelationship;
    private javax.swing.JTextField txtFindSchedule;
    private javax.swing.JTextField txtFindStaff;
    private javax.swing.JTextField txtFindStudent;
    private javax.swing.JTextField txtFindSubject;
    private javax.swing.JTextField txtFindTeacher;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtFirstNameParent;
    private javax.swing.JTextField txtFirstNameStaff;
    private javax.swing.JTextField txtFirstNameStudent;
    private javax.swing.JTextField txtFirstNameTeacher;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDClass;
    private javax.swing.JTextField txtIDCourse;
    private javax.swing.JTextField txtIDParent;
    private javax.swing.JTextField txtIDResetPassParent;
    private javax.swing.JTextField txtIDResetPassStaff;
    private javax.swing.JTextField txtIDResetPassTeacher;
    private javax.swing.JTextField txtIDStaff;
    private javax.swing.JTextField txtIDStudent;
    private javax.swing.JTextField txtIDSubject;
    private javax.swing.JTextField txtIDTeacher;
    private javax.swing.JTextField txtIDTeacherPoint;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtLastNameParent;
    private javax.swing.JTextField txtLastNameStaff;
    private javax.swing.JTextField txtLastNameStudent;
    private javax.swing.JTextField txtLastNameTeacher;
    private javax.swing.JTextField txtMidName;
    private javax.swing.JTextField txtMidNameParent;
    private javax.swing.JTextField txtMidNameStaff;
    private javax.swing.JTextField txtMidNameStudent;
    private javax.swing.JTextField txtMidNameTeacher;
    private javax.swing.JTextField txtNameSubject;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JTextArea txtNote;
    private javax.swing.JTextArea txtNoteClass;
    private javax.swing.JTextArea txtNoteCourse;
    private javax.swing.JTextArea txtNoteParent;
    private javax.swing.JTextArea txtNotePoint;
    private javax.swing.JTextArea txtNoteStaff;
    private javax.swing.JTextArea txtNoteStudent;
    private javax.swing.JTextArea txtNoteSubject;
    private javax.swing.JTextArea txtNoteTeacher;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtPhoneParent;
    private javax.swing.JTextField txtPhoneStaff;
    private javax.swing.JTextField txtPhoneTeacher;
    private javax.swing.JTextField txtPoint;
    private javax.swing.JTextField txtQuantity;
    private javax.swing.JTextField txtRelationshipGuadians;
    private javax.swing.JTextField txtRelationshipStudent;
    private javax.swing.JTextField txtScheduleCreatedDate;
    private javax.swing.JTextArea txtScheduleNote;
    private javax.swing.JTextField txtScheduleSchoolDay;
    private javax.swing.JTextField txtStartDateStaff;
    private javax.swing.JTextField txtStartDateTeacher;
    private javax.swing.JTextField txtYearCourse;
    // End of variables declaration//GEN-END:variables
}
