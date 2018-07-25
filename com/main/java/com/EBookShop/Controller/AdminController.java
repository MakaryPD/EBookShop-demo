package com.EBookShop.Controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EBookShop.Entity.Book;
import com.EBookShop.Entity.PersonalData;
import com.EBookShop.Entity.Transaction;
import com.EBookShop.Entity.UserCrm;
import com.EBookShop.Service.IBookService;
import com.EBookShop.Service.ICartService;
import com.EBookShop.Service.IPersonalDataService;
import com.EBookShop.Service.ITransactionService;
import com.EBookShop.Service.IUserCrmService;

@Controller
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	IUserCrmService userService;
	@Autowired
	IPersonalDataService personalDataService;
	@Autowired
	IBookService bookService;
	@Autowired
	ICartService cartService;
	@Autowired 
	ITransactionService transactionService;
	
	private List<Book> tempBooks;
	private List<UserCrm> tempUsersCrm;
	private List<Transaction> tempTransactions;
	
	@GetMapping("/")
	public String showAdminPage() {
		return "admin_page";
	}
	
	@GetMapping("/userList")
	public String userListPage(Model model) {
		tempUsersCrm = userService.getUsers();
		model.addAttribute("users",tempUsersCrm);
		return "user_list";
	}
	
	@PostMapping("/searchByUserCrm")
	public String searchEngineUserCrm(@RequestParam("searchCategory") String searchCategory,@RequestParam("searchWord") String searchWord, Model model) {
		if(searchCategory==null||searchWord==null) {
			return "redirect:userList";
		}else {
			tempUsersCrm= userService.searchUsers(searchCategory, searchWord);
			System.out.println(tempUsersCrm.size());
			model.addAttribute("users",tempUsersCrm);
			return "user_list"; 
		}
	}
	
	@PostMapping("/sortByUserCrm")
	public String sortUsersCrm(@RequestParam("sortCategory") String sortCategory, Model model) {
		UserCrm.setCompareToValue(sortCategory);
		if(UserCrm.getCompareToValue()==null) {
			return "redirect:userList";
		}else {
			Collections.sort(tempUsersCrm);
			model.addAttribute("users",tempUsersCrm);
			return "user_list"; 
		}
	}
	
	@GetMapping("/updateUserInfo")
	public String updateUserInfo(@RequestParam("username") String username, Model userCrm_model, Model personalData_model) {
		UserCrm userCrm = userService.getUser(username);
		int id = userCrm.getPersonalData().getId();
		PersonalData personalData = personalDataService.getPersonalData(id);
		userCrm_model.addAttribute("userCrm",userCrm);
		personalData_model.addAttribute("personalData",personalData);
		return "user_update_form";
	}
	
	@PostMapping("/updateData")
	public String updateUserCrm(@Valid @ModelAttribute("personalData") PersonalData pd, BindingResult bindingResult, Model model) {
		PersonalData personalData = personalDataService.getPersonalData(pd.getId());
		if(bindingResult.hasErrors()) {
			model.addAttribute("personalData",pd);
			return "user_update_form";
		}else {
			personalDataService.updatePersonalData(pd, personalData.getUserCrm());
			List<UserCrm> users = userService.getUsers();
			model.addAttribute("users",users);
			return "user_list";
		}
	}
	@GetMapping("/bookList")
	public String bookListPage(Model model) {
		tempBooks = bookService.getBooks();
		model.addAttribute("books",tempBooks);
		return "admin_book_list";
	}
	
	@PostMapping("/searchByBook")
	public String searchEngineBook(@RequestParam("searchCategory") String searchCategory,@RequestParam("searchWord") String searchWord, Model model) {
		if(searchCategory==null||searchWord==null) {
			return "redirect:bookList";
		}else {
			tempBooks= bookService.searchBooks(searchCategory, searchWord);
			System.out.println(tempBooks.size());
			model.addAttribute("books",tempBooks);
			return "admin_book_list"; 
		}
	}
	
	@PostMapping("/sortByBook")
	public String sortBooks(@RequestParam("sortCategory") String sortCategory, Model model) {
		Book.setCompareToValue(sortCategory);
		if(Book.getCompareToValue()==null) {
			return "redirect:bookList";
		}else {
			Collections.sort(tempBooks);
			model.addAttribute("books",tempBooks);
			return "admin_book_list"; 
		}
	}
	
	@GetMapping("/addBook")
	public String addBookPage(Model model) {
		Book book = new Book();
		model.addAttribute("book",book);
		return "admin_book_update_form";
	}
	
	@GetMapping("/updateBookInfo")
	public String updateBookPage(@RequestParam("bookId") int bookId,Model model) {
		Book book = bookService.getBook(bookId);
		model.addAttribute("book",book);
		return "admin_book_update_form";
	}
	@PostMapping("/updateBook")
	public String updateBook(@Valid @ModelAttribute("book") Book bk, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("book",bk);
			return "admin_book_update_form"; 
		}else {
			bookService.updateBook(bk);
			return "redirect:bookList";
		}
	}
	@GetMapping("/deleteBook")
	public String deleteBook(@RequestParam("bookId") int bookId) {
		Book book = bookService.getBook(bookId);
		cartService.clearCartFromDeletedBook(book);
		bookService.deleteBook(book);
		return "redirect:bookList";
	}
	@GetMapping("/disableBook")
	public String disableBook(@RequestParam("bookId") int bookId) {
		Book book = bookService.getBook(bookId);
		bookService.disableBook(book);
		return "redirect:bookList";
	}
	
	@GetMapping("/transactionList")
	public String transactionListPage(Model model) {
		tempTransactions  = transactionService.getUserTransactions();
		model.addAttribute("transactions",tempTransactions);
		return "admin_transaction_list"; 
	}
	
	@PostMapping("/searchByTransaction")
	public String searchEngineTransaction(@RequestParam("searchCategory") String searchCategory,@RequestParam("searchWord") String searchWord, Model model) {
		if(searchCategory==null||searchWord==null) {
			return "redirect:transactionList";
		}else {
			tempTransactions =  transactionService.searchTransactions(searchCategory, searchWord);
			model.addAttribute("transactions",tempTransactions);
			return "admin_transaction_list"; 
		}
	}
	
	@PostMapping("/sortByTransaction")
	public String sortTransactions(@RequestParam("sortCategory") String sortCategory, Model model) {
		Transaction.setCompareToValue(sortCategory);
		if(Transaction.getCompareToValue()==null) {
			return "redirect:transactionList";
		}else {
			Collections.sort(tempTransactions);
			model.addAttribute("transactions",tempTransactions);
			return "admin_transaction_list"; 
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
	StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
	dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
}
