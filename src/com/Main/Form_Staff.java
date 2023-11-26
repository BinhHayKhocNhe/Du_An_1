/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Main;

import com.DAO.StaffDAO;
import com.Entity.Staff;
import com.Utils.Message;
import com.Utils.Time;
import com.Utils.XImage;
import java.awt.Color;
import java.awt.Cursor;
import java.io.File;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Form_Staff extends javax.swing.JFrame {

    int x = 210;    //chieu rong
    int y = 600;    //chieu cao
    private JFileChooser fileChooser = new JFileChooser();
    private String pathOfSelectedImage = null;
    StaffDAO dao = new StaffDAO();

    /**
     * Creates new form Menu
     */
    public Form_Staff() {
        initComponents();
        this.setIconImage(XImage.getAppIcon());
        this.setTitle("Group 5 - Viet Duc School");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.uploadCombobox();
        fillTable();
        


    }

    private void CardFalse() {
        cardHome.setVisible(false);
        cardThemSau.setVisible(false);
        cardInfor.setVisible(false);
        cardSchedule.setVisible(false);
        cardStaff_List.setVisible(false);
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
            lbAvatar.setIcon(icon);
            lbAvatar.setToolTipText(file.getName());
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
        cbYear.setModel(cbYearModel);
        cbMonth.setModel(cbMonthModel);
        updateDays();
        cbMonth.addActionListener((e) -> {
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
        int selectedMonth = (Integer) cbMonth.getSelectedItem();
        int selectedYear = (Integer) cbYear.getSelectedItem();
        int daysInMonth = getDaysInMonth(selectedMonth, selectedYear);
        cbDay.removeAllItems();
        for (int i = 1; i <= daysInMonth; i++) {
            cbDayModel.addElement(i);
        }
        cbDay.setModel(cbDayModel);
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
        lbAvatar.setText("");
    }
// đổ dữ liệu lên table
        void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblStaff.getModel();
        model.setRowCount(0);
        try {
            String keyword = txtFind.getText();
            List <Staff> list = dao.selectByKeyword(keyword);
            for (Staff sta : list) {
                Object[] row = {
                    sta.getID_Staff(),
                    sta.getFirst_Name(),
                    sta.getEmail(),
                    sta.isGender()?"Male":"Female",
                    sta.isStatus_Staff()?"On":"Off",
                    Time.toString(sta.getStart_Date(), "MM/dd/yyyy"),
                    sta.getPosition(),
                    sta.getNote(),
                };
                model.addRow(row);
            }
        } 
        catch (Exception e) {
            System.out.print(e.getMessage());
            Message.alert(this, "Lỗi truy vấn dữ liệu!");
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
        jPanel1 = new javax.swing.JPanel();
        jplSlideMenu = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
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
        cardHome = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
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
        cbYear = new javax.swing.JComboBox<>();
        jLabel22 = new javax.swing.JLabel();
        cbMonth = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        cbDay = new javax.swing.JComboBox<>();
        lbAvatar = new javax.swing.JLabel();
        btnUpload = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtPosition = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStaff = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        cardStaff_List = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
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
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jplSlideMenu.setBackground(new java.awt.Color(255, 255, 255));
        jplSlideMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jplSlideMenu.setPreferredSize(new java.awt.Dimension(210, 600));
        jplSlideMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/user.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Staff");

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        lblSta_Li.setText("Staff List");
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

        jPanel1.add(jpllMenuBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 940, 40));

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

        cardHome1.setBackground(new java.awt.Color(255, 255, 255));
        cardHome1.setPreferredSize(new java.awt.Dimension(940, 540));
        cardHome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardHome1MouseClicked(evt);
            }
        });

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/education.png"))); // NOI18N

        txtGioiThieu.setEditable(false);
        txtGioiThieu.setText("Polypro là dự án mẫu. Mục tiêu chính là huấn luyện sinh viên qui trình thực hiện dự án.\n\nMục tiêu của dự án này là để rèn luyện kỹ năng IO (CDIO) tức không yêu cầu sinh viên phải thu thập phân tích mà chỉ  thực hiện và vận hành một phần mềm chuẩn bị cho các dự án sau này. Các kỹ năng CD (trong CDIO) sẽ được huấn luyện ở dự án 1 và dự án 2.\n\nYêu cầu về môi trường:\n1. Hệ điều hành bất kỳ\n2. JDK 1.8 trở lên\n3. SQL Server 2008 trở lên");
        txtGioiThieu.setPreferredSize(new java.awt.Dimension(512, 175));
        txtGioiThieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtGioiThieuMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout cardHome1Layout = new javax.swing.GroupLayout(cardHome1);
        cardHome1.setLayout(cardHome1Layout);
        cardHome1Layout.setHorizontalGroup(
            cardHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHome1Layout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtGioiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 453, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        cardHome1Layout.setVerticalGroup(
            cardHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHome1Layout.createSequentialGroup()
                .addGap(105, 105, 105)
                .addGroup(cardHome1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(txtGioiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(179, Short.MAX_VALUE))
        );

        jplMain.add(cardHome1, "card3");

        cardHome.setBackground(new java.awt.Color(255, 255, 255));
        cardHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardHomeMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setText("Home");

        javax.swing.GroupLayout cardHomeLayout = new javax.swing.GroupLayout(cardHome);
        cardHome.setLayout(cardHomeLayout);
        cardHomeLayout.setHorizontalGroup(
            cardHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHomeLayout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jLabel10)
                .addContainerGap(470, Short.MAX_VALUE))
        );
        cardHomeLayout.setVerticalGroup(
            cardHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardHomeLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel10)
                .addContainerGap(471, Short.MAX_VALUE))
        );

        jplMain.add(cardHome, "card3");

        cardInfor.setBackground(new java.awt.Color(255, 255, 255));
        cardInfor.setPreferredSize(new java.awt.Dimension(940, 540));

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

        cbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel22.setText("Month of birth:");

        cbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel23.setText("Day of birth:");

        cbDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lbAvatar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Up.png"))); // NOI18N
        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        jLabel24.setText("Position:");

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
                                .addComponent(cbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEdit)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(txtNote, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
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
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtP_Num, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(rdoMale, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(rdoFemale, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(cbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(18, 18, 18)
                        .addComponent(lbAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnUpload)
                        .addGap(39, 39, 39))))
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
                            .addComponent(cbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(lbAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpload)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jScrollPane1.setViewportView(tblStaff);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("ID or Email or Name");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Zoom.png"))); // NOI18N
        jButton1.setText("Find");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(376, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );

        INFORMATION.addTab("STAFF LIST", jPanel4);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 876, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 365, Short.MAX_VALUE)
        );

        INFORMATION.addTab("CHANGE PASS", jPanel5);

        javax.swing.GroupLayout cardInforLayout = new javax.swing.GroupLayout(cardInfor);
        cardInfor.setLayout(cardInforLayout);
        cardInforLayout.setHorizontalGroup(
            cardInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardInforLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(INFORMATION, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        cardInforLayout.setVerticalGroup(
            cardInforLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardInforLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(INFORMATION, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        jplMain.add(cardInfor, "card3");

        cardStaff_List.setBackground(new java.awt.Color(255, 255, 255));
        cardStaff_List.setPreferredSize(new java.awt.Dimension(940, 540));
        cardStaff_List.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardStaff_ListMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Staff List");

        javax.swing.GroupLayout cardStaff_ListLayout = new javax.swing.GroupLayout(cardStaff_List);
        cardStaff_List.setLayout(cardStaff_ListLayout);
        cardStaff_ListLayout.setHorizontalGroup(
            cardStaff_ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardStaff_ListLayout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jLabel2)
                .addContainerGap(435, Short.MAX_VALUE))
        );
        cardStaff_ListLayout.setVerticalGroup(
            cardStaff_ListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardStaff_ListLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel2)
                .addContainerGap(471, Short.MAX_VALUE))
        );

        jplMain.add(cardStaff_List, "card3");

        cardNhapdiem.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Nhập điểm");

        javax.swing.GroupLayout cardNhapdiemLayout = new javax.swing.GroupLayout(cardNhapdiem);
        cardNhapdiem.setLayout(cardNhapdiemLayout);
        cardNhapdiemLayout.setHorizontalGroup(
            cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jLabel5)
                .addContainerGap(419, Short.MAX_VALUE))
        );
        cardNhapdiemLayout.setVerticalGroup(
            cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addContainerGap(471, Short.MAX_VALUE))
        );

        jplMain.add(cardNhapdiem, "card3");

        cardSchedule.setBackground(new java.awt.Color(255, 255, 255));
        cardSchedule.setPreferredSize(new java.awt.Dimension(940, 540));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setText("Schedule");

        javax.swing.GroupLayout cardScheduleLayout = new javax.swing.GroupLayout(cardSchedule);
        cardSchedule.setLayout(cardScheduleLayout);
        cardScheduleLayout.setHorizontalGroup(
            cardScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardScheduleLayout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jLabel6)
                .addContainerGap(440, Short.MAX_VALUE))
        );
        cardScheduleLayout.setVerticalGroup(
            cardScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardScheduleLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel6)
                .addContainerGap(471, Short.MAX_VALUE))
        );

        jplMain.add(cardSchedule, "card3");

        cardhelp.setBackground(new java.awt.Color(255, 255, 255));
        cardhelp.setPreferredSize(new java.awt.Dimension(940, 540));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setText("Help");

        javax.swing.GroupLayout cardhelpLayout = new javax.swing.GroupLayout(cardhelp);
        cardhelp.setLayout(cardhelpLayout);
        cardhelpLayout.setHorizontalGroup(
            cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardhelpLayout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jLabel7)
                .addContainerGap(481, Short.MAX_VALUE))
        );
        cardhelpLayout.setVerticalGroup(
            cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardhelpLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel7)
                .addContainerGap(471, Short.MAX_VALUE))
        );

        jplMain.add(cardhelp, "card3");

        cardThemSau.setBackground(new java.awt.Color(255, 255, 255));
        cardThemSau.setPreferredSize(new java.awt.Dimension(940, 540));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setText("Có Gì Thiếu");

        javax.swing.GroupLayout cardThemSauLayout = new javax.swing.GroupLayout(cardThemSau);
        cardThemSau.setLayout(cardThemSauLayout);
        cardThemSauLayout.setHorizontalGroup(
            cardThemSauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardThemSauLayout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jLabel12)
                .addContainerGap(404, Short.MAX_VALUE))
        );
        cardThemSauLayout.setVerticalGroup(
            cardThemSauLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardThemSauLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(463, Short.MAX_VALUE))
        );

        jplMain.add(cardThemSau, "card2");

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
        cardInfor.setVisible(true);

    }//GEN-LAST:event_lblAccMouseClicked

    private void lblSta_LiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblSta_LiMouseClicked
        CardFalse();
        cardStaff_List.setVisible(true);

    }//GEN-LAST:event_lblSta_LiMouseClicked

    private void cardStaff_ListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardStaff_ListMouseClicked

    }//GEN-LAST:event_cardStaff_ListMouseClicked

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
        cardStaff_List.setVisible(true);
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

    private void cardHome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardHome1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cardHome1MouseClicked

    private void txtGioiThieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtGioiThieuMouseClicked
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_txtGioiThieuMouseClicked

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
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnUpload;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel cardHome;
    private javax.swing.JPanel cardHome1;
    private javax.swing.JPanel cardInfor;
    private javax.swing.JPanel cardNhapdiem;
    private javax.swing.JPanel cardSchedule;
    private javax.swing.JPanel cardStaff_List;
    private javax.swing.JPanel cardThemSau;
    private javax.swing.JPanel cardhelp;
    private javax.swing.JComboBox<String> cbDay;
    private javax.swing.JComboBox<String> cbMonth;
    private javax.swing.JComboBox<String> cbYear;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jplMain;
    private javax.swing.JPanel jplSlideMenu;
    private javax.swing.JPanel jplTitle;
    private javax.swing.JPanel jpllMenuBar;
    private javax.swing.JLabel lbAvatar;
    private javax.swing.JLabel lblAcc;
    private javax.swing.JLabel lblCloseMenu;
    private javax.swing.JLabel lblHelp;
    private javax.swing.JLabel lblHome;
    private javax.swing.JLabel lblLog_out;
    private javax.swing.JLabel lblOpenMenu;
    private javax.swing.JLabel lblSche;
    private javax.swing.JLabel lblSta_Li;
    private javax.swing.JLabel lblsuadiem;
    private javax.swing.JRadioButton rdoFemale;
    private javax.swing.JRadioButton rdoMale;
    private javax.swing.JTable tblStaff;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtF_Name;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextPane txtGioiThieu;
    private javax.swing.JTextField txtId_Staff;
    private javax.swing.JTextField txtL_Name;
    private javax.swing.JTextField txtM_Name;
    private javax.swing.JTextField txtNote;
    private javax.swing.JTextField txtP_Num;
    private javax.swing.JTextField txtPosition;
    // End of variables declaration//GEN-END:variables
}
