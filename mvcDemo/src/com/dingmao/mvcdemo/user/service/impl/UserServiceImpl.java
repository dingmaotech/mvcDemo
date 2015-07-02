package com.dingmao.mvcdemo.user.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dingmao.mvcdemo.user.dao.UserDao;
import com.dingmao.mvcdemo.user.model.User;
import com.dingmao.mvcdemo.user.service.UserService;
import com.dingmao.platform.service.ServiceException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(readOnly=true)
	public User getById(String id) throws ServiceException {
		return userDao.get(id);
	}

	@Override
	@Transactional
	public User save(User entity) throws ServiceException {
		return userDao.save(entity);
	}

	@Override
	@Transactional
	public void update(User entity) throws ServiceException {
		userDao.update(entity);
	}

	@Override
	@Transactional
	public void delete(Collection<String> ids) throws ServiceException {
		userDao.delete(ids);
	}

	@Override
	@Transactional
	public int delete(String id) throws ServiceException {
		return userDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAll() throws ServiceException {
		return userDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public User getUserByUsername(String name) {
		return userDao.getUserByUsername(name);
	}

	@Override
	@Transactional
	public void pwdInit(User user, String password) {
		userDao.pwdInit(user, password);
	}

	@Override
	@Transactional
	public User checkUserPwd(User user) {
		return userDao.checkUserPwd(user);
	}


}
