package com.Main;

import javax.swing.JOptionPane;

import com.DAO.ClassDAO;
import com.DAO.PointDAO;
import com.Entity.Class;
import com.DAO.ScheduleDAO;
import com.DAO.StudentDAO;
import com.DAO.Teacher_DAO;
import com.Entity.Point;
import com.Entity.Schedule;

import com.Entity.Student;
import com.Entity.Teacher;
import com.Utils.Authentication;
import com.Utils.IsValidForm;
import com.Utils.Message;

import java.util.Arrays;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Menu extends javax.swing.JFrame {

    StudentDAO studentDAO = new StudentDAO();
    PointDAO pointDAO = new PointDAO();
    ScheduleDAO scheduleDAO = new ScheduleDAO();
    Teacher_DAO teacher_DAO = new Teacher_DAO();
    ClassDAO classDAO = new ClassDAO();
    int x = 210;    //chieu rong
    int y = 600;    //chieu cao
    DefaultTableModel hehe = new DefaultTableModel();
    DefaultTableModel huhu = new DefaultTableModel();

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        cardTrangChu.setVisible(true);
        cardTaiKhoan.setVisible(false);
        jplSlideMenu.setSize(210, 600);
//
        Tbl_list_HocSinh();
        //        loadClassNamesToComboBox();
        initTablePointt();
        Tbl_diem();
        initTableSche();
        filldiem();
        initComboBoxes();
//        validateInput();
        InforTeacher();
    }

    public void openMenu() {
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

    public void closeMenu() {
        jplSlideMenu.setSize(x, y);
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

    public void closecard() {
        closeMenu();
        cardTrangChu.setVisible(false);
        cardTaiKhoan.setVisible(false);
        cardLich.setVisible(false);
        cardDoimk.setVisible(false);
        cardListhocsinh.setVisible(false);
        cardNhapdiem.setVisible(false);
        cardhelp.setVisible(false);

    }

    private void Tbl_list_HocSinh() {
        List<Student> studentList = studentDAO.selectAll();
        DefaultTableModel studentModel = new DefaultTableModel();

        studentModel.addColumn("Student ID");
        studentModel.addColumn("Full Name");
        studentModel.addColumn("Gender");
        studentModel.addColumn("Address");
        studentModel.addColumn("Status");
        studentModel.addColumn("ID_Class");
//        studentModel.addColumn("Class_Name");
        String keyword = txt_id_hocsinh.getText();

        List<Student> list = studentDAO.selectByKeyword(keyword);

//        String ketword2 = (String) cmb_list_class.getSelectedItem();
//         List<Student> list2 = studentDAO.selectByKeyword2(ketword2);
        // Add data to the table model for Student
        for (Student student : list) {
            String fullName = student.getFirst_Name() + " " + student.getMiddle_Name() + " " + student.getLast_Name();
            String genderString = student.isGender() ? "Male" : "Female";
            Object[] rowData = {
                student.getID_Student(),
                fullName,
                genderString,
                student.getAddress_Student(),
                student.isStatus_Student(),
                student.getID_Class(), //               student.getClass_Name(),
            };
            studentModel.addRow(rowData);
        }

        tbl_list_hocsinh.setModel(studentModel);

    }

    private void initTablePointt() {
        String columns[] = {"Student ID", "Class ID", "Subject ID", "Teacher ID", "Year", "Point", "Course Name", "Note"};
        hehe.setColumnIdentifiers(columns);
        tbl_diem.setModel(hehe);
    }

    private void Tbl_diem() {
//        String keyword = txt_tim_name.getText();
//       List<Point> list = pointDAO.selectByKeyword(keyword);
        hehe.setRowCount(0);
        try {
            List<Point> pointList = pointDAO.selectAll();
            for (Point point : pointList) {
                Object[] rowData = {
                    point.getID_Student(),
                    point.getID_Class(),
                    point.getID_Subject(),
                    point.getID_Teacher(),
                    point.getYear(),
                    point.getPoint(),
                    point.getCourse_Name(),
                    point.getNote()
                };
                hehe.addRow(rowData);

            }
        } catch (Exception e) {
        }
    }

    private void initTableSche() {
        String abc[] = {"Course ID", "Teacher ID", "Student ID", "Class ID", "Subject ID", "School Day", "Schedule Date", "Course Name", "Note"};
        huhu.setColumnIdentifiers(abc);
        tbl_lich.setModel(huhu);
    }

    public void filldiem() {

        huhu.setRowCount(0);

        try {
            List<Schedule> scheduleList = scheduleDAO.selectAll();
            for (Schedule schedule : scheduleList) {
                Object[] rowData = {
                    schedule.getID_Course(),
                    schedule.getID_Teacher(),
                    schedule.getID_Student(),
                    schedule.getID_Class(),
                    schedule.getID_Subject(),
                    schedule.getSchoolDay(),
                    schedule.getScheduleDate(),
                    schedule.getCourseName(),
                    schedule.getNote()
                };
                huhu.addRow(rowData);

            }
        } catch (Exception e) {

        }

    }

    private void initComboBoxes() {
        // Xóa tất cả mục hiện có
        cmb_id_teacher.removeAllItems();
        cmb_list_class.removeAllItems();
        cmb_list_sb.removeAllItems();
        cmb_list_hs1.removeAllItems();
        cmbclass.removeAllItems();
        cmb_list_mon.removeAllItems();
        cmb_time.removeAllItems();
        // Sau đó thêm dữ liệu mới
        PointDAO pointDAO = new PointDAO();

        List<String> studentIds = pointDAO.getUniqueStudentIDs();
        for (String id : studentIds) {
            cmb_list_hs1.addItem(id);
        }

        List<String> classIds = pointDAO.getUniqueClassIDs();
        for (String id : classIds) {
            cmb_list_class.addItem(id);
        }

        List<String> subjectIds = pointDAO.getUniqueSubjectIDs();
        for (String id : subjectIds) {
            cmb_list_sb.addItem(id);
        }
        List<String> TeacherIsd = pointDAO.getUniqueTeacherIDs();
        for (String id : TeacherIsd) {
            cmb_id_teacher.addItem(id);
        }
        List<String> classIsd = studentDAO.getidlclass();
        for (String id : classIsd) {
            cmbclass.addItem(id);
        }
        List<String> chIds = scheduleDAO.get_sub();
        for (String id : chIds) {
            cmb_list_mon.addItem(id);
        }
        List<String> Time = scheduleDAO.get_day();
        for (String id : Time) {
            cmb_time.addItem(id);
        }
    }

    private boolean validateInput() {
        // Kiểm tra các trường không được để trống
        if (cmb_id_teacher.getSelectedItem() == null
                || cmb_list_class.getSelectedItem() == null
                || cmb_list_sb.getSelectedItem() == null
                || cmb_id_teacher.getSelectedItem() == null // Thêm vào kiểm tra cho ID_Teacher
                || txt_poin.getText().isEmpty()
                || txt_year.getText().isEmpty()
                //                || txtcr.getText().isEmpty()
                || txt_note.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tất cả các trường phải được điền.");
            return false;
        }

        // Kiểm tra giá trị điểm
        try {
            float point = Float.parseFloat(txt_poin.getText());
            if (point < 0 || point > 10) {
                JOptionPane.showMessageDialog(null, "Điểm phải nằm trong khoảng từ 0 đến 10.");
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Điểm phải là một số hợp lệ.");
            return false;
        }

        return true;
    }

    private void InforTeacher() {
        Teacher teacher = teacher_DAO.selectById(Authentication.teacher.getID_Teacher());
        setFormIforn(teacher);
    }

    private void setFormIforn(Teacher sta) {
        txtID.setText(sta.getID_Teacher());
        txtFirstName.setText(sta.getFirst_Name());
        txtEmail.setText(sta.getEmail());
        (sta.isGender() ? rdMale : rdFemale).setSelected(true);

        txtMidName.setText(sta.getMiddle_Name());
        txtLastName.setText(sta.getLast_Name());

        txtNote.setText(sta.getNote());
        txtPhone.setText(sta.getPhone_Number());
        txtAddress.setText(sta.getAddress_Teacher());

        Message.alert(this, "Hello " + sta.getFirst_Name() + " " + sta.getMiddle_Name() + " " + sta.getLast_Name());
    }

    private void resetFormChangePassWord() {
        JTextField text[] = {txtCurrentPass, txtNewPass, txtEnterPass};
        IsValidForm.refreshForm(text);
//        jTabbedPane1.setSelectedIndex(0);
    }

    private void changePassword() {
        teacher_DAO.changePassword(Authentication.teacher.getID_Teacher(),
                String.valueOf(txtCurrentPass.getPassword()), String.valueOf(txtEnterPass.getPassword()));
        this.resetFormChangePassWord();
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

//    private void loadDataToTable() {
//
//        List<Student> studentList = HocSinhDao.getAllStudents();
//        List<Class> classList = ClassDAO.getAllClasses();
//
//        List<Student> studentList = studentDAO.selectAll();
//        DefaultTableModel model = new DefaultTableModel();
//        tbl_list_hocsinh.setModel(model);
//
//
//        DefaultTableModel studentModel = new DefaultTableModel();
//        DefaultTableModel classModel = new DefaultTableModel();
//
//        studentModel.addColumn("Student ID");
//        studentModel.addColumn("Full Name");
//        studentModel.addColumn("Gender");
//        studentModel.addColumn("Address");
//        studentModel.addColumn("Status");
//
//        // Add data to the table model for Student
//        for (Student student : studentList) {
//            String fullName = student.getFirst_Name() + " " + student.getMiddle_Name() + " " + student.getLast_Name();
//            String genderString = student.isGender() ? "Male" : "Female";
//            Object[] rowData = {
//                student.getID_Student(),
//
//                fullName,
//                genderString,
//                student.getAddress_Student(),
//                student.isStatus_Student(), //             
//
//                student.getFirst_Name(),
//                student.getMiddle_Name(),
//                student.getLast_Name(),
//                student.isGender(),
//                student.getAddress_Student(),
//                student.isStatus_Student(),
//                student.getAvatar(),
//                student.getDate_Of_Birth(),
//                student.getMonth_Of_Birth(),
//                student.getYear_Of_Birth(),
//                student.getNote()
//
//            };
//            studentModel.addRow(rowData);
//        }
//
//        classModel.addColumn("Class ID");
//        classModel.addColumn("Class Name");
//
//        for (Class classEntity : classList) {
//            Object[] rowData = {
//                classEntity.getID_Class(),
//                classEntity.getClass_Name()
//
//            };
//            classModel.addRow(rowData);
//        }
//
//        DefaultTableModel mergedModel = mergeModels(studentModel, classModel);
//
//        tbl_list_hocsinh.setModel(mergedModel);
//    }
//
//    private DefaultTableModel mergeModels(DefaultTableModel model1, DefaultTableModel model2) {
//        DefaultTableModel mergedModel = new DefaultTableModel();
//
//        for (int i = 0; i < model1.getColumnCount(); i++) {
//            mergedModel.addColumn(model1.getColumnName(i));
//        }
//
//        for (int i = 0; i < model2.getColumnCount(); i++) {
//            mergedModel.addColumn(model2.getColumnName(i));
//        }
//
//        int rowCount = Math.max(model1.getRowCount(), model2.getRowCount());
//        for (int i = 0; i < rowCount; i++) {
//            Object[] rowData = new Object[model1.getColumnCount() + model2.getColumnCount()];
//            for (int j = 0; j < model1.getColumnCount(); j++) {
//                rowData[j] = (i < model1.getRowCount()) ? model1.getValueAt(i, j) : null;
//            }
//            for (int j = 0; j < model2.getColumnCount(); j++) {
//                rowData[model1.getColumnCount() + j] = (i < model2.getRowCount()) ? model2.getValueAt(i, j) : null;
//            }
//            mergedModel.addRow(rowData);
//        }
//
//        return mergedModel;
//
//    }
//
//    private void loadClassNamesToComboBox() {
//
//        List<Class> classes = ClassDAO.getAllClasses();
//
//        cmbclass.removeAllItems();
//
//        for (Class classEntity : classes) {
//            cmbclass.addItem(classEntity.getClass_Name());
//        }
//    }
//
//
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jplSlideMenu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblCloseMenu = new javax.swing.JLabel();
        lblTrangChu = new javax.swing.JLabel();
        lblTaiKhoan = new javax.swing.JLabel();
        lblstudenlist = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblthoat = new javax.swing.JLabel();
        lblhotro = new javax.swing.JLabel();
        lblsuadiem = new javax.swing.JLabel();
        lbllich1 = new javax.swing.JLabel();
        jpllMenuBar = new javax.swing.JPanel();
        lblOpenMenu = new javax.swing.JLabel();
        jplTitle = new javax.swing.JPanel();
        jplMain = new javax.swing.JPanel();
        cardTrangChu = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        cardTaiKhoan = new javax.swing.JPanel();
        tabAccount = new javax.swing.JTabbedPane();
        jLayeredPane3 = new javax.swing.JLayeredPane();
        jLabel18 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        txtFirstName = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtLastName = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        txtMidName = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        rdFemale = new javax.swing.JRadioButton();
        rdMale = new javax.swing.JRadioButton();
        jLabel25 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        btnUpdate = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        txtCurrentPass = new javax.swing.JPasswordField();
        txtNewPass = new javax.swing.JPasswordField();
        txtEnterPass = new javax.swing.JPasswordField();
        btnAccept = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lbShowCurrentPass = new javax.swing.JLabel();
        lbShowEnterPass = new javax.swing.JLabel();
        lbShowNewPass = new javax.swing.JLabel();
        cardListhocsinh = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_list_hocsinh = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txt_id_hocsinh = new javax.swing.JTextField();
        cmbclass = new javax.swing.JComboBox<>();
        cardNhapdiem = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_diem = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txt_tim_name = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        cmb_list_sb = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cmb_list_class = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txt_poin = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txt_year = new javax.swing.JTextField();
        bnt_new = new javax.swing.JButton();
        bnt_add = new javax.swing.JButton();
        cmb_id_teacher = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        txt_note = new javax.swing.JTextArea();
        jLabel41 = new javax.swing.JLabel();
        cmb_list_hs1 = new javax.swing.JComboBox<>();
        txtcr = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        cardLich = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbl_lich = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cmb_time = new javax.swing.JComboBox<>();
        cmb_list_mon = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        cardhelp = new javax.swing.JPanel();
        jTextField9 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        cardDoimk = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();

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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/user.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Teacher");

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(lblCloseMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(72, 72, 72))))
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
                .addComponent(jLabel4)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jplSlideMenu.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 150));

        lblTrangChu.setBackground(new java.awt.Color(255, 255, 255));
        lblTrangChu.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblTrangChu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTrangChu.setText("Home");
        lblTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTrangChuMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lblTrangChu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 210, 30));

        lblTaiKhoan.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTaiKhoan.setText("Account");
        lblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblTaiKhoanMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lblTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, 210, 30));

        lblstudenlist.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblstudenlist.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblstudenlist.setText("Student list");
        lblstudenlist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblstudenlistMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lblstudenlist, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 210, 30));
        jplSlideMenu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 500, 210, 10));

        lblthoat.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblthoat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblthoat.setText("Log Out");
        lblthoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblthoatMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lblthoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 210, 30));

        lblhotro.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblhotro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblhotro.setText("Help");
        lblhotro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhotroMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lblhotro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 210, 30));

        lblsuadiem.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblsuadiem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblsuadiem.setText("Enter scores");
        lblsuadiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblsuadiemMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lblsuadiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 210, 30));

        lbllich1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbllich1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbllich1.setText(" Schedule");
        lbllich1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbllich1MouseClicked(evt);
            }
        });
        jplSlideMenu.add(lbllich1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 210, 30));

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
                .addGap(19, 19, 19)
                .addComponent(lblOpenMenu)
                .addContainerGap(890, Short.MAX_VALUE))
        );
        jpllMenuBarLayout.setVerticalGroup(
            jpllMenuBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jpllMenuBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOpenMenu)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.add(jpllMenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 940, 60));

        jplTitle.setBackground(new java.awt.Color(0, 168, 255));

        javax.swing.GroupLayout jplTitleLayout = new javax.swing.GroupLayout(jplTitle);
        jplTitle.setLayout(jplTitleLayout);
        jplTitleLayout.setHorizontalGroup(
            jplTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
        );
        jplTitleLayout.setVerticalGroup(
            jplTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jplTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 940, 30));

        jplMain.setBackground(new java.awt.Color(255, 255, 255));
        jplMain.setLayout(new java.awt.CardLayout());

        cardTrangChu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/education.png"))); // NOI18N

        jTextPane1.setEditable(false);
        jTextPane1.setBackground(new java.awt.Color(204, 204, 204));
        jTextPane1.setText("Congratulations on successfully logging in! \nYou have officially become a part of our academic community. Get ready for dynamic learning journeys, where new knowledge awaits, and personal development takes center stage.\nDive into the exciting classes, participate in activities, and share your thoughts. We believe each step you take will bring you closer to great achievements.\nCheers to starting your new learning journey with excitement!\n\nEnvironmental requirements:\n1. Any operating system\n2. JDK 1.8 or higher\n3. SQL Server 2008 or later");
        jScrollPane5.setViewportView(jTextPane1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel12)
                .addContainerGap(53, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        javax.swing.GroupLayout cardTrangChuLayout = new javax.swing.GroupLayout(cardTrangChu);
        cardTrangChu.setLayout(cardTrangChuLayout);
        cardTrangChuLayout.setHorizontalGroup(
            cardTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardTrangChuLayout.createSequentialGroup()
                .addContainerGap(212, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        cardTrangChuLayout.setVerticalGroup(
            cardTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTrangChuLayout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 141, Short.MAX_VALUE))
        );

        jplMain.add(cardTrangChu, "card2");

        cardTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));

        tabAccount.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("ID:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("First Name:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Middle Name:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Last Name:");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Email:");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Phone:");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Gender:");

        rdFemale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdFemale.setText("Female");

        rdMale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdMale.setText("Male");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Address:");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Note:");

        txtNote.setColumns(20);
        txtNote.setRows(5);
        jScrollPane4.setViewportView(txtNote);

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

        jLayeredPane3.setLayer(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtID, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtFirstName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtLastName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtMidName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtEmail, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtPhone, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(rdFemale, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(rdMale, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(txtAddress, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jLabel26, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane3.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);
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
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtMidName, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                                .addComponent(txtFirstName)
                                .addComponent(txtLastName)))
                        .addGap(74, 74, 74)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(53, 53, 53)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane3Layout.createSequentialGroup()
                                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel25))
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
                        .addComponent(jLabel26)
                        .addGap(78, 78, 78)
                        .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                                .addGap(129, 129, 129)
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnRefresh))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane3Layout.setVerticalGroup(
            jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane3Layout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel22)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel23)
                    .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMidName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel24)
                    .addComponent(rdFemale)
                    .addComponent(rdMale))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jLabel25)
                    .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jLayeredPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate)
                    .addComponent(btnRefresh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabAccount.addTab("INFORMATION", jLayeredPane3);

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Current password:");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("New password:");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel40.setText("Enter the password:");

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

        jLayeredPane1.setLayer(jLabel27, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel28, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel40, javax.swing.JLayeredPane.DEFAULT_LAYER);
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
                            .addComponent(jLabel27)
                            .addComponent(jLabel40))
                        .addGap(43, 43, 43)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jLayeredPane1Layout.createSequentialGroup()
                                .addComponent(btnAccept)
                                .addGap(18, 18, 18)
                                .addComponent(btnExit))
                            .addComponent(txtNewPass, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(txtCurrentPass)
                            .addComponent(txtEnterPass)))
                    .addComponent(jLabel28))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbShowCurrentPass)
                    .addComponent(lbShowEnterPass)
                    .addComponent(lbShowNewPass))
                .addContainerGap(140, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(txtCurrentPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbShowCurrentPass))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbShowNewPass))
                .addGap(18, 18, 18)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel40)
                        .addComponent(txtEnterPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbShowEnterPass))
                .addGap(45, 45, 45)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAccept)
                    .addComponent(btnExit))
                .addContainerGap(100, Short.MAX_VALUE))
        );

        tabAccount.addTab("CHANGE PASSWORD", jLayeredPane1);

        javax.swing.GroupLayout cardTaiKhoanLayout = new javax.swing.GroupLayout(cardTaiKhoan);
        cardTaiKhoan.setLayout(cardTaiKhoanLayout);
        cardTaiKhoanLayout.setHorizontalGroup(
            cardTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardTaiKhoanLayout.createSequentialGroup()
                .addContainerGap(219, Short.MAX_VALUE)
                .addComponent(tabAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );
        cardTaiKhoanLayout.setVerticalGroup(
            cardTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTaiKhoanLayout.createSequentialGroup()
                .addComponent(tabAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 99, Short.MAX_VALUE))
        );

        jplMain.add(cardTaiKhoan, "card3");

        cardListhocsinh.setBackground(new java.awt.Color(255, 255, 255));
        cardListhocsinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardListhocsinhMouseClicked(evt);
            }
        });

        tbl_list_hocsinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Id Student", "Full name", "Address", "Status", "Class", "Gender"
            }
        ));
        jScrollPane2.setViewportView(tbl_list_hocsinh);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Id student");

        txt_id_hocsinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_id_hocsinhActionPerformed(evt);
            }
        });
        txt_id_hocsinh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_id_hocsinhKeyReleased(evt);
            }
        });

        cmbclass.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbclass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cmbclassKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout cardListhocsinhLayout = new javax.swing.GroupLayout(cardListhocsinh);
        cardListhocsinh.setLayout(cardListhocsinhLayout);
        cardListhocsinhLayout.setHorizontalGroup(
            cardListhocsinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardListhocsinhLayout.createSequentialGroup()
                .addContainerGap(311, Short.MAX_VALUE)
                .addGroup(cardListhocsinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardListhocsinhLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_id_hocsinh, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbclass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardListhocsinhLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 622, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        cardListhocsinhLayout.setVerticalGroup(
            cardListhocsinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardListhocsinhLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(cardListhocsinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id_hocsinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbclass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jplMain.add(cardListhocsinh, "card3");

        cardNhapdiem.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Enter grades");

        tbl_diem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Sequence number", "ID Student", "Name", "Point"
            }
        ));
        jScrollPane1.setViewportView(tbl_diem);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Find");

        txt_tim_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tim_nameKeyReleased(evt);
            }
        });

        jLabel11.setText("ID Student");

        cmb_list_sb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setText("Note");

        jLabel14.setText("ID Class");

        cmb_list_class.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setText("ID_Subject");

        jLabel16.setText("Poin");

        txt_poin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_poinActionPerformed(evt);
            }
        });

        jLabel17.setText("Year");

        bnt_new.setText("New");

        bnt_add.setText("Add");
        bnt_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnt_addActionPerformed(evt);
            }
        });

        cmb_id_teacher.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txt_note.setColumns(20);
        txt_note.setRows(5);
        jScrollPane6.setViewportView(txt_note);

        jLabel41.setText("ID Teacher");

        cmb_list_hs1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel42.setText("Course_Name");

        javax.swing.GroupLayout cardNhapdiemLayout = new javax.swing.GroupLayout(cardNhapdiem);
        cardNhapdiem.setLayout(cardNhapdiemLayout);
        cardNhapdiemLayout.setHorizontalGroup(
            cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                .addContainerGap(211, Short.MAX_VALUE)
                .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardNhapdiemLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(378, 378, 378))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardNhapdiemLayout.createSequentialGroup()
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardNhapdiemLayout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardNhapdiemLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(txt_tim_name, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(84, 84, 84)))
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(cardNhapdiemLayout.createSequentialGroup()
                                    .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel16))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txt_poin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmb_list_class, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmb_list_sb, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(cardNhapdiemLayout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(cardNhapdiemLayout.createSequentialGroup()
                                            .addComponent(bnt_add)
                                            .addGap(39, 39, 39)
                                            .addComponent(bnt_new))
                                        .addGroup(cardNhapdiemLayout.createSequentialGroup()
                                            .addComponent(jLabel17)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(cardNhapdiemLayout.createSequentialGroup()
                                    .addComponent(jLabel42)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtcr, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(cardNhapdiemLayout.createSequentialGroup()
                                    .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel41)
                                        .addComponent(jLabel11))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cmb_list_hs1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cmb_id_teacher, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(115, 115, 115))))
        );
        cardNhapdiemLayout.setVerticalGroup(
            cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_tim_name, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel41)
                    .addComponent(cmb_id_teacher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardNhapdiemLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_list_hs1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmb_list_sb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(cmb_list_class, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txt_poin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txt_year, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtcr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnt_add)
                    .addComponent(bnt_new))
                .addGap(118, 118, 118))
        );

        jplMain.add(cardNhapdiem, "card3");

        cardLich.setBackground(new java.awt.Color(255, 255, 255));

        tbl_lich.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Number", "Day", "Room", "Course", "Class", "Time", "Specifics"
            }
        ));
        jScrollPane3.setViewportView(tbl_lich);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Time");

        cmb_time.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmb_list_mon.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton10.setText("<");

        jLabel9.setText("1");

        jButton11.setText(">");

        javax.swing.GroupLayout cardLichLayout = new javax.swing.GroupLayout(cardLich);
        cardLich.setLayout(cardLichLayout);
        cardLichLayout.setHorizontalGroup(
            cardLichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardLichLayout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addGroup(cardLichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardLichLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cmb_time, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardLichLayout.createSequentialGroup()
                .addGap(0, 227, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardLichLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cmb_list_mon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addGap(107, 107, 107))
        );
        cardLichLayout.setVerticalGroup(
            cardLichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardLichLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmb_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(cardLichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmb_list_mon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10)
                    .addComponent(jLabel9)
                    .addComponent(jButton11))
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jplMain.add(cardLich, "card3");

        cardhelp.setBackground(new java.awt.Color(255, 255, 255));

        jTextField9.setText("Search");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Category");

        jLabel30.setText("Frequently asked questions");

        jLabel31.setText("Reset password");

        jLabel32.setText("administration");

        jLabel33.setText("Train");

        jLabel34.setText("Newly admittied student ");
        jLabel34.setToolTipText("");

        jLabel35.setText("778/B1 Nguyen Kiem, Ward 4, Phu Nhuan District, City. Ho Chi Minh.");

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/icons8-school-96.png"))); // NOI18N

        jLabel37.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel37.setText("Contact Info");

        jLabel38.setText("Contact phone number to answer student comments: 0868247043");

        jLabel39.setText("Email addresses of departments: viettvps32768@fpt.edu.vn");

        javax.swing.GroupLayout cardhelpLayout = new javax.swing.GroupLayout(cardhelp);
        cardhelp.setLayout(cardhelpLayout);
        cardhelpLayout.setHorizontalGroup(
            cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardhelpLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardhelpLayout.createSequentialGroup()
                .addContainerGap(210, Short.MAX_VALUE)
                .addGroup(cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardhelpLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel30)
                        .addGap(112, 112, 112)
                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cardhelpLayout.createSequentialGroup()
                        .addGroup(cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(jLabel33)
                            .addComponent(jLabel32)
                            .addComponent(jLabel31))
                        .addGap(64, 64, 64)
                        .addComponent(jLabel36)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jLabel39))))
                .addContainerGap(79, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardhelpLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel35)
                .addGap(223, 223, 223))
        );
        cardhelpLayout.setVerticalGroup(
            cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardhelpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel7)
                    .addComponent(jLabel37))
                .addGroup(cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardhelpLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardhelpLayout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34))
                            .addComponent(jLabel36)))
                    .addGroup(cardhelpLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel39)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addContainerGap(339, Short.MAX_VALUE))
        );

        jplMain.add(cardhelp, "card3");

        cardDoimk.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setText("User Name");

        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel8.setText("Old Password");

        jLabel29.setText("New Password");

        jButton12.setText("Change Password");

        jButton13.setText("Exit");

        javax.swing.GroupLayout cardDoimkLayout = new javax.swing.GroupLayout(cardDoimk);
        cardDoimk.setLayout(cardDoimkLayout);
        cardDoimkLayout.setHorizontalGroup(
            cardDoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDoimkLayout.createSequentialGroup()
                .addGroup(cardDoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardDoimkLayout.createSequentialGroup()
                        .addGap(338, 338, 338)
                        .addGroup(cardDoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel29)
                            .addComponent(jLabel8)
                            .addComponent(jLabel1)
                            .addComponent(jTextField8, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
                            .addComponent(jTextField7)
                            .addComponent(jTextField6)))
                    .addGroup(cardDoimkLayout.createSequentialGroup()
                        .addGap(352, 352, 352)
                        .addComponent(jButton12)
                        .addGap(18, 18, 18)
                        .addComponent(jButton13)))
                .addContainerGap(360, Short.MAX_VALUE))
        );
        cardDoimkLayout.setVerticalGroup(
            cardDoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDoimkLayout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(cardDoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton12)
                    .addComponent(jButton13))
                .addContainerGap(263, Short.MAX_VALUE))
        );

        jplMain.add(cardDoimk, "card3");

        jPanel1.add(jplMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 940, 540));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        jplSlideMenu.setSize(0, y);
        x = 0;
    }//GEN-LAST:event_formWindowOpened

    private void lblTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTrangChuMouseClicked
        closecard();
        cardTrangChu.setVisible(true);


    }//GEN-LAST:event_lblTrangChuMouseClicked

    private void lblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblTaiKhoanMouseClicked
        closecard();
        cardTaiKhoan.setVisible(true);

    }//GEN-LAST:event_lblTaiKhoanMouseClicked

    private void lblstudenlistMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblstudenlistMouseClicked
        closecard();
        cardListhocsinh.setVisible(true);

    }//GEN-LAST:event_lblstudenlistMouseClicked

    private void cardListhocsinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardListhocsinhMouseClicked

    }//GEN-LAST:event_cardListhocsinhMouseClicked

    private void lblsuadiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsuadiemMouseClicked
        closecard();
        cardNhapdiem.setVisible(true);

    }//GEN-LAST:event_lblsuadiemMouseClicked

    private void lblhotroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblhotroMouseClicked
        closecard();
        cardhelp.setVisible(true);
    }//GEN-LAST:event_lblhotroMouseClicked

    private void lblthoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblthoatMouseClicked
        int choice = JOptionPane.showConfirmDialog(null, "Do you want exit?", "Log Out ", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            System.out.println("Out.");
            System.exit(0);
        } else {
            System.out.println(".");

        }

        System.exit(0);
    }//GEN-LAST:event_lblthoatMouseClicked

    private void lbllich1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbllich1MouseClicked
        closecard();
        cardLich.setVisible(true);

    }//GEN-LAST:event_lbllich1MouseClicked

    private void txt_poinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_poinActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_poinActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void txt_id_hocsinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_id_hocsinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_id_hocsinhActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed

    }//GEN-LAST:event_btnRefreshActionPerformed

    private void btnAcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAcceptActionPerformed
        if (!checkNull()) {
            return;
        }
        this.changePassword();
    }//GEN-LAST:event_btnAcceptActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed

    }//GEN-LAST:event_btnExitActionPerformed

    private void lbShowCurrentPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbShowCurrentPassMouseClicked

    }//GEN-LAST:event_lbShowCurrentPassMouseClicked

    private void lbShowEnterPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbShowEnterPassMouseClicked

    }//GEN-LAST:event_lbShowEnterPassMouseClicked

    private void lbShowNewPassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbShowNewPassMouseClicked

    }//GEN-LAST:event_lbShowNewPassMouseClicked

    private void bnt_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bnt_addActionPerformed
        if (!validateInput()) {
            return;
        }

        Point point = new Point();
        point.setID_Student(cmb_list_hs1.getSelectedItem().toString());
        point.setID_Class(cmb_list_class.getSelectedItem().toString());
        point.setID_Subject(cmb_list_sb.getSelectedItem().toString());
        point.setID_Teacher(cmb_id_teacher.getSelectedItem().toString()); // Đặt ID_Teacher
        point.setPoint(Float.parseFloat(txt_poin.getText()));
        point.setYear(Integer.parseInt(txt_year.getText()));
