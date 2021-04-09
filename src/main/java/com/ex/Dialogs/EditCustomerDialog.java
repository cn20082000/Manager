/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Dialogs;

import javax.swing.JDialog;
import java.awt.*;


import com.ex.Panels.MainPanel;

/**
 *
 * @author cn200
 */
public class EditCustomerDialog extends JDialog {
    
    private MainPanel parent;
    
    public EditCustomerDialog(MainPanel parent) {
        init();
        this.parent = parent;
    }
    
    private void init() {
        this.setSize(400, 180);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    }
    
    public void addCustomer() {
        this.setTitle("Thêm khách hàng mới");
        this.add(new com.ex.Panels.EditCustomerPanel(this));
        this.setVisible(true);
    }
    
    public void close(int result) {
        parent.returnFromEditCustomerDialog(result);
        this.dispose();
    }
}
