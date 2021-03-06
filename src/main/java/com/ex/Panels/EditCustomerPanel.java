/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Panels;

import javax.swing.*;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.*;

import com.ex.Dialogs.EditCustomerDialog;
import com.ex.Models.Customer;
import com.ex.Utilities.EditCustomerPanelSQLHelper;
import com.ex.Utilities.Cons;
import com.ex.Utilities.SomeFunc;

/**
 *
 * @author cn200
 */
public class EditCustomerPanel extends javax.swing.JPanel {

    private EditCustomerDialog parent;
    private EditCustomerPanelSQLHelper helper = new EditCustomerPanelSQLHelper();
    
    /**
     * Creates new form EditCustomerPanel
     */
    public EditCustomerPanel(EditCustomerDialog parent) {
        this.parent = parent;
        initComponents();
    }
    
    private int confirmActionResult() {
        // Cắt họ và tên ra thành họ, tên 
        String firstName, lastName;
        if (edtName.getText().matches("^([a-zA-ZÀ-ÍÒ-ÕÙÚÝà-ãè-êìíò-õùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]\\s?)+$")) {
            String name = edtName.getText().trim();
            int k;
            for (k = name.length() - 1; k > 0; k--) {
                if (name.charAt(k) == ' ') {
                    break;
                }
            }
            if (k > 0) {
                firstName = name.substring(k + 1);
                lastName = name.substring(0, k);
            } else {
                firstName = name;
                lastName = "";
            }
            firstName = firstName.toUpperCase();
            lastName = lastName.toUpperCase();
        } else {
            return Cons.ERROR_NAME_FIELD;
        }
        
        // Địa chỉ 
        String address = edtAddress.getText();
        
        // Ngày sinh
        Date birthday;
        try {
            int day = SomeFunc.parseInt(edtDay.getText(), 1);
            int month = SomeFunc.parseInt(edtMonth.getText(), 1);
            int year = SomeFunc.parseInt(edtYear.getText(), 1900);
            Calendar cal = Calendar.getInstance();
            cal.set(year, month - 1, day);
            birthday = cal.getTime();
            System.out.println(cal.toString());
        } catch (Exception e) {
            System.out.println(e.toString());
            return Cons.ERROR_BIRTHDAY_FIELD;
        }
        
        // Số điện thoại
        String phoneNumber;
        if (edtPhoneNumber.getText().matches("^\\d+$")) {
            phoneNumber = edtPhoneNumber.getText();
        } else {
            return Cons.ERROR_PHONE_NUMBER_FIELD;
        }
        
        Customer cus = new Customer(0, firstName, lastName, address, birthday, phoneNumber);
        
        return helper.addCustomer(cus);
    }
    
    private void confirmAction() {
        int result = confirmActionResult();
        switch (result) {
            case Cons.ERROR_NAME_FIELD: {
                JOptionPane.showMessageDialog(parent, "Sai định dạng tên!", 
                        "Thêm khách hàng mới thất bại", JOptionPane.PLAIN_MESSAGE);
                edtName.requestFocusInWindow();
                break;
            }
            case Cons.ERROR_BIRTHDAY_FIELD: {
                JOptionPane.showMessageDialog(parent, "Sai định dạng ngày sinh!", 
                        "Thêm khách hàng mới thất bại", JOptionPane.PLAIN_MESSAGE);
                edtDay.requestFocusInWindow();
                break;
            }
            case Cons.ERROR_PHONE_NUMBER_FIELD: {
                JOptionPane.showMessageDialog(parent, "Sai định dạng số điện thoại!", 
                        "Thêm khách hàng mới thất bại", JOptionPane.PLAIN_MESSAGE);
                edtPhoneNumber.requestFocusInWindow();
                break;
            }
            default: {
                JOptionPane.showMessageDialog(parent, "Thêm khách hàng mới thành công!", 
                        "Thành công", JOptionPane.PLAIN_MESSAGE);
                parent.close(result);
            }
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        edtName = new javax.swing.JTextField();
        edtAddress = new javax.swing.JTextField();
        edtPhoneNumber = new javax.swing.JTextField();
        edtDay = new javax.swing.JTextField();
        btnConfirm = new javax.swing.JButton();
        edtMonth = new javax.swing.JTextField();
        edtYear = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel1.setText("Họ và tên:");

        jLabel2.setText("Địa chỉ:");

        jLabel3.setText("Ngày sinh:");

        jLabel4.setText("Số điện thoại:");

        edtName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtNameKeyPressed(evt);
            }
        });

        edtAddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtAddressKeyPressed(evt);
            }
        });

        edtPhoneNumber.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtPhoneNumberKeyPressed(evt);
            }
        });

        edtDay.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtDayKeyPressed(evt);
            }
        });

        btnConfirm.setText("Xác nhận");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        edtMonth.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtMonthKeyPressed(evt);
            }
        });

        edtYear.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                edtYearKeyPressed(evt);
            }
        });

        jLabel5.setText("/");

        jLabel6.setText("/");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(edtName)
                            .addComponent(edtAddress)
                            .addComponent(edtPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(edtDay, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edtYear))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnConfirm)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(edtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(edtAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(edtDay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(edtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfirm)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
        
        confirmAction();
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void edtNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtNameKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            edtAddress.requestFocusInWindow();
        }
    }//GEN-LAST:event_edtNameKeyPressed

    private void edtAddressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtAddressKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            edtDay.requestFocusInWindow();
        }
    }//GEN-LAST:event_edtAddressKeyPressed

    private void edtDayKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtDayKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            edtMonth.requestFocusInWindow();
        }
    }//GEN-LAST:event_edtDayKeyPressed

    private void edtMonthKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtMonthKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            edtYear.requestFocusInWindow();
        }
    }//GEN-LAST:event_edtMonthKeyPressed

    private void edtYearKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtYearKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            edtPhoneNumber.requestFocusInWindow();
        }
    }//GEN-LAST:event_edtYearKeyPressed

    private void edtPhoneNumberKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edtPhoneNumberKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            confirmAction();
        }
    }//GEN-LAST:event_edtPhoneNumberKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirm;
    private javax.swing.JTextField edtAddress;
    private javax.swing.JTextField edtDay;
    private javax.swing.JTextField edtMonth;
    private javax.swing.JTextField edtName;
    private javax.swing.JTextField edtPhoneNumber;
    private javax.swing.JTextField edtYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    // End of variables declaration//GEN-END:variables
}
