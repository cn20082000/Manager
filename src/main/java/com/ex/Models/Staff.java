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
public class Staff {
    private int id;
    private String firstName;
    private String lastName;
    private String signature;   // Chữ kí 
    
    public Staff() {}
    
    public Staff(int id, String firstName, String lastName, String signature) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.signature = signature;
    }

    public String getName() {
        return lastName + " " + firstName;
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

    public void setSignature(String signature) {
        this.signature = signature;
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

    public String getSignature() {
        return signature;
    }
    
    
}
