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
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="books")
public class Book implements Comparable<Book> {

	private static String compareToValue;
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id; 
	@Column(name="title")
	@NotNull(message="This field cannot be empty")
	@Size(min=1, message="This field cannot be empty")
	private String title; 
	@Column(name="author")
	@NotNull(message="This field cannot be empty")
	@Size(min=1, message="This field cannot be empty")
	private String author; 
	@Column(name="publisher")
	@NotNull(message="This field cannot be empty")
	@Size(min=1, message="This field cannot be empty")
	private String publisher;
	@Column(name="year")
	@NotNull(message="This field cannot be empty")
	@Size(min=1, message="This field cannot be empty")
	private String year;
	@Column(name="type")
	@NotNull(message="This field cannot be empty")
	@Size(min=1, message="This field cannot be empty")
	private String type;
	@Column(name="price")
	@NotNull(message="This field cannot be empty")
	@Min(1)
	private float price;
	@Column(name="vat")
	@NotNull(message="This field cannot be empty")
	@Min(1)
	private int vat;
	@Column(name="number")
	@NotNull(message="This field cannot be empty")
	@Min(1)
	private int number;
	@Column(name="enabled")
	private boolean enabled;
	@ManyToMany(cascade = { CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="cart_book",
				joinColumns=@JoinColumn(name="book_id"),
				inverseJoinColumns= @JoinColumn(name="cart_id"))
	private List<Cart> carts;
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="transactions_books",
				joinColumns=@JoinColumn(name="book_id"),
				inverseJoinColumns= @JoinColumn(name="transaction_id"))
	private List<Transaction> transactions;
	
	public Book(){
		
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}
	
	public List<Transaction> getTransactions() {
		return transactions;
	}


	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	

	public static String getCompareToValue() {
		return compareToValue;
	}


	public static void setCompareToValue(String compareValue) {
		Book.compareToValue = compareValue;
	}


	@Override
	public String toString() {
		return  "ID: "+this.id+", "+this.title+". Number in stock: "+this.number;
	}

	
	

	@Override
	public int compareTo(Book anotherBook) {
		if(compareToValue.equals("title")) {
		return this.title.compareTo(anotherBook.title);
		}else if(compareToValue.equals("author")) {
			return this.author.compareTo(anotherBook.author);
		}else if(compareToValue.equals("publisher")) {
			return this.publisher.compareTo(anotherBook.publisher);
		}else if(compareToValue.equals("type")) {
			return this.type.compareTo(anotherBook.type);
		}else if(compareToValue.equals("price")) {
			return Float.compare(this.price, anotherBook.price);
		}else {
			return 0;
		}
	}
	
}