//        point.setCourse_Name(txtcr.getText());
        point.setNote(txt_note.getText());
        // Thiếu Course_Name
        point.setCourse_Name(txtcr.getText());

        try {
            pointDAO.insert(point);
        } catch (Exception ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ
        }

    }//GEN-LAST:event_bnt_addActionPerformed

    private void txt_id_hocsinhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_id_hocsinhKeyReleased
        // TODO add your handling code here:
        Tbl_list_HocSinh();
    }//GEN-LAST:event_txt_id_hocsinhKeyReleased

    private void txt_tim_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tim_nameKeyReleased
//        Tbl_diem();
    }//GEN-LAST:event_txt_tim_nameKeyReleased

    private void cmbclassKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmbclassKeyReleased
//        Tbl_list_HocSinh();
    }//GEN-LAST:event_cmbclassKeyReleased
    private void btn_tim_hocsinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tim_hocsinhActionPerformed

        String studentId = txt_id_hocsinh.getText();

// Lấy danh sách học sinh từ DAO
        List<Student> studentList = studentDAO.selectAll();

// Lấy danh sách lớp học từ DAO
        List<Class> classList = classDAO.selectAll();

// Tạo danh sách dòng dữ liệu cho bảng
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Student ID");
        tableModel.addColumn("Full Name");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Address");
        tableModel.addColumn("Status");
        tableModel.addColumn("Class ID");
        tableModel.addColumn("Class Name");

