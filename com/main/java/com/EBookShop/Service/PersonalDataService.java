package com.EBookShop.Service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EBookShop.DAO.ICreditCardDAO;
import com.EBookShop.DAO.IPersonalDataDAO;
import com.EBookShop.Entity.CreditCard;
import com.EBookShop.Entity.PersonalData;
import com.EBookShop.Entity.UserCrm;
import com.EBookShop.Registration.UserRegistration;

@Service
public class PersonalDataService implements IPersonalDataService {

	@Autowired
	IPersonalDataDAO personalDataDAO;
	@Autowired
	ICreditCardDAO creditCardDAO;

	@Override
	@Transactional
	public void savePersonalData(UserRegistration userRegistration, UserCrm userCrm, boolean sameRegisterAndContactAdress) {
		personalDataDAO.savePersonalData(userRegistration, userCrm, sameRegisterAndContactAdress);
		
	}

	@Override
	@Transactional
	public PersonalData getPersonalData(int id) {
		PersonalData personalData = personalDataDAO.getPerosnalData(id);
		return personalData;
	}

	@Override
	@Transactional
	public void updatePersonalData(PersonalData personalData,UserCrm userCrm) {
		personalDataDAO.updatePersonalData(personalData,userCrm);
		
	}

	@Override
	@Transactional
	public void removeCreditCard(int cardId, int personalDataId) {
		PersonalData personalData = personalDataDAO.getPerosnalData(personalDataId);
		CreditCard creditCard = creditCardDAO.getCreditCard(cardId);
		personalData.getCreditCards().remove(creditCard);
		creditCardDAO.removeCreditCard(creditCard);
	} 
}
