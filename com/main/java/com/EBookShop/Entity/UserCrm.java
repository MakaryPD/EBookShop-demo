package com.EBookShop.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="users")
public class UserCrm implements Comparable<UserCrm>{

	
	private static String compareToValue;
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password; 
	@Column(name="enabled")
	private int enabled; 
	@OneToOne(mappedBy="user")
	private Cart cart;
	@OneToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="personal_data_id")
	private PersonalData personalData;
	
	
	@Transient 
	private boolean logged; 

	public UserCrm() {

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

	public int getEnabled() {
		return enabled;
	}

	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
	}

	public static String getCompareToValue() {
		return compareToValue;
	}

	public static void setCompareToValue(String compareToValue) {
		UserCrm.compareToValue = compareToValue;
	}

	@Override
	public int compareTo(UserCrm anotherUserCrm) {
		if(compareToValue.equals("username")) {
			return this.username.compareTo(anotherUserCrm.username);
		}else if(compareToValue.equals("firstName")) {
			return this.personalData.getFirstName().compareTo(anotherUserCrm.getPersonalData().getFirstName());
		}else if(compareToValue.equals("lastName")) {
			return this.personalData.getLastName().compareTo(anotherUserCrm.getPersonalData().getLastName());
		}else if(compareToValue.equals("email")) {
			return this.personalData.getEmail().compareTo(anotherUserCrm.getPersonalData().getEmail());
		}
		return 0;
	}

	
	
	
	
	
}
