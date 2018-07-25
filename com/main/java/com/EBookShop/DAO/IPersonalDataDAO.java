package com.EBookShop.DAO;

import java.util.List;

import com.EBookShop.Entity.PersonalData;
import com.EBookShop.Entity.UserCrm;
import com.EBookShop.Registration.UserRegistration;

public interface IPersonalDataDAO {

	public List<PersonalData> searchPersonalData(String searchCategroy, String searchWord);
	public void savePersonalData(UserRegistration userRegistration, UserCrm userCrm, boolean sameRegisterAndContactAdress);
	public PersonalData getPerosnalData(int id);
	public void updatePersonalData(PersonalData personalData, UserCrm userCrm); 
}
