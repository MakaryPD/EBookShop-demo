package com.EBookShop.Entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="credit_card")
public class CreditCard {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	@Column(name="number")
	@NotNull(message="this field is required")
	@Size(min=16, message="this field must be 16 digits.")
	private String number; 
	@Column(name="card_type")
	@NotNull(message="YYYY-MM-DD")
	@Size(min=1, message="this field must be in YYY-MM-DD formtat.")
	private String cardType;
	@Column(name="expire_date")
	private Date expireDate; 
	@Column(name="owner_first_name")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required")
	private String firstName; 
	@Column(name="owner_last_name")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required")
	private String lastName;
	@Column(name="password")
	@NotNull(message="this field is required")
	@Min(100)
	private int password;
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="personal_data_id")
	private PersonalData personalData; 
	@OneToMany(mappedBy="creditCard", orphanRemoval=true)
	private List<Transaction> transactions;
	
	public CreditCard() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public int getPassword() {
		return password;
	}

	public void setPassword(int password) {
		this.password = password;
	}

	public PersonalData getPersonalData() {
		return personalData;
	}

	public void setPersonalData(PersonalData personalData) {
		this.personalData = personalData;
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

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	
	
	
	
	
}
