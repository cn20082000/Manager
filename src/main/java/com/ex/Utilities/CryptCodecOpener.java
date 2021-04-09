/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Utilities;

import java.io.File;
import java.io.IOException;
import net.ucanaccess.jdbc.JackcessOpenerInterf­ace;
import com.healthmarketscience.jackcess.CryptCo­decProvider;
import com.healthmarketscience.jackcess.Databas­e;
import com.healthmarketscience.jackcess.Databas­eBuilder;

/**
 *
 * @author cn200
 */
public class CryptCodecOpener implements JackcessOpenerInterface {
    @Override
    public Database open(File fl,String pwd) throws IOException {
       DatabaseBuilder dbd =new DatabaseBuilder(fl);
       dbd.setAutoSync(false);
       dbd.setCodecProvider(new CryptCodecProvider(pwd));
       dbd.setReadOnly(false);
       return dbd.open();
    }
  //Notice that the parameter setting autosync =true is recommended with UCanAccess for performance reasons. 
  //UCanAccess flushes the updates to disk at transaction end. 
  //For more details about autosync parameter (and related tradeoff), see the Jackcess documentation. 
}