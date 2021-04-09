/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cn200
 */
public class SQLHelper {
    
    protected Connection conn;
    
    public SQLHelper() {}
    
    protected void open() {
        try {
            conn = DriverManager.getConnection("jdbc:ucanaccess://" 
                    + Config.getDatabaseURL() + ";jackcessOpener=com.ex.Utilities.CryptCodecOpener", 
                    "congChinh", Config.getDatabasePass());
        } catch (SQLException ex) {
            Logger.getLogger(MainPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(MainPanelSQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
