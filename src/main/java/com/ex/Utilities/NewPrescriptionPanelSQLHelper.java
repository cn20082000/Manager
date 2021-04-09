/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Utilities;

import com.ex.Models.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cn200
 */
public class NewPrescriptionPanelSQLHelper extends SQLHelper {
    public NewPrescriptionPanelSQLHelper() {
        super();
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
            
            while (rs.next()) {
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
    
    public int lastPresId(int cusId) {
        int result = 0;
        
        open();
        
        String sql = "SELECT MAX(ID) FROM TABLE_PRESCRIPTION WHERE customer = " + cusId;
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                result = rs.getInt(1);
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Ma don han cuoi cung: " + result);
        close();
        return result;
    }
    
    public int lastReportId(int presId) {
        int result = -1;
        
        open();
        
        String sql = "SELECT current_report FROM TABLE_PRESCRIPTION WHERE ID = " + presId;
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                result = rs.getInt(1);
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        return result;
    }
    
    public Report lastReport(int reportId) {
        Report result = new Report();
        result.setId(reportId);
        result.setLeftEye(new Eye());
        result.setRightEye(new Eye());
        
        open();
        
        String sql = "SELECT left_eye, right_eye, distance, neutral, reading, time "
                + "FROM TABLE_REPORT WHERE ID = " + reportId;
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                int leftEye = rs.getInt(1);
                int rightEye = rs.getInt(2);
                boolean distance = rs.getBoolean(3);
                boolean neutral = rs.getBoolean(4);
                boolean reading = rs.getBoolean(5);
                Date time = new Date(rs.getDate(6).getTime());
                
                result.getLeftEye().setId(leftEye);
                result.getRightEye().setId(rightEye);
                result.setDistance(distance);
                result.setNeutral(neutral);
                result.setReading(reading);
                result.setTime(time);
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql2 = "SELECT sph, cyl, ax, add, va, fh, pd_2, pd FROM "
                + "TABLE_EYE WHERE ID = ";
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql2 + result.getLeftEye().getId());
            PreparedStatement statement2 = conn.prepareStatement(sql2 + result.getRightEye().getId());
            
            // Mắt trái
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                float sph = rs.getFloat(1);
                float cyl = rs.getFloat(2);
                float ax = rs.getFloat(3);
                float add = rs.getFloat(4);
                int va = rs.getInt(5);
                int fh = rs.getInt(6);
                float pd_2 = rs.getFloat(7);
                float pd = rs.getFloat(8);
                
                result.getLeftEye().setSph(sph);
                result.getLeftEye().setCyl(cyl);
                result.getLeftEye().setAx(ax);
                result.getLeftEye().setAdd(add);
                result.getLeftEye().setVa(va);
                result.getLeftEye().setFh(fh);
                result.getLeftEye().setPd_2(pd_2);
                result.getLeftEye().setPd(pd);
            }
            
            statement.close();
            
            // Mắt phải 
            rs = statement2.executeQuery();
            
            while (rs.next()) {
                float sph = rs.getFloat(1);
                float cyl = rs.getFloat(2);
                float ax = rs.getFloat(3);
                float add = rs.getFloat(4);
                int va = rs.getInt(5);
                int fh = rs.getInt(6);
                float pd_2 = rs.getFloat(7);
                float pd = rs.getFloat(8);
                
                result.getRightEye().setSph(sph);
                result.getRightEye().setCyl(cyl);
                result.getRightEye().setAx(ax);
                result.getRightEye().setAdd(add);
                result.getRightEye().setVa(va);
                result.getRightEye().setFh(fh);
                result.getRightEye().setPd_2(pd_2);
                result.getRightEye().setPd(pd);
            }
            
            statement2.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        return result;
    }
    
