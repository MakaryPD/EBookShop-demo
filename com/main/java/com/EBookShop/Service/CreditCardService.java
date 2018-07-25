package com.EBookShop.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EBookShop.DAO.ICreditCardDAO;
import com.EBookShop.Entity.CreditCard;
import com.EBookShop.Entity.PersonalData;

@Service
public class CreditCardService implements ICreditCardService {

	@Autowired
	ICreditCardDAO creditCardDAO;

	@Override
	@Transactional
	public List<CreditCard> getCreditCards(PersonalData personalData) {
		List<CreditCard> creditCards = creditCardDAO.getCreditCards(personalData);
		return creditCards;
	}

	@Override
	@Transactional
	public CreditCard getCreditCard(int id) {
		CreditCard card = creditCardDAO.getCreditCard(id);
		return card;
	}

	@Override
	@Transactional
	public void updateCreditCard(CreditCard card,PersonalData personalData) {
	creditCardDAO.updateCreditCard(card,personalData);
	} 
}