// Lọc danh sách học sinh và lớp học dựa trên ID học sinh
        for (Student student : studentList) {
            if (student.getID_Student().equalsIgnoreCase(studentId)) {
                String fullName = student.getFirst_Name() + " " + student.getMiddle_Name() + " " + student.getLast_Name();
                String genderString = student.isGender() ? "Male" : "Female";

                // Dòng dữ liệu cho học sinh
                Object[] rowData = {
                    student.getID_Student(),
                    fullName,
                    genderString,
                    student.getAddress_Student(),
                    student.isStatus_Student(),
                    "", // Dòng này để trống cho thông tin lớp học, bạn có thể cập nhật sau
                    "" // Dòng này để trống cho thông tin tên lớp học, bạn có thể cập nhật sau
                };

                // Thêm dòng dữ liệu học sinh vào bảng
                tableModel.addRow(rowData);

                // Tìm lớp học tương ứng với ID học sinh
                for (Class classEntity : classList) {
                    if (classEntity.getID_Student().equalsIgnoreCase(studentId)) {
                        // Cập nhật thông tin lớp học vào dòng dữ liệu đã tạo
                        tableModel.setValueAt(classEntity.getID_Class(), tableModel.getRowCount() - 1, 5);
                        tableModel.setValueAt(classEntity.getClass_Name(), tableModel.getRowCount() - 1, 6);
                    }
                }
            }
        }

