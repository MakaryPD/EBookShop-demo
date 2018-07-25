package com.EBookShop.DAO;

import java.util.List;

import com.EBookShop.Entity.Cart;
import com.EBookShop.Entity.UserCrm;

public interface IUserCrmDAO {
	public List<UserCrm> getUsers(); 
	public List<UserCrm> searchUsers(String searchCategory, String searchWord);
	public UserCrm getUser(String username);
	public void updateUserCrm(UserCrm userCrm); 
	public void addCart(Cart cart);
}
