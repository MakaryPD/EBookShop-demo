package com.EBookShop.Registration;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegistration {

	@NotNull(message="This field is required")
	@Size(min=1, message="This field is required")
	private String username;
	@NotNull(message="This field is required")
	@Size(min=1, message="This field is required")
	private String password; 
	@NotNull
	@Size(min=1, message="This field is required")
	private String firstName; 
	@NotNull(message="This field is required")
	@Size(min=1, message="This field is required")
	private String lastName; 
	@NotNull
	@Size(min=1, message="This field is required")
	private String email; 
	@NotNull(message="This field is required")
	@Size(min=1,max=5, message="This field is required")
	private String reg_postal; 
	@NotNull(message="This field is required")
	@Size(min=1, message="This field is required")
	private String reg_city;
	@NotNull(message="This field is required")
	@Size(min=1, message="This field is required")
	private String reg_street;
	@NotNull(message="This field is required")
	@Size(min=1, message="This field is required")
	private String reg_houseNr;
	@NotNull(message="This field is required")
	@Size(min=1, message="This field is required")
	private String reg_apartmentNr;

	private String contact_postal; 

	private String contact_city;

	private String contact_street;

	private String contact_houseNr;

	private String contact_apartmentNr;
	
	
	private boolean checkbox;
	public UserRegistration() {
		
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReg_postal() {
		return reg_postal;
	}

	public void setReg_postal(String reg_postal) {
		this.reg_postal = reg_postal;
	}

	public String getReg_city() {
		return reg_city;
	}

	public void setReg_city(String reg_city) {
		this.reg_city = reg_city;
	}

	public String getReg_street() {
		return reg_street;
	}

	public void setReg_street(String reg_street) {
		this.reg_street = reg_street;
	}

	public String getReg_houseNr() {
		return reg_houseNr;
	}

	public void setReg_houseNr(String reg_houseNr) {
		this.reg_houseNr = reg_houseNr;
	}

	public String getReg_apartmentNr() {
		return reg_apartmentNr;
	}

	public void setReg_apartmentNr(String reg_apartmentNr) {
		this.reg_apartmentNr = reg_apartmentNr;
	}

	public String getContact_postal() {
		return contact_postal;
	}

	public void setContact_postal(String contact_postal) {
		this.contact_postal = contact_postal;
	}

	public String getContact_city() {
		return contact_city;
	}

	public void setContact_city(String contact_city) {
		this.contact_city = contact_city;
	}

	public String getContact_street() {
		return contact_street;
	}

	public void setContact_street(String contact_street) {
		this.contact_street = contact_street;
	}

	public String getContact_houseNr() {
		return contact_houseNr;
	}

	public void setContact_houseNr(String contact_houseNr) {
		this.contact_houseNr = contact_houseNr;
	}

	public String getContact_apartmentNr() {
		return contact_apartmentNr;
	}

	public void setContact_apartmentNr(String contact_apartmentNr) {
		this.contact_apartmentNr = contact_apartmentNr;
	}

	public boolean isCheckbox() {
		return checkbox;
	}

	public void setCheckbox(boolean checkbox) {
		this.checkbox = checkbox;
	}
	
	
	
	
	
	
}
