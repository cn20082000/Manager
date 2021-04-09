/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Models;

/**
 *
 * @author cn200
 */
public class Eye {
    private int id;
    private float sph, cyl, ax; // Cầu, trụ, trục 
    private float add;          // Cộng thêm 
    private int va;             // Thị lực 
    private int fh;             // Chiều cao tâm kính 
    private float pd_2, pd;     // PD/2, PD 
    
    public Eye() {}
    
    public Eye(int id, float sph, float cyl, float ax, float add, int va, int fh, 
            float pd_2, float pd) {
        this.id = id;
        this.sph = sph;
        this.cyl = cyl;
        this.ax = ax;
        this.add = add;
        this.va = va;
        this.fh = fh;
        this.pd_2 = pd_2;
        this.pd = pd;
    }

    public int getId() {
        return id;
    }

    public float getSph() {
        return sph;
    }

    public float getCyl() {
        return cyl;
    }

    public float getAx() {
        return ax;
    }

    public float getAdd() {
        return add;
    }

    public int getVa() {
        return va;
    }

    public int getFh() {
        return fh;
    }

    public float getPd_2() {
        return pd_2;
    }

    public float getPd() {
        return pd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSph(float sph) {
        this.sph = sph;
    }

    public void setCyl(float cyl) {
        this.cyl = cyl;
    }

    public void setAx(float ax) {
        this.ax = ax;
    }

    public void setAdd(float add) {
        this.add = add;
    }

    public void setVa(int va) {
        this.va = va;
    }

    public void setFh(int fh) {
        this.fh = fh;
    }

    public void setPd_2(float pd_2) {
        this.pd_2 = pd_2;
    }

    public void setPd(float pd) {
        this.pd = pd;
    }
    
    
}
