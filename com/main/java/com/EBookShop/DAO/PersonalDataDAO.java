package com.EBookShop.DAO;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.EBookShop.Entity.PersonalData;
import com.EBookShop.Entity.UserCrm;
import com.EBookShop.Registration.UserRegistration;

@Repository
public class PersonalDataDAO implements IPersonalDataDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void savePersonalData(UserRegistration userRegistration, UserCrm user, boolean sameRegisterAndContactAdress) {
		Session session = sessionFactory.getCurrentSession();
		PersonalData personalData = new PersonalData();
		personalData.setFirstName(userRegistration.getFirstName());
		personalData.setLastName(userRegistration.getLastName());
		personalData.setEmail(userRegistration.getEmail());
		personalData.setUserCrm(user);
		
		personalData.setRegPostal(userRegistration.getReg_postal());
		personalData.setRegCity(userRegistration.getReg_city());
		personalData.setRegStreet(userRegistration.getReg_street());
		personalData.setRegHouseNr(userRegistration.getReg_houseNr());
		personalData.setRegApartmentNr(userRegistration.getReg_apartmentNr());
		
		if(sameRegisterAndContactAdress) {
			personalData.setContactPostal(userRegistration.getReg_postal());
			personalData.setContactCity(userRegistration.getReg_city());
			personalData.setContactStreet(userRegistration.getReg_street());
			personalData.setContactHouseNr(userRegistration.getReg_houseNr());
			personalData.setContactApartmentNr(userRegistration.getReg_apartmentNr());
		}else if(!sameRegisterAndContactAdress) {
			personalData.setContactPostal(userRegistration.getContact_postal());
			personalData.setContactCity(userRegistration.getContact_city());
			personalData.setContactStreet(userRegistration.getContact_street());
			personalData.setContactHouseNr(userRegistration.getContact_houseNr());
			personalData.setContactApartmentNr(userRegistration.getContact_apartmentNr());
		}
		user.setPersonalData(personalData);
		session.save(personalData);
		session.saveOrUpdate(user);
	}

	@Override
	public PersonalData getPerosnalData(int id) {
		Session session = sessionFactory.getCurrentSession();
		PersonalData personalData = session.get(PersonalData.class, id);
		return personalData;
	}

	@Override
	public void updatePersonalData(PersonalData personalData, UserCrm userCrm) {
		Session session = sessionFactory.getCurrentSession();
		userCrm.setPersonalData(personalData);
		session.saveOrUpdate(personalData);
		session.saveOrUpdate(userCrm);
		
	}

	@Override
	public List<PersonalData> searchPersonalData(String searchCategroy, String searchWord) {
		Session session = sessionFactory.getCurrentSession();
		Query<PersonalData> query = session.createQuery("FROM PersonalData pd WHERE pd."+searchCategroy+" LIKE '%"+searchWord+"%'",PersonalData.class);
		List<PersonalData> personalDatas = query.getResultList();
		return personalDatas;
		
	}


}
