/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ex.Utilities;

import java.util.Calendar;
import java.util.Random;

/**
 *
 * @author cn200
 */
public class SomeFunc {
    public static int generateKey(int oldKey) {
        // Cấu trúc key: 
        // 2 chữ số đầu tiên là năm
        // 7 chữ số tiếp theo là mã tăng dần
        
        int oldYear = oldKey / 10000000;
        int key = oldKey % 10000000;
        
        Calendar cal = Calendar.getInstance();
        Random generator = new Random(cal.getTime().getTime());
        int newYear = cal.get(Calendar.YEAR) - 2000;
        if (newYear != oldYear) {
            key = 0;
        }
        
        key += Math.abs(generator.nextInt()) % (cal.get(Calendar.HOUR) + 1) + 1;
        System.out.println("key: " + key);
        
        return newYear * 10000000 + key;
    }
    
    public static String valueOf(int value) {
        if (value == Cons.VALUE_NULL) {
            return "";
        }
        return String.valueOf(value);
    }
    
    public static String valueOf(float value) {
        if (value == Cons.VALUE_NULL) {
            return "";
        }
        return String.valueOf(value);
    }
    
    public static int parseInt(String s) throws NumberFormatException{
        if (s.equals("")) {
            return Cons.VALUE_NULL;
        }
        try {
            int result = Integer.parseInt(s);
            return result;
        } catch (NumberFormatException e) {
            throw e;
        }
    }
    
    public static float parseFloat(String s) throws NumberFormatException{
        if (s.equals("")) {
            return Cons.VALUE_NULL;
        }
        try {
            float result = Float.valueOf(s);
            return result;
        } catch (NumberFormatException e) {
            throw e;
        }
    }
    
    public static int parseInt(String s, int defaultValue) throws NumberFormatException{
        if (s.equals("")) {
            return defaultValue;
        }
        try {
            int result = Integer.parseInt(s);
            return result;
        } catch (NumberFormatException e) {
            throw e;
        }
    }
    
    public static float parseFloat(String s, float defaultValue) throws NumberFormatException{
        if (s.equals("")) {
            return defaultValue;
        }
        try {
            float result = Float.valueOf(s);
            return result;
        } catch (NumberFormatException e) {
            throw e;
        }
    }
}
