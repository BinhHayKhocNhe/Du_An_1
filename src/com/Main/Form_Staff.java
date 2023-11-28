/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Main;

import com.DAO.StaffDAO;
import com.Entity.Staff;
import com.Utils.Authentication;
import com.Utils.IsValidForm;
import com.Utils.Message;
import com.Utils.Time;
import com.Utils.XImage;
import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.security.interfaces.RSAKey;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Form_Staff extends javax.swing.JFrame {

    int x = 210;    //chieu rong
    int y = 700;    //chieu cao
    private JFileChooser fileChooser = new JFileChooser();
    private String pathOfSelectedImage = null;
    StaffDAO dao = new StaffDAO();

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
        this.uploadCombobox();
        fillTable();
        InforStaff();
    }

    private void CardFalse() {
        cardHome.setVisible(false);
        cardThemSau.setVisible(false);
        cardInfor.setVisible(false);
        cardSchedule.setVisible(false);
        cardStaff.setVisible(false);
        cardNhapdiem.setVisible(false);
        cardhelp.setVisible(false);
        cardHome1.setVisible(false);
        closeMenu();
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

    private void chooseImage() {
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
        for (int i = 1980; i < 2024; i++) {
            cbYearModel.addElement(i);
        }
        for (int i = 1; i <= 12; i++) {
            cbMonthModel.addElement(i);
        }
        cboYear.setModel(cbYearModel);
        cboYear1.setModel(cbYearModel);
        cboMonth.setModel(cbMonthModel);
        cboMonth1.setModel(cbMonthModel);
        updateDays();
        cboMonth.addActionListener((e) -> {
            updateDays();
        });
        cboMonth1.addActionListener((e) -> {
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
        int selectedMonth = (Integer) cboMonth.getSelectedItem();
        int selectedMonth1 = (Integer) cboMonth1.getSelectedItem();
        int selectedYear = (Integer) cboYear.getSelectedItem();
        int selectedYear1 = (Integer) cboYear1.getSelectedItem();
        int daysInMonth = getDaysInMonth(selectedMonth, selectedYear);
        int daysInMonth1 = getDaysInMonth(selectedMonth1, selectedYear1);
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
            List<Staff> list = dao.selectByKeyword(keyword);
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
        Staff sta = dao.selectById(ID_Staff);
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
        lblAvatar.setIcon(XImage.read(sta.getAvatar()));
        txtPosition1.setText(sta.getPosition());
        lblNameBig.setText(sta.getFirst_Name() + " " + sta.getMiddle_Name() + " " + sta.getLast_Name());
        Message.alert(this, "Hello " + sta.getFirst_Name() + " " + sta.getMiddle_Name() + " " + sta.getLast_Name());
    }

    private void InforStaff() {
        Staff staff = dao.selectById(Authentication.staff.getID_Staff());
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
        dao.changePassword(Authentication.staff.getID_Staff(),
                String.valueOf(txtCurrentPass.getPassword()), String.valueOf(txtEnterPass.getPassword()));
        this.resetFormChangePassWord();
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
        jPanel1 = new javax.swing.JPanel();
        jplSlideMenu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblNameBig = new javax.swing.JLabel();
        lblCloseMenu = new javax.swing.JLabel();
        lblHome = new javax.swing.JLabel();
        lblAcc = new javax.swing.JLabel();
        lblSta_Li = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lblLog_out = new javax.swing.JLabel();
        lblHelp = new javax.swing.JLabel();
        lblsuadiem = new javax.swing.JLabel();
        lblSche = new javax.swing.JLabel();
        jpllMenuBar = new javax.swing.JPanel();
        lblOpenMenu = new javax.swing.JLabel();
        jplTitle = new javax.swing.JPanel();
        jplMain = new javax.swing.JPanel();
        cardHome1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        txtGioiThieu = new javax.swing.JTextPane();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
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
        txtNote = new javax.swing.JTextField();
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
        btnNew1 = new javax.swing.JButton();
        btnAdd1 = new javax.swing.JButton();
        btnEdit1 = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        txtNote1 = new javax.swing.JTextField();
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
        cardNhapdiem = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cardSchedule = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cardhelp = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cardThemSau = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();

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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/user.png"))); // NOI18N

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
                .addContainerGap(60, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(lblCloseMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
            .addComponent(lblNameBig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                .addComponent(lblNameBig)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jplSlideMenu.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 210, 150));

        lblHome.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblHome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHome.setText("Home");
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

        lblAcc.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblAcc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblAcc.setText("Account");
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

        lblSta_Li.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblSta_Li.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSta_Li.setText("Staff ");
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
        jplSlideMenu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 469, 210, 10));

        lblLog_out.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblLog_out.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLog_out.setText("Log Out");
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
        jplSlideMenu.add(lblLog_out, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 550, 120, 30));

        lblHelp.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblHelp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHelp.setText("Help");
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
        jplSlideMenu.add(lblHelp, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 500, 120, 30));

        lblsuadiem.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblsuadiem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblsuadiem.setText("Enter scores");
        lblsuadiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblsuadiemMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblsuadiemMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblsuadiemMouseExited(evt);
            }
        });
        jplSlideMenu.add(lblsuadiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 300, 120, 30));

        lblSche.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblSche.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblSche.setText(" Schedule");
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

        javax.swing.GroupLayout jplTitleLayout = new javax.swing.GroupLayout(jplTitle);
        jplTitle.setLayout(jplTitleLayout);
        jplTitleLayout.setHorizontalGroup(
            jplTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1100, Short.MAX_VALUE)
        );
        jplTitleLayout.setVerticalGroup(
            jplTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
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

        txtGioiThieu.setEditable(false);
        txtGioiThieu.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        txtGioiThieu.setText("Polypro là dự án mẫu. Mục tiêu chính là huấn luyện sinh viên qui trình thực hiện dự án.\n\nMục tiêu của dự án này là để rèn luyện kỹ năng IO (CDIO) tức không yêu cầu sinh viên phải thu thập phân tích mà chỉ  thực hiện và vận hành một phần mềm chuẩn bị cho các dự án sau này. Các kỹ năng CD (trong CDIO) sẽ được huấn luyện ở dự án 1 và dự án 2.\n\nYêu cầu về môi trường:\n1. Hệ điều hành bất kỳ\n2. JDK 1.8 trở lên\n3. SQL Server 2008 trở lên");
        txtGioiThieu.setPreferredSize(new java.awt.Dimension(512, 175));
        txtGioiThieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGioiThieuMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("INTRODUCE");

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout cardHome1Layout = new javax.swing.GroupLayout(cardHome1);
        cardHome1.setLayout(cardHome1Layout);
        cardHome1Layout.setHorizontalGroup(
            cardHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHome1Layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGioiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardHome1Layout.createSequentialGroup()
                .addGap(520, 520, 520)
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
                        .addGap(103, 103, 103)
                        .addComponent(txtGioiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(txtNote, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                    .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(36, Short.MAX_VALUE))
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

        buttonGroup1.add(rdoMale1);
        rdoMale1.setText("Male");
        rdoMale1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoMale1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdoFemale1);
        rdoFemale1.setText("Female");

        btnNew1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/newadd.png"))); // NOI18N
        btnNew1.setText("New");
        btnNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNew1ActionPerformed(evt);
            }
        });

        btnAdd1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Add.png"))); // NOI18N
        btnAdd1.setText("Add");

        btnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Edit.png"))); // NOI18N
        btnEdit1.setText("Edit");

        btnDelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Trash.png"))); // NOI18N
        btnDelete1.setText("Delete");

        jLabel36.setText("Note:");

        jLabel37.setText("Year of birth:");

        cboYear1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", " " }));

        jLabel38.setText("Month of birth:");

        cboMonth1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", " " }));

        jLabel39.setText("Day of birth:");

        cboDay1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", " " }));
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

        buttonGroup2.add(rdoOn1);
        rdoOn1.setText("On");
        rdoOn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoOn1ActionPerformed(evt);
            }
        });

        buttonGroup2.add(rdoOff1);
        rdoOff1.setText("Off");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
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
                        .addComponent(txtM_Name1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnNew1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(cboYear1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboMonth1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(btnAdd1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEdit1)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete1))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                                .addComponent(txtNote1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                    .addComponent(txtNote1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnNew1)
                                            .addComponent(btnAdd1)))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnDelete1)
                                            .addComponent(btnEdit1)))))
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
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("INFORMATON", jPanel8);

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

        cardNhapdiem.setBackground(new java.awt.Color(255, 255, 255));
        cardNhapdiem.setPreferredSize(new java.awt.Dimension(1100, 540));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Nhập điểm");

        javax.swing.GroupLayout cardNhapdiemLayout = new javax.swing.GroupLayout(cardNhapdiem);
        cardNhapdiem.setLayout(cardNhapdiemLayout);
        cardNhapdiemLayout.setHorizontalGroup(
            cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jLabel5)
                .addContainerGap(579, Short.MAX_VALUE))
        );
        cardNhapdiemLayout.setVerticalGroup(
            cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addContainerGap(481, Short.MAX_VALUE))
        );

        jplMain.add(cardNhapdiem, "card3");

        cardSchedule.setBackground(new java.awt.Color(255, 255, 255));
        cardSchedule.setPreferredSize(new java.awt.Dimension(1100, 540));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setText("Schedule");

        javax.swing.GroupLayout cardScheduleLayout = new javax.swing.GroupLayout(cardSchedule);
        cardSchedule.setLayout(cardScheduleLayout);
        cardScheduleLayout.setHorizontalGroup(
            cardScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardScheduleLayout.createSequentialGroup()
                .addGap(520, 520, 520)
                .addComponent(jLabel6)
                .addContainerGap(490, Short.MAX_VALUE))
        );
        cardScheduleLayout.setVerticalGroup(
            cardScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardScheduleLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel6)
                .addContainerGap(521, Short.MAX_VALUE))
        );

        jplMain.add(cardSchedule, "card3");

        cardhelp.setBackground(new java.awt.Color(255, 255, 255));
        cardhelp.setPreferredSize(new java.awt.Dimension(1100, 540));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setText("Help");

        javax.swing.GroupLayout cardhelpLayout = new javax.swing.GroupLayout(cardhelp);
        cardhelp.setLayout(cardhelpLayout);
        cardhelpLayout.setHorizontalGroup(
            cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardhelpLayout.createSequentialGroup()
                .addGap(520, 520, 520)
                .addComponent(jLabel7)
                .addContainerGap(531, Short.MAX_VALUE))
        );
        cardhelpLayout.setVerticalGroup(
            cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardhelpLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel7)
                .addContainerGap(521, Short.MAX_VALUE))
        );

        jplMain.add(cardhelp, "card3");

        cardThemSau.setBackground(new java.awt.Color(255, 255, 255));
        cardThemSau.setPreferredSize(new java.awt.Dimension(1100, 540));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setText("Có Gì Thiếu");

        javax.swing.GroupLayout cardThemSauLayout = new javax.swing.GroupLayout(cardThemSau);
        cardThemSau.setLayout(cardThemSauLayout);
        cardThemSauLayout.setHorizontalGroup(
            cardThemSauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardThemSauLayout.createSequentialGroup()
                .addGap(520, 520, 520)
                .addComponent(jLabel12)
                .addContainerGap(454, Short.MAX_VALUE))
        );
        cardThemSauLayout.setVerticalGroup(
            cardThemSauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardThemSauLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(513, Short.MAX_VALUE))
        );

        jplMain.add(cardThemSau, "card2");

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
        cardHome.setVisible(true);
    }//GEN-LAST:event_lblHomeMouseClicked

    private void lblAccMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblAccMouseClicked
        CardFalse();
        cardStaff.setVisible(true);

    }//GEN-LAST:event_lblAccMouseClicked

    private void lblSta_LiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSta_LiMouseClicked
        CardFalse();
        cardInfor.setVisible(true);

    }//GEN-LAST:event_lblSta_LiMouseClicked

    private void cardStaffMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardStaffMouseClicked

    }//GEN-LAST:event_cardStaffMouseClicked

    private void lblsuadiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsuadiemMouseClicked
        CardFalse();

    }//GEN-LAST:event_lblsuadiemMouseClicked

    private void lblHelpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHelpMouseClicked
        CardFalse();
        cardhelp.setVisible(true);

    }//GEN-LAST:event_lblHelpMouseClicked

    private void lblLog_outMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLog_outMouseClicked
        System.exit(0);
    }//GEN-LAST:event_lblLog_outMouseClicked

    private void cardHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardHomeMouseClicked

    }//GEN-LAST:event_cardHomeMouseClicked

    private void lblScheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblScheMouseClicked
        CardFalse();
        cardStaff.setVisible(true);
    }//GEN-LAST:event_lblScheMouseClicked

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

    private void lblsuadiemMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsuadiemMouseEntered
        // TODO add your handling code here:
        this.setCursor(new Cursor(HAND_CURSOR));
        lblsuadiem.setForeground(Color.RED);
    }//GEN-LAST:event_lblsuadiemMouseEntered

    private void lblScheMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblScheMouseEntered
        // TODO add your handling code here:
        this.setCursor(new Cursor(HAND_CURSOR));
        lblSche.setForeground(Color.RED);
    }//GEN-LAST:event_lblScheMouseEntered

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

    private void lblsuadiemMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblsuadiemMouseExited
        // TODO add your handling code here:
        this.setCursor(new Cursor(DEFAULT_CURSOR));
        lblsuadiem.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblsuadiemMouseExited

    private void lblScheMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblScheMouseExited
        // TODO add your handling code here:
        this.setCursor(new Cursor(DEFAULT_CURSOR));
        lblSche.setForeground(Color.BLACK);
    }//GEN-LAST:event_lblScheMouseExited

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
        this.chooseImage();
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

    private void txtGioiThieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGioiThieuMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_txtGioiThieuMouseClicked

    private void rdoOn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoOn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoOn1ActionPerformed

    private void btnUpload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpload1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpload1ActionPerformed

    private void btnNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNew1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNew1ActionPerformed

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
    private javax.swing.JButton btnAccept;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnAdd1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnEdit1;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNew1;
    private javax.swing.JButton btnUpload;
    private javax.swing.JButton btnUpload1;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JPanel cardHome;
    private javax.swing.JPanel cardHome1;
    private javax.swing.JPanel cardInfor;
    private javax.swing.JPanel cardNhapdiem;
    private javax.swing.JPanel cardSchedule;
    private javax.swing.JPanel cardStaff;
    private javax.swing.JPanel cardThemSau;
    private javax.swing.JPanel cardhelp;
    private javax.swing.JComboBox<String> cboDay;
    private javax.swing.JComboBox<String> cboDay1;
    private javax.swing.JComboBox<String> cboMonth;
    private javax.swing.JComboBox<String> cboMonth1;
    private javax.swing.JComboBox<String> cboYear;
    private javax.swing.JComboBox<String> cboYear1;
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
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel jplMain;
    private javax.swing.JPanel jplSlideMenu;
    private javax.swing.JPanel jplTitle;
    private javax.swing.JPanel jpllMenuBar;
    private javax.swing.JLabel lbShowCurrentPass;
    private javax.swing.JLabel lbShowEnterPass;
    private javax.swing.JLabel lbShowNewPass;
    private javax.swing.JLabel lblAcc;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblAvatar1;
    private javax.swing.JLabel lblCloseMenu;
    private javax.swing.JLabel lblHelp;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblLog_out;
    private javax.swing.JLabel lblNameBig;
    private javax.swing.JLabel lblOpenMenu;
    private javax.swing.JLabel lblSche;
    private javax.swing.JLabel lblSta_Li;
    private javax.swing.JLabel lblsuadiem;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoFemale1;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JRadioButton rdoMale1;
    private javax.swing.JRadioButton rdoOff;
    private javax.swing.JRadioButton rdoOff1;
    private javax.swing.JRadioButton rdoOn;
    private javax.swing.JRadioButton rdoOn1;
    private javax.swing.JTable tblStaff;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtAddress1;
    private javax.swing.JPasswordField txtCurrentPass;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtEmail1;
    private javax.swing.JPasswordField txtEnterPass;
    private javax.swing.JTextField txtF_Name;
    private javax.swing.JTextField txtF_Name1;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextPane txtGioiThieu;
    private javax.swing.JTextField txtId_Staff;
    private javax.swing.JTextField txtId_Staff1;
    private javax.swing.JTextField txtL_Name;
    private javax.swing.JTextField txtL_Name1;
    private javax.swing.JTextField txtM_Name;
    private javax.swing.JTextField txtM_Name1;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtNote1;
    private javax.swing.JTextField txtP_Num;
    private javax.swing.JTextField txtP_Num1;
    private javax.swing.JTextField txtPosition;
    private javax.swing.JTextField txtPosition1;
    private javax.swing.JTextField txtStartDay;
    private javax.swing.JTextField txtStartDay1;
    // End of variables declaration//GEN-END:variables
}
