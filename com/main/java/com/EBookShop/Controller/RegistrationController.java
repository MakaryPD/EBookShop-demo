package com.EBookShop.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.EBookShop.Entity.UserCrm;
import com.EBookShop.Registration.UserRegistration;
import com.EBookShop.Service.ICartService;
import com.EBookShop.Service.IPersonalDataService;
import com.EBookShop.Service.IUserCrmService;


@Controller
@RequestMapping("/register")
public class RegistrationController {
	
	@Autowired
	private UserDetailsManager userDetailsManager;
	@Autowired
	ICartService cartService;
	@Autowired
	IUserCrmService userService;
	@Autowired
	IPersonalDataService personalDataService;

	
	
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@GetMapping("/showRegistrationForm")
	public String showRegistrationPage(Model model) {
		model.addAttribute("user",new UserRegistration());
		return "registration_form";
	}
	
	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(@Valid @ModelAttribute("user") UserRegistration userRegistration,
										 BindingResult bidingResult,
										 Model model) {
	String username = userRegistration.getUsername();
	// form validation
	if(bidingResult.hasErrors()) {
		model.addAttribute("user",new UserRegistration());
		model.addAttribute("registrationError","Fields with * are required.");
		return "registration_form";
	}
	// check the database if user already exists
	boolean userExists = doesUserExist(username);
	if (userExists) {
		model.addAttribute("user", new UserRegistration());
		model.addAttribute("registrationError", "User name already exists.");	
		return "registration_form";		
	}
	// encrypt the password
	String encodedPassword = passwordEncoder.encode(userRegistration.getPassword());
	 // prepend the encoding algorithm id
	encodedPassword = "{bcrypt}" + encodedPassword;
	// give user default role of "user"
	List<GrantedAuthority> authorities =AuthorityUtils.createAuthorityList("ROLE_USER");	
	 // create user details object
	 User tempUser = new User(username, encodedPassword, authorities);
	 // save user in the database
	 userDetailsManager.createUser(tempUser);
	 UserCrm user2 = userService.getUser(tempUser.getUsername());
	 cartService.saveCart(user2);
	if(userRegistration.isCheckbox()) {
		personalDataService.savePersonalData(userRegistration, user2, true);
	}else {
		personalDataService.savePersonalData(userRegistration, user2, false);
	}
	 return "registration_confirmation";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
	StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
	dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	private boolean doesUserExist(String username) {
		// check the database if the user already exists
		boolean exists = userDetailsManager.userExists(username);
		return exists;
		}
}
