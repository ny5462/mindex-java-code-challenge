package com.mindex.challenge.dao;

import com.mindex.challenge.data.Compensation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompensationRepository  extends MongoRepository<Compensation, String> {
    // finding id of a nested object using underscore and capital field names
    // https://stackoverflow.com/questions/55579240/spring-data-find-by-property-of-a-nested-object
    Compensation findByEmployee_EmployeeId(String id);
}
