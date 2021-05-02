package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This file implements compensation service functions to
 * add compensation and get compensation for an employee
 *
 */
@Service
public class CompensationServiceImpl implements CompensationService {
    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);

    // dependency injection using compensation repository which interacts with DB
    @Autowired
    private CompensationRepository compensationRepository;

    // dependency injection using employee repository which interacts with DB
    @Autowired
    private EmployeeService employeeService;

    /**
     * This method creates a compensation in the database in service layer
     * @param compensation  compensation with given employee, salary, effective date
     * @return  compensation
     */
    @Override
    public Compensation addNewCompensation(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);
        // fetches employee from employee repository and sets it as compensation employee
        String id=compensation.getEmployee().getEmployeeId();
        // makes sure if employee doesn't exist in employee repo, exception will be thrown
        Employee employee=employeeService.read(id);
        compensation.setEmployee(employee);
        // inserts valid compensation into compensation repository
        compensationRepository.insert(compensation);
        return compensation;
    }

    /**
     * This method fetches compensation for given employee id and returns it if present
     * @param id    employee id
     * @return      compensation object
     */
    @Override
    public Compensation getCompensation(String id) {
        LOG.debug("Getting compensation for employee with id [{}]", id);
        Optional<Compensation> compensation = Optional.ofNullable(compensationRepository.findByEmployee_EmployeeId(id));
        if(!compensation.isPresent()){
            throw new IllegalStateException("Compensation with employee id "+id+" doesn't exist");
        }
        return compensation.get();
    }
}
