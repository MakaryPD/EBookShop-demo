package com.EBookShop.DAO;

import java.util.List;

import com.EBookShop.Entity.CreditCard;
import com.EBookShop.Entity.PersonalData;

public interface ICreditCardDAO {

	public List<CreditCard> getCreditCards(PersonalData personalData); 
	public CreditCard getCreditCard(int id);
	public void updateCreditCard(CreditCard card,PersonalData personalData);
	public void removeCreditCard(CreditCard creditCard);
}
