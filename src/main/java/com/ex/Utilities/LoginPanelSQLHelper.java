/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Utilities;

import com.ex.Models.Staff;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cn200
 */
public class LoginPanelSQLHelper extends SQLHelper {
    public LoginPanelSQLHelper() {
        super();
    }
    
    public Staff login(String username, String password) {
        Staff result = new Staff();
        
        open();
        
        String sql = "SELECT ID, first_name, last_name, signature FROM TABLE_STAFF "
                + "WHERE username = '" + username + "' AND password = '" + password + "'";
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String signature = rs.getString(4);
                
                result = new Staff(id, firstName, lastName, signature);
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(LoginPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        return result;
    }
}
