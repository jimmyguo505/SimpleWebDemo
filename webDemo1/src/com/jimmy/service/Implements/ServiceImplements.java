package com.jimmy.service.Implements;

import com.jimmy.dao.AppDao;
import com.jimmy.dao.Implments.DaoImplements;
import com.jimmy.domin.User;
import com.jimmy.exception.UserExistException;
import com.jimmy.service.AppService;

public class ServiceImplements implements AppService{

	AppDao appDao = new DaoImplements();
	
	//
	public User logIn(User user) {
		User findUser = appDao.findUser(user);
		return findUser;
	}
	
	//
	public void register(User user) {
		appDao.insertUser(user);
	}
	
	//
	public boolean checkUserByName(String name) throws Exception {
		boolean bl = appDao.findUserByName(name);
		if (bl) {
			throw new UserExistException("用户名已存在！");
		}
		return bl;
	}

}
