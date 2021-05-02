package com.mindex.challenge.data;

/**
 * This file is a class for creating type compensation with fields employee,
 * salary and effective date
 * @Author - Nikhil Yadav
 */
public class Compensation {

    Employee employee;
    int salary;
    String effectiveDate;

    public Compensation() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

}
