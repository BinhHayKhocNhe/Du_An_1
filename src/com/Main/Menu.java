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
        haha = new javax.swing.JLabel();
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
        jLabel12 = new javax.swing.JLabel();
        cardTaiKhoan = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cardListhocsinh = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cardNhapdiem = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        cardLich = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        cardhelp = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        cardDoimk = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cardDoimk1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();

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

        haha.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        haha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        haha.setText("haha");
        haha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                hahaMouseClicked(evt);
            }
        });
        jplSlideMenu.add(haha, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 210, 30));

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
                .addContainerGap(23, Short.MAX_VALUE))
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

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel12.setText("TRANG CHỦ");

        javax.swing.GroupLayout cardTrangChuLayout = new javax.swing.GroupLayout(cardTrangChu);
        cardTrangChu.setLayout(cardTrangChuLayout);
        cardTrangChuLayout.setHorizontalGroup(
            cardTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTrangChuLayout.createSequentialGroup()
                .addGap(359, 359, 359)
                .addComponent(jLabel12)
                .addContainerGap(438, Short.MAX_VALUE))
        );
        cardTrangChuLayout.setVerticalGroup(
            cardTrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTrangChuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(492, Short.MAX_VALUE))
        );

        jplMain.add(cardTrangChu, "card2");

        cardTaiKhoan.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setText("TÀI KHOẢN");

        javax.swing.GroupLayout cardTaiKhoanLayout = new javax.swing.GroupLayout(cardTaiKhoan);
        cardTaiKhoan.setLayout(cardTaiKhoanLayout);
        cardTaiKhoanLayout.setHorizontalGroup(
            cardTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTaiKhoanLayout.createSequentialGroup()
                .addGap(387, 387, 387)
                .addComponent(jLabel1)
                .addContainerGap(414, Short.MAX_VALUE))
        );
        cardTaiKhoanLayout.setVerticalGroup(
            cardTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardTaiKhoanLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addContainerGap(484, Short.MAX_VALUE))
        );

        jplMain.add(cardTaiKhoan, "card3");

        cardListhocsinh.setBackground(new java.awt.Color(255, 255, 255));
        cardListhocsinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardListhocsinhMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Học Sinh");

        javax.swing.GroupLayout cardListhocsinhLayout = new javax.swing.GroupLayout(cardListhocsinh);
        cardListhocsinh.setLayout(cardListhocsinhLayout);
        cardListhocsinhLayout.setHorizontalGroup(
            cardListhocsinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardListhocsinhLayout.createSequentialGroup()
                .addGap(387, 387, 387)
                .addComponent(jLabel2)
                .addContainerGap(460, Short.MAX_VALUE))
        );
        cardListhocsinhLayout.setVerticalGroup(
            cardListhocsinhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardListhocsinhLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addContainerGap(484, Short.MAX_VALUE))
        );

        jplMain.add(cardListhocsinh, "card3");

        cardNhapdiem.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Nhập điểm");

        javax.swing.GroupLayout cardNhapdiemLayout = new javax.swing.GroupLayout(cardNhapdiem);
        cardNhapdiem.setLayout(cardNhapdiemLayout);
        cardNhapdiemLayout.setHorizontalGroup(
            cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                .addGap(387, 387, 387)
                .addComponent(jLabel5)
                .addContainerGap(442, Short.MAX_VALUE))
        );
        cardNhapdiemLayout.setVerticalGroup(
            cardNhapdiemLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardNhapdiemLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addContainerGap(484, Short.MAX_VALUE))
        );

        jplMain.add(cardNhapdiem, "card3");

        cardLich.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setText("Lịch");

        javax.swing.GroupLayout cardLichLayout = new javax.swing.GroupLayout(cardLich);
        cardLich.setLayout(cardLichLayout);
        cardLichLayout.setHorizontalGroup(
            cardLichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardLichLayout.createSequentialGroup()
                .addGap(387, 387, 387)
                .addComponent(jLabel6)
                .addContainerGap(507, Short.MAX_VALUE))
        );
        cardLichLayout.setVerticalGroup(
            cardLichLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardLichLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel6)
                .addContainerGap(484, Short.MAX_VALUE))
        );

        jplMain.add(cardLich, "card3");

        cardhelp.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setText("Help");

        javax.swing.GroupLayout cardhelpLayout = new javax.swing.GroupLayout(cardhelp);
        cardhelp.setLayout(cardhelpLayout);
        cardhelpLayout.setHorizontalGroup(
            cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardhelpLayout.createSequentialGroup()
                .addGap(387, 387, 387)
                .addComponent(jLabel7)
                .addContainerGap(504, Short.MAX_VALUE))
        );
        cardhelpLayout.setVerticalGroup(
            cardhelpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardhelpLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel7)
                .addContainerGap(484, Short.MAX_VALUE))
        );

        jplMain.add(cardhelp, "card3");

        cardDoimk.setBackground(new java.awt.Color(255, 255, 255));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setText("ĐỔI mk");

        javax.swing.GroupLayout cardDoimkLayout = new javax.swing.GroupLayout(cardDoimk);
        cardDoimk.setLayout(cardDoimkLayout);
        cardDoimkLayout.setHorizontalGroup(
            cardDoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDoimkLayout.createSequentialGroup()
                .addGap(387, 387, 387)
                .addComponent(jLabel8)
                .addContainerGap(470, Short.MAX_VALUE))
        );
        cardDoimkLayout.setVerticalGroup(
            cardDoimkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDoimkLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel8)
                .addContainerGap(484, Short.MAX_VALUE))
        );

        jplMain.add(cardDoimk, "card3");

        cardDoimk1.setBackground(new java.awt.Color(255, 255, 255));
        cardDoimk1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cardDoimk1MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setText("ĐỔI mk1");

        javax.swing.GroupLayout cardDoimk1Layout = new javax.swing.GroupLayout(cardDoimk1);
        cardDoimk1.setLayout(cardDoimk1Layout);
        cardDoimk1Layout.setHorizontalGroup(
            cardDoimk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDoimk1Layout.createSequentialGroup()
                .addGap(387, 387, 387)
                .addComponent(jLabel9)
                .addContainerGap(458, Short.MAX_VALUE))
        );
        cardDoimk1Layout.setVerticalGroup(
            cardDoimk1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cardDoimk1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel9)
                .addContainerGap(484, Short.MAX_VALUE))
        );

        jplMain.add(cardDoimk1, "card3");

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

    private void hahaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_hahaMouseClicked
        closecard();
        cardDoimk1.setVisible(true);
    }//GEN-LAST:event_hahaMouseClicked

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

    private void cardDoimk1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardDoimk1MouseClicked

    }//GEN-LAST:event_cardDoimk1MouseClicked

    private void lbllich1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbllich1MouseClicked
         closecard();
        cardLich.setVisible(true);
     
    }//GEN-LAST:event_lbllich1MouseClicked

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
    private javax.swing.JPanel cardDoimk;
    private javax.swing.JPanel cardDoimk1;
    private javax.swing.JPanel cardLich;
    private javax.swing.JPanel cardListhocsinh;
    private javax.swing.JPanel cardNhapdiem;
    private javax.swing.JPanel cardTaiKhoan;
    private javax.swing.JPanel cardTrangChu;
    private javax.swing.JPanel cardhelp;
    private javax.swing.JLabel haha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel jplMain;
    private javax.swing.JPanel jplSlideMenu;
    private javax.swing.JPanel jplTitle;
    private javax.swing.JPanel jpllMenuBar;
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
    // End of variables declaration//GEN-END:variables
}
