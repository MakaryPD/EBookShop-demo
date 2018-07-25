package com.EBookShop.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.query.Query;
import com.EBookShop.Entity.Book;

@Repository
public class BookDAO implements IBookDAO{
	
	@Autowired
	public SessionFactory sessionFactory; 

	@Override
	public List<Book> getBooks() {
		Session session = sessionFactory.getCurrentSession();
		Query<Book> query = session.createQuery("FROM Book",Book.class);
		List<Book> books = query.getResultList();
		return books;
	}

	@Override
	public Book getBook(int id) {
		Session session = sessionFactory.getCurrentSession();
		Book book = session.get(Book.class, id);
		return book;
	}
	
	public void changeNumberOfBooks(Book book, boolean increment) {
		Session session = sessionFactory.getCurrentSession();
		if(increment) {
			book.setNumber(book.getNumber()+1);
		}else if(!increment) {
			book.setNumber(book.getNumber()-1);
		}
		session.update(book);
	}

	@Override
	public void updateBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(book);
	}

	@Override
	public void deleteBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(book);
	}

	@Override
	public void disableBook(Book book) {
		Session session = sessionFactory.getCurrentSession();
		book.setEnabled(!book.isEnabled());
		session.saveOrUpdate(book);
		
	}

	@Override
	public List<Book> searchBooks(String category, String name) {
		Session session = sessionFactory.getCurrentSession();
		Query<Book> query = session.createQuery("FROM Book b WHERE b."+category+" LIKE '%"+name+"%'",Book.class);
		List<Book> books = query.getResultList();
		return books;
	}


}
