package com.EBookShop.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EBookShop.DAO.ICartDAO;
import com.EBookShop.Entity.Book;
import com.EBookShop.Entity.Cart;
import com.EBookShop.Entity.UserCrm;

@Service
public class CartService implements ICartService{

	@Autowired
	ICartDAO cartDAO;

	@Override
	@Transactional
	public Cart getCart(int id) {
		Cart cart = cartDAO.getCart(id);
		return cart;
	}

	@Override
	@Transactional
	public void saveCart(UserCrm user) {
		cartDAO.saveCart(user);
	}

	@Override
	@Transactional
	public void clearCartFromDeletedBook(Book book) {
		cartDAO.clearCartFromDeletedBook(book);
	}
}
