/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Panels;

import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.List;
import java.util.ArrayList;


import com.ex.Utilities.Config;
import com.ex.Utilities.MainPanelSQLHelper;
import com.ex.Models.Customer;
import com.ex.Dialogs.EditCustomerDialog;
import com.ex.Dialogs.ResultSearchCustomerDialog;
import com.ex.Utilities.SomeFunc;
import com.ex.Dialogs.PresDetailDialog;

/**
 *
 * @author cn200
 */
public class MainPanel extends javax.swing.JPanel {
    
    private MainPanelSQLHelper helper = new MainPanelSQLHelper();
    private List<Integer> tabList = new ArrayList<>();

    /**
     * Creates new form MainPanel
     */
    public MainPanel() {
        initComponents();
        
        setupHeader();
    }
    
    // Cài đặt các thành phần Header 
    private void setupHeader() {
        // Cài đặt icon
        try {
            BufferedImage image = ImageIO.read(new File(Config.getAppIcon()));
            int x = lblIcon.getMaximumSize().width;
            int y = lblIcon.getMaximumSize().height;
            int ix = image.getWidth();
            int iy = image.getHeight();

            int dx = 0;
            int dy = 0;
            if (x / y > ix / iy){
                dy = y;
                dx = dy * ix / iy;
            } else {
                dx = x;
                dy = dx * iy / ix;
            }

            ImageIcon icon = new ImageIcon(image.getScaledInstance(dx, dy, BufferedImage.SCALE_SMOOTH));
            lblIcon.setIcon(icon);
        } catch (IOException e) {
//            Logger.getLogger(ImageDemo.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println(e.toString());
        }
        
        // Cài đặt các thông tin khác 
        lblName.setText(Config.getStoreName());
        lblInf1.setText(Config.getContactInfo1());
        lblInf2.setText(Config.getContactInfo2());
    }
    
    // Lọc trước tìm kiếm
    private List<Customer> filter(String keyWord) {
        List<Customer> result = new ArrayList<>();
        
        if (keyWord.matches("^([a-zA-ZÀ-ÍÒ-ÕÙÚÝà-ãè-êìíò-õùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]\\s?)+$")) {
            result = helper.searchCustomer(keyWord.toUpperCase(), 0);
        } else if (keyWord.matches("^\\d+$")) {
            result = helper.searchCustomer(keyWord, 1);
        } else {
            System.out.println("Sai!");
        }
        
        return result;
    }
    
    // Thực hiện tìm kiếm 
    private void search(String keyWord) {
        List<Customer> result = filter(keyWord);
        
        System.out.println(result);
        
        if (result.isEmpty()) {
            int n = JOptionPane.showConfirmDialog(this, "Khách hàng chưa tồn tại!\n"
                    + "Bạn có muốn tạo khách hàng mới?", "Cảnh báo!", JOptionPane.YES_NO_OPTION);
            if (n == JOptionPane.YES_OPTION) {
                System.out.println("Tao khach hang moi!");
                EditCustomerDialog dialog = new EditCustomerDialog(this);
                dialog.addCustomer();
                edtSearch.setText("");
            }
        } else if (result.size() == 1) {
            System.out.println("Ma khach hang: " + result.get(0).getId());
            addTab(result.get(0).getId());
            edtSearch.setText("");
        } else {
            ResultSearchCustomerDialog dialog = new ResultSearchCustomerDialog(this);
            dialog.selectCustomer(result);
            edtSearch.setText("");
        }
    }
    
    // Trả về kết quả lựa chọn
    public void returnFromResultSearchCustomerDialog(int result) {
        System.out.println("Ma khach hang: " + result);
        addTab(result);
    }
    
    // Trả về kết quả tạo tài khoản mới
    public void returnFromEditCustomerDialog(int result) {
        System.out.println("Ma khach hang: " + result);
        addTab(result);
    }
    
    // Thêm tab mới
    private void addTab(int id) {
        Customer cus = helper.searchCustomer(id);
        tp.add(cus.getName(), new ScrollPanel(this, id));
        tabList.add(id);
        tp.setSelectedIndex(tp.getTabCount() - 1);
        System.out.println("Cac tab hien tai: " + tabList);
    }
    
    // Xóa tab hiện tại và hiển thị đơn hàng 
    public void deleteCurrentTab(int id) {
        int nowTab = tp.getSelectedIndex();
        if (nowTab >= 0) {
            tp.remove(nowTab);
            tabList.remove(nowTab);
        }
        System.out.println("Cac tab hien tai: " + tabList);
        
        PresDetailDialog dialog = new PresDetailDialog(this);
        dialog.show(helper.searchPrescription(id));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIcon = new javax.swing.JLabel();
        tp = new javax.swing.JTabbedPane();
        lblName = new javax.swing.JLabel();
        lblInf1 = new javax.swing.JLabel();
        lblInf2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnSearch = new javax.swing.JButton();
        edtSearch = new javax.swing.JTextField();
        btnDelete = new javax.swing.JButton();

        lblIcon.setText("Logo here");
        lblIcon.setMaximumSize(new java.awt.Dimension(72, 72));
        lblIcon.setMinimumSize(new java.awt.Dimension(72, 72));
        lblIcon.setPreferredSize(new java.awt.Dimension(72, 72));

        lblName.setFont(lblName.getFont().deriveFont((float)18));
        lblName.setText("Name");

        lblInf1.setText("Contact 1");

        lblInf2.setText("Contact 2");

        btnSearch.setText("Tìm kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        edtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtSearchKeyPressed(evt);
            }
        });

        btnDelete.setText("Xóa tab hiện tại");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDelete)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(edtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(edtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnDelete))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblInf1)
                            .addComponent(lblName)
                            .addComponent(lblInf2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 124, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tp))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblName)
                        .addGap(11, 11, 11)
                        .addComponent(lblInf1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblInf2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tp, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String keyWord = edtSearch.getText();
        search(keyWord);
    }//GEN-LAST:event_btnSearchActionPerformed

    private void edtSearchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtSearchKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String keyWord = edtSearch.getText();
            search(keyWord);
        }
    }//GEN-LAST:event_edtSearchKeyPressed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        int nowTab = tp.getSelectedIndex();
        if (nowTab >= 0) {
            tp.remove(nowTab);
            tabList.remove(nowTab);
        }
        System.out.println("Cac tab hien tai: " + tabList);
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnSearch;
    private javax.swing.JTextField edtSearch;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblIcon;
    private javax.swing.JLabel lblInf1;
    private javax.swing.JLabel lblInf2;
    private javax.swing.JLabel lblName;
    private javax.swing.JTabbedPane tp;
    // End of variables declaration//GEN-END:variables
}
