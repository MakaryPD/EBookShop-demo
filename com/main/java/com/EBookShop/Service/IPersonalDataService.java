package com.EBookShop.Service;

import com.EBookShop.Entity.PersonalData;
import com.EBookShop.Entity.UserCrm;
import com.EBookShop.Registration.UserRegistration;

public interface IPersonalDataService {

	public void savePersonalData(UserRegistration userRegistration, UserCrm userCrm, boolean sameRegisterAndContactAdress);
	public PersonalData getPersonalData(int id);
	public void updatePersonalData(PersonalData personalData,UserCrm userCrm); 
	public void removeCreditCard(int cardId, int personalDataId); 
}
