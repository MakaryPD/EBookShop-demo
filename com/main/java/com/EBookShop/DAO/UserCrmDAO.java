package com.EBookShop.DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.EBookShop.Entity.Cart;
import com.EBookShop.Entity.PersonalData;
import com.EBookShop.Entity.UserCrm;
@Repository
public class UserCrmDAO implements IUserCrmDAO {

	@Autowired
	public SessionFactory sessionFactory; 
	
	@Autowired
	private IPersonalDataDAO personalDataDAO; 
	
	@Override
	public List<UserCrm> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		Query<UserCrm> query = session.createQuery("FROM UserCrm",UserCrm.class);
		List<UserCrm> users = query.getResultList();
		return users;
	}

	@Override
	public UserCrm getUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		Query<UserCrm> query = session.createQuery("FROM UserCrm u WHERE u.username LIKE '"+username+"'",UserCrm.class);
		UserCrm user = query.getSingleResult();
		return user;
	}

	@Override
	public void addCart(Cart cart) {
		Session session = sessionFactory.getCurrentSession();
		UserCrm user = new UserCrm(); 
		user.setCart(cart);
		session.saveOrUpdate(user);
	}

	@Override
	public void updateUserCrm(UserCrm userCrm) {
		Session session = sessionFactory.getCurrentSession();
		session.update(userCrm);
	}

	@Override
	public List<UserCrm> searchUsers(String searchCategory, String searchWord) {
		Session session = sessionFactory.getCurrentSession();
		List<UserCrm> users  = getUsers();
		users.removeAll(users);
		if(searchCategory.equals("username")) {
			Query<UserCrm> query = session.createQuery("FROM UserCrm u WHERE u."+searchCategory+" LIKE '%"+searchWord+"%'",UserCrm.class);
			users = query.getResultList();
		}else {
			List<PersonalData> personalDatas = personalDataDAO.searchPersonalData(searchCategory, searchWord);
			for(PersonalData pd: personalDatas) {
				Query<UserCrm> query = session.createQuery("FROM UserCrm u WHERE u.personalData LIKE '"+pd.getId()+"'",UserCrm.class);
				UserCrm tempUserCrm = query.getSingleResult();
				users.add(tempUserCrm);
			}
		}
		return users;
	}


}
