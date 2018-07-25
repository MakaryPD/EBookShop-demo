package com.EBookShop.Controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.EBookShop.Entity.Book;
import com.EBookShop.Entity.Cart;
import com.EBookShop.Entity.CreditCard;
import com.EBookShop.Entity.PersonalData;
import com.EBookShop.Entity.UserCrm;
import com.EBookShop.Service.IBookService;
import com.EBookShop.Service.ICartService;
import com.EBookShop.Service.ICreditCardService;
import com.EBookShop.Service.IPersonalDataService;
import com.EBookShop.Service.ITransactionService;
import com.EBookShop.Service.IUserCrmService;

@Controller
@RequestMapping("/Payment")
public class PaymentController {
	
	@Autowired
	IUserCrmService userCrmService;

	@Autowired
	IPersonalDataService personalDataService;
	@Autowired
	ICreditCardService creditCardService;
	@Autowired
	ICartService cartService; 
	@Autowired
	ITransactionService transactionService; 
	@Autowired
	IBookService bookService; 
	
	@GetMapping("/paymentMethod")
	public String paymentMethod(Principal principal,Model model) {
		String username = principal.getName();
		UserCrm userCrm = userCrmService.getUser(username);
		PersonalData personalData = personalDataService.getPersonalData(userCrm.getPersonalData().getId());
		HashMap<Integer,String> tempMap = populateHashMap(personalData);
		model.addAttribute("personalData",personalData);
		model.addAttribute("tempMap",tempMap);
		return "payment_payment_method";
	}
	
	@PostMapping("/confirmPaymentMethod")
	public String confirmPayment( @ModelAttribute("personalData") PersonalData personalData,@Valid @RequestParam("userPass")@NotNull int userCardPass,BindingResult bindingResult, Model model,
			Principal principal) {
		CreditCard creditCard = creditCardService.getCreditCard(personalData.getCardId());
		UserCrm userCrm = userCrmService.getUser(principal.getName());
		PersonalData pd = personalDataService.getPersonalData(userCrm.getPersonalData().getId());
		Cart cart = cartService.getCart(userCrm.getCart().getId());
		Book book = new Book();
		if( personalData.getCardId()==-1 || personalData.getAdress()==null) {
			model.addAttribute("dataNotFound","Please, make sure you've selected correct credit card and adress.");
			HashMap<Integer,String> tempMap = populateHashMap(pd);
			model.addAttribute("personalData",pd);
			model.addAttribute("tempMap",tempMap);
			return "payment_payment_method";
		}else if(userCardPass!=creditCard.getPassword() || bindingResult.hasErrors()) {
			model.addAttribute("passIncorrect","The password is incorrect! Please, try again.");
			HashMap<Integer,String> tempMap = populateHashMap(pd);
			model.addAttribute("personalData",pd);
			model.addAttribute("tempMap",tempMap);
			return "payment_payment_method";
		}
		else{
			model.addAttribute("personalData", personalData);
			model.addAttribute("pd",pd);
			model.addAttribute("userCrm",userCrm);
			model.addAttribute("creditCard",creditCard);
			model.addAttribute("cart",cart);
			model.addAttribute("book",book);
			return "payment_summary"; 
		}
	}
	
	@PostMapping("/cancelPayment")
	public String summaryPage() {
		return "redirect:/User/shopping_cart"; 
	}
	
	@PostMapping("/createOrder")
	public String paymentConfirmationPage(Principal principal, @ModelAttribute("pd")PersonalData pd) {
		UserCrm userCrm = userCrmService.getUser(principal.getName());
		Cart cart = cartService.getCart(userCrm.getCart().getId());
		System.out.println(pd.getCardId());
		transactionService.createTransaction(userCrm, cart, pd);
		for(Book i : cart.getBooks()) {
			bookService.removeBookFromCartPernamently(cart.getId(), i.getId());
		}
		return "payment_confirmation";
	}
	
	private HashMap<Integer,String> populateHashMap( PersonalData pd){
		HashMap<Integer,String> tempMap = new LinkedHashMap<>();
		List<CreditCard> cards = creditCardService.getCreditCards(pd);
		for(CreditCard i : cards) {
			tempMap.put(i.getId(), i.getCardType()+" "+i.getNumber());
		}
		return tempMap;
	}

}
