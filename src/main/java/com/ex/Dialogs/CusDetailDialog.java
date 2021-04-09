/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Dialogs;

import javax.swing.*;
import java.awt.*;
import com.ex.Models.*;
import com.ex.Panels.CusDetailPanel;

/**
 *
 * @author cn200
 */
public class CusDetailDialog extends JDialog {
    private com.ex.Frames.MainFrame parent;
    private com.ex.Utilities.MainFrameSQLHelper helper = new com.ex.Utilities.MainFrameSQLHelper();
    
    public CusDetailDialog(com.ex.Frames.MainFrame parent) {
        init();
        this.parent = parent;
    }
    
    private void init() {
        this.setSize(600, 450);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
//        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    }
    
    public void show(Customer cus) {
        this.setTitle("Khách hàng - " + cus.getName());
        this.add(new CusDetailPanel(this, cus));
        this.setVisible(true);
    }
    
    public void close() {
        this.dispose();
    }
    
    public void open(int id) {
        PresDetailDialog dialog = new PresDetailDialog();
        dialog.show(helper.searchPrescription(id));
    }
}
