/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Utilities;

import com.ex.Models.Customer;
import com.ex.Models.Prescription;
import com.ex.Models.Report;
import com.ex.Models.Staff;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cn200
 */
public class CusDetailPanelSQLHelper extends SQLHelper {
    public CusDetailPanelSQLHelper() {
        super();
    }
    
    public List<Prescription> searchPres(Customer cus) {
        List<Prescription> result = new ArrayList<>();
        open();
        
        String presSql = "SELECT ID, time, key FROM TABLE_PRESCRIPTION WHERE customer = " + cus.getId();
        
        try {
            PreparedStatement statement = conn.prepareStatement(presSql);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                Prescription pres = new Prescription();
                pres.setId(rs.getInt(1));
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(rs.getTime(2));
                pres.setTime(new java.util.Date(cal.getTime().getTime() + rs.getDate(2).getTime() + 25200000));
                
                pres.setKey(rs.getInt(3));
                result.add(pres);
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
