package com.mn210511.specialbonuscalculator.entities;

import java.util.List;

/**
 * Class that saves the needed Values for the calculation
 */
public class Record {

    String company;
    String employee;

    List<Worktime> worktimes;
    boolean shiftyear;
    double average;
    double bonus;

    public Record(String company, String employee, List<Worktime> worktimes, boolean shiftyear, double average, double bonus) {
        this.company = company;
        this.employee = employee;
        this.worktimes = worktimes;
        this.shiftyear = shiftyear;
        this.average = average;
        this.bonus = bonus;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmployee() {
        return employee;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public List<Worktime> getWorktimes() {
        return worktimes;
    }

    public void setWorktimes(List<Worktime> worktimes) {
        this.worktimes = worktimes;
    }

    public boolean isShiftyear() {
        return shiftyear;
    }

    public void setShiftyear(boolean shiftyear) {
        this.shiftyear = shiftyear;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
