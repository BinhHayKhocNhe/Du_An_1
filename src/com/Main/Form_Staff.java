/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Main;

import com.DAO.ClassDAO;
import com.DAO.CoursesDAO;
import com.DAO.HealthDAO;
import com.DAO.ScheduleStaffDAO;
import com.DAO.StaffDAO;
import com.DAO.Staff_SalaryDAO;
import com.DAO.StudentDAO;
import com.Entity.Staff;
import com.Entity.Staff_Salary;
import com.Entity.Student;
import com.Entity.Class;
import com.Entity.Health;
import com.Entity.Schedule_Staff;
import com.Utils.Authentication;
import com.Utils.IsValidForm;
import com.Utils.Message;
import com.Utils.Time;
import com.Utils.XImage;
import com.microsoft.sqlserver.jdbc.StringUtils;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author User
 */
public class Form_Staff extends javax.swing.JFrame {

    int x = 210;    //chieu rong
    int y = 700;    //chieu cao
    private JFileChooser fileChooser = new JFileChooser();
    private String pathOfSelectedImage = null;
    StaffDAO daoStaff = new StaffDAO();
    StudentDAO daostu = new StudentDAO();
    ClassDAO classDAO = new ClassDAO();
    Staff_SalaryDAO salaryDAO = new Staff_SalaryDAO();
    HealthDAO healthDAO = new HealthDAO();
    CoursesDAO coursesDAO = new CoursesDAO();
    ScheduleStaffDAO scheduleStaffDAO = new ScheduleStaffDAO();

    /**
     * Creates new form Menu
     */
    public Form_Staff() {

        initComponents();
//        formLogin();

        this.setIconImage(XImage.getAppIcon());
        this.setTitle("Group 5 - Viet Duc School");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        uploadCombobox();
        fillTable();
        setTableStudent();
        InforStaff();
        uploadcboIDClass();
        setRdo();
        setMonthSalary();
        tableSalary();
        loadTableHealth();
        loadTableIDStudent();
        loadCboIDCourseANDcboIDStaff();
        loadTblSchedule();
    }

    private void CardFalse() {
        cardHome.setVisible(false);
        cardStaff_Salary.setVisible(false);
        cardInfor.setVisible(false);
        cardHealth.setVisible(false);
        cardStaff.setVisible(false);
        cardAddStudent.setVisible(false);
        cardhelp.setVisible(false);
        cardHome1.setVisible(false);
        closeMenu();
    }

    private void setBackGround() {
        lblHome.setOpaque(false);
        lblAcc.setOpaque(false);
        lblSta_Li.setOpaque(false);
        lblStudent.setOpaque(false);
        lblSche.setOpaque(false);
        lblHealth.setOpaque(false);
        lblSalary.setOpaque(false);

    }

    private void setTimeSlide() {
        if (x == 0) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 0; i <= 210; i++) {
                            jplSlideMenu.setSize(i, y);
                            Thread.sleep(1, 5);
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
            x = 210;
        }
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
                            Thread.sleep(1, 5);
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
        if (x == 210) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        for (int i = 210; i >= 0; i--) {
                            jplSlideMenu.setSize(i, y);
                            Thread.sleep(1, 5);
                        }
                    } catch (Exception e) {
                    }
                }
            }).start();
            x = 0;
        }
    }
//Chọn file

    private void chooseImageStaff() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            pathOfSelectedImage = file.getAbsolutePath();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lblAvatar.setIcon(icon);
            lblAvatar.setToolTipText(file.getName());
        }
    }

//  up load combo box
    private void uploadCombobox() {
        DefaultComboBoxModel cbYearModel = new DefaultComboBoxModel();
        DefaultComboBoxModel cbMonthModel = new DefaultComboBoxModel();
        DefaultComboBoxModel cbYearModelSutdent = new DefaultComboBoxModel();
        for (int i = 1980; i < 2024; i++) {
            cbYearModel.addElement(i);
        }
        for (int i = 1; i <= 12; i++) {
            cbMonthModel.addElement(i);
        }
//        set Year cho student
        int currentYear = LocalDate.now().getYear();
        int sixYearAgo = currentYear - 6;
        for (int i = sixYearAgo; i <= currentYear; i++) {
            cbYearModelSutdent.addElement(i);
        }
        cboYear.setModel(cbYearModel);
        cboYear1.setModel(cbYearModel);
        cboMonth.setModel(cbMonthModel);
        cboMonth1.setModel(cbMonthModel);
        cboYear2.setModel(cbYearModelSutdent);
        cboMonth2.setModel(cbMonthModel);
        updateDays();
        cboMonth.addActionListener((e) -> {
            updateDays();
        });
        cboMonth1.addActionListener((e) -> {
            updateDays();
        });
        cboMonth2.addActionListener((e) -> {
            updateDays();
        });
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
        DefaultComboBoxModel cbDayModel1 = new DefaultComboBoxModel<>();
        DefaultComboBoxModel cbDayModel2 = new DefaultComboBoxModel<>();
        int selectedMonth = (Integer) cboMonth.getSelectedItem();
        int selectedMonth1 = (Integer) cboMonth1.getSelectedItem();
        int selectedYear = (Integer) cboYear.getSelectedItem();
        int selectedYear1 = (Integer) cboYear1.getSelectedItem();
        int selectedMonth2 = (Integer) cboMonth2.getSelectedItem();
        int selectedYear2 = (Integer) cboYear2.getSelectedItem();
        int daysInMonth = getDaysInMonth(selectedMonth, selectedYear);
        int daysInMonth1 = getDaysInMonth(selectedMonth1, selectedYear1);
        int daysInMonth2 = getDaysInMonth(selectedMonth2, selectedYear2);
        cboDay.removeAllItems();
        for (int i = 1; i <= daysInMonth; i++) {
            cbDayModel.addElement(i);
        }
        cboDay.setModel(cbDayModel);
        cboDay1.removeAllItems();
        for (int i = 1; i <= daysInMonth1; i++) {
            cbDayModel1.addElement(i);
        }
        cboDay1.setModel(cbDayModel);
        cboDay2.removeAllItems();
        for (int i = 1; i <= daysInMonth1; i++) {
            cbDayModel1.addElement(i);
        }
        cboDay2.setModel(cbDayModel);
    }
// Xóa trắng

    private void ClearForm() {
        txtId_Staff.setText("");
        txtF_Name.setText("");
        txtM_Name.setText("");
        txtL_Name.setText("");
        txtEmail.setText("");
        txtNote.setText("");
        txtP_Num.setText("");
        txtAddress.setText("");
        rdoFemale.setSelected(false);
        rdoOff.setSelected(false);
        lblAvatar.setText("");
        txtPosition.setText("");
        txtStartDay.setText("");
        cboDay.setSelectedIndex(0);
        cboMonth.setSelectedIndex(0);
        cboYear.setSelectedIndex(0);

    }
// đổ dữ liệu lên table

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblStaff.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtFind.getText();
            List<Staff> list = daoStaff.selectByKeyword(keyword);
            for (Staff sta : list) {
                Object[] row = {
                    sta.getID_Staff(),
                    sta.getFirst_Name() + " " + sta.getMiddle_Name() + " " + sta.getLast_Name(),
                    sta.getEmail(),
                    sta.isGender() ? "Male" : "Female",
                    sta.isStatus_Staff() ? "On" : "Off",
                    Time.toString(sta.getStart_Date(), "MM/dd/yyyy"),
                    sta.getPosition(),
                    sta.getNote(),};
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            Message.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
// Hiện lên form

    private void ClickTable() {
        int row = tblStaff.getSelectedRow();
        String ID_Staff = (String) tblStaff.getValueAt(row, 0);
        Staff sta = daoStaff.selectById(ID_Staff);
        this.setForm(sta);
        INFORMATION.setSelectedIndex(0);
    }

    private void setForm(Staff sta) {
        txtId_Staff.setText(sta.getID_Staff());
        txtF_Name.setText(sta.getFirst_Name());
        txtEmail.setText(sta.getEmail());
        (sta.isGender() ? rdoMale : rdoFemale).setSelected(true);
        (sta.isStatus_Staff() ? rdoOn : rdoOff).setSelected(true);
        cboYear.setSelectedItem(sta.getYear_Of_Birth());
        cboMonth.setSelectedItem(sta.getMonth_Of_Birth());
        cboDay.setSelectedItem(sta.getDate_Of_Birth());
        txtStartDay.setText(String.valueOf(sta.getStart_Date()));
        txtNote.setText(sta.getNote());
        txtP_Num.setText(sta.getPhone_Number());
        txtAddress.setText(sta.getAddress_Staff());
        txtM_Name.setText(sta.getMiddle_Name());
        txtL_Name.setText(sta.getLast_Name());
        lblAvatar.setIcon(XImage.read(sta.getAvatar()));
        txtPosition.setText(sta.getPosition());

    }

// Lấy thông tin Staff
    private void setFormIforn(Staff sta) {
        txtId_Staff1.setText(sta.getID_Staff());
        txtF_Name1.setText(sta.getFirst_Name());
        txtEmail1.setText(sta.getEmail());
        (sta.isGender() ? rdoMale1 : rdoFemale1).setSelected(true);
        (sta.isStatus_Staff() ? rdoOn1 : rdoOff1).setSelected(true);
        cboYear1.setSelectedItem(sta.getYear_Of_Birth());
        cboMonth1.setSelectedItem(sta.getMonth_Of_Birth());
        cboDay1.setSelectedItem(sta.getDate_Of_Birth());
        txtStartDay1.setText(String.valueOf(sta.getStart_Date()));
        txtNote1.setText(sta.getNote());
        txtP_Num1.setText(sta.getPhone_Number());
        txtAddress1.setText(sta.getAddress_Staff());
        txtM_Name1.setText(sta.getMiddle_Name());
        txtL_Name1.setText(sta.getLast_Name());
        txtPosition1.setText(sta.getPosition());
        ImageIcon icon = XImage.read(sta.getAvatar());
        Image originalImage = icon.getImage();
        int width1 = lblAvartarBig.getWidth();
        int height1 = lblAvartarBig.getHeight();
        int width = lblAvatar1.getWidth();
        int height = lblAvatar1.getHeight();
        Image scaledImage1 = originalImage.getScaledInstance(width1, height1, Image.SCALE_SMOOTH);
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        lblAvartarBig.setIcon(scaledIcon1);
        lblAvatar1.setIcon(scaledIcon);
        lblNameBig.setText(sta.getFirst_Name() + " " + sta.getMiddle_Name() + " " + sta.getLast_Name());
        Message.alert(this, "Hello " + sta.getFirst_Name() + " " + sta.getMiddle_Name() + " " + sta.getLast_Name());
    }

    private void InforStaff() {
        Staff staff = daoStaff.selectById(Authentication.staff.getID_Staff());
        setFormIforn(staff);
    }
// change pass

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
        jTabbedPane1.setSelectedIndex(0);
    }

    private void changePassword() {
        daoStaff.changePassword(Authentication.staff.getID_Staff(),
                String.valueOf(txtCurrentPass.getPassword()), String.valueOf(txtEnterPass.getPassword()));
        this.resetFormChangePassWord();
    }

