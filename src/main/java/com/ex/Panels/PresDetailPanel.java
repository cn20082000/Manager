/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Panels;

import javax.swing.table.DefaultTableModel;
import java.util.Calendar;
import javax.swing.JTable;

import com.ex.Models.Prescription;
import com.ex.Utilities.SomeFunc;

/**
 *
 * @author cn200
 */
public class PresDetailPanel extends javax.swing.JPanel {
    
    private com.ex.Dialogs.PresDetailDialog parent;
    private Prescription pres;
    private DefaultTableModel tm;

    /**
     * Creates new form PresDetailPanel
     */
    public PresDetailPanel(com.ex.Dialogs.PresDetailDialog parent, Prescription pres) {
        initComponents();
        this.parent = parent;
        this.pres = pres;
        
        // Bảng 
        String[] cols = {"", "Mắt trái (cũ)", "Mắt phải (cũ)", "Mắt trái (mới)", "Mắt phải (mới)"};
        tm = new DefaultTableModel(cols, 0);
        tb.setModel(tm);
        tb.getColumnModel().getColumn(0).setPreferredWidth(180);
//        tb.getColumnModel().getColumn(1).setPreferredWidth(80);
//        tb.getColumnModel().getColumn(2).setPreferredWidth(80);
//        tb.getColumnModel().getColumn(3).setPreferredWidth(80);
//        tb.getColumnModel().getColumn(4).setPreferredWidth(80);
        tb.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        
        // Khởi tạo thông tin 
        init();
    }
    
