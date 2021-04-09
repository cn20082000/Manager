/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Utilities;

import com.ex.Models.Staff;

/**
 *
 * @author cn200
 */
public class Config {
    // Tên ứng dụng 
    private static String appName = "Quản lý bán hàng";
    
    // Địa chỉ icon 
    private static String appIcon = "19247609_849081241916094_2443705581497116024_n.jpg";    
    // Tên cửa hàng 
    private static String storeName = "Cửa hàng kính mắt";
    
    // Thông tin liên hệ 1
    private static String contactInfo1 = "Điện thoại: 0123 456 789";
    
    // Thông tin liên hệ 2 
    private static String contactInfo2 = "Địa chỉ: Ngự Câu - An Thượng - Hoài Đức - Hà Nội";
    
    // Địa chỉ cơ sở dữ liệu 
    private static String databaseURL = "Db.accdb";
    
    // Mật khẩu cơ sở dữ liệu 
    private static String databasePass = "ch1nh2";
    
    // Nhân viên hiện tại 
    private static Staff staff = new Staff();

    public Config() {}

    public static Staff getStaff() {
        return staff;
    }

    public static void setStaff(Staff staff) {
        Config.staff = staff;
    }
    
    public static void setAppName(String appName) {
        Config.appName = appName;
    }

    public static void setAppIcon(String appIcon) {
        Config.appIcon = appIcon;
    }

    public static void setStoreName(String storeName) {
        Config.storeName = storeName;
    }

    public static void setContactInfo1(String contactInfo1) {
        Config.contactInfo1 = contactInfo1;
    }

    public static void setContactInfo2(String contactInfo2) {
        Config.contactInfo2 = contactInfo2;
    }

    public static void setDatabaseURL(String databaseURL) {
        Config.databaseURL = databaseURL;
    }

    public static void setDatabasePass(String databasePass) {
        Config.databasePass = databasePass;
    }

    public static String getAppName() {
        return appName;
    }

    public static String getAppIcon() {
        return appIcon;
    }

    public static String getStoreName() {
        return storeName;
    }

    public static String getContactInfo1() {
        return contactInfo1;
    }

    public static String getContactInfo2() {
        return contactInfo2;
    }

    public static String getDatabaseURL() {
        return databaseURL;
    }

    public static String getDatabasePass() {
        return databasePass;
    }
    
    
}
