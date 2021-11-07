package com.mn210511.specialbonuscalculator.entities;

import java.time.LocalDate;

/**
 * Class to store a Worktime data
 */
public class Worktime {
    double hoursPerWeek;
    int duration;
    double average;
    LocalDate begin;
    LocalDate end;

    public Worktime(double hoursPerWeek, int duration) {
        this.hoursPerWeek = hoursPerWeek;
        this.duration = duration;
    }

    public Worktime(double hoursPerWeek, int duration, LocalDate begin, LocalDate end) {
        this.hoursPerWeek = hoursPerWeek;
        this.duration = duration;
        this.begin = begin;
        this.end = end;
    }

    public LocalDate getBegin() {
        return begin;
    }

    public void setBegin(LocalDate begin) {
        this.begin = begin;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
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
