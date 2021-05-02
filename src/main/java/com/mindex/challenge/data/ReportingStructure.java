package com.mindex.challenge.data;

/**
 * This file is a class file for reporting structure, which is an object which displays information
 * about an employee and the number of employees that report to him directly/undirectly
 */
public class ReportingStructure {
    Employee employee;
    int numberOfReports;

    public ReportingStructure() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() {
        return numberOfReports;
    }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }


}
