package com.EBookShop.Entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="personal_data")
public class PersonalData {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="first_name")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required.")
	private String firstName; 
	@Column(name="last_name")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required.")
	private String lastName; 
	@Column(name="email")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required.")
	private String email;
	@Column(name="reg_postal")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required.")
	private String regPostal; 
	@Column(name="reg_city")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required.")
	private String regCity; 
	@Column(name="reg_street")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required.")
	private String regStreet; 
	@Column(name="reg_house_nr")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required.")
	private String regHouseNr; 
	@Column(name="reg_apartment_nr")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required.")
	private String regApartmentNr; 
	@Column(name="contact_postal")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required.")
	private String contactPostal; 
	@Column(name="contact_city")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required.")
	private String contactCity; 
	@Column(name="contact_street")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required.")
	private String contactStreet; 
	@Column(name="contact_house_nr")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required.")
	private String contactHouseNr; 
	@Column(name="contact_apartment_nr")
	@NotNull(message="this field is required")
	@Size(min=1, message="this field is required.")
	private String contactApartmentNr; 
	
	@OneToOne(mappedBy="personalData", fetch = FetchType.EAGER)
	private UserCrm userCrm;
	@OneToMany(mappedBy="personalData")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CreditCard> creditCards;
	
	@Transient
	private Integer cardId;
	@Transient 
	private String Adress; 
	@Transient
	private String registeredAdress;
	@Transient 
	private String contactAdress;
	
	public PersonalData() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public UserCrm getUserCrm() {
		return userCrm;
	}

	public void setUserCrm(UserCrm userCrm) {
		this.userCrm = userCrm;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public String getRegPostal() {

		return regPostal;
	}

	public void setRegPostal(String regPostal) {

		this.regPostal = regPostal;
	}

	public String getRegCity() {
		return regCity;
	}

	public void setRegCity(String regCity) {
		this.regCity = regCity;
	}

	public String getRegStreet() {
		return regStreet;
	}

	public void setRegStreet(String regStreet) {
		this.regStreet = regStreet;
	}

	public String getRegHouseNr() {
		return regHouseNr;
	}

	public void setRegHouseNr(String regHouseNr) {
		this.regHouseNr = regHouseNr;
	}

	public String getRegApartmentNr() {
		return regApartmentNr;
	}

	public void setRegApartmentNr(String regApartmentNr) {
		this.regApartmentNr = regApartmentNr;
	}

	public String getContactPostal() {
		return contactPostal;
	}

	public void setContactPostal(String contactPostal) {
		this.contactPostal = contactPostal;
	}

	public String getContactCity() {
		return contactCity;
	}

	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}

	public String getContactStreet() {
		return contactStreet;
	}

	public void setContactStreet(String contactStreet) {
		this.contactStreet = contactStreet;
	}

	public String getContactHouseNr() {
		return contactHouseNr;
	}

	public void setContactHouseNr(String contactHouseNr) {
		this.contactHouseNr = contactHouseNr;
	}

	public String getContactApartmentNr() {
		return contactApartmentNr;
	}

	public void setContactApartmentNr(String contactApartmentNr) {
		this.contactApartmentNr = contactApartmentNr;
	}

	public Integer getCardId() {
		return cardId;
	}

	public void setCardId(Integer cardId) {
		this.cardId = cardId;
	}
	
	public String getRegisteredAdress() {
		return this.regPostal+" "+this.regCity+" "+this.regStreet+" "+" "+this.regHouseNr+"/"+this.regApartmentNr;
	}
	
	public String getContactAdress() {
		return this.contactPostal+" "+this.contactCity+" "+this.contactStreet+" "+" "+this.contactHouseNr+"/"+this.contactApartmentNr;
	}

	public void setRegisteredAdress(String registeredAdress) {
		this.registeredAdress = registeredAdress;
	}

	public void setContactAdress(String contactAdress) {
		this.contactAdress = contactAdress;
	}

	public String getAdress() {
		return Adress;
	}

	public void setAdress(String adress) {
		Adress = adress;
	}
	
	
	

	

	
	
	
	
}
