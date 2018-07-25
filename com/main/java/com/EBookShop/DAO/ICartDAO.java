package com.EBookShop.DAO;


import com.EBookShop.Entity.Book;
import com.EBookShop.Entity.Cart;
import com.EBookShop.Entity.UserCrm;

public interface ICartDAO {
	public Cart getCart(int id);
	public void clearCartFromDeletedBook(Book book);
	public void changeCartSize(Cart cart,boolean increment);
	public void saveCart(UserCrm user); 
}
