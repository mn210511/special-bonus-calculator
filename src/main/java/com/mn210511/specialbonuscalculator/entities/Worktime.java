package com.mn210511.specialbonuscalculator.entities;

/**
 * Class to store a Worktime data
 */
public class Worktime {
    double hoursPerWeek;
    int duration;
    double average;

    public Worktime(double hoursPerWeek, int duration, double average) {
        this.hoursPerWeek = hoursPerWeek;
        this.duration = duration;
        this.average = average;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(double hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
