/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Dialogs;

import java.awt.*;
import javax.swing.*;
import java.util.List;

import com.ex.Models.Customer;
import com.ex.Panels.ResultSearchCustomerPanel;
import com.ex.Panels.MainPanel;

/**
 *
 * @author cn200
 */
public class ResultSearchCustomerDialog extends JDialog {
    
    private MainPanel parent;
    private int result;
    
    public ResultSearchCustomerDialog() {
        init();
    }
    
    public ResultSearchCustomerDialog(MainPanel parent) {
        init();
        this.parent = parent;
    }
    
    private void init() {
        this.setSize(500, 270);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    }
    
    public void selectCustomer(List<Customer> list) {
        ResultSearchCustomerPanel panel = new ResultSearchCustomerPanel(this, list);
        this.setTitle("Kết quả tìm kiếm");
        this.add(panel);
        this.setVisible(true);
    }
    
    public int selectCustomer(List<Customer> list, int ver) {
        ResultSearchCustomerPanel panel = new ResultSearchCustomerPanel(this, list, ver);
        this.setTitle("Kết quả tìm kiếm");
        this.add(panel);
        this.setVisible(true);
        return result;
    }
    
    public void close(int result) {
        parent.returnFromResultSearchCustomerDialog(result);
        this.dispose();
    }
    
    public void close(int result, int ver) {
        this.result = result;
        this.dispose();
    }
}
