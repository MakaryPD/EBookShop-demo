package com.EBookShop.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EBookShop.DAO.ITransactionDAO;
import com.EBookShop.Entity.Cart;
import com.EBookShop.Entity.PersonalData;
import com.EBookShop.Entity.Transaction;
import com.EBookShop.Entity.UserCrm;

@Service
public class TransactionService implements ITransactionService {

	@Autowired
	ITransactionDAO transactionDAO;

	@Override
	@Transactional
	public void createTransaction(UserCrm userCrm, Cart cart, PersonalData pd) {
		transactionDAO.createTransaction(userCrm,cart, pd);
	}

	@Override
	@Transactional
	public List<Transaction> getUserTransactions(UserCrm userCrm) {
		List<Transaction> transactions = transactionDAO.getUserTransactions(userCrm);
		return transactions;
	}
	@Override
	@Transactional
	public List<Transaction> getUserTransactions() {
		List<Transaction> transactions = transactionDAO.getUserTransactions();
		return transactions;
	}

	@Override
	@Transactional
	public Transaction getTransaction(int id) {
		Transaction transaction = transactionDAO.getTransaction(id);
		return transaction;
	}

	@Override
	@Transactional
	public List<Transaction> searchTransactions(String searchCategory, String searchWord) {
		List<Transaction> transactions = transactionDAO.searchTransactions(searchCategory, searchWord);
		return transactions;
	}
}
