/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Dialogs;

import javax.swing.JDialog;
import java.awt.*;

import com.ex.Panels.*;

/**
 *
 * @author cn200
 */
public class SearchDialog extends JDialog {
    
    public static final int CUSTOMER_SEARCH = 1;
    public static final int PRESCRIPTION_SEARCH = 2;
    
    private com.ex.Frames.MainFrame parent;
    private String result = "";
    
    public SearchDialog(com.ex.Frames.MainFrame parent) {
        init();
        this.parent = parent;
    }
    
    private void init() {
        this.setSize(360, 110);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    }
    
    public String show(int type) {
        switch (type) {
            case CUSTOMER_SEARCH: {
                this.setTitle("Tra cứu thông tin khách hàng");
                break;
            }
            case PRESCRIPTION_SEARCH: {
                this.setTitle("Tra cứu đơn hàng");
            }
        }
        this.add(new SearchPanel(this, type));
        this.setVisible(true);
        return result;
    }
    
    public void close(String s, int type) {
//        parent.searchAction(s, type);
        this.result = s;
        this.dispose();
//        this.setVisible(false);
    }
}
