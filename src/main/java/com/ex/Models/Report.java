/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Models;

import java.util.Date;

/**
 *
 * @author cn200
 */
public class Report {
    private int id;
    private Eye leftEye, rightEye;
    private boolean distance, neutral, reading;  // Tầm nhìn xa, trung, cận 
    private Date time;
    
    public Report() {}
    
    public Report(int id, Eye leftEye, Eye rightEye, boolean distance, boolean neutral, 
            boolean reading, Date time) {
        this.id = id;
        this.leftEye = leftEye;
        this.rightEye = rightEye;
        this.distance = distance;
        this.neutral = neutral;
        this.reading = reading;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public Eye getLeftEye() {
        return leftEye;
    }

    public Eye getRightEye() {
        return rightEye;
    }

    public boolean isDistance() {
        return distance;
    }

    public boolean isNeutral() {
        return neutral;
    }

    public boolean isReading() {
        return reading;
    }

    public Date getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLeftEye(Eye leftEye) {
        this.leftEye = leftEye;
    }

    public void setRightEye(Eye rightEye) {
        this.rightEye = rightEye;
    }

    public void setDistance(boolean distance) {
        this.distance = distance;
    }

    public void setNeutral(boolean neutral) {
        this.neutral = neutral;
    }

    public void setReading(boolean reading) {
        this.reading = reading;
    }

    public void setTime(Date time) {
        this.time = time;
    }
    
    
}
