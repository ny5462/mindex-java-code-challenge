package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * This file acts in service layer to implement functions to create number of reports on the fly
 * for a given employee, where number of reports is the number of direct and undirect reportees
 * to the given employee
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * This method will generate reporting structure for a valid employee, i.e if it exists
     * and calculate no of reportees for that employee and returns the reporting structure
     * @param id    employee id
     * @return
     */
    @Override
    public ReportingStructure getReportingStructure(String id) {
        LOG.debug("getting ReportingStructure for employee with id [{}]", id);
        // finds employee from employee repository given an id
        Optional<Employee> employee = Optional.ofNullable(employeeRepository.findByEmployeeId(id));
        // if employee doesn't exist throw exception
        if(!employee.isPresent()){
            throw new IllegalStateException("Employee with id "+id+" doesn't exist");
        }
        // calculates number of reports for given employee id
        int numberOfReports = getAllReportsUnderEmployee(id);
        // create reporting structure
        ReportingStructure report=new ReportingStructure();
        report.setEmployee(employee.get());
        report.setNumberOfReports(numberOfReports);
        return report;

    }

    /**
     * recursive function which uses number of direct reportees of every valid employee in
     * the heirarchy of given employee id and adds them up, which indeed is the total number
     * of reportees direct/indirect for given employee
     * @param id
     * @return
     */
    public int getAllReportsUnderEmployee(String id){
        int totalReports=0;
        Employee employee=employeeRepository.findByEmployeeId(id);
        // checks if employee exists
        if(employee==null){
            throw new IllegalStateException("Employee with id "+id+" Doesn't exist");
        }
        // all direct reportees for given employee to evaluated recursively
        List<Employee> candidates=employee.getDirectReports();
        if(candidates!=null && candidates.size()>0){
            totalReports=candidates.size();
            for(Employee emp:candidates){
                totalReports+=getAllReportsUnderEmployee(emp.getEmployeeId());
            }
        }
        return totalReports;

    }
}
