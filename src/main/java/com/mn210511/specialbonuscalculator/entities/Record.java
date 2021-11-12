package com.mn210511.specialbonuscalculator.entities;

abstract public class Record {

    String company;
    String employee;

    public Record(String company, String employee) {
        this.company = company;
        this.employee = employee;
    }

    public Record(){

    }


    abstract public String getCompany();
    abstract public String getEmployee();
}