// Cập nhật bảng tbl_list_hocsinh bằng dữ liệu đã tạo
        tbl_list_hocsinh.setModel(tableModel);

//String studentId = txt_id_hocsinh.getText();
//
//// Lấy danh sách học sinh từ DAO
//List<Student> studentList = HocSinhDao.getAllStudents();
//
//// Lấy danh sách lớp học từ DAO
//List<Class> classList = ClassDAO.getAllClasses();
//
//// Tạo danh sách dòng dữ liệu cho bảng
//DefaultTableModel tableModel = new DefaultTableModel();
//tableModel.addColumn("Student ID");
//tableModel.addColumn("Full Name");
//tableModel.addColumn("Gender");
//tableModel.addColumn("Address");
//tableModel.addColumn("Status");
//tableModel.addColumn("Class ID");
//tableModel.addColumn("Class Name");
//
//// Lọc danh sách học sinh và lớp học dựa trên ID học sinh
//for (Student student : studentList) {
//    if (student.getID_Student().equalsIgnoreCase(studentId)) {
//        String fullName = student.getFirst_Name() + " " + student.getMiddle_Name() + " " + student.getLast_Name();
//        String genderString = student.isGender() ? "Male" : "Female";
//        
//        // Dòng dữ liệu cho học sinh
//        Object[] rowData = {
//            student.getID_Student(),
//            fullName,
//            genderString,
//            student.getAddress_Student(),
//            student.isStatus_Student(),
//            "", // Dòng này để trống cho thông tin lớp học, bạn có thể cập nhật sau
//            ""  // Dòng này để trống cho thông tin tên lớp học, bạn có thể cập nhật sau
//        };
//        
//        // Thêm dòng dữ liệu học sinh vào bảng
//        tableModel.addRow(rowData);
//        
//        // Tìm lớp học tương ứng với ID học sinh
//        for (Class classEntity : classList) {
//            if (classEntity.getID_Student().equalsIgnoreCase(studentId)) {
//                // Cập nhật thông tin lớp học vào dòng dữ liệu đã tạo
//                tableModel.setValueAt(classEntity.getID_Class(), tableModel.getRowCount() - 1, 5);
//                tableModel.setValueAt(classEntity.getClass_Name(), tableModel.getRowCount() - 1, 6);
//            }
//        }
//    }
//}
//
//// Cập nhật bảng tbl_list_hocsinh bằng dữ liệu đã tạo
//tbl_list_hocsinh.setModel(tableModel);           

    }//GEN-LAST:event_btn_tim_hocsinhActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bnt_add;
    private javax.swing.JButton bnt_new;
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JPanel cardDoimk;
    private javax.swing.JPanel cardLich;
    private javax.swing.JPanel cardListhocsinh;
    private javax.swing.JPanel cardNhapdiem;
    private javax.swing.JPanel cardTaiKhoan;
    private javax.swing.JPanel cardTrangChu;
    private javax.swing.JPanel cardhelp;
    private javax.swing.JComboBox<String> cmb_id_teacher;
    private javax.swing.JComboBox<String> cmb_list_class;
    private javax.swing.JComboBox<String> cmb_list_hs1;
    private javax.swing.JComboBox<String> cmb_list_mon;
    private javax.swing.JComboBox<String> cmb_list_sb;
    private javax.swing.JComboBox<String> cmb_time;
    private javax.swing.JComboBox<String> cmbclass;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel jplMain;
    private javax.swing.JPanel jplSlideMenu;
    private javax.swing.JPanel jplTitle;
    private javax.swing.JPanel jpllMenuBar;
    private javax.swing.JLabel lbShowCurrentPass;
    private javax.swing.JLabel lbShowEnterPass;
    private javax.swing.JLabel lbShowNewPass;
    private javax.swing.JLabel lblCloseMenu;
    private javax.swing.JLabel lblOpenMenu;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JLabel lblhotro;
    private javax.swing.JLabel lbllich1;
    private javax.swing.JLabel lblstudenlist;
    private javax.swing.JLabel lblsuadiem;
    private javax.swing.JLabel lblthoat;
    private javax.swing.JRadioButton rdFemale;
    private javax.swing.JRadioButton rdMale;
    private javax.swing.JTabbedPane tabAccount;
    private javax.swing.JTable tbl_diem;
    private javax.swing.JTable tbl_lich;
    private javax.swing.JTable tbl_list_hocsinh;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JPasswordField txtCurrentPass;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JPasswordField txtEnterPass;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMidName;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JTextArea txtNote;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txt_id_hocsinh;
    private javax.swing.JTextArea txt_note;
    private javax.swing.JTextField txt_poin;
    private javax.swing.JTextField txt_tim_name;
    private javax.swing.JTextField txt_year;
    private javax.swing.JTextField txtcr;
    // End of variables declaration//GEN-END:variables
}
