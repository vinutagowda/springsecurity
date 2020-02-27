package com.cts.swrd.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity
@Table(name="employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long empId;
	
	@NotEmpty(message="firstName can not be empty")
	@NotNull(message="firstName can not be omitted")
	private String firstName;
	
	@NotEmpty(message="lastName can not be empty")
	@NotNull(message="lastName can not be omitted")
	private String lastName;
	
	
	@Min(value=25000,message="basic can not be less than 25 thousand")
	@Max(value=450000,message="basic can not be less than 4.5 lakh")
	private double basic;
	
	@DateTimeFormat(iso=ISO.DATE)
	private LocalDate joinDate;
	
	@Enumerated(EnumType.STRING)
	private Department dept;
	
	@Pattern(regexp="[1-9][0-9]{9}",message="Mobile number is expected to be 10 digits not strings")
	@NotNull(message="Mobile number canmnot be omitted.")
	private String mobileNumber;
		
	@Email(message="email must be valid one")
	@NotNull(message="email can not be omitted.")
	private String email;


	public Employee(String firstName, String lastName, double basic, LocalDate  joinDate, Department dept, String mobileNumber,
			String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.basic = basic;
		this.joinDate = joinDate;
		this.dept = dept;
		this.mobileNumber = mobileNumber;
		this.email = email;
	}


	public Employee() {
		
	}


	public Long getEmpId() {
		return empId;
	}


	public void setEmpId(Long empId) {
		this.empId = empId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public double getBasic() {
		return basic;
	}


	public void setBasic(double basic) {
		this.basic = basic;
	}


	public LocalDate getJoinDate() {
		return joinDate;
	}


	public void setJoinDate(LocalDate joinDate) {
		this.joinDate = joinDate;
	}


	public Department getDept() {
		return dept;
	}


	public void setDept(Department dept) {
		this.dept = dept;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
