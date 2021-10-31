package com.mn210511.specialbonuscalculator.services;

public class Calculator {

    /**
     *
     * @param hours
     * @param days
     * @return
     */
    public double averageHours (double hours, int days, boolean shiftYear) {
    int yearInDays = 0;
       if (shiftYear) {
           yearInDays = 366;
       } else {
           yearInDays = 365;
       }

   return (hours/yearInDays)*days;
}
}