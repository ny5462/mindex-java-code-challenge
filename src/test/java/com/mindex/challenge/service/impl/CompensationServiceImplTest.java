package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
    private String compensationUrl;
    private String compensationIdUrl;

    @Autowired
    private EmployeeRepository employeeRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Employee testEmployee;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "compensation/employee/{id}";
        testEmployee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    }
    @After
    public void teardown() {
        compensationUrl = null;
        compensationIdUrl = null;

        testEmployee = null;
    }


    @Test
    public void testAddGetCompensation(){

        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary(50000);
        testCompensation.setEffectiveDate("05/02/2021");

        //create checks or add compensation check
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation,
                Compensation.class).getBody();
        assertCompensationEquivalence(testCompensation,createdCompensation);

        //read checks or get compensation check
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class,
                createdCompensation.getEmployee().getEmployeeId()).getBody();
        assertCompensationEquivalence(readCompensation,createdCompensation);



    }
    private static void assertEmployeeEquivalence(Employee expected, Employee actual) {
        assertEquals(expected.getFirstName(), actual.getFirstName());
        assertEquals(expected.getLastName(), actual.getLastName());
        assertEquals(expected.getDepartment(), actual.getDepartment());
        assertEquals(expected.getPosition(), actual.getPosition());
    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual){
        assertEmployeeEquivalence(expected.getEmployee(),actual.getEmployee());
        assertEquals(expected.getSalary(),actual.getSalary());
        assertEquals(expected.getEffectiveDate(),actual.getEffectiveDate());

    }

}