    public Prescription addNewPres(Prescription pres, boolean isMod) {
        open();
        
        if (isMod) {
            // Thêm báo cáo cũ 
            String eyeSql = "INSERT INTO TABLE_EYE (sph, cyl, ax, add, va, fh, pd_2, pd) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            String idSql = "SELECT MAX(ID) FROM TABLE_EYE";
            String reportSql = "INSERT INTO TABLE_REPORT(left_eye, right_eye, distance, "
                    + "neutral, reading, time) VALUES (?, ?, ?, ?, ?, ?)";
            String idSql2 = "SELECT MAX(ID) FROM TABLE_REPORT";
            
            // Mắt trái 
            try {
                PreparedStatement statement = conn.prepareStatement(eyeSql);
                statement.setFloat(1, pres.getLastReport().getLeftEye().getSph());
                statement.setFloat(2, pres.getLastReport().getLeftEye().getCyl());
                statement.setFloat(3, pres.getLastReport().getLeftEye().getAx());
                statement.setFloat(4, pres.getLastReport().getLeftEye().getAdd());
                statement.setInt(5, pres.getLastReport().getLeftEye().getVa());
                statement.setInt(6, pres.getLastReport().getLeftEye().getFh());
                statement.setFloat(7, pres.getLastReport().getLeftEye().getPd_2());
                statement.setFloat(8, pres.getLastReport().getLeftEye().getPd());
                
                int r = statement.executeUpdate();
                
                if (r > 0) {
                    System.out.println("Insert left eye, last report success");
                }
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                PreparedStatement statement = conn.prepareStatement(idSql);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()) {
                    pres.getLastReport().getLeftEye().setId(rs.getInt(1));
                }
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Mắt phải 
            try {
                PreparedStatement statement = conn.prepareStatement(eyeSql);
                statement.setFloat(1, pres.getLastReport().getRightEye().getSph());
                statement.setFloat(2, pres.getLastReport().getRightEye().getCyl());
                statement.setFloat(3, pres.getLastReport().getRightEye().getAx());
                statement.setFloat(4, pres.getLastReport().getRightEye().getAdd());
                statement.setInt(5, pres.getLastReport().getRightEye().getVa());
                statement.setInt(6, pres.getLastReport().getRightEye().getFh());
                statement.setFloat(7, pres.getLastReport().getRightEye().getPd_2());
                statement.setFloat(8, pres.getLastReport().getRightEye().getPd());
                
                int r = statement.executeUpdate();
                
                if (r > 0) {
                    System.out.println("Insert right eye, last report success");
                }
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                PreparedStatement statement = conn.prepareStatement(idSql);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()) {
                    pres.getLastReport().getRightEye().setId(rs.getInt(1));
                }
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Báo cáo cũ 
            try {
                PreparedStatement statement = conn.prepareStatement(reportSql);
                statement.setInt(1, pres.getLastReport().getLeftEye().getId());
                statement.setInt(2, pres.getLastReport().getRightEye().getId());
                statement.setBoolean(3, pres.getLastReport().isDistance());
                statement.setBoolean(4, pres.getLastReport().isNeutral());
                statement.setBoolean(5, pres.getLastReport().isReading());
                statement.setDate(6, new java.sql.Date(pres.getLastReport().getTime().getTime()));
                
                int r = statement.executeUpdate();
                
                if (r > 0) {
                    System.out.println("Insert last report success");
                }
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            try {
                PreparedStatement statement = conn.prepareStatement(idSql2);
                
                ResultSet rs = statement.executeQuery();
                
                while (rs.next()) {
                    pres.getLastReport().setId(rs.getInt(1));
                }
                statement.close();
            } catch (SQLException ex) {
                Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Thêm báo cáo mới 
        String eyeSql = "INSERT INTO TABLE_EYE (sph, cyl, ax, add, va, fh, pd_2, pd) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String idSql = "SELECT MAX(ID) FROM TABLE_EYE";
        String reportSql = "INSERT INTO TABLE_REPORT(left_eye, right_eye, distance, "
                + "neutral, reading, time) VALUES (?, ?, ?, ?, ?, ?)";
        String idSql2 = "SELECT MAX(ID) FROM TABLE_REPORT";

        // Mắt trái 
        try {
            PreparedStatement statement = conn.prepareStatement(eyeSql);
            statement.setFloat(1, pres.getNowReport().getLeftEye().getSph());
            statement.setFloat(2, pres.getNowReport().getLeftEye().getCyl());
            statement.setFloat(3, pres.getNowReport().getLeftEye().getAx());
            statement.setFloat(4, pres.getNowReport().getLeftEye().getAdd());
            statement.setInt(5, pres.getNowReport().getLeftEye().getVa());
            statement.setInt(6, pres.getNowReport().getLeftEye().getFh());
            statement.setFloat(7, pres.getNowReport().getLeftEye().getPd_2());
            statement.setFloat(8, pres.getNowReport().getLeftEye().getPd());

            int r = statement.executeUpdate();

            if (r > 0) {
                System.out.println("Insert left eye, now report success");
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            PreparedStatement statement = conn.prepareStatement(idSql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                pres.getNowReport().getLeftEye().setId(rs.getInt(1));
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Mắt phải 
        try {
            PreparedStatement statement = conn.prepareStatement(eyeSql);
            statement.setFloat(1, pres.getNowReport().getRightEye().getSph());
            statement.setFloat(2, pres.getNowReport().getRightEye().getCyl());
            statement.setFloat(3, pres.getNowReport().getRightEye().getAx());
            statement.setFloat(4, pres.getNowReport().getRightEye().getAdd());
            statement.setInt(5, pres.getNowReport().getRightEye().getVa());
            statement.setInt(6, pres.getNowReport().getRightEye().getFh());
            statement.setFloat(7, pres.getNowReport().getRightEye().getPd_2());
            statement.setFloat(8, pres.getNowReport().getRightEye().getPd());

            int r = statement.executeUpdate();

            if (r > 0) {
                System.out.println("Insert right eye, now report success");
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            PreparedStatement statement = conn.prepareStatement(idSql);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                pres.getNowReport().getRightEye().setId(rs.getInt(1));
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Báo cáo mới 
        try {
            PreparedStatement statement = conn.prepareStatement(reportSql);
            statement.setInt(1, pres.getNowReport().getLeftEye().getId());
            statement.setInt(2, pres.getNowReport().getRightEye().getId());
            statement.setBoolean(3, pres.getNowReport().isDistance());
            statement.setBoolean(4, pres.getNowReport().isNeutral());
            statement.setBoolean(5, pres.getNowReport().isReading());
            statement.setDate(6, new java.sql.Date(pres.getNowReport().getTime().getTime()));

            int r = statement.executeUpdate();

            if (r > 0) {
                System.out.println("Insert new report success");
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            PreparedStatement statement = conn.prepareStatement(idSql2);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                pres.getNowReport().setId(rs.getInt(1));
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Lấy key 
        int key = (Calendar.getInstance().get(Calendar.YEAR) - 2000) * 10000000;
        String keySql = "SELECT MAX(key) FROM TABLE_PRESCRIPTION";
        
        try {
            PreparedStatement statement = conn.prepareStatement(keySql);
            
            ResultSet rs = statement.executeQuery();
            
            if (rs.next()) {
                key = rs.getInt(1);
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        pres.setKey(SomeFunc.generateKey(key));
        
        // Ghi dữ liệu 
        String sql = "INSERT INTO TABLE_PRESCRIPTION(last_report, current_report, "
                + "eyewear, note, re_exam, time, staff, customer, total, key) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, pres.getLastReport().getId());
            statement.setInt(2, pres.getNowReport().getId());
            statement.setInt(3, pres.getEyewear());
            statement.setString(4, pres.getNote());
            statement.setInt(5, pres.getReExam());
            statement.setDate(6, new java.sql.Date(pres.getTime().getTime()));
            statement.setInt(7, pres.getStaff().getId());
            statement.setInt(8, pres.getCus().getId());
            statement.setFloat(9, pres.getTotal());
            statement.setInt(10, pres.getKey());

            int r = statement.executeUpdate();
            
            if (r > 0) {
                System.out.println("Insert new prescription success");
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Lấy mã của đơn vừa ghi 
        String sql2 = "SELECT MAX(ID) FROM TABLE_PRESCRIPTION";
        int id = 0;
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql2);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                id = rs.getInt(1);
                pres.setId(id);
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(NewPrescriptionPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        return pres;
    }
}
