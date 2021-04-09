/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Dialogs;

import javax.swing.*;
import java.awt.*;

import com.ex.Models.Prescription;
import com.ex.Panels.PresDetailPanel;

/**
 *
 * @author cn200
 */
public class PresDetailDialog extends JDialog {
    
    private com.ex.Panels.MainPanel parent;
    
    public PresDetailDialog() {
        init();
    }
    
    public PresDetailDialog(com.ex.Panels.MainPanel parent) {
        init();
        this.parent = parent;
    }
    
    private void init() {
        this.setSize(600, 450);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
//        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    }
    
    public void show(Prescription pres) {
        this.setTitle("Mã đơn hàng - M" + pres.getKey());
        this.add(new PresDetailPanel(this, pres));
        this.setVisible(true);
    }
    
    public void close() {
        this.dispose();
    }
}
