package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class acts as a Rest controller or API layer using spring framework for type Reporting Structure,
 * It has get method implemented by retrieving reporting Structure from service layer and returns
 * the object with fields employee and number of reports(number of people reporting an employee with given
 * id) directly and undirectly.
 * @Author - Nikhil Yadav
 */
@RestController
public class ReportingStructureController {

    private
    final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    // dependency injection getting service layer features
    @Autowired
    private ReportingStructureService reportingStructureService;

    /**
     * This method takes an employee id and returns its reporting structure
     * @param id    employeeID
     * @return      reporting structure with fields employee and number of reports
     */
    @GetMapping(path="/reportingStructure/{id}")
    public ReportingStructure getReportingStructureById(@PathVariable String id){
        LOG.debug("Received reporting structure get request for id [{}]", id);
        return reportingStructureService.getReportingStructure(id);
    }
}
