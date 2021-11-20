package com.mn210511.specialbonuscalculator.entities;

public class RecordThreeMonth extends Record{
    double avgSalary;
    double avgDirtAllowance;
    double avgOvertime;
    double avgDivAllowance;
    double[] salarys = new double[3];
    double[] dirtAllowances = new double[3];
    double[] overtimes = new double[3];
    double[] divAllowances = new double[3];
    double bonus;

    public RecordThreeMonth(String company, String employee) {
        super(company, employee);
    }

    public RecordThreeMonth() {
    }

    @Override
    public String getCompany() {
        return company;
    }

    @Override
    public String getEmployee() {
        return employee;
    }



    public double getAvgSalary() {
        return avgSalary;
    }

    public void setAvgSalary(double avgSalary) {
        this.avgSalary = avgSalary;
    }

    public double getAvgDirtAllowance() {
        return avgDirtAllowance;
    }

    public void setAvgDirtAllowance(double avgDirtAllowance) {
        this.avgDirtAllowance = avgDirtAllowance;
    }

    public double getAvgOvertime() {
        return avgOvertime;
    }

    public void setAvgOvertime(double avgOvertime) {
        this.avgOvertime = avgOvertime;
    }

    public double getAvgDivAllowance() {
        return avgDivAllowance;
    }

    public void setAvgDivAllowance(double avgDivAllowance) {
        this.avgDivAllowance = avgDivAllowance;
    }

    public double[] getSalarys() {
        return salarys;
    }

    public void setSalarys(double[] salarys) {
        this.salarys = salarys;
    }

    public double[] getDirtAllowances() {
        return dirtAllowances;
    }

    public void setDirtAllowances(double[] dirtAllowances) {
        this.dirtAllowances = dirtAllowances;
    }

    public double[] getOvertimes() {
        return overtimes;
    }

    public void setOvertimes(double[] overtimes) {
        this.overtimes = overtimes;
    }

    public double[] getDivAllowances() {
        return divAllowances;
    }

    public void setDivAllowances(double[] divAllowances) {
        this.divAllowances = divAllowances;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }
}
