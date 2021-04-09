/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

import com.ex.Panels.*;
import com.ex.Models.*;
import com.ex.Dialogs.*;
import com.ex.Utilities.*;

/**
 *
 * @author cn200
 */
public class MainFrame extends JFrame {
    
    private MainFrameSQLHelper helper = new MainFrameSQLHelper();
    private ImageIcon iconImg = new ImageIcon(Config.getAppIcon());
    private JMenuBar menuBar = new JMenuBar();
    private JMenu generalMenu = new JMenu();
    private JMenuItem searchCustomerItem, searchPrescriptionItem, loginItem, logoutItem;
    
    public MainFrame() {
        init();
    }
    
    public static void main(String args[]) {
        new MainFrame();
    }
    
    private void init() {
        // Cài đặt font mặc định 
        setUIFont (new javax.swing.plaf.FontUIResource("Arial", Font.PLAIN, 12));
        
        // Cài đặt title 
        this.setTitle(Config.getAppName());
        
        // Cài đặt icon
        this.setIconImage(iconImg.getImage());
        
        // Cài đặt kích thước
        this.setSize(1000, 600);
        
        // Cài đặt nút exit
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Cài đặt layout 
        this.setLayout(new BorderLayout());
        this.add(new MainPanel(), BorderLayout.CENTER);
        
        // Cài đặt thanh menu 
        setMenuBar();
        this.setJMenuBar(menuBar);
        
        // Cài đặt hiển thị 
        this.setVisible(true);
    }
    
    // Cài đặt các thành phần của thanh menu 
    private void setMenuBar() {
        // Menu chung
        generalMenu.setText("Chung");
        
        searchCustomerItem = new JMenuItem("Tra cứu thông tin khách hàng");
        searchPrescriptionItem = new JMenuItem("Tra cứu đơn hàng");
        loginItem = new JMenuItem("Đăng nhập");
        logoutItem = new JMenuItem("Đăng xuất");
        
        generalMenu.add(searchCustomerItem);
        generalMenu.add(searchPrescriptionItem);
        
        // Sự kiện cho các nút 
        searchCustomerItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchDialog dialog = new SearchDialog(MainFrame.this);
                String key = dialog.show(SearchDialog.CUSTOMER_SEARCH);
                
                List<Customer> result = new ArrayList<>();
            
                if (key.matches("^([a-zA-ZÀ-ÍÒ-ÕÙÚÝà-ãè-êìíò-õùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]\\s?)+$")) {
                    result = helper.searchCustomer(key.toUpperCase(), 0);
                } else if (key.matches("^\\d+$")) {
                    result = helper.searchCustomer(key, 1);
                } else {
                    System.out.println("Sai!");
                    return;
                }

                if (result.size() == 1) {
                    CusDetailDialog cusDialog = new CusDetailDialog(MainFrame.this);
                    cusDialog.show(result.get(0));
                } else if (result.size() > 1) {
                    ResultSearchCustomerDialog reDialog = new ResultSearchCustomerDialog();
                    int id = reDialog.selectCustomer(result, 1);
                    
                    if (id > 0) {
                        CusDetailDialog cusDialog = new CusDetailDialog(MainFrame.this);
                        cusDialog.show(helper.searchCustomer(id));
                    }
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "Không tìm thấy khách hàng!", 
                        "Tìm kiếm thất bại", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        });
        searchPrescriptionItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchDialog dialog = new SearchDialog(MainFrame.this);
                String key = dialog.show(SearchDialog.PRESCRIPTION_SEARCH);
                
                int result;
            
                if (key.matches("^[Mm]\\d{9}$")) {
                    result = helper.searchPrescription(key.toUpperCase());
                } else {
                    System.out.println("Sai!");
                    return;
                }

                if (result > 0) {
                    PresDetailDialog presDialog = new PresDetailDialog();
                    presDialog.show(helper.searchPrescription(result));
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "Không tìm thấy đơn hàng!", 
                        "Tìm kiếm thất bại", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginOut();
        checkLogin();
        
        // Thanh menu
        menuBar.add(generalMenu);
    }
    
    // Kiểm tra đăng nhập và sự kiện nút đăng nhập 
    public void checkLogin() {
        if (Config.getStaff().getId() > 0) {
            System.out.println("Dang nhap thanh cong, ma nv: " + Config.getStaff().getId());
            logoutItem.setText(Config.getStaff().getLastName() + " " 
                    + Config.getStaff().getFirstName());
            generalMenu.remove(loginItem);
            generalMenu.add(logoutItem);
        } else {
            generalMenu.remove(logoutItem);
            generalMenu.add(loginItem);
        }
    }
    
    // Sự kiện đăng nhập, đăng xuất hệ thống hệ thống 
    private void loginOut() {
        loginItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginDialog dialog = new LoginDialog(MainFrame.this);
                dialog.login();
            }
        });
        logoutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int n = JOptionPane.showConfirmDialog(MainFrame.this, "Bạn có chắc chắn muốn đăng xuất?", 
                        "Đăng xuất", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    Config.setStaff(new Staff());
                    checkLogin();
                }
            }
        });
    }
    
    // Cài đặt font mặc định
    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get (key);
        if (value instanceof javax.swing.plaf.FontUIResource)
            UIManager.put (key, f);
        }
    } 
    
    // sự kiện tìm kiếm 
    public void searchAction(String key, int type) {
        
        if (type == SearchDialog.CUSTOMER_SEARCH) {
            List<Customer> result = new ArrayList<>();
            
            if (key.matches("^([a-zA-ZÀ-ÍÒ-ÕÙÚÝà-ãè-êìíò-õùúýĂăĐđĨĩŨũƠơƯưẠ-ỹ]\\s?)+$")) {
                result = helper.searchCustomer(key.toUpperCase(), 0);
            } else if (key.matches("^\\d+$")) {
                result = helper.searchCustomer(key, 1);
            } else {
                System.out.println("Sai!");
            }
            
            if (result.size() == 1) {
                CusDetailDialog dialog = new CusDetailDialog(this);
                dialog.show(result.get(0));
            } else if (result.size() > 1) {
//                ResultSearchCustomerDialog dialog = new ResultSearchCustomerDialog(this);
//                dialog.selectCustomer(result);
//                edtSearch.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng!", 
                    "Tìm kiếm thất bại", JOptionPane.ERROR_MESSAGE);
            }
        } else if (type == SearchDialog.PRESCRIPTION_SEARCH) {
            
        }
    }
    
    // hiển thị thông tin 
}
