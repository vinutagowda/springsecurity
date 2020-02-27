
package com.cts.swrd;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.swrd.dao.EmployeeRepository;
import com.cts.swrd.model.Department;
import com.cts.swrd.model.Employee;

//spring test module uses Junit internally so same annotations
//to inform that we are using spring test -@RunWith
@RunWith(SpringRunner.class) // bridge between Spring Boot test features and Junit
@DataJpaTest // configuring H2, an in-memory db,setting hibernate ,Spring data and data
				// source,performing an @EntityScan turning on SQL logging
@TestPropertySource(locations="classpath:application.properties")
public class EmployeeRepositoryUnitTest {
	private Employee emps[];

	@Autowired
	private TestEntityManager entityManager;// using entity managaer we are testing dao functions ex:for testing whether
											// record is inserted or not
	
	// we insert using dao function but retrieve using entity manager fun as we are
	// not sure whether retreive fun of dao works or not
	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeRepositoryUnitTest() {
		emps = new Employee[] { new Employee("Meena", "Chowdary", 45000, LocalDate.now(), Department.DEVELOPMENT,
				"9010930815", "meenachowdary@yahoo.com") };
	}

	@Before
	public void beforeEachTest() {
		for (Employee e : emps) {
			entityManager.persist(e);
		}
		entityManager.flush();
	}

	@After
	public void afterEachTest() {
		for (Employee e : emps) {
			entityManager.remove(e);
		}
		entityManager.flush();
	}

	@Test
	public void whenFindByMobileNumber_thenReturnEmployee() { // in test cases we should provide readability hence can
																// use underscore in function name
		Employee e = employeeRepository.findByMobileNumber(emps[0].getMobileNumber()); // called bdd-behavior driven
																						// development
		assertThat(e).isEqualTo(emps[0]);
	}

	@Test
	public void whenFindByMobileNumber_withNonExistingMobileNumber_thenReturnNull() {
		Employee e = employeeRepository.findByMobileNumber("1234567890");
		assertThat(e).isNull();
	}

}