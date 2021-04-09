/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Utilities;

import com.ex.Models.*;
import static com.ex.Utilities.MainPanelSQLHelper.TYPE_NAME;
import static com.ex.Utilities.MainPanelSQLHelper.TYPE_PHONE_NUMBER;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cn200
 */
public class MainFrameSQLHelper extends SQLHelper {
    public MainFrameSQLHelper() {
        super();
    }
    
    public Prescription searchPrescription(int id) {
        Prescription pres = new Prescription();
        pres.setId(id);
        
        open();
        
        String presSql = "SELECT last_report, current_report, eyewear, note, re_exam, "
                + "time, staff, customer, total, key FROM TABLE_PRESCRIPTION WHERE ID = " + pres.getId();
        
        try {
            PreparedStatement statement = conn.prepareStatement(presSql);
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                pres.setLastReport(new Report());
                pres.getLastReport().setId(rs.getInt(1));
                pres.setNowReport(new Report());
                pres.getNowReport().setId(rs.getInt(2));
                pres.setEyewear(rs.getInt(3));
                pres.setNote(rs.getString(4));
                pres.setReExam(rs.getInt(5));
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(rs.getTime(6));
                pres.setTime(new Date(cal.getTime().getTime() + rs.getDate(6).getTime() + 25200000));
                
                pres.setStaff(new Staff());
                pres.getStaff().setId(rs.getInt(7));
                pres.setCus(new Customer());
                pres.getCus().setId(rs.getInt(8));
                pres.setTotal(rs.getFloat(9));
                pres.setKey(rs.getInt(10));
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        
        pres.setLastReport(searchReport(pres.getLastReport().getId()));
        pres.setNowReport(searchReport(pres.getNowReport().getId()));
        pres.setStaff(searchStaff(pres.getStaff().getId()));
        pres.setCus(searchCustomer(pres.getCus().getId()));
        return pres;
    }
    
    public Report searchReport(int id) {
        Report report = new Report();
        report.setId(id);
        
        open();
        
        String reportSql = "SELECT left_eye, right_eye, distance, neutral, reading "
                + "FROM TABLE_REPORT WHERE ID = " + report.getId();
        
        try {
            PreparedStatement statement = conn.prepareStatement(reportSql);
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                report.setLeftEye(new Eye());
                report.getLeftEye().setId(rs.getInt(1));
                report.setRightEye(new Eye());
                report.getRightEye().setId(rs.getInt(2));
                report.setDistance(rs.getBoolean(3));
                report.setNeutral(rs.getBoolean(4));
                report.setReading(rs.getBoolean(5));
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        report.setLeftEye(searchEye(report.getLeftEye().getId()));
        report.setRightEye(searchEye(report.getRightEye().getId()));
        return report;
    }
    
    public Eye searchEye(int id) {
        Eye eye = new Eye();
        eye.setId(id);
        
        open();
        
        String eyeSql = "SELECT sph, cyl, ax, add, va, fh, pd_2, pd "
                + "FROM TABLE_EYE WHERE ID = " + eye.getId();
        
        try {
            PreparedStatement statement = conn.prepareStatement(eyeSql);
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                eye.setSph(rs.getFloat(1));
                eye.setCyl(rs.getFloat(2));
                eye.setAx(rs.getFloat(3));
                eye.setAdd(rs.getFloat(4));
                eye.setVa(rs.getInt(5));
                eye.setFh(rs.getInt(6));
                eye.setPd_2(rs.getFloat(7));
                eye.setPd(rs.getFloat(8));
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        return eye;
    }
    
    public Staff searchStaff(int id) {
        if (id <= 0) {
            return new Staff(0, "không họ", "không tên", "không đăng nhập");
        }
        Staff staff = new Staff();
        staff.setId(id);
        
        open();
        
        String staffSql = "SELECT first_name, last_name, signature "
                + "FROM TABLE_STAFF WHERE ID = " + staff.getId();
        
        try {
            PreparedStatement statement = conn.prepareStatement(staffSql);
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                staff.setFirstName(rs.getString(1));
                staff.setLastName(rs.getString(2));
                staff.setSignature(rs.getString(3));
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        return staff;
    }
    
    public Customer searchCustomer(int id) {
        Customer cus = new Customer();
        cus.setId(id);
        
        open();
        
        String sql = "SELECT TABLE_CUSTOMER.first_name, TABLE_CUSTOMER.last_name, "
                + "TABLE_CUSTOMER.address, TABLE_CUSTOMER.birthday, TABLE_CUSTOMER.phone_number "
                + "FROM TABLE_CUSTOMER WHERE TABLE_CUSTOMER.ID = " + id;
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                cus.setFirstName(rs.getString(1));
                cus.setLastName(rs.getString(2));
                cus.setAddress(rs.getString(3));
                cus.setBirthday(new Date(rs.getDate(4).getTime()));
                cus.setPhoneNumber(rs.getString(5));
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        return cus;
    }
    
    public List<Customer> searchCustomer(String keyWord, int type) {
        List<Customer> result = new ArrayList<>();
        
        open();
        System.out.println(keyWord + ", " + type);
        
        if (type == TYPE_NAME) {
            String sql = "SELECT TABLE_CUSTOMER.ID, TABLE_CUSTOMER.first_name, "
                    + "TABLE_CUSTOMER.last_name, TABLE_CUSTOMER.address, "
                    + "TABLE_CUSTOMER.birthday, TABLE_CUSTOMER.phone_number "
                    + "FROM TABLE_CUSTOMER, (SELECT * FROM (SELECT TABLE_CUSTOMER.ID AS ID2, "
                    + "TABLE_CUSTOMER.last_name & ' ' & TABLE_CUSTOMER.first_name "
                    + "AS name FROM TABLE_CUSTOMER) AS TABLE_CUSTOMER_2 WHERE "
                    + "TABLE_CUSTOMER_2.name LIKE '*" + keyWord + "*') AS TABLE_CUSTOMER_3 "
                    + "WHERE TABLE_CUSTOMER.ID = TABLE_CUSTOMER_3.ID2";
            
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
//                statement.setString(0, keyWord);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String firstName = rs.getString(2);
                    String lastName = rs.getString(3);
                    String address = rs.getString(4);
                    Date birthday = new Date(rs.getDate(5).getTime());
                    String phoneNumber = rs.getString(6);
                    
                    result.add(new Customer(id, firstName, lastName, address, birthday, phoneNumber));
                }
                
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MainPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (type == TYPE_PHONE_NUMBER) {
            String sql = "SELECT TABLE_CUSTOMER.ID, TABLE_CUSTOMER.first_name, "
                    + "TABLE_CUSTOMER.last_name, TABLE_CUSTOMER.address, "
                    + "TABLE_CUSTOMER.birthday, TABLE_CUSTOMER.phone_number "
                    + "FROM TABLE_CUSTOMER WHERE phone_number "
                    + "LIKE '*" + keyWord + "*%'";
            
            try {
                PreparedStatement statement = conn.prepareStatement(sql);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String firstName = rs.getString(2);
                    String lastName = rs.getString(3);
                    String address = rs.getString(4);
                    Date birthday = new Date(rs.getDate(5).getTime());
                    String phoneNumber = rs.getString(6);
                    
                    result.add(new Customer(id, firstName, lastName, address, birthday, phoneNumber));
                }
                
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(MainPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        close();
        return result;
    }
    
    public int searchPrescription(String key) {
        int id = 0;
        int keyInt = Integer.parseInt(key.substring(1));
        
        open();
        
        String presSql = "SELECT ID FROM TABLE_PRESCRIPTION WHERE key = " + keyInt;
        
        try {
            PreparedStatement statement = conn.prepareStatement(presSql);
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        
        return id;
    }
}
