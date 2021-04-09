/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Models;

import java.util.Date;
import java.util.Calendar;

/**
 *
 * @author cn200
 */
public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private Date birthday;
    private String phoneNumber;
    
    public Customer() {}
    
    public Customer(int id, String firstName, String lastName, String address, 
            Date birthday, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return lastName + " " + firstName;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }
    
    public String getsBirthday() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);
        int month = cal.get(Calendar.MONTH) + 1;
        return cal.get(Calendar.DAY_OF_MONTH) + "/" + month + "/" 
                + cal.get(Calendar.YEAR);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Object[] toObject() {
        // Mã id
        // Họ và tên 
        String name = lastName + ' ' + firstName;
            
        // địa chỉ address
            
        // Ngày sinh 
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);
        int month = cal.get(Calendar.MONTH) + 1;
        String sBirthday = cal.get(Calendar.DAY_OF_MONTH) + "/" + month + "/" 
                + cal.get(Calendar.YEAR);
            
        // Số điện thoại phoneNumber
            
        // Tổng hợp 
        return new Object[] {id, name, address, sBirthday, phoneNumber};
    }
}
