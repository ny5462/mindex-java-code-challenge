package com.mindex.challenge.service;

import com.mindex.challenge.data.ReportingStructure;

/**
 * This interface is to design functionality of reporting structure service
 * where you have a method to fetch a implement a reporting structure given
 * an employee id
 */
public interface ReportingStructureService {
    // return a reporting structure given an employee id
    ReportingStructure getReportingStructure(String id);

}
