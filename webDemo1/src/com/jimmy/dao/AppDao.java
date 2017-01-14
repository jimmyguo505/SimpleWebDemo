package com.jimmy.dao;

import com.jimmy.domin.User;

public interface AppDao {
	//定义一个插入数据库的函数。用于注册等
	public void insertUser(User user);
	//定义一个查找用户的函数。用于登录等
	public User findUser(User user);
	//定义一个检查用户名是否存在的方法。用于注册时检测用户名是否存在
	public boolean findUserByName(String name); 
	
}
