/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Panels;

import com.ex.Models.*;
import com.ex.Utilities.*;

import javax.swing.JOptionPane;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author cn200
 */
public class NewPrescriptionPanel extends javax.swing.JPanel {
    
    private MainPanel parent;
    private NewPrescriptionPanelSQLHelper helper = new NewPrescriptionPanelSQLHelper();
    private Prescription pres = new Prescription();
    private boolean isMod = true;

    /**
     * Creates new form NewPrescriptionPanel
     */
    public NewPrescriptionPanel(MainPanel parent, int id) {
        initComponents();
        this.parent = parent;
        
        pres.setCus(new Customer());
        pres.getCus().setId(id);
        
        customerInfo();
        lastPres();
    }
    
    // Cài đặt các thông tin khách hàng 
    private void customerInfo() {
        pres.setCus(helper.searchCustomer(pres.getCus().getId()));
        
        lblName.setText(pres.getCus().getName());
        lblPhoneNumber.setText(pres.getCus().getPhoneNumber());
        lblBirthday.setText(pres.getCus().getsBirthday());
        lblAddress.setText(pres.getCus().getAddress());
    }
    
    // Tìm kiếm đơn hàng gần nhất và cài đặt số đo cho đơn hàng mới 
    private void lastPres() {
        int last = helper.lastPresId(pres.getCus().getId());
        
        if (last > 0) {
            isMod = false;
            btnMod.setEnabled(true);
            
            // Tìm kiếm id của bản báo cáo cuối cùng 
            int lastReport = helper.lastReportId(last);
            
            // Tìm kiếm thông số của bản báo cáo cuối cùng 
            pres.setLastReport(helper.lastReport(lastReport));
            
            // Hiển thị số đo cho đơn hàng mới
            // Mắt trái 
            edtOldLeftSph.setText(SomeFunc.valueOf(pres.getLastReport().getLeftEye().getSph()));
            edtOldLeftCyl.setText(SomeFunc.valueOf(pres.getLastReport().getLeftEye().getCyl()));
            edtOldLeftAx.setText(SomeFunc.valueOf(pres.getLastReport().getLeftEye().getAx()));
            edtOldLeftAdd.setText(SomeFunc.valueOf(pres.getLastReport().getLeftEye().getAdd()));
            edtOldLeftVa.setText(SomeFunc.valueOf(pres.getLastReport().getLeftEye().getVa()));
            edtOldLeftFh.setText(SomeFunc.valueOf(pres.getLastReport().getLeftEye().getFh()));
            edtOldLeftPd_2.setText(SomeFunc.valueOf(pres.getLastReport().getLeftEye().getPd_2()));
            edtOldLeftPd.setText(SomeFunc.valueOf(pres.getLastReport().getLeftEye().getPd()));
            
            edtOldLeftSph.setEnabled(false);
            edtOldLeftCyl.setEnabled(false);
            edtOldLeftAx.setEnabled(false);
            edtOldLeftAdd.setEnabled(false);
            edtOldLeftVa.setEnabled(false);
            edtOldLeftFh.setEnabled(false);
            edtOldLeftPd_2.setEnabled(false);
            edtOldLeftPd.setEnabled(false);
            
            // Mắt phải 
            edtOldRightSph.setText(SomeFunc.valueOf(pres.getLastReport().getRightEye().getSph()));
            edtOldRightCyl.setText(SomeFunc.valueOf(pres.getLastReport().getRightEye().getCyl()));
            edtOldRightAx.setText(SomeFunc.valueOf(pres.getLastReport().getRightEye().getAx()));
            edtOldRightAdd.setText(SomeFunc.valueOf(pres.getLastReport().getRightEye().getAdd()));
            edtOldRightVa.setText(SomeFunc.valueOf(pres.getLastReport().getRightEye().getVa()));
            edtOldRightFh.setText(SomeFunc.valueOf(pres.getLastReport().getRightEye().getFh()));
            edtOldRightPd_2.setText(SomeFunc.valueOf(pres.getLastReport().getRightEye().getPd_2()));
            edtOldRightPd.setText(SomeFunc.valueOf(pres.getLastReport().getRightEye().getPd()));
            
            edtOldRightSph.setEnabled(false);
            edtOldRightCyl.setEnabled(false);
            edtOldRightAx.setEnabled(false);
            edtOldRightAdd.setEnabled(false);
            edtOldRightVa.setEnabled(false);
            edtOldRightFh.setEnabled(false);
            edtOldRightPd_2.setEnabled(false);
            edtOldRightPd.setEnabled(false);
            
            // Các thông số khác
            cbOldDistance.setSelected(pres.getLastReport().isDistance());
            cbOldNeutral.setSelected(pres.getLastReport().isNeutral());
            cbOldReading.setSelected(pres.getLastReport().isReading());
            
            cbOldDistance.setEnabled(false);
            cbOldNeutral.setEnabled(false);
            cbOldReading.setEnabled(false);
        } else {
            isMod = true;
            btnMod.setEnabled(false);
        }
    }
    
