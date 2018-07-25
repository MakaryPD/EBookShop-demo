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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="carts")
public class Cart {

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id; 
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="username")
	private UserCrm user;
	@Column(name="size")
	private int size; 
	@Column(name="total_cost")
	private float totalCost;
	@ManyToMany(cascade= {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="cart_book",
				joinColumns=@JoinColumn(name="cart_id"),
				inverseJoinColumns= @JoinColumn(name="book_id"))
	private List<Book> books; 
	
	public Cart() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserCrm getUser() {
		return user;
	}

	public void setUser(UserCrm user) {
		this.user = user;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}
	
	
	
	
	
}
