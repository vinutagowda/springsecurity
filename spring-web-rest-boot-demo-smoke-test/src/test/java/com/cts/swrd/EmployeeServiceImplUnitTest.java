package com.cts.swrd;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.swrd.dao.EmployeeRepository;
import com.cts.swrd.model.Department;
import com.cts.swrd.model.Employee;
import com.cts.swrd.service.EmployeeService;
import com.cts.swrd.service.EmployeeServiceImpl;

@RunWith(SpringRunner.class)
public class EmployeeServiceImplUnitTest {
	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration{
		@Bean
		public EmployeeService employeeService() {
			return new EmployeeServiceImpl();
			
		}
	}
	
@Autowired
private EmployeeService employeeService;

@MockBean
private EmployeeRepository employeeRepository;

@Before
public void setUp() {
	Employee emp=new Employee("vinu","gowda",45000,
			LocalDate.now(),Department.DEVELOPMENT,"9052224753",
			"vinu@gmail.com");
	Mockito.when(employeeRepository.findByMobileNumber(emp.getMobileNumber())).thenReturn(emp);
}
@Test
public void whenValidMobileNumber_thenEmployeeShouldBeFound() {
	String mno="9052224753";
	Employee found=employeeService.findByMobileNumber(mno);
	assertThat(found.getMobileNumber()).isEqualTo(mno);
}

@Test
public void whenInValidMobileNumber_thenEmployeeShouldNotBefound() {
	String mno="1234567890";
	Employee found=employeeService.findByMobileNumber(mno);
	assertThat(found).isNull();
}
}
