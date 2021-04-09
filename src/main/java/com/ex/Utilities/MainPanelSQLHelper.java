/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Utilities;

import com.ex.Models.*;
import java.sql.*;

import com.ex.Utilities.Config;
import com.ex.Utilities.CryptCodecOpener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 *
 * @author cn200
 */
public class MainPanelSQLHelper extends MainFrameSQLHelper {
    
    public static final int TYPE_NAME = 0;
    public static final int TYPE_PHONE_NUMBER = 1;
    
    public MainPanelSQLHelper() {
        super();
    }
    
}
