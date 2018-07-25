package com.EBookShop.Service;

import com.EBookShop.Entity.Book;
import com.EBookShop.Entity.Cart;
import com.EBookShop.Entity.UserCrm;

public interface ICartService {

	public Cart getCart(int id);
	public void saveCart(UserCrm user); 
	public void clearCartFromDeletedBook(Book book);
}
