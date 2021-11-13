package com.mn210511.specialbonuscalculator.services;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

public class Calculator {

    /**
     * calculatates the average hours
     *
     * @param hours the hours that where worked per week
     * @param days  the duration of the worked in the time-model
     * @return the avg value
     */
    public double averageHours(double hours, int days, boolean shiftYear) {
        int yearInDays = 0;
        if (shiftYear) {
            yearInDays = 366;
        } else {
            yearInDays = 365;
        }

        double tmp = (double) days / yearInDays;
        double ret = tmp * hours;

        return ret;
    }

    /**
     * sums all the avg values
     *
     * @param values the avg values to sum
     * @return a sum fo the values
     */
    public double sumAverageHours(List<Double> values) {
double ret = 0;
        for (Double v: values
             ) {
            ret = ret + v;
        }

        return ret;
    }

    /**
     * calculates the correct bonus to pay
     *
     * @param salary          the salary of the employee
     * @param avgHours        the sum of the avgHours
     * @param workDaysPerWeek the worktime model (40, 38, 38.5)
     * @return the bonus to pay
     */
    public double calculateBonus(double salary, double avgHours, double workDaysPerWeek) {

        return (salary / workDaysPerWeek) * avgHours;
    }

    public double calculateThreeMonthAverage(double ... values) {
     double ret = Arrays.stream(values).sum();

        return ret/3;
    };

    public double sum (double ... values) {
        return Arrays.stream(values).sum();
    }

}