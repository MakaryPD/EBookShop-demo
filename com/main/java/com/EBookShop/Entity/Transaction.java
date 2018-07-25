package com.EBookShop.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="transactions")
public class Transaction implements Comparable<Transaction> {

	private static String compareToValue; 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id; 
	@ManyToOne(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="username")
	private UserCrm userCrm; 
	@Column(name="total_cost")
	private float totalCost;
	@Column(name="size")
	private int size;
	@Column(name="adress")
	private String Adress; 
	@ManyToOne(cascade = { CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="credit_card_id")
	private CreditCard creditCard;
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="transactions_books",
				joinColumns=@JoinColumn(name="transaction_id"),
				inverseJoinColumns= @JoinColumn(name="book_id"))
	private List<Book> books;
	
	public Transaction() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserCrm getUserCrm() {
		return userCrm;
	}

	public void setUserCrm(UserCrm userCrm) {
		this.userCrm = userCrm;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public String getAdress() {
		return Adress;
	}

	public void setAdress(String adress) {
		Adress = adress;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public static String getCompareToValue() {
		return compareToValue;
	}

	public static void setCompareToValue(String compareToValue) {
		Transaction.compareToValue = compareToValue;
	}

	@Override
	public int compareTo(Transaction anotherTransaction) {
		if(compareToValue.equals("id")) {
			return Integer.compare(this.id, anotherTransaction.id);
		}else if(compareToValue.equals("username")) {
			return this.userCrm.getUsername().compareTo(anotherTransaction.getUserCrm().getUsername());
		}else if(compareToValue.equals("totalCost")) {
			return Float.compare(this.totalCost, anotherTransaction.totalCost);
		}
		return 0;
	}
	
	
	
	
	
}
