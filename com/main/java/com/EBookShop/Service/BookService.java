package com.EBookShop.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EBookShop.DAO.IBookDAO;
import com.EBookShop.DAO.ICartDAO;
import com.EBookShop.Entity.Book;
import com.EBookShop.Entity.Cart;

@Service
public class BookService implements IBookService {

	@Autowired
	IBookDAO bookDAO; 
	@Autowired
	ICartDAO cartDAO;
	
	@Override
	@Transactional
	public List<Book> getBooks() {
		List<Book> books = bookDAO.getBooks();
		return books;
	}

	@Override
	@Transactional
	public Book getBook(int id) {
		Book book = bookDAO.getBook(id);
		return book;
	}

	@Override
	@Transactional
	public void addBookToCart(int cartId, int bookId) {
		Cart cart = cartDAO.getCart(cartId);
		Book book = bookDAO.getBook(bookId);
		cart.getBooks().add(book);
		cartDAO.changeCartSize(cart,true);
		bookDAO.changeNumberOfBooks(book,false);
		cart.setTotalCost(cart.getTotalCost()+book.getPrice());
	}

	@Override
	@Transactional
	public void removeBookFromCart(int cartId, int bookId) {
		Cart cart = cartDAO.getCart(cartId);
		Book book = bookDAO.getBook(bookId);
		cart.getBooks().remove(book);
		cartDAO.changeCartSize(cart, false);
		bookDAO.changeNumberOfBooks(book, true);
		cart.setTotalCost(cart.getTotalCost()-book.getPrice());
	}
	@Override
	@Transactional
	public void removeBookFromCartPernamently(int cartId, int bookId) {
		Cart cart = cartDAO.getCart(cartId);
		Book book = bookDAO.getBook(bookId);
		cart.getBooks().remove(book);
		cartDAO.changeCartSize(cart, false);
		cart.setTotalCost(cart.getTotalCost()-book.getPrice());
	}

	@Override
	@Transactional
	public boolean findBookInCart(int cartId, int bookId) {
		Cart cart = cartDAO.getCart(cartId);
		for(Book i : cart.getBooks()) {
			if(i.getId()==bookId) {
				return true; 
			}
		}
		return false;
	}

	@Override
	@Transactional
	public void updateBook(Book book) {
		bookDAO.updateBook(book);
		
	}

	@Override
	@Transactional
	public void deleteBook(Book book) {
		bookDAO.deleteBook(book);
		
	}

	@Override
	@Transactional
	public void disableBook(Book book) {
		bookDAO.disableBook(book);
		
	}

	@Override
	@Transactional
	public List<Book> searchBooks(String category, String name) {
		List<Book> books = bookDAO.searchBooks(category, name);
		return books;
	}
	
}
