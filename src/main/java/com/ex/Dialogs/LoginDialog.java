/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Dialogs;

import javax.swing.*;
import java.awt.*;

import com.ex.Panels.LoginPanel;
import com.ex.Utilities.Config;

/**
 *
 * @author cn200
 */
public class LoginDialog extends JDialog {
    private com.ex.Frames.MainFrame parent;
    
    public LoginDialog(com.ex.Frames.MainFrame parent) {
        init();
        this.parent = parent;
    }
    
    private void init() {
        this.setSize(300, 132);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    }
    
    public void login() {
        this.setTitle("Đăng nhập");
        this.add(new LoginPanel(this));
        this.setVisible(true);
    }
    
    public void close() {
        if (Config.getStaff().getId() <= 0) {
            JOptionPane.showMessageDialog(parent, "Tên đăng nhập hoặc mật khẩu chưa đúng!", 
                    "Đăng nhập thất bại", JOptionPane.PLAIN_MESSAGE);
        }
        parent.checkLogin();
        this.dispose();
    }
}
