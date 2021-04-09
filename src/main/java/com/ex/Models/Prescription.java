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
public class Prescription {
    private int id;
    private Report lastReport, nowReport;
    private int eyewear;        // Kính gọng 1 đơn, 2 hai, 3 đa, 4 
    private String note;        // Ghi chú 
    private int reExam;         // Khám lại 
    private Date time;
    private Staff staff;        // Nhân viên 
    private Customer cus;       // Khách hàng 
    private float total;        // Thanh toán 
    private int key;         // Tìm kiếm 
    
    public Prescription() {}
    
    public Prescription(int id, Report lastReport, Report nowReport, int eyewear, 
            String note, int reExam, Date time, Staff staff, Customer cus, 
            float total, int key) {
        this.id = id;
        this.lastReport = lastReport;
        this.nowReport = nowReport;
        this.eyewear = eyewear;
        this.note = note;
        this.reExam = reExam;
        this.time = time;
        this.staff = staff;
        this.cus = cus;
        this.total = total;
        this.key = key;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastReport(Report lastReport) {
        this.lastReport = lastReport;
    }

    public void setNowReport(Report nowReport) {
        this.nowReport = nowReport;
    }

    public void setEyewear(int eyewear) {
        this.eyewear = eyewear;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setReExam(int reExam) {
        this.reExam = reExam;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public Report getLastReport() {
        return lastReport;
    }

    public Report getNowReport() {
        return nowReport;
    }

    public int getEyewear() {
        return eyewear;
    }

    public String getNote() {
        return note;
    }

    public int getReExam() {
        return reExam;
    }

    public Date getTime() {
        return time;
    }
    
    public String getsTime(boolean time, boolean date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(this.time);
        
        if (time) {
            if (date) {
                return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) 
                        + ":" + cal.get(Calendar.SECOND) + " " + cal.get(Calendar.DAY_OF_MONTH) 
                        + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.YEAR);
            } else {
                return cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE) 
                        + ":" + cal.get(Calendar.SECOND);
            }
        } else {
            if (date) {
                return cal.get(Calendar.DAY_OF_MONTH) + "/" + (cal.get(Calendar.MONTH) + 1) + "/" 
                + cal.get(Calendar.YEAR);
            }
        }
        return "";
    }

    public Staff getStaff() {
        return staff;
    }

    public Customer getCus() {
        return cus;
    }

    public float getTotal() {
        return total;
    }
    
    
}
