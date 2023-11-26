/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Main;

import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Menu extends javax.swing.JFrame {

    int x = 210;    //chieu rong
    int y = 600;    //chieu cao

    /**
     * Creates new form Menu
     */
    public Menu() {
        initComponents();
        cardTrangChu.setVisible(true);
        cardTaiKhoan.setVisible(false);
        jplSlideMenu.setSize(210, 600);
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
        lbldoimk = new javax.swing.JLabel();
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
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel18 = new javax.swing.JLabel();
        txtFristName = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtMidName = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        rdMale = new javax.swing.JRadioButton();
        rdFemale = new javax.swing.JRadioButton();
        txtID = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        rdAttending = new javax.swing.JRadioButton();
        rdnotAttending = new javax.swing.JRadioButton();
        jLabel24 = new javax.swing.JLabel();
        txtAddress = new javax.swing.JTextField();
        lbAvatar = new javax.swing.JLabel();
        btnUpload = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        cbYear = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        cbMonth = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        cbDay = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        cardListhocsinh = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        cardNhapdiem = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jComboBox4 = new javax.swing.JComboBox<>();
        cardLich = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jComboBox6 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
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
        jplSlideMenu.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 452, 210, 10));

        lblthoat.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblthoat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblthoat.setText("Log Out");
        lblthoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblthoatMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lblthoat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 560, 210, 30));

        lbldoimk.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lbldoimk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbldoimk.setText("Change Password");
        lbldoimk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldoimkMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lbldoimk, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 210, 30));

        lblhotro.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        lblhotro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblhotro.setText("Help");
        lblhotro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblhotroMouseClicked(evt);
            }
        });
        jplSlideMenu.add(lblhotro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 210, 30));

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(jLabel12)
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83))
        );

        javax.swing.GroupLayout cardTrangChuLayout = new javax.swing.GroupLayout(cardTrangChu);
        cardTrangChu.setLayout(cardTrangChuLayout);
        cardTrangChuLayout.setHorizontalGroup(
            cardTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTrangChuLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        cardTrangChuLayout.setVerticalGroup(
            cardTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        jplMain.add(cardTrangChu, "card2");

        cardTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("ID teacher:");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("First Name:");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Middle Name:");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Last Name:");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("Gender:");

        rdMale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdMale.setText("Male");

        rdFemale.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdFemale.setText("Female");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Status:");

        rdAttending.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdAttending.setText("Attending");

        rdnotAttending.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        rdnotAttending.setText("Not attending");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel24.setText("Address:");

        lbAvatar.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnUpload.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnUpload.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/Icon/Up.png"))); // NOI18N
        btnUpload.setText("Upload");
        btnUpload.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel25.setText("Year of birth:");

        cbYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbYearActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Month of birth:");

        cbMonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel27.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel27.setText("Day of birth:");

        cbDay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Note:");

        txtNote.setColumns(20);
        txtNote.setRows(5);
        jScrollPane4.setViewportView(txtNote);

        jLayeredPane1.setLayer(jLabel18, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtFristName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel19, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel20, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtMidName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtLastName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel21, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel22, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(rdMale, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(rdFemale, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtID, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel23, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(rdAttending, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(rdnotAttending, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel24, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(txtAddress, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(lbAvatar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(btnUpload, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel25, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cbYear, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel26, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cbMonth, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel27, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(cbDay, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jLabel28, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jScrollPane4, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21)
                    .addComponent(jLabel23)
                    .addComponent(jLabel24)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jLabel22))
                        .addGap(32, 32, 32)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtMidName, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtFristName, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(rdMale)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdFemale)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                                .addComponent(lbAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(cbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel26)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                        .addComponent(rdAttending)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdnotAttending)))
                                .addGap(18, 18, 18)
                                .addComponent(btnUpload))
                            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel27)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(cbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel25)
                    .addComponent(jLabel28))
                .addGap(0, 66, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtFristName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtMidName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(rdMale)
                            .addComponent(rdFemale))
                        .addGap(34, 34, 34)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel23)
                            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdAttending)
                                .addComponent(rdnotAttending)))
                        .addGap(12, 12, 12)
                        .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(txtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jLayeredPane1Layout.createSequentialGroup()
                        .addComponent(lbAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnUpload)
                        .addGap(53, 53, 53)))
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(cbYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(cbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27)
                    .addComponent(cbDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout cardTaiKhoanLayout = new javax.swing.GroupLayout(cardTaiKhoan);
        cardTaiKhoan.setLayout(cardTaiKhoanLayout);
        cardTaiKhoanLayout.setHorizontalGroup(
            cardTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardTaiKhoanLayout.createSequentialGroup()
                .addContainerGap(197, Short.MAX_VALUE)
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );
        cardTaiKhoanLayout.setVerticalGroup(
            cardTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTaiKhoanLayout.createSequentialGroup()
                .addComponent(jLayeredPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 39, Short.MAX_VALUE))
        );

        jplMain.add(cardTaiKhoan, "card3");

        cardListhocsinh.setBackground(new java.awt.Color(255, 255, 255));
        cardListhocsinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardListhocsinhMouseClicked(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Id Student", "Full name", "Address", "Status", "Class"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Id student");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton9.setText("Find");

        javax.swing.GroupLayout cardListhocsinhLayout = new javax.swing.GroupLayout(cardListhocsinh);
        cardListhocsinh.setLayout(cardListhocsinhLayout);
        cardListhocsinhLayout.setHorizontalGroup(
            cardListhocsinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardListhocsinhLayout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(cardListhocsinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 662, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(cardListhocsinhLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton9)))
                .addContainerGap(185, Short.MAX_VALUE))
        );
        cardListhocsinhLayout.setVerticalGroup(
            cardListhocsinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardListhocsinhLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(cardListhocsinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        jplMain.add(cardListhocsinh, "card3");

        cardNhapdiem.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Enter grades");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Find");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("ID Student");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel13.setText("Full name");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel14.setText("Subject name");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel15.setText("Gender");

        jLabel16.setText("Note");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel17.setText("Semester grades");

        jButton1.setText("Find");

        jButton2.setText("Reload");

        jButton3.setText("Exit");

        jButton4.setText("Cannel");

        jButton5.setText("Save");

        jButton6.setText("Add");

        jButton7.setText("Add");

        jButton8.setText("Save");

        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout cardNhapdiemLayout = new javax.swing.GroupLayout(cardNhapdiem);
        cardNhapdiem.setLayout(cardNhapdiemLayout);
        cardNhapdiemLayout.setHorizontalGroup(
            cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardNhapdiemLayout.createSequentialGroup()
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                                .addGap(359, 359, 359)
                                .addComponent(jLabel5))
                            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                                .addGap(420, 420, 420)
                                .addComponent(jButton6)
                                .addGap(38, 38, 38)
                                .addComponent(jButton5)
                                .addGap(27, 27, 27)
                                .addComponent(jButton4)
                                .addGap(37, 37, 37)
                                .addComponent(jButton3)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(cardNhapdiemLayout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                                .addComponent(jButton7)
                                .addGap(18, 18, 18)
                                .addComponent(jButton8)
                                .addGap(15, 15, 15))
                            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                                .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox2, 0, 189, Short.MAX_VALUE)
                                    .addComponent(jTextField2)
                                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField4)
                                    .addComponent(jTextField5)
                                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(0, 4, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardNhapdiemLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        cardNhapdiemLayout.setVerticalGroup(
            cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardNhapdiemLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)))
                    .addGroup(cardNhapdiemLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cardNhapdiemLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(jButton4)
                            .addComponent(jButton5)
                            .addComponent(jButton6)))
                    .addGroup(cardNhapdiemLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addComponent(jButton8))))
                .addContainerGap(138, Short.MAX_VALUE))
        );

        jplMain.add(cardNhapdiem, "card3");

        cardLich.setBackground(new java.awt.Color(255, 255, 255));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Sequence number", "Day", "Room", "Course", "Class", "Time", "Specifics"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Time");

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton10.setText("<");

        jLabel9.setText("1");

        jButton11.setText(">");

        javax.swing.GroupLayout cardLichLayout = new javax.swing.GroupLayout(cardLich);
        cardLich.setLayout(cardLichLayout);
        cardLichLayout.setHorizontalGroup(
            cardLichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardLichLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addGap(240, 240, 240))
            .addGroup(cardLichLayout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addGroup(cardLichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 773, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        cardLichLayout.setVerticalGroup(
            cardLichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardLichLayout.createSequentialGroup()
                .addGroup(cardLichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(cardLichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10)
                    .addComponent(jLabel9)
                    .addComponent(jButton11))
                .addContainerGap(40, Short.MAX_VALUE))
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
            .addGroup(cardhelpLayout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(jLabel33)
                    .addComponent(jLabel32)
                    .addComponent(jLabel31)
                    .addGroup(cardhelpLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel30))
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardhelpLayout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
                .addGroup(cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel38))
                .addGap(47, 47, 47))
        );
        cardhelpLayout.setVerticalGroup(
            cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardhelpLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel30))
                .addGap(31, 31, 31)
                .addComponent(jLabel31)
                .addGap(28, 28, 28)
                .addComponent(jLabel32)
                .addGap(30, 30, 30)
                .addComponent(jLabel33)
                .addGap(36, 36, 36)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addGroup(cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cardhelpLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel38))
                    .addComponent(jLabel36, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel39)
                    .addComponent(jLabel35))
                .addGap(36, 36, 36))
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

    private void lbldoimkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldoimkMouseClicked
         closecard();
        cardDoimk.setVisible(true);
     
    }//GEN-LAST:event_lbldoimkMouseClicked

    private void lblthoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblthoatMouseClicked
        int choice = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát chương trình không?", "Xác nhận", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            System.out.println("Chương trình kết thúc.");
            System.exit(0);
        } else {
            System.out.println("Chương trình tiếp tục thực thi.");
           
        }

        System.exit(0);
    }//GEN-LAST:event_lblthoatMouseClicked

    private void lbllich1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbllich1MouseClicked
         closecard();
        cardLich.setVisible(true);
     
    }//GEN-LAST:event_lbllich1MouseClicked

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void btnUploadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadActionPerformed
       
    }//GEN-LAST:event_btnUploadActionPerformed

    private void cbYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbYearActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpload;
    private javax.swing.JPanel cardDoimk;
    private javax.swing.JPanel cardLich;
    private javax.swing.JPanel cardListhocsinh;
    private javax.swing.JPanel cardNhapdiem;
    private javax.swing.JPanel cardTaiKhoan;
    private javax.swing.JPanel cardTrangChu;
    private javax.swing.JPanel cardhelp;
    private javax.swing.JComboBox<String> cbDay;
    private javax.swing.JComboBox<String> cbMonth;
    private javax.swing.JComboBox<String> cbYear;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JPanel jplMain;
    private javax.swing.JPanel jplSlideMenu;
    private javax.swing.JPanel jplTitle;
    private javax.swing.JPanel jpllMenuBar;
    private javax.swing.JLabel lbAvatar;
    private javax.swing.JLabel lblCloseMenu;
    private javax.swing.JLabel lblOpenMenu;
    private javax.swing.JLabel lblTaiKhoan;
    private javax.swing.JLabel lblTrangChu;
    private javax.swing.JLabel lbldoimk;
    private javax.swing.JLabel lblhotro;
    private javax.swing.JLabel lbllich1;
    private javax.swing.JLabel lblstudenlist;
    private javax.swing.JLabel lblsuadiem;
    private javax.swing.JLabel lblthoat;
    private javax.swing.JRadioButton rdAttending;
    private javax.swing.JRadioButton rdFemale;
    private javax.swing.JRadioButton rdMale;
    private javax.swing.JRadioButton rdnotAttending;
    private javax.swing.JTextField txtAddress;
    private javax.swing.JTextField txtFristName;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtMidName;
    private javax.swing.JTextArea txtNote;
    // End of variables declaration//GEN-END:variables
}
