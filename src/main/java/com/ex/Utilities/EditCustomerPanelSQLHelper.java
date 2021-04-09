/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Utilities;

import com.ex.Models.Customer;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cn200
 */
public class EditCustomerPanelSQLHelper extends SQLHelper{
    public EditCustomerPanelSQLHelper() {
        super();
    }
    
    public int addCustomer(Customer cus) {
        open();
        
        String sql = "INSERT INTO TABLE_CUSTOMER (first_name, last_name, address, "
                + "birthday, phone_number) VALUES (?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cus.getFirstName());
            statement.setString(2, cus.getLastName());
            statement.setString(3, cus.getAddress());
            statement.setDate(4, new Date(cus.getBirthday().getTime()));
            statement.setString(5, cus.getPhoneNumber());
            
            int r = statement.executeUpdate();
            
            if (r > 0) {
                System.out.println("Insert new customer!");
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditCustomerPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql2 = "SELECT MAX(TABLE_CUSTOMER.ID) FROM TABLE_CUSTOMER";
        int result = -1;
        
        try {
            PreparedStatement statement = conn.prepareStatement(sql2);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                result = rs.getInt(1);
            }
            
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(EditCustomerPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        close();
        
        return result;
    }
}
