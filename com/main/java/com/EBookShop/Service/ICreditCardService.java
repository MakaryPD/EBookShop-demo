package com.EBookShop.Service;

import java.util.List;

import com.EBookShop.Entity.CreditCard;
import com.EBookShop.Entity.PersonalData;

public interface ICreditCardService {

	public List<CreditCard> getCreditCards(PersonalData personalData); 
	public CreditCard getCreditCard(int id);
	public void updateCreditCard(CreditCard card,PersonalData personalData);
}