    // Lấy số đo cũ 
    private Report getLastReport() throws NumberFormatException{
        // Mắt trái
        float sph = SomeFunc.parseFloat(edtOldLeftSph.getText());
        float cyl = SomeFunc.parseFloat(edtOldLeftCyl.getText());
        float ax = SomeFunc.parseFloat(edtOldLeftAx.getText());
        float add = SomeFunc.parseFloat(edtOldLeftAdd.getText());
        int va = SomeFunc.parseInt(edtOldLeftVa.getText());
        int fh = SomeFunc.parseInt(edtOldLeftFh.getText());
        float pd_2 = SomeFunc.parseFloat(edtOldLeftPd_2.getText());
        float pd = SomeFunc.parseFloat(edtOldLeftPd.getText());
        
        Eye left = new Eye(0, sph, cyl, ax, add, va, fh, pd_2, pd);
        
        // Mắt phải 
        sph = SomeFunc.parseFloat(edtOldRightSph.getText());
        cyl = SomeFunc.parseFloat(edtOldRightCyl.getText());
        ax = SomeFunc.parseFloat(edtOldRightAx.getText());
        add = SomeFunc.parseFloat(edtOldRightAdd.getText());
        va = SomeFunc.parseInt(edtOldRightVa.getText());
        fh = SomeFunc.parseInt(edtOldRightFh.getText());
        pd_2 = SomeFunc.parseFloat(edtOldRightPd_2.getText());
        pd = SomeFunc.parseFloat(edtOldRightPd.getText());
        
        Eye right = new Eye(0, sph, cyl, ax, add, va, fh, pd_2, pd);
        
        // Báo cáo
        boolean distance = cbOldDistance.isSelected();
        boolean neutral = cbOldNeutral.isSelected();
        boolean reading = cbOldReading.isSelected();
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        
        return new Report(0, left, right, distance, neutral, reading, time);
    }
    
    // Lấy số đo mới 
    private Report getNowReport() throws NumberFormatException{
        // Mắt trái
        float sph = SomeFunc.parseFloat(edtNewLeftSph.getText());
        float cyl = SomeFunc.parseFloat(edtNewLeftCyl.getText());
        float ax = SomeFunc.parseFloat(edtNewLeftAx.getText());
        float add = SomeFunc.parseFloat(edtNewLeftAdd.getText());
        int va = SomeFunc.parseInt(edtNewLeftVa.getText());
        int fh = SomeFunc.parseInt(edtNewLeftFh.getText());
        float pd_2 = SomeFunc.parseFloat(edtNewLeftPd_2.getText());
        float pd = SomeFunc.parseFloat(edtNewLeftPd.getText());
        
        Eye left = new Eye(0, sph, cyl, ax, add, va, fh, pd_2, pd);
        
        // Mắt phải 
        sph = SomeFunc.parseFloat(edtNewRightSph.getText());
        cyl = SomeFunc.parseFloat(edtNewRightCyl.getText());
        ax = SomeFunc.parseFloat(edtNewRightAx.getText());
        add = SomeFunc.parseFloat(edtNewRightAdd.getText());
        va = SomeFunc.parseInt(edtNewRightVa.getText());
        fh = SomeFunc.parseInt(edtNewRightFh.getText());
        pd_2 = SomeFunc.parseFloat(edtNewRightPd_2.getText());
        pd = SomeFunc.parseFloat(edtNewRightPd.getText());
        
        Eye right = new Eye(0, sph, cyl, ax, add, va, fh, pd_2, pd);
        
        // Báo cáo
        boolean distance = cbNewDistance.isSelected();
        boolean neutral = cbNewNeutral.isSelected();
        boolean reading = cbNewReading.isSelected();
        Calendar cal = Calendar.getInstance();
        Date time = cal.getTime();
        
        return new Report(0, left, right, distance, neutral, reading, time);
    }
    
