package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest extends TestCase {


    private String reportingStructureUrl;


    @Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Employee testEmployee;

    @Before
    public void setup() {
        reportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";
        testEmployee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }

    @Test
    public void testGetReportingStructure() {
        ReportingStructure testReport = new ReportingStructure();
        testReport.setEmployee(testEmployee);


        ReportingStructure getTestReport = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class,
                testReport.getEmployee().getEmployeeId()).getBody();
        //4 people underReport to john, let's test it
        assertNotNull(getTestReport);
        assertEquals(4,getTestReport.getNumberOfReports());
    }
}