package com.EBookShop.DAO;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.EBookShop.Entity.Book;
import com.EBookShop.Entity.Cart;
import com.EBookShop.Entity.UserCrm;

@Repository
public class CartDAO implements ICartDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Cart getCart(int id) {
		Session session = sessionFactory.getCurrentSession();
		Cart cart = session.get(Cart.class, id);
		return cart;
	}

	@Override
	public void changeCartSize(Cart cart,boolean increment) {
		Session session = sessionFactory.getCurrentSession();
		if(increment) {
			cart.setSize(cart.getSize()+1);
		}else if(!increment) {
			cart.setSize(cart.getSize()-1);
		}
		session.update(cart);				
	}

	@Override
	public void saveCart(UserCrm user) {
		Session session = sessionFactory.getCurrentSession();
		Cart cart = new Cart(); 
		cart.setSize(0);
		cart.setTotalCost(0);
		cart.setUser(user);
		session.save(cart);
	}

	@Override
	public void clearCartFromDeletedBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		for(Cart i : book.getCarts()) {
			i.setSize(i.getSize()-1);
			i.setTotalCost(i.getTotalCost()-book.getPrice());
			session.saveOrUpdate(i);
		}
	}


}
