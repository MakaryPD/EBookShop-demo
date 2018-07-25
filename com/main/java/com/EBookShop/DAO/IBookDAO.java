package com.EBookShop.DAO;

import java.util.List;
import com.EBookShop.Entity.Book;

public interface IBookDAO {

	public List<Book> getBooks();
	public List<Book> searchBooks(String category, String name);
	public Book getBook(int id);
	public void changeNumberOfBooks(Book book, boolean increment);
	public void updateBook(Book book);
	public void deleteBook(Book book);
	public void disableBook(Book book);
}
