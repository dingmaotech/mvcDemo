package com.dingmao.mvcdemo.user.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dingmao.mvcdemo.user.model.User;
import com.dingmao.platform.dao.base.HibernateDao;

/**
 * 
 * @author Administrator
 *
 */
@Repository
public class UserDao extends HibernateDao<User, String> {

	/**
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username) {
		String hql = " from  User u WHERE u.username = ? ";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, username);
		List list = query.list();
		return list.isEmpty() ? null : (User) list.get(0);
	}

	public void pwdInit(User user, String newPwd) {
		String hql = "from User u where u.username = :username ";
		Query query = getSession().createQuery(hql);
		query.setParameter("username", user.getUsername());
		List users = query.list();
		if (users != null && users.size() > 0) {
			user = (User) users.get(0);
			user.setPassword(newPwd);
			save(user);
		}
	}

	public User checkUserPwd(User user) {
		String hql = "from User u where u.username = :username and u.password=:passowrd";
		Query query = getSession().createQuery(hql);
		query.setParameter("username", user.getUsername());
		query.setParameter("passowrd", user.getPassword());
		List users = query.list();
		if (users != null && users.size() > 0)
			return (User) users.get(0);
		else
			return null;
	}
}
