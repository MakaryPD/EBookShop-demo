package com.EBookShop.Service;

import java.util.List;

import com.EBookShop.Entity.UserCrm;

public interface IUserCrmService {

	public List<UserCrm> getUsers();
	public List<UserCrm> searchUsers(String searchCategory, String searchWord);
	public UserCrm getUser(String username);
	public void updateUserCrm(UserCrm userCrm);
	
}