// set Studen table
    private void setTableStudent() {
        DefaultTableModel model = (DefaultTableModel) tblList_stu.getModel();
        model.setRowCount(0);
        try {
            String kyeword = txtFind_Stu.getText();
            List<Student> stu = daostu.selectByKeyword(kyeword);
            for (Student student : stu) {
                Object row[] = {
                    student.getID_Student(),
                    student.getFirst_Name() + " " + student.getMiddle_Name() + " " + student.getLast_Name(),
                    student.getID_Class(),
                    student.isGender() ? "Male" : "Female",
                    student.isStatus_Student() ? "On" : "Off",
                    student.getDate_Of_Birth() + "/" + student.getMonth_Of_Birth() + "/" + student.getYear_Of_Birth(),
                    student.getNote()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            Message.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
//Chọn ảnh học sinh

    private void chooseImageStudent() {
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            pathOfSelectedImage = file.getAbsolutePath();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lblAvatar2.setIcon(icon);
            lblAvatar2.setToolTipText(file.getName());
        }
    }
// Lấy infor học sinh

    private Student getFormStudent() {
        Student student = new Student();
        student.setID_Student(txtID_Student.getText());
        student.setFirst_Name(txtF_Name_Student.getText());
        student.setMiddle_Name(txtM_Name_Student.getText());
        student.setLast_Name(txtL_Name_Student.getText());
        student.setAddress_Student(txtAdd_Student.getText());
        student.setAvatar(lblAvatar2.getToolTipText());
        student.setDate_Of_Birth((int) cboDay2.getSelectedItem());
        student.setMonth_Of_Birth((int) cboMonth2.getSelectedItem());
        student.setYear_Of_Birth((int) cboYear2.getSelectedItem());
        student.setNote(txtNote_Student.getText());
        student.setGender(rdoMale_Student.isSelected() ? true : false);
        student.setStatus_Student(rdoStatus_Student_On.isSelected() ? true : false);
        student.setID_Class((String) cboID_Class_Student.getSelectedItem());
        return student;
    }

    private void setButtonOffice() {
        String position = daoStaff.ReturnPosition(Authentication.staff.getID_Staff());
        if (!position.equals("Office")) {
            Message.alert(this, "you don't have access");
        } else {
            CardFalse();
            lbTitle.setText("Viet Duc School - Student");
            setBackGround();
            lblStudent.setOpaque(true);
            lblStudent.setBackground(Color.getHSBColor((float) 0.16, (float) 0.6, (float) 0.9));

            cardAddStudent.setVisible(true);
        }
    }

    private void addStudent() {
        JTextComponent text[] = {txtID_Student, txtF_Name_Student, txtNote_Student,
            txtL_Name_Student, txtAdd_Student};
        IsValidForm.checkNull(text);

        // Kiểm tra ToolTipText không rỗng
        if (StringUtils.isEmpty(lblAvatar2.getToolTipText())) {
            Message.alert(this, "Please choose a representative photo!");
            return;
        }

        Student student = getFormStudent();

//         Kiểm tra trùng lặp ID sinh viên
//        if (!daostu.checkCountIDStudent(txtID_Student.getText())) {
//            Message.alert(this, "The student code is already in use!");
//            return;
//        }
        int count = daostu.selectCountStudent(txtID_Student.getText());
        int Quantity = classDAO.selectQuantity((String) cboID_Class_Student.getSelectedItem());
        if (count >= Quantity) {
            Message.alert(this, "The number of students in this class is sufficient !");
            txtID_Student.requestFocus();
            return;
        }
        try {
            // Thêm sinh viên vào cơ sở dữ liệu
            daostu.insert(student);
            // Hiển thị thông báo thành công trên luồng giao diện người dùng chính
            SwingUtilities.invokeLater(() -> {
                Message.alert(this, "Added Student Successfully!");
                autoID();
            });
        } catch (Exception e) {
            // Xử lý ngoại lệ chung
            e.printStackTrace();
        }
    }
    // set Combo Box ID class cho form student

    private void uploadcboIDClass() {
        DefaultComboBoxModel cbo = new DefaultComboBoxModel<>();
        List<Class> IDClass_Student = classDAO.selectAll();
        for (Class class1 : IDClass_Student) {
            cbo.addElement(class1.getID_Class());
        }
        cboID_Class_Student.setModel(cbo);
    }
// Click table hiện student lên form

    private void ClicktableStudent() {
        int row = tblList_stu.getSelectedRow();
        String ID_student = (String) tblList_stu.getValueAt(row, 0);
        Student student = daostu.selectById(ID_student);
        setFormStudent(student);
        INFORSTUDENT.setSelectedIndex(0);
    }

    private void setFormStudent(Student stu) {
        txtID_Student.setText(stu.getID_Student());
        txtF_Name_Student.setText(stu.getFirst_Name());
        txtM_Name_Student.setText(stu.getMiddle_Name());
        txtL_Name_Student.setText(stu.getLast_Name());
        txtAdd_Student.setText(stu.getAddress_Student());
        (stu.isGender() ? rdoMale_Student : rdoFemale_Student).setSelected(true);
        (stu.isStatus_Student() ? rdoStatus_Student_On : rdoStatus_Student_Off).setSelected(true);
        lblAvatar2.setIcon(XImage.read(stu.getAvatar()));
        cboDay2.setSelectedItem(stu.getDate_Of_Birth());
        cboMonth2.setSelectedItem(stu.getMonth_Of_Birth());
        cboYear2.setSelectedItem(stu.getYear_Of_Birth());
        cboID_Class_Student.setSelectedItem(stu.getID_Class());
        txtNote_Student.setText(stu.getNote());

    }
// setSelected(True) cho các rdos

    private void setRdo() {
        rdoMale.setSelected(true);
        rdoMale1.setSelected(true);
        rdoMale_Student.setSelected(true);
        rdoOn.setSelected(true);
        rdoOn1.setSelected(true);
        rdoStatus_Student_On.setSelected(true);
        rdoGood.setSelected(true);
    }
// Tăng tự động id học sinh

    private String autoID_Student(String input) {
        // Kiểm tra xem input có định dạng "STU" + số không
        if (input.matches("STU\\d+")) {
            // Lấy phần số, cộng 1, và ghép lại với "STU"
            int number = Integer.parseInt(input.replaceAll("\\D", ""));
            number++;
            return "STU" + String.format("%03d", number);
        } else {
            // Trả về input không thay đổi nếu không phải định dạng mong đợi
            return input;
        }
    }

    private void autoID() {
        String resetFormStudent = daostu.returnLastID();
        String newID_Stuent = autoID_Student(resetFormStudent);
        txtID_Student.setText(newID_Stuent);
        refreshFormStudent();
    }

// refresh form student
    private void refreshFormStudent() {
        JTextComponent text[] = {txtNote_Student, txtAdd_Student, txtL_Name_Student, txtM_Name_Student, txtF_Name_Student};
        IsValidForm.refreshForm(text);
        lblAvatar2.setText(null);
        rdoMale_Student.setSelected(true);
        rdoStatus_Student_On.setSelected(true);
    }
// salary staff

    private void tableSalary() {
        if (Authentication.staff != null) {
            String staffID = Authentication.staff.getID_Staff();
            try {
// Đảm bảo selectedItem không phải là null
                Object selectedItem = cboMonthSalary.getSelectedIndex();

                // Chuyển đổi selectedItem thành String
                String monthString = selectedItem.toString();
                int month = Integer.parseInt(monthString);
                if (staffID != null && !staffID.isEmpty()) {
                    List<Staff_Salary> salaryStaff;
                    // Kiểm tra xem month có giá trị hợp lệ không
                    if (month == 0) {
                        salaryStaff = salaryDAO.returnAllSalaryByID(staffID);
                    } else {
                        salaryStaff = salaryDAO.returnAllSalary(staffID, month);
                    }
                    DefaultTableModel model = (DefaultTableModel) tblSalary_Student.getModel();
                    model.setRowCount(0);
                    // Tiếp tục với logic xử lý dữ liệu
                    for (Staff_Salary salary : salaryStaff) {
                        Object row[] = {
                            salary.getID_Staff_Salary(),
                            salary.getNumber_Of_Working_Days(),
                            salary.getDaily_Wage(),
                            salary.getMonth(),
                            salary.getYear(),
                            salary.getNumber_Of_Working_Days() * salary.getDaily_Wage(),
                            salary.getNote()
                        };
                        model.addRow(row);
                    }
                } else {
                    Message.alert(this, "Mã nhân viên không hợp lệ hoặc trống.");
                }

            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
                Message.alert(this, "Định dạng tháng không hợp lệ: " + numberFormatException.getMessage());
            }
        } else {
            Message.alert(this, "Authentication.staff là null.");
        }
    }

// setComboBox Tháng Lương
    private void setMonthSalary() {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        model.addElement("All");
        for (int i = 1; i <= 12; i++) {
            model.addElement(String.valueOf(i));
        }
        cboMonthSalary.setModel(model);
    }

// Health
    private void setPositionDoctor() {
        String position = daoStaff.ReturnPosition(Authentication.staff.getID_Staff());
        if (!position.equals("Doctor")) {
            Message.alert(this, "you don't have access");
        } else {
            CardFalse();
            lbTitle.setText("Viet Duc School - Health");
            setBackGround();
            lblHealth.setOpaque(true);
            lblHealth.setBackground(Color.getHSBColor((float) 0.16, (float) 0.6, (float) 0.9));
            cardHealth.setVisible(true);
        }
    }
// load table Health

    private void loadTableHealth() {
        DefaultTableModel model = (DefaultTableModel) tblHealth.getModel();
        model.setRowCount(0);
        try {
            List<Health> health = healthDAO.selectAll();
            for (Health health1 : health) {
                Object row[] = {
                    health1.getID_Course(),
                    health1.getID_Student(),
                    health1.getID_Staff(),
                    health1.getHeight(),
                    health1.getWeight(),
                    health1.isStatus() ? "Good" : "Bad",
                    health1.getNote()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            Message.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }
// load table ID student

    private void loadTableIDStudent() {
        DefaultTableModel model = (DefaultTableModel) tblIDStudent.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtIDStudentHealth.getText();
            List<String> idStudent = daostu.getidlStudentByID(keyword);
            for (String idStu : idStudent) {
                Object rowObject[] = {
                    idStu
                };
                model.addRow(rowObject);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            Message.alert(this, "Lỗi truy vấn dữ liệu!");
        }
    }

// load cbo ID Course
    private void loadCboIDCourseANDcboIDStaff() {
        DefaultComboBoxModel modelCourse = (DefaultComboBoxModel) cboIDCourse.getModel();
        DefaultComboBoxModel modelStaff = (DefaultComboBoxModel) cboIDStaffDoctor.getModel();
        modelCourse.removeAllElements();
        modelStaff.removeAllElements();
        List<String> cboCourse = coursesDAO.returntListCouseName();
        List<String> cboStaff = daoStaff.returntListIDstaff();
        modelCourse.addElement("Choose");
        modelStaff.addElement("Choose");
        for (String string : cboCourse) {
            modelCourse.addElement(string);
        }
        for (String string1 : cboStaff) {
            modelStaff.addElement(string1);
        }
        cboIDStaffDoctor.setSelectedItem(Authentication.staff.getID_Staff());
        cboIDStaffDoctor.setEnabled(false);
    }
// save health

    private Health getFormHealth() {
        Health health = new Health();
        health.setID_Course((String) cboIDCourse.getSelectedItem());
        health.setCourse_Name(txtCourseName.getText());
        health.setID_Staff((String) cboIDStaffDoctor.getSelectedItem());
        health.setID_Student(txtIDStudentHealth.getText());
        health.setStatus(rdoGood.isSelected());

        // Thực hiện kiểm tra trước khi chuyển đổi từ chuỗi sang số
        try {
            health.setHeight(Integer.parseInt(txtHeight.getText()));
            health.setWeight(Integer.parseInt(txtWeight.getText()));
        } catch (NumberFormatException e) {
            // Xử lý khi có lỗi chuyển đổi
            e.printStackTrace();
            Message.alert(this, "Please enter valid numbers for Height and Weight.");
            return null; // Trả về null để biểu thị rằng có lỗi xảy ra
        }

        health.setNote(txtaNote.getText());
        return health;
    }

    private void addHealth() {
        JTextComponent text[] = {txtCourseName, txtIDStudentHealth, txtHeight, txtWeight};

        // Kiểm tra null và kiểm tra chuỗi rỗng
        IsValidForm.checkNull(text);

        try {
            // Kiểm tra định dạng số cho chiều cao và cân nặng
            if (!txtHeight.getText().trim().isEmpty() && !txtWeight.getText().trim().isEmpty()) {
                int height = Integer.parseInt(txtHeight.getText());
                int weight = Integer.parseInt(txtWeight.getText());

                // Kiểm tra giá trị hợp lệ cho chiều cao và cân nặng
                if (height < 70) {
                    Message.alert(this, "Please fill in the correct number of Centimeters!");
                    return;
                }

                if (weight < 10) {
                    Message.alert(this, "Please fill in the correct number of Kilograms!");
                    return;
                }
            } else {
                Message.alert(this, "Please enter valid numbers for Height and Weight.");
                return;
            }

            // Tạo đối tượng Health từ các trường nhập liệu
            Health health = getFormHealth();

            // Kiểm tra null trước khi thêm vào cơ sở dữ liệu
            if (health != null) {
                healthDAO.insert(health);
                this.loadTableHealth();
                Message.alert(this, "Health added successfully!");
            }
        } catch (NumberFormatException e) {
            // Xử lý ngoại lệ nếu không thể chuyển đổi thành số
            e.printStackTrace();
            Message.alert(this, "Please enter valid numbers for Height and Weight.");
        } catch (Exception e) {
            // Xử lý ngoại lệ chung
            e.printStackTrace();
            Message.alert(this, "An error occurred while adding health information.");
        }
    }

// Load Table Schedule
    private void loadTblSchedule() {

        DefaultTableModel model = (DefaultTableModel) tblSchedule.getModel();
        model.setRowCount(0);
        String ID = Authentication.staff.getID_Staff();
        try {
            List<Schedule_Staff> ssdao = scheduleStaffDAO.selectSchedule(ID);
            for (Schedule_Staff schedule_Staff : ssdao) {
                Object row[] = {
                    schedule_Staff.getWork_Date(),
                    schedule_Staff.getDay_Of_Week(),
                    schedule_Staff.getStart_Time(),
                    schedule_Staff.getEnd_Time()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            e.getMessage();
        }
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
        jPanel1 = new javax.swing.JPanel();
        jplSlideMenu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblAvartarBig = new javax.swing.JLabel();
        lblNameBig = new javax.swing.JLabel();
        lblCloseMenu = new javax.swing.JLabel();
        lblHome = new javax.swing.JLabel();
        lblAcc = new javax.swing.JLabel();
        lblSta_Li = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblLog_out = new javax.swing.JLabel();
        lblHelp = new javax.swing.JLabel();
        lblStudent = new javax.swing.JLabel();
        lblHealth = new javax.swing.JLabel();
        lblSche = new javax.swing.JLabel();
        lblSalary = new javax.swing.JLabel();
        jpllMenuBar = new javax.swing.JPanel();
        lblOpenMenu = new javax.swing.JLabel();
        jplTitle = new javax.swing.JPanel();
        lbTitle = new javax.swing.JLabel();
        jplMain = new javax.swing.JPanel();
        cardHome1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        cardHome = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        cardInfor = new javax.swing.JPanel();
        INFORMATION = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        rdoMale = new javax.swing.JRadioButton();
        rdoFemale = new javax.swing.JRadioButton();
        txtId_Staff = new javax.swing.JTextField();
        txtF_Name = new javax.swing.JTextField();
        txtM_Name = new javax.swing.JTextField();
        txtL_Name = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtP_Num = new javax.swing.JTextField();
        txtAddress = new javax.swing.JTextField();
        btnNew = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cboYear = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cboMonth = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        cboDay = new javax.swing.JComboBox<>();
        lblAvatar = new javax.swing.JLabel();
        btnUpload = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtPosition = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtStartDay = new javax.swing.JTextField();
        rdoOn = new javax.swing.JRadioButton();
        rdoOff = new javax.swing.JRadioButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStaff = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        cardStaff = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        rdoMale1 = new javax.swing.JRadioButton();
        rdoFemale1 = new javax.swing.JRadioButton();
        txtId_Staff1 = new javax.swing.JTextField();
        txtF_Name1 = new javax.swing.JTextField();
        txtM_Name1 = new javax.swing.JTextField();
        txtL_Name1 = new javax.swing.JTextField();
        txtEmail1 = new javax.swing.JTextField();
        txtP_Num1 = new javax.swing.JTextField();
        txtAddress1 = new javax.swing.JTextField();
        btnEdit1 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        cboYear1 = new javax.swing.JComboBox<>();
        jLabel38 = new javax.swing.JLabel();
        cboMonth1 = new javax.swing.JComboBox<>();
        jLabel39 = new javax.swing.JLabel();
        cboDay1 = new javax.swing.JComboBox<>();
        lblAvatar1 = new javax.swing.JLabel();
        btnUpload1 = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        txtPosition1 = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        txtStartDay1 = new javax.swing.JTextField();
        rdoOn1 = new javax.swing.JRadioButton();
        rdoOff1 = new javax.swing.JRadioButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtNote1 = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        txtCurrentPass = new javax.swing.JPasswordField();
        txtNewPass = new javax.swing.JPasswordField();
        txtEnterPass = new javax.swing.JPasswordField();
        btnAccept = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lbShowCurrentPass = new javax.swing.JLabel();
        lbShowEnterPass = new javax.swing.JLabel();
        lbShowNewPass = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        cardAddStudent = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        INFORSTUDENT = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        txtID_Student = new javax.swing.JTextField();
        jLabel47 = new javax.swing.JLabel();
        txtF_Name_Student = new javax.swing.JTextField();
        jLabel48 = new javax.swing.JLabel();
        txtM_Name_Student = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        txtL_Name_Student = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        txtAdd_Student = new javax.swing.JTextField();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        rdoMale_Student = new javax.swing.JRadioButton();
        rdoFemale_Student = new javax.swing.JRadioButton();
        jLabel54 = new javax.swing.JLabel();
        cboYear2 = new javax.swing.JComboBox<>();
        jLabel55 = new javax.swing.JLabel();
        cboMonth2 = new javax.swing.JComboBox<>();
        jLabel56 = new javax.swing.JLabel();
        cboDay2 = new javax.swing.JComboBox<>();
        lblAvatar2 = new javax.swing.JLabel();
        btnUpload2 = new javax.swing.JButton();
        btnNew2 = new javax.swing.JButton();
        btnAdd2 = new javax.swing.JButton();
        rdoStatus_Student_On = new javax.swing.JRadioButton();
        rdoStatus_Student_Off = new javax.swing.JRadioButton();
        cboID_Class_Student = new javax.swing.JComboBox<>();
        jLabel57 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        txtNote_Student = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblList_stu = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        txtFind_Stu = new javax.swing.JTextField();
        cardHealth = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        cboIDCourse = new javax.swing.JComboBox<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        tblIDStudent = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        txtIDStudentHealth = new javax.swing.JTextField();
        jScrollPane9 = new javax.swing.JScrollPane();
        txtaNote = new javax.swing.JTextArea();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        cboIDStaffDoctor = new javax.swing.JComboBox<>();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblHealth = new javax.swing.JTable();
        jLabel64 = new javax.swing.JLabel();
        txtCourseName = new javax.swing.JTextField();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txtHeight = new javax.swing.JTextField();
        txtWeight = new javax.swing.JTextField();
        rdoGood = new javax.swing.JRadioButton();
        rdoBab = new javax.swing.JRadioButton();
        jLabel67 = new javax.swing.JLabel();
        btnSaveHealth = new javax.swing.JButton();
        jLabel68 = new javax.swing.JLabel();
        cardSchedule = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jSeparator9 = new javax.swing.JSeparator();
        jScrollPane11 = new javax.swing.JScrollPane();
        tblSchedule = new javax.swing.JTable();
        cardhelp = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        lblAVT = new javax.swing.JLabel();
        cardStaff_Salary = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblSalary_Student = new javax.swing.JTable();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        cboMonthSalary = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 700));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jplSlideMenu.setBackground(new java.awt.Color(255, 255, 255));
        jplSlideMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jplSlideMenu.setPreferredSize(new java.awt.Dimension(210, 700));
        jplSlideMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblAvartarBig.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAvartarBig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/User.png"))); // NOI18N
        lblAvartarBig.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lblNameBig.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblNameBig.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNameBig.setText("Staff");
        lblNameBig.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lblCloseMenu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        lblCloseMenu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCloseMenu.setText("X");
        lblCloseMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblCloseMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblCloseMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblCloseMenuMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addComponent(lblAvartarBig, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblCloseMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addComponent(lblNameBig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCloseMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblAvartarBig, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblNameBig)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jplSlideMenu.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 150));

        lblHome.setBackground(new java.awt.Color(255, 255, 255));
        lblHome.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHome.setText("HOME");
        lblHome.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHomeMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHomeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHomeMouseExited(evt);
            }
        });
        jplSlideMenu.add(lblHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 150, 120, 30));

        lblAcc.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblAcc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAcc.setText("ACCOUNT");
        lblAcc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAccMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblAccMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblAccMouseExited(evt);
            }
        });
        lblAcc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lblAccKeyPressed(evt);
            }
        });
        jplSlideMenu.add(lblAcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 200, 120, 30));

        lblSta_Li.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSta_Li.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSta_Li.setText("STAFF");
        lblSta_Li.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSta_LiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSta_LiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSta_LiMouseExited(evt);
            }
        });
        jplSlideMenu.add(lblSta_Li, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 250, 120, 30));
        jplSlideMenu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 210, 10));

        lblLog_out.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblLog_out.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLog_out.setText("LOG OUT");
        lblLog_out.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLog_outMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLog_outMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLog_outMouseExited(evt);
            }
        });
        jplSlideMenu.add(lblLog_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 120, 30));

        lblHelp.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblHelp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHelp.setText("HELP");
        lblHelp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHelpMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHelpMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHelpMouseExited(evt);
            }
        });
        jplSlideMenu.add(lblHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 120, 30));

        lblStudent.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblStudent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblStudent.setText("STUDENT");
        lblStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblStudentMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblStudentMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblStudentMouseExited(evt);
            }
        });
        jplSlideMenu.add(lblStudent, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 300, 120, 30));

        lblHealth.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblHealth.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHealth.setText("HEALTH");
        lblHealth.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHealthMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblHealthMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblHealthMouseExited(evt);
            }
        });
        jplSlideMenu.add(lblHealth, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 450, 120, 30));

        lblSche.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSche.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSche.setText("SCHEDULE");
        lblSche.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblScheMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblScheMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblScheMouseExited(evt);
            }
        });
        jplSlideMenu.add(lblSche, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 350, 120, 30));

        lblSalary.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblSalary.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSalary.setText("SALARY");
        lblSalary.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblSalaryMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblSalaryMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblSalaryMouseExited(evt);
            }
        });
        jplSlideMenu.add(lblSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 400, 120, 30));

        jPanel1.add(jplSlideMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 0, 600));

        jpllMenuBar.setBackground(new java.awt.Color(255, 255, 255));

        lblOpenMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/menu.png"))); // NOI18N
        lblOpenMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblOpenMenuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblOpenMenuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblOpenMenuMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jpllMenuBarLayout = new javax.swing.GroupLayout(jpllMenuBar);
        jpllMenuBar.setLayout(jpllMenuBarLayout);
        jpllMenuBarLayout.setHorizontalGroup(
            jpllMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpllMenuBarLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lblOpenMenu)
                .addContainerGap(886, Short.MAX_VALUE))
        );
        jpllMenuBarLayout.setVerticalGroup(
            jpllMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpllMenuBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpenMenu)
                .addContainerGap())
        );

        jPanel1.add(jpllMenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 940, -1));

        jplTitle.setBackground(new java.awt.Color(0, 168, 255));

        lbTitle.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Viet Duc School");

        javax.swing.GroupLayout jplTitleLayout = new javax.swing.GroupLayout(jplTitle);
        jplTitle.setLayout(jplTitleLayout);
        jplTitleLayout.setHorizontalGroup(
            jplTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 1100, Short.MAX_VALUE)
        );
        jplTitleLayout.setVerticalGroup(
            jplTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jplTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jplTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1100, 30));

        jplMain.setBackground(new java.awt.Color(255, 255, 255));
        jplMain.setPreferredSize(new java.awt.Dimension(1100, 550));
        jplMain.setLayout(new java.awt.CardLayout());

        cardHome1.setBackground(new java.awt.Color(255, 255, 255));
        cardHome1.setPreferredSize(new java.awt.Dimension(1100, 540));
        cardHome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardHome1MouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/education.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("INTRODUCE");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(204, 204, 204));
        jTextPane1.setText("Congratulations on successfully logging in! \nYou have officially become a part of our academic community. Get ready for dynamic learning journeys, where new knowledge awaits, and personal development takes center stage.\nDive into the exciting classes, participate in activities, and share your thoughts. We believe each step you take will bring you closer to great achievements.\nCheers to starting your new learning journey with excitement!\n\nEnvironmental requirements:\n1. Any operating system\n2. JDK 1.8 or higher\n3. SQL Server 2008 or later");
        jScrollPane3.setViewportView(jTextPane1);

        javax.swing.GroupLayout cardHome1Layout = new javax.swing.GroupLayout(cardHome1);
        cardHome1.setLayout(cardHome1Layout);
        cardHome1Layout.setHorizontalGroup(
            cardHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHome1Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardHome1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(cardHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(432, 432, 432))
        );
        cardHome1Layout.setVerticalGroup(
            cardHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHome1Layout.createSequentialGroup()
                .addGroup(cardHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardHome1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cardHome1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(123, Short.MAX_VALUE))
        );

        jplMain.add(cardHome1, "card3");

        cardHome.setBackground(new java.awt.Color(255, 255, 255));
        cardHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardHomeMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setText("HOME");

        jSeparator4.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator4.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout cardHomeLayout = new javax.swing.GroupLayout(cardHome);
        cardHome.setLayout(cardHomeLayout);
        cardHomeLayout.setHorizontalGroup(
            cardHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHomeLayout.createSequentialGroup()
                .addGap(520, 520, 520)
                .addGroup(cardHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator4))
                .addContainerGap(505, Short.MAX_VALUE))
        );
        cardHomeLayout.setVerticalGroup(
            cardHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHomeLayout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(0, 0, 0)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(509, Short.MAX_VALUE))
        );

        jplMain.add(cardHome, "card3");

        cardInfor.setBackground(new java.awt.Color(255, 255, 255));
        cardInfor.setPreferredSize(new java.awt.Dimension(1100, 540));

        INFORMATION.setBackground(new java.awt.Color(255, 255, 255));
        INFORMATION.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        INFORMATION.setPreferredSize(new java.awt.Dimension(600, 414));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setText("ID Staff:");

        jLabel13.setText("First Name:");

        jLabel14.setText("Middle Name:");

        jLabel15.setText("Last Name:");

        jLabel16.setText("Email:");

        jLabel17.setText("Phone Number:");

        jLabel18.setText("Address:");

        jLabel19.setText("Gender:");

        buttonGroup1.add(rdoMale);
        rdoMale.setText("Male");
        rdoMale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMaleActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoFemale);
        rdoFemale.setText("Female");

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/newadd.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        btnAdd.setText("Add");

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Edit.png"))); // NOI18N
        btnEdit.setText("Edit");

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Trash.png"))); // NOI18N
        btnDelete.setText("Delete");

        jLabel20.setText("Note:");

        jLabel21.setText("Year of birth:");

        cboYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel22.setText("Month of birth:");

        cboMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel23.setText("Day of birth:");

        cboDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblAvatar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Up.png"))); // NOI18N
        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        jLabel24.setText("Position:");

        jLabel25.setText("Status");

        jLabel26.setText("Start Day");

        buttonGroup2.add(rdoOn);
        rdoOn.setText("On");
        rdoOn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoOnActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoOff);
        rdoOff.setText("Off");

        txtNote.setColumns(20);
        txtNote.setRows(5);
        jScrollPane4.setViewportView(txtNote);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtL_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId_Staff, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtF_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtM_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnNew)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEdit)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtP_Num, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(rdoMale, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdoFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStartDay, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(rdoOn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoOff, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnUpload)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId_Staff, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtF_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtP_Num, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtM_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoMale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtL_Name, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(82, 82, 82))
                                    .addComponent(jScrollPane4))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnNew)
                                            .addComponent(btnAdd)))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnDelete)
                                            .addComponent(btnEdit)))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoOn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoOff, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStartDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(lblAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpload)))
                .addGap(36, 36, 36))
        );

        INFORMATION.addTab("INFORMATON", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        tblStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Full Name", "Email", "Gender", "Status Staff", "Start Day", "Position", "Note"
            }
        ));
        tblStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStaffMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblStaff);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Find ID or Name");

        txtFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFindActionPerformed(evt);
            }
        });
        txtFind.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFindKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE))
        );

        INFORMATION.addTab("STAFF LIST", jPanel4);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("STAFF");

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout cardInforLayout = new javax.swing.GroupLayout(cardInfor);
        cardInfor.setLayout(cardInforLayout);
        cardInforLayout.setHorizontalGroup(
            cardInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardInforLayout.createSequentialGroup()
                .addGap(520, 520, 520)
                .addGroup(cardInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardInforLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(INFORMATION, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        cardInforLayout.setVerticalGroup(
            cardInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardInforLayout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 0, 0)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(INFORMATION, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jplMain.add(cardInfor, "card3");

        cardStaff.setBackground(new java.awt.Color(255, 255, 255));
        cardStaff.setPreferredSize(new java.awt.Dimension(1100, 540));
        cardStaff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardStaffMouseClicked(evt);
            }
        });

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(600, 414));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel28.setText("ID Staff:");

        jLabel29.setText("First Name:");

        jLabel30.setText("Middle Name:");

        jLabel31.setText("Last Name:");

        jLabel32.setText("Email:");

        jLabel33.setText("Phone Number:");

        jLabel34.setText("Address:");

        jLabel35.setText("Gender:");

        buttonGroup3.add(rdoMale1);
        rdoMale1.setText("Male");
        rdoMale1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMale1ActionPerformed(evt);
            }
        });

        buttonGroup3.add(rdoFemale1);
        rdoFemale1.setText("Female");

        txtId_Staff1.setEnabled(false);
        txtId_Staff1.setOpaque(true);

        btnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Edit.png"))); // NOI18N
        btnEdit1.setText("Edit");

        jLabel36.setText("Note:");

        jLabel37.setText("Year of birth:");

        cboYear1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", " " }));
        cboYear1.setEnabled(false);

        jLabel38.setText("Month of birth:");

        cboMonth1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", " " }));
        cboMonth1.setEnabled(false);

        jLabel39.setText("Day of birth:");

        cboDay1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", " " }));
        cboDay1.setEnabled(false);
        cboDay1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDay1ActionPerformed(evt);
            }
        });

        lblAvatar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnUpload1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Up.png"))); // NOI18N
        btnUpload1.setText("Upload");
        btnUpload1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpload1ActionPerformed(evt);
            }
        });

        jLabel40.setText("Position:");

        jLabel41.setText("Status");

        jLabel42.setText("Start Day");

        txtStartDay1.setEditable(false);

        buttonGroup4.add(rdoOn1);
        rdoOn1.setText("On");
        rdoOn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoOn1ActionPerformed(evt);
            }
        });

        buttonGroup4.add(rdoOff1);
        rdoOff1.setText("Off");

        txtNote1.setColumns(20);
        txtNote1.setRows(5);
        jScrollPane5.setViewportView(txtNote1);

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(cboYear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnEdit1)
                                .addGap(117, 117, 117))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtL_Name1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtId_Staff1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtF_Name1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtM_Name1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtP_Num1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel8Layout.createSequentialGroup()
                                    .addComponent(rdoMale1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rdoFemale1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPosition1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtStartDay1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(rdoOn1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoOff1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cboDay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnUpload1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtId_Staff1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmail1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtF_Name1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtP_Num1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtM_Name1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoMale1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoFemale1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtL_Name1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAddress1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboYear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(82, 82, 82))
                                    .addComponent(jScrollPane5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnEdit1)
                                .addGap(1, 1, 1))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPosition1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoOn1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoOff1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtStartDay1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(lblAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpload1)))
                .addGap(36, 36, 36))
        );

        jTabbedPane1.addTab("INFORMATON", jPanel8);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel43.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel43.setText("Current password:");

        jLabel44.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel44.setText("New password:");

        jLabel45.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel45.setText("Enter the password:");

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

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(197, 197, 197)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel43)
                            .addComponent(jLabel45))
                        .addGap(43, 43, 43)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addComponent(btnAccept)
                                .addGap(18, 18, 18)
                                .addComponent(btnExit))
                            .addComponent(txtNewPass)
                            .addComponent(txtCurrentPass)
                            .addComponent(txtEnterPass, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel44))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbShowCurrentPass)
                    .addComponent(lbShowEnterPass)
                    .addComponent(lbShowNewPass))
                .addContainerGap(245, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43)
                    .addComponent(txtCurrentPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbShowCurrentPass))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel44)
                        .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbShowNewPass))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel45)
                        .addComponent(txtEnterPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbShowEnterPass))
                .addGap(45, 45, 45)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAccept)
                    .addComponent(btnExit))
                .addContainerGap(113, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("CHANGE PASSWORD", jPanel7);

        jLabel27.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel27.setText("INFORMATION");

        jSeparator5.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout cardStaffLayout = new javax.swing.GroupLayout(cardStaff);
        cardStaff.setLayout(cardStaffLayout);
        cardStaffLayout.setHorizontalGroup(
            cardStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardStaffLayout.createSequentialGroup()
                .addGroup(cardStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardStaffLayout.createSequentialGroup()
                        .addGap(520, 520, 520)
                        .addGroup(cardStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator5)))
                    .addGroup(cardStaffLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        cardStaffLayout.setVerticalGroup(
            cardStaffLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardStaffLayout.createSequentialGroup()
                .addComponent(jLabel27)
                .addGap(0, 0, 0)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        jplMain.add(cardStaff, "card3");

        cardAddStudent.setBackground(new java.awt.Color(255, 255, 255));
        cardAddStudent.setPreferredSize(new java.awt.Dimension(1100, 540));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText(" STUDENT");

        INFORSTUDENT.setBackground(new java.awt.Color(255, 255, 255));
        INFORSTUDENT.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(102, 102, 102)));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel46.setText("ID Student:");

        txtID_Student.setEnabled(false);
        txtID_Student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtID_StudentActionPerformed(evt);
            }
        });

        jLabel47.setText("First Name:");

        txtF_Name_Student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtF_Name_StudentActionPerformed(evt);
            }
        });

        jLabel48.setText("Middle Name:");

        txtM_Name_Student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtM_Name_StudentActionPerformed(evt);
            }
        });

        jLabel49.setText("Last Name");

        txtL_Name_Student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtL_Name_StudentActionPerformed(evt);
            }
        });

        jLabel50.setText("Address Student");

        txtAdd_Student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAdd_StudentActionPerformed(evt);
            }
        });

        jLabel51.setText("Note:");

        jLabel52.setText("Status Student:");

        jLabel53.setText("Gender:");

        buttonGroup6.add(rdoMale_Student);
        rdoMale_Student.setText("Male");
        rdoMale_Student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMale_StudentActionPerformed(evt);
            }
        });

        buttonGroup6.add(rdoFemale_Student);
        rdoFemale_Student.setText("Female");
        rdoFemale_Student.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoFemale_StudentActionPerformed(evt);
            }
        });

        jLabel54.setText("Year of birth:");

        cboYear2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", " " }));

        jLabel55.setText("Month of birth:");

        cboMonth2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", " " }));

        jLabel56.setText("Day of birth:");

        cboDay2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", " " }));
        cboDay2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboDay2ActionPerformed(evt);
            }
        });

        lblAvatar2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnUpload2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Up.png"))); // NOI18N
        btnUpload2.setText("Upload");
        btnUpload2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpload2ActionPerformed(evt);
            }
        });

        btnNew2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/newadd.png"))); // NOI18N
        btnNew2.setText("New");
        btnNew2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNew2ActionPerformed(evt);
            }
        });

        btnAdd2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        btnAdd2.setText("Add");
        btnAdd2.setEnabled(false);
        btnAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdd2ActionPerformed(evt);
            }
        });

        buttonGroup5.add(rdoStatus_Student_On);
        rdoStatus_Student_On.setText("On");
        rdoStatus_Student_On.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoStatus_Student_OnActionPerformed(evt);
            }
        });

        buttonGroup5.add(rdoStatus_Student_Off);
        rdoStatus_Student_Off.setText("Off");
        rdoStatus_Student_Off.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoStatus_Student_OffActionPerformed(evt);
            }
        });

        cboID_Class_Student.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel57.setText("ID Class");

        txtNote_Student.setColumns(20);
        txtNote_Student.setRows(5);
        jScrollPane6.setViewportView(txtNote_Student);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtID_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtF_Name_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtM_Name_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtL_Name_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAdd_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel54, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel57, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoMale_Student, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoStatus_Student_On, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rdoStatus_Student_Off, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rdoFemale_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(cboMonth2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboYear2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboID_Class_Student, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(btnUpload2)
                                .addGap(85, 85, 85))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addComponent(lblAvatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(btnNew2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd2)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lblAvatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnUpload2)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoStatus_Student_On, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoStatus_Student_Off, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtF_Name_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoMale_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdoFemale_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtM_Name_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboYear2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtL_Name_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboMonth2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAdd_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboDay2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboID_Class_Student, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(74, 74, 74)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAdd2)
                                    .addComponent(btnNew2)))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        INFORSTUDENT.addTab("INFORMATION STUDENT", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        tblList_stu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Student", "Full Name", "ID Class", "Gender", "Status", "Birth Day", "Note"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblList_stu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblList_stuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblList_stu);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("ID or Nam Student");

        txtFind_Stu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFind_StuActionPerformed(evt);
            }
        });
        txtFind_Stu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFind_StuKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFind_Stu, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFind_Stu, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        INFORSTUDENT.addTab("LIST STUDENT", jPanel6);

        javax.swing.GroupLayout cardAddStudentLayout = new javax.swing.GroupLayout(cardAddStudent);
        cardAddStudent.setLayout(cardAddStudentLayout);
        cardAddStudentLayout.setHorizontalGroup(
            cardAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardAddStudentLayout.createSequentialGroup()
                .addGap(520, 520, 520)
                .addGroup(cardAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator6))
                .addGap(375, 375, 375))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardAddStudentLayout.createSequentialGroup()
                .addGap(215, 215, 215)
                .addComponent(INFORSTUDENT, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        cardAddStudentLayout.setVerticalGroup(
            cardAddStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardAddStudentLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, 0)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(INFORSTUDENT, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );

        jplMain.add(cardAddStudent, "card3");

        cardHealth.setBackground(new java.awt.Color(255, 255, 255));
        cardHealth.setPreferredSize(new java.awt.Dimension(1100, 540));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setText("HEALTH");

        cboIDCourse.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboIDCourse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboIDCourseActionPerformed(evt);
            }
        });

        tblIDStudent.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "ID Student"
            }
        ));
        tblIDStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblIDStudentMouseClicked(evt);
            }
        });
        jScrollPane8.setViewportView(tblIDStudent);

        jLabel3.setText("Couse Name:");

        jLabel61.setText("ID Student:");

        txtIDStudentHealth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIDStudentHealthKeyReleased(evt);
            }
        });

        txtaNote.setColumns(20);
        txtaNote.setRows(5);
        jScrollPane9.setViewportView(txtaNote);

        jLabel62.setText("Note:");

        jLabel63.setText("ID Staff:");

        cboIDStaffDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tblHealth.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Couse", "ID Student", "ID Staff", "Height", "Weight", "Status", "Note"
            }
        ));
        jScrollPane10.setViewportView(tblHealth);

        jLabel64.setText("ID Course");

        txtCourseName.setEditable(false);

        jLabel65.setText("Height:");

        jLabel66.setText("Weight:");

        buttonGroup7.add(rdoGood);
        rdoGood.setText("Good ");

        buttonGroup7.add(rdoBab);
        rdoBab.setText("Bab");

        jLabel67.setText("Status:");

        btnSaveHealth.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Accept.png"))); // NOI18N
        btnSaveHealth.setText("Save");
        btnSaveHealth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveHealthActionPerformed(evt);
            }
        });

        jLabel68.setForeground(new java.awt.Color(255, 0, 51));
        jLabel68.setText("Find ID Student Form Table");

        javax.swing.GroupLayout cardHealthLayout = new javax.swing.GroupLayout(cardHealth);
        cardHealth.setLayout(cardHealthLayout);
        cardHealthLayout.setHorizontalGroup(
            cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHealthLayout.createSequentialGroup()
                .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardHealthLayout.createSequentialGroup()
                        .addGap(520, 520, 520)
                        .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator7)))
                    .addGroup(cardHealthLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(cardHealthLayout.createSequentialGroup()
                                .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(cardHealthLayout.createSequentialGroup()
                                        .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(cardHealthLayout.createSequentialGroup()
                                                .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtIDStudentHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(cardHealthLayout.createSequentialGroup()
                                                .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cboIDCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(cardHealthLayout.createSequentialGroup()
                                                .addGap(129, 129, 129)
                                                .addComponent(jLabel66, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardHealthLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel63, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel65, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                    .addComponent(jLabel67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSaveHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(cardHealthLayout.createSequentialGroup()
                                        .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(cardHealthLayout.createSequentialGroup()
                                                .addComponent(cboIDStaffDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(88, 88, 88)
                                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(txtHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(cardHealthLayout.createSequentialGroup()
                                                .addComponent(rdoGood, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rdoBab, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(cardHealthLayout.createSequentialGroup()
                                .addComponent(jLabel68)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(46, 46, 46))
        );
        cardHealthLayout.setVerticalGroup(
            cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHealthLayout.createSequentialGroup()
                .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardHealthLayout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardHealthLayout.createSequentialGroup()
                        .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardHealthLayout.createSequentialGroup()
                                .addGap(59, 59, 59)
                                .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboIDCourse, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboIDStaffDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCourseName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtHeight, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIDStudentHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtWeight, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(cardHealthLayout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, 0)
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardHealthLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(cardHealthLayout.createSequentialGroup()
                                        .addGroup(cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(rdoBab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cardHealthLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(rdoGood)
                                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(51, 51, 51)
                                        .addComponent(btnSaveHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(cardHealthLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addComponent(jLabel68)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );

        jplMain.add(cardHealth, "card3");

        cardSchedule.setBackground(new java.awt.Color(255, 255, 255));
        cardSchedule.setPreferredSize(new java.awt.Dimension(1100, 540));

        jLabel60.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel60.setText("SCHEDULE");

        tblSchedule.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Work Date", "Day Of Week", "Start Time", "End Time"
            }
        ));
        jScrollPane11.setViewportView(tblSchedule);

        javax.swing.GroupLayout cardScheduleLayout = new javax.swing.GroupLayout(cardSchedule);
        cardSchedule.setLayout(cardScheduleLayout);
        cardScheduleLayout.setHorizontalGroup(
            cardScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardScheduleLayout.createSequentialGroup()
                .addGroup(cardScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardScheduleLayout.createSequentialGroup()
                        .addGap(520, 520, 520)
                        .addGroup(cardScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator9)))
                    .addGroup(cardScheduleLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        cardScheduleLayout.setVerticalGroup(
            cardScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardScheduleLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel60)
                .addGap(0, 0, 0)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        jplMain.add(cardSchedule, "card3");

        cardhelp.setBackground(new java.awt.Color(255, 255, 255));
        cardhelp.setPreferredSize(new java.awt.Dimension(1100, 540));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setText("Help");

        lblAVT.setText("lbl");

        javax.swing.GroupLayout cardhelpLayout = new javax.swing.GroupLayout(cardhelp);
        cardhelp.setLayout(cardhelpLayout);
        cardhelpLayout.setHorizontalGroup(
            cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardhelpLayout.createSequentialGroup()
                .addGroup(cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardhelpLayout.createSequentialGroup()
                        .addGap(520, 520, 520)
                        .addComponent(jLabel7))
                    .addGroup(cardhelpLayout.createSequentialGroup()
                        .addGap(369, 369, 369)
                        .addComponent(lblAVT, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(531, Short.MAX_VALUE))
        );
        cardhelpLayout.setVerticalGroup(
            cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardhelpLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel7)
                .addGap(89, 89, 89)
                .addComponent(lblAVT, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(252, Short.MAX_VALUE))
        );

        jplMain.add(cardhelp, "card3");

        cardStaff_Salary.setBackground(new java.awt.Color(255, 255, 255));
        cardStaff_Salary.setPreferredSize(new java.awt.Dimension(1100, 540));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setText("SALARY");

        jTabbedPane2.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(51, 51, 51), new java.awt.Color(153, 153, 153)));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));

        tblSalary_Student.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Salary", "Number_Of_Working_Days", "Daily_Wage", "Month", "Year", "Total", "Note"
            }
        ));
        jScrollPane7.setViewportView(tblSalary_Student);

        jLabel58.setText("Find Month");

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 0, 0));
        jLabel59.setText("Note: History only displays the most recent 12 months");

        cboMonthSalary.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboMonthSalary.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMonthSalaryItemStateChanged(evt);
            }
        });
        cboMonthSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMonthSalaryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane7)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboMonthSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addComponent(jLabel59)
                .addGap(28, 28, 28))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(0, 16, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel58, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(cboMonthSalary))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane2.addTab("HISTORY SALARY", jPanel9);

        javax.swing.GroupLayout cardStaff_SalaryLayout = new javax.swing.GroupLayout(cardStaff_Salary);
        cardStaff_Salary.setLayout(cardStaff_SalaryLayout);
        cardStaff_SalaryLayout.setHorizontalGroup(
            cardStaff_SalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardStaff_SalaryLayout.createSequentialGroup()
                .addGroup(cardStaff_SalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardStaff_SalaryLayout.createSequentialGroup()
                        .addGap(520, 520, 520)
                        .addGroup(cardStaff_SalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator8)))
                    .addGroup(cardStaff_SalaryLayout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 870, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        cardStaff_SalaryLayout.setVerticalGroup(
            cardStaff_SalaryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardStaff_SalaryLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jplMain.add(cardStaff_Salary, "card2");

        jPanel1.add(jplMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblCloseMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMenuMouseClicked
        closeMenu();
    }//GEN-LAST:event_lblCloseMenuMouseClicked

    private void lblOpenMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpenMenuMouseClicked
        openMenu();
    }//GEN-LAST:event_lblOpenMenuMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        x = 0;
        setTimeSlide();
//        jplSlideMenu.setSize(0, y);
//        x = 0;
    }//GEN-LAST:event_formWindowOpened

    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseClicked
        CardFalse();
        lbTitle.setText("Viet Duc School - Home");
        setBackGround();
        lblHome.setOpaque(true);
        lblHome.setBackground(Color.getHSBColor((float) 0.16, (float) 0.6, (float) 0.9));
        cardHome1.setVisible(true);
    }//GEN-LAST:event_lblHomeMouseClicked

    private void lblAccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAccMouseClicked
        CardFalse();
        lbTitle.setText("Viet Duc School - Account");
        setBackGround();
        lblAcc.setOpaque(true);
        lblAcc.setBackground(Color.getHSBColor((float) 0.16, (float) 0.6, (float) 0.9));
        cardStaff.setVisible(true);

    }//GEN-LAST:event_lblAccMouseClicked

    private void lblSta_LiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSta_LiMouseClicked
        CardFalse();
        lbTitle.setText("Viet Duc School - Staff");
        setBackGround();
        lblSta_Li.setOpaque(true);
        lblSta_Li.setBackground(Color.getHSBColor((float) 0.16, (float) 0.6, (float) 0.9));
        cardInfor.setVisible(true);

    }//GEN-LAST:event_lblSta_LiMouseClicked

    private void cardStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardStaffMouseClicked

    }//GEN-LAST:event_cardStaffMouseClicked

    private void lblStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStudentMouseClicked
        setButtonOffice();

    }//GEN-LAST:event_lblStudentMouseClicked

    private void lblHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHelpMouseClicked
        CardFalse();
        cardhelp.setVisible(true);

    }//GEN-LAST:event_lblHelpMouseClicked

    private void lblLog_outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLog_outMouseClicked
        if (Message.confirm(this, "Do you want log out ?")) {
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_lblLog_outMouseClicked

    private void cardHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardHomeMouseClicked

    }//GEN-LAST:event_cardHomeMouseClicked

    private void lblHealthMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHealthMouseClicked
        setPositionDoctor();
    }//GEN-LAST:event_lblHealthMouseClicked

    private void lblCloseMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMenuMouseEntered
        // TODO add your handling code here:this.setBackground(Color.yellow);
        this.setCursor(new Cursor(HAND_CURSOR));
        lblCloseMenu.setForeground(Color.RED);


    }//GEN-LAST:event_lblCloseMenuMouseEntered

    private void lblCloseMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblCloseMenuMouseExited
        // TODO add your handling code here:
        this.setCursor(new Cursor(DEFAULT_CURSOR));
        lblCloseMenu.setForeground(Color.BLACK);

    }//GEN-LAST:event_lblCloseMenuMouseExited

    private void lblOpenMenuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpenMenuMouseEntered
        // TODO add your handling code here:
        this.setCursor(new Cursor(HAND_CURSOR));

    }//GEN-LAST:event_lblOpenMenuMouseEntered

    private void lblOpenMenuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOpenMenuMouseExited
        // TODO add your handling code here:
        this.setCursor(new Cursor(DEFAULT_CURSOR));
    }//GEN-LAST:event_lblOpenMenuMouseExited

    private void lblHomeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseEntered
        // TODO add your handling code here:
        this.setCursor(new Cursor(HAND_CURSOR) {
        });

        lblHome.setForeground(Color.RED);
    }//GEN-LAST:event_lblHomeMouseEntered

    private void lblAccMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAccMouseEntered
        // TODO add your handling code here:
        this.setCursor(new Cursor(HAND_CURSOR));
        lblAcc.setForeground(Color.RED);

    }//GEN-LAST:event_lblAccMouseEntered

    private void lblSta_LiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSta_LiMouseEntered
        // TODO add your handling code here:
        this.setCursor(new Cursor(HAND_CURSOR));
        lblSta_Li.setForeground(Color.RED);
    }//GEN-LAST:event_lblSta_LiMouseEntered

    private void lblStudentMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStudentMouseEntered
        // TODO add your handling code here:
        this.setCursor(new Cursor(HAND_CURSOR));
        lblStudent.setForeground(Color.RED);
    }//GEN-LAST:event_lblStudentMouseEntered

    private void lblHealthMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHealthMouseEntered
        // TODO add your handling code here:
        this.setCursor(new Cursor(HAND_CURSOR));
        lblHealth.setForeground(Color.RED);
    }//GEN-LAST:event_lblHealthMouseEntered

    private void lblHelpMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHelpMouseEntered
        // TODO add your handling code here:
        this.setCursor(new Cursor(HAND_CURSOR));
        lblHelp.setForeground(Color.RED);
    }//GEN-LAST:event_lblHelpMouseEntered

    private void lblLog_outMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLog_outMouseEntered
        // TODO add your handling code here:
        this.setCursor(new Cursor(HAND_CURSOR));
        lblLog_out.setForeground(Color.RED);
    }//GEN-LAST:event_lblLog_outMouseEntered

    private void lblHomeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseExited
        // TODO add your handling code here:
        this.setCursor(new Cursor(DEFAULT_CURSOR));
        lblHome.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblHomeMouseExited

    private void lblAccMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAccMouseExited
        // TODO add your handling code here:
        this.setCursor(new Cursor(DEFAULT_CURSOR));
        lblAcc.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblAccMouseExited

    private void lblSta_LiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSta_LiMouseExited
        // TODO add your handling code here:
        this.setCursor(new Cursor(DEFAULT_CURSOR));
        lblSta_Li.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblSta_LiMouseExited

    private void lblStudentMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblStudentMouseExited
        // TODO add your handling code here:
        this.setCursor(new Cursor(DEFAULT_CURSOR));
        lblStudent.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblStudentMouseExited

    private void lblHealthMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHealthMouseExited
        // TODO add your handling code here:
        this.setCursor(new Cursor(DEFAULT_CURSOR));
        lblHealth.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblHealthMouseExited

    private void lblHelpMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHelpMouseExited
        // TODO add your handling code here:
        this.setCursor(new Cursor(DEFAULT_CURSOR));
        lblHelp.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblHelpMouseExited

    private void lblLog_outMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLog_outMouseExited
        // TODO add your handling code here:
        this.setCursor(new Cursor(DEFAULT_CURSOR));
        lblLog_out.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblLog_outMouseExited

    private void rdoMaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoMaleActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        ClearForm();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
        this.chooseImageStaff();
    }//GEN-LAST:event_btnUploadActionPerformed

    private void lblAccKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lblAccKeyPressed

    }//GEN-LAST:event_lblAccKeyPressed

    private void txtFindKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFindKeyReleased

        fillTable();
        ClearForm();
    }//GEN-LAST:event_txtFindKeyReleased

    private void txtFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFindActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFindActionPerformed

    private void tblStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStaffMouseClicked
        // TODO add your handling code here:
        tblStaff.setEnabled(false);
        if (evt.getClickCount() == 2) {
            ClickTable();
            tblStaff.setEnabled(true);
        }
    }//GEN-LAST:event_tblStaffMouseClicked

    private void rdoOnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoOnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoOnActionPerformed

    private void cardHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardHome1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cardHome1MouseClicked

    private void rdoOn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoOn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoOn1ActionPerformed

    private void btnUpload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpload1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpload1ActionPerformed

    private void rdoMale1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMale1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoMale1ActionPerformed

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        if (!checkNull()) {
            return;
        }
        this.changePassword();
    }//GEN-LAST:event_btnAcceptActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        if (Message.confirm(this, "Do you want exit ?")) {
            this.CardFalse();
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void lbShowCurrentPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbShowCurrentPassMouseClicked
        boolean isPasswordVisible = txtCurrentPass.getEchoChar() == 0;
        txtCurrentPass.setEchoChar(isPasswordVisible ? '\u2022' : (char) 0);
        lbShowCurrentPass.setIcon(isPasswordVisible ? new ImageIcon(getClass().getResource("/com/Icon/show.png"))
                : new ImageIcon(getClass().getResource("/com/Icon/eye-crossed.png")));
    }//GEN-LAST:event_lbShowCurrentPassMouseClicked

    private void lbShowEnterPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbShowEnterPassMouseClicked
        boolean isPasswordVisible = txtEnterPass.getEchoChar() == 0;
        txtEnterPass.setEchoChar(isPasswordVisible ? '\u2022' : (char) 0);
        lbShowEnterPass.setIcon(isPasswordVisible ? new ImageIcon(getClass().getResource("/com/Icon/show.png"))
                : new ImageIcon(getClass().getResource("/com/Icon/eye-crossed.png")));
    }//GEN-LAST:event_lbShowEnterPassMouseClicked

    private void lbShowNewPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbShowNewPassMouseClicked
        boolean isPasswordVisible = txtNewPass.getEchoChar() == 0;
        txtNewPass.setEchoChar(isPasswordVisible ? '\u2022' : (char) 0);
        lbShowNewPass.setIcon(isPasswordVisible ? new ImageIcon(getClass().getResource("/com/Icon/show.png"))
                : new ImageIcon(getClass().getResource("/com/Icon/eye-crossed.png")));
    }//GEN-LAST:event_lbShowNewPassMouseClicked

    private void cboDay1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDay1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDay1ActionPerformed

    private void txtFind_StuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFind_StuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFind_StuActionPerformed

    private void txtFind_StuKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFind_StuKeyReleased
        // TODO add your handling code here:
        setTableStudent();

    }//GEN-LAST:event_txtFind_StuKeyReleased

    private void txtID_StudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtID_StudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtID_StudentActionPerformed

    private void txtF_Name_StudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtF_Name_StudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtF_Name_StudentActionPerformed

    private void txtM_Name_StudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtM_Name_StudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtM_Name_StudentActionPerformed

    private void txtL_Name_StudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtL_Name_StudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtL_Name_StudentActionPerformed

    private void txtAdd_StudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAdd_StudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAdd_StudentActionPerformed

    private void rdoMale_StudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoMale_StudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoMale_StudentActionPerformed

    private void rdoFemale_StudentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoFemale_StudentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoFemale_StudentActionPerformed

    private void cboDay2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboDay2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboDay2ActionPerformed

    private void btnUpload2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpload2ActionPerformed
        // TODO add your handling code here:
        this.chooseImageStudent();
    }//GEN-LAST:event_btnUpload2ActionPerformed

    private void btnNew2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNew2ActionPerformed
        // TODO add your handling code here:
        autoID();
        btnAdd2.setEnabled(true);
    }//GEN-LAST:event_btnNew2ActionPerformed

    private void rdoStatus_Student_OnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoStatus_Student_OnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoStatus_Student_OnActionPerformed

    private void rdoStatus_Student_OffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoStatus_Student_OffActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoStatus_Student_OffActionPerformed

    private void btnAdd2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdd2ActionPerformed
        // TODO add your handling code here:
        addStudent();
        setTableStudent();
    }//GEN-LAST:event_btnAdd2ActionPerformed

    private void tblList_stuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblList_stuMouseClicked
        // TODO add your handling code here:
        tblList_stu.setEnabled(false);
        if (evt.getClickCount() == 2) {
            ClicktableStudent();
            tblList_stu.setEnabled(true);
        }
    }//GEN-LAST:event_tblList_stuMouseClicked

    private void lblScheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblScheMouseClicked
        // TODO add your handling code here:
        CardFalse();
        lbTitle.setText("Viet Duc School - Schedule");
        setBackGround();
        lblSche.setOpaque(true);
        lblSche.setBackground(Color.getHSBColor((float) 0.16, (float) 0.6, (float) 0.9));
        cardSchedule.setVisible(true);
    }//GEN-LAST:event_lblScheMouseClicked

    private void lblScheMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblScheMouseEntered
        // TODO add your handling code here:
        this.setCursor(new Cursor(HAND_CURSOR));
        lblSche.setForeground(Color.RED);
    }//GEN-LAST:event_lblScheMouseEntered

    private void lblScheMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblScheMouseExited
        // TODO add your handling code here:
        this.setCursor(new Cursor(DEFAULT_CURSOR));
        lblSche.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblScheMouseExited

    private void cboMonthSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMonthSalaryActionPerformed
        // TODO add your handling code here:
        tableSalary();
    }//GEN-LAST:event_cboMonthSalaryActionPerformed

    private void cboMonthSalaryItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMonthSalaryItemStateChanged

    }//GEN-LAST:event_cboMonthSalaryItemStateChanged

    private void lblSalaryMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalaryMouseClicked
        // TODO add your handling code here:
        CardFalse();
        lbTitle.setText("Viet Duc School - Salary");
        setBackGround();
        lblSalary.setOpaque(true);
        lblSalary.setBackground(Color.getHSBColor((float) 0.16, (float) 0.6, (float) 0.9));
        cardStaff_Salary.setVisible(true);
    }//GEN-LAST:event_lblSalaryMouseClicked

    private void lblSalaryMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalaryMouseEntered
        // TODO add your handling code here:
        this.setCursor(new Cursor(HAND_CURSOR));
        lblSalary.setForeground(Color.RED);
    }//GEN-LAST:event_lblSalaryMouseEntered

    private void lblSalaryMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSalaryMouseExited
        // TODO add your handling code here
        this.setCursor(new Cursor(DEFAULT_CURSOR));
        lblSalary.setForeground(Color.BLACK);
    
    }//GEN-LAST:event_lblSalaryMouseExited

    private void cboIDCourseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboIDCourseActionPerformed
        // TODO add your handling code here:
        Object selectedItem = cboIDCourse.getSelectedItem();
        if (selectedItem != null) {
            String name = selectedItem.toString();
            String courseName = coursesDAO.returnCourseName(name);
            txtCourseName.setText(courseName);
        } else {
            // Xử lý khi không có mục nào được chọn trong ComboBox
            txtCourseName.setText(""); // hoặc thực hiện các hành động khác tùy thuộc vào yêu cầu của bạn
        }
    }//GEN-LAST:event_cboIDCourseActionPerformed

    private void txtIDStudentHealthKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIDStudentHealthKeyReleased
        // TODO add your handling code here:
        loadTableIDStudent();
    }//GEN-LAST:event_txtIDStudentHealthKeyReleased

    private void tblIDStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblIDStudentMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            int row = tblIDStudent.getSelectedRow();
            txtIDStudentHealth.setText(String.valueOf(tblIDStudent.getValueAt(row, 0)));

        }
    }//GEN-LAST:event_tblIDStudentMouseClicked

    private void btnSaveHealthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveHealthActionPerformed
        // TODO add your handling code here:
        addHealth();
    }//GEN-LAST:event_btnSaveHealthActionPerformed

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
            java.util.logging.Logger.getLogger(Form_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form_Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Form_Staff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane INFORMATION;
    private javax.swing.JTabbedPane INFORSTUDENT;
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd2;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEdit1;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNew2;
    private javax.swing.JButton btnSaveHealth;
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton btnUpload1;
    private javax.swing.JButton btnUpload2;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.ButtonGroup buttonGroup4;
    private javax.swing.ButtonGroup buttonGroup5;
    private javax.swing.ButtonGroup buttonGroup6;
    private javax.swing.ButtonGroup buttonGroup7;
    private javax.swing.JPanel cardAddStudent;
    private javax.swing.JPanel cardHealth;
    private javax.swing.JPanel cardHome;
    private javax.swing.JPanel cardHome1;
    private javax.swing.JPanel cardInfor;
    private javax.swing.JPanel cardSchedule;
    private javax.swing.JPanel cardStaff;
    private javax.swing.JPanel cardStaff_Salary;
    private javax.swing.JPanel cardhelp;
    private javax.swing.JComboBox<String> cboDay;
    private javax.swing.JComboBox<String> cboDay1;
    private javax.swing.JComboBox<String> cboDay2;
    private javax.swing.JComboBox<String> cboIDCourse;
    private javax.swing.JComboBox<String> cboIDStaffDoctor;
    private javax.swing.JComboBox<String> cboID_Class_Student;
    private javax.swing.JComboBox<String> cboMonth;
    private javax.swing.JComboBox<String> cboMonth1;
    private javax.swing.JComboBox<String> cboMonth2;
    private javax.swing.JComboBox<String> cboMonthSalary;
    private javax.swing.JComboBox<String> cboYear;
    private javax.swing.JComboBox<String> cboYear1;
    private javax.swing.JComboBox<String> cboYear2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel jplMain;
    private javax.swing.JPanel jplSlideMenu;
    private javax.swing.JPanel jplTitle;
    private javax.swing.JPanel jpllMenuBar;
    private javax.swing.JLabel lbShowCurrentPass;
    private javax.swing.JLabel lbShowEnterPass;
    private javax.swing.JLabel lbShowNewPass;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel lblAVT;
    private javax.swing.JLabel lblAcc;
    private javax.swing.JLabel lblAvartarBig;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblAvatar1;
    private javax.swing.JLabel lblAvatar2;
    private javax.swing.JLabel lblCloseMenu;
    private javax.swing.JLabel lblHealth;
    private javax.swing.JLabel lblHelp;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblLog_out;
    private javax.swing.JLabel lblNameBig;
    private javax.swing.JLabel lblOpenMenu;
    private javax.swing.JLabel lblSalary;
    private javax.swing.JLabel lblSche;
    private javax.swing.JLabel lblSta_Li;
    private javax.swing.JLabel lblStudent;
    private javax.swing.JRadioButton rdoBab;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoFemale1;
    private javax.swing.JRadioButton rdoFemale_Student;
    private javax.swing.JRadioButton rdoGood;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JRadioButton rdoMale1;
    private javax.swing.JRadioButton rdoMale_Student;
    private javax.swing.JRadioButton rdoOff;
    private javax.swing.JRadioButton rdoOff1;
    private javax.swing.JRadioButton rdoOn;
    private javax.swing.JRadioButton rdoOn1;
    private javax.swing.JRadioButton rdoStatus_Student_Off;
    private javax.swing.JRadioButton rdoStatus_Student_On;
    private javax.swing.JTable tblHealth;
    private javax.swing.JTable tblIDStudent;
    private javax.swing.JTable tblList_stu;
    private javax.swing.JTable tblSalary_Student;
    private javax.swing.JTable tblSchedule;
    private javax.swing.JTable tblStaff;
    private javax.swing.JTextField txtAdd_Student;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAddress1;
    private javax.swing.JTextField txtCourseName;
    private javax.swing.JPasswordField txtCurrentPass;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JPasswordField txtEnterPass;
    private javax.swing.JTextField txtF_Name;
    private javax.swing.JTextField txtF_Name1;
    private javax.swing.JTextField txtF_Name_Student;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtFind_Stu;
    private javax.swing.JTextField txtHeight;
    private javax.swing.JTextField txtIDStudentHealth;
    private javax.swing.JTextField txtID_Student;
    private javax.swing.JTextField txtId_Staff;
    private javax.swing.JTextField txtId_Staff1;
    private javax.swing.JTextField txtL_Name;
    private javax.swing.JTextField txtL_Name1;
    private javax.swing.JTextField txtL_Name_Student;
    private javax.swing.JTextField txtM_Name;
    private javax.swing.JTextField txtM_Name1;
    private javax.swing.JTextField txtM_Name_Student;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JTextArea txtNote;
    private javax.swing.JTextArea txtNote1;
    private javax.swing.JTextArea txtNote_Student;
    private javax.swing.JTextField txtP_Num;
    private javax.swing.JTextField txtP_Num1;
    private javax.swing.JTextField txtPosition;
    private javax.swing.JTextField txtPosition1;
    private javax.swing.JTextField txtStartDay;
    private javax.swing.JTextField txtStartDay1;
    private javax.swing.JTextField txtWeight;
    private javax.swing.JTextArea txtaNote;
    // End of variables declaration//GEN-END:variables
}
