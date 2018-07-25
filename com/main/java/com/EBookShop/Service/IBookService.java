package com.EBookShop.Service;

import java.util.List;

import com.EBookShop.Entity.Book;
public interface IBookService {

	public List<Book> getBooks();
	public List<Book> searchBooks(String category, String name);
	public Book getBook(int id);
	public void addBookToCart(int cartId, int  bookId);
	public void removeBookFromCart(int cartId, int  bookId);
	public void removeBookFromCartPernamently(int cartId, int  bookId);
	public boolean findBookInCart(int cartId, int bookId);
	public void updateBook(Book book);
	public void deleteBook(Book book);
	public void disableBook(Book book);
	
}
