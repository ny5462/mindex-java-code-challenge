package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * This interface is to design functionality of compensation service having
 * functions to add a compensation and get a compensation
 */
public interface CompensationService {
    // method to create or add a compensation
    Compensation addNewCompensation(Compensation compensation);
    // method to get a compensation given an employee id
    Compensation getCompensation(String id);
}
