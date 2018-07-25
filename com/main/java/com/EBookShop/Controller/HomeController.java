package com.EBookShop.Controller;


import java.security.Principal;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EBookShop.Entity.Book;
import com.EBookShop.Entity.UserCrm;
import com.EBookShop.Service.IBookService;
import com.EBookShop.Service.IUserCrmService;

@Controller
@RequestMapping("/Home")
public class HomeController {
	
	@Autowired
	IBookService bookService;
	@Autowired
	IUserCrmService userService;
	
	private List<Book> tempBooks;
	
	@GetMapping("/")
	public String getHomePage() {
		return "Home";
	}
	
	@GetMapping("/book_list")
	public String getBookListPage(Model model) {
		tempBooks = bookService.getBooks();
		model.addAttribute("books",tempBooks);
		return "book_list";
	}
	
	@PostMapping("/searchBy")
	public String searchEngine(@RequestParam("searchCategory") String searchCategory,@RequestParam("searchWord") String searchWord, Model model) {
		if(searchCategory==""||searchWord=="") {
			return "redirect:book_list";
		}else {
			tempBooks= bookService.searchBooks(searchCategory, searchWord);
			System.out.println(tempBooks.size());
			model.addAttribute("books",tempBooks);
			return "book_list"; 
		}
	}
	
	@PostMapping("/sortBy")
	public String sort(@RequestParam("sortCategory") String sortCategory, Model model) {
		Book.setCompareToValue(sortCategory);
		Collections.sort(tempBooks);
		model.addAttribute("books",tempBooks);
		return "book_list"; 
	}
	
	@GetMapping("/book")
	public String getBookPage(@RequestParam("bookId") int bookID, Model model) {
		Book book = bookService.getBook(bookID);
		model.addAttribute("book",book);
		return "book_preview"; 
	}
	
	@PostMapping("/addBookToCart")
	public String addBookToCart(Principal principal,@RequestParam("bookID") int bookId) {
		if(principal!=null) {
			UserCrm user = userService.getUser(principal.getName());
			bookService.addBookToCart(user.getCart().getId(), bookId);
			//Removes book after 1 min
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			            	if(bookService.findBookInCart(user.getCart().getId(), bookId)) {
			            		bookService.removeBookFromCart(user.getCart().getId(), bookId);
			            	}else {
			            		System.out.println("Not taking action.");
			            	}
			            }
			        }, 
			        600000
			);
		}
		return "redirect:book_list";
	}
	
}
