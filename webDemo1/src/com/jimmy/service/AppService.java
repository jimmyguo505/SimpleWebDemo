package com.jimmy.service;

import com.jimmy.domin.User;

public interface AppService {
	
	//应用中有登陆功能函数
	public User logIn(User user); 
	//应用中有注册功能函数
	public void register(User user); 
	//应用中应有检查用户名是否存在的方法
	public boolean checkUserByName(String name) throws Exception;
}