    private void init() {
        // Thông tin cơ bản
        lblName.setText(pres.getCus().getName());
        lblBirthday.setText(pres.getCus().getsBirthday());
        lblAddress.setText(pres.getCus().getAddress());
        lblPhoneNumber.setText(pres.getCus().getPhoneNumber());
        lblTime.setText(pres.getsTime(true, true));
        
        // Bảng 
        tm.addRow(new Object[] {"Cầu", SomeFunc.valueOf(pres.getLastReport().getLeftEye().getSph()), 
            SomeFunc.valueOf(pres.getLastReport().getRightEye().getSph()), 
            SomeFunc.valueOf(pres.getNowReport().getLeftEye().getSph()), 
            SomeFunc.valueOf(pres.getNowReport().getRightEye().getSph())});
        tm.addRow(new Object[] {"Trụ", SomeFunc.valueOf(pres.getLastReport().getLeftEye().getCyl()), 
            SomeFunc.valueOf(pres.getLastReport().getRightEye().getCyl()), 
            SomeFunc.valueOf(pres.getNowReport().getLeftEye().getCyl()), 
            SomeFunc.valueOf(pres.getNowReport().getRightEye().getCyl())});
        tm.addRow(new Object[] {"Trục", SomeFunc.valueOf(pres.getLastReport().getLeftEye().getAx()), 
            SomeFunc.valueOf(pres.getLastReport().getRightEye().getAx()), 
            SomeFunc.valueOf(pres.getNowReport().getLeftEye().getAx()), 
            SomeFunc.valueOf(pres.getNowReport().getRightEye().getAx())});
        tm.addRow(new Object[] {"Cộng thêm", SomeFunc.valueOf(pres.getLastReport().getLeftEye().getAdd()), 
            SomeFunc.valueOf(pres.getLastReport().getRightEye().getAdd()), 
            SomeFunc.valueOf(pres.getNowReport().getLeftEye().getAdd()), 
            SomeFunc.valueOf(pres.getNowReport().getRightEye().getAdd())});
        tm.addRow(new Object[] {"Thị lực", SomeFunc.valueOf(pres.getLastReport().getLeftEye().getVa()), 
            SomeFunc.valueOf(pres.getLastReport().getRightEye().getVa()), 
            SomeFunc.valueOf(pres.getNowReport().getLeftEye().getVa()), 
            SomeFunc.valueOf(pres.getNowReport().getRightEye().getVa())});
        tm.addRow(new Object[] {"Chiều cao tâm kính", SomeFunc.valueOf(pres.getLastReport().getLeftEye().getFh()), 
            SomeFunc.valueOf(pres.getLastReport().getRightEye().getFh()), 
            SomeFunc.valueOf(pres.getNowReport().getLeftEye().getFh()), 
            SomeFunc.valueOf(pres.getNowReport().getRightEye().getFh())});
        tm.addRow(new Object[] {"PD/2", SomeFunc.valueOf(pres.getLastReport().getLeftEye().getPd_2()), 
            SomeFunc.valueOf(pres.getLastReport().getRightEye().getPd_2()), 
            SomeFunc.valueOf(pres.getNowReport().getLeftEye().getPd_2()), 
            SomeFunc.valueOf(pres.getNowReport().getRightEye().getPd_2())});
        tm.addRow(new Object[] {"PD", SomeFunc.valueOf(pres.getLastReport().getLeftEye().getPd()), 
            SomeFunc.valueOf(pres.getLastReport().getRightEye().getPd()), 
            SomeFunc.valueOf(pres.getNowReport().getLeftEye().getPd()), 
            SomeFunc.valueOf(pres.getNowReport().getRightEye().getPd())});
        
        // Tầm nhìn 
        cbOldDistance.setSelected(pres.getLastReport().isDistance());
        cbOldNeutral.setSelected(pres.getLastReport().isNeutral());
        cbOldReading.setSelected(pres.getLastReport().isReading());
        cbNewDistance.setSelected(pres.getNowReport().isDistance());
        cbNewNeutral.setSelected(pres.getNowReport().isNeutral());
        cbNewReading.setSelected(pres.getNowReport().isReading());
        
        // Các thông tin khác
        switch (pres.getEyewear()) {
            case 1: {
                lblEyewear.setText("Đơn tròng");
                break;
            }
            case 2: {
                lblEyewear.setText("Hai tròng");
                break;
            }
            case 3: {
                lblEyewear.setText("Đa tròng");
                break;
            }
            case 4: {
                lblEyewear.setText("Mắt đặt");
                break;
            }
        }
        lblNote.setText(pres.getNote());
        lblReExam.setText(SomeFunc.valueOf(pres.getReExam()));
        lblTotal.setText(SomeFunc.valueOf(pres.getTotal()));
        lblStaff.setText(pres.getStaff().getSignature());
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
        lblName = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblBirthday = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        lblAddress = new javax.swing.JLabel();
        lblPhoneNumber = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbOldDistance = new javax.swing.JCheckBox();
        cbNewDistance = new javax.swing.JCheckBox();
        cbOldNeutral = new javax.swing.JCheckBox();
        cbNewNeutral = new javax.swing.JCheckBox();
        cbOldReading = new javax.swing.JCheckBox();
        cbNewReading = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        lblEyewear = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lblNote = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lblReExam = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lblStaff = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jLabel1.setText("Họ và tên:");

        lblName.setText("name");

        jLabel3.setText("Ngày sinh:");

        lblBirthday.setText("birthday");

        jLabel2.setText("Địa chỉ:");

        lblAddress.setText("address");

        lblPhoneNumber.setText("phone number");

        jLabel5.setText("Số điện thoại:");

        tb.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb.setMinimumSize(new java.awt.Dimension(60, 132));
        jScrollPane1.setViewportView(tb);
        if (tb.getColumnModel().getColumnCount() > 0) {
            tb.getColumnModel().getColumn(0).setPreferredWidth(180);
        }

        jLabel4.setText("Tầm nhìn (cũ): ");

        jLabel6.setText("Tầm nhìn (mới):");

        cbOldDistance.setText("Nhìn xa");
        cbOldDistance.setEnabled(false);

        cbNewDistance.setText("Nhìn xa");
        cbNewDistance.setEnabled(false);

        cbOldNeutral.setText("Nhìn trung");
        cbOldNeutral.setEnabled(false);

        cbNewNeutral.setText("Nhìn trung");
        cbNewNeutral.setEnabled(false);

        cbOldReading.setText("Nhìn gần");
        cbOldReading.setEnabled(false);

        cbNewReading.setText("Nhìn gần");
        cbNewReading.setEnabled(false);

        jLabel7.setText("Kính gọng:");

        lblEyewear.setText("eyewear");

        jLabel9.setText("Ghi chú:");

        lblNote.setText("note");

        jLabel11.setText("Khám lại sau:");

        lblReExam.setText("re-exam");

        jLabel13.setText("tháng");

        jLabel14.setText("Tổng số tiền:");

        lblTotal.setText("total");

        lblTime.setText("time");

        jLabel17.setText("Thời gian:");

        jLabel18.setText("Bác sỹ khám:");

        lblStaff.setText("staff");

        jButton1.setText("In");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblPhoneNumber))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBirthday)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTime))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblAddress))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbOldDistance)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbOldNeutral)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbOldReading))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbNewDistance)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbNewNeutral)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbNewReading))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblEyewear))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblNote))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblReExam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotal))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblStaff)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblBirthday)
                    .addComponent(lblTime)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(lblAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbOldDistance)
                    .addComponent(cbOldNeutral)
                    .addComponent(cbOldReading))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbNewDistance)
                    .addComponent(cbNewNeutral)
                    .addComponent(cbNewReading))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblEyewear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(lblNote))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblReExam)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(lblTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lblStaff))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbNewDistance;
    private javax.swing.JCheckBox cbNewNeutral;
    private javax.swing.JCheckBox cbNewReading;
    private javax.swing.JCheckBox cbOldDistance;
    private javax.swing.JCheckBox cbOldNeutral;
    private javax.swing.JCheckBox cbOldReading;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblBirthday;
    private javax.swing.JLabel lblEyewear;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNote;
    private javax.swing.JLabel lblPhoneNumber;
    private javax.swing.JLabel lblReExam;
    private javax.swing.JLabel lblStaff;
    private javax.swing.JLabel lblTime;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JTable tb;
    // End of variables declaration//GEN-END:variables
}
