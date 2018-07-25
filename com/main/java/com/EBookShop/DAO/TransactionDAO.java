package com.EBookShop.DAO;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.EBookShop.Entity.Book;
import com.EBookShop.Entity.Cart;
import com.EBookShop.Entity.CreditCard;
import com.EBookShop.Entity.PersonalData;
import com.EBookShop.Entity.Transaction;
import com.EBookShop.Entity.UserCrm;
import com.EBookShop.Service.ICreditCardService;

@Repository
public class TransactionDAO implements ITransactionDAO {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	ICreditCardService creditCardService;
	@Autowired
	IUserCrmDAO userCrmDAO;
	
	@Override
	public void createTransaction(UserCrm userCrm, Cart cart,PersonalData pd) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = new Transaction();
		List<Book> tempBooks = new ArrayList<>();
		tempBooks.addAll(cart.getBooks());
		transaction.setBooks(tempBooks);
		transaction.setUserCrm(userCrm);
		transaction.setTotalCost(cart.getTotalCost());
		transaction.setSize(cart.getSize());
		CreditCard creditCard = creditCardService.getCreditCard(pd.getCardId());
		transaction.setCreditCard(creditCard);
		transaction.setAdress(pd.getAdress());
		session.save(transaction);
	}

	@Override
	public List<Transaction> getUserTransactions(UserCrm userCrm) {
		Session session = sessionFactory.getCurrentSession();
		Query<Transaction> query = session.createQuery("FROM Transaction t WHERE t.userCrm LIKE '"+userCrm.getUsername()+"'",Transaction.class);
		List<Transaction> transactions = query.getResultList();
		return transactions;
	}
	public List<Transaction> getUserTransactions() {
		Session session = sessionFactory.getCurrentSession();
		Query<Transaction> query = session.createQuery("FROM Transaction",Transaction.class);
		List<Transaction> transactions = query.getResultList();
		return transactions;
	}

	@Override
	public Transaction getTransaction(int id) {
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.get(Transaction.class, id);
		return transaction;
	}

	@Override
	public List<Transaction> searchTransactions(String searchCategory, String searchWord) {
		Session session = sessionFactory.getCurrentSession();
		List<Transaction> transactions = getUserTransactions(); 
		transactions.removeAll(transactions);
		if(searchCategory.equals("id")) {
			Query<Transaction> query = session.createQuery("FROM Transaction t WHERE t."+searchCategory+" LIKE '"+searchWord+"'",Transaction.class);
			transactions = query.getResultList();
		}else if(searchCategory.equals("totalCostLess")) {
			Query<Transaction> query = session.createQuery("FROM Transaction t WHERE t.totalCost <= '"+Float.parseFloat(searchWord)+"'",Transaction.class);
			transactions = query.getResultList();
		}else if(searchCategory.equals("totalCostMore")) {
			Query<Transaction> query = session.createQuery("FROM Transaction t WHERE t.totalCost >= '"+Float.parseFloat(searchWord)+"'",Transaction.class);
			transactions = query.getResultList();
		}else if(searchCategory.equals("username")){
			List<UserCrm> users = userCrmDAO.searchUsers(searchCategory, searchWord);
			System.out.println(users.size());
			for(UserCrm user: users) {
				Query<Transaction> query  = session.createQuery("FROM Transaction t WHERE t.userCrm LIKE '"+user.getUsername()+"'",Transaction.class);
				transactions = query.getResultList();
			}
		}
		return transactions;
	}
}
