package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * This program makes use of the spring boot framework to build a Rest Controller for compensation.
 * This class acts as an API layer or controller for type compensation with post and get methods,
 * implemented where you can add a new compensation for an employee or retrieve the compensation
 * for an employee
 * @Author -Nikhil Yadav
 */
@RestController
public class CompensationController {
    private
    final Logger LOG = LoggerFactory.getLogger(CompensationController.class);

    @Autowired
    private CompensationService compensationService;


    /**
     * Post Method for receiving compensation from client and passing it to
     * service layer
     * @param compensation  an object with fields employee, salary and effective date
     * @return  compensation
     */
    @PostMapping(path="/compensation")
    public Compensation addCompensation(@RequestBody Compensation compensation){
        LOG.debug("Received compensation create request for [{}]", compensation);
        compensationService.addNewCompensation(compensation);
        return compensation;
    }

    /**
     * This method is a GET Method to retrieve compensation for an employee with give id.
     * @param id    unique Id of employee
     * @return      compensation for employee with given employee id
     */
    @GetMapping(path="/compensation/employee/{id}")
    public Compensation getCompensationById(@PathVariable String id){
        LOG.debug("Received compensation get request for id [{}]", id);
        return compensationService.getCompensation(id);
    }

}
