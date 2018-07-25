package com.EBookShop.Controller;

import java.security.Principal;
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
import com.EBookShop.Entity.Cart;
import com.EBookShop.Entity.CreditCard;
import com.EBookShop.Entity.PersonalData;
import com.EBookShop.Entity.Transaction;
import com.EBookShop.Entity.UserCrm;
import com.EBookShop.Service.IBookService;
import com.EBookShop.Service.ICreditCardService;
import com.EBookShop.Service.IPersonalDataService;
import com.EBookShop.Service.ITransactionService;
import com.EBookShop.Service.IUserCrmService;

@Controller
@RequestMapping("/User")
public class UserController {
	
	@Autowired
	IUserCrmService userCrmService;
	@Autowired
	IBookService bookService;
	@Autowired
	IPersonalDataService personalDataService;
	@Autowired
	ICreditCardService creditCardService;
	@Autowired
	ITransactionService transactionService;


	@GetMapping("/user_profile")
	public String getUserProfilePage(Principal principal,Model personalData_model,Model CreditCard_model) {
		if(principal!=null) {
			UserCrm user = userCrmService.getUser(principal.getName());
			int personalDataId = user.getPersonalData().getId();
			PersonalData personalData = personalDataService.getPersonalData(personalDataId);
			personalData_model.addAttribute("personalData", personalData);
			List<CreditCard> creditCards = creditCardService.getCreditCards(personalData);
			CreditCard_model.addAttribute("creditCards",creditCards);
		}
		return "user_profile";
	}
	@GetMapping("/update_user_profile")
	public String updateUserProfile(Model personal_model, @RequestParam("personalId") int id) {
		PersonalData personalData = personalDataService.getPersonalData(id);
		personal_model.addAttribute("personalData",personalData);
		return "user_update_form";
	}
	
	@PostMapping("/updateData")
	public String updatePersonalData(Principal principal,@Valid @ModelAttribute("personalData") PersonalData personalData, BindingResult bindingResult, Model model) {
		UserCrm userCrm = userCrmService.getUser(principal.getName());
		if(bindingResult.hasErrors()) {
			model.addAttribute("personalData",personalData);
			return "user_update_form";
		}else {
			personalDataService.updatePersonalData(personalData,userCrm);
			List<CreditCard> creditCards = creditCardService.getCreditCards(personalData);
			model.addAttribute("creditCards",creditCards);
			return "user_profile";
		}
	}
	
	@GetMapping("/createCard")
	public String createCard(Model model) {
		CreditCard creditCard = new CreditCard();
		model.addAttribute("creditCard",creditCard);
		return "update_card_form";
	}
	
	@GetMapping("/showCardUpdateFrom")
	public String updateCardPage(@RequestParam("cardId")int id, Model model) {
		CreditCard creditCard = creditCardService.getCreditCard(id);
		model.addAttribute("creditCard",creditCard);
		return "update_card_form";
	}
	
	@PostMapping("/updateCardForm")
	public String updateCardForm(@Valid @ModelAttribute("creditCard") CreditCard card, BindingResult bindingResult, Principal principal, Model model) {
		String username = principal.getName();
		UserCrm user = userCrmService.getUser(username);
		int personalId = user.getPersonalData().getId();
		PersonalData personalData = personalDataService.getPersonalData(personalId);
		if(bindingResult.hasErrors()) {
			model.addAttribute("creditCard",card);
			return "update_card_form";
		}else {
			creditCardService.updateCreditCard(card,personalData);
			return "redirect:user_profile";
		}
	}
	
	@GetMapping("/deleteCard")
	public String deleteCard(Principal principal,@RequestParam("cardId") int cardId) {
		String username = principal.getName();
		UserCrm userCrm = userCrmService.getUser(username);
		int personalId = userCrm.getPersonalData().getId();
		personalDataService.removeCreditCard(cardId, personalId);
		return "redirect:user_profile";
	}
	
	@GetMapping("/shopping_cart")
	public String getShoppingCartPage(Principal principal, Model model, Model model_2) {
		UserCrm user = userCrmService.getUser(principal.getName());
		Cart cart = user.getCart();
		Book book = new Book(); 
		PersonalData personalData = personalDataService.getPersonalData(user.getPersonalData().getId());
		List<CreditCard> creditCards = creditCardService.getCreditCards(personalData);
		model.addAttribute("cart", cart);
		model_2.addAttribute("book",book);
		model.addAttribute("creditCards",creditCards);
		return "shopping_cart";
	}
	
	@GetMapping("/deleteBookFromCart")
	public String delete(Principal principal,@RequestParam("bookId") int bookId) {
		UserCrm userCrm = userCrmService.getUser(principal.getName());
		int cartId = userCrm.getCart().getId();
		bookService.removeBookFromCart(cartId, bookId);
		return "redirect:/User/shopping_cart";
	}
	
	@GetMapping("/userTransactionHistory")
	public String userTransactionHistoryPage(Principal principal,Model model) {
		UserCrm userCrm = userCrmService.getUser(principal.getName());
		List<Transaction> transactions = transactionService.getUserTransactions(userCrm);
		model.addAttribute("transactions",transactions);
		for(Transaction t : transactions) {
			System.out.println(t.getAdress());
		}
		return "user_transaction_history";
	}
	
	@GetMapping("/transactionPreview")
	public String transactionPreviewPage(@RequestParam("transactionId") int transactionId, Model model) {
		Transaction transaction = transactionService.getTransaction(transactionId);
		UserCrm userCrm = userCrmService.getUser(transaction.getUserCrm().getUsername());
		PersonalData personalData = personalDataService.getPersonalData(userCrm.getPersonalData().getId());
		model.addAttribute("transaction",transaction);
		model.addAttribute("personalData",personalData);
		return "transaction_preview";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
	StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
	dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	

}
