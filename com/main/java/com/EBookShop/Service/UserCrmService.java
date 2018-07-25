package com.EBookShop.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.EBookShop.DAO.IUserCrmDAO;
import com.EBookShop.Entity.UserCrm;
@Service
public class UserCrmService implements IUserCrmService {

	@Autowired
	IUserCrmDAO userDAO; 
	
	@Override
	@Transactional
	public List<UserCrm> getUsers() {
		List<UserCrm> users = userDAO.getUsers();
		return users;
	}

	@Override
	@Transactional
	public UserCrm getUser(String username) {
		UserCrm user = userDAO.getUser(username);
		return user;
	}

	@Override
	@Transactional
	public void updateUserCrm(UserCrm userCrm) {
		userDAO.updateUserCrm(userCrm);
	}

	@Override
	@Transactional
	public List<UserCrm> searchUsers(String searchCategory, String searchWord) {
		List<UserCrm> users = userDAO.searchUsers(searchCategory, searchWord);
		return users;
	}

}
