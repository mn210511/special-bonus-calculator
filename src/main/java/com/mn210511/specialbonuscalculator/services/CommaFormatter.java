package com.mn210511.specialbonuscalculator.services;

public class CommaFormatter {

    public String changeToComma (String text) {
        char[] chars = text.toCharArray();

     for (int i = 0; i < chars.length; i++) {
         if (chars[i]=='.'){
             chars[i]=',';
         }
     }


        String ret = String.valueOf(chars);
        System.out.println("mit Beistrich " + ret);
        return ret;
    }


}
