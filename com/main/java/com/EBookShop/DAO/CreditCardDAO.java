package com.EBookShop.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.EBookShop.Entity.CreditCard;
import com.EBookShop.Entity.PersonalData;

@Repository
public class CreditCardDAO implements ICreditCardDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<CreditCard> getCreditCards(PersonalData personalData) {
		Session session = sessionFactory.getCurrentSession();
		Query<CreditCard> query = session.createQuery("FROM CreditCard c WHERE c.personalData LIKE '"+personalData.getId()+"'",CreditCard.class);
		List<CreditCard> creditCards = query.getResultList();
		return creditCards;
	}

	@Override
	public CreditCard getCreditCard(int id) {
		Session session = sessionFactory.getCurrentSession();
		CreditCard card = session.get(CreditCard.class, id);
		return card;
	}

	@Override
	public void updateCreditCard(CreditCard card,PersonalData personalData) {
		Session session = sessionFactory.getCurrentSession();
		card.setPersonalData(personalData);
		session.saveOrUpdate(card);
		
	}

	@Override
	public void removeCreditCard(CreditCard creditCard) {
		Session session = sessionFactory.getCurrentSession();
		session.remove(creditCard);
		
	}

	
	
}
