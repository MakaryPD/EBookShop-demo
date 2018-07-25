package com.EBookShop.DAO;

import java.util.List;

import com.EBookShop.Entity.Cart;
import com.EBookShop.Entity.PersonalData;
import com.EBookShop.Entity.Transaction;
import com.EBookShop.Entity.UserCrm;

public interface ITransactionDAO {

	public void createTransaction(UserCrm userCrm, Cart cart,PersonalData pd);
	public List<Transaction> getUserTransactions(UserCrm userCrm);
	public List<Transaction> getUserTransactions();
	public List<Transaction> searchTransactions(String searchCategory, String searchWord);
	public Transaction getTransaction(int id);
	
}
