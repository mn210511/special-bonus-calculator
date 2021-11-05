package com.mn210511.specialbonuscalculator.entities;

/**
 * Class to store a Worktime data
 */
public class Worktime {
    double hoursPerWeek;
    int duration;

    public Worktime(double hoursPerWeek, int duration) {
        this.hoursPerWeek = hoursPerWeek;
        this.duration = duration;
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