    // Lấy các thông tin khác
    private void getOtherInfo() throws NumberFormatException{
        int eyewear = 0;
        if (cbSingle.isSelected()) {
            eyewear = 1;
        } else if (cbBiofocal.isSelected()) {
            eyewear = 2;
        } else if (cbProgressive.isSelected()) {
            eyewear = 3;
        } else if (cbEyewearSet.isSelected()) {
            eyewear = 4;
        }
        
        String note = edtNote.getText();
        int reExam = SomeFunc.parseInt(edtReExam.getText(), 0);
        Date time = Calendar.getInstance().getTime();
        Staff staff = Config.getStaff();
        float total = SomeFunc.parseFloat(edtTotal.getText(), 0);
        
        pres.setEyewear(eyewear);
        pres.setNote(note);
        pres.setReExam(reExam);
        pres.setTime(time);
        pres.setStaff(staff);
        pres.setTotal(total);
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
        lblName = new javax.swing.JLabel();
        lblBirthday = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblPhoneNumber = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        btnMod = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        edtOldRightCyl = new javax.swing.JTextField();
        edtOldLeftCyl = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        edtOldRightAx = new javax.swing.JTextField();
        edtOldLeftAx = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        edtOldRightAdd = new javax.swing.JTextField();
        edtOldLeftAdd = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        edtOldRightVa = new javax.swing.JTextField();
        edtOldLeftVa = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        edtOldRightFh = new javax.swing.JTextField();
        edtOldLeftFh = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        edtOldRightPd_2 = new javax.swing.JTextField();
        edtOldLeftPd_2 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        edtOldRightPd = new javax.swing.JTextField();
        edtOldLeftPd = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbOldDistance = new javax.swing.JCheckBox();
        cbOldNeutral = new javax.swing.JCheckBox();
        cbOldReading = new javax.swing.JCheckBox();
        edtOldRightSph = new javax.swing.JTextField();
        edtOldLeftSph = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        edtNewRightCyl = new javax.swing.JTextField();
        edtNewLeftCyl = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        edtNewRightAx = new javax.swing.JTextField();
        edtNewLeftAx = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        edtNewRightAdd = new javax.swing.JTextField();
        edtNewLeftAdd = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        edtNewRightVa = new javax.swing.JTextField();
        edtNewLeftVa = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        edtNewRightFh = new javax.swing.JTextField();
        edtNewLeftFh = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        edtNewRightPd_2 = new javax.swing.JTextField();
        edtNewLeftPd_2 = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        edtNewRightPd = new javax.swing.JTextField();
        edtNewLeftPd = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        cbNewDistance = new javax.swing.JCheckBox();
        cbNewNeutral = new javax.swing.JCheckBox();
        cbNewReading = new javax.swing.JCheckBox();
        edtNewRightSph = new javax.swing.JTextField();
        edtNewLeftSph = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        cbSingle = new javax.swing.JCheckBox();
        cbBiofocal = new javax.swing.JCheckBox();
        cbProgressive = new javax.swing.JCheckBox();
        cbEyewearSet = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        edtNote = new javax.swing.JTextArea();
        jLabel33 = new javax.swing.JLabel();
        edtReExam = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jLabel35 = new javax.swing.JLabel();
        edtTotal = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();

        jLabel1.setText("Họ và tên:");

        jLabel2.setText("Ngày sinh:");

        jLabel3.setText("Địa chỉ:");

        lblName.setText("name");

        lblBirthday.setText("birthday");

        lblAddress.setText("address");

        lblPhoneNumber.setText("phone_number");

        jLabel4.setText("Số điện thoại:");

        jPanel5.setLayout(new java.awt.GridLayout(1, 0));

        jLabel9.setText("Số đo cũ:");

        btnMod.setText("Sửa");
        btnMod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModActionPerformed(evt);
            }
        });

        jLabel11.setText("Cầu:");

        jLabel20.setText("Mắt phải");

        jLabel21.setText("Mắt trái");

        jLabel12.setText("Trụ:");

        edtOldRightCyl.setPreferredSize(new java.awt.Dimension(60, 20));

        edtOldLeftCyl.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel13.setText("Trục:");

        edtOldRightAx.setPreferredSize(new java.awt.Dimension(60, 20));

        edtOldLeftAx.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel14.setText("Cộng thêm:");

        edtOldRightAdd.setPreferredSize(new java.awt.Dimension(60, 20));

        edtOldLeftAdd.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel15.setText("Thị lực:");

        edtOldRightVa.setPreferredSize(new java.awt.Dimension(60, 20));

        edtOldLeftVa.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel16.setText("Chiều cao");

        jLabel17.setText("tâm kính:");

        edtOldRightFh.setPreferredSize(new java.awt.Dimension(60, 20));

        edtOldLeftFh.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel18.setText("PD/2:");

        edtOldRightPd_2.setPreferredSize(new java.awt.Dimension(60, 20));

        edtOldLeftPd_2.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel19.setText("PD:");

        edtOldRightPd.setPreferredSize(new java.awt.Dimension(60, 20));

        edtOldLeftPd.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel5.setText("Tầm nhìn:");

        cbOldDistance.setText("Nhìn xa");

        cbOldNeutral.setText("Nhìn trung");

        cbOldReading.setText("Nhìn gần");

        edtOldRightSph.setPreferredSize(new java.awt.Dimension(60, 20));

        edtOldLeftSph.setPreferredSize(new java.awt.Dimension(60, 20));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMod))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel21))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edtOldLeftSph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel20))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edtOldRightSph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtOldLeftCyl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtOldRightCyl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtOldLeftAx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtOldRightAx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(edtOldLeftAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtOldRightAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtOldLeftVa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtOldRightVa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtOldLeftFh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtOldRightFh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtOldLeftPd_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtOldRightPd_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtOldLeftPd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtOldRightPd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbOldDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbOldReading, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbOldNeutral, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(btnMod))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(edtOldRightSph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtOldLeftSph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(edtOldRightCyl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtOldLeftCyl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(edtOldRightAx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtOldLeftAx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(edtOldRightAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtOldLeftAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(edtOldRightVa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtOldLeftVa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(edtOldRightFh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtOldLeftFh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(edtOldRightPd_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtOldLeftPd_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(edtOldRightPd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtOldLeftPd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbOldDistance))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbOldNeutral)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbOldReading)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel7);

        jLabel10.setText("Số đo hiện thời:");

        jLabel22.setText("Mắt phải");

        jLabel6.setText("Cầu:");

        jLabel23.setText("Mắt trái");

        jLabel7.setText("Trụ:");

        edtNewRightCyl.setPreferredSize(new java.awt.Dimension(60, 20));

        edtNewLeftCyl.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel8.setText("Trục:");

        edtNewRightAx.setPreferredSize(new java.awt.Dimension(60, 20));

        edtNewLeftAx.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel24.setText("Cộng thêm:");

        edtNewRightAdd.setPreferredSize(new java.awt.Dimension(60, 20));

        edtNewLeftAdd.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel25.setText("Thị lực:");

        edtNewRightVa.setPreferredSize(new java.awt.Dimension(60, 20));

        edtNewLeftVa.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel26.setText("Chiều cao");

        jLabel27.setText("tâm kính:");

        edtNewRightFh.setPreferredSize(new java.awt.Dimension(60, 20));

        edtNewLeftFh.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel28.setText("PD/2:");

        edtNewRightPd_2.setPreferredSize(new java.awt.Dimension(60, 20));

        edtNewLeftPd_2.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel29.setText("PD:");

        edtNewRightPd.setPreferredSize(new java.awt.Dimension(60, 20));

        edtNewLeftPd.setPreferredSize(new java.awt.Dimension(60, 20));

        jLabel30.setText("Tầm nhìn:");

        cbNewDistance.setText("Nhìn xa");

        cbNewNeutral.setText("Nhìn trung");

        cbNewReading.setText("Nhìn gần");

        edtNewRightSph.setPreferredSize(new java.awt.Dimension(60, 20));

        edtNewLeftSph.setPreferredSize(new java.awt.Dimension(60, 20));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel23))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edtNewLeftSph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(jLabel22))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(edtNewRightSph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtNewLeftCyl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNewRightCyl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtNewLeftAx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNewRightAx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(edtNewLeftAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNewRightAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtNewLeftVa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNewRightVa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtNewLeftFh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNewRightFh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtNewLeftPd_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNewRightPd_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(edtNewLeftPd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(edtNewRightPd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel26))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cbNewDistance, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbNewReading, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbNewNeutral, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(edtNewRightSph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtNewLeftSph, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(edtNewRightCyl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtNewLeftCyl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(edtNewRightAx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtNewLeftAx, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(edtNewRightAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtNewLeftAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(edtNewRightVa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtNewLeftVa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(edtNewRightFh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtNewLeftFh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(edtNewRightPd_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtNewLeftPd_2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(edtNewRightPd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edtNewLeftPd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(cbNewDistance))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbNewNeutral)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbNewReading)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel6);

        jLabel31.setText("Kính gọng:");

        cbSingle.setText("Đơn tròng");
        cbSingle.setPreferredSize(new java.awt.Dimension(72, 23));

        cbBiofocal.setText("Hai tròng");
        cbBiofocal.setPreferredSize(new java.awt.Dimension(72, 23));

        cbProgressive.setText("Đa tròng");
        cbProgressive.setPreferredSize(new java.awt.Dimension(72, 23));

        cbEyewearSet.setText("Mắt đặt");
        cbEyewearSet.setPreferredSize(new java.awt.Dimension(72, 23));

        jLabel32.setText("Ghi chú:");

        edtNote.setColumns(20);
        edtNote.setRows(5);
        edtNote.setPreferredSize(new java.awt.Dimension(164, 72));
        jScrollPane1.setViewportView(edtNote);

        jLabel33.setText("Khám lại sau:");

        edtReExam.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        edtReExam.setMinimumSize(new java.awt.Dimension(60, 20));

        jLabel34.setText("tháng.");

        btnSave.setText("Lưu");
        btnSave.setPreferredSize(new java.awt.Dimension(60, 23));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel35.setText("Thanh toán:");

        edtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel36.setText("VND");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 243, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPhoneNumber))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel32)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblBirthday))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAddress))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbProgressive, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbEyewearSet, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbSingle, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbBiofocal, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel33)
                                .addGap(10, 10, 10)
                                .addComponent(edtReExam, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(edtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel36)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblName)
                    .addComponent(lblPhoneNumber)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblBirthday))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(cbSingle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbBiofocal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbProgressive, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbEyewearSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel32)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(edtReExam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(edtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnModActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModActionPerformed
        // TODO add your handling code here:
        int n = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn chỉnh sửa?", 
                "Cảnh báo!", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            isMod = true;
            btnMod.setEnabled(false);
            
            edtOldLeftSph.setEnabled(true);
            edtOldLeftCyl.setEnabled(true);
            edtOldLeftAx.setEnabled(true);
            edtOldLeftAdd.setEnabled(true);
            edtOldLeftVa.setEnabled(true);
            edtOldLeftFh.setEnabled(true);
            edtOldLeftPd_2.setEnabled(true);
            edtOldLeftPd.setEnabled(true);
            
            edtOldRightSph.setEnabled(true);
            edtOldRightCyl.setEnabled(true);
            edtOldRightAx.setEnabled(true);
            edtOldRightAdd.setEnabled(true);
            edtOldRightVa.setEnabled(true);
            edtOldRightFh.setEnabled(true);
            edtOldRightPd_2.setEnabled(true);
            edtOldRightPd.setEnabled(true);
            
            cbOldDistance.setEnabled(true);
            cbOldNeutral.setEnabled(true);
            cbOldReading.setEnabled(true);
        }
    }//GEN-LAST:event_btnModActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        try {
            // Lấy số đo cũ 
            if (isMod) {
                pres.setLastReport(getLastReport());
            }

            // Lấy số đo mới 
            pres.setNowReport(getNowReport());

            // Lấy các thông tin khác 
            getOtherInfo();

            // Lưu thông tin vào csdl
            pres = helper.addNewPres(pres, isMod);
            
            parent.deleteCurrentTab(pres.getId());
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Thông số nhập chưa đúng định dạng!", 
                    "Lưu thất bại", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSaveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMod;
    private javax.swing.JButton btnSave;
    private javax.swing.JCheckBox cbBiofocal;
    private javax.swing.JCheckBox cbEyewearSet;
    private javax.swing.JCheckBox cbNewDistance;
    private javax.swing.JCheckBox cbNewNeutral;
    private javax.swing.JCheckBox cbNewReading;
    private javax.swing.JCheckBox cbOldDistance;
    private javax.swing.JCheckBox cbOldNeutral;
    private javax.swing.JCheckBox cbOldReading;
    private javax.swing.JCheckBox cbProgressive;
    private javax.swing.JCheckBox cbSingle;
    private javax.swing.JTextField edtNewLeftAdd;
    private javax.swing.JTextField edtNewLeftAx;
    private javax.swing.JTextField edtNewLeftCyl;
    private javax.swing.JTextField edtNewLeftFh;
    private javax.swing.JTextField edtNewLeftPd;
    private javax.swing.JTextField edtNewLeftPd_2;
    private javax.swing.JTextField edtNewLeftSph;
    private javax.swing.JTextField edtNewLeftVa;
    private javax.swing.JTextField edtNewRightAdd;
    private javax.swing.JTextField edtNewRightAx;
    private javax.swing.JTextField edtNewRightCyl;
    private javax.swing.JTextField edtNewRightFh;
    private javax.swing.JTextField edtNewRightPd;
    private javax.swing.JTextField edtNewRightPd_2;
    private javax.swing.JTextField edtNewRightSph;
    private javax.swing.JTextField edtNewRightVa;
    private javax.swing.JTextArea edtNote;
    private javax.swing.JTextField edtOldLeftAdd;
    private javax.swing.JTextField edtOldLeftAx;
    private javax.swing.JTextField edtOldLeftCyl;
    private javax.swing.JTextField edtOldLeftFh;
    private javax.swing.JTextField edtOldLeftPd;
    private javax.swing.JTextField edtOldLeftPd_2;
    private javax.swing.JTextField edtOldLeftSph;
    private javax.swing.JTextField edtOldLeftVa;
    private javax.swing.JTextField edtOldRightAdd;
    private javax.swing.JTextField edtOldRightAx;
    private javax.swing.JTextField edtOldRightCyl;
    private javax.swing.JTextField edtOldRightFh;
    private javax.swing.JTextField edtOldRightPd;
    private javax.swing.JTextField edtOldRightPd_2;
    private javax.swing.JTextField edtOldRightSph;
    private javax.swing.JTextField edtOldRightVa;
    private javax.swing.JTextField edtReExam;
    private javax.swing.JTextField edtTotal;
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblBirthday;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblPhoneNumber;
    // End of variables declaration//GEN-END:variables
}
